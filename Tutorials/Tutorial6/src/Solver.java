//import java.util.ArrayList;
//import java.util.Objects;
//
///**
// * Functionality to solve a given Sudoku game.
// */
//public class Solver {
//
//	/**
//	 * Solve the given Sudoku game using backtracking.
//	 *
//	 * @param game The game to be solved.
//	 * @return true if a solution was found, false otherwise.
//	 */
//	public static ArrayList<GameGrid> findAllSolutions(GameGrid game) {
//		Objects.requireNonNull(game);
//		ArrayList<GameGrid> allSolutions = new ArrayList<GameGrid>();
//		
//		int column = 0;
//		int row = 0;
//		int lastCol = 0;
//		int lastRow = 0;
//
//		// if true, we move backwards through the grid, forward otherwise
//		boolean goBack = false;
//
//		// while not iterated through all possible combinations yet
//		while (!(column == GameGrid.GRID_DIM - 1 && row == -1)) {
//
//			// try values on current field
//			if (!(game.isInitial(column, row))) {
//				goBack = false;
//				if (!tryIncrease(game, column, row)) {
//					game.clearField(column, row);
//					goBack =true;
//				} else {
//					lastCol = column;
//					lastRow = row;
//				}
//			}
//				lastCol = column;
//				lastRow = row;
//			// move through grid
//			if (goBack) { // backwards
//				column--;
//				if (column < 0) { // move up one row
//					column = GameGrid.GRID_DIM - 1;
//					row--;
//				}
//			} else { // forward
//				column++;
//				if (column >= GameGrid.GRID_DIM) { // move down one row
//					column = 0;
//					row++;
//				}
//			}
//
//			// we reached the end, hence found a valid solution
//			if (column == 0 && row == GameGrid.GRID_DIM) {
//				if (!allSolutions.contains(new GameGrid(game)))
//					allSolutions.add(new GameGrid(game));
//				column = lastCol;
//				row = lastRow;
//			}
//		}
//			
//		// we tried all possible combinations without reaching the end, hence no
//		// solution
//		return allSolutions;
//	}
//	
//	public static boolean solve(GameGrid game) {
//       Objects.requireNonNull(game);
//
//       // start at top left
//       int column = 0;
//       int row = 0;
//
//       // if true, we move backwards through the grid, forward otherwise
//       boolean goBack = false;
//
//       // while not iterated through all possible combinations yet
//       while(!(column == GameGrid.GRID_DIM - 1 && row == -1)) {
//           
//           // try values on current field
//           if(!game.isInitial(column,row)) {
//               goBack = false; // go forward
//               if(!tryIncrease(game,column,row)) {
//                   game.clearField(column,row);
//                   goBack = true; // track back
//               }
//
//           } 
//           
//           // move through grid
//           if(goBack) { // backwards
//               column--;
//               if(column < 0) { // move up one row
//                   column = GameGrid.GRID_DIM - 1;
//                   row--;
//               }
//           } else { // forward
//              column++;
//              if(column >= GameGrid.GRID_DIM) { // move down one row
//                  column = 0;
//                  row++;
//              }
//           }
//
//           // we reached the end, hence found a valid solution
//           if (column == 0 && row == GameGrid.GRID_DIM)
//               return true;
//      }
//
//      // we tried all possible combinations without reaching the end, hence no solution
//      return false;
//    }
//
//	/**
//     * A helper method trying to increase the value of a specified field 
//     * in the given game based on Sudoku rules.
//     *
//     * @param game The game of which the specified value is to be increased
//     * @param column x coordinate between 0 and 8
//     * @param row y coordinate between 0 and 8
//     * @return true if the value could be increased, false otherwise.
//     *
//     */
//    private static boolean tryIncrease(GameGrid game, int column, int row) {
//        int val = game.getField(column,row);
//
//        boolean success = false;
//        for(int i = val + 1; i <= GameGrid.MAX_VAL; i++) {
//            if(game.setField(column,row,i)) {
//                success = true;
//                break;
//            }
//        }
//
//        return success;
//    }
//}
import java.util.ArrayList;

public class Solver {
	
	static ArrayList<GameGrid> solutions = new ArrayList<GameGrid>();
	
	public static boolean solve (GameGrid input)
	{
		GameGrid game = new GameGrid(input);
		for(int i=0; i<9; i++)
		{
			for(int j=0; j<9; j++)
			{
				if(game.getField(i, j)==0)
				{
					for(int ans=1; ans<10; ans++)
					{
						if(game.setField(i, j, ans)==true)
						{
							if(solve(game) && notexist(solutions, game)) return true;
							else game.clearField(i, j);
						}
					}
					return false;
				}
			}
		}
		solutions.add(game);
		return false;
	}
	
	public static ArrayList<GameGrid> findAllSolutions (GameGrid game)
	{
		solve (game);
		return solutions;
	}
	
	public static boolean notexist (ArrayList<GameGrid> solutions, GameGrid game)
	{
		boolean flag = false;
		if(solutions.size()==0)
		flag = true;
		else
		{
			for(GameGrid sol : solutions)
			{
				for(int i=0; i<9; i++)
					for(int j=0; j<9; j++)
					{
						if (game.getField(i, j)==sol.getField(i,j))
							flag = false;
						else
							flag = true;
					}
			}
		}
		return flag;
	}
}
