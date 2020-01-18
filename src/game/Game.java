package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import entity.Coin;
import entity.EntityHandler;
import entity.MedKit;
import entity.Player;
import entity.SlimeEnemy;
import graphics.HUD;
import graphics.Sprite;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 960;
	public static final int HEIGHT = 566;

	public static final Dimension MapDim = new Dimension(1280, 1280);

	private double version = 1.4;
	private String title = "Survive";

	private Thread thread;
	private boolean isRunning = false;

	// DECALRING INSTANCES OF NEEDED CLASSES

	private Window window;

	public EntityHandler handler;

	public Player player = new Player(MapDim.width / 2 - 16, MapDim.height / 2 - 16, this);

	public static Level lvl = new Level(MapDim.width, MapDim.height);

	private HUD hud;

	public Camera camera;

	public Random r = new Random();

	public BufferedImage lvlImg = lvl.getImg();

	private MiniMap miniMap;

	private Spawn spawn = new Spawn(this);

	private Menu menu;

	private BufferedImage aniImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

	private BufferedImage titleScreen = Sprite.titleScreen.getImage();

	public InputEvents input = new InputEvents(this);

	// private BufferedImage[] enemies = Sprite.titleScreen.getImage();

	// MAIN VARS

	private int counter = 0;
	public int counter2 = 0;

	public int coins = 0;

	public int wave = 1;

	public enum GameState {
		Game, Menu, GameOver, AnimateIn, AnimateOut, TitleScreen, Pause, WaveTransition,
	};

	// Beginning GameState
	public GameState state = GameState.Menu;

	public Game() {

		window = new Window(WIDTH, HEIGHT, title + " v " + version, this);

		menu = new Menu(this);

		camera = new Camera(0, 0);

		handler = new EntityHandler(this, player);

		this.addKeyListener(input);
		this.addMouseListener(input);
		this.addMouseListener(menu);
		this.addMouseMotionListener(menu);

		hud = new HUD(this);

		miniMap = new MiniMap(5, 5, this);

		// for testing

		/*
		 * for (int i = 0; i < 5; i++) { handler.add(new MedKit(r.nextInt(900) +
		 * 300, r.nextInt(900) + 300, this)); }
		 * 
		 * for (int i = 0; i < 50; i++) { handler.add(new Coin(r.nextInt(900) +
		 * 300, r.nextInt(900) + 300, this)); }
		 */
		/*for (int i = 0; i < 100; i++) {
			handler.add(new SlimeEnemy(r.nextInt(1280), r.nextInt(1280), 5.0f, 5.0f, 0.5f, this));
		}*/
	}

	public void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				try {
					tick();
					render();
					updates++;
					frames++;
				} catch (RuntimeException e) {
				}
				delta--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				window.frame.setTitle(title + " v " + version + " | FPS: " + frames + " | Updates: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}

	public void tick() {
		if (state == GameState.Game) {
			// Check for game over
			handler.tick();
			hud.tick();
			player.tick();
			camera.tick(player);
			// input.tick();

			if (player.getHealth() <= 0)
				state = GameState.GameOver;
			counter2 += 3;
		} else if (state == GameState.WaveTransition) {
			
			handler.tick();
			player.tick();
			camera.tick(player);
			
			counter2 += 1;
		} else if (state == GameState.Menu) {
			menu.tick();
			camera.tick(player);
			counter2 += 3;
		} else if (state == GameState.TitleScreen) {
			counter2 += 1;
		} else if (state == GameState.Pause) {

		} else if (state == GameState.GameOver) {
			// getUsername();
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			// The optimal amount of buffers in the highest number, but it
			// stresses resources
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		Graphics2D g2d = (Graphics2D) g;

		Graphics ani = aniImg.getGraphics();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		if (state == GameState.TitleScreen) {

			if (counter2 >= 0 && counter2 <= 256) {
				g.setColor(new Color(0, 0, 0, 256 - counter2));
			} else if (counter2 >= 257 && counter2 <= 510) {
				g.setColor(new Color(0, 0, 0, counter2 % 256));
			} else {
				state = GameState.Menu;
				counter2 = 0;
			}
			g.drawImage(titleScreen, 0, 0, null);
			g.fillRect(0, 0, WIDTH, HEIGHT);
		}

		if (state == GameState.Menu) {

			g2d.translate(-camera.getX(), -camera.getY());
			g.drawImage(lvlImg, 0, 0, null);
			g2d.translate(camera.getX(), camera.getY());
			/*
			 * s += cnt % 127 == 0 ? 1 : 0; if (s % 2 == 0) { g.setColor(new
			 * Color(255,255,255,127 - (cnt % 127))); } else { g.setColor(new
			 * Color(255,255,255,cnt%127)); } g.fillRect(0, 0, WIDTH, HEIGHT);
			 */
			menu.render(g);

			if (counter2 >= 0 && counter2 <= 255) {
				g.setColor(new Color(0, 0, 0, 255 - counter2));
				g.fillRect(0, 0, WIDTH, HEIGHT);
			}
		}

		if (state == GameState.AnimateIn) {

			g2d.translate(-camera.getX(), -camera.getY());
			g.drawImage(lvlImg, 0, 0, null);
			g2d.translate(camera.getX(), camera.getY());

			counter += 3;
			if (counter < 256) {
				menu.render(g);
				ani.setColor(new Color(0, 0, 0, counter));
			} else {
				state = GameState.WaveTransition;
				counter2 = 0;
			}
			ani.fillRect(0, 0, WIDTH, HEIGHT);
			g.drawImage(aniImg, 0, 0, null);
		}

		if (state == GameState.WaveTransition) {
			
			

			g2d.translate(-camera.getX(), -camera.getY());
			
			g.drawImage(lvlImg, 0, 0, null);
			player.render(g);
			handler.render(g);
			
			
			g2d.translate(camera.getX(), camera.getY());

			if (counter2 < 256) {
				g.setColor(new Color(255, 255, 255, counter2));
			} else if (counter2 > 255 && counter2 < 512) {
				g.setColor(new Color(255, 255, 255, 255 - counter2 % 256));
			} else {
				state = GameState.Game;
			}
			
			g.setFont(new Font("arial", Font.BOLD, 140));
			g.drawString("WAVE " + wave, Game.WIDTH / 2 - 225, Game.HEIGHT / 2 - 25);
			
		}

		// RENDERING WHEN PLAYING GAME

		if (state == GameState.Game) {

			g2d.translate(-camera.getX(), -camera.getY());

			g.drawImage(lvlImg, 0, 0, null);
			handler.render(g);
			player.render(g);

			g2d.translate(camera.getX(), camera.getY());

			hud.render(g);
			miniMap.render(g);

			g.setColor(Color.BLACK);

			g.setFont(new Font("arial", Font.BOLD, 12));
			g.drawString("[" + player.x + ", " + player.y + "]", 13, 105);
			g.drawString("Coins: " + coins, 40, 10);

			if (counter2 >= 0 && counter2 <= 255) {
				g.setColor(new Color(0, 0, 0, 255 - counter2));
				g.fillRect(0, 0, WIDTH, HEIGHT);
			}

			/*
			 * g.setColor(new Color(255,255,255,128)); g.setFont(new
			 * Font("arial", Font.BOLD, 120)); g.drawString("WAVE 1", 100, 100);
			 */

			// g.drawImage(anim, x, y, observer)
		}

		if (state == GameState.Pause) {

			g2d.translate(-camera.getX(), -camera.getY());

			g.drawImage(lvlImg, 0, 0, null);
			handler.render(g);
			player.render(g);

			g2d.translate(camera.getX(), camera.getY());

			menu.render(g);

		}

		if (state == GameState.GameOver) {
			// getUsername();
		}
		g.dispose();
		bs.show();
	}

	// Could be used to animate in
	public void animateIn(Graphics g) {
		if (counter2 >= 0 && counter2 <= 255) {
			g.setColor(new Color(0, 0, 0, 255 - counter2));
			g.fillRect(0, 0, WIDTH, HEIGHT);
		}
	}

	public static float clamp(int var, int min, int max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	public String getUsername() {
		String username = JOptionPane.showInputDialog("Enter your name: ");
		if (username == null)
			username = "unknown";
		return username;
	}

	public static void main(String[] args) {
		new Game();
	}
}
