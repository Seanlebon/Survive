package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;

public class TileMap {
	
	public static final Dimension tileDim = new Dimension(32, 32);
	
	public LinkedList<Tile> tiles = new LinkedList<Tile>();
	
	public void tick() {
		for (Tile tile : tiles) {
			tile.tick();
		}
	}
	
	public void render(Graphics g) {
		for (Tile tile : tiles) {
			tile.render(g);
		}
	}
	
	public void add(Tile tile) {
		this.tiles.add(tile);
	}
	
	public void remove(Tile tile) {
		this.tiles.remove(tile);
	}
}
