package entity;

import game.Game;

public abstract class Projectile extends Entity {

	private float xVel = 0;
	private float yVel = 0;

	private float angle;

	public float damage;

	public Projectile(int x, int y, float dmg, int mx, int my, Game game) {
		super(x, y, game);
		angle = (float) Math.atan2(y - my, x - mx);
		this.damage = dmg;
	}

	public void move(float speed) {
		xVel = (float) ((-speed) * Math.cos(angle));
		yVel = (float) ((-speed) * Math.sin(angle));

		x += xVel;
		y += yVel;
	}
}
