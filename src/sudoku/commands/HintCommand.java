package sudoku.commands;

import jguic.Command;
import sudoku.core.Grid;

/**
 * Comando para mostrar una pista (una jugada correcta posible).
 */
public class HintCommand implements Command {
    private int[] hint = null; // {x, y, value} o null

    public int[] getHint() {
        return hint;
    }

    public void setHint(int[] hint) {
        this.hint = hint;
    }

    public boolean hasHint() {
        return hint != null;
    }

    @Override
    public void execute() throws Exception {
    }
}
