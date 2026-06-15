public class Board {

<<<<<<< HEAD
    private char[][] cells = new char[3][3];

    public Board() {
        clear();
    }

    public boolean isCellEmpty(int x, int y) {
        return cells[x][y] == ' ';
    }

    public void place(int x, int y, char marker) {
        cells[x][y] = marker;
    }

    public boolean isFull() {
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                if(cells[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void clear() {
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                cells[row][col] = ' ';
            }
        }
    }

    public void print() {
        for(int row = 0; row < 3; row++) {
            System.out.println("-------------");
            for(int col = 0; col < 3; col++) {
                System.out.print("| " + cells[row][col] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------");
    }

    public char getCell(int x, int y) {
        return cells[x][y];
    }
=======
    private char[][] board = new char[3][3];

    public Board() {

        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {

                board[row][col] = ' ';

            }
        }
    }

    public boolean makeMove(int row, int col, char symbol) {

        if(row < 0 || row > 2 || col < 0 || col > 2) {

            System.out.println("Ungültiger Zug");
            return false;
        }

        if(board[row][col] != ' ') {

            System.out.println("Ungültiger Zug");
            return false;
        }

        board[row][col] = symbol;
        return true;
    }

    public void printBoard() {

        for(int row = 0; row < 3; row++) {

            System.out.println("-------------");

            for(int col = 0; col < 3; col++) {

                System.out.print("| " + board[row][col] + " ");

            }

            System.out.println("|");
        }

        System.out.println("-------------");
    }

    public char getSymbol(int row, int col) {
        return board[row][col];
    }

>>>>>>> main
}