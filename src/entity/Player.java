package entity;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Game;
import graphics.Sprite;
import graphics.TileMap;

public class Player {

	public float x, y;

	private int w, h;

	public float xVel = 0;
	public float yVel = 0;

	private float walkSpeed = 4.0f;
	private float runSpeed = 6.0f;

	private static final BufferedImage[] imgs = { Sprite.playerSprites[0].getImage(),
			Sprite.playerSprites[1].getImage() };

	public static final Dimension playerDim = new Dimension(32, 32);

	private float health = 200.0f;
	private float maxHealth = 200.0f;

	private float shield = 100.0f;
	private float maxShield = 100.0f;

	private float shieldInc = 1.0f;
	private int shieldCounter = 0;
	private int shieldRegenTime = 200;

	private Game game;

	private int dir = 0;

	public Player(int x, int y, Game game) {
		this.x = x;
		this.y = y;
		this.game = game;
		this.w = imgs[0].getWidth();
		this.h = imgs[0].getHeight();
	}

	public void tick() {

		// Player Movement
		float speed = walkSpeed;

		if (game.input.keyDown[4])
			speed = runSpeed;

		if (game.input.keyDown[0]) {
			x += speed;
			dir = 1;
		}
		if (game.input.keyDown[1]) {
			x += -speed;
			dir = 0;
		}
		if (game.input.keyDown[2]) {
			y += speed;
		}
		if (game.input.keyDown[3]) {
			y += -speed;
		}

		// Keeping player in bounds
		x = Game.clamp((int) x, 0 + TileMap.tileDim.width,
				Game.MapDim.width - playerDim.height - TileMap.tileDim.width);
		y = Game.clamp((int) y, 0 + TileMap.tileDim.height,
				Game.MapDim.height - playerDim.height - TileMap.tileDim.height);

		// Shield increase
		if (shield < maxShield) {
			shieldCounter++;
			if (shieldCounter % shieldRegenTime == 0) {
				shield = clamp(shield + shieldInc, 0.0f, maxShield);
			}
		} else {
			shieldCounter = 0;
		}
	}

	public void damagePlayer(double dmg) {
		if (shield >= 0) {
			if (shield - dmg < 0) {
				health -= Math.abs(shield - dmg);
				shield = 0;
			} else {
				shield -= dmg;
			}
		} else {
			health -= dmg;
		}
	}

	public void addHealth(float healthIncrease) {
		health = clamp(health + healthIncrease, 0, maxHealth);
	}

	public void render(Graphics g) {
		g.drawImage(imgs[dir], (int) x, (int) y, null);
	}

	public static float clamp(float var, float min, float max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getxVel() {
		return xVel;
	}

	public void setxVel(float xVel) {
		this.xVel = xVel;
	}

	public float getyVel() {
		return yVel;
	}

	public void setyVel(float yVel) {
		this.yVel = yVel;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;
	}

	public double getShield() {
		return shield;
	}

	public void setShield(float shield) {
		this.shield = shield;
	}

	public double getMaxShield() {
		return maxShield;
	}

	public void setMaxShield(float maxShield) {
		this.maxShield = maxShield;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, w, h);
	}
}
