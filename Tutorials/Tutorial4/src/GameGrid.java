import java.util.Arrays;
import java.util.Objects;

public class GameGrid {

	public static final int GRID_DIM = 9;
	public static final int SUBGRID_DIM = GRID_DIM / 3;
	public static final int MAX_VAL = 9;
	public static final int MIN_VAL = 1;
	public static final int EMPTY_VAL = 0;
	private int[][] grid = null;

	public GameGrid(String gridPath) {
		Objects.requireNonNull(gridPath, "path to grid is null.");
		grid = IOUtils.loadFromFile(gridPath);
	}

	public int getField(int row, int column) {
		if (row < 0 || row > 8 || column < 0 || column > 8) {
			throw new IllegalArgumentException("The value of either row or column was out of bounds.");
		}
		return grid[row][column];
	}

	public boolean setField(int row, int column, int value) {
		if (row < 0 || row > 8 || column < 0 || column > 8) {
			throw new IllegalArgumentException("The value of either row or column was out of bounds.");
		}
		if (isValid(row, column, value)) {
			grid[row][column] = value;
			return true;
		} else {
			return false;
		}

	}

	private boolean checkRow(int row, int column, int value) {
		if (row < 0 || row > 8 || column < 0 || column > 8) {
			throw new IllegalArgumentException("The value of either row or column was out of bounds.");
		}
		for (int j = 0; j < GRID_DIM; j++) {
			if (value == grid[row][j]) {// printing out the index of the value which is against the rules.
				System.out.println("\nThere is a " + value + " at position " + "(" + row + ", " + j + ")");
				return false;
			}
		}
		return true;
	}

	private boolean checkColumn(int row, int column, int value) {
		if (row < 0 || row > 8 || column < 0 || column > 8) {
			throw new IllegalArgumentException("The value of either row or column was out of bounds.");
		}
		for (int i = 0; i < GRID_DIM; i++) {
			if (value == grid[i][column]) {// printing out the index of the value which is against the rules.
				System.out.println("\nThere is a " + value + " at position " + "(" + i + ", " + column + ")");
				return false;
			}
		}
		return true;
	}

	private boolean checkSubGrid(int row, int column, int value) {
		if (row < 0 || row > 8 || column < 0 || column > 8) {
			throw new IllegalArgumentException("The value of either row or column was out of bounds.");
		}
		// Dividing subgrids into subgrid X and Y
		int subGridX = 3 * (row / 3);
		int subGridY = 3 * (column / 3);
		for (int i = subGridX; i < (subGridX + 3); i++) {
			for (int j = subGridY; j < (subGridY + 3); j++) {
				if (value == grid[i][j]) { // printing out the index of the value which is against the rules.
					System.out.println("\nThere is a " + value + " at position " + "(" + i + ", " + j + ")");
					return false;
				}
			}
		}
		return true;
	}

	private boolean isValid(int row, int column, int value) {
		if (row < 0 || row > 8 || column < 0 || column > 8) {
			throw new IllegalArgumentException("The value of either row or column was out of bounds.");
		} // check if the value is legal at the given position.
		return checkRow(row, column, value) && checkColumn(row, column, value) && checkSubGrid(row, column, value);
	}

	public String toString() {
		Objects.requireNonNull(grid, "The rid is null");
		String gridS = "";
		for (int i = 0; i < GRID_DIM; i++) {
			if (i == 0)
				gridS += "    0 1 2   3 4 5   6 7 8\n";
			if (i % 3 == 0)
				gridS += " ---------------------------\n";
			for (int j = 0; j < 9; ++j) {
				if (j == 0)
					gridS += i + " ";
				if (j % 3 == 0)
					gridS += "| ";
				gridS += grid[i][j];
				gridS += ' ';
			}
			gridS += "|\n";
		}
		gridS += " ---------------------------\n";
		return gridS;
	}

	public void clearField(int row, int column) {
		if (row < 0 || row > 8 || column < 0 || column > 8) {
			throw new IllegalArgumentException("The value of either row or column was out of bounds.");
		}
		grid[row][column] = EMPTY_VAL;
	}
}