package game;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import graphics.Sprite;
import graphics.TileMap;

public class Level {
	
	private final int WIDTH, HEIGHT;
	
	public static final Dimension tileDim = new Dimension(32, 32);
	
	private Random r = new Random();
	
	public BufferedImage img;
	
	public Level(int widthOfMap, int heightOfMap) {
		this.WIDTH = widthOfMap;
		this.HEIGHT = heightOfMap;
		
		img = new BufferedImage(widthOfMap, heightOfMap, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = img.createGraphics();
		
		for (int y = 0; y < HEIGHT / TileMap.tileDim.height; y++) {
			for (int x = 0; x < WIDTH / TileMap.tileDim.width; x++) {

				if ((y == 0 || x == 0) || (y == (HEIGHT / TileMap.tileDim.height) - 1 || x == (WIDTH / TileMap.tileDim.width) - 1)) {
					g2d.drawImage(Sprite.darkStone.getImage(), x * tileDim.width, y * tileDim.height, tileDim.width, tileDim.height, null);
					continue;
				}
				int rs = r.nextInt(10);
				if (rs == 0)
					g2d.drawImage(Sprite.mossyStone.getImage(), x * tileDim.width, y * tileDim.height, tileDim.width, tileDim.height, null);
				else if (rs > 0 && rs < 5)
					g2d.drawImage(Sprite.lightStone.getImage(), x * tileDim.width, y * tileDim.height, tileDim.width, tileDim.height, null);
				//else if (rs == 5)
					//tileMap.add(new Tile(x * tileDim.width, y * tileDim.height, false, TileID.Grass, Sprite.grass_and_stone));
				else
					g2d.drawImage(Sprite.stone.getImage(), x * tileDim.width, y * tileDim.height, tileDim.width, tileDim.height, null);
			}
		}
	}
	
	public BufferedImage getImg() {
		return this.img;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}
}
