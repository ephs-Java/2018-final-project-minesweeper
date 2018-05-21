package MineSweeperClasses;

import java.awt.*;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import java.awt.event.*;

public class UserInterface {
	
	GYButton[][] Buttons;
	Tile[][] grid;
	point[] tests;
	MouseListener2 ml = new MouseListener2();

	private boolean gameover = false;
	private JFrame window = new JFrame("Minesweeper");
	private JPanel mainPanel = new JPanel();
	private JPanel gamePanel = new JPanel();
	private JPanel menuBarPanel = new JPanel();
	private int money = 0;
	private JLabel moneyLabel = new JLabel("$" + money);
	private int reactCount = 0;
	
	public UserInterface(Tile[][] grid, point[] tests){
		
		this.tests = tests;
		this.grid = grid;
		
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.Buttons = new GYButton[grid.length][grid[0].length];
		
		menuBarPanel.setPreferredSize(new Dimension(grid.length * 1, 30));
		gamePanel.setPreferredSize(new Dimension(grid.length * 25, grid[0].length * 25));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		window.add(mainPanel);
		mainPanel.add(menuBarPanel);
		mainPanel.add(gamePanel);
		menuBarPanel.add(moneyLabel);
		
		gamePanel.setLayout(new GridLayout(grid.length, grid[0].length));
		
		for(int r = 0; r < Buttons.length; r++){
			for(int c = 0; c < Buttons[0].length; c++){
				this.Buttons[r][c] = new GYButton(r, c, grid[r][c].getVal(), grid[r][c].isMine());
				this.Buttons[r][c].setPreferredSize(new Dimension(25, 25));
				this.Buttons[r][c].setIcon(new ImageIcon(getClass().getResource("Unclicked.png")));
				this.gamePanel.add(this.Buttons[r][c]);
			}
		}
		
		setButtons(this.grid);
		this.window.setVisible(true);
		this.window.setResizable(false);
		this.window.pack();
	}
	
	public void setButtons(Tile[][] tiles){
		for(int r = 0; r < Buttons.length; r++){
			for(int c = 0; c < Buttons[0].length; c++){
				Buttons[r][c].setText("");
				Buttons[r][c].addMouseListener(ml);
				this.window.pack();
			}
		}	
	}
	
	public void react(GYButton current){
		for(int i = 0; i < tests.length; i++){
			try{
				
				GYButton test = Buttons[current.getRow() + tests[i].getX()][current.getCol() + tests[i].getY()];
				if(!test.isClicked()) {
					if(test.getVal() == 0) {
						test.setIcon(new ImageIcon(getClass().getResource("" + test.getVal() + ".png")));
						test.setClicked(true);
						reactCount = reactCount + 1;
						react(test);
					} else {
						test.setClicked(true);
						reactCount = reactCount + 1;
						test.setIcon(new ImageIcon(getClass().getResource("" + test.getVal() + ".png")));
					}	
				}
			} catch (IndexOutOfBoundsException e) {
				
			}
		}
	}
	
	public void end(){
		moneyLabel.setText("Victory Royale");
		gameover = true;
		for(int r = 0; r < Buttons.length; r++){
			for(int c = 0; c < Buttons[0].length; c++){
				
				GYButton current = Buttons[r][c];
				if (current.isMine() && !current.isFlagged()) {
					current.setIcon(new ImageIcon(getClass().getResource("bombMissed.png")));
				} else if (current.isFlagged() && !current.isMine()) {
					current.setIcon(new ImageIcon(getClass().getResource("FlagError.png")));
				}
			}
		}
	}
	
	public class MouseListener2 implements MouseListener {
		public void mouseClicked(MouseEvent e) {
		}
		public void mousePressed(MouseEvent e) {
			GYButton current = (GYButton) e.getSource();

			if (gameover || current.isClicked()) {
				
			} else if (SwingUtilities.isLeftMouseButton(e) && !current.isFlagged()) {
				
				current.setClicked(true);
				current.setImage();
				if(current.getVal() == 0 && !current.isMine()){
					react(current);
					money = money + reactCount;
					reactCount = 0;
				}
				current.setPreferredSize(new Dimension(25, 25));
				window.pack();
				if(current.isMine()){
					current.setClicked(false);
					end();
				} else {
					money = money + 1;
					moneyLabel.setText("$" + money);
				}
			} else if(SwingUtilities.isRightMouseButton(e) && !current.isClicked()){
				if(!current.isFlagged()){
					current.setIsFlagged(true);
					current.setIcon(new ImageIcon(getClass().getResource("Flag.png")));
				} else {
					current.setIsFlagged(false);
					current.setIcon(new ImageIcon(getClass().getResource("Unclicked.png")));
				}
			}
		}
		public void mouseReleased(MouseEvent e) {
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
		
	}
	
}
