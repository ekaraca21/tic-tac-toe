import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestTicTacToe {
    @Test
    void testGewinnerInReihe() {
        String input = "0\n0\n1\n0\n0\n1\n1\n1\n0\n2\nn\n";

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
    void testGewinnerInSpalte() {
        String input = "0\n0\n0\n1\n1\n0\n1\n1\n2\n0\nn\n";

        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

        System.setIn(testInput);
        System.setOut(new PrintStream(testOutput));

        TicTacToe game = new TicTacToe();
        game.start();

        String output = testOutput.toString();

        // prüfen, ob Spieler X durch drei gleiche Symbole in einer vertikalen Spalte gewinnt.
        assertTrue(output.contains("Spieler X hat gewonnen!"));
    }

    @Test
    void testGewinnerDiagonal() {
        String input = "0\n0\n0\n1\n1\n1\n0\n2\n2\n2\nn\n";

        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

        System.setIn(testInput);
        System.setOut(new PrintStream(testOutput));

        TicTacToe game = new TicTacToe();
        game.start();

        String output = testOutput.toString();

        // prüfen, ob Spieler X durch drei gleiche Symbole in einer Diagonale gewinnt.
        assertTrue(output.contains("Spieler X hat gewonnen!"));
    }

    @Test
    void testSpielEndetMitUnentschieden() {
        String input = "0\n0\n0\n1\n0\n2\n1\n1\n1\n0\n1\n2\n2\n1\n2\n0\n2\n2\nn\n";

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

    @Test
    void testUngueltigerZugWirdAbgelehnt() {
        String input = "3\n0\n0\n0\n1\n0\n0\n1\n1\n1\n0\n2\nn\n";

        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

        System.setIn(testInput);
        System.setOut(new PrintStream(testOutput));

        TicTacToe game = new TicTacToe();
        game.start();

        String output = testOutput.toString();

        // prüfen, ob ein Zug außerhalb des Spielfelds abgelehnt wird.
        assertTrue(output.contains("Ungültiger Zug"));
    }

    @Test
    void testBelegtesFeldWirdAbgelehnt() {
        String input = "0\n0\n0\n0\n1\n0\n0\n1\n1\n1\n0\n2\nn\n";

        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

        System.setIn(testInput);
        System.setOut(new PrintStream(testOutput));

        TicTacToe game = new TicTacToe();
        game.start();

        String output = testOutput.toString();

        // prüfen, ob ein Zug auf ein bereits belegtes Feld abgelehnt wird.
        assertTrue(output.contains("Ungültiger Zug"));
    }

    @Test
    void testNachSiegWirdNachNeuemSpielGefragt() {
        String input = "0\n0\n1\n0\n0\n1\n1\n1\n0\n2\nn\n";

        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

        System.setIn(testInput);
        System.setOut(new PrintStream(testOutput));

        TicTacToe game = new TicTacToe();
        game.start();

        String output = testOutput.toString();

        // prüfen, ob nach einem Sieg gefragt wird, ob erneut gespielt werden soll.
        assertTrue(output.contains("Nochmal spielen? (j/n):"));
    }

    @Test
    void testSpielWirdBeiAblehnungBeendet() {
        String input = "0\n0\n1\n0\n0\n1\n1\n1\n0\n2\nn\n";

        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

        System.setIn(testInput);
        System.setOut(new PrintStream(testOutput));

        TicTacToe game = new TicTacToe();
        game.start();

        String output = testOutput.toString();

        // prüfen, ob das Spiel bei Eingabe von n beendet wird.
        assertTrue(output.contains("Spiel beendet. Danke fürs Spielen!"));
    }

    @Test
    void testNeuesSpielStartetWiederMitSpielerX() {
        String input =
                "0\n0\n1\n0\n0\n1\n1\n1\n0\n2\n" +   // erstes Spiel: X gewinnt
                        "j\n" +                               // nochmal spielen
                        "0\n0\n1\n0\n0\n1\n1\n1\n0\n2\n" +   // zweites Spiel: X gewinnt wieder
                        "n\n";                                // beenden

        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

        System.setIn(testInput);
        System.setOut(new PrintStream(testOutput));

        TicTacToe game = new TicTacToe();
        game.start();

        String output = testOutput.toString();

        // prüfen, ob nach Bestätigung ein neues Spiel wieder mit Spieler X startet.
        assertTrue(output.contains("Nochmal spielen? (j/n):"));
        int occurrences = output.split("Current Player: X", -1).length - 1;
        assertTrue(occurrences >= 2, "Nach dem Neustart sollte wieder Spieler X beginnen.");
    }

    @Test
    void testNeuesSpielHatKeineAltenZuege() {
        String input =
                "0\n0\n1\n0\n0\n1\n1\n1\n0\n2\n" +   // erstes Spiel: X gewinnt
                        "j\n" +                               // nochmal spielen
                        "0\n0\n1\n0\n0\n1\n1\n1\n0\n2\n" +   // zweites Spiel nutzt wieder Feld 0,0
                        "n\n";

        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

        System.setIn(testInput);
        System.setOut(new PrintStream(testOutput));

        TicTacToe game = new TicTacToe();
        game.start();

        String output = testOutput.toString();

        // prüfen, ob alte Züge im neuen Spiel entfernt wurden.
        assertFalse(output.contains("Ungültiger Zug"), "Das neue Spiel sollte keine alten belegten Felder enthalten.");

        int wins = output.split("Spieler X hat gewonnen!", -1).length - 1;
        assertEquals(2, wins, "Spieler X sollte in beiden Spielen gewinnen.");
    }

    @Test
    void testUngueltigeEingabeBeiNeustartFrageFuehrtNichtZumAbsturz() {
        String input = "0\n0\n1\n0\n0\n1\n1\n1\n0\n2\nabc\n";

        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

        System.setIn(testInput);
        System.setOut(new PrintStream(testOutput));

        TicTacToe game = new TicTacToe();
        game.start();

        String output = testOutput.toString();

        // prüfen, ob eine ungültige Eingabe bei der Neustart-Frage nicht zum Absturz führt.
        assertTrue(output.contains("Spiel beendet. Danke fürs Spielen!"));
    }
}