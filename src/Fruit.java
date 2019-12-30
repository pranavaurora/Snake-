import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Fruit extends GameObj {
		
	public static final String IMG_FILE = "pics/apple.png";

	private static BufferedImage img;
	
	public Fruit (int px, int py) {
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
    	int x = this.getX() + this.getVx();
    	int y = this.getY() + this.getVy();
    	this.setPx(x);
    	this.setPy(y);
    }   
}   