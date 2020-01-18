package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import game.Game;
import graphics.Sprite;

public class SlimeEnemy extends Mob {

	private float speed = 0.5f;
	
	private int dir = 0;
	
	private int w, h;
	
	public static final int coinsDrop = 2;
	
	private static final BufferedImage[] imgs = {
			Sprite.slimeEnemies[0].getImage(),
			Sprite.slimeEnemies[1].getImage(),
			Sprite.slimeEnemies[2].getImage()
	};
	
	
	public SlimeEnemy(int x, int y, float health, float maxHealth, float damage, Game game) {
		super(x, y, health, maxHealth, damage, game);
		w = imgs[0].getWidth();
		h = imgs[0].getHeight();
	}

	public void tick() {
		
		move(speed);
		
		if (xVel > 0) dir = 0;
		else if (xVel < 0) dir = 1;
		else dir = 2;
	}

	public void render(Graphics g) {
		
		// Rendering enemySprites
		g.drawImage(imgs[dir], (int)x, (int)y, null);
		
		// Enemy health bar
		g.setColor(Color.RED);
		g.fillRect((int)x,(int) y-8, imgs[0].getWidth(), 4);
		
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y-8, (int)(getPercentageHealth() * w), 4);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, w, h);
	}
}
