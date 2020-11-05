package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

import gui.components.Button;

public class Menu extends GameObject {
	
	private Button btn_start;
	BufferedImage fondo;
	
	public Menu(GameEngine parent) {
		super(parent);
	}

	@Override
	public void initResources() {
		fondo = getImage(ResouresPaths.MENU_BG);
		BufferedImage temp = getImage(ResouresPaths.PLAY);
		btn_start = new Button(temp, (getWidth() / 2) - (temp.getWidth() / 2), (getHeight()/ 2) - (temp.getHeight() / 2) - 100);
	}

	@Override
	public void render(Graphics2D g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(fondo, 0, 0, null);
		btn_start.render(g);
	}

	@Override
	public void update(long time) {
		if(click()) {
			if(btn_start.click(getMouseX(), getMouseY())) {
				parent.nextGameID = 1;
				finish();
			}
		}
	}

}
