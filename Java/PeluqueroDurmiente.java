package com.company;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PeluqueroDurmiente {

    public static void main(String a[])
    {
        Peluqeria p = new Peluqeria();

        Peluqero peluqero = new Peluqero(p);
        GeneradorClientes gc = new GeneradorClientes(p);

        //Iniciamos los hilos con la interfaz runnable
        Thread hpeluquero = new Thread(peluqero);
        Thread hgc = new Thread(gc);
        hgc.start();
        hpeluquero.start();
    }
}

//Clase peluquero que hace que el peluqero corte el pelo a los clientes si hay clientes
class Peluqero implements Runnable
{
    Peluqeria p;

    public Peluqero(Peluqeria p)
    {
        this.p = p;
    }
    public void run()
    {
        try
        {
            Thread.sleep(10000);
        }
        catch(InterruptedException iex)
        {
            iex.printStackTrace();
        }
        System.out.println("El peluquero se despierta...");
        while(true)
        {
            p.cutHair();
        }
    }
}

//Clase cliente
class Cliente implements Runnable
{
    String name;
    Date inTime;

    Peluqeria p;

    public Cliente(Peluqeria p)
    {
        this.p = p;
    }

    public String getName() {
        return name;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public void run()
    {
        goForHairCut();
    }
    //Cuando el cliente va a cortarse el pelo se añade a la lista de procesos de la peluqería esperando a que pueda cortarse el pelo
    private synchronized void goForHairCut()
    {
        p.add(this);
    }
}

//Clase que genera clientes que van llegando a la peluquería
class GeneradorClientes implements Runnable
{
    Peluqeria p;

    public GeneradorClientes(Peluqeria p)
    {
        this.p = p;
    }

    public void run()
    {
        while(true)
        {
            Cliente c = new Cliente(p);
            c.setInTime(new Date());
            Thread thcustomer = new Thread(c);
            c.setName("El cliente "+thcustomer.getId());
            thcustomer.start();

            try
            {
                TimeUnit.SECONDS.sleep((long)(Math.random()*10));
            }
            catch(InterruptedException iex)
            {
                iex.printStackTrace();
            }
        }
    }

}

class Peluqeria
{
    int nchair;
    List<Cliente> listC;

    public Peluqeria()
    {
        nchair = 3;
        listC = new LinkedList<Cliente>();
    }

    //Función que saca de la lista de clientes esperando al primer cliente para cortarle el pelo
    public void cutHair()
    {
        Cliente c;
        synchronized (listC)
        {

            //Si no hay clientes, se duerme
            while(listC.size()==0)
            {
                System.out.println("El peluquero espera a los clientes dormido.");
                try
                {
                    listC.wait();
                }
                catch(InterruptedException iex)
                {
                    iex.printStackTrace();
                }
            }
            //Cuando encuentra a un cliente, se prepara para cortarle el pelo
            System.out.println("El peluquero ve a un cliente esperando.");
            c = (Cliente)((LinkedList<?>)listC).poll();
        }
        long duration=0;
        try
        {
            System.out.println("Cortando el pelo de : "+c.getName());
            duration = (long)(Math.random()*30);
            TimeUnit.SECONDS.sleep(duration);
        }
        catch(InterruptedException iex)
        {
            iex.printStackTrace();
        }
        System.out.println("El peluquero ha terminado de cortar el pelo de : "+c.getName() + " en "+duration+ " segundos.");
    }

    //función que añade clientes a la lista de espera mientras haya sillas libres
    public void add(Cliente c)
    {
        System.out.println("El cliente : "+c.getName()+ " entra en la peluquería a fecha de "+c.getInTime());

        synchronized (listC)
        {
            if(listC.size() == nchair)
            {
                System.out.println("No hay sillas libres para el cliente "+c.getName());
                System.out.println("El cliente "+c.getName()+" se va...");
                return ;
            }

            ((LinkedList<Cliente>)listC).offer(c);
            System.out.println("El cliente : "+c.getName()+ " se sienta en una silla.");

            if(listC.size()==1)
                listC.notify();
        }
    }
}
