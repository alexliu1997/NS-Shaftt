import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.*;
public class Player {
	private ImageIcon imgPlayerEast, imgPlayerWest, imgInjuredEast, imgInjuredWest;
	private int xPos, yPos, height, width, direction;
	public static final int EAST = 1, WEST = 0;
	public boolean isInjured;
	public Player() {
		direction = EAST;
		imgPlayerEast = new ImageIcon("images\\caveman" + EAST + ".png");
		imgPlayerWest = new ImageIcon("images\\caveman" + WEST + ".png");
		imgInjuredEast = new ImageIcon("images\\caveman" + EAST + "_injured.png");
		imgInjuredWest = new ImageIcon("images\\caveman" + WEST + "_injured.png");
		width = imgPlayerEast.getIconWidth();
		height = imgPlayerEast.getIconHeight();
		xPos = 310 - width/2;
		yPos = 205 - height/2;
		isInjured = false;
	}
	public void draw(Graphics2D g2) {
		if (!isInjured) {
			if (direction == EAST) {
				g2.drawImage(imgPlayerEast.getImage(), xPos, yPos, null);
			}
			else if (direction == WEST){
				g2.drawImage(imgPlayerWest.getImage(), xPos, yPos, null);
			}
		}
		else {
			if (direction == EAST) {
				g2.drawImage(imgInjuredEast.getImage(), xPos, yPos, null);
			}
			else if (direction == WEST){
				g2.drawImage(imgInjuredWest.getImage(), xPos, yPos, null);
			}
		}
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
	public int getDirection() {
		return direction;
	}
	public void move() {
		yPos++;
	}
	public void moveOpposite() {
		yPos--;
	}
	public void setLocation(int x, int y) {
		xPos = x;
		yPos = y;
	}
	public void setDirection(int dir) {
		direction = dir;
	}
	public void setX(int x) {
		xPos = x;
	}
	public void setY(int y) {
		yPos = y;
	}
	// bottom rectangle checks collision with platforms
	public Rectangle getRectBottom() {
		Rectangle r = new Rectangle(xPos + 20, yPos + height - 2, 10, 1);
		return r;
	}
	// top rectangle checks collision with spikes at the top
	public Rectangle getRectTop() {
		Rectangle r = new Rectangle(xPos + 2, yPos, 10, 1);
		return r;
	}
	public void injured() {
		isInjured = true;
	}
	public void notInjured() {
		isInjured = false;
	}
	
}
