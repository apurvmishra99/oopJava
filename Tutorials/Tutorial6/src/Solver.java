import java.util.Objects;

/**
 * Functionality to solve a given Sudoku game. 
 */
public class Solver {

    /** 
     * Solve the given Sudoku game using backtracking.
     *
     * @param game The game to be solved.
     * @return true if a solution was found, false otherwise.
     */
    public static boolean solve(GameGrid game) {
       Objects.requireNonNull(game);

       // start at top left
       int column = 0;
       int row = 0;

       // if true, we move backwards through the grid, forward otherwise
       boolean goBack = false;

       // while not iterated through all possible combinations yet
       while(!(column == GameGrid.GRID_DIM - 1 && row == -1)) {
           
           // try values on current field
           if(!game.isInitial(column,row)) {
               goBack = false; // go forward
               if(!tryIncrease(game,column,row)) {
                   game.clearField(column,row);
                   goBack = true; // track back
               }

           } 
           
           // move through grid
           if(goBack) { // backwards
               column--;
               if(column < 0) { // move up one row
                   column = GameGrid.GRID_DIM - 1;
                   row--;
               }
           } else { // forward
              column++;
              if(column >= GameGrid.GRID_DIM) { // move down one row
                  column = 0;
                  row++;
              }
           }

           // we reached the end, hence found a valid solution
           if (column == 0 && row == GameGrid.GRID_DIM)
               return true;
      }

      // we tried all possible combinations without reaching the end, hence no solution
      return false;
    }

    /**
     * A helper method trying to increase the value of a specified field 
     * in the given game based on Sudoku rules.
     *
     * @param game The game of which the specified value is to be increased
     * @param column x coordinate between 0 and 8
     * @param row y coordinate between 0 and 8
     * @return true if the value could be increased, false otherwise.
     *
     */
    private static boolean tryIncrease(GameGrid game, int column, int row) {
        int val = game.getField(column,row);

        boolean success = false;
        for(int i = val + 1; i <= GameGrid.MAX_VAL; i++) {
            if(game.setField(column,row,i)) {
                success = true;
                break;
            }
        }

        return success;
    }
}
