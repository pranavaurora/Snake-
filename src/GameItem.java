import java.awt.Graphics;

public interface GameItem {
	public boolean wallCollision ();
	public void draw (Graphics g);
	public boolean collisionObject(GameObj that);
	public int getX ();
	public int getY ();
	public int getWidth ();
	public int getHeight ();
}
