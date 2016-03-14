import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;


public class TempPlatform extends Platform{
	Rectangle r;
	public TempPlatform() {
		Random rnd = new Random();
		imgPlatform = new ImageIcon("images\\temp_platform.png");
		width = imgPlatform.getIconWidth();
		height = imgPlatform.getIconHeight();
		xPos = rnd.nextInt(605 - width);
		yPos = 430;
		type = "temp";
		r = new Rectangle(xPos, yPos, width, 5);
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return new Rectangle(xPos, yPos, width, 5);
	}

}