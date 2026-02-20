/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.Command;
import sudoku.core.Grid;

public class CheckSolutionCommand
implements Command {
    private boolean gridfull = false;
    private boolean valid = false;
    private Grid grid;

    public void execute() throws Exception {
    }

    public boolean isValid() {
        return this.valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isGridFull() {
        return this.gridfull;
    }

    public void setGridFull(boolean gridfull) {
        this.gridfull = gridfull;
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}

