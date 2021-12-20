package com.company;

public class SemaforoMonitor {

    public static void main(String[] args){
        int[] array = new int[10];

        for (int a: array) {
            a = 0;
        }

        Reading read = new Reading(array);
        semafReader r1 = new semafReader(read,1);
        semafReader r2 = new semafReader(read,2);
        r1.start();
        r2.start();
    }

}
