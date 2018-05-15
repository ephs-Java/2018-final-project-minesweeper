package MineSweeperClasses;

import java.util.Random;

public class GenerateTiles {
	
	Tile[][] tiles;
	
	public GenerateTiles(String difficulty){
		
		Random r = new Random();
		
		if(difficulty.equals("easy")){
			tiles = new Tile[7][7];
		} else if(difficulty.equals("medium")){
			tiles = new Tile[13][13];
		} else {
			tiles = new Tile[20][20];
		}
		
		//this for loop generates the mines and assigns each tile in the 2D array to a new mine
		
		for(int row = 0; row < tiles.length; row++){
			for(int col = 0; col < tiles.length; col++){
				tiles[row][col] = new Tile(r.nextInt(5));
			}
		}
		
		genTileVal();
		
		UserInterface UI = new UserInterface(tiles);
		
	}
	
	//This method generates the value for each tile in the 2D array of tiles
	
	public void genTileVal(){
		
		point[] tests = new point[8];
		
		tests[0] = new point(1, 0);
		tests[1] = new point(1, 1);
		tests[2] = new point(0, 1);
		tests[3] = new point(-1, 1);
		tests[4] = new point(-1, 0);
		tests[5] = new point(-1, -1);
		tests[6] = new point(0, -1);
		tests[7] = new point(1, -1);
		
		int count = 0;
		
		for(int row = 0; row < tiles.length; row ++){
			for(int col = 0; col < tiles[0].length; col++){
				if(!tiles[row][col].isMine()){
					
					for(int i = 0; i < tests.length; i++){
						try {
							if(tiles[  row + tests[i].getX()  ][  col + tests[i].getY()  ].isMine()){
								count++;
							}
						} catch (IndexOutOfBoundsException e) {
							
						}
					}
					
					tiles[row][col].setVal(count);
					count = 0;
					
				}
			}
		}
	}
}
