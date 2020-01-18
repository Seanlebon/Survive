package entity;

import game.Game;

public abstract class Mob extends Entity{
	
	public float damage;
	
	public float xVel = 0;
	public float yVel = 0;
	
	public float maxHealth, health;
	
	public Mob(int x, int y, float health, float maxHealth, float damage, Game game) {
		super(x, y, game);
		
		this.maxHealth = maxHealth;
		this.health = health;
		this.damage = damage;
	}
	
	public float getPercentageHealth() {
		return health / maxHealth;
	}
	
	public void move(float speed) {
		int diffX = (int) (game.player.getX() - x);
		int diffY = (int) (game.player.getY() - y);
		float angle = (float) Math.atan2(diffY, diffX);
		xVel = (float) (speed * Math.cos(angle));
		yVel = (float) (speed * Math.sin(angle));
		
		x += xVel;
		y += yVel;
	}
}
