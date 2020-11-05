package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

import AI.Algorithm;
import AI.Evaluation;
import gui.components.Button;
import logic.GameLogic;

public class Connect4 extends GameObject {
	
	private GameLogic game;
	
	//IMAGES
	private BufferedImage fondo;
	private BufferedImage board;
	private BufferedImage win, lose, tie;
	
	
	Button back_btn;
	
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
	
	private boolean throwed, finished;
	private char winner;
	
	
	public Connect4(GameEngine parent) {
		super(parent);
		game = new GameLogic();
		finished = false;
		winner = '-';
	}

	@Override
	public void initResources() {
		fondo = getImage(ResouresPaths.BG_GAME);
		board = getImage(ResouresPaths.BOARD);
		win = getImage(ResouresPaths.WIN);
		lose = getImage(ResouresPaths.LOSE);
		tie = getImage(ResouresPaths.TIE);
		
		BufferedImage temp = getImage(ResouresPaths.BACK);
		back_btn = new Button(temp, (getWidth() / 2) - (temp.getWidth() / 2), (getHeight()/ 2) - (temp.getHeight() / 2));
		
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
		if(finished) {
			switch (winner) {
			case 'P': //Ganar
				g.drawImage(win, 0, 0, null);
				break;
			case 'A': //Perder
				g.drawImage(lose, 0, 0, null);
				break;
			case 'E': //Empate
				g.drawImage(tie, 0, 0, null);
				break;
			default:
				break;
			}
			back_btn.render(g);
		} else {
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
	}

	@Override
	public void update(long time) {
		throwed = false;
		pointer_column = pointer_column_ai -1;
		if(finished) {
			if(click())
				if(back_btn.click(getMouseX(), getMouseY())) 
					end();
			
		} else {
			if(inBoard()) {
				pointer_column = calculatePointerColumn();
				if(click()) {			
					if(game.p1.throwPiece(pointer_column)) {
						if(Evaluation.isMeta(game.board) == 'P') {
							finished = true;
							winner = 'P';							
						}
						throwed = true;
						pointer_column = -1;
					}
				}
			}
		}
	}
	
	private void end() {
		parent.nextGameID = 0;
		finish();
	}
	
	private void tryT(Graphics2D g) {	
		if(!finished) {
			Algorithm alg = new Algorithm(game.board);
			if(game.p2.throwPiece(alg.poda())) {
				char eval = Evaluation.isMeta(game.board); 
				if(eval == 'A') {					
					finished = true;
					winner = 'A';
				} else if(eval == 'E'){
					finished = true;
					winner = 'E';									
				}
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
