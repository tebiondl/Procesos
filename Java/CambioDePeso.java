package com.company;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class CambioDePeso {

    public static void main(String[] args){
        String dniIntroducido = "";
        int pesoIntroducido = 0;

        //Pedimos la información por teclado
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el dni del personaje");
        dniIntroducido = sc.nextLine();
        System.out.println("Introduce el peso del personaje");
        pesoIntroducido = sc.nextInt();

        //Llamamos a la función para leer la información
        int peso = readData(dniIntroducido);

        //Si el peso es -1 significa que ningún personaje tiene el dni especificado arriba
        if(peso == -1){
            return;
        }

        //Comprobamos cómo ha cambiado su peso
        if(pesoIntroducido < peso){
            System.out.println("Ha adelgazado");
        }else if (peso == pesoIntroducido){
            System.out.println("Sigue igual");
        }else{
            System.out.println("Ha engordado");
        }
    }

    public static int readData(String dni){
        int peso = -1;
        try{
            RandomAccessFile rf = new RandomAccessFile("marvel.dat", "r");
            boolean encontrado = false;
            int i = 0;
            rf.seek(0);

            //Leemos el dni y peso de cada personaje
            while (!encontrado){
                try{
                    rf.readInt();
                    String dn = rf.readUTF();
                    rf.readUTF();
                    rf.readUTF();
                    rf.readUTF();
                    peso = rf.readInt();
                    //En caso de que el dni coincida con el que nos han dado, salimos del bucle con el peso ya guardado
                    if(dn.equals(dni)){
                        encontrado = true;
                    }
                    rf.readInt();
                }catch (EOFException e){
                    break;
                }
            }

            //Si no ha coincidido el dni, el peso se pone a -1
            if(!encontrado){
                System.out.println("No existe el personaje");
                peso = -1;
            }
            rf.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return peso;
    }

}
