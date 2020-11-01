package AI;

import logic.Board;

public class Evaluation {
	
	
	//Funciones de evaluación.
	public static float evaluation(Board data) {
		return free_rows(data.board, 'P');
	}
	
	public static int free_rows(char [][] matrix, char c) {
		int cont = 0;
		for (int row = 0; row < matrix.length; row++) {
			if(max_for_row(matrix, row, c) >= 4)
				cont++;
		}
		return cont;
	}
	
	public static int max_for_row(char[][] matrix, int row, char c) {
		int cont = 0, pa = 0;
		for(int column = 0; column < matrix[row].length; column++) {
			System.out.println("Col: " + column);
			if(matrix[row][column] == c || matrix[row][column] == '-') {
				System.out.println("Entre si que si");
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
		for(int i = 0; i < 4; i++)
			if(matrix[row - i][column + i] != c)
				return false;
		return true;
	}
	

}
