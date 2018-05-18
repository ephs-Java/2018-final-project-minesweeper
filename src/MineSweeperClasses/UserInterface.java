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

	private JFrame window = new JFrame("Minesweeper");
	
	public UserInterface(Tile[][] grid, point[] tests){
		
		this.tests = tests;
		this.grid = grid;
		
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setLayout(new GridLayout(grid.length, grid[0].length));
		this.Buttons = new GYButton[grid.length][grid[0].length];
		
		for(int r = 0; r < Buttons.length; r++){
			for(int c = 0; c < Buttons[0].length; c++){
				this.Buttons[r][c] = new GYButton(r, c, grid[r][c].getVal(), new ImageIcon(getClass().getResource("Unclicked.png")));
				this.Buttons[r][c].setPreferredSize(new Dimension(25, 25));
				this.window.add(this.Buttons[r][c]);
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
						react(test);
					} else {
						test.setClicked(true);
						test.setIcon(new ImageIcon(getClass().getResource("" + test.getVal() + ".png")));
					}	
				}
			} catch (IndexOutOfBoundsException e) {
				
			}
		}
	}
	
	public void click(GYButton current){
		
	}
	public class MouseListener2 implements MouseListener {
		public void mouseClicked(MouseEvent e) {
		}
		public void mousePressed(MouseEvent e) {
			GYButton current = (GYButton) e.getSource();
			
			if(SwingUtilities.isLeftMouseButton(e) && !current.isFlagged()) {
				current.setClicked(true);
				if(grid[current.getRow()][current.getCol()].isMine()){
					current.setIcon(new ImageIcon(getClass().getResource("bomb.png")));
				} else {
					current.setIcon(new ImageIcon(getClass().getResource("" + current.getVal() + ".png")));
					if(current.getVal() == 0){
						react(current);
					}
				}
				
				current.setPreferredSize(new Dimension(25, 25));
				window.pack();
			} 
			if(SwingUtilities.isRightMouseButton(e) && !current.isClicked()){
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
