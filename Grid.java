package sudoku;

import java.util.*;


public class Grid 
{
	private int[][]						values;
	

	//
	// DON'T CHANGE THIS.
	//
	// See TestGridSupplier for examples of input.
	// Dots in input strings represent 0s in values[][].
	//
	public Grid(String[] rows)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
		{
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i=0; i<9; i++)
			{
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}
	
	//
	// DON'T CHANGE THIS.
	//
	public String toString()
	{
		String s = "";
		for (int j=0; j<9; j++)
		{
			for (int i=0; i<9; i++)
			{
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char)('0' + n);
			}
			s += "\n";
		}
		return s;
	}
	
	
	//
	// COMPLETE THIS
	//
	//
	// Finds an empty member of values[][]. Returns an array list of 9 grids that look like the current grid,
	// except the empty member contains 1, 2, 3 .... 9. Returns null if the current grid is full.
	//
	// Example: if this grid = 1........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//
	// Then the returned array list would contain:
	//
	// 11.......          12.......          13.......          14.......    and so on     19.......
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	//
	public ArrayList<Grid> next9Grids()
	{	
		if(this.isFull()) {
			return null; 
		}
		
		ArrayList<Grid> nineGrids = new ArrayList<Grid>();
		for(int i = 0; i < values.length; i++) {
			for(int j = 0; j < values[0].length; j++) {
				if(values[i][j] == 0) {
					for(int k = 1; k < 10; k++) {
						values[i][j] = k;	
						String something = this.toString();
						String [] rows = something.split("\n");
						Grid g = new Grid(rows);
						nineGrids.add(g);
					}
					return nineGrids; 
				}
			}
		}
		return null;
	}
	
	//
	// COMPLETE THIS
	//
	// Returns true if this grid is legal. A grid is legal if no row, column, or 
	// 3x3 block contains a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
	//
	public boolean isLegal()
	{
		return checkThreeByThree() && checkRow() && checkColumn();
	}
	
	//checks to see if the 3x3 grid is legal
	public boolean checkThreeByThree() {
		HashMap<Integer, Integer> allVals = new HashMap<>();
		int starter = 0; 
		int ender = 0; 
		int counter = 0; 
		while(counter < 9) {
			if(counter % 3 == 0 && counter != 0) {
				starter = 0; 
				ender += 3; 
			}
			for(int i = starter; i < starter + 3; i++) {
				for(int j = ender; j < ender + 3; j++) {
					if(!allVals.containsKey(values[i][j])) {
						allVals.put(values[i][j], 1);
					}
					else if(values[i][j] == 0) {
						continue;
					}
					else {
						return false;
					}
				}
			}
			starter += 3; 
			counter++;
			allVals.clear();
		}
		return true;
	}

	//checks to see if the row is legal
	public boolean checkRow() {
		HashMap<Integer, Integer> allVals = new HashMap<>();
		for(int i = 0; i < values.length; i++) {
			for(int j = 0; j < values[0].length; j++) {
				if(!allVals.containsKey(values[i][j])) {
					allVals.put(values[i][j], 1);
				}
				else if(values[i][j] == 0) {
					continue;
				}
				else {
					return false;
				}
			}
			allVals.clear();
		}
		return true;
	}
	
	//checks to see if the column is legal
	public boolean checkColumn() {
		HashMap<Integer, Integer> allVals = new HashMap<>();
		for(int i = 0; i < values.length; i++) {
			for(int j = 0; j < values[0].length; j++) {
				if(!allVals.containsKey(values[j][i])) {
					allVals.put(values[j][i], 1);
				}
				else if(values[j][i] == 0) {
					continue;
				}
				else {
					return false;
				}
			}
			allVals.clear();
		}
		return true;
	}
	
	
	//
	// COMPLETE THIS
	//
	// Returns true if every cell member of values[][] is a digit from 1-9.
	//
	public boolean isFull()
	{
		for(int i = 0; i < values.length; i++) {
			for(int j = 0; j < values[0].length; j++) {
				if(values[i][j] < 1 || values[i][j] > 9) {
					return false;
				}
			}
		}
		return true;
	}
	
	//
	// COMPLETE THIS
	//
	// Returns true if x is a Grid and, for every (i,j), 
	// x.values[i][j] == this.values[i][j].
	//
	public boolean equals(Object x)
	{
		Grid that = (Grid) x;
		for(int i = 0; i < values.length; i++) {
			for(int j = 0; j < values[0].length; j++) {
				if(that.values[i][j] != this.values[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
