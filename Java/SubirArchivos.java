package com.company;

import java.io.*;
import org.apache.commons.net.ftp.*;

public class SubirArchivos {
    public static void main(String[] args){
        FTPClient client = new FTPClient();
        String server = "127.0.0.1";
        String user = "user1";
        String pass = "pass1";

        try{
            client.connect(server);
            showServerReply(client);
            client.enterLocalPassiveMode();
            boolean login = client.login(user,pass);
            showServerReply(client);
            client.setFileType(FTP.BINARY_FILE_TYPE);
            String dir = "/NEWDIR";

            if(login){
                System.out.println("LOGUEADO");

                if(!client.changeWorkingDirectory(dir)){
                    String otherDir = "NEWDIR";

                    if(client.makeDirectory(otherDir)){
                        System.out.println("CREADO NUEVA CARPETA: "+otherDir);
                        client.changeWorkingDirectory(otherDir);
                    }else{
                        System.out.println("ERROR CREANDO CARPETA");
                        System.exit(0);
                    }
                }

                System.out.println("CARPETA ACTUAL: "+client.printWorkingDirectory());

                String archivo = "C:\\Users\\JAVIER-MIGUEL\\Desktop\\Holaaa.txt";
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(archivo));

                if(client.storeFile("Holaaa.txt", in)){
                    System.out.println("SUBIDA CORRECTA");
                }else{
                    System.out.println("ERROR AL SUBIR");
                }
                in.close();
                client.logout();
                client.disconnect();
            }else{
                System.out.println("ERROR AL CONECTARSE");
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }
}
