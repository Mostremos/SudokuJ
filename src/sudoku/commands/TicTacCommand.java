/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.Command;
import sudoku.gui.Clock;

public class TicTacCommand
implements Command {
    private Clock clock;

    public TicTacCommand(Clock clock) {
        this.clock = clock;
    }

    public TicTacCommand() {
        this.clock = new Clock();
    }

    public Clock getClock() {
        return this.clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public void execute() throws Exception {
    }
}

