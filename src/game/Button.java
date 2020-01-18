package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Button {

	private int x, y, w, h;
	private String content;
	
	private int size = 20;
	
	public Font font = new Font("Veradana", Font.BOLD, size);
	
	private String fontName;
	
	public Color buttonColour;

	public Button(int x, int y, int w, int h, String content, Color buttonColour) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.content = content;
		this.buttonColour = buttonColour;
	}
	
	// Overloading
	public Button(int x, int y, int w, int h, String content, String font, int size, Color buttonColour) {
		this.fontName = font;
		this.font = new Font(font, Font.BOLD, size);
		this.size = size;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.content = content;
		this.buttonColour = buttonColour;
	}
	
	

	public void render(Graphics g, Color colour) {
		g.setColor(colour);
		g.fillRect(x, y, w, h);
	}

	public void render(Graphics g, int outlineSize, Color outlineColour) {
		
		g.setColor(outlineColour);
		g.fillRect(x - outlineSize, y - outlineSize, w + outlineSize, outlineSize);
		
		g.fillRect(x + w, y - outlineSize, outlineSize, h + outlineSize);
		
		g.fillRect(x - outlineSize, y + h, w + outlineSize*2, outlineSize);
		
		g.fillRect(x - outlineSize, y, outlineSize, h);
		//g.fillRect(x - outlineSize, y - outlineSize, w + outlineSize*2, h + outlineSize*2);
		
		g.setColor(buttonColour);
		g.fillRect(x, y, w, h);
		
	
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		
		font = new Font(fontName, Font.BOLD, size);
		
		FontMetrics fm = g.getFontMetrics();
	    int x = this.x + (w - fm.stringWidth(content)) / 2;
	    int y = this.y + (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
	    g.drawString(content, x, y);
	    //System.out.println(content);
	}

	public Rectangle bounds() {
		return new Rectangle(x, y, w, h);
	}
	
	public boolean intersectsAtPoint(int x, int y) {
		return bounds().contains(new Point(x, y)) ? true : false;
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

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Color getButtonColour() {
		return buttonColour;
	}

	public void setButtonColour(Color buttonColour) {
		this.buttonColour = buttonColour;
	}
}
