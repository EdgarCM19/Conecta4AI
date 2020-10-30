package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

public class Connect4 extends GameObject {
	
	BufferedImage fondo;
	
	public Connect4(GameEngine parent) {
		super(parent);
		System.out.println(getWidth() + "x" + getHeight());
	}

	@Override
	public void initResources() {
		fondo = getImage(ResouresPaths.BG_GAME);
	}

	@Override
	public void render(Graphics2D g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(fondo, 0, 0, null);
		g.setColor(Color.RED);
		g.fillOval(100, 100, 40, 70);
	}

	@Override
	public void update(long time) {

	}

}
