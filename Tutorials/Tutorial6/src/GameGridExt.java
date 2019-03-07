import java.util.Objects;
import java.util.ArrayList;

/**
 * Encapsulates Sudoku grid data and provides functionality to 
 * access and modify it.
 */
public class GameGridExt {

    // Constants for coordinate boundaries and Sudoku numbers
    public static final int GRID_DIM = 9;
    public static final int SUBGRID_DIM = GRID_DIM / 3;
    public static final int MAX_VAL = 9;
    public static final int MIN_VAL = 1;
    public static final int EMPTY_VAL = 0;

    /** Sudoku grid data */
    private final ArrayList<ArrayList<Field>> grid;

    /**
     * Create a new GameGrid with data based on the given array.
     * @param grid Sudoku game data. 
     */
    public GameGridExt(int[][] grid) {
        Objects.requireNonNull(grid);
        this.grid = initialiseGrid(grid);
    }

    /**
     * Create a new GameGrid with data based on the content of the provided file.
     * @param gridFile path to a file containing Sudoku grid data
     */
    public GameGridExt(String gridFile) {
        this(IOUtils.loadFromFile(gridFile));
    }

    /**
     * Create a Sudoku by creating a deep copy of the given one.
     * @param cpy Game grid data to be copied
     */
    public GameGridExt(GameGridExt cpy) {
        Objects.requireNonNull(cpy);

        grid = new ArrayList<>(GRID_DIM);

        for(int row = 0; row < GRID_DIM; row++) {
            grid.add(new ArrayList<>(GRID_DIM));
            for(int column = 0; column < GRID_DIM; column++) {
                // get value and initial flag from grid to be copied
                grid.get(row).add(new Field(
                        cpy.grid.get(row).get(column).getValue(), 
                        cpy.grid.get(row).get(column).isInitial()
                        ));
            }
        }
    }

    /** 
     * Initialise this GameGrid's grid member with the given data.
     *
     * @param gridData Sudoku grid data to be used for initialisation
     */
    private ArrayList<ArrayList<Field>> initialiseGrid(int[][] gridData) {
        ArrayList<ArrayList<Field>> result = new ArrayList<>(GRID_DIM);

        for(int row = 0; row < gridData.length; row++) {
            result.add(new ArrayList<>(GRID_DIM));
            for(int column = 0; column < gridData[row].length; column++) {
                int value = gridData[row][column];
                if (value == EMPTY_VAL) result.get(row).add(new Field());
                // during initialisation all grid values are initial values
                else result.get(row).add(new Field(value, true));             
            }
        }

        return result;
    }

    /** 
     * Check if the field at the given coordinates was set from the beginning
     * of the game.
     * @param column x coordinate between 0 and 8
     * @param row y coordinate between 0 and 8
     * @return true if this field was set from the beginning of the game, false otherwise.
     */
    public boolean isInitial(int column, int row) {
        if (column < 0 || column >= GRID_DIM || row < 0 || row >= GRID_DIM)
            throw new IllegalArgumentException("Given dimensions invalid: " + column + "x" + row);
        return grid.get(row).get(column).isInitial();
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
        return grid.get(row).get(column).getValue();
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

        if(!isInitial(column, row) && isValid(column,row,value)) {
            grid.get(row).get(column).setValue(value);
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

        grid.get(row).get(column).setValue(EMPTY_VAL);
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
            if (colIdx != column && grid.get(row).get(colIdx).getValue() == value) {
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
            if (rowIdx != row && grid.get(rowIdx).get(column).getValue() == value) {
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
                if (colIdx != column && rowIdx != row && grid.get(rowIdx).get(colIdx).getValue() == value) {
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
        String result = "";
        
        for (int row = 0; row < GRID_DIM; row++) {
            for(int column = 0; column < GRID_DIM; column++) {
                result += grid.get(row).get(column).toString();
                if(column % SUBGRID_DIM == 2) result += " ";
                result += " ";
            }

            if(row % SUBGRID_DIM == 2) result += "\n";
            result += "\n";
        }

        return result;
    }

}
