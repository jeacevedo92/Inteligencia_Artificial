
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4_linea;

/**
 *
 * @author Jhon E
 */
public class MiniMax {
    
    private int maximo;
    
    public MiniMax(int maximo){
		this.maximo=maximo;
	}

       
    public int minimax(int [][] Board,int jugador){
		int mejorMovimiento=-1;
		int mejorSucesor=Integer.MIN_VALUE;
		for(int i=0;i<Board.length;i++){
			int [][] aux=clonar(Board);
			int valorSucesor=Integer.MIN_VALUE;
			if(tablero.SetCoin(aux,i, jugador)){
				valorSucesor=Min_Valor(aux,Integer.MIN_VALUE,Integer.MAX_VALUE,cambiarJugador(jugador),i,1);
				if(valorSucesor>=mejorSucesor){
					mejorSucesor=valorSucesor;
					mejorMovimiento=i;
				}
			}
		}
		return mejorMovimiento;
	}
    
    public int Max_Valor(int [][] Board,int alpha,int beta,int jugador,int columna,int profundidad){
		if(ganador(jugador,Board,columna))return Integer.MAX_VALUE;
		else if(ganador(cambiarJugador(jugador),Board,columna))return Integer.MIN_VALUE;
		else if(hayEmpate(Board))
			if(tresEnRaya(Board,jugador)>tresEnRaya(Board,cambiarJugador(jugador)))
				return Integer.MAX_VALUE;
			else if(tresEnRaya(Board,jugador)<tresEnRaya(Board,cambiarJugador(jugador)))
				return Integer.MIN_VALUE;
			else if(dosEnRaya(Board,jugador)>dosEnRaya(Board,cambiarJugador(jugador)))
				return Integer.MAX_VALUE;
			else if(dosEnRaya(Board,jugador)<dosEnRaya(Board,cambiarJugador(jugador)))
				return Integer.MIN_VALUE;
			else return 0;
		else if(profundidad>=maximo)
			return utilidad(Board,jugador);
		
		int valor=Integer.MIN_VALUE;
		int [][] aux;
		for(int i=0;i<Board[0].length;i++){
			aux=clonar(Board);
			if(tablero.SetCoin(aux,i, jugador)){
				valor=Math.max(valor, Min_Valor(aux,alpha,beta,cambiarJugador(jugador),i,profundidad+1));
				if(valor>=beta){
					return valor;
				}
				alpha=Math.max(alpha,valor);
			}
		}
		return valor;
	}
    
    
    public int Min_Valor(int [][] Board,int alpha,int beta,int jugador,int columna,int profundidad){
		if(ganador(jugador,Board,columna)){
			return Integer.MIN_VALUE;
		}
		else if(ganador(cambiarJugador(jugador),Board,columna)){
			return Integer.MAX_VALUE;
		}
		else if(hayEmpate(Board))
			if(tresEnRaya(Board,jugador)>tresEnRaya(Board,cambiarJugador(jugador)))
				return Integer.MIN_VALUE;
			else if(tresEnRaya(Board,jugador)<tresEnRaya(Board,cambiarJugador(jugador)))
				return Integer.MAX_VALUE;
			else if(dosEnRaya(Board,jugador)>dosEnRaya(Board,cambiarJugador(jugador)))
				return Integer.MIN_VALUE;
			else if(dosEnRaya(Board,jugador)<dosEnRaya(Board,cambiarJugador(jugador)))
				return Integer.MAX_VALUE;
			else return 0;
		else if(profundidad>=maximo)
			return utilidad(Board,jugador);
		
		int valor=Integer.MAX_VALUE;
		int [][] aux;
		for(int i=0;i<Board[0].length;i++){
			aux=clonar(Board);
			if(tablero.SetCoin(aux,i, jugador)){
				valor=Math.min(valor,Max_Valor(aux,alpha,beta,cambiarJugador(jugador),i,profundidad+1));
				if(valor<=alpha){
					return valor;
				}
				beta=Math.min(beta,valor);
			}
		}
		return valor;	
	}
    
