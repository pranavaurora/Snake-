import java.awt.Graphics;


public abstract class GameObj implements GameItem {

	public int px;
	public int py;
	public int vx = 10; 
	public int vy = 10;
	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	
	//update the value of maxX and maxY once gamecourt is defined.
	public static final int maxX = GameCourt.COURT_WIDTH - GameObj.WIDTH;
	public static final int maxY = GameCourt.COURT_HEIGHT  - GameObj.HEIGHT;

	public GameObj (int px, int py) {
		this.px = px;
		this.py = py;
	}

	public abstract void draw (Graphics g);
	
	
	public boolean wallCollision () {
		if (this.px - this.vx < 0) {
			return true;
		} else if (this.px + this.vx > maxX) {
			return true;	
		} else if (this.py + this.vy > maxY) {
			return true;	
		} else if (this.py - this.vy < 0) {
			return true;	
		} else {
			return false;
		}
	}
	
	public abstract void move();
	
	public boolean exactSame (GameObj that) {
		if (this.getX() == that.getX() && this.getY() == that.getY()) {
			return true;
		} else {
			return false;
		}

	}
	
    public boolean collisionObject(GameObj that) {
        return (this.px + GameObj.WIDTH >= that.px
            && this.py + GameObj.WIDTH >= that.py
            && that.px + GameObj.WIDTH >= this.px 
            && that.py + GameObj.WIDTH >= this.py);
    }
    
 	public int getX() {
		return this.px;
	}

	public int getY() {
		return this.py;
	}
	public int getWidth () {
		return GameObj.WIDTH;
	}
	public int getHeight () {
		return GameObj.HEIGHT;
	}
	public int getVx() {
		return this.vx;
	}
	public int getVy() {
		return this.vy;
	}
	
	public void setPx(int px) {
		if (px > GameCourt.COURT_WIDTH - GameObj.WIDTH) {
			this.px = GameCourt.COURT_WIDTH - GameObj.WIDTH;
		} else if (px < 0) {
			this.px = 0;
		} else {
		    this.px = px;
		}
	}

	public void setPy(int py){
		if (py > GameCourt.COURT_HEIGHT - GameObj.HEIGHT) {
			this.py = GameCourt.COURT_HEIGHT - GameObj.HEIGHT;
		} else if (py < 0) {
			this.py = 0;
		} else {
		    this.py = py;
		}
	}
	
	public void setVx(int vx) {
		this.vx = vx;
	}

	public void setVy(int vy){
		this.vy = vy;
	}
}
