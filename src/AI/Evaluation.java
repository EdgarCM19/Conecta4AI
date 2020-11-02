package AI;

import logic.Board;

public class Evaluation {
	
	
	//Funciones de evaluación.
	public static float evaluation(Board data, char player) {
		//return free_rows(data.board, player);
		return diagonals_down(data.board,player);
		//return free_columns(data.board,player);
		//return diagonals_up(data.board,player);
	}
	
	//[x] filas disponibles
	//Cuenta cuantas filas cuentan con el espacio minimo para ganar
	public static int free_rows(char [][] matrix, char c) {
		int cont = 0;
		for (int row = 0; row < matrix.length; row++) {
			if(max_for_row(matrix, row, c) >= 4)
				cont++;
		}
		System.out.println("Columnas libres para :" + c + " = " + cont);
		return cont;
	}
	
	//cuenta espacios maximos de una fila
	public static int max_for_row(char[][] matrix, int row, char c) {
		int cont = 0, pa = 0;
		for(int column = 0; column < matrix[row].length; column++) {	
			if(matrix[row][column] == c || matrix[row][column] == '-') {
				pa += 1;
			} else {
				cont = max(pa, cont);
				pa = 0;
			}
		}
		cont = max(pa, cont);
		System.out.println("Salida de " + cont);
		return cont;
	}
	
	//[x] columnas disponibles 
	public static int free_columns(char [][] matrix, char c){
		int cont = 0;
		for (int column = 0; column< 6; column++) {
			if(max_for_column(matrix,column, c) >= 4)
				cont++;
		}
		System.out.println("Filas libres para :" + c + " = " + cont);
		return cont;
	}
	
	private static int max_for_column(char[][] matrix, int column, char c) {
		int cont=0,pa=0;
		for(int rows=0;rows<matrix.length;rows++) {
			if(matrix[rows][column] == c || matrix[rows][column]=='-')
				pa+=1;
			else {
				cont = max(pa,cont);
				pa=0;
			}	
		}
		System.out.println("Salida de " + cont);
		return cont;
	}
	
	//[x] diagonal derecha hacia abajo 
	private static int diagonals_down(char[][]matrix,char c) {
		int cont=0;
		for(int colum = 0;colum<4;colum++) {
			if(max_for_diagonals_down(matrix,colum,c)>0)
				cont++;
			System.out.println("Este es el conta: "+cont);
		}
		System.out.println("Filas libres el diagonal desendente para :" + c + " = " + cont);
		return cont;
	}
	
	private static int max_for_diagonals_down(char[][] matrix, int colum,char c) {
		int cont=0;
		for(int rows=0;rows<3;rows++) {
			if(evaluateDiagonalEspaceDown(matrix,rows,colum,'-',c))
				cont++;	
		}
		System.out.println("Salida de filas:" + cont);
		return cont;
	}

	//[x] diagonal derecha hacia arriba
	public static int diagonals_up(char [][] matrix, char c) {
		int cont=0;
		for(int colm=0;colm<4;colm++) {
			if(max_for_diadonals_up(matrix,colm,c)>0)
				cont++;
		}
		System.out.println("Filas libres el diagonal acendente para :" + c + " = " + cont);
		return cont;
	}
	private static int max_for_diadonals_up(char[][] matrix, int colm, char c) {
		int cont=0;
		for(int rows=5;rows>2;rows--) {
			if(eveluteDiagonalEspaceUp(matrix,rows,colm,'-',c))
				cont++;
		}
		System.out.println("Salida de " + cont);
		return cont;
	}

	private static int min(int a, int b) {
		return (a < b) ?  a : b;
	}
	
	private static int max(int a, int b) {
		return (a > b) ?  a : b;
	}
	
	//Funciones para ver si es meta.
	public static boolean isMeta(Board data) {
		for(int column = 0; column < data.board[0].length; column++) {
			for(int row = 0; row < data.board.length; row++) {
				if(data.board[row][column] != '-') {
					if(canRigth(column, data.board[0].length)) {
						if(evaluateR(data.board, row, column))
							return true;
						if(canDown(row, data.board.length)) {
							if(evaluateD(data.board, row, column))
								return true;
							if(evaluateDR(data.board, row, column))
								return true;
							
						}
						if(canUp(row)) {
							if(evaluateUR(data.board, row, column))
								return true;
						}
					} else {
						if(canDown(row, data.board.length)) {
							if(evaluateD(data.board, row, column))
								return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private static boolean canUp(int index) {
		return index - 3 >= 0;  
	}
	
	private static boolean canDown(int index, int height) {
		return index + 3 < height;  
	}
	
	private static boolean canRigth(int index, int weight) {
		return index + 3 < weight;
	}
	
	private static boolean evaluateR(char[][] matrix, int row, int column) {
		char c = matrix[row][column];
		for(int i = 0; i < 4; i++)
			if(matrix[row][column + i] != c)
				return false;
		return true;
	}
	
	private static boolean evaluateD(char[][] matrix, int row, int column) {
		char c = matrix[row][column];
		for(int i = 0; i < 4; i++)
			if(matrix[row + i][column] != c)
				return false;
		return true;
	}
	
	private static boolean evaluateDR(char[][] matrix, int row, int column) {
		char c = matrix[row][column];
		for(int i = 0; i < 4; i++)
			if(matrix[row + i][column + i] != c)
				return false;
		return true;
	}
	
	private static boolean evaluateUR(char[][] matrix, int row, int column) {
		char c = matrix[row][column];
		for(int i = 0; i < 3; i++)
			if(matrix[row - i][column + i] != c)
				return false;
		return true;
	}
	
	private static boolean eveluteDiagonalEspaceUp(char[][] matrix,int row,int column,char c,char player) {
		for(int i = 0; i < 4; i++) {
			if(matrix[row - i][column + i] != c && matrix[row - i][column + i] !=player)
				return false;
		}
		return true;
	}
	
	private static boolean evaluateDiagonalEspaceDown(char[][] matrix, int row, int column,char c,char player) {
		for(int i = 0; i < 4; i++) {
			if(matrix[row + i][column + i] != c && matrix[row+i][column+i]!=player)
				return false;
		}	
		System.out.println("Ya sali de: fila: "+ row+" columna: "+column);
		return true;
	}
}
