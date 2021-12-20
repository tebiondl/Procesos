package com.company;

import java.util.Random;

public class Reading {

    int[] array;
    semaf s;
    Random r = new Random();

    public Reading(int[] array){
        this.array = array;
        this.s = new semaf();
    }

    public void modifyArray(int id){
        try {
            System.out.println(id+" Intenta modificar");
            s.semafAcquire();
            int pos = r.nextInt(array.length);
            array[pos] = id;
            System.out.println(id+" Consigue modificar");
            String str = "[";
            for (int a: array) {
                str+=a+",";
            }
            str+="]";
            System.out.println("Estado del array -> "+str);
        }catch (InterruptedException e){

        }

    }

    public void releaseArray(int id){

        System.out.println(id+" libera el array");
        s.semafRelease();

    }

}
