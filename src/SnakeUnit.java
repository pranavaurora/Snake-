import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SnakeUnit extends GameObj {
	
	
	public static final String IMG_FILE = "pics/unit.png";

	private static BufferedImage img;
	
	public SnakeUnit (int px, int py) {
		super (px, py);
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
	}
	
    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
    }
    
    @Override
    public void move() {
    	if (GameCourt.getDirection() == "left") {
    		int x = this.getX() - this.getVx();
    		this.setPx(x);
    	} else if (GameCourt.getDirection() == "right") {
    		int x = this.getX() + this.getVx();
    		this.setPx(x);
    	} else if (GameCourt.getDirection() == "up") {
    		int y = this.getY() - this.getVy();
    		this.setPy(y);
    	} else if (GameCourt.getDirection() == "down") {
    		int y = this.getY() + this.getVy();
    		this.setPy(y);
    	}
    }

    
}
