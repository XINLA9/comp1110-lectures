// package comp1110.exam;
package comp1110.lectures.R02;

/**
 * COMP1110 Final Exam, Question 1.3
 */
public class Q1Sudoku {
    public static final int SIDE_LENGTH = 9;

    /**
     * Solve a Sudoku puzzle.
     * A Sudoku puzzle consists of the numbers 1-9 arranged in a 9x9 grid,
     * divided into nine 3x3 subgrids.
     * In a valid solution for the puzzle, each of the numbers 1-9 appears only
     * once in each row, column and subgrid. For example, the following is a
     * valid solution:
     * -------------------------
     * | 5 8 1 | 9 4 7 | 6 3 2 |
     * | 3 7 6 | 8 1 2 | 5 9 4 |
     * | 9 2 4 | 3 5 6 | 7 8 1 |
     * -------------------------
     * | 6 5 7 | 2 8 9 | 1 4 3 |
     * | 1 3 8 | 6 7 4 | 2 5 9 |
     * | 2 4 9 | 1 3 5 | 8 7 6 |
     * -------------------------
     * | 8 9 3 | 7 6 1 | 4 2 5 |
     * | 4 6 2 | 5 9 8 | 3 1 7 |
     * | 7 1 5 | 4 2 3 | 9 6 8 |
     * -------------------------
     * <p>
     * whereas the following partial layout is not valid, because the number
     * 5 appears twice in the upper-left subgrid.
     * ---------------
     * | 5 8 1 | 3 ...
     * | 3 7 2 | 4 ...
     * | 4 5 6 | 1 ...
     * ---------------
     * |  ...  |   ...
     * <p>
     * The puzzle is specified as a 9x9 array of integers which holds the rows
     * and columns of numbers in each cell of the puzzle.
     * For example, the number in the second row and third column of the puzzle
     * is held in array element a[1][2].
     * If the setup gives zero for a number, it means that value for that cell
     * is not specified, and must be solved. In other words, zero must be
     * replaced with a number 1-9 to create a valid solution.
     * <p>
     * For example, the following is one possible setup for the valid solution
     * given above:
     * -------------------------
     * | 0 0 1 | 9 4 7 | 0 0 0 |
     * | 3 7 6 | 8 1 2 | 0 9 0 |
     * | 9 2 4 | 3 5 0 | 0 0 0 |
     * -------------------------
     * | 0 5 7 | 0 0 9 | 1 0 0 |
     * | 1 3 8 | 6 0 4 | 2 5 9 |
     * | 0 0 9 | 1 0 0 | 8 7 0 |
     * -------------------------
     * | 0 0 3 | 0 6 1 | 4 2 5 |
     * | 0 6 0 | 5 9 8 | 3 1 7 |
     * | 0 0 0 | 4 2 3 | 9 0 0 |
     * -------------------------
     * <p>
     * To solve the puzzle, each zero must be replaced with a number 1-9 so as to
     * create a valid solution.
     *
     * @param setup a 9x9 array of integers 0-9, where 0 represents a value to be solved
     * @return a valid solution for the given setup
     */

    /**
     * A simple class to hold a combined row and col index in the grid; makes
     * writing some of the helper methods easier.
     */
    private static class RowAndCol {
        int row;
        int col;

        RowAndCol(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // precompute static arrays of RowAndCols representing each row,
    // column, and 3x3 "box" in the grid:
    static final RowAndCol[][] rows = new RowAndCol[9][9];
    static final RowAndCol[][] cols = new RowAndCol[9][9];
    static final RowAndCol[][] boxes = new RowAndCol[9][9];

    // static initialisation block: this is run once, when the class is first
    // loaded/accessed.
    static {
        for (int row = 0; row < 9; row++)
            for (int col = 0; col < 9; col++) {
                rows[row][col] = new RowAndCol(row, col);
                cols[col][row] = new RowAndCol(row, col);
            }
        for (int box_row = 0; box_row < 3; box_row++)
            for (int box_col = 0; box_col < 3; box_col++) {
                int box_num = (3 * box_row) + box_col;
                // in the next two loops, row and col are within a box
                for (int row = 0; row < 3; row++)
                    for (int col = 0; col < 3; col++) {
                        int i = (3 * row) + col;
                        boxes[box_num][i] = new RowAndCol(3 * box_row + row, 3 * box_col + col);
                    }
            }
    }

    /**
     * Return the "next" empty cell in the grid, in the form of a RowAndCol
     * object; if the grid is filled (there are no blanks), return null.
     */
    static RowAndCol findNextBlank(int[][] grid) {
        for (int row = 0; row < 9; row++)
            for (int col = 0; col < 9; col++)
                if (grid[row][col] == 0)
                    return new RowAndCol(row, col);
        // if we have searched the whole grid and not returned, there are
        // no more blanks
        return null;
    }

    /**
     * Check if a partially or completely filled grid is valid. A grid is not valid
     * iff (a) some row, (b) some column, or (c) some 3x3 subsquare contains a
     * duplicate of some non-zero value. This implies that a completely filled valid
     * grid is a solution to the puzzle; as discussed in the lecture, Sudoku puzzles
     * have the property that once a partially filled grid becomes invalid, it can
     * not become valid again by filling in more cells. Therefore, we can cut off
     * a branch of the search as soon as we find that the current grid is invalid.
     * @param grid
     * @return false iff the grid contains a conflicting pair (is invalid).
     */
    static boolean isValid(int[][] grid) {
        // the idea is to just loop over the precomputed arrays of cells
        // representing rows, columns and boxes, and call checkSub on each:
        for (int i = 0; i < 9; i++)
            if (!checkSub(grid, rows[i])) return false;
        for (int i = 0; i < 9; i++)
            if (!checkSub(grid, cols[i])) return false;
        for (int i = 0; i < 9; i++)
            if (!checkSub(grid, boxes[i])) return false;
        return true;
    }

    /**
     * Subroutine used by isValid: checks if there is a duplicate non-zero
     */
    static boolean checkSub(int[][] grid, RowAndCol[] cells) {
        boolean[] present = {false, false, false, false, false, false, false, false, false, false}; // init a 9-bool array of falses
        for (var cell : cells) {
            int num = grid[cell.row][cell.col];
            if (num > 0 && present[num]) return false;
            present[num] = true;
        }
        return true;
    }

    /**
     * Recursive solution method for sudoku puzzle.
     * Btw, notice the similarity with the solution to the PenguinsPoolParty shown
     * in the game AI lecture; both are what we called (in the lecture) single-agent
     * deterministic games.
     * @return true if solution found; in this case grid is modified to the solution;
     *         false if no solution; in this case grid should be unchanged.
     */
    static boolean subsolve(int[][] grid) {
        RowAndCol next_blank = findNextBlank(grid);
        if (next_blank == null) return isValid(grid);
        for (int num = 1; num <= 9; num++) {
            grid[next_blank.row][next_blank.col] = num;
            if (isValid(grid)) {
                boolean success = subsolve(grid);
                if (success) return true;
            }
        }
        grid[next_blank.row][next_blank.col] = 0;
        return false;
    }

    public static int[][] solve(int[][] setup) {
        // FIXME complete this method
        int[][] partial = setup.clone();
        boolean success = subsolve(partial);
        assert success;
        return partial;
    }
}