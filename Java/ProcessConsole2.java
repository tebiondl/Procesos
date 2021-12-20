package com.company;
import java.io.*;

public class ProcessConsole2 {
    public static void main(String[] args)throws IOException{
        Process p = new ProcessBuilder("CMD","/C","DIR").start();

        //Órdenes a ejecutar
        for (String s:args){
            switch (s){
                case "o1":
                    System.out.println("Orden 1");
                    break;
                case "o2":
                    System.out.println("Orden 2");
                    break;
                case "o3":
                    System.out.println("Orden 3");
                    break;
            }
        }

        int exitVal;
        try{
            exitVal = p.waitFor();
            System.out.println(exitVal);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
