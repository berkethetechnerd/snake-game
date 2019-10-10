package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GameOverScreen {

	public Board theEnd;
	public JButton newGame;
	public JButton quit;

	public GameOverScreen (int score) {
		theEnd = new Board (800, 800, score);
		newGame = new JButton();
		quit = new JButton();

		newGame.setText("New Game");
		newGame.setBounds(100, 600, 200, 80);
		newGame.setVisible(true);
		theEnd.canvas.add(newGame);

		quit.setText("Quit");
		quit.setBounds(450, 600, 200, 80);
		quit.setVisible(true);
		theEnd.canvas.add(quit);

		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				theEnd.frame.dispose();
				new Game();
			}
		});

		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				killTheScreen();
			}
		});
	}
	
	public void killTheScreen () {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}

//	END OF CLASS GAME OVER SCREEN
