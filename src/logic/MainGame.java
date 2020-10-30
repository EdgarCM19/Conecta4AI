package logic;

import java.awt.Dimension;

import com.golden.gamedev.GameLoader;

import gui.SceneLoader;

public class MainGame {

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new SceneLoader(), new Dimension(1080, 720), false);
		game.start();
	}

}
