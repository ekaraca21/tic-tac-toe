import java.util.Scanner;

public class TicTacToe {

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;

    public TicTacToe() {
        player1 = new Player('X');
        player2 = new Player('O');
        currentPlayer = player1;
        board = new Board();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Current Player: " + currentPlayer.getMarker());
            board.print();

            int row = getInput(scanner, "row (0-2): ");
            int col = getInput(scanner, "column (0-2): ");

            if(!board.isCellEmpty(row, col)) {
                System.out.println("Ungültiger Zug!");
                continue;
            }

            board.place(row, col, currentPlayer.getMarker());

            if(hasWinner()) {
                board.print();
                System.out.println("Spieler " + currentPlayer.getMarker() + " hat gewonnen!");
                break;
            }

            if(board.isFull()) {
                board.print();
                System.out.println("Unentschieden!");
                break;
            }

            switchCurrentPlayer();
        }
    }

    private void switchCurrentPlayer() {
        if(currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    private boolean hasWinner() {
        char m = currentPlayer.getMarker();

        for(int i = 0; i < 3; i++) {
            if(board.getCell(i, 0) == m && board.getCell(i, 1) == m && board.getCell(i, 2) == m) return true;
            if(board.getCell(0, i) == m && board.getCell(1, i) == m && board.getCell(2, i) == m) return true;
        }

        if(board.getCell(0, 0) == m && board.getCell(1, 1) == m && board.getCell(2, 2) == m) return true;
        if(board.getCell(0, 2) == m && board.getCell(1, 1) == m && board.getCell(2, 0) == m) return true;

        return false;
    }

    private int getInput(Scanner scanner, String prompt) {
        while(true) {
            System.out.print(prompt);
            if(scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Ungültige Eingabe! Bitte eine Zahl zwischen 0 und 2 eingeben.");
                scanner.next();
            }
        }
    }
}