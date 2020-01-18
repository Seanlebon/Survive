package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Game;
import graphics.Sprite;

public class Coin extends Entity {

	private static final BufferedImage[] imgs = {
			Sprite.coins[0].getImage(),
			Sprite.coins[1].getImage(),
			Sprite.coins[2].getImage(),
			Sprite.coins[3].getImage(),
			Sprite.coins[4].getImage(),
			Sprite.coins[5].getImage(),
			Sprite.coins[6].getImage(),
			Sprite.coins[7].getImage(),
			Sprite.coins[8].getImage()
	};

	private float range = 64;

	private float xVel = 0;
	private float yVel = 0;
	
	private float speedOfCoins = 5.2f;
	
	private int aniCounter = 0;
	private int aniInterval = 10;
	
	private int counter = 0;
	
	private float moveCounter = 0;

	public Coin(int x, int y, Game game) {
		super(x, y, game);
	}

	public void tick() {
		
		aniCounter++;
		moveCounter += 0.55f;
		
		if (aniCounter % aniInterval == 0) {
			counter++;
		}

		// Eventually become a magnet
		x += xVel;
		y += yVel;
		
		int diffX = (int) (game.player.getX() + Player.playerDim.width / 2 - x);
		int diffY = (int) (game.player.getY() + Player.playerDim.height / 2 - y);
		float distance = (float) Math.sqrt((x - game.player.getX()) * (x - game.player.getX())
				+ (y - game.player.getY()) * (y - game.player.getY()));
		
		if (distance < range) {
			float angle = (float) Math.atan2(diffY, diffX);
			xVel = (float) (speedOfCoins * Math.cos(angle));
			yVel = (float) (speedOfCoins * Math.sin(angle));
		}
		else {
			xVel = 0;
			yVel = (float) Math.sin(moveCounter / 4);
		}
	}

	public void render(Graphics g) {
		g.drawImage(imgs[counter % imgs.length], (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, imgs[0].getWidth(), imgs[0].getHeight());
	}

}
