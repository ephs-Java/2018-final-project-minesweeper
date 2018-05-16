package MineSweeperClasses;

import java.awt.*;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import java.awt.event.*;

public class UserInterface {
	
	GYButton[][] Buttons;
	Tile[][] grid;

	private JFrame window = new JFrame("Minesweeper");
	
	public UserInterface(Tile[][] grid){
		
		this.grid = grid;
		
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setLayout(new GridLayout(grid.length, grid[0].length));
		this.Buttons = new GYButton[grid.length][grid[0].length];
		
		for(int r = 0; r < Buttons.length; r++){
			for(int c = 0; c < Buttons[0].length; c++){
				Icon b = new ImageIcon(getClass().getResource("Unclicked.png"));
				Image image = ((ImageIcon) b).getImage(); // transform it 
				Image newimg = image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				b = new ImageIcon(newimg);
				this.Buttons[r][c] = new GYButton(r, c, grid[r][c].getVal(), b);
				this.Buttons[r][c].setPreferredSize(new Dimension(25, 25));
				this.window.add(this.Buttons[r][c]);
			}
		}
		
		setButtons(this.grid);
		this.window.pack();
		this.window.setVisible(true);
		this.window.setResizable(false);
	}
	
	ClickListener cl = new ClickListener();
	
	public void setButtons(Tile[][] tiles){
		for(int r = 0; r < Buttons.length; r++){
			for(int c = 0; c < Buttons[0].length; c++){
				Buttons[r][c].setText("");
				Buttons[r][c].addActionListener(cl);
			}
		}	
	}
	public class ClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			GYButton current = (GYButton) e.getSource();
			
			if(grid[current.getRow()][current.getCol()].isMine()){
				Icon x = new ImageIcon(getClass().getResource("bomb.png"));
				Image image = ((ImageIcon) x).getImage(); // transform it 
				Image newimg = image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				x = new ImageIcon(newimg);
				current.setIcon(x);
				current.setPreferredSize(new Dimension(25, 25));
			} else {
				Icon z = new ImageIcon(getClass().getResource("" + current.getVal() + ".png"));
				Image image = ((ImageIcon) z).getImage(); // transform it 
				Image newimg = image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				z = new ImageIcon(newimg);
				current.setIcon(z);
				current.setPreferredSize(new Dimension(25, 25));
			}
			window.pack();
		}
	}
	
}
