package com.company;

import java.util.Random;

public class Consumidor extends Thread {
    private final Contenedor contenedor;
    private final int idconsumidor;
    private final Random aleatorio;

    public Consumidor(Contenedor contenedor, int idconsumidor) {
        this.contenedor = contenedor;
        this.idconsumidor = idconsumidor;
        aleatorio = new Random();
    }

    @Override
    public void run() {
        while (true) {
            int coger = contenedor.get(aleatorio.nextInt(300));
            System.out.println("El consumidor " + idconsumidor + " consume: " + coger);
        }
    }
}
