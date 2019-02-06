import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Objects;

public class Sudoku01 {

    /**
     * Print a game menu message to the console.
     */
    public static void printMenu() {
        System.out.print("\n" +
        		"1. Set field\n" +
        		"2. Clear field\n" +
                "3. Print game\n" +
                "4. Exit\n\n" +
                "Select an action [1-4]: ");
    }   

    /**
     * Read a single integer value from the console and return it.
     * This function blocks the program's execution until a user
     * entered a value into the command line and confirmed by pressing
     * the Enter key.
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
    public static int requestInt(String msg, int min, int max) {
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
    public static void printGrid(int[][] mat) {
    	System.out.println("");
//    	for (int i=0; i < 9; i++) {
//        	for (int j = 0;  j< 9; j++) {// print the grid here
//        		if ((j+1) % 3 == 0) { //space after every 3 columns
//        			System.out.print(mat[i][j] + "  ");	
//        		}
//        		else {
//        			System.out.print(mat[i][j] + " ");
//        		}
//        	}
//        	if ((i+1) % 3 == 0) { //2 new lines after every 3 rows
//        		System.out.print("\n\n");
//        	}
//        	else  {
//        		System.out.print("\n");
//        	}
//        }
    
    	
		for (int i = 0; i < 9; ++i) {
			if(i == 0)
				System.out.println("    0 1 2   3 4 5   6 7 8");
		    if (i % 3 == 0)
		        System.out.println(" ---------------------------");
		    for (int j = 0; j < 9; ++j) {
		    	if (j == 0)
		    		System.out.print(i + " ");
		        if (j % 3 == 0) System.out.print("| ");
		        System.out.print(mat[i][j]);
		        System.out.print(' ');
		    }
		    System.out.println("|");
		}
		System.out.println(" ---------------------------");
    }
	 
	public static int[][] setField(int[][] mat) {
		int row = requestInt("Row number of field to change", 0, 8);
		int column = requestInt("Column number of field to change", 0, 8);
		mat[row][column] = requestInt("Enter the value you wish to assign", 0, 9);
		
		return mat;
	}
	
	public static int[][] clearField(int[][] mat) {
		int row = requestInt("Row number of field to clear", 0, 9);
		int column = requestInt("Column number of field to clear", 0, 9);
		mat[row][column] = 0;
		
		return mat;
	}
	
    public static void main(String[] args) {
        int[][] grid = {
            {9,4,0,1,0,2,0,5,8},
            {6,0,0,0,5,0,0,0,4},
            {0,0,2,4,0,3,1,0,0},
            {0,2,0,0,0,0,0,6,0},
            {5,0,8,0,2,0,4,0,1},
            {0,6,0,0,0,0,0,8,0},
            {0,0,1,6,0,8,7,0,0},
            {7,0,0,0,4,0,0,0,3},
            {4,3,0,5,0,9,0,1,2}
        };

        
        boolean flag = true;
        while (flag) {
        	printMenu();
        	int choice = parseInput(); //change to requestInt
        	switch (choice) {
			case 1:
				grid = setField(grid);
				break;
			case 2:
				grid = clearField(grid);
				break;
			case 3:
				printGrid(grid);
				break;
			case 4:
				flag = false; //break out of the loop
				break;
			default:
				System.out.println("Invalid Input Try again");
				break;
			}
        }
    }
}
