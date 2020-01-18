package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entity.Entity;
import entity.Mob;

public class MiniMap {
	
	private static int numTilesX = (Game.MapDim.width / Level.tileDim.width) + 3;
	private static int numTilesY = (Game.MapDim.height / Level.tileDim.height) + 3;
	
	private static int scalar = 2;
	
	private BufferedImage img;
	
	private int x, y;
	
	private Game game;
	
	public MiniMap(int x, int y, Game game) {
		this.x = x;
		this.y = y;
		this.game = game;
		img = new BufferedImage(numTilesX * scalar, numTilesY * scalar, BufferedImage.TYPE_INT_ARGB);
	}
	
	public void render(Graphics g) {
		Graphics g2d = img.createGraphics();
		// put color into static memory
		g2d.setColor(Color.black);
		g2d.fillRect(x, y, img.getWidth(), img.getHeight());
		g2d.setColor(Color.WHITE);
		g2d.drawRect(x + (game.camera.getX() / Level.tileDim.width * scalar), y + (game.camera.getY() / Level.tileDim.height * scalar),
				(Game.WIDTH / Level.tileDim.width) * scalar, (Game.HEIGHT / Level.tileDim.width) * scalar);
		
		g2d.setColor(Color.GREEN);
		// Should be divided by player dims
		g2d.fillRect((int)(x + (game.player.x / Level.tileDim.width * scalar)), (int)(y + (game.player.y / Level.tileDim.height * scalar)),
				2 * scalar, 2 * scalar);
		
		g2d.setColor(Color.RED);
		for (int i = 0; i < game.handler.obj.size(); i++) {
			Entity temp = game.handler.obj.get(i);
			if (temp instanceof Mob)
				g2d.fillRect((int)(x + (temp.x / Level.tileDim.width * scalar)), (int)(y + (temp.y / Level.tileDim.height * scalar)), 
						2 * scalar, 2 * scalar);
		}
		
		g.drawImage(img, x, y, null);
		g2d.dispose();
	}

}
