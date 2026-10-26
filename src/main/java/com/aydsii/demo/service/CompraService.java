package com.aydsii.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.aydsii.demo.Estados;
import com.aydsii.demo.SistemaPagoSim;
import com.aydsii.demo.entity.Carrito;
import com.aydsii.demo.entity.Cliente;
import com.aydsii.demo.entity.DetallePedido;
import com.aydsii.demo.entity.ItemCarrito;
import com.aydsii.demo.entity.Pedido;
import com.aydsii.demo.entity.Producto;
import com.aydsii.demo.repository.CarritoRepository;
import com.aydsii.demo.repository.ClienteRepository;
import com.aydsii.demo.repository.DetallePedidoRepository;
import com.aydsii.demo.repository.ItemCarritoRepository;
import com.aydsii.demo.repository.PedidoRepository;
import com.aydsii.demo.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final ClienteRepository clienteRepository;
    private final CarritoRepository carritoRepository;
    private final ItemCarritoRepository itemCarritoRepository;
    private final ProductoRepository productoRepository;
    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final SistemaPagoSim sistemaPagoSim;

    
    @Transactional
    public Map<String, Object> comprar(Integer clienteId, String numeroTarjeta, String direccion) {
        Cliente cliente = buscarCliente(clienteId);
        Carrito carrito = carritoRepository.findByClienteId(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "El cliente no tiene carrito"));

        List<DetallePedido> detalles = detalleDelCarrito(carrito);
        if (detalles.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El carrito está vacío");
        }

        validarTarjeta(numeroTarjeta);
        descontarStockDe(detalles);
        Pedido pedido = registrarPedido(cliente, detalles, direccion);

        itemCarritoRepository.eliminarPorCarritoId(carrito.getId());
        return armarRespuesta(pedido, detalles);
    }

    @Transactional
    public Map<String, Object> compraUnitaria(Integer clienteId, Integer productoId, Integer cantidad,
            String numeroTarjeta, String direccion) {
        if (cantidad == null || cantidad < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad debe ser al menos 1");
        }
        Cliente cliente = buscarCliente(clienteId);
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Producto inexistente: " + productoId));

        validarTarjeta(numeroTarjeta);
        List<DetallePedido> detalles = List.of(detalleProducto(producto, cantidad));
        descontarStockDe(detalles);
        Pedido pedido = registrarPedido(cliente, detalles, direccion);

        return armarRespuesta(pedido, detalles);
    }

    private Cliente buscarCliente(Integer clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente inexistente: " + clienteId));
    }

    private void validarTarjeta(String numeroTarjeta) {
        if (!sistemaPagoSim.esTarjetaValida(numeroTarjeta)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tarjeta inválida: compra no realizada");
        }
    }

    private List<DetallePedido> detalleDelCarrito(Carrito c) {
        List<DetallePedido> detalles = new ArrayList<>();
        for (ItemCarrito item : itemCarritoRepository.buscarPorCarritoId(c.getId())) {
            detalles.add(detalleProducto(item.getProducto(), item.getCantidad()));
        }
        return detalles;
    }

    private DetallePedido detalleProducto(Producto p, Integer cant) {
        if (p.getActivo() == false) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "El producto no está disponible: " + p.getNombre());
        }
        DetallePedido detalle = new DetallePedido();
        detalle.setProducto(p);
        detalle.setCantidad(cant);
        detalle.setPrecioUnitario(p.getPrecio()); // el precio queda fijado al comprar
        return detalle;
    }

    private void descontarStockDe(List<DetallePedido> detalles) {
        for (DetallePedido detalle : detalles) {
            int filasAfectadas = productoRepository.descontarStock(
                    detalle.getProducto().getId(), detalle.getCantidad());
            if (filasAfectadas == 0) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Stock insuficiente para: " + detalle.getProducto().getNombre());
            }
        }
    }

    private Pedido registrarPedido(Cliente c, List<DetallePedido> d, String dir) {
        double total = d.stream()
                .mapToDouble(detalle -> detalle.getPrecioUnitario() * detalle.getCantidad())
                .sum();

        Pedido pedido = new Pedido();
        pedido.setCliente(c);
        pedido.setFechaRealizacion(LocalDateTime.now());
        pedido.setEstado(Estados.ACEPTADO);
        pedido.setTotal(total);
        pedido.setDireccion(dir);
        pedido = pedidoRepository.save(pedido);

        for (DetallePedido detalle : d) {
            detalle.setPedido(pedido);
        }
        detallePedidoRepository.saveAll(d);
        return pedido;
    }

    private Map<String, Object> armarRespuesta(Pedido pedido, List<DetallePedido> detalles) {
        List<Map<String, Object>> productosComprados = new ArrayList<>();
        for (DetallePedido detalle : detalles) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("producto", detalle.getProducto().getNombre());
            item.put("cantidad", detalle.getCantidad());
            item.put("precioUnitario", detalle.getPrecioUnitario());
            productosComprados.add(item);
        }

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("montoTotal", pedido.getTotal());
        respuesta.put("productosComprados", productosComprados);
        respuesta.put("llegadaEstimada", calcularLlegada());
        return respuesta;
    }

    private LocalDate calcularLlegada() {
        return LocalDate.now().plusDays(ThreadLocalRandom.current().nextInt(3, 8));
    }
}