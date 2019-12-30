import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Poison extends GameObj {
		
	public static final String IMG_FILE = "pics/poison1.png";

	private static BufferedImage img;
	
	public Poison (int px, int py) {
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
    	int x = (int) (this.getX() + 0.2 * this.getVx());
    	int y = (int) (this.getY() + 0.1 * this.getVy());
    	this.setPx(x);
    	this.setPy(y);
    }   
    
 
    public void bounce(Direction d) {
        if (d == null) return; 
        switch (d) {
        case UP:
            this.vy = Math.abs(this.vy);
            break;  
        case DOWN:
            this.vy = -Math.abs(this.vy);
            break;
        case LEFT:
            this.vx = Math.abs(this.vx);
            break;
        case RIGHT:
            this.vx = -Math.abs(this.vx);
            break;
        }
    }
    
    public Direction hitWall() {
        if (this.px + this.vx < 0) {
            return Direction.LEFT;
        } else if (this.px + this.vx > GameObj.maxX) {
           return Direction.RIGHT;
        }

        if (this.py + this.vy < 0) {
            return Direction.UP;
        } else if (this.py + this.vy > GameObj.maxY) {
            return Direction.DOWN;
        } else {
            return null;
        }
    }    
}
