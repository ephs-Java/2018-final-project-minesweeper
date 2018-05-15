package MineSweeperClasses;

import java.awt.*;
import javax.swing.*;
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
				this.Buttons[r][c] = new GYButton(r, c, grid[r][c].getVal());
				this.window.add(this.Buttons[r][c]);
			}
		}

		setButtons(this.grid);
		this.window.pack();
		this.window.setVisible(true);
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
				current.setText("Bomb");
			} else {
				current.setText("" + current.getVal());
			}
		}
	}
	
}
