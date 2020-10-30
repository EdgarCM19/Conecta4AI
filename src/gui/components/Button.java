package gui.components;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;

public class Button extends Sprite {
	
	public Button(BufferedImage img, int x, int y) {
		super(img, x, y);
	}
	
	
	public boolean click(int x, int y) {
		if(x < getX())
			return false;
		if(x > getX() + width)
			return false;
		if(y < getY())
			return false;
		if(y > getY() + height)
			return false;
		return true;
	}

}
