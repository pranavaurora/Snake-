import org.junit.Test;
import static org.junit.Assert.*;

public class Tests {
	private Snake snake = new Snake(100,200);
	SnakeUnit unit1 = new SnakeUnit(100,200);
	private Poison poison = new Poison(100,200);
	private Fruit apple = new Fruit(105,205);

	
	@Test
	public void newSnake () {
		assertEquals(4, snake.getLength());
		assertEquals(unit1.getX(), snake.getPiece(0).getX());
		assertEquals(unit1.getY(), snake.getPiece(0).getY());
		assertEquals(unit1.getY(), snake.getPiece(1).getY());
		assertEquals(unit1.getY(), snake.getPiece(2).getY());
		assertEquals(unit1.getY(), snake.getPiece(3).getY());
		assertEquals(unit1.getX() + 22, snake.getPiece(1).getX());
	}
	
	@Test
	public void originaldirection () {
		assertEquals("right", GameCourt.getDirection());		
	}
	
	@Test
	public void snakeoutofBoundsX () {
		SnakeUnit one = snake.getPiece(0);
		one.setPx(-100);
		assertEquals(0, one.getX());
		one.setPx(1000);
		assertEquals(GameCourt.COURT_WIDTH - GameObj.WIDTH, one.getX());
	}
	
	@Test
	public void snakeoutofBoundsY () {
		SnakeUnit one = snake.getPiece(0);
		one.setPy(-100);
		assertEquals(0, one.getY());
		one.setPy(1000);
		assertEquals(GameCourt.COURT_HEIGHT - GameObj.HEIGHT, one.getY());
	}
	
	@Test
	public void moveRight () {
		snake.moveRight();
		assertEquals(200, snake.getPiece(0).getY());	
		assertEquals(110, snake.getPiece(0).getX());
		assertEquals(200, snake.getPiece(1).getY());
		assertEquals(100, snake.getPiece(1).getX());	
		assertEquals(200, snake.getPiece(2).getY());	
		assertEquals(122, snake.getPiece(2).getX());	
		assertEquals(144, snake.getPiece(3).getX());	
	}
	
	@Test
	public void moveLeft () {
		snake.moveLeft();
		assertEquals(200, snake.getPiece(0).getY());	
		assertEquals(90, snake.getPiece(0).getX());
		assertEquals(200, snake.getPiece(1).getY());	
		assertEquals(100, snake.getPiece(1).getX());	
		assertEquals(122, snake.getPiece(2).getX());
		assertEquals(200, snake.getPiece(2).getY());	
		assertEquals(144, snake.getPiece(3).getX());	
	}
	
	@Test
	public void moveUp () {
		snake.moveUp();
		assertEquals(190, snake.getPiece(0).getY());	
		assertEquals(100, snake.getPiece(0).getX());
		assertEquals(200, snake.getPiece(1).getY());	
		assertEquals(100, snake.getPiece(1).getX());	
		assertEquals(122, snake.getPiece(2).getX());
		assertEquals(200, snake.getPiece(2).getY());	
		assertEquals(144, snake.getPiece(3).getX());	
	}
	
	@Test
	public void moveDown () {
		snake.moveDown();
		assertEquals(210, snake.getPiece(0).getY());	
		assertEquals(100, snake.getPiece(0).getX());
		assertEquals(200, snake.getPiece(1).getY());	
		assertEquals(100, snake.getPiece(1).getX());	
		assertEquals(122, snake.getPiece(2).getX());
		assertEquals(200, snake.getPiece(2).getY());	
		assertEquals(144, snake.getPiece(3).getX());	
	}
	
	@Test
	public void doesNotChangeDirection () {
		assertEquals("right", GameCourt.getDirection());
		snake.moveRight();
		assertEquals("right", GameCourt.getDirection());
	}
	
	@Test
	public void snakeIncreaseLength () {
		snake.enlongateSnake();
		assertEquals(5, snake.getLength());
	}
	
	@Test
	public void collisionPoison() {
		assertEquals(true, (snake.collisionObject(poison)));
		}
	
	@Test
	public void collisionApple() {
		assertEquals(true, (snake.collisionObject(apple)));
		}
}
	
