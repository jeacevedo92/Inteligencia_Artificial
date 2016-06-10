/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;
import pkg4_linea.MiniMax;
import pkg4_linea.tablero;


/**
 *
 * @author Jhon E
 */
public class Main {
    
   // public static int EMPTY = -1;
    public static int jugador; 
    //public static int jugador2 = 2;

    /**
     * @param args the command line arguments
     */
  
    public static boolean verifica_Col(int x)
    {
        if(x>=1 && x<8)
            return true;
        else
            return false;        
    }
    
    public static void main(String[] args) {
        
         System.out.println("#############   CONECTA 4   #############");
        System.out.println("");
        System.out.println("");        
        Scanner sc = new Scanner(System.in);
        int col;;
       MiniMax Solve = new MiniMax(8);
        
       int [][] Board = new int [6][7];
            
       tablero.Board_Ini(Board);
       tablero.mostrar(Board); 
       
       
       
       jugador = 1;
      
       
       
       while(!tablero.Finish_Game(Board))
       {
                System.out.print("Jugador ");
                System.out.println(jugador);
                System.out.println("Ingrese Columna");
                
                if(jugador==1){
                    col = sc.nextInt();
                        while(!verifica_Col(col)){
                            System.out.println("Por favor ingrese una columna valida !!! ");
                            col = sc.nextInt();
                        }                 
                }   
                    else
                        col=Solve.minimax(Board, jugador);
                
                
                if(tablero.SetCoin(Board, col-1, jugador))
                {
                    tablero.mostrar(Board); 
                    
                    if(Solve.ganador(jugador, Board, col-1))
                    {
                        System.out.println("Jugador ");
                        System.out.println(jugador);
                        System.out.println(" GANO !!! ");
                        break;                
                    }
                    else
                        jugador = Solve.cambiarJugador(jugador);                        
                }
                
                    else
                        System.out.println("No se puede ingresar en esta Columna");                    
       }
       
       }
       
       
    }
 