import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;


public class Snake implements GameItem {
	
	List<SnakeUnit> totalSnake = new LinkedList<SnakeUnit> ();

		

	public Snake (int x, int y) {
		for (int i = 0; i < 4; i ++) {
			SnakeUnit Piece1 = new SnakeUnit (x + (22 * i), y);
			totalSnake.add(Piece1);	
			}
	}
	
//	private int getVx() {
//		return getPiece(0).getVx();
//	}
//	private int getVy() {
//		return getPiece(0).getVy();
//	}
	
	public int getX () {
		SnakeUnit one = this.getPiece(0);
		return one.getX();
	}
	
	public int getY () {
		SnakeUnit one = this.getPiece(0);
		return one.getY();
	}
	
	public int getWidth () {
		return this.getLength()*22;
	}
	public int getHeight () {
		return GameObj.HEIGHT;
	}
		
	public int getLength() {
		return totalSnake.size();
	}
	
	public SnakeUnit getPiece(int x) {
		return totalSnake.get(x);
	}
	
	public void moveLeft () {
		SnakeUnit head = totalSnake.get(0);
		SnakeUnit newHead = new SnakeUnit((int) (head.getX())-head.vx, head.getY());
		totalSnake.add(0, newHead);
		totalSnake.remove(totalSnake.size()-1);
	}
	
	public void moveRight() {
		SnakeUnit head = totalSnake.get(0);
		SnakeUnit newHead = new SnakeUnit((int) (head.getX()+ head.vx), head.getY());
		totalSnake.add(0, newHead);
		totalSnake.remove(totalSnake.size()-1);
	}
	
	public void moveUp() {
		SnakeUnit head = totalSnake.get(0);
		SnakeUnit newHead = new SnakeUnit(head.getX(), (int) (head.getY() - head.vy));
		totalSnake.add(0, newHead);
		totalSnake.remove(totalSnake.size()-1);
	}
	
	public void moveDown() {
		SnakeUnit head = totalSnake.get(0);
		SnakeUnit newHead = new SnakeUnit(head.getX(), (int) (head.getY()+ head.vy));
		totalSnake.add(0, newHead);
		totalSnake.remove(totalSnake.size()-1);
	}	
	
	
	public void draw (Graphics g) {
		for (SnakeUnit p: totalSnake) {
			p.draw(g);
		}
	}
	
	public boolean wallCollision () {
		for (SnakeUnit p: totalSnake) {
			if (p.wallCollision()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean collisionObject(GameObj that) {
		for (SnakeUnit p: totalSnake) {
			if (p.collisionObject(that)) {
				return true;
			}
		}
		return false;
	}
	
	public void enlongateSnake() {
		SnakeUnit s = this.getPiece(this.getLength()-1);
		int px = s.getX();
		int py = s.getY();
		SnakeUnit new1 = new SnakeUnit(px,py);
		totalSnake.add(new1);
	}
	
	public List<SnakeUnit> total (){
		return totalSnake;
	}
	
	public boolean selfCollision () {
		java.util.Iterator<SnakeUnit> iter = totalSnake.iterator();
		SnakeUnit next = iter.next();
		while(iter.hasNext())
		{			
			if(iter.next().exactSame(next)) {
				return true;
			}
		}	
		return false;
	}
}
