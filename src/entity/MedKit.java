package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Game;
import graphics.Sprite;

public class MedKit extends Entity {
	
	public static final float HEALTH_INCREASE = 25.0f;

	private BufferedImage img = Sprite.medKit.getImage();

	private int w, h;

	public MedKit(int x, int y, Game game) {
		super(x, y, game);
		w = img.getWidth();
		h = img.getHeight();
	}

	public void tick() {

	}

	public void render(Graphics g) {
		g.drawImage(img, (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, w, h);
	}

}
