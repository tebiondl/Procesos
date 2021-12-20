package com.company;

import java.util.concurrent.Semaphore;

public class FilosofosComen {

    final static int N = 5;
    public static Semaphore[] tenedores = new Semaphore[N];
    public static int comidas = 10; //Comidas max

    public static void main(String[] args)
    {
        System.out.println("Empezamos");
        tenedores = new Semaphore[N];

        //final int N = 5; // five philosophers, five forks

        // Create the forks, 1 fork per philosopher
        //Semaphore[] fork = new Semaphore[N];
        for (int f = 0; f < N; f++) {
            // each fork is a single resource
            tenedores[f] = new Semaphore(1, true);
        }

        // Create the philosophers, pass in their forks
        Filosofo[] philosopher = new Filosofo[N];
        for (int me = 0; me < N; me++) {
            // determine my right-hand neighbor's ID
            int myneighbor = me + 1;
            if (myneighbor == N) myneighbor = 0;

            // Initialize each philosopher (no pun intended)
            philosopher[me] = new Filosofo(me, tenedores[me], tenedores[myneighbor], tenedores, N, comidas);
        }

        // Start the philosophers
        for (int i = 0; i < N; i++) {
            philosopher[i].start();
        }

        // Wait for them to finish
        for (int i = 0; i < N; i++) {
            try {
                philosopher[i].join();
            } catch(InterruptedException ex) { }
        }

        // All done
        System.out.println("Terminamos");
    }

}
