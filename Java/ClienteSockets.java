package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.net.*;

public class ClienteSockets {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = "";
        Listener l = new Listener();
        System.out.println("EMPIEZA EL CHAT");
        while (!s.contains("*")){
            s = sc.nextLine();
            enviarTexto(s,"Miguel");
        }
        System.out.println("TERMINADO");
    }

    public static void enviarTexto(String s,String nick){

        try{
           Socket socket = new Socket("192.168.1.90",999);
           Envio envio = new Envio(s,nick);
           ObjectOutputStream datosSalida = new ObjectOutputStream(socket.getOutputStream());
           datosSalida.writeObject(envio);
           datosSalida.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

class Listener implements Runnable{

    public Listener(){
        Thread hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        try {
            ServerSocket serversocket = new ServerSocket(909);
            Envio envio;
            while(true){
                Socket socket = serversocket.accept();
                ObjectInputStream dataEntrada = new ObjectInputStream(socket.getInputStream());
                envio = (Envio) dataEntrada.readObject();
                socket.close();
                System.out.println(envio.getNick()+" te ha dicho: "+envio.getTexto());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
