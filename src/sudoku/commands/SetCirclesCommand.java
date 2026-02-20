/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.Command;

public class SetCirclesCommand
implements Command {
    private boolean circles;

    public SetCirclesCommand(boolean circles) {
        this.circles = circles;
    }

    public SetCirclesCommand() {
        this.circles = false;
    }

    public boolean isCircles() {
        return this.circles;
    }

    public void setCircles(boolean circles) {
        this.circles = circles;
    }

    public void execute() throws Exception {
    }
}

