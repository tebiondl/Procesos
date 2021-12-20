package com.company;

import java.util.concurrent.Semaphore;
import java.util.Random;


public class Filosofo extends Thread
{
    private static final Random rand = new Random();
    private static int event=0;
    private int oneOnTop;

    private Semaphore[] tenedores;
    private int id;
    private Semaphore miTenedor;
    private Semaphore tenedorVecino;
    private int comidas;

    //Constructor
    public Filosofo(int i, Semaphore tenedor1, Semaphore tenedor2, Semaphore[] tenedores, int oneOnTop, int comidas)
    {
        id = i;
        miTenedor = tenedor1;
        tenedorVecino = tenedor2;
        this.tenedores = tenedores;
        this.oneOnTop = oneOnTop;
        this.comidas = comidas;
    }

    private void mensaje(String str) {
        System.out.println(++event + " Tenedores Restantes: "+getTopOne()+".El Filósofo     "+id+" "+str);
    }

    //Pausamos al filósofo para que espere x segundos

    private void pausar()
    {
        try
        {
            sleep(rand.nextInt(4000));
        } catch (InterruptedException e){}
    }


    //El filósofo piensa, por lo cual espera

    private void pensar()
    {
        mensaje("está pensando");
        pausar();
    }

    private synchronized void cogerTenedor()
    {
        oneOnTop--;
    }

    private synchronized void dejarTenedor()
    {
        oneOnTop++;
    }

    private synchronized int getTopOne()
    {
        return oneOnTop;
    }

    private void intentarComer()
    {
        if (getTopOne() < 2){
            mensaje("está esperando a que haya suficientes tenedores en la mesa");
        } else {
            mensaje("tiene hambre e intenta coger dos tenedores");
        }
        pausar();
        try {
            //Semáforo - espera en su propio tenedor
            cogerTenedor();
            miTenedor = tenedores[getTopOne() - 1];
            miTenedor.acquire();

            // Coge su propio tenedor e intenta coger el de su vecino
            // (No espera)
            cogerTenedor();
            tenedorVecino = tenedores[getTopOne() - 1];
            if (!tenedorVecino.tryAcquire()) {
                // No lo consigue
                mensaje(">>>> no ha conseguido coger el segundo tenedor");
                return;
            };

            // Lo consigue y empieza a comer
            mensaje("ha conseguido dos tenedores y se poner a comer la comida #" + (10 - --comidas));
            pausar();

            // Deja los tenedores
            mensaje("deja los tenedores en la mesa");
            dejarTenedor();
            tenedorVecino.release();

        } catch (InterruptedException e) {
            mensaje("ha sido interrumpido al esperar por el tenedor");
        }
        finally { // siempre deja los tenedores
            dejarTenedor();
            miTenedor.release();
        }
    }

    @Override
    public void run()
    {
        while (comidas > 0)
        {
            pensar();
            intentarComer();
        }
    }


}
