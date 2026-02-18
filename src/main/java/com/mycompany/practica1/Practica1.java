

package com.mycompany.practica1;
import java.util.Scanner;
import java.util.Random;

public class Practica1 {
    // constantes y variables globales 
    //dimension de los tableros 
  private static final int FILAS_PEQUENOS = 5;
    private static final int COLUMNAS_PEQUENO =6;
    private static final int FILAS_GRANDES =10;
    private static final int COLUMNAS_GRANDES=10;
    // simbolos 
    private static final String fantasma = "@";
    private static final String premio = "0";
    private static final String premio_especial = "$";
    private static final String pared = "X";
    private static final String pacman = "<";
    
    // sc para leer lo que escribimos 
    private static Scanner sc = new Scanner(System.in);
    // para generar posiciones aleatorias al colocar elementos 
    private static Random rand = new Random();

    // punto de entrada 
    public static void main(String [] args){
        
        System.out.println("Bienvenido a pacman:");
        int opcionInicio;
    
        System.out.println("Elija las opciones");
        System.out.println("Elija 1 para crear el tablero");
        System.out.println("elija 2 para ver los puntos");
        System.out.println("Elija 3 para salir");
        
        opcionInicio = sc.nextInt();
        opcionElegida(opcionInicio);

        System.out.println("Gracias por jugar PAC-MAN :)");
        
    }
    //menu de pacman
    
