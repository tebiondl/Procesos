package com.company;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Lampert extends Thread{

    public int id;

    public Lampert(int id) {
        this.id = id;
    }

    public void run() {
        for (int i = 0; i < LampertStart.pedidosMax; i++) {

            lock(this.id);
            //Comienza la sección crítica
            LampertStart.count += 1;
            System.out.println("Soy el hilo " + this.id + " y el pedido que estoy haciendo ahora es el: " + LampertStart.count);

            //Hacer que el hilo espere
            try {
                double espera = (3000 * Math.random()) + 1000;
                DecimalFormat df = new DecimalFormat("0.00");
                System.out.println("Mi pedido va a tardar: "+df.format(espera*0.001)+" segundos");
                sleep((int)espera);
            } catch (InterruptedException e) {}
            //Final de la sección crítica
            unlock(this.id);
        }
    }

    //Bloquea los hilos mientras están pidiendo
    public void lock(int id) {
        //El sigueinte hilo quiere pedir
        LampertStart.eligiendo[id] = true;

        //Encontrar el último turno para saber qué turno le toca a este hilo
        LampertStart.ticket[id] = findMax() + 1;
        LampertStart.eligiendo[id] = false;

        for (int j = 0; j < LampertStart.numerohilos; j++) {

            //Si el hilo j es este hilo, continuar al siguiente
            if (j == id)
                continue;

            //Esperar si el hilo j ya está pidiendo
            while (LampertStart.eligiendo[j]) {}

            while (LampertStart.ticket[j] != 0 && (LampertStart.ticket[id] > LampertStart.ticket[j] || (LampertStart.ticket[id] == LampertStart.ticket[j] && id > j))) {}

        }
    }

    //Desbloquear el hilo
    private void unlock(int id) {
        LampertStart.ticket[id] = 0;
    }

    //Encuentra el último turno para pedir
    private int findMax() {

        int m = LampertStart.ticket[0];

        for (int i = 1; i < LampertStart.ticket.length; i++) {
            if (LampertStart.ticket[i] > m)
                m = LampertStart.ticket[i];
        }
        return m;
    }

}
