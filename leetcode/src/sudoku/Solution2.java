package sudoku;

// Algorithm to fill row, col, or block that already has the most digits filled out.
public class Solution2 implements Solution {

    private static class Position {
        public final int row, col, block, blockIndex;
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
            block = row / 3 * 3 + col / 3;
            blockIndex = row % 3 * 3 + col % 3;
        }
    }

    private static final Position[][] TABLE = new Position[9][9];
    private static final Position[][] BLOCKS = new Position[9][9];
    static {
        for (int row = 0; row < 9; row++) for (int col = 0; col < 9; col++) {
            var d = new Position(row, col);
            BLOCKS[d.block][d.blockIndex] = TABLE[d.row][d.col] = d;
        }
    }

    private char[][] board;
    private boolean[][] rows;
    private boolean[][] cols;
    private boolean[][] blocks;
    private int[] rowCount, colCount, blockCount;
    private int count;

    public void solveSudoku(char[][] board) {
        this.board = board;
        init(board);
        solve(next());
    }

    private Position next() {
        int index = 0, max = -1, dim = 0;
        for (int i = 0; i < 9; i++) {
            var c = blockCount[i];
            if (c < 9 && c > max) {
                max = c;
                index = i;
            }
        }
        for (int i = 0; i < 9; i++) {
            var c = colCount[i];
            if (c < 9 && c > max) {
                max = c;
                index = i;
                dim = 1;
            }
        }
        for (int i = 0; i < 9; i++) {
            var c = rowCount[i];
            if (c < 9 && c > max) {
                max = c;
                index = i;
                dim = 2;
            }
        }
        switch (dim) {
            case 0: return nextBlock(index);
            case 1: return nextCol(index);
            default: return nextRow(index);
        }
    }

    private Position nextBlock(int block) {
        int blockIndex = 0;
        for (int max = -1, i = 0; i < 9; i++) {
            var d = BLOCKS[block][i];
            if (board[d.row][d.col] == '.') {
                 var c = colCount[d.col] + rowCount[d.row];
                 if (c > max) {
                     max = c;
                     blockIndex = i;
                 }
            }
        }
        return BLOCKS[block][blockIndex];
    }

    private Position nextRow(int row) {
        int col = 0;
        for (int max = -1, i = 0; i < 9; i++) {
            var d = TABLE[row][i];
            if (board[d.row][d.col] == '.') {
                var c = colCount[d.col] + blockCount[d.block];
                if (c > max) {
                    max = c;
                    col = i;
                }
            }
        }
        return TABLE[row][col];
    }

    private Position nextCol(int col) {
        int row = 0;
        for (int max = -1, i = 0; i < 9; i++) {
            var d = TABLE[i][col];
            if (board[d.row][d.col] == '.') {
                var c = rowCount[d.col] + blockCount[d.block];
                if (c > max) {
                    max = c;
                    row = i;
                }
            }
        }
        return TABLE[row][col];
    }

    private boolean solve(Position d) {
        for (int i = 0; i < 9; i++) {
            if (blocks[d.block][i] || rows[d.row][i] || cols[d.col][i]) continue;
            board[d.row][d.col] = (char) ('1' + i);
            mark(d, i);
            if (count == 9*9 || solve(next())) return true;
            unmark(d, i);
            board[d.row][d.col] = '.';
        }
        return false;
    }

    private void init(char[][] board) {
        rows = new boolean[9][9];
        cols = new boolean[9][9];
        blocks = new boolean[9][9];
        rowCount = new int[9];
        colCount = new int[9];
        blockCount = new int[9];
        count = 0;
        for (var row : TABLE) for (var d : row){
            char c = board[d.row][d.col];
            if (c == '.') continue;
            int val = c - '1';
            mark(d, val);
        }
    }

    private void unmark(Position d, int val) {
        rows[d.row][val] = cols[d.col][val] = blocks[d.block][val] = false;
        rowCount[d.row]--;
        colCount[d.col]--;
        blockCount[d.block]--;
        count--;
    }

    private void mark(Position d, int val) {
        rows[d.row][val] = cols[d.col][val] = blocks[d.block][val] = true;
        rowCount[d.row]++;
        colCount[d.col]++;
        blockCount[d.block]++;
        count++;
    }
}
