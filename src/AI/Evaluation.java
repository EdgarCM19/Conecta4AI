package AI;

import logic.Board;

public class Evaluation {
	
	private final static float alpha1 = 0.1f; 
	private final static float alpha2 = 0.4f; 
	private final static float alpha3 = 0.5f; 
	
	//Funcion de evaluación.
	public static float evaluation(Board data, char player) {
		float contaPlayer,contaOther;
		char other = (player == 'A') ? 'P' : 'A';
		contaPlayer = evaluationLibre(data, player) + numCanWin(data, player);
		contaOther = evaluationLibre(data, other) + numCanWin(data, other);
		return ((alpha1 * contaPlayer) - (alpha2 * contaOther)) + (alpha3 * ((isMeta(data) == player) ? 1 : 0));
	}
	
	private static float evaluationLibre(Board data,char player) {
		return  (free_rows(data.board, player) +
				free_columns(data.board, player) + 
				freeDR(data.board, player)+ 
				freeUR(data.board, player)) ;
	}
	
	private static float  numCanWin(Board data, char player) {
		return  (numRowsCanWin(data.board, player) +
				numColumnsCanWin(data.board, player) +
				numDownDiagonalsCanWin(data.board, player) + 
				numUpDiagonalsCanWin(data.board, player));
	}
	
	private static float free_rows(char [][] matrix, char c) {
		int cont = 0;
		for (int row = 0; row < matrix.length; row++) {
			if(max_for_row(matrix, row, c) >= 4)
				cont++;
		}
		return cont / 6;
	}
	
