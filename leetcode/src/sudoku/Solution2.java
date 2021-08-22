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

    private static final int BLOCK = 0, ROW = 1, COL = 2;
    private static final Position[][][] POS = new Position[3][9][9];
    static {
        for (int row = 0; row < 9; row++) for (int col = 0; col < 9; col++) {
            var d = new Position(row, col);
            POS[BLOCK][d.block][d.blockIndex] = POS[ROW][d.row][d.col] = POS[COL][d.col][d.row] = d;
        }
    }

    private static class Sudoku {
        char[][] board;
        private int[][] count = new int[3][9];
        private boolean[][][] used = new boolean[3][9][9];
        private int counter = 0;

        Sudoku(char[][] board) {
            this.board = board;
            for (var row : POS[ROW]) for (var d : row){
                char c = board[d.row][d.col];
                if (c == '.') continue;
                mark(d, c - '1');
            }
        }

        void unmark(Position p, int n) {
            used[ROW][p.row][n] = used[COL][p.col][n] = used[BLOCK][p.block][n] = false;
            count[ROW][p.row]--; count[COL][p.col]--; count[BLOCK][p.block]--;
            counter--;
        }

        void mark(Position p, int n) {
            used[ROW][p.row][n] = used[COL][p.col][n] = used[BLOCK][p.block][n] = true;
            count[ROW][p.row]++; count[COL][p.col]++; count[BLOCK][p.block]++;
            counter++;
        }

        Position next() {
            int dim = BLOCK, x = 0;
            for (int max = -1, d = 0; d < 3; d++) for (int i = 0; i < 9; i++) {
                var c = count[d][i];
                if (c < 9 && c > max) {
                    max = c;
                    x = i;
                    dim = d;
                }
            }
            int y = 0;
            for (int max = -1, i = 0; i < 9; i++) {
                var d = POS[dim][x][i];
                if (board[d.row][d.col] == '.') {
                    var c = count[ROW][d.row] + count[COL][d.col] + count[BLOCK][d.block];
                    if (c > max) {
                        max = c;
                        y = i;
                    }
                }
            }
            return POS[dim][x][y];
        }

        boolean solve() {
            return solve(next());
        }

        boolean solve(Position p) {
            for (int n = 0; n < 9; n++) {
                if (used[BLOCK][p.block][n] || used[ROW][p.row][n] || used[COL][p.col][n]) continue;
                board[p.row][p.col] = (char) ('1' + n);
                mark(p, n);
                if (counter == 9 * 9 || solve(next())) return true;
                unmark(p, n);
                board[p.row][p.col] = '.';
            }
            return false;
        }
    }

    public void solveSudoku(char[][] board) {
        new Sudoku(board).solve();
    }

}
