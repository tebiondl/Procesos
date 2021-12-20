package com.company;

import java.util.concurrent.locks.*;
import java.util.*;

interface STATE {
    int HUNGRY=0, THINKING=1, EATING=2;
}

class Global{
    static int num;
    static{
        num=5;
    }

    static int Left(int i){
        return (i-1+num)%num;
    }
    static int Right(int i){
        return (i+1)%num;
    }
}

class Monitor{
    private final ReentrantLock entLock;
    private final Condition[] self;
    private int[] state;

    public Monitor(int num){
        entLock=new ReentrantLock();
        self=new Condition[num];
        state=new int[num];

        for(int i=0; i<num; i++){
            self[i]=entLock.newCondition();
            state[i]=STATE.THINKING;
        }
    }

    public void go(int who){
        try {
            pickup(who);
            putdown(who);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pickup(int who) throws InterruptedException {
        entLock.lock();
        state[who]=STATE.HUNGRY;
        System.out.println("El filósofo " + who + " tiene hambre.");
        Test(who);

        if(state[who]!=STATE.EATING){
            System.out.println("El filósofo " + who + " espera para poder coger un tenedor.");
            self[who].await();
        }

        entLock.unlock();
    }

    private void Test(int who){
        if(state[Global.Left(who)]!=STATE.EATING &&
                state[Global.Right(who)]!=STATE.EATING &&
                state[who]==STATE.HUNGRY){
            state[who]=STATE.EATING;
            System.out.println("El filósofo " + who + " está comiendo.");
            self[who].signal();

        }
    }

    private void putdown(int who){
        entLock.lock();
        state[who]=STATE.THINKING;
        System.out.println("El filósofo " + who + " deja de comer y se pone a pensar.");

        Test(Global.Left(who));
        Test(Global.Right(who));

        entLock.unlock();
    }
}

class Philosopher implements Runnable{
    Monitor M;
    int ID;

    public Philosopher(Monitor M, int ID){
        this.M=M;
        this.ID=ID;
    }

    @Override
    public void run(){
        System.out.println(ID);
        while(true){
            M.go(ID);
        }
    }
}

public class PhilosophersEat {
    public static void main(String[] args){
        Philosopher[] P=new Philosopher[Global.num];
        Monitor M=new Monitor(Global.num);
        Thread[] threads = new Thread[Global.num];

        System.out.println("Empezamos");
        for(int i=0; i<Global.num; i++){
            P[i]=new Philosopher(M, i);
            threads[i] = new Thread(P[i]);
            threads[i].start();
        }

        try {
            for(int i=0; i<Global.num; i++){
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Terminado");
    }
}
