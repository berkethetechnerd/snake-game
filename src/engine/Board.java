package engine;

import java.awt.Color;

import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JFrame;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Board {
	
	public int width;
	public int heigth;
	
	public JFrame frame = new JFrame ();
	public GCanvas canvas = new GCanvas ();
	public GRect[][] rects = new GRect[10][10];
	public GRect[] edges = new GRect[4];
	public GLabel score = new GLabel ("");
	
	public Board (int width, int heigth) {
		this.width = width;
		this.heigth = heigth;
		
		frame.getContentPane().add(canvas);
		frame.setSize(width+100, heigth+100);
		frame.setVisible(true);
		
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				rects[i][j] = new GRect(5+80*j, 5+80*i , 80, 80);
				rects[i][j].setFilled(true);
				rects[i][j].setColor(Color.WHITE);
				canvas.add(rects[i][j]);
			}
		}
		
		edges[0] = new GRect(5, 5, 800, 2);
		edges[1] = new GRect(5, 805, 800, 2);
		edges[2] = new GRect(5, 5, 2, 800);
		edges[3] = new GRect(805, 5, 2, 800);
		
		for (int i=0; i<4; i++) {
			edges[i].setFilled(true);
			edges[i].setColor(Color.BLACK);
			canvas.add(edges[i]);
		}
		
		score.setLocation(10, 835);
		score.setFont("SansSerif-bold-25");
		score.setVisible(true);
		canvas.add(score);
		
	}
	
	public Board (int width, int heigth, int score) {
		this.width = width;
		this.heigth = heigth;
		
		frame.getContentPane().add(canvas);
		frame.setSize(width+100, heigth+100);
		frame.setVisible(true);
		
		edges[0] = new GRect(5, 5, 800, 2);
		edges[1] = new GRect(5, 805, 800, 2);
		edges[2] = new GRect(5, 5, 2, 800);
		edges[3] = new GRect(805, 5, 2, 800);
		
		for (int i=0; i<4; i++) {
			edges[i].setFilled(true);
			edges[i].setColor(Color.BLACK);
			canvas.add(edges[i]);
		}
		
		GLabel gameOver = new GLabel ("Game OVER");
		gameOver.setFont("SansSerif-bold-100");
		gameOver.setLocation(100, 200);
		gameOver.setVisible(true);
		gameOver.setColor(Color.RED);
		canvas.add(gameOver);
		
		this.score.setLabel("FINAL SCORE: " + score);
		this.score.setLocation(80, 400);
		this.score.setFont("SansSerif-bold-80");
		this.score.setVisible(true);
		this.score.setColor(Color.RED);
		canvas.add(this.score);	
	}
	
	public void updateScore (int score) {
		this.score.setLabel("Your score: " + score);
	}
	
	public void updateCanvas(LinkedList<Node> snake) {
		ListIterator<Node> iterator = snake.listIterator();
		
		while (iterator.hasNext()) {
			Node temp = iterator.next();
			rects[temp.coordinateX][temp.coordinateY].setColor(Color.GREEN);
		}
		rects[snake.getLast().coordinateX][snake.getLast().coordinateY].setColor(Color.ORANGE);
	}
}

// END OF CLASS BOARD