import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Objects;

public class Sudoku {
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

	public static void setField(GameGrid game) {
		int row = requestInt("Row number of field to change", 0, 8);
		int column = requestInt("Column number of field to change", 0, 8);
		int value = requestInt("Enter the value you wish to assign", 0, 9);
		game.setField(row, column, value);
	}

	public static void clearField(GameGrid game) {
		int row = requestInt("Row number of field to clear", 0, 8);
		int column = requestInt("Column number of field to clear", 0, 8);
		game.clearField(row, column);
	}

	public static void main(String[] args) {
		
		GameGrid game = new GameGrid(args[0]);
		boolean flag = true;
		while (flag) {
			printMenu();
			int choice = parseInput(); // change to requestInt
			switch (choice) {
			case 1:
				setField(game);
				break;
			case 2:
				clearField(game);
				break;
			case 3:
				System.out.println(game);
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
