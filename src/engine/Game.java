package engine;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.LinkedList;
import java.util.Random;

public class Game {

	public final int WAIT_BEFORE_CLOSE = 15000;
	public final int BOARD_WIDTH = 800;
	public final int BOARD_HEIGTH = 800;

	public Board gameBoard;
	public LinkedList<Node> snake;
	public Node bait;
	public Random rand;

	public int score;
	public String direction;
	public boolean generatedBait;

	public Game () {
		this.gameBoard = new Board(BOARD_WIDTH, BOARD_HEIGTH);
		this.snake = new LinkedList<Node>();
		this.rand = new Random();

		for (int i=0; i<3; i++) {
			Node newNode = new Node(4,2+i);
			snake.add(newNode);
		}

		this.score = snake.size();
		this.generatedBait = false;

		gameBoard.frame.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				Node head = snake.getLast();
				int X = head.coordinateX;
				int Y = head.coordinateY;

				int key = e.getKeyCode();

				if (key == KeyEvent.VK_UP)
					doMove(X-1, Y);
				else if (key == KeyEvent.VK_LEFT) 
					doMove(X, Y-1);
				else if (key == KeyEvent.VK_DOWN) 
					doMove(X+1, Y);
				else if (key == KeyEvent.VK_RIGHT) 
					doMove(X, Y+1);

				doUpdate();
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		

		gameBoard.updateScore(score);
		gameBoard.updateCanvas(snake);
		generateBait(rand);

	}

	public void doMove(int X, int Y) {
		if (!checkGameOver(X, Y) && !checkSelfCross(X, Y)) {
			Node newHead = new Node(X, Y);
			snake.add(newHead);
			checkBait(X, Y);
		}
		else {
			gameBoard.frame.dispose();
			new GameOverScreen (snake.size());
		}
	}
	
	public void doUpdate () {
		gameBoard.updateScore(snake.size());
		gameBoard.updateCanvas(snake);
	}
	
	public boolean checkSelfCross(int X, int Y) {
		if (gameBoard.rects[X][Y].getColor() == Color.GREEN) {
			return true;
		}
		else {
			return false;
		}
	}


	public boolean checkGameOver (int X, int Y) {
		if (X>=0 && X<=9 && Y>=0 && Y<=9) {
			return false;
		}
		else {
			return true;
		}
	}

	public void checkBait (int X, int Y) {
		if (bait.coordinateX == X && bait.coordinateY == Y) {
			generatedBait=false;
			generateBait(rand);
		}

		else {
			Node deleted = snake.removeFirst();
			gameBoard.rects[deleted.coordinateX][deleted.coordinateY].setColor(Color.WHITE);
		}
	}

	public void generateBait (Random rand) {
		while (!generatedBait) {
			int X = rand.nextInt(10);
			int Y = rand.nextInt(10);

			if (gameBoard.rects[X][Y].getColor() == Color.WHITE) {
				bait = new Node(X,Y);
				gameBoard.rects[X][Y].setColor(Color.BLUE);
				generatedBait = true;
			}
		}
	}
}

// END OF CLASS GAME