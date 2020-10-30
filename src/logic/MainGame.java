package logic;

import java.awt.Dimension;

import com.golden.gamedev.GameLoader;

import gui.SceneLoader;

public class MainGame {

	public static void main(String[] args) {
		Board test = new Board();
		test.addPiece(3, '1');
		test.addPiece(3, '1');
		test.addPiece(3, '1');
		test.addPiece(3, '2');
		test.addPiece(3, '2');
		test.addPiece(3, '2');
		test.addPiece(3, '2');
		System.out.println(test.canAdd(3));
		test.addPiece(4, '2');
		test.addPiece(4, '2');
		test.addPiece(4, '2');
		System.out.println(test.columnIndex(4));
		System.out.println(test.canAdd(2));
		test.print();
		System.out.println("Eh");
		GameLoader game = new GameLoader();
		game.setup(new SceneLoader(), new Dimension(1080, 720), false);
		game.start();
	}

}
