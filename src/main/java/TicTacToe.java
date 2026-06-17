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

            // prüfen, ob der Zug innerhalb des Spielfelds liegt.
            if(row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Ungültiger Zug");
                continue;
            }

            // prüfen, ob das ausgewählte Feld leer ist.
            if(!board.isCellEmpty(row, col)) {
                System.out.println("Ungültiger Zug");
                continue;
            }

            // prüfen, ob das Symbol des aktuellen Spielers platziert wird.
            board.place(row, col, currentPlayer.getMarker());

            // prüfen, ob der aktuelle Spieler nach dem Zug gewonnen hat.
            if(hasWinner()) {
                board.print();
                System.out.println("Spieler " + currentPlayer.getMarker() + " hat gewonnen!");
                break;
            }

            // prüfen, ob das Spielfeld voll ist und das Spiel unentschieden endet.
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