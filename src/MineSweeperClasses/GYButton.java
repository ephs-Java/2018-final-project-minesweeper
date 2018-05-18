package MineSweeperClasses;

import javax.swing.event.*;
import javax.swing.*;

public class GYButton extends JButton{
	 
	private int row;
	private int col;
	
	private int val;
	private boolean isClicked = false;
	private boolean isFlagged = false;
	
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
	public boolean isClicked(){
		return this.isClicked;
	}
	public void setClicked(boolean b){
		this.isClicked = b;
	}
	public boolean isFlagged(){
		return this.isFlagged;
	}
	public void setIsFlagged(boolean b){
		this.isFlagged = b;
	}
}