	private static int max_for_row(char[][] matrix, int row, char c) {
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
	
	private static float free_columns(char [][] matrix, char c){
		int cont = 0;
		for (int column = 0; column< 6; column++) {
			if(max_for_column(matrix,column, c) >= 4)
				cont++;
		}
		return cont / 7;
	}
	
	private static int max_for_column(char[][] matrix, int column, char c) {
		int cont = 0, pa = 0;
		for(int rows = 0;rows < matrix.length; rows++) {
			if(matrix[rows][column] == c || matrix[rows][column] == '-')
				pa += 1;
			else {
				cont = max(pa,cont);
				pa = 0;
			}	
		}
		return cont;
	}
	
	private static float freeDR(char [][] matrix, char c) {
		int cont = 0;
		for (int row = 0; canDown(row, matrix.length); row++) {
			if(maxDR(matrix, row, 0, c) >= 4)
				cont++;
		}
		for (int column = 1; canRigth(column, matrix[0].length); column++) {
			if(maxDR(matrix, 0, column, c) >= 4)
				cont++;
		}
		return cont/4;
	}
	
	private static float freeUR(char [][] matrix, char c) {
		int cont = 0;
		for (int row = matrix.length - 1; canUp(row); row--) {
			if(maxUR(matrix, row, 0, c) >= 4) {
				cont++;
			}
		}
		for (int column = 1; canRigth(column, matrix[0].length); column++) {
			if(maxUR(matrix, matrix.length - 1, column, c) >= 4) {
				cont++;
			}
		}
		return cont / 4;
	}
	
	private static int maxDR(char [][] matrix, int row, int column, char c) {
		int cont = 0, pa = 0;
		for (int index = row, desp = 0; index < matrix.length && (column + desp) < matrix[row].length; index++, desp++) {
			if(matrix[index][column + desp] == c || matrix[index][column + desp] == '-') {
				pa += 1;
			} else {
				cont = max(pa, cont);
				pa = 0;
			}
		}
		cont = max(pa, cont);
		return cont;
	}
	
	private static int maxUR(char [][] matrix, int row, int column, char c) {
		int cont = 0, pa = 0;
		for (int index = row, desp = 0; index >= 0 && (column + desp) < matrix[row].length; index--, desp++) {
			//System.out.println("[" + index + "][" + (column + desp) + "]>" + matrix[index][column + desp]);
			if(matrix[index][column + desp] == c || matrix[index][column + desp] == '-') {
				pa += 1;
			} else {
				cont = max(pa, cont);
				pa = 0;
			}
		}
		cont = max(pa, cont);
		//System.out.println("MAX UR>" + cont);
		return cont;
	}
	
	private static int max(int a, int b) {
		return (a > b) ?  a : b;
	}
	
	private static float  numRowsCanWin(char [][] matrix, char c) {
		int cont = 0;
		for (int row = 0; row < matrix.length; row++) {
			if(canWinInRow(matrix, row, c))
				cont++;
		}
		return cont / 6;
	}
	
	private static boolean canWinInRow(char [][] matrix, int row, char c) {
		int prev = 0, next = 0;
		boolean white = false;
		for (int column = 0; column < matrix[row].length; column++) {
			if(matrix[row][column] == c) {
				if(!white) {
					prev++;
				} else {
					next++;
				}
				if((prev + (next > 0 ? next : -1) + 1) >= 4)
					return true;
			} else if(matrix[row][column] == '-') {
				white = true;
			} else {
				prev = next = 0;
				white = false;
			}
		} 
		return false;
	}
	
	private static float numColumnsCanWin(char [][] matrix, char c) {
		int cont = 0;
		for (int column = 0; column < matrix[0].length; column++) {
			if(canWinInColumn(matrix, column, c))
				cont++;
		}
		return cont / 7;
	}
	
	private static boolean canWinInColumn(char [][] matrix, int column, char c) {
		int prev = 0, next = 0;
		boolean white = false;
		for (int row = 0; row < matrix.length; row++) {
			if(matrix[row][column] == c) {
				if(!white) {
					prev++;
				} else {
					next++;
				}
				if((prev + (next > 0 ? next : -1) + 1) >= 4)
					return true;
			} else if(matrix[row][column] == '-') {
				white = true;
			} else {
				prev = next = 0;
				white = false;
			}
		} 
		return false;
	}
	
	private static float numDownDiagonalsCanWin(char [][] matrix, char c) {
		int cont = 0;
		for (int row = 0; canDown(row, matrix.length); row++) {
			if(canWinInDownDiagonal(matrix, row, 0, c))
				cont++;
		}
		for (int column = 1; canRigth(column, matrix[0].length); column++) {
			if(canWinInDownDiagonal(matrix, 0, column, c))
				cont++;
		}
		return cont/7;
	}
	
	private static boolean canWinInDownDiagonal(char [][] matrix, int row, int column, char c) {
		int prev = 0, next = 0;
		boolean white = false;
		for (int index = row, desp = 0; index < matrix.length && (column + desp) < matrix[row].length; index++, desp++) {
			if(matrix[index][column + desp] == c) {
				if(!white) {
					prev++;
				} else {
					next++;
				}
				if((prev + (next > 0 ? next : -1) + 1) >= 4)
					return true;
			} else if(matrix[index][column + desp] == '-') {
				white = true;
			} else {
				prev = next = 0;
				white = false;
			}
		}
		return false;
	}
	
	private static float numUpDiagonalsCanWin(char [][] matrix, char c) {
		int cont = 0;
		for (int row = matrix.length - 1; canUp(row); row--) {
			if(canWinInUpDiagonal(matrix, row, 0, c))
				cont++;
		}
		for (int column = 1; canRigth(column, matrix[0].length); column++) {
			if(canWinInUpDiagonal(matrix, matrix.length - 1, column, c))
				cont++;
		}
		return cont/7;
	}
	
	private static boolean canWinInUpDiagonal(char [][] matrix, int row, int column, char c) {
		int prev = 0, next = 0;
		boolean white = false;
		for (int index = row, desp = 0; index >= 0 && (column + desp) < matrix[row].length; index--, desp++) {
			if(matrix[index][column + desp] == c) {
				if(!white) {
					prev++;
				} else {
					next++;
				}
				if((prev + (next > 0 ? next : -1) + 1) >= 4)
					return true;
			} else if(matrix[index][column + desp] == '-') {
				white = true;
			} else {
				prev = next = 0;
				white = false;
			}
		}
		return false;
	}

	
	
	//META
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
	
}

