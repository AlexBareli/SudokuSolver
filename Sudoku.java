import java.util.Arrays;
//Alexander Bareli
//@01/9/22 - Version 1.01
//CS245 Lab 2: Sudoku Solver

//This is a class Sudoku that solves a given Sudoku puzzle or returns "no solution" if unsolvable.
//Stores a private charArray which is the given puzzle to solve.

public class Sudoku {
	private char[][] sudoku;

	//Constructor for the Sudoku class, creates a new charArray that is 9x9

	public Sudoku () {
		sudoku = new char[9][9];
	}

	//Method that sets the initialized charArray to a given board.

	public void inputBoard(char [][] board) throws Exception {
		sudoku = board;

	}

	//Method that calls the solveSudoku method and returns true or false if it can be solved.

	public boolean solveSudoku(){
		return solveSudoku(0, 0);
	}

	//Method that solves the Sudoku puzzle. Takes row and column to specify which cell to look at.

	public boolean solveSudoku(int row, int col) {

		//Base Case: if the row and column are equal to the length of the charArray,
		//puzzle has been solved, return true.

		if(row == sudoku.length-1 && col == sudoku.length){
			return true;
		}

		//If column is equal length of the charArray, increment row by 1 and set column back to 0.

		if(col == sudoku.length){
			row += 1;
			col = 0;
		}

		//Check to see if the given [row][column] combination is not an empty cell. If it isn't,
		//increment the column by 1.

		if(sudoku[row][col] != '.'){
			return solveSudoku(row, col + 1);
		}

		//If [row][column] is empty cell, check numbers 1 through 9 to see if it is a valid placement
		//and recursively call solveSudoku, incrementing column by 1. If placement breaks down in later
		//attempts, backtrack and try another number until program is solved.

		for (int i = 1; i < 10; i++) {
			if (checkNumber(row, col, i)) {
				sudoku[row][col] = Integer.toString(i).charAt(0);
				if (solveSudoku(row, col + 1)) {
					return true;
				}
				sudoku[row][col] = '.';
			}
		}
		return false;
	}


	//Method to check if number is a valid placement. Checks to see if number is contained in the column
	//or row, and then checks to see if it's contained within a 3x3 section. Returns true if valid placement.

	public boolean checkNumber (int row, int column, int num){

		for(int i = 0; i < sudoku.length; i++) {
			if (sudoku[row][i] == Integer.toString(num).charAt(0)) {
				return false;
			}
			if (sudoku[i][column] == Integer.toString(num).charAt(0)){
				return false;
			}


		}

		int rowSector = row - (row % 3);
		int columnSector = column - (column % 3);

		for(int x = rowSector; x < rowSector + 3; x++){
			for(int y = columnSector; y < columnSector + 3; y++){
				if(sudoku[x][y] == Integer.toString(num).charAt(0)){
					return false;
				}
			}
		}




		return true;
	}

	//Prints the solved Sudoku Board.

	public void printBoard() {
		for(int i = 0; i < sudoku.length; i++){
			for(int y = 0; y < sudoku[i].length; y++){
				System.out.print(sudoku[i][y]);
			}
			System.out.println();
		}
	}

	//Main Method, creates a new Sudoku class and sets the charArray equal to specified board.
	//Prints board if solvable, otherwise no solution is given.

	public static void main(String[] args) throws Exception {

		char[][] board = {{'5','3','.','.','7','.','.','.','.'},
				{'6','.','.','1','9','5','.','.','.'},
				{'.','9','8','.','.','.','.','6','.'},
				{'8','.','.','.','6','.','.','.','3'},
				{'4','.','.','8','.','3','.','.','1'},
				{'7','.','.','.','2','.','.','.','6'},
				{'.','6','.','.','.','.','2','8','.'},
				{'.','.','.','4','1','9','.','.','5'},
				{'.','.','.','.','8','.','.','7','9'}};

		/* Solution for board.

			{{"5","3","4","6","7","8","9","1","2"},
			{"6","7","2","1","9","5","3","4","8"},
			{"1","9","8","3","4","2","5","6","7"},
			{"8","5","9","7","6","1","4","2","3"},
			{"4","2","6","8","5","3","7","9","1"},
			{"7","1","3","9","2","4","8","5","6"},
			{"9","6","1","5","3","7","2","8","4"},
			{"2","8","7","4","1","9","6","3","5"},
			{"3","4","5","2","8","6","1","7","9"}}

		 */

		char[][] board2 = {{'2','.','.','.','.','.','3','.','.'},
				{'.','.','5','.','6','.','.','7','.'},
				{'3','.','.','.','.','7','8','.','.'},
				{'.','5','.','.','2','9','.','.','.'},
				{'7','.','.','.','3','.','.','.','5'},
				{'.','.','.','6','1','.','.','2','.'},
				{'.','.','1','8','.','.','.','.','3'},
				{'.','4','.','.','7','.','1','.','.'},
				{'.','.','8','.','.','.','.','.','9'}};

		/*	Solution for board 2. sudokuoftheday.com (puzzle for Feb 10)
				{{'2','9','7','1','8','4','3','5','6'},
				{'4','8','5','9','6','3','2','7','1'},
				{'3','1','6','2','5','7','8','9','4'},
				{'1','5','4','7','2','9','6','3','8'},
				{'7','6','2','4','3','8','9','1','5'},
				{'8','3','9','6','1','5','4','2','7'},
				{'6','7','1','8','9','2','5','4','3'},
				{'9','4','3','5','7','6','1','8','2'},
				{'5','2','8','3','4','1','7','6','9'}}
		 */

		Sudoku sudoku = new Sudoku();

		sudoku.inputBoard(board);
		if(sudoku.solveSudoku()) {
			System.out.println("Solution for Board: ");
			System.out.println();
			sudoku.printBoard();
		}
		else{
			System.out.println("No solutions found");
		}

		System.out.println();

		sudoku.inputBoard(board2);
		if(sudoku.solveSudoku()){
			System.out.println("Soluton for Board 2 :");
			System.out.println();
			sudoku.printBoard();
		}
		else{
			System.out.println("No solutions found");
		}

	}

}
