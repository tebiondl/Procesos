package com.company;

import java.util.Random;

public class ArrayDeCienHilos {

    public static void main(String[] args){
        int[] nums = new int[100];
        int[] bigger = new int[1];

        //Iniciamos el array con valores y lo randomizamos
        for(int i = 0; i< nums.length; i++){
            nums[i] = i*3;
        }
        shuffleArray(nums);

        for (int i = 0; i < nums.length; i++)
        {
            if(i%10==0)
                System.out.print("| "+nums[i] + " ");
            else
                System.out.print(nums[i] + " ");
        }
        System.out.println();

        HiloArray h1 = new HiloArray(0,nums,bigger);
        HiloArray h2 = new HiloArray(1,nums,bigger);
        HiloArray h3 = new HiloArray(2,nums,bigger);
        HiloArray h4 = new HiloArray(3,nums,bigger);
        HiloArray h5 = new HiloArray(4,nums,bigger);
        HiloArray h6 = new HiloArray(5,nums,bigger);
        HiloArray h7 = new HiloArray(6,nums,bigger);
        HiloArray h8 = new HiloArray(7,nums,bigger);
        HiloArray h9 = new HiloArray(8,nums,bigger);
        HiloArray h10 = new HiloArray(9,nums,bigger);

        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();
        h6.start();
        h7.start();
        h8.start();
        h9.start();
        h10.start();

        try{
            h1.join();
            h2.join();
            h3.join();
            h4.join();
            h5.join();
            h6.join();
            h7.join();
            h8.join();
            h9.join();
            h10.join();

            System.out.println("El más grande de todos es: "+bigger[0]);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    public static void shuffleArray(int[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

}
