package sudoku.commands;

import jguic.Command;

/**
 * Resalta una celda que fue rellenada por una pista.
 * Las celdas marcadas se mantienen resaltadas hasta nueva partida/reset.
 */
public class HintCellHighlightCommand implements Command {
    private final int row; // 1-based
    private final int col; // 1-based

    public HintCellHighlightCommand(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public void execute() throws Exception {
    }
}
