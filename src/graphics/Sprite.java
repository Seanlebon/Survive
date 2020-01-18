package graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	
	private BufferedImage img;
	
	private int x, y;
	
	public int[] pixels;
	
	public static Sprite lightStone = new Sprite(32, 0, 0, SpriteSheet.tiles);
	public static Sprite stone = new Sprite(32, 1, 0, SpriteSheet.tiles);
	public static Sprite darkStone = new Sprite(32, 2, 0, SpriteSheet.tiles);
	
	public static Sprite grass_and_stone = new Sprite(32, 0, 1, SpriteSheet.tiles);
	public static Sprite mossyStone = new Sprite(32, 0, 2, SpriteSheet.tiles);
	
	
	public static Sprite medKit = new Sprite(32, 0, 0, SpriteSheet.entites);
	public static Sprite shield = new Sprite(32, 1, 0, SpriteSheet.entites);
	
	public static Sprite title = new Sprite("/res/title.png");
	
	public static Sprite player = new Sprite("/res/player1.png");
	
	public static Sprite enemy = new Sprite("/res/enemy.png");
	
	public static Sprite coin = new Sprite("/res/coin.png");
	public static Sprite heart = new Sprite("/res/heart.png");
	
	public static Sprite titleScreen = new Sprite("/res/titleScreen.png");
	
	public static Sprite hudHealth = new Sprite("/res/healthBar2.png");
	
	public static Sprite[] playerSprites = {
			new Sprite("/res/player1.png"),
			new Sprite("/res/player2.png"),
	};
	
	public static Sprite[] slimeEnemies = {
			new Sprite(32, 0, 0, SpriteSheet.mob),
			new Sprite(32, 1, 0, SpriteSheet.mob),
			new Sprite(32, 2, 0, SpriteSheet.mob),
	};
	
	public static Sprite[] coins = {
			new Sprite(16, 0, 0, SpriteSheet.coins),
			new Sprite(16, 1, 0, SpriteSheet.coins),
			new Sprite(16, 2, 0, SpriteSheet.coins),
			new Sprite(16, 3, 0, SpriteSheet.coins),
			new Sprite(16, 4, 0, SpriteSheet.coins),
			new Sprite(16, 5, 0, SpriteSheet.coins),
			new Sprite(16, 6, 0, SpriteSheet.coins),
			new Sprite(16, 7, 0, SpriteSheet.coins),
			new Sprite(16, 8, 0, SpriteSheet.coins),
	};
	
	
	// Overload the constructor for parsing data from a sprite sheet or direct path
	
	public Sprite(String path) {
		img = loadImage(path);
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		this.x = x * size;
		this.y = y * size;
		for (int yy = 0; yy < size; yy++)
			for (int xx = 0; xx < size; xx++)
				pixels[xx + yy * size] = sheet.pixels[(xx + this.x) + (yy + this.y) * sheet.SIZE];
	}
	
	public BufferedImage loadImage(String path) {
		try {
			img = ImageIO.read(Sprite.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	public BufferedImage getImage() {
		return img;
	}

}
