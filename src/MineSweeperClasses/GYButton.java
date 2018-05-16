package MineSweeperClasses;

import javax.swing.event.*;
import javax.swing.*;

public class GYButton extends JButton{
	 
	private int row;
	private int col;
	
	private int val;
	
	public GYButton(int row, int col, int val, Icon x){
		super(x);
		this.row = row;
		this.col = col;
		this.val = val;
	}
	
	public int getRow(){
		return this.row;
	}
	public int getCol(){
		return this.col;
	}
	public int getVal(){
		return this.val;
	}
}
