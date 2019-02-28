import java.util.Objects;

/**
 * Encapsulates Sudoku grid data and provides functionality to 
 * access and modify it.
 */
public class GameGrid {

    // Constants for coordinate boundaries and Sudoku numbers
    public static final int GRID_DIM = 9;
    public static final int SUBGRID_DIM = GRID_DIM / 3;
    public static final int MAX_VAL = 9;
    public static final int MIN_VAL = 1;
    public static final int EMPTY_VAL = 0;

    /** Sudoku grid data */
    private final Field[][] grid = new Field[GRID_DIM][GRID_DIM];

    /**
     * Create a new GameGrid with data based on the given array.
     * @param grid Sudoku game data. 
     */
    public GameGrid (int[][] mat) {
        Objects.requireNonNull(mat);
        initialiseGrid(mat);
    }

    /**
     * Create a new GameGrid with data based on the content of the provided file.
     * @param gridFile path to a file containing Sudoku grid data
     */
    public GameGrid (String gridFile) {
        Objects.requireNonNull(gridFile);
        initialiseGrid(IOUtils.loadFromFile(gridFile));
    }
    
    public GameGrid (GameGrid otherGrid) {
    	for (int i = 0; i < GRID_DIM; i++) {
			for (int j = 0; j < GRID_DIM; j++) {
				this.grid[j][i] = new Field(otherGrid.getField(i, j), otherGrid.isInitial(i, j));
			}
    	}
    }
    
    private void initialiseGrid(int[][] mat) {
    	for (int i = 0; i < GRID_DIM; i++) {
			for (int j = 0; j < GRID_DIM; j++) {
				if(mat[i][j] != 0) {
					this.grid[i][j] = new Field(mat[i][j], true);
				}
				else {
					this.grid[i][j] = new Field();
				}
			}	
		}
    }
    
    public boolean isInitial(int rowIdx, int colIdx) {
    	if (colIdx < 0 || colIdx >= GRID_DIM || rowIdx < 0 || rowIdx >= GRID_DIM)
            throw new IllegalArgumentException("Given dimensions invalid: " + colIdx + "x" + rowIdx);
    	return grid[rowIdx][colIdx].isInitial();
    }
    /**
     * Return the grid value specified by the given coordinates.
     * @param column x coordinate between 0 and 8
     * @param row y coordinate between 0 and 8
     * @return the specified Sudoku value between 0 and 9
     */
    public int getField(int column, int row) {
        if (column < 0 || column >= GRID_DIM || row < 0 || row >= GRID_DIM)
            throw new IllegalArgumentException("Given dimensions invalid: " + column + "x" + row);
        return grid[row][column].getValue();
    }

    /**
     * Sets the grid at the given coordinate to the given value.
     * 
     * If the value is not allowed according to Sudoku rules, 
     * the field is not set and false is returned.
     * If setting was successful, true is returned. 
     *
     * @param column x coordinate between 0 and 8
     * @param row y coordinate between 0 and 8
     * @param value the Sudoku number to be set between 1 and 9
     * @return true if successful, false otherwise
     */
    public boolean setField(int column, int row, int value) {
        if (column < 0 || column >= GRID_DIM || row < 0 || row >= GRID_DIM)
            throw new IllegalArgumentException("Given dimensions invalid: " + column + "x" + row);
        if (value < MIN_VAL || value > MAX_VAL)
            throw new IllegalArgumentException("Given value invalid: " + value);

        if(isValid(column,row,value) && !grid[row][column].isInitial()) {
            grid[row][column].setValue(value);
            return true;
        }

        return false;
    }

    /**
     * Clear the specified Sudoku field.
     * @param column x coordinate between 0 and 8
     * @param row y coordinate between 0 and 8    
     */
    public void clearField(int column, int row) {
        if (column < 0 || column >= GRID_DIM || row < 0 || row >= GRID_DIM)
            throw new IllegalArgumentException("Given dimensions invalid: " + column + "x" + row);

        grid[row][column].setValue(EMPTY_VAL);
    }

    /**
     * Check if the specified value is allowed at the specified position
     * according to Sudoku rules.
     *
     * @param column x coordinate between 0 and 8
     * @param row y coordinate between 0 and 8
     * @param value the Sudoku number to be set between 1 and 9
     * @return true if allowed, false otherwise 
     */
    private boolean isValid(int column, int row, int value) {
        return checkRow(column,row,value) && 
               checkColumn(column,row,value) && 
               checkSubGrid(column,row,value);
    }

    /**
     * Check if the specified value is allowed in the specified row
     * according to Sudoku rules.
     *
     * @param column x coordinate between 0 and 8
     * @param row y coordinate between 0 and 8
     * @param value the Sudoku number to be set between 1 and 9
     * @return true if allowed, false otherwise 
     */   
    private boolean checkRow(int column, int row, int value) {
        boolean result = true;        
        for(int colIdx = 0; colIdx < GRID_DIM; colIdx++) {
            if (colIdx != column && grid[row][colIdx].getValue() == value) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Check if the specified value is allowed in the specified column
     * according to Sudoku rules.
     *
     * @param column x coordinate between 0 and 8
     * @param row y coordinate between 0 and 8
     * @param value the Sudoku number to be set between 1 and 9
     * @return true if allowed, false otherwise 
     */   
    private boolean checkColumn(int column, int row, int value) {
        boolean result = true;        
        for(int rowIdx = 0; rowIdx < GRID_DIM; rowIdx++) {
            if (rowIdx != row && grid[rowIdx][column].getValue() == value) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Check if the specified value is allowed in the specified subgrid
     * according to Sudoku rules.
     *
     * @param column x coordinate between 0 and 8
     * @param row y coordinate between 0 and 8
     * @param value the Sudoku number to be set between 1 and 9
     * @return true if allowed, false otherwise 
     */   
    private boolean checkSubGrid(int column, int row, int value) {
        // calculate sub 3x4 grid start values
        final int column_start = column / SUBGRID_DIM * SUBGRID_DIM;
        final int row_start = row / SUBGRID_DIM * SUBGRID_DIM;

        boolean result = true;
        for(int colIdx = column_start; colIdx < column_start + SUBGRID_DIM; colIdx++) {
            for(int rowIdx = row_start; rowIdx < row_start + SUBGRID_DIM; rowIdx++) {
                if (colIdx != column && rowIdx != row && grid[rowIdx][colIdx].getValue() == value) {
                    result = false;
                    break;
                }
            }
        } 
        return result;
    }

    /**
     * Return an ASCII representation of the game grid. 
     * @param grid the game grid to be printed
     */
    public String toString() {
        StringBuilder bld = new StringBuilder();
        
        for (int row = 0; row < GRID_DIM; row++) {
            for(int column = 0; column < GRID_DIM; column++) {
                bld.append(grid[row][column].getValue());
                if(column % SUBGRID_DIM == 2) bld.append(" ");
                bld.append(" ");
            }

            if(row % SUBGRID_DIM == 2) bld.append("\n");
            bld.append("\n");
        }

        return bld.toString();
    }

}