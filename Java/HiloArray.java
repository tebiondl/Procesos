package com.company;

public class HiloArray extends Thread{

    private int id;
    private int[] nums;
    private int[] bigger;

    public HiloArray(int id, int[] nums, int[]bigger){
        this.id = id;
        this.nums = nums;
        this.bigger = bigger;
    }

    @Override
    public void run() {
        int grande = 0;

        for(int i = id*10; i<(id*10)+10; i++){
            if(nums[i] > grande){
                grande = nums[i];
            }
        }
        System.out.println("El más grande del hilo "+id+" es: "+grande);
        makeBigger(grande);
    }

    synchronized public void makeBigger(int big){
        if(big>bigger[0])
            bigger[0] = big;
    }
}
