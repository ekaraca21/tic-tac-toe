public class Board {
    private char[][] cells = new char[3][3];

    public Board() {
        clear();
    }

    // prüfen, ob das ausgewählte Feld leer ist.
    public boolean isCellEmpty(int x, int y) {
        return cells[x][y] == ' ';
    }

    // prüfen, ob ein Symbol auf dem ausgewählten Feld platziert werden kann.
    public void place(int x, int y, char marker) {
        cells[x][y] = marker;
    }

    // prüfen, ob das Spielfeld vollständig gefüllt ist.
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

    // prüfen, ob das Spielfeld zurückgesetzt und alle Felder geleert werden.
    public void clear() {
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                cells[row][col] = ' ';
            }
        }
    }

    // prüfen, ob das Spielfeld in der Konsole angezeigt wird.
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

    // prüfen, welches Symbol an einer bestimmten Position gespeichert ist.
    // Diese Methode ist zusätzlich für Unit Tests notwendig.
    public char getSymbol(int row, int col) {
        return cells[row][col];
    }
}