import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
	mineButton[][] Buttons;
	tile[][] gameinfo;
	
	private JFrame window = new JFrame("Minesweeper");
	private JPanel mainPanel = new JPanel();
	private JPanel gamePanel = new JPanel();
	private JPanel menuBarPanel = new JPanel();
	
	public GUI (gameGeneration game) {
		
		this.gameinfo = game.getGame();
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.Buttons = new mineButton[gameinfo.length][gameinfo.length];
		
		menuBarPanel.setPreferredSize(new Dimension(gameinfo.length * 1, 30));
		gamePanel.setPreferredSize(new Dimension(gameinfo.length * 25, gameinfo[0].length * 25));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		gamePanel.setLayout(new GridLayout(gameinfo.length, gameinfo[0].length));
		
		window.add(mainPanel);
		mainPanel.add(menuBarPanel);
		mainPanel.add(gamePanel);
		
		gamePanel.setLayout(new GridLayout(gameinfo.length, gameinfo[0].length));
		
		for(int r = 0; r < Buttons.length; r++){
			for(int c = 0; c < Buttons[0].length; c++){
				this.Buttons[r][c] = new mineButton(gameinfo[r][c]);
				this.Buttons[r][c].setPreferredSize(new Dimension(25, 25));
				this.Buttons[r][c].setIcon(new ImageIcon(getClass().getResource("Unclicked.png")));
				this.gamePanel.add(this.Buttons[r][c]);
			}
		}
		
		setButtons(gameinfo);
		this.window.setVisible(true);
		this.window.setResizable(false);
		this.window.pack();
		
		
	}
	public void setButtons(tile[][] tiles){
		for(int r = 0; r < Buttons.length; r++){
			for(int c = 0; c < Buttons[0].length; c++){
				Buttons[r][c].setText("");
				this.window.pack();
			}
		}	
	}

}
