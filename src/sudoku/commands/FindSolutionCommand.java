/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.Command;

public class FindSolutionCommand
implements Command {
    private boolean solution = false;

    public boolean hasSolution() {
        return this.solution;
    }

    public void setSolution(boolean solution) {
        this.solution = solution;
    }

    public void execute() throws Exception {
    }
}

