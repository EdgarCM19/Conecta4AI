package AI;

import logic.Board;

public class Evaluation {
	
	private final static float alpha1 = 0.15f; //Filas
	private final static float alpha2 = 0.15f; //Columnas
	private final static float alpha3 = 0.2f; //Diagonal sup
	private final static float alpha4 = 0.2f; //Diagonal inf
	private final static float alpha5 = 0.3f; //Close to win
	
	//Funciones de evaluación.
	public static float evaluation(Board data, char player) {
		char other = (player == 'A') ? 'P' : 'A';
		return ((alpha1 * free_rows(data.board, player)) +
				(alpha2 * free_columns(data.board, player)) +
				(alpha3 * diagonals_down(data.board, player)) +
				(alpha4 * diagonals_up(data.board, player)) +
				(alpha5 * closeToWin(data.board, player)))
				-
				((alpha1 * free_rows(data.board, other)) +
				(alpha2 * free_columns(data.board, other)) +
				(alpha3 * diagonals_down(data.board, other)) +
				(alpha4 * diagonals_up(data.board, other)) +
				(alpha5 * closeToWin(data.board, other))) + ((isMeta(data) == player) ? 5f : 0f);
	}
	
	public static int followingPieces(char [][] matrix, int row, int column, int mode) {
		int cont = 0;
		char c = matrix[row][column];
		switch(mode) {
			case 0: //Derecha
				for(int index = 0; index < 3; index++) {	
					if(matrix[row][column + index] != c) 
						return cont;
					else
						cont++;
				}
				return cont;
			case 1: //Arriba
				for(int index = 0; index < 3; index++) {	
					if(matrix[row - index][column] != c) 
						return cont;
					else
						cont++;
				}
				break;
			case 2: //Diagonal arriba
				for(int index = 0; index < 3; index++) {	
					if(matrix[row - index][column + index] != c) 
						return cont;
					else
						cont++;
				}
				break;
			case 3: //Diagonal abajo
				for(int index = 0; index < 3; index++) {	
					if(matrix[row + index][column + index] != c) 
						return cont;
					else
						cont++;
				}
				break;
			default:
				return -1;
		}
		return cont;
	}
	
	public static int closeToWin(char [][] matrix, char player) {
		char other = player == 'A' ? 'P' : 'A';
		int close = 0, aux;
		for (int i = matrix.length - 1; i >= 0; i--) {
			for (int j = 0; j < matrix[i].length; j++) {
				if(matrix[i][j] == player) {
					if(canUp(i) && matrix[i][j] != player){
						aux = followingPieces(matrix, i, j, 1);
						if(aux == 3) {
							if(matrix[i - aux][j] == '-') {
								//System.out.println("> Posibilidad hacia arriba en: " + i + ", " + j);
								close++;
							}
						}
						//System.out.println("Break 1");
						if(canRigth(j, matrix[0].length) && matrix[i][j] == player) {
							if((aux = followingPieces(matrix, i, j, 0)) == 3) {
								if(matrix[i][j + aux] == '-' && j > 0) {
									//System.out.println("Posibilidad hacia derecha en: " + i + ", " + j);
									close++;
									if(matrix[i][j-1] == '-') {
										//System.out.println("Posibilidad izquierda en: " + i + ", " + j);
										close++;
									}
								}
							}
							//System.out.println("break 2");
							//Diagonal arriba derecha
							if((aux = followingPieces(matrix, i, j, 2)) == 3) {
								if(matrix[i - aux][j + aux] == '-' && i < matrix.length - 1 && j > 0) {
									//System.out.println("Posibilidad hacia arriba derecha en: " + i + ", " + j);
									close++;
									if(matrix[i + 1][j - 1] == '-') {
										//System.out.println("Posibilidad hacia arriba derecha antes en: " + (i - 1) + ", " + (j - 1));
										close++;
									}
								}
							}
							
						}
					}
					//System.out.println("break 3");
					if(canDown(i, matrix.length)){
						if(canRigth(j, matrix[0].length) && matrix[i][j] == player) {
							if((aux = followingPieces(matrix, i, j, 3)) == 3) {							
								if(matrix[i + aux][j + aux] == '-' && j < matrix[i].length) {
									close++;
									if(matrix[i + 1][j + 1] == '-') {
										//System.out.println("Posibilidad hacia arriba derecha antes en: " + (i - 1) + ", " + (j - 1));
										close++;
									}
								}
							}
						}
					}
				}
			}
		}
		return close;
	}
	
	
	//[x] filas disponibles
	//Cuenta cuantas filas cuentan con el espacio minimo para ganar
	public static int free_rows(char [][] matrix, char c) {
		int cont = 0;
		for (int row = 0; row < matrix.length; row++) {
			if(max_for_row(matrix, row, c) >= 4)
				cont++;
		}
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
		return cont;
	}
	
