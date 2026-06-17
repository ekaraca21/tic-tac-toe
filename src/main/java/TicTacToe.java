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
            board.printBoard();

            int row = getInput(scanner, "row (0-2): ");
            int col = getInput(scanner, "column (0-2): ");

            if(!board.makeMove(row, col, currentPlayer.getMarker())) {
                continue;
            }

            if(hasWinner()) {
                board.printBoard();
                System.out.println("Spieler " + currentPlayer.getMarker() + " hat gewonnen!");
                break;
            }

            if(board.isFull()) {
                board.printBoard();
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

    private boolean hasWinner() {
        char m = currentPlayer.getMarker();

        for(int i = 0; i < 3; i++) {
            // prüfen, ob der aktuelle Spieler eine vollständige horizontale Reihe hat.
            if(board.getSymbol(i, 0) == m && board.getSymbol(i, 1) == m && board.getSymbol(i, 2) == m) return true;

            // prüfen, ob der aktuelle Spieler eine vollständige vertikale Spalte hat.
            if(board.getSymbol(0, i) == m && board.getSymbol(1, i) == m && board.getSymbol(2, i) == m) return true;
        }


        // prüfen, ob der aktuelle Spieler die Diagonale von links oben nach rechts unten hat.
        if(board.getSymbol(0, 0) == m && board.getSymbol(1, 1) == m && board.getSymbol(2, 2) == m) return true;

        // prüfen, ob der aktuelle Spieler die Diagonale von rechts oben nach links unten hat.
        if(board.getSymbol(0, 2) == m && board.getSymbol(1, 1) == m && board.getSymbol(2, 0) == m) return true;

        // prüfen, ob kein Gewinn gefunden wurde.
        return false;
    }
}