
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


@SuppressWarnings("serial")
public class GameCourt extends JPanel {
	
	private static int score = 0;
    private Snake snake = new Snake(100,200);
    private Poison poison = new Poison(300,400);
    
	Random rand = new Random();
	int w = rand.nextInt(GameCourt.COURT_WIDTH);
	int h = rand.nextInt(GameCourt.COURT_HEIGHT);
	private Fruit fruit1 = new Fruit(w,h);

    
    public static boolean playing = false; // whether the game is running 
    private JLabel status; // Current status text, i.e. "Running..."
    
    public static final int COURT_WIDTH = 600;
    public static final int COURT_HEIGHT = 600; 
    
        
    public static final int INTERVAL = 35;
    public static final int SQUARE_VELOCITY = 4;
    
    public static String direction = "right";
    
  

    public GameCourt(JLabel status) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));


        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (playing) {
                    status.setText("Your Score is: " + GameCourt.getScore());
            	} else {
                    status.setText("Game Over!!!");
            	}
            	tick();
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        // This key listener allows the square to move as long as an arrow key is pressed, by
        // changing the square's velocity accordingly. (The tick method below actually moves the
        // square.)
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                	if (direction != "right") {
                		snake.moveLeft();
                    	GameCourt.direction = "left";
                	}    
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                	if (direction != "left") {
                    	snake.moveRight();
                    	GameCourt.direction = "right";
                	}
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                	if (direction != "up") {
                    	snake.moveDown();
                    	GameCourt.direction = "down";
                	}
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                	if (direction != "down") {
                    	snake.moveUp();
                    	GameCourt.direction = "up";	
                	}
                } 
            }
            
        });

        this.status = status;
    }

    /**
     * (Re-)set the game to its initial state.
     */
    
    
    public void reset() {
    	snake = new Snake(100,200);
    	poison = new Poison (100,300);
    	int w = rand.nextInt(GameCourt.COURT_WIDTH - GameObj.WIDTH);
    	int h = rand.nextInt(GameCourt.COURT_HEIGHT - GameObj.HEIGHT);
    	fruit1 = new Fruit (w,h);
    	GameCourt.direction = "right";
    	score = 0;
        status.setText("Your Score is: " + GameCourt.getScore());
        Game.writehighScores();
    	playing = true;

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
        if (playing) {
        	poison.move();
        	
        	poison.bounce(poison.hitWall());
        	if (snake.getLength() == 1) {
        		snake.getPiece(0).move();
        	}
        	else if (snake.getLength() != 1) {
            	if (direction == "right") {
            		snake.moveRight();
            	} else if (direction == "left") {
            		snake.moveLeft();
            	} else if (direction == "down") {
            		snake.moveDown();
            	} else {
            		snake.moveUp();
            	}

        	}
        	if (snake.wallCollision() || snake.getPiece(0).wallCollision() || 
        			snake.collisionObject(poison) || snake.selfCollision()){               
        		playing = false;
                status.setText("Your Score is: " + GameCourt.getScore());
                Game.writehighScores();
        		}
        	
        	if (snake.collisionObject(fruit1)) {
        		snake.enlongateSnake();
        		this.newFruit();
        		score += 20;
        	}        	
        }
        repaint();
    }
    
    public static String getDirection() {
    	return direction;
    }
    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		snake.draw(g);
		poison.draw(g);
		fruit1.draw(g);
    }
    
   public static int getScore() {
    	return GameCourt.score;
    }
    
    private void newFruit () {
    	int w = rand.nextInt(GameCourt.COURT_WIDTH - GameObj.WIDTH);
    	int h = rand.nextInt(GameCourt.COURT_HEIGHT - GameObj.HEIGHT);
    	fruit1.setPx(w);
    	fruit1.setPy(h);
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
    
    
    public static void setPlaying() {
    	GameCourt.playing = !GameCourt.playing;
    }
    
}