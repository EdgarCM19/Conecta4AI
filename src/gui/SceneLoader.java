package gui;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

public class SceneLoader extends GameEngine {

	public GameObject getGame(int gameID) {
		switch (gameID) {
		case 0: //Menu
			return new Menu(this);
		case 1: //Game
			return new Connect4(this);
		default:
			break;
		}
		return null;
	}

}
