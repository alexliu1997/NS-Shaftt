import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.*;
public abstract class Platform {
	ImageIcon imgPlatform;
	int xPos, yPos, width, height, direction;
	String type;
	public void draw(Graphics2D g2) {
		g2.drawImage(imgPlatform.getImage(), xPos, yPos, null);
	}
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}
	public void setLocation(int x, int y) {
		xPos = x;
		yPos = y;
	}

	public void setX(int x) {
		xPos = x;
	}

	public void setY(int y) {
		yPos = y;
	}
	public abstract Rectangle getRect();
	public void move() {
		yPos--;
	}
	public String getType() {
		return type;
	}
	public int getDirection() {
		return direction;
	}
}
