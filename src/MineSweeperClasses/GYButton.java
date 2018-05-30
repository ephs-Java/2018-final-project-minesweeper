package MineSweeperClasses;

import javax.swing.event.*;
import javax.swing.*;

public class GYButton extends JButton{
	 
	private int row;
	private int col;
	
	private int val;
	private boolean isClicked = false;
	private boolean isFlagged = false;
	
	private ImageIcon icon;
	private boolean isMine;
	
	public GYButton(){
	}
	public void setData(int row, int col, int val, boolean isMine){
		this.row = row;
		this.col = col;
		this.val = val;
		this.isMine = isMine;
		this.isClicked = false;
		this.isFlagged = false;
	}
	public void setImageType(){
		if(this.isMine){
			this.icon = new ImageIcon(getClass().getResource("bomb.png"));
		} else {
			this.icon = new ImageIcon(getClass().getResource("" + this.val + ".png"));
		}
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
	public ImageIcon getSavedIcon(){
		return this.icon;
	}
	public boolean isMine(){
		return this.isMine;
	}
	
	public void setImage(){
		setIcon(this.icon);
	}
}










