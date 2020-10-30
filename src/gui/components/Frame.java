package gui.components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;

public class Frame extends Sprite {
	
	private ArrayList<Sprite> components;
	private BufferedImage img;
	private int x, y;
	
	public Frame(BufferedImage img, int x, int y) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.components = new ArrayList<Sprite>(); 
	}
	
	public void addComponent(Sprite component) {
		components.add(component);
	}
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		for (Sprite sprite : components) {
			sprite.render(g);
		}
	}
	
	@Override
	public void render(Graphics2D g, int x, int y) {
		super.render(g, x, y);
		for (Sprite sprite : components) {
			sprite.render(g, x, y);
		}
	}
	
	@Override
	public void update(long time) {
		super.update(time);
		for (Sprite sprite : components) {
			sprite.update(time);
		}
	}

}
