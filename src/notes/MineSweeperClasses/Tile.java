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
	public void setMine(){
		this.isMine = true;
	}

	public boolean isMine() {
		return isMine;
	}

	public int getVal() {
		return val;
	}
	
}