	//[x] columnas disponibles 
	public static int free_columns(char [][] matrix, char c){
		int cont = 0;
		for (int column = 0; column< 6; column++) {
			if(max_for_column(matrix,column, c) >= 4)
				cont++;
		}
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
		return cont;
	}
	
	//[x] diagonal derecha hacia abajo 
	private static int diagonals_down(char[][]matrix,char c) {
		int cont=0;
		for(int colum = 0;colum<4;colum++) {
			if(max_for_diagonals_down(matrix,colum,c)>0)
				cont++;
		}
		return cont;
	}
	
	private static int max_for_diagonals_down(char[][] matrix, int colum,char c) {
		int cont=0;
		for(int rows=0;rows<3;rows++) {
			if(evaluateDiagonalEspaceDown(matrix,rows,colum,'-',c))
				cont++;	
		}
		return cont;
	}

	//[x] diagonal derecha hacia arriba
	public static int diagonals_up(char [][] matrix, char c) {
		int cont=0;
		for(int colm=0;colm<4;colm++) {
			if(max_for_diadonals_up(matrix,colm,c)>0)
				cont++;
		}
		return cont;
	}
	private static int max_for_diadonals_up(char[][] matrix, int colm, char c) {
		int cont=0;
		for(int rows=5;rows>2;rows--) {
			if(eveluteDiagonalEspaceUp(matrix,rows,colm,'-',c))
				cont++;
		}
		return cont;
	}

	private static int min(int a, int b) {
		return (a < b) ?  a : b;
	}
	
	private static int max(int a, int b) {
		return (a > b) ?  a : b;
	}
	//Funciones para ver si es meta.
	/*
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
		for(int c : data.pieces)
			if(c < data.board.length)
				return false;
		return true;
	}
	*/
	
	public static char isMeta(Board data) {		
		for(int column = 0; column < data.board[0].length; column++) {
			for(int row = 0; row < data.board.length; row++) {
				if(data.board[row][column] != '-') {
					if(canRigth(column, data.board[0].length)) {
						if(evaluateR(data.board, row, column))
							return data.board[row][column];
						if(canDown(row, data.board.length)) {
							if(evaluateD(data.board, row, column))
								return data.board[row][column];
							if(evaluateDR(data.board, row, column))
								return data.board[row][column];
							
						}
						if(canUp(row)) {
							if(evaluateUR(data.board, row, column))
								return data.board[row][column];
						}
					} else {
						if(canDown(row, data.board.length)) {
							if(evaluateD(data.board, row, column))
								return data.board[row][column];
						}
					}
				}
			}
		}
		for(int c : data.pieces)
			if(c < data.board.length)
				return '-';
		return 'E';
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
		for(int i = 0; i < 4; i++)
			if(matrix[row - i][column + i] != c)
				return false;
		return true;
	}
	
	private static boolean eveluteDiagonalEspaceUp(char[][] matrix,int row,int column,char c,char player) {
		for(int i = 0; i < 4; i++) {
			if(matrix[row - i][column + i] != c && matrix[row - i][column + i] != player)
				return false;
		}
		return true;
	}
	
	private static boolean evaluateDiagonalEspaceDown(char[][] matrix, int row, int column,char c,char player) {
		for(int i = 0; i < 4; i++) {
			if(matrix[row + i][column + i] != c && matrix[row+i][column+i]!=player)
				return false;
		}	
		return true;
	}
}
