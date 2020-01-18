package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Game;

public abstract class Entity {

	public float x, y;

	public Game game;

	public Entity(int x, int y, Game game) {
		this.x = x;
		this.y = y;
		this.game = game;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();
}
