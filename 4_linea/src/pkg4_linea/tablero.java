/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4_linea;

/**
 *@param columna
 * @param jugador
 * @author Jhon E
 */
public class tablero {
    
        
    public static void Board_Ini(int Board[][]){
          for(int f=0; f<6; f++)
             for(int c=0; c<7; c++)
                 Board[f][c] = 0;
    }
     
     public static boolean SetCoin(int [][] Board,int col,int Jugador){
         
        for(int f=5; f>=0 ;f--)
        {
            if(Board[f][col]==0)
            {
                Board[f][col]=Jugador;
                return true;
            }
        }
        return false;
    }
     
   public static boolean Finish_Game(int[][] Board)
   {       
       for (int c=0; c<7; c++) 
            if (Board[0][c]==0)  
                return false; 	
	return true;  
   }
   
   public static boolean Full_Col (int[][] Board,int columna){
        if (Board[0][columna]==0)
            return false;
        else
            return true;
    }
   
   public static void mostrar(int[][] Board) {
		for (int i = 0; i < Board.length; i++) {
			for (int j = 0; j < Board[0].length; j++) {
				System.out.print(Board[i][j]);
                                System.out.print("  ");
			}
			System.out.println();
		}
		System.out.println();
	} 
   
}
