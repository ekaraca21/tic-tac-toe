import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestBoard {

    @Test
    void testGueltigerZug() {
        Board board = new Board();

        // prüfen, ob ein Spieler ein leeres Feld auswählen kann.
        assertTrue(board.isCellEmpty(1, 1), "Das Feld sollte leer sein.");

        // prüfen, ob das Symbol auf dem ausgewählten Feld gespeichert wird.
        board.place(1, 1, 'X');

        // prüfen, ob das X auf dem Feld gespeichert wurde.
        assertEquals('X', board.getSymbol(1, 1), "Auf dem Feld (1,1) sollte ein X stehen.");
    }

    @Test
    void testFeldMussLeerSein() {
        Board board = new Board();

        // prüfen, ob ein Symbol auf einem leeren Feld gespeichert werden kann.
        board.place(0, 0, 'X');

        // prüfen, ob das Feld nach dem Platzieren nicht mehr leer ist.
        assertFalse(board.isCellEmpty(0, 0), "Ein belegtes Feld darf nicht als leer erkannt werden.");

        // prüfen, ob das ursprüngliche Symbol gespeichert bleibt.
        assertEquals('X', board.getSymbol(0, 0), "Das X sollte auf dem Feld bleiben.");
    }

    @Test
    void testInitialBoardIstLeer() {
        Board board = new Board();

        // prüfen, ob alle Felder am Anfang leer sind.
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

        // prüfen, ob der erste Zug im Spielfeld gespeichert wird.
        board.place(0, 0, 'X');
        assertEquals('X', board.getSymbol(0, 0), "Feld (0,0) sollte ein X enthalten.");

        // prüfen, ob der zweite Zug im Spielfeld gespeichert wird.
        board.place(1, 1, 'O');
        assertEquals('O', board.getSymbol(1, 1), "Feld (1,1) sollte ein O enthalten.");

        // prüfen, ob der erste Zug nach dem zweiten Zug weiterhin vorhanden ist.
        assertEquals('X', board.getSymbol(0, 0), "Das X sollte nach dem zweiten Zug immer noch sichtbar sein.");
    }

    @Test
    void testUnentschiedenWennBoardVollUndKeinGewinner() {
        Board board = new Board();

        // prüfen, ob ein volles Spielfeld erkannt wird.
        board.place(0, 0, 'X');
        board.place(0, 1, 'O');
        board.place(0, 2, 'X');

        board.place(1, 0, 'X');
        board.place(1, 1, 'O');
        board.place(1, 2, 'O');

        board.place(2, 0, 'O');
        board.place(2, 1, 'X');
        board.place(2, 2, 'X');

        assertTrue(board.isFull(), "Das Spielfeld sollte voll sein.");
    }

    @Test
    void testBoardIstNichtVoll() {
        Board board = new Board();

        // prüfen, ob ein leeres Spielfeld nicht als voll erkannt wird.
        assertFalse(board.isFull(), "Ein neues Spielfeld sollte nicht voll sein.");

        // prüfen, ob ein teilweise gefülltes Spielfeld nicht als voll erkannt wird.
        board.place(0, 0, 'X');
        assertFalse(board.isFull(), "Ein teilweise gefülltes Spielfeld sollte nicht voll sein.");
    }

    @Test
    void testClearLeertDasSpielfeld() {
        Board board = new Board();

        // prüfen, ob clear alle gesetzten Symbole entfernt.
        board.place(0, 0, 'X');
        board.place(1, 1, 'O');

        board.clear();

        assertEquals(' ', board.getSymbol(0, 0), "Das Feld sollte nach clear leer sein.");
        assertEquals(' ', board.getSymbol(1, 1), "Das Feld sollte nach clear leer sein.");
    }
}
