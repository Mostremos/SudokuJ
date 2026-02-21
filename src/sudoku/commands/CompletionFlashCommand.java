package sudoku.commands;

import java.util.HashSet;
import java.util.Set;
import jguic.Command;

/**
 * Comando para hacer flash visual en celdas completadas (fila, columna, cuadro).
 * Las coordenadas son 1-based (row, col) como en GUIGridPCell.
 */
public class CompletionFlashCommand implements Command {
    private final Set<String> cells = new HashSet<>(); // "row,col"

    public void addCell(int row, int col) {
        cells.add(row + "," + col);
    }

    public boolean containsCell(int row, int col) {
        return cells.contains(row + "," + col);
    }

    @Override
    public void execute() throws Exception {
    }
}
