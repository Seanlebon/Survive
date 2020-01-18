package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import game.Game;

public class EntityHandler {

	public ArrayList<Entity> obj = new ArrayList<Entity>();

	private Game game;

	private Player player;

	private int range = 50;
	
	ArrayList<Entity> remove = new ArrayList<Entity>();
	ArrayList<Entity> add = new ArrayList<Entity>();

	public EntityHandler(Game game, Player player) {
		this.game = game;
		this.player = player;
	}

	public void tick() {
		
		remove = new ArrayList<Entity>();
		add = new ArrayList<Entity>();
		
		for (Entity entity : obj) {
			entity.tick();

			// Testing
			if (entity instanceof Coin) {
				if (entity.getBounds().intersects(player.getBounds())) {
					remove.add(entity);
					game.coins += 1;
				}
			}
			else if (entity instanceof MedKit) {
				if (entity.getBounds().intersects(player.getBounds())) {
					remove.add(entity);
					player.addHealth(MedKit.HEALTH_INCREASE);
				}
			}

			else if (entity instanceof Mob) {
				if (((Mob) entity).health <= 0) {
					remove.add(entity);
					int coinsToDrop = 0;
					if (entity instanceof SlimeEnemy)
						coinsToDrop = SlimeEnemy.coinsDrop;
					for (int i = 0; i < coinsToDrop; i++)
						add.add(new Coin((int) entity.x + game.r.nextInt(range / 2) - range,
								(int) entity.y + game.r.nextInt(range / 2) - range, game));
				}
				if (entity instanceof SlimeEnemy) {
					//
				}
				if (entity.getBounds().intersects(player.getBounds())) {
					player.damagePlayer(((Mob) entity).damage);
				}
			}
			else if (entity instanceof Projectile) {
				if (entity.x < -10 || entity.x > Game.MapDim.width + 10 || entity.y < -10 || entity.y > Game.MapDim.height + 10) {
					remove.add(entity);
				}
				for (Entity enemy : obj) {
					if (enemy instanceof Mob) {
						if (entity.getBounds().intersects(enemy.getBounds())) {
							((Mob) enemy).health -= ((Projectile) entity).damage;
							remove.add(entity);
							break;
						}
					}
				}
			}
		}
		obj.removeAll(remove);
		obj.addAll(add);
	}

	public void render(Graphics g) {
		
		Iterator<Entity> iter = obj.iterator();
		
		while (iter.hasNext()) {
			iter.next().render(g);
		}
	}

	public void add(Entity entity) {
		this.obj.add(entity);
	}

	public void remove(Entity entity) {
		this.obj.remove(entity);
	}

	public void removeAll() {
		this.obj.clear();
	}
}
