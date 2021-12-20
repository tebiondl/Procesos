package com.company;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("java","-classpath","C:\\Users\\JAVIER-MIGUEL\\Desktop\\Todo\\GithubRepos\\Procesos\\src","com.company.LeerCaracteres");
        Process p = pb.start();

        OutputStream os = p.getOutputStream();
        os.write(("hola\n").getBytes());
        os.flush();

        try{
            InputStream is = p.getInputStream();
            int c;
            while((c = is.read()) != -1){
                System.out.print((char) c);
            }
            is.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
