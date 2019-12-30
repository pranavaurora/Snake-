/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
	
	static Map <Integer, String> scoreMap = new TreeMap<Integer, String>();
	static int[] highestScore = new int[3];
	static BufferedReader br;
	static BufferedWriter wr;

	
    public void run() {
        // Top-level frame in which game components live
    	    	
    	final JFrame frame = new JFrame("Snake");
        frame.setLocation(600, 600);
        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Status");
        status_panel.add(status);
        
        // Main playing area
        final GameCourt court = new GameCourt(status);
        frame.add(court, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

		final JButton instructions = new JButton("Instructions");
		instructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	final JFrame frame1 = new JFrame("Instructions");
		        frame1.setLocation(200, 200);
		        String instructions = "Instructions: Use Arrow Keys to control snake. Eat apples, "
		        		+ "set a high score, avoid the poison";
		        JOptionPane.showMessageDialog(frame1, instructions, "Snake.", JOptionPane.OK_OPTION);
		        frame1.setVisible(true);
		        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame1.dispose();
			}
		});
		
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }    
        
        });
        
        JLabel label = new JLabel(Game.makeJLabel());

        
        
        status_panel.add(reset);
        status_panel.add(instructions);
        control_panel.add(label);

        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
 
        
        court.reset();
        
        
    }
    
    private static void readhighScores() {
    	try {
			br = new BufferedReader(new FileReader("pics/scores.txt"));
			String next = br.readLine();
			while (next != null) {
				String[] splited = next.split(":");
				int result = Integer.parseInt(splited[0]);	
				scoreMap.put(result, splited[1]);
				next = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}    	
    }
    
    private static int[] getHighScores() {
    	Game.readhighScores();
    	SortedSet<Integer> keys = new TreeSet<>(scoreMap.keySet());
    	highestScore[0] = keys.last();
    	keys.remove(keys.last());
    	highestScore[1] = keys.last();
    	keys.remove(keys.last());
    	highestScore[2] = keys.last();
    	keys.remove(keys.last());


//    	int highest1 = 1600;
//    	int highest2 = 700;
//    	int highest3 = 540;
//    	for (Integer key: scoreMap.keySet()) {
//    		if (key > highest3) {
//    			if (key < highest2) {
//    				highest3 = key;
//    			}
//    			else if (key < highest1) {
//    				highest2 = key;
//    			}
//    			else {
//    				highest1 = key;
//    			}
//    		}
//    		
//    	}
//    	highestScore[0] = highest1;
//    	highestScore[1] = highest2;
//    	highestScore[2] = highest3;
    	return highestScore;
    }
    
 
    public static void writehighScores() {
       	try {
    			wr = new BufferedWriter(new FileWriter("pics/scores.txt", true));
    			long millis=System.currentTimeMillis();  
    			java.sql.Date date = new java.sql.Date(millis);  
    			String x = Integer.toString(GameCourt.getScore());
    			wr.write(x + ": " + date);
    			wr.newLine();
    			wr.close();
    		} catch (IOException e) {
    			throw new IllegalArgumentException();
    			}
    }
    
    public static String makeJLabel() {
    	int[] x = Game.getHighScores();
    	String date1 = scoreMap.get(x[0]);
    	String date2 = scoreMap.get(x[1]);
    	String date3 = scoreMap.get(x[2]);
    	String line1 = "1) " + x[0] + " on " +  date1 + ".";
    	String line2 = "2) " + x[1] + " on " + date2 + ".";
    	String line3 = "3) " + x[2] + " on " + date3 + ".";

    	return "High Scores: " + line1 + "\n" + line2 + "\n" + line3;
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}