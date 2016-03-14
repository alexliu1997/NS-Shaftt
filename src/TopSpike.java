import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class TopSpike {
	ImageIcon imgSpike;
	int width, height;
	public TopSpike() {
		imgSpike = new ImageIcon("images\\top_spike.png");
		width = imgSpike.getIconWidth();
		height = imgSpike.getIconHeight();
	}
	public void draw(Graphics2D g2) {
		g2.drawImage(imgSpike.getImage(), 0, 0, null);
	}
	public Rectangle getRect() {
		return new Rectangle(0,0,width,height);
	}
}
