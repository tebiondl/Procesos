package com.company;

public class Contenedor
{
    private int contenido;
    private int maxContenido = 500;

    public synchronized int get(int value)
    {
        while (contenido <= 0)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                System.err.println("Contenedor: Error en get -> " + e.getMessage());
            }
        }
        int conseguido;
        if(value > contenido){
            conseguido = contenido;
            contenido = 0;
        }else{
            contenido-=value;
            conseguido = value;
        }
        notify();
        return conseguido;
    }

    public synchronized void put(int value)
    {
        while (contenido==maxContenido)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                System.err.println("Contenedor: Error en put -> " + e.getMessage());
            }
        }
        contenido += value;
        if (contenido>=maxContenido){
            contenido = maxContenido;
        }

        if(contenido>0){
            notify();
        }
    }
}
