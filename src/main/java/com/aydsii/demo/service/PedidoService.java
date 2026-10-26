package com.aydsii.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aydsii.demo.entity.DetallePedido;
import com.aydsii.demo.entity.Pedido;
import com.aydsii.demo.entity.Producto;
import com.aydsii.demo.model.HistorialRespuestaDTO;
import com.aydsii.demo.model.ProductoHistorialDTO;
import com.aydsii.demo.repository.DetallePedidoRepository;
import com.aydsii.demo.repository.PedidoRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         DetallePedidoRepository detallePedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
    }

   
    public List<HistorialRespuestaDTO> consultarHistorial(Integer clienteId) {

        //pedidos del cliente
        List<Pedido> pedidos =
            pedidoRepository.findByClienteIdOrderByFechaRealizacionDesc(clienteId);

        if (pedidos.isEmpty()) {
            return List.of();
        }

        //ids de esos pedidos
        List<Integer> pedidoIds = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            pedidoIds.add(pedido.getId());
        }

        //detalles de esos pedidos
        List<DetallePedido> detalles =
            detallePedidoRepository.findByPedidoIdIn(pedidoIds);

        //agrupar los detalles por pedido
        Map<Integer, List<DetallePedido>> detallesPorPedido = new HashMap<>();

        for (DetallePedido detalle : detalles) {
            Integer pedidoId = detalle.getPedido().getId();

            if (!detallesPorPedido.containsKey(pedidoId)) {
                detallesPorPedido.put(pedidoId, new ArrayList<>());
            }

            detallesPorPedido.get(pedidoId).add(detalle);
        }

        //crear la respuesta
        List<HistorialRespuestaDTO> historial = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            List<DetallePedido> detallesDelPedido =
                detallesPorPedido.getOrDefault(pedido.getId(), List.of());

            historial.add(armarDTO(pedido, detallesDelPedido));
        }

        return historial;
    }

    private HistorialRespuestaDTO armarDTO(Pedido pedido, List<DetallePedido> detalles) {

        List<ProductoHistorialDTO> productos = new ArrayList<>();

        for (DetallePedido detalle : detalles) {
            Producto producto = detalle.getProducto();

            productos.add(new ProductoHistorialDTO(
                producto.getNombre(),
                producto.getDescripcion(),
                detalle.getCantidad(),
                detalle.getCantidad() * detalle.getPrecioUnitario()
            ));
        }

        return new HistorialRespuestaDTO(
            pedido.getTotal(),
            pedido.getFechaRealizacion(),
            pedido.getEstado(),
            pedido.getUltimos4(),
            pedido.getDireccion(),
            pedido.getMotivo(),
            productos
        );
    }
}
