/*
 * Decompiled with CFR 0.152.
 */
package jguic.util;

import jguic.Command;
import jguic.Mediator;
import jguic.util.DesactivateRedo;
import jguic.util.DesactivateUndo;
import jguic.util.Redo;
import jguic.util.Undo;
import jguic.util.UndoManager;
import jguic.util.UndoableCommand;

public abstract class MainMediator
extends Mediator {
    protected UndoManager undoManager = new UndoManager(100);

    protected void handle(Command command) {
        if (command instanceof Undo) {
            try {
                UndoableCommand undoC = this.undoManager.undo();
                this.undoOn(undoC);
                this.sendCommand(undoC);
            }
            catch (Exception e) {
                this.undoError(e, this.undoManager.getCommand());
            }
            if (!this.undoManager.canUndo()) {
                this.sendCommand(new DesactivateUndo());
            }
        } else if (command instanceof Redo) {
            try {
                UndoableCommand redoC = this.undoManager.redo();
                this.redoOn(redoC);
                this.sendCommand(redoC);
            }
            catch (Exception e) {
                this.redoError(e, this.undoManager.getCommand());
            }
            if (!this.undoManager.canRedo()) {
                this.sendCommand(new DesactivateRedo());
            }
        } else {
            try {
                if (this.prepare(command)) {
                    command.execute();
                    if (command instanceof UndoableCommand) {
                        this.undoManager.push((UndoableCommand)command);
                    }
                    this.executed(command);
                    this.sendCommand(command);
                    if (command instanceof UndoableCommand) {
                        this.sendCommand(new DesactivateRedo());
                    }
                }
            }
            catch (Exception e) {
                this.executionError(e, command);
            }
        }
    }

    protected boolean prepare(Command command) {
        return true;
    }

    protected void executionError(Exception e, Command c) {
    }

    protected void undoError(Exception e, UndoableCommand c) {
    }

    protected void redoError(Exception e, UndoableCommand c) {
    }

    protected void executed(Command command) {
    }

    protected void undoOn(UndoableCommand command) {
    }

    protected void redoOn(UndoableCommand command) {
    }
}

