package game;

import entity.Player;
import graphics.TileMap;

public class Camera {
	
	private int x, y;
	
	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(Player player) {
		x = (int) Game.clamp((int)player.getX() - Game.WIDTH / 2, 0, Game.MapDim.width / 4);
		y = (int) Game.clamp((int)player.getY() - Game.HEIGHT / 2, 0, (int) (Game.MapDim.height / 2 + (3 *TileMap.tileDim.getHeight())));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
