import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;


class TestBoard {

    @Test
    void testGueltigerZug() {
        Board board = new Board();

        // prüfen, ob Spieler das Feld auswählen kann & X/O erscheint
        boolean successfulMove = board.makeMove(1, 1, 'X');

        // prüfen, ob die Methode 'true' (Erfolg) zurückgibt
        assertTrue(successfulMove, "Der Zug sollte erfolgreich sein.");

        // prüfen, ob das 'X' auf dem Feld gespeichert wurde
        assertEquals('X', board.getSymbol(1, 1), "Auf dem Feld (1,1) sollte ein X stehen.");
    }

    @Test
    void testFeldMussLeerSein() {
        Board board = new Board();

        // 'X' auf Feld (0,0) setzen
        board.makeMove(0, 0, 'X');

        //  prüfen, ob ungültige Züge abgelehnt werden
        boolean secondMove = board.makeMove(0, 0, 'O');

        //  prüfen, ob der zweite Zug mit 'false' abgelehnt wurde
        assertFalse(secondMove, "Ein Zug auf ein belegtes Feld muss abgelehnt werden.");

        // prüfen ob, dass das ursprüngliche 'X' nicht überschrieben wurde
        assertEquals('X', board.getSymbol(0, 0), "Das X darf nicht vom O überschrieben werden.");
    }

    @Test
    void testUngueltigeZuegeAusserhalb() {
        Board board = new Board();

        // prüfen, ungültige Züge werden abgelehnt
        assertFalse(board.makeMove(-1, 0, 'X'), "Negative Zeilen müssen abgelehnt werden.");
        assertFalse(board.makeMove(0, 3, 'O'), "Spalten größer als 2 müssen abgelehnt werden.");
        assertFalse(board.makeMove(3, 3, 'X'), "Zeilen und Spalten größer als 2 müssen abgelehnt werden.");
    }

    @Test
    void testInitialBoardIstLeer() {
        Board board = new Board();
        // prüfen, ob alle Felder am Anfang leer sind
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                assertEquals(' ', board.getSymbol(row, col),
                        "Feld (" + row + "," + col + ") sollte leer sein.");
            }
        }
    }

    @Test
    void testSpielstandAktualisiertSichNachZug() {
        Board board = new Board();

        // Erster Zug
        board.makeMove(0, 0, 'X');
        assertEquals('X', board.getSymbol(0, 0), "Feld (0,0) sollte ein 'X' enthalten.");

        // Zweiter Zug
        board.makeMove(1, 1, 'O');
        assertEquals('O', board.getSymbol(1, 1), "Feld (1,1) sollte ein 'O' enthalten.");

        // prüfen, ob das 'X' aus dem ersten Zug immer noch da ist
        assertEquals('X', board.getSymbol(0, 0), "Das 'X' sollte nach dem zweiten Zug immer noch sichtbar sein.");
    }

}