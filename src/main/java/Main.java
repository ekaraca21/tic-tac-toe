<<<<<<< HEAD
public class Main {

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.start();
=======
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Board board = new Board();
        Scanner scanner = new Scanner(System.in);

        char currentPlayer = 'X';

        while(true) {

            System.out.println("Current Player: " + currentPlayer);
            board.printBoard();

            int row = getInput(scanner, "row (0-2): ");
            int col = getInput(scanner, "column (0-2): ");

            boolean moved = board.makeMove(row, col, currentPlayer);

            if(!moved) {
                continue;
            }

            if(currentPlayer == 'X') {
                currentPlayer = 'O';
            } else {
                currentPlayer = 'X';
            }
        }
    }

    private static int getInput(Scanner scanner, String prompt) {

        while(true) {

            System.out.print(prompt);

            if(scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Ungültige Eingabe! Bitte eine Zahl zwischen 0 und 2 eingeben.");
                scanner.next();
            }
        }
>>>>>>> main
    }
}