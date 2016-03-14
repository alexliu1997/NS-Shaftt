import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;


public class NormalPlatform extends Platform{
	public NormalPlatform() {
		Random rnd = new Random();
		imgPlatform = new ImageIcon("images\\normal_platform.png");
		width = imgPlatform.getIconWidth();
		height = imgPlatform.getIconHeight();
		xPos = rnd.nextInt(605 - width);
		yPos = 430;
		type = "normal";
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return new Rectangle(xPos, yPos, width, 5);
	}

}
