package sudoku;

// Algorithm to fill from top down and left to right

public class Solution1 implements SudokuSolver {

    private static class Position {
        public final int index, row, col, block;
        public Position(int index) {
            this.index = index;
            row = index / 9;
            col = index % 9;
            block = row / 3 * 3 + col / 3;
        }
    }

    private static final Position[] FLAT = new Position[9 * 9];
    static { for (int i = 0; i < 9 * 9; i++) FLAT[i] = new Position(i); }

    private char[][] board;
    private boolean[][] rows;
    private boolean[][] cols;
    private boolean[][] blocks;

    public void solveSudoku(char[][] board) {
        init(board);
        solve(next(0));
    }

    private Position next(int index) {
        for (; index < 9 * 9; index++) {
            var d = FLAT[index];
            if (board[d.row][d.col] == '.') return d;
        }
        return null;
    }

    private boolean solve(Position d) {
        for (int i = 0; i < 9; i++) {
            if (blocks[d.block][i] || rows[d.row][i] || cols[d.col][i]) continue;
            board[d.row][d.col] = (char) ('1' + i);
            rows[d.row][i] = cols[d.col][i] = blocks[d.block][i] = true;
            var next = next(d.index + 1);
            if (next == null || solve(next)) return true;
            rows[d.row][i] = cols[d.col][i] = blocks[d.block][i] = false;
            board[d.row][d.col] = '.';
        }
        return false;
    }

    private void init(char[][] board) {
        this.board = board;
        rows = new boolean[9][9];
        cols = new boolean[9][9];
        blocks = new boolean[9][9];
        for (var d : FLAT) {
            char c = board[d.row][d.col];
            if (c == '.') continue;
            int val = c - '1';
            rows[d.row][val] = cols[d.col][val] = blocks[d.block][val] = true;
        }
    }
}
