/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.util.CannotRedoException;
import jguic.util.CannotUndoException;
import jguic.util.UndoableCommand;

public abstract class DefaultSudokuCommand
implements UndoableCommand {
    public boolean canRedo() {
        return true;
    }

    public boolean canUndo() {
        return true;
    }

    public boolean canReplace(UndoableCommand c) {
        return false;
    }

    public void execute() throws Exception {
    }

    public void redo() throws CannotRedoException {
        this.switchVars();
    }

    public void undo() throws CannotUndoException {
        this.switchVars();
    }

    protected abstract void switchVars();
}

