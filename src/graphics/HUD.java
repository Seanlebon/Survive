package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Game;

public class HUD {

	private Game game;

	double percentPlayerHealth = 1;
	double percentPlayerShield = 1;

	private Font font = new Font("arial", Font.BOLD, 12);
	private Font font2 = new Font("arial", Font.BOLD, 9);

	private Rectangle healthShieldRec = new Rectangle(Game.WIDTH / 2 - 300, Game.HEIGHT - 91, 600, 41);

	private int healthOpacity = 255;

	private BufferedImage hudDisplay = Sprite.hudHealth.getImage();
	
	public HUD(Game game) {
		this.game = game;
	}

	public void tick() {
		// Calculate percentage of health;
		percentPlayerHealth = game.player.getHealth() / game.player.getMaxHealth();
		percentPlayerShield = game.player.getShield() / game.player.getMaxShield();

		healthShieldRec = new Rectangle(Game.WIDTH / 2 - 300 + game.camera.getX(), Game.HEIGHT - 91 + game.camera.getY(), 600, 41);
		
		if (game.player.getBounds().intersects(healthShieldRec)) {
			healthOpacity = 32;
		}
		else {
			healthOpacity = 255;
		}
		// System.out.println(percentPlayerHealth);
	}

	public void render(Graphics g) {

		// background
		
		//g.drawImage(hudDisplay, Game.WIDTH / 2 - 300, Game.HEIGHT - 100, null);
		
		g.setColor(new Color(0, 0, 0, healthOpacity));
		g.fillRect(Game.WIDTH / 2 - 300, Game.HEIGHT - 75, 600, 25);
		g.fillRect(Game.WIDTH / 2 - 250, Game.HEIGHT - 91, 500, 16);
		
		g.setColor(new Color(128,128,128, healthOpacity));
		g.fillRect(Game.WIDTH / 2 - 295, Game.HEIGHT - 70, 590, 15);
		g.fillRect(Game.WIDTH / 2 - 246, Game.HEIGHT - 88, 492, 12);

		// Drawing health bar
		if (percentPlayerHealth <= 0.1)
			g.setColor(new Color(139, 0, 0, healthOpacity));
		else if (percentPlayerHealth <= 0.25 && percentPlayerHealth > 0.1)
			g.setColor(new Color(255, 0, 0, healthOpacity));
		else if (percentPlayerHealth <= 0.75 && percentPlayerHealth > 0.25)
			g.setColor(new Color(255, 140, 0, healthOpacity));
		else
			g.setColor(new Color(0, 255, 0, healthOpacity));
		g.fillRect(Game.WIDTH / 2 - 295, Game.HEIGHT - 70, (int) (percentPlayerHealth * 590), 15);

		// Drawing Shield
		g.setColor(new Color(0, 255, 255, healthOpacity));
		g.fillRect(Game.WIDTH / 2 - 246, Game.HEIGHT - 88, (int) (percentPlayerShield * 492), 12);

		// Stats for health
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString(Math.round(game.player.getHealth()) + "/" + Math.round(game.player.getMaxHealth()),
				Game.WIDTH / 2 - 20, Game.HEIGHT - 58);

		// Stats for Shield
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString(Math.round(game.player.getShield()) + "/" + Math.round(game.player.getMaxShield()),
				Game.WIDTH / 2 - 20, Game.HEIGHT - 77);
	}

}
