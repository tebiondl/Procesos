package com.company;

public class LampertStart {

    //variables globales
    public static final int pedidosMax = 200;
    public static final int numerohilos = 5; //Se puede cambiar y sigue funcionando perfectamente
    public static volatile int count = 0; //Contador para testear que funciona
    public static volatile boolean[] eligiendo = new boolean[numerohilos]; //Contiene qué hilos están pidiendo
    public static volatile int[] ticket = new int[numerohilos]; //Contiene el turno para ser atendido de cada hilo

    public static void main(String[] args) {
        //Inicializar variables
        for (int i = 0; i < numerohilos; i++) {
            eligiendo[i] = false;
            ticket[i] = 0;
        }

        Lampert[] threads = new Lampert[numerohilos];

        //Inicializar los hilos
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Lampert(i);
            threads[i].start();
        }

        //Esperar a que los hilos terminen
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nPedidos hechos: " + count);
        System.out.println("\nPedidos esperados: " + (pedidosMax * numerohilos));
    }

}
