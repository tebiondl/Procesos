package com.company;

public class ProductorConsumidor
{
    private static Contenedor contenedor;
    private static Productor[] productores;
    private static Consumidor [] consumidores;
    private static final int CANTIDADCONSUMIDORES = 2;
    private static final int CANTIDADPRODUCTORES = 2;

    public static void main(String[] args)
    {
        contenedor = new Contenedor();
        productores = new Productor[CANTIDADPRODUCTORES];
        consumidores = new Consumidor[CANTIDADCONSUMIDORES];

        for(int i = 0; i < CANTIDADCONSUMIDORES; i++)
        {
            consumidores[i] = new Consumidor(contenedor, i);
            consumidores[i].start();
        }

        for(int i = 0; i < CANTIDADPRODUCTORES; i++)
        {
            productores[i] = new Productor(contenedor, i);
            productores[i].start();
        }
    }
}
