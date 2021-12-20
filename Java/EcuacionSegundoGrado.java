package com.company;

public class EcuacionSegundoGrado {

    public static void main(String args[]){
        ecSegundoGrado(-2,2,8);
    }

    public static void ecSegundoGrado(double a, double b, double c){

        //Comprobar tipo de ecuación
        if(a == 0){
            //No es de segundo grado
            System.out.println("Esta ecuación no es de segundo grado, es de primer grado");
            return;
        }

        //ver el tipo de ecuación, en caso de no ser ni la tipo 2,3 ó 4, se comprobará si puede ser tipo 1

        //Tipo 2 -> b==0 , c!=0
        if(b == 0 && c != 0){
            ecTipo2(a,c);
            return;
        }
        //Tipo 3 -> b!=0 , c==0
        if(b != 0 && c == 0){
            ecTipo3(a,b);
            return;
        }
        //Tipo 4 -> b==0 , c==0
        if(b == 0 && c == 0){
            ecTipo4();
            return;
        }

        //Comprobar si es posible la de tipo 1
        double y = Math.pow(b,2) - (4 * a * c);
        if(y >= 0){
            //Tipo 1 -> b!=0 , c!=0
            if(b != 0 && c != 0){
                ecTipo1(a,b,c);
                return;
            }
        }else{
            //No se puede calcular
            System.out.println("Los coeficientes no son válidos para resolver la ecuación de segundo grado");
        }
    }

    public static void ecTipo1(double a, double b, double c){
        double y = Math.pow(b,2) - (4 * a * c);
        //Calcular ec segundo grado
        double x1 = (-b + Math.sqrt(y))/(2*a);
        double x2 = (-b - Math.sqrt(y))/(2*a);
        //Imprimir en pantalla
        System.out.println("Las dos soluciones a la ecuación son: "+x1+" y "+x2);
    }

    public static void ecTipo2(double a, double c){
        //Calcular ec segundo grado
        double x1 = +Math.sqrt(-c/a);
        double x2 = -Math.sqrt(-c/a);
        //Imprimir en pantalla
        System.out.println("Las dos soluciones a la ecuación son: "+x1+" y "+x2);
    }

    public static void ecTipo3(double a, double b){
        //Calcular ec segundo grado
        double x1 = 0;
        double x2 = -b/a;
        //Imprimir en pantalla
        System.out.println("Las dos soluciones a la ecuación son: "+x1+" y "+x2);
    }

    public static void ecTipo4(){
        //La única solución es 0
        //Imprimir en pantalla
        System.out.println("Las única solución es: "+0);
    }

}
