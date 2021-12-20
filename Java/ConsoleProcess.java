package com.company;
import java.io.*;

public class ConsoleProcess {
    public static void main(String[] args) throws IOException, InterruptedException {

        String[] params = new String[4];

        String st = "";
        int cont = 0;
        char a = ' ';
        do{
            a = (char)System.in.read();
            //System.out.println("char: "+a);
            if(a != ' ' && a != '\n'){
                st+=a;
            }
            if (a == ' ' || a == '\n'){
                params[cont] = st;
                st = "";
                cont++;
            }
        }while(a!='\n' && cont < 3);

        cont = 0;

        for (String s:params){
            if(s == null){
                cont++;
            }
        }

        try
        {
            if(cont < 2){
                //error
            }else if (cont == 2) {
                String[] params2 = {params[0], "-classpath","C:\\Users\\JAVIER-MIGUEL\\Desktop\\Todo\\GithubRepos\\Procesos\\src","com.company."+params[1]};
                Process p = Runtime.getRuntime().exec(params2);
            }else {
                String[] params2 = {params[0], "-classpath","C:\\Users\\JAVIER-MIGUEL\\Desktop\\Todo\\GithubRepos\\Procesos\\src","com.company."+params[1], params[2]};
                Process p = Runtime.getRuntime().exec(params2);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


    }
}
