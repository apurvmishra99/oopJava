import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Objects;

public class Sudoku04 {

    /**
     * Print a game menu message to the console.
     */
    private static void printMenu() {
        System.out.print("\n" +
                "1. Set field\n" +
                "2. Clear field\n" +
                "3. Find Solution\n" +
                "4. Print game\n" +
                "5. Exit\n\n" +
                "Select an action [1-5]: ");
    }   

    /**
     * Read a single integer value from the console and return it.
     * This function blocks the program's execution until a user
     * entered a value into the command line and confirmed by pressing
     * the Enter key.
     * @return The user's input as integer or -1 if the user's input was invalid.
     */
    private static int parseInput() {
        Scanner in = new Scanner(System.in);
        try {
            return in.nextInt();
        } catch (InputMismatchException missE) {
            in.next(); // discard invalid input
            return -1;
        }
    }   

    /**
     * Display a dialog requesting a single integer which is returned
     * upon completion.
     *
     * The dialog is repeated in an endless loop if the given input 
     * is not an integer or not within min and max bounds.
     *
     * @param msg: a name for the requested data.
     * @param min: minimum accepted integer.
     * @param max: maximum accepted integer.
     * @return The user's input as integer.
     */
    private static int requestInt(String msg, int min, int max) {
        Objects.requireNonNull(msg);

        while(true) {
            System.out.print("Please provide " + msg + ": ");
            int input = parseInput();
            if (input >= min && input <= max) return input;
            else {
                System.out.println("Invalid input. Must be between " + min + " and " + max);
            }
        }
    }

    /**
     * Start a Sudoku game.
     *
     * @param args a single entry representing the path to a Sudoku data file
     */
    public static void main(String[] args) {
        if (args.length < 1)
            throw new IllegalArgumentException("Expecting path to game file as first argument.");
        GameGrid game = new GameGrid(args[0]);
		boolean flag = true;
		while (flag) {
			printMenu();
			int choice = parseInput();
			int column, row, val;
			switch (choice) {
			case 1:
				column = requestInt("column coordinate", 0, 8);
				row = requestInt("row coordinate", 0, 8);
				val = requestInt("field value", 1, 9);
              
				if (game.setField(column,row,val)) {
					System.out.println(game);                  
				} else {
					System.out.println("Value " + val + " not allowed at (" + column + "," + row + ")!");
				}
				break;
			case 2:
				column = requestInt("x coordinate", 0, 8);
				row = requestInt("y coordinate", 0, 8);
	            game.clearField(column,row);
	            System.out.println(game); 
				break;
			case 3:
				GameGrid gameToSolve = new GameGrid(game);
				if(Solver.solve(gameToSolve))
					System.out.println("Solution found:\n" + gameToSolve);
		        else
		            System.out.println("No Solution found!");
				break;
			case 4:
				System.out.println(game);
				break;
			case 5:
				flag = false; // break out of the loop
				break;
			default:
				System.out.println("Invalid Input Try again");
				break;
			}
		}
	}
}
