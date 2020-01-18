package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import graphics.Sprite;

public class Menu extends MouseAdapter implements MouseMotionListener {

	private Button b1;
	private Button b2;
	private Button b3;
	private Button b4;
	private Button b5;
	private Button b6;
	private Button b7;

	private BufferedImage title = Sprite.title.getImage();

	private Game game;

	private int buttonOpacity = 128;

	private Font font1 = new Font("", Font.BOLD, 128);

	private float titleCounter = 0;

	private Color buttonColour = new Color(0, 0, 0, buttonOpacity);

	private ArrayList<Button> menu_buttons = new ArrayList<Button>();
	private ArrayList<Button> pause_buttons = new ArrayList<Button>();

	public Menu(Game game) {
		this.game = game;
		b1 = new Button(Game.WIDTH / 2 - 300, 225, 600, 40, "PLAY", "arial", 25, buttonColour);
		b2 = new Button(Game.WIDTH / 2 - 300, 280, 600, 40, "INSTRUCTIONS", "arial", 25, buttonColour);
		b3 = new Button(Game.WIDTH / 2 - 300, 335, 295, 40, "OPTIONS", "arial", 25, buttonColour);
		b4 = new Button(Game.WIDTH / 2 + 5, 335, 295, 40, "QUIT", "arial", 25, buttonColour);

		b5 = new Button(Game.WIDTH / 2 - 300, 225, 600, 40, "RESUME", "arial", 25, buttonColour);
		b6 = new Button(Game.WIDTH / 2 - 300, 280, 600, 40, "MAIN MENU", "arial", 25, buttonColour);
		b7 = new Button(Game.WIDTH / 2 - 300, 355, 600, 40, "QUIT", "arial", 25, buttonColour);

		// Add Menu Buttons
		menu_buttons.add(b1);
		menu_buttons.add(b2);
		menu_buttons.add(b3);
		menu_buttons.add(b4);

		// Add Pause Buttons
		pause_buttons.add(b5);
		pause_buttons.add(b6);
		pause_buttons.add(b7);
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (game.state == Game.GameState.Menu) {

			if (b1.intersectsAtPoint(mx, my)) {
				game.state = Game.GameState.AnimateIn;
			}

			if (b4.intersectsAtPoint(mx, my))
				System.exit(0);
		} else if (game.state == Game.GameState.Pause) {
			if (b5.intersectsAtPoint(mx, my)) {
				game.state = Game.GameState.Game;
			}

			if (b6.intersectsAtPoint(mx, my)) {
				game.state = Game.GameState.Menu;
			}

			if (b7.intersectsAtPoint(mx, my)) {
				System.exit(0);
			}
		}

	}

	public void mouseMoved(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (game.state == Game.GameState.Menu) {

			for (Button b : menu_buttons) {
				if (b.intersectsAtPoint(mx, my))
					b.setButtonColour(new Color(0, 0, 0, 255));
				else
					b.setButtonColour(buttonColour);
			}
		} else if (game.state == Game.GameState.Pause) {

			for (Button b : pause_buttons) {
				if (b.intersectsAtPoint(mx, my))
					b.setButtonColour(new Color(0, 0, 0, 255));
				else
					b.setButtonColour(buttonColour);
			}

		}

	}

	public void tick() {
		titleCounter += .10f;
	}

	public void render(Graphics g) {
		// Make sure that the outline fillrect has same opacity and foreground
		if (game.state == Game.GameState.Menu) {

			g.drawImage(title, Game.WIDTH / 2 - title.getWidth() / 2, (int) (70 + 10 * Math.sin(titleCounter)), null);

			// g.setColor(Color.black);
			// g.fillRect(Game.WIDTH / 2 - 300, 25, 600, 125);
			b1.render(g, 2, Color.WHITE);
			b2.render(g, 2, Color.WHITE);
			b3.render(g, 2, Color.WHITE);
			b4.render(g, 2, Color.WHITE);
		} else if (game.state == Game.GameState.Pause) {

			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.BOLD, 70));
			g.drawString("PAUSED!", Game.WIDTH / 2 - 150, 150);

			b5.render(g, 2, Color.WHITE);
			b6.render(g, 2, Color.WHITE);
			b7.render(g, 2, Color.WHITE);
		}

	}
}
