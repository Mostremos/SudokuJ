/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.Command;

public class PlayPauseCommand
implements Command {
    private boolean pause = false;

    public boolean isPaused() {
        return this.pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void execute() throws Exception {
    }
}

