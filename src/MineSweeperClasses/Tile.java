package MineSweeperClasses;

public class Tile {
	
	private boolean isMine;
	private int val;
	
	public Tile(int x){
		if(x == 0){
			this.isMine = true;
		}
	}
	
	public void setVal(int x){
		this.val = x;
	}
	public void setMine(boolean b){
		this.isMine = b;
	}

	public boolean isMine() {
		return isMine;
	}

	public int getVal() {
		return val;
	}
	
}
