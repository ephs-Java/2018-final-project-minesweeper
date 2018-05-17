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
		this.window.pack();
		this.window.setVisible(true);
		this.window.setResizable(false);
	}
	
	MouseListener2 ml = new MouseListener2();
	
	public void setButtons(Tile[][] tiles){
		for(int r = 0; r < Buttons.length; r++){
			for(int c = 0; c < Buttons[0].length; c++){
				Buttons[r][c].setText("");
				Buttons[r][c].addMouseListener(ml);
			}
		}	
	}
	public class ClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			GYButton current = (GYButton) e.getSource();
				
			current.setClicked();
			
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
	}
	public void react(GYButton current){
		for(int i = 0; i < tests.length; i++){
			try{
				
				GYButton test = Buttons[current.getRow() + tests[i].getX()][current.getCol() + tests[i].getY()];
				if(!test.isClicked()) {
					if(test.getVal() == 0) {
						test.setIcon(new ImageIcon(getClass().getResource("" + test.getVal() + ".png")));
						test.setClicked();
						react(test);
					} else {
						test.setClicked();
						test.setIcon(new ImageIcon(getClass().getResource("" + test.getVal() + ".png")));
					}	
				}
			} catch (IndexOutOfBoundsException e) {
				
			}
		}
	}
	public class MouseListener2 implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			
			GYButton current = (GYButton) e.getSource();
			
			current.setClicked();
			
			if(SwingUtilities.isLeftMouseButton(e)) {
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
			} else if(SwingUtilities.isRightMouseButton(e)){
				current.setIcon(new ImageIcon(getClass().getResource("Flag.png")));
			}
		}
		public void mousePressed(MouseEvent e) {
		}
		public void mouseReleased(MouseEvent e) {
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
		
	}
	
}
