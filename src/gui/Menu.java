package gui;

import java.awt.Color;
import java.awt.Graphics2D;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

import gui.components.Button;

public class Menu extends GameObject {
	
	private Button btn_start;
	
	public Menu(GameEngine parent) {
		super(parent);
	}

	@Override
	public void initResources() {
		btn_start = new Button(getImage(ResouresPaths.BTN_START), 0, 0);
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.CYAN);
		g.fillRect(100, 100, 300, 300);
		btn_start.render(g, 100, 100);
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