    public static void opcionElegida(int opcionInicio){
        
            int numeroPremios;
            int numeroParedes;
            int numeroTrampas;
        
        switch (opcionInicio) {
                case 1:
                    System.out.println("Elija tipo el tipo de tablero (P= pequeno, G= grande): ");
                    String tipo = sc.next().toUpperCase();
                    
                    int filas;
                    int columnas;
                    if (tipo.equals("G")){  // SI ELIGE G SERAN LAS FILAS GRANDES 
                        filas = FILAS_GRANDES;
                        columnas = COLUMNAS_GRANDES;
                    } else{              // SI NO SERAN LAS FILAS QUENAS 
                        filas = FILAS_PEQUENOS;
                        columnas = COLUMNAS_PEQUENO;
                        
                    }
                    
                    //pedir cantidades con validacion
                    System.out.println("Por favor, ingresa los siguientes valores:");
                    
                    //Asigna premios 40%
                    numeroPremios = asignarCantidades("premios", 0.4, filas, columnas);
                    
                    //Asigna paredes 20%
                    numeroParedes = asignarCantidades("paredes",0.2, filas, columnas);
                    
                    //Asigna trampas 20%
                    numeroTrampas = asignarCantidades("fantasmas",0.4, filas, columnas);
                    
                    System.out.println("Creando tablero...");
                    
                    String[][] tablero = new String[filas][columnas];
                    // si el usuario elige grande, debe ser 10x10, si no cambia esto, siempre sera 5x6 supongamoe :(
                    
                    
                    //Llenando de espacios la matriz
                    for(int i = 0; i < filas; i++){
                        for(int j = 0; j < columnas; j++){
                            tablero[i][j]= " ";
                        }
                    }
                    
                    //Poniendo premios en la matriz
                    colocarElemento(tablero, premio,numeroPremios, filas, columnas);
                    
                    // paredes en la matriz
                    colocarElemento(tablero, pared, numeroParedes, filas, columnas);
                    
                    //Poniendo fantasmas
                    colocarElemento(tablero,fantasma,numeroTrampas, filas, columnas);
                    
                    System.out.println("Tablero de "+filas+"X"+columnas+" creado!");
                    
                    imprimirTablero(tablero, filas, columnas);
                    System.out.println();
                     
                    //colocar pacman
                    int personajeFila;
                    int personajeColumna;
                    
                    do{
                        System.out.println("Escoja donde desea colocar al personaje:");
                        System.out.print("Filas: ");
                        personajeFila = sc.nextInt();
                        System.out.print("Columnas: ");
                        personajeColumna = sc.nextInt();
                        if(verificacionPosicionPersonaje(personajeFila,personajeColumna,tablero, filas, columnas) == false){
                            System.out.println("Posición no válida, escoja otro lugar");
                        }
                    }while(verificacionPosicionPersonaje(personajeFila,personajeColumna,tablero, filas, columnas) == false);
                    
                    
                    System.out.println("Personaje colocado exitosamente!");
                    tablero[personajeFila-1][personajeColumna-1] = "<";
                    imprimirTablero(tablero, filas, columnas);
                    
                    // loop del juego :)
                    int pacFila = personajeFila -1;
                    int pacColumna = personajeColumna -1; // aqui guardamos donde esta actualmente el pacman
                    
                    boolean jugando = true; // mientras el juego sea true sontinuara 
                    
                    while(jugando){
                        System.out.println("Mover: 8=arriba, 5=abajo, 6=derecha, 4=izquierda, 0=salir"); //opciones del movimientoo
                        //lee el numero que el usuario ingresara 
                        int mov = sc.nextInt();
                        // variables nuevas para calcular las nuevas posciones 
                        int nuevaFila= pacFila;
                        int nuevaColumna = pacColumna;
                        switch(mov){
                            case 8:
                                 nuevaFila--;
                                 break;
                            case 5:
                                nuevaFila++;
                                break;
                            case 6:
                                nuevaColumna++;
                                break;
                            case 4:
                                nuevaColumna--;
                                break;
                            case 0: // salir del juego 
                                jugando = false;
                                continue;
                            default: 
                                System.out.println("Movimiento no valido.");
                                continue;
                        }
                        if(nuevaFila <0) nuevaFila =filas-1;
                        if (nuevaFila >= filas) nuevaFila =0;
                        if(nuevaColumna < 0) nuevaColumna = nuevaColumna - 1;
                        if(nuevaColumna >= columnas) nuevaColumna = 0;
                        
                        //no tiene que pasar paredes 
                        if(tablero[nuevaFila][nuevaColumna].equals(pared)){
                            System.out.println("Hay pared no puede pasar");
                            continue;
                        }
                        
                    }
                    
                    // esto es un cambio para probar git
                    
                    
                    

                    break;
                case 2:
                    //TODO: IMPLEMENTAR SECCION DE PUNTOS
                    break;
                case 3:
                    // TODO: IMPLEMENTAR SALIR
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo");
        }
        
    }
    // validar cantidades segun % 
    public static void imprimirTablero(String[][] tablero, int filas, int columnas) {

        // Borde superior
        for (int j = 0; j < columnas + 2; j++) {
            System.out.print("--");
        }
        System.out.println();

        // Filas del tablero
        for (int i = 0; i < filas; i++) {
            System.out.print("|");

            for (int j = 0; j < columnas; j++) {
                System.out.print(tablero[i][j] + " ");
            }

            System.out.println("|");
        }

        // Borde inferior
        for (int j = 0; j < columnas + 2; j++) {
            System.out.print("--");
        }
        System.out.println();
    }

    
    public static boolean verificacionPosicionPersonaje(int x, int y, String[][] tablero, int filas, int columnas){
        
        if(x < 1 || x > filas || y < 1 || y > columnas){ // validar que el personaje este dentro 
            return false; // si el usuario coloca de 0 a 100 no caiga
        }
        if (!tablero[x-1][y-1].equals(" ")){
        return false; // validar cuando la casilla este vacia  
        }
        //Posible método para poder implementar choques con paredes.
        
        return true;
        
    }
    // asignar cantidades 
    public static int asignarCantidades(String objeto, double porcentaje, int filas, int columnas){
        boolean bandera = false;
        int cantidad = 1;
        while(bandera == false){
            System.out.print("Elige la cantidad de "+objeto+": ");
            cantidad = sc.nextInt();
            bandera = true;
            int maxPremios = (int)(filas* columnas * porcentaje); // el maximo cambia si el tablero es grande 
            if (cantidad < 1 || cantidad > maxPremios) {
                System.out.println("Por favor ingresa una cantidad correcta.");
                bandera = false;
            }  
        }
        
        return cantidad;
        
    }
    // colocar elemntos 
    
    public static void colocarElemento(String[][] tablero, String simbolo, int cantidad, int filas, int columnas) {
        int colocados = 0;

        while (colocados < cantidad) {
            int fila = rand.nextInt(filas);
            int columna = rand.nextInt(columnas);

            if (tablero[fila][columna].equals(" ")) {
                tablero[fila][columna] = simbolo;
                colocados++;
            }
        }
    }

}

    
