package com.company;
import java.io.*;

public class LeerCaracteres {

    public static void main(String[] args) throws IOException {
        Process p = new ProcessBuilder("CMD","/C","DIR").start();

        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);

        String s = "";
        try{
            while (!s.contains("*")){
                System.out.println("Introduce una cadena");
            s = br.readLine();
            System.out.println(s);
            }

            in.close();
        }catch (Exception e){
            e.printStackTrace();
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
