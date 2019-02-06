import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Objects;

public class Sudoku02 {
	// set the value for the sudoku grid from the arguments given when running the program.
	public static int[][] parseArgs(String[] args) {
		int[][] grid = new int[9][9];
		for (int i = 0; i < 9; i++) {
			int count = 9;
			for (int j = 0; j < 9; j++) {
				grid[i][j] = Integer.parseInt(args[i*count + j]);
			}
		}
		return grid;
	}

	/**
	 * Print a game menu message to the console.
	 */
	public static void printMenu() {
		System.out.print("\n" + "1. Set field\n" + "2. Clear field\n" + "3. Print game\n" + "4. Exit\n\n"
				+ "Select an action [1-4]: ");
	}

	/**
	 * Read a single integer value from the console and return it. This function
	 * blocks the program's execution until a user entered a value into the command
	 * line and confirmed by pressing the Enter key.
	 * 
	 * @return The user's input as integer or -1 if the user's input was invalid.
	 */
	public static int parseInput() {
		Scanner in = new Scanner(System.in);
		try {
			return in.nextInt();
		} catch (InputMismatchException missE) {
			in.next(); // discard invalid input
			return -1;
		}
	}

	/**
	 * Display a dialog requesting a single integer which is returned upon
	 * completion.
	 *
	 * The dialog is repeated in an endless loop if the given input is not an
	 * integer or not within min and max bounds.
	 *
	 * @param msg: a name for the requested data.
	 * @param min: minimum accepted integer.
	 * @param max: maximum accepted integer.
	 * @return The user's input as integer.
	 */
	public static int requestInt(String msg, int min, int max) {
		Objects.requireNonNull(msg);

		while (true) {
			System.out.print("Please provide " + msg + ": ");
			int input = parseInput();
			if (input >= min && input <= max)
				return input;
			else {
				System.out.println("Invalid input. Must be between " + min + " and " + max);
			}
		}
	}

	public static void printGrid(int[][] mat) {
		System.out.println("");
		for (int i = 0; i < 9; ++i) {
			if (i == 0)
				System.out.println("    0 1 2   3 4 5   6 7 8");
			if (i % 3 == 0)
				System.out.println(" ---------------------------");
			for (int j = 0; j < 9; ++j) {
				if (j == 0)
					System.out.print(i + " ");
				if (j % 3 == 0)
					System.out.print("| ");
				System.out.print(mat[i][j]);
				System.out.print(' ');
			}
			System.out.println("|");
		}
		System.out.println(" ---------------------------");
	}

	public static void setField(int[][] mat) {
		int row = requestInt("Row number of field to change", 0, 8);
		int column = requestInt("Column number of field to change", 0, 8);
		int value = requestInt("Enter the value you wish to assign", 0, 9);
		if (isValid(row, column, value, mat)) { //check if your value can be assigned to the given position
			mat[row][column] = value;
		} else {
			System.err.println("\nValue not allowed");
		}
	}

	public static void clearField(int[][] mat) {
		int row = requestInt("Row number of field to clear", 0, 8);
		int column = requestInt("Column number of field to clear", 0, 8);
		mat[row][column] = 0;
	}

	public static boolean checkRow(int x, int y, int value, int[][] mat) {
		for (int j = 0; j < 9; j++) {
			if (value == mat[x][j]) {//printing out the index of the value which is against the rules.
				System.out.println("\nThere is a " + value + " at position " + "(" + x + ", " + j + ")");
				return false;
			}
		}
		return true;
	}

	public static boolean checkColumn(int x, int y, int value, int[][] mat) {
		for (int i = 0; i < 9; i++) {
			if (value == mat[i][y]) {//printing out the index of the value which is against the rules.
				System.out.println("\nThere is a " + value + " at position " + "(" + i + ", " + y + ")");
				return false;
			}
		}
		return true;
	}

	public static boolean checkSubGrid(int x, int y, int value, int[][] mat) {
		// Dividing subgrids into subgrid X and Y
		int subGridX = x / 3;
		int subGridY = y / 3;
		for (int i = subGridX * 3; i < (subGridX * 3 + 3); i++) {
			for (int j = subGridY * 3; j < (subGridY * 3 + 3); j++) {
				if (value == mat[i][j]) { //printing out the index of the value which is against the rules.
					System.out.println("\nThere is a " + value + " at position " + "(" + i + ", " + j + ")");
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isValid(int x, int y, int value, int[][] mat) { //check if the value is legal at the given position.
		return checkRow(x, y, value, mat) && checkColumn(x, y, value, mat) && checkSubGrid(x, y, value, mat);
	}

	public static void main(String[] args) {
//		int[][] grid = { { 9, 4, 0, 1, 0, 2, 0, 5, 8 }, { 6, 0, 0, 0, 5, 0, 0, 0, 4 }, { 0, 0, 2, 4, 0, 3, 1, 0, 0 },
//				{ 0, 2, 0, 0, 0, 0, 0, 6, 0 }, { 5, 0, 8, 0, 2, 0, 4, 0, 1 }, { 0, 6, 0, 0, 0, 0, 0, 8, 0 },
//				{ 0, 0, 1, 6, 0, 8, 7, 0, 0 }, { 7, 0, 0, 0, 4, 0, 0, 0, 3 }, { 4, 3, 0, 5, 0, 9, 0, 1, 2 } };
		int[][] grid = parseArgs(args);
		boolean flag = true;
		while (flag) {
			printMenu();
			int choice = parseInput(); // change to requestInt
			switch (choice) {
			case 1:
				setField(grid);
				break;
			case 2:
				clearField(grid);
				break;
			case 3:
				printGrid(grid);
				break;
			case 4:
				flag = false; // break out of the loop
				break;
			default:
				System.out.println("Invalid Input Try again");
				break;
			}
		}
	}
}
