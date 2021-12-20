package com.company;
import java.io.*;
import java.net.*;

public class ServidorSockets{
    public static void main(String[] args){
        Server server = new Server();
    }
}

class Server implements Runnable{

    public Server(){
        Thread hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        try {
            ServerSocket serversocket = new ServerSocket(999);
            Envio envio;
            while(true){
                Socket socket = serversocket.accept();
                ObjectInputStream dataEntrada = new ObjectInputStream(socket.getInputStream());
                envio = (Envio) dataEntrada.readObject();
                socket.close();
                System.out.println(envio.getNick()+": "+envio.getTexto());
                Socket destinatario = new Socket("192.168.1.90",909);
                ObjectOutputStream datosSalida = new ObjectOutputStream(destinatario.getOutputStream());
                datosSalida.writeObject(envio);
                datosSalida.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

class Envio implements Serializable {
    String texto, nick;

    public Envio(){

    }

    public Envio(String texto,String nick){
        this.texto = texto;
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
