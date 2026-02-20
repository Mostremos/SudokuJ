/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.Command;

public class UnsetHighlightCommand
implements Command {
    private int figure;

    public UnsetHighlightCommand(int figure) {
        this.figure = figure;
    }

    public UnsetHighlightCommand() {
        this.figure = 0;
    }

    public int getFigure() {
        return this.figure;
    }

    public void setFigure(int figure) {
        this.figure = figure;
    }

    public void execute() throws Exception {
    }
}

