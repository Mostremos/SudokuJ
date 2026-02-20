/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.Command;

public class ValidateGridCommand
implements Command {
    private boolean valid;

    public boolean isValid() {
        return this.valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void execute() throws Exception {
    }
}

