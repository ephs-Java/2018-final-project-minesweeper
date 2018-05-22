import java.util.Random;

public class gameGeneration {

	private tile[][] game;
	private Random r = new Random();
	
	public gameGeneration (int size) {
		this.game = newGame(size);
	}
	private tile[][] newGame(int size) {
		tile[][] game = gamePlusVals(gamePlusBombs(size));
		return game;
	}
	private tile[][] gamePlusBombs(int size) {
		tile[][] game = new tile[size][size];
		for (int x = 0; x < game.length; x++) {
			for (int y = 0; y < game[x].length; y++) {
				game[x][y] = new tile(x,y,randomBomb(),0);
			}
		}
		return game;
	}
	private tile[][] gamePlusVals(tile[][] game) {
		int count = 0;
		for (int x = 0; x < game.length; x++) {
			for (int y = 0; y < game[x].length; y++) {
				for (int i = -1; i < 2; i++) {
					for (int k = -1; k < 2; k++) {
						try {
							if (game[x + i][y + k].getIsMine() && !(i + k == 0)) {
								count++;
							}
						}
						catch (IndexOutOfBoundsException e) {
							
						}
					}
				}
				game[x][y].setValue(count);
				count = 0;
			}
		}
		return game;
	}
	private boolean randomBomb() {
		return (r.nextInt(6) == 0);
	}
	public tile[][] getGame() {
		return this.game;
	}
}