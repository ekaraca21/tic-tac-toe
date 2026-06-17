import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestTicTacToe {
    @Test
    void testGewinnerInReihe() {
        String input = "0\n0\n1\n0\n0\n1\n1\n1\n0\n2\n";

        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

        System.setIn(testInput);
        System.setOut(new PrintStream(testOutput));

        TicTacToe game = new TicTacToe();
        game.start();

        String output = testOutput.toString();

        // prüfen, ob Spieler X durch drei gleiche Symbole in einer horizontalen Reihe gewinnt.
        assertTrue(output.contains("Spieler X hat gewonnen!"));
    }

    @Test
    void testSpielEndetMitUnentschieden() {
        String input = "0\n0\n0\n1\n0\n2\n1\n1\n1\n0\n1\n2\n2\n1\n2\n0\n2\n2\n";

        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

        System.setIn(testInput);
        System.setOut(new PrintStream(testOutput));

        TicTacToe game = new TicTacToe();
        game.start();

        String output = testOutput.toString();

        // prüfen, ob das Spiel bei vollem Spielfeld ohne Gewinner unentschieden endet.
        assertTrue(output.contains("Unentschieden!"));
    }
}

