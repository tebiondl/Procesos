package com.company;

import java.util.Random;

public class semafReader extends Thread{

    Random r = new Random();
    Reading read;
    int id;
    int amount = 10;

    public semafReader(Reading read, int id){
        this.read = read;
        this.id = id;
    }

    @Override
    public void run() {
        while (amount>0){
            read.modifyArray(id);
            esperar();
            read.releaseArray(id);
            amount--;
        }
    }

    public void esperar(){
        try {
            sleep(r.nextInt(5000));
        } catch (InterruptedException e){

        }
    }
}
