/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.Command;

public class ShowSolutionCommand
implements Command {
    private boolean hasSolution = false;

    public boolean hasSolution() {
        return this.hasSolution;
    }

    public void setHasSolution(boolean hasSolution) {
        this.hasSolution = hasSolution;
    }

    public void execute() throws Exception {
    }
}