  /*  public boolean ganador(int jugador,int[][] tablero,int columna){
    
    if(win_row(jugador,tablero,columna)||win_col(jugador,tablero,columna)||win_diag(jugador,tablero,columna))
         return true;      
         return false;
        
    }
    
    
    boolean win_row(int jugador,int[][] Board,int fila,int columna){
      for (int f=0; f<6;f++)          
            for (int c=0; c<4; c++)
              if (Board[f][c] != 0)
                  if (jugador==Board[f][c+1]&&jugador==Board[f][c+2]&&jugador==Board[f][c+3])
                        return true;
          
        return false;
    }
     
     boolean win_col(int jugador,int[][] Board,int f,int c){
               if (Board[f][c] != 0)
                  if (jugador==Board[f+1][c]&&jugador==Board[f+2][c] &&jugador==Board[f+3][c])
                        return true;
        return false;
    }
     
   boolean win_diag(int jugador,int[][] Board,int f,int c){      
	//comprobacion de izquierda a derecha

                if(Board[f][c]!=0)
                    if(jugador==Board[f+1][c+1] &&
                       jugador==Board[f+2][c+2] &&
                       jugador==Board[f+3][c+3])
			return true;

	// comprobacion de derecha a izquierda

		if(Board[f][c]!=0)
                    if(jugador==Board[f+1][c-1] &&
                       jugador==Board[f+2][c-2] &&
                       jugador==Board[f+3][c-3])
			return true;
	return false;
    }
    */
    
    
    public boolean ganador(int jugador,int[][] tablero,int columna){
		int fila=0;
		try{
		while(tablero[fila][columna]!=jugador)
			fila++;
		}catch(IndexOutOfBoundsException e){
			return false;
		}
		return vertical(jugador,tablero,fila,columna)|| horizontal(jugador,tablero,fila,columna)||
		ascenDer(jugador,tablero,fila,columna) || ascenIzq(jugador,tablero,fila,columna);
	}
    
