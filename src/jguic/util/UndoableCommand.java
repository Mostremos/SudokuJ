/*
 * Decompiled with CFR 0.152.
 */
package jguic.util;

import jguic.Command;
import jguic.util.CannotRedoException;
import jguic.util.CannotUndoException;

public interface UndoableCommand
extends Command {
    public boolean canUndo();

    public boolean canRedo();

    public void undo() throws CannotUndoException;

    public void redo() throws CannotRedoException;

    public boolean canReplace(UndoableCommand var1);
}

