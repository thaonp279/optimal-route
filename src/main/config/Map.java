package main.config;

/********************Starter Code
 * 
 * This class contains the maps to be used for evaluation 
 * 
 * @author at258
 *
 */

public enum Map{


	//************************TEST MAPS as discussed in lectures ******************** 

	JMAP00(new int [][] { //JMAP00 is the map in the spec  
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,1,1,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0}

	}),


	JMAP01(new int [][] { //JMAP01 is used in the given tests
		{0,0,0},
		{0,0,0},
		{0,0,0}
	}),
	

	JMAP02(new int [][] { //JMAP03 is used in the given tests
		{0,0,0},
		{0,0,0},
		{0,1,0}
	}),

	//************************MAPS for evaluation ********************
	MAP0(new int [][] {
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0}
	}),
	MAP1(new int [][] {
		{0,0,0,0,0},
		{0,0,1,0,0},
		{0,0,1,0,0},
		{0,0,1,0,0},
		{0,0,0,0,0},
	}),
	MAP2(new int [][] {
		{0,0,0,0,0,0},
		{0,0,1,1,0,0},
		{0,0,1,0,1,0},
		{0,0,1,1,1,0},
		{0,0,0,0,0,0},
		{0,0,1,1,1,0}
	}),
	MAP3(new int [][] {
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,1,0,0,1,0},
		{0,0,0,1,0,0,0,0,0,0},
		{0,0,0,0,0,0,1,1,0,0},
		{0,0,1,1,0,1,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},

	}),
	MAP4(new int [][] {
		{0,0,0,0,0,0,0,0},
		{0,0,0,0,0,1,0,0},
		{1,0,0,1,1,0,0,0},
		{0,0,0,1,0,0,0,0},
		{0,0,1,0,0,0,1,0},
		{0,0,1,1,0,0,0,0},
		{0,0,1,1,0,0,0,0},
		{0,0,0,0,0,0,0,0}
	})
	;

	private final int[][] map;
	private final int rowBound;
	private final int colBound;

	Map(int[][] map){
		this.map=map;
		rowBound = map.length - 1; //inclusive upperbound on row index
		colBound = map[0].length - 1; //inclusive upperbound on col index
	}

	public int[][] getMap() {
		return map;
	}

	public boolean isLegalRowCol(int row, int col) {
		return isValidRow(row) && isValidCol(col) && !isLand(row, col);
	}

	public boolean isLand(int row, int col) {
		if (!isValidRow(row) || !isValidCol(col)) { // if out of bound then is not land
			return false;
		}
		return map[row][col] == 1;
	}

	public boolean isValidRow(int row) {
		return row >= 0 && row <= rowBound;
	}

	public boolean isValidCol(int col) {
		return col >= 0 && col <= colBound;
	}

}