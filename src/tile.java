
public class tile {
	private int x;
	private int y;
	private boolean isMine = false;
	private int value = 0;
	
	public tile(int x, int y, boolean isMine, int value) {
		this.x = x;
		this.y = y;
		this.isMine = isMine;
		this.value = value;
	}
	public int getx() {
		return x;
	}
	public int gety() {
		return y;
	}
	public boolean getIsMine() {
		return isMine;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
