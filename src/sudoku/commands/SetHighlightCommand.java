/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import java.awt.Color;
import jguic.Command;

public class SetHighlightCommand
implements Command {
    private int figure;
    private Color color;

    public SetHighlightCommand(int figure) {
        this.figure = figure;
    }

    public int getFigure() {
        return this.figure;
    }

    public void setFigure(int figure) {
        this.figure = figure;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void execute() throws Exception {
    }
}

