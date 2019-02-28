public class Solver {
	public static boolean solve(GameGrid game) {
		for (int row = 0; row < GameGrid.GRID_DIM; row++) {
			for (int col = 0; col < GameGrid.GRID_DIM; col++) {
				if (game.getField(col, row) == 0) {
					for (int ans = GameGrid.MIN_VAL; ans <= GameGrid.MAX_VAL; ans++) {
						if (game.setField(col, row, ans) == true) {
							if (solve(game)) {
								return true;
							} else {
								game.clearField(col, row);
							}
						}

					}
					return false;
				}
			}
		}
		return true;
	}
}
