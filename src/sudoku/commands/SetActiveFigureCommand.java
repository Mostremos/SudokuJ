/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.Command;

public class SetActiveFigureCommand
implements Command {
    private int figure;

    public SetActiveFigureCommand(int figure) {
        this.figure = figure;
    }

    public SetActiveFigureCommand() {
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

