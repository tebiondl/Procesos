package com.company;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class TipoDePersonaje {

    public static void main(String[] args){
        String tipo = "";
        Scanner sc = new Scanner(System.in);

        //Pedimos el tipo de personaje
        System.out.println("Introduce el tipo del personaje");
        tipo = sc.nextLine();
        //Visualizamos el texto devuelto por la función
        String finaltext = readData(tipo);
        System.out.println(finaltext);
    }

    public static String readData(String tipo){

        //String en el que vamos a guardar toda la información para luego visualizarla
        String text = "";

        try{
            RandomAccessFile rf = new RandomAccessFile("marvel.dat", "r");
            rf.seek(0);
            int i = 0;
            while (true){
                try{
                    //Guardamos todos los datos del personaje en cada iteración
                    int id = rf.readInt();
                    String dni = rf.readUTF();
                    String nombre = rf.readUTF();
                    String identidad = rf.readUTF();
                    String tp = rf.readUTF();
                    int peso = rf.readInt();
                    int altura = rf.readInt();

                    //Si el tipo es el mismo que el que nos han dado, añadimos la información al string
                    if(tp.equals(tipo)){
                        text+="ID: "+id+", DNI: "+dni+", NOMBRE: "+nombre+", IDENTIDAD: "+identidad+", TIPO: "+tp+", PESO: "+peso+", ALTURA: "+altura+"\n";
                        //cada vez que encontremos a un personaje con este tipo, sumamos 1
                        i++;
                    }

                }catch (EOFException e){
                    break;
                }
            }

            //En caso de que i sea 0, significa que no hemos encontrado ningún personaje de este tipo
            if(i<=0){
                System.out.println("No existe el tipo");
            }
            rf.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return text;
    }

}
