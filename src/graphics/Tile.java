package graphics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tile {
	
	public int x, y;
	public boolean isSolid;
	
	public BufferedImage img;
	
	public Tile(int x, int y, boolean isSolid, BufferedImage img) {
		this.x = x;
		this.y = y;
		this.isSolid = isSolid;
		this.img = img;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, img.getWidth(), img.getHeight());
	}
	
	public boolean isSolid() {
		return isSolid;
	}

}
