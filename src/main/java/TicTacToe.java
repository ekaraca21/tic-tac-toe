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

        // äußere Schleife: ein Durchlauf = ein komplettes Spiel.
        while(true) {

            boolean gameOver = false;

            // inneres Spiel: läuft, bis jemand gewinnt oder es unentschieden steht.
            while(!gameOver) {
                System.out.println("Current Player: " + currentPlayer.getMarker());
                board.print();

                System.out.print("row (0-2): ");
                int row = scanner.nextInt();

                System.out.print("column (0-2): ");
                int col = scanner.nextInt();

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

                // Symbol des aktuellen Spielers platzieren.
                board.place(row, col, currentPlayer.getMarker());

                // prüfen, ob der aktuelle Spieler gewonnen hat.
                if(hasWinner()) {
                    board.print();
                    System.out.println("Spieler " + currentPlayer.getMarker() + " hat gewonnen!");
                    gameOver = true;
                    continue;
                }

                // prüfen, ob das Spielfeld voll ist (Unentschieden).
                if(board.isFull()) {
                    board.print();
                    System.out.println("Unentschieden!");
                    gameOver = true;
                    continue;
                }

                switchCurrentPlayer();
            }

            // Spiel ist beendet – fragen, ob nochmal gespielt wird.
            System.out.print("Nochmal spielen? (j/n): ");

            // keine weitere Eingabe vorhanden -> Spiel beenden (verhindert Absturz).
            if(!scanner.hasNext()) {
                break;
            }

            String answer = scanner.next();

            if(answer.equalsIgnoreCase("j")) {
                board.clear();              // Spielfeld leeren
                currentPlayer = player1;    // neues Spiel beginnt wieder mit X
            } else {
                System.out.println("Spiel beendet. Danke fürs Spielen!");
                break;
            }
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
            if(board.getSymbol(i, 0) == m && board.getSymbol(i, 1) == m && board.getSymbol(i, 2) == m) return true;
            if(board.getSymbol(0, i) == m && board.getSymbol(1, i) == m && board.getSymbol(2, i) == m) return true;
        }

        if(board.getSymbol(0, 0) == m && board.getSymbol(1, 1) == m && board.getSymbol(2, 2) == m) return true;
        if(board.getSymbol(0, 2) == m && board.getSymbol(1, 1) == m && board.getSymbol(2, 0) == m) return true;

        return false;
    }
}