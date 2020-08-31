public class Sudoku {
    public static int[][] GRID_PUZZLE = {
            {9,0,0,1,0,0,0,0,5},
            {0,0,5,0,9,0,2,0,1},
            {8,0,0,0,4,0,0,0,0},
            {0,0,0,0,8,0,0,0,0},
            {0,0,0,7,0,0,0,0,0},
            {0,0,0,0,2,6,0,0,9},
            {2,0,0,3,0,0,0,0,6},
            {0,0,0,2,0,0,9,0,0},
            {0,0,1,9,0,4,5,7,0},
    };
    private int[][] board;
    public static final int EMPTY = 0; // empty cell
    public static final int SIZE = 9; // size of our Sudoku grids

    public Sudoku(int[][] board) {
        this.board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }

    private boolean isInRow(int row, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num) {
                return true;
            }
        }
        return false;
    }
    private boolean isInColumn(int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num) {
                return true;
            }
        }
        return false;
    }
    private boolean isInBox(int row, int col, int num) {
        int r = row - row % 3;
        int c = col - col % 3;
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (board [i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isOk(int row, int col, int num) {
        return !isInRow(row, num) && !isInColumn(col, num) && !isInBox(row, col, num);
    }

    public boolean solve() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isOk(i, j, num)) {
                            board[i][j] = num;
                            if (solve()) {
                                return true;
                            } else {
                                board[i][j] = EMPTY;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public void display() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku(GRID_PUZZLE);
        System.out.println("Sudoku grid to solve");
        sudoku.display();
        System.out.println();

        if (sudoku.solve()) {
            sudoku.display();
        } else {
            System.out.println("Unsolvable");
        }
    }
}
