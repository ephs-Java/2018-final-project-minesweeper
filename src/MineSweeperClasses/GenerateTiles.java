package MineSweeperClasses;

import java.util.Random;

public class GenerateTiles {
	
	Tile[][] tiles;
	point[] tests;
	
	private Random r;
	
	public GenerateTiles(int size){
		
		this.r = new Random();
		
				tiles = new Tile[size][size];
		
		//this for loop generates the mines and assigns each tile in the 2D array to a new mine
		
		for(int row = 0; row < tiles.length; row++){
			for(int col = 0; col < tiles.length; col++){
				tiles[row][col] = new Tile(r.nextInt(10));
			}
		}
	
		generateMines();
		genTileVal();
		
		UserInterface UI = new UserInterface(tiles, tests, this);
		
	}
	
	public void generateMines(){
		for(int row = 0; row < tiles.length; row++){
			for(int col = 0; col < tiles.length; col++){
				tiles[row][col] = new Tile(r.nextInt(10));
			}
		}
	}
	
	public void resetTiles(){
		for(int row = 0; row < tiles.length; row++){
			for(int col = 0; col < tiles.length; col++){
				tiles[row][col].setMine(false);
				tiles[row][col].setVal(0);
			}
		}
	}
	
	public Tile[][] getTiles(){
		return tiles;
	}
	
	//This method generates the value for each tile in the 2D array of tiles
	
	public void genTileVal(){
		
		point[] tests = new point[8];
		this.tests = tests;
		
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
