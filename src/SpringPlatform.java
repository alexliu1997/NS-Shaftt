import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class SpringPlatform extends Platform {

	public SpringPlatform() {
		Random rnd = new Random();
		imgPlatform = new ImageIcon("images\\spring_platform.png");
		width = imgPlatform.getIconWidth();
		height = imgPlatform.getIconHeight();
		xPos = rnd.nextInt(605 - width);
		yPos = 430;
		type = "spring";
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return new Rectangle(xPos, yPos, width, 5);
	}

}