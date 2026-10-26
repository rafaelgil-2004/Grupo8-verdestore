package com.aydsii.demo;

import org.springframework.stereotype.Component;

@Component
public class SistemaPagoSim {

    public boolean esTarjetaValida(String numTarjeta) {
        if (numTarjeta == null) {
            return false;
        }
        String digitos = numTarjeta.replaceAll("[\\s-]", "");
        if (!digitos.matches("\\d{13,19}")) {
            return false;
        }
        return true;
    }
}