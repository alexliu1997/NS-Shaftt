import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;


public class RollingPlatform extends Platform{
	private final static int EAST = 0;
	private final static int WEST = 1;
	public RollingPlatform() {
		Random rnd = new Random();
		direction = rnd.nextInt(2);
		if (direction == EAST) {
			imgPlatform = new ImageIcon("images\\rolling_platform_left.png");
		}
		else if (direction == WEST) {
			imgPlatform = new ImageIcon("images\\rolling_platform_right.png");
		}
		width = imgPlatform.getIconWidth();
		height = imgPlatform.getIconHeight();
		xPos = rnd.nextInt(605 - width);
		yPos = 430;
		type = "rolling";
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return new Rectangle(xPos, yPos, width, 5);
	}


}