    public boolean ascenDer(int jugador,int [][]tablero,int fila,int columna){
		boolean ganador=false;
		try{
			ganador=tablero[fila+1][columna+1]==jugador && tablero[fila+2][columna+2]==jugador
			&& tablero[fila+3][columna+3]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!ganador)
				ganador=tablero[fila-1][columna-1]==jugador && tablero[fila+1][columna+1]==jugador
				&& tablero[fila+2][columna+2]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!ganador)
				ganador=tablero[fila-2][columna-2]==jugador && tablero[fila-1][columna-1]==jugador
				&& tablero[fila+1][columna+1]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!ganador)
				ganador=tablero[fila-3][columna-3]==jugador && tablero[fila-2][columna-2]==jugador
				&& tablero[fila-1][columna-1]==jugador;
		}catch(IndexOutOfBoundsException e){}
		return ganador;
	}
    
    public boolean ascenIzq(int jugador,int [][] tablero,int fila,int columna){
		boolean ganador=false;
		try{
			ganador=tablero[fila+1][columna-1]==jugador && tablero[fila+2][columna-2]==jugador
			&& tablero[fila+3][columna-3]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!ganador)
				ganador=tablero[fila-1][columna+1]==jugador && tablero[fila+1][columna-1]==jugador
				&& tablero[fila+2][columna-2]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!ganador)
				ganador=tablero[fila-1][columna+1]==jugador && tablero[fila-2][columna+2]==jugador
				&& tablero[fila+1][columna-1]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!ganador)
				ganador=tablero[fila-1][columna+1]==jugador && tablero[fila-2][columna+2]==jugador
				&& tablero[fila-3][columna+3]==jugador;
		}catch(IndexOutOfBoundsException e){}
		return ganador;
	}
    
    public boolean horizontal(int jugador,int [][] tablero,int fila,int columna){
		boolean ganador=false;
		try{
			ganador=tablero[fila][columna+1]==jugador && tablero[fila][columna+2]==jugador
			&& tablero[fila][columna+3]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!ganador)
				ganador=tablero[fila][columna-1]==jugador && tablero[fila][columna+1]==jugador
				&& tablero[fila][columna+2]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!ganador)
				ganador=tablero[fila][columna-2]==jugador && tablero[fila][columna-1]==jugador
				&& tablero[fila][columna+1]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!ganador)
				ganador=tablero[fila][columna-3]==jugador && tablero[fila][columna-2]==jugador
				&& tablero[fila][columna-1]==jugador;
		}catch(IndexOutOfBoundsException e){}
		return ganador;
	}
    
    public boolean vertical(int jugador,int [][] tablero,int fila,int columna){
		boolean ganador=false;
		try{
			ganador=tablero[fila+1][columna]==jugador && tablero[fila+2][columna]==jugador
			&& tablero[fila+3][columna]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!ganador)
				ganador=tablero[fila-1][columna]==jugador && tablero[fila+1][columna]==jugador
				&& tablero[fila+2][columna]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!ganador)
				ganador=tablero[fila-2][columna]==jugador && tablero[fila-1][columna]==jugador
				&& tablero[fila+1][columna]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!ganador)
				ganador=tablero[fila-3][columna]==jugador && tablero[fila-2][columna]==jugador
				&& tablero[fila-1][columna]==jugador;
		}catch(IndexOutOfBoundsException e){}
		return ganador;
	}
    
    public int utilidad(int [][] tablero,int jugador){
		int tres=tresEnRaya(tablero,jugador);
		int dos=dosEnRaya(tablero,jugador);
		return tres*60+dos*40;
	}
    
    public boolean hayEmpate(int[][] tablero){
		boolean empate=true;
		for(int i=0;i<tablero[0].length;i++)
			if(tablero[0][i]==0)
				empate=false;
		return empate;
	}
    
    public int tresEnRaya(int [][] tablero,int jugador){
		int total=0;
		for(int i=0;i<tablero.length;i++)
			for(int j=0;j<tablero[0].length;j++)
				if(tresHorizontal(tablero,i,j,jugador) || tresVertical(tablero,i,j,jugador)
					|| tresDerAsc(tablero,i,j,jugador) || tresIzqAsc(tablero,i,j,jugador))
					total++;
		return total;
	}
    
    private boolean tresHorizontal(int [][] tablero,int fila, int columna,int jugador){
		boolean raya=false;
		try{
			raya=tablero[fila][columna+1]==jugador && tablero[fila][columna+2]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!raya)
				raya=tablero[fila][columna-1]==jugador && tablero[fila][columna+1]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!raya)
				raya=tablero[fila][columna-1]==jugador && tablero[fila][columna-2]==jugador;
		}catch(IndexOutOfBoundsException e){}
		return raya;
	}
    
    public boolean tresVertical(int [][] tablero,int fila, int columna, int jugador){
		boolean raya=false;
		try{
			raya=tablero[fila+1][columna]==jugador && tablero[fila+2][columna]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!raya)
				raya=tablero[fila-1][columna]==jugador && tablero[fila+1][columna]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!raya)
				raya=tablero[fila-1][columna]==jugador && tablero[fila-2][columna]==jugador;
		}catch(IndexOutOfBoundsException e){}
		
		return raya;
	}
    
    public boolean tresDerAsc(int [][] tablero, int fila, int columna, int jugador){
		boolean raya=false;
		try{
			raya=tablero[fila+1][columna+1]==jugador && tablero[fila+2][columna+2]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!raya)
				raya=tablero[fila+1][columna+1]==jugador && tablero[fila-1][columna]-1==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!raya)
				raya=tablero[fila-1][columna-1]==jugador && tablero[fila-2][columna-2]==jugador;
		}catch(IndexOutOfBoundsException e){}
		
		return raya;
	}
    
    public boolean tresIzqAsc(int [][] tablero, int fila, int columna, int jugador){
		boolean raya=false;
		try{
			raya=tablero[fila+1][columna-1]==jugador && tablero[fila+2][columna-2]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!raya)
				raya=tablero[fila+1][columna-1]==jugador && tablero[fila-1][columna+1]==jugador;
		}catch(IndexOutOfBoundsException e){}
		try{
			if(!raya)
				raya=tablero[fila-1][columna+1]==jugador && tablero[fila-2][columna+2]==jugador;
		}catch(IndexOutOfBoundsException e){}
		return raya;
	}
    
    public int dosEnRaya(int [][] tablero,int jugador){
		int total=0;
		for(int i=0;i<tablero.length;i++)
			for(int j=0;j<tablero[0].length;j++)
				if(dosEnRaya(tablero,i,j,jugador))
					total++;
		return total;
	}
	public boolean dosEnRaya(int [][] tablero,int fila,int columna,int jugador){
		boolean dos=false;
		try{
			dos=tablero[fila+1][columna]==jugador || tablero[fila-1][columna]==jugador
			|| tablero[fila+1][columna+1]==jugador || tablero[fila+1][columna-1]==jugador
			|| tablero[fila][columna+1]==jugador || tablero[fila][columna-1]==jugador
			|| tablero[fila-1][columna+1]==jugador ||tablero[fila-1][columna-1]==jugador;
		}catch(IndexOutOfBoundsException e){}
		return dos;
	}
   
   private int [][]  clonar(int [][] tablero){
		int [][] copia = new int [6][7];
		for(int i=0;i<tablero.length;i++)
			for(int j=0;j<tablero[0].length;j++)
				copia[i][j]=tablero[i][j];
		return copia;
	}
   
   public int cambiarJugador(int jugador){
		if(jugador== 1)
			return 2;
		else return 1;
	}
    
    
    
    
    
    
    
}
