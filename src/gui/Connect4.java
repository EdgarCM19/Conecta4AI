package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

import AI.Algorithm;
import AI.Evaluation;
import AI.Node;
import logic.GameLogic;

public class Connect4 extends GameObject {
	
	private GameLogic game;
	
	//IMAGES
	private BufferedImage fondo;
	private BufferedImage board;
	
	//BOARD VARIABLES
	private final int BORDER = 25;
	private final int PAD_X = 5;
	private final int PAD_Y = 5;
	private final int TOKEN_SIZE = 65;
	private final int TILE_SIZE_X = PAD_X + TOKEN_SIZE + PAD_X;
	private final int TILE_SIZE_Y = PAD_Y + TOKEN_SIZE + PAD_Y;
	private final int BOARD_SIZE_X = TILE_SIZE_X * 7;
	private final int BOARD_SIZE_Y = TILE_SIZE_Y * 6;
	
	private int board_x, board_y;
	private int pointer_column, pointer_column_ai;
	
	private boolean throwed;
	
	
	public Connect4(GameEngine parent) {
		super(parent);
		game = new GameLogic();
	}

	@Override
	public void initResources() {
		fondo = getImage(ResouresPaths.BG_GAME);
		board = getImage(ResouresPaths.BOARD);
		board_x = (getWidth() / 2) - (board.getWidth() / 2);
		board_y = (getHeight()/ 2) - (board.getHeight() / 2);
	}

	@Override
	public void render(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(fondo, 0, 0, null);
		g.drawImage(board, board_x, board_y, null);
		drawPieces(g);
		if(throwed) {
			tryT(g);
		} else {
			if(pointer_column != -1) {
				g.setColor(Color.RED);
				g.fillOval(board_x + BORDER + PAD_X+ (pointer_column * TILE_SIZE_X) , 
						board_y - TILE_SIZE_Y - (BORDER / 2), TOKEN_SIZE, TOKEN_SIZE);
			}
		}
	}

	@Override
	public void update(long time) {
		throwed = false;
		pointer_column = pointer_column_ai -1;
		if(inBoard()) {
			pointer_column = calculatePointerColumn();
			if(click()) {
				if(game.board.addPiece(pointer_column, 'P')) {
					throwed = true;
					System.out.println(Evaluation.closeToWin(game.board.board, 'P'));
					pointer_column = -1;
					if(Evaluation.isMeta(game.board)) {
						System.out.println("Ganaste");
						System.out.println("[FINAL]>");
						System.out.println(game.board);
						parent.nextGameID = 0;
						finish();
					}
				}
			}
		}
	}
	
	private void tryT(Graphics2D g) {
		
		/*
		while(true) {
			Random rnd = new Random();
			int column = rnd.nextInt(7);
			pointer_column_ai = column;
			if(game.board.addPiece(column, 'A')) {
				System.out.println(Evaluation.evaluation(game.board, 'A'));
				if(Evaluation.isMeta(game.board)) {
					System.out.println("Te gano la AI");
					parent.nextGameID = 0;
					finish();
				}
				Node temp = new Node(game.board, 0, -1);
				temp.createTreeN(temp, 0, 'A', 1);
				temp.printNodeTree(temp);
				break;
			}
		} */
		
		Algorithm alg = new Algorithm(game.board);
		if(game.board.addPiece(alg.poda(), 'A')) {
			//System.out.println(Evaluation.evaluation(game.board, 'A'));
			//game.printTree(game.board);
			if(Evaluation.isMeta(game.board)) {
				System.out.println("Te gano la AI");
				System.out.println("[FINAL]>");
				System.out.println(game.board);
				parent.nextGameID = 0;
				finish();
			}
		}
	}
	
	private void drawPieces(Graphics2D g) {
		for (int column = 0; column < game.board.board[0].length; column++) {
			for (int row = game.board.board.length - 1; row >= 0; row--) {
				if(game.board.board[row][column] == '-')
					continue;
				if(game.board.board[row][column] == 'A')
					g.setColor(Color.YELLOW);
				else
					g.setColor(Color.RED);
				g.fillOval(board_x + BORDER + PAD_X + (column * TILE_SIZE_X) , 
						board_y + BORDER + PAD_Y + (row * TILE_SIZE_Y), TOKEN_SIZE, TOKEN_SIZE);
			}
		}
	}
	
	private boolean inBoard() {

		if(getMouseX() < board_x + BORDER)
			return false;
		if(getMouseX() > board_x + BOARD_SIZE_X)
			return false;
		if(getMouseY() < board_y + BORDER)
			return false;
		if(getMouseY() > board_y + BOARD_SIZE_Y)
			return false;
		return true;		
	}
	
	private int calculatePointerColumn() {
		return (int)Math.floor((getMouseX() - board_x - BORDER) / TILE_SIZE_X);
	}
	
}
