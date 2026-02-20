/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.util.UndoableCommand;
import sudoku.commands.DefaultSudokuCommand;

public class SetValueCommand
extends DefaultSudokuCommand {
    private int cell_x;
    private int cell_y;
    private int old_value;
    private int new_value;

    public SetValueCommand(int cx, int cy) {
        this.construct();
        this.cell_x = cx;
        this.cell_y = cy;
    }

    public SetValueCommand() {
        this.construct();
    }

    private void construct() {
        this.cell_x = 0;
        this.cell_y = 0;
        this.old_value = 0;
        this.new_value = 0;
    }

    public int getCellX() {
        return this.cell_x;
    }

    public int getCellY() {
        return this.cell_y;
    }

    public int getOldValue() {
        return this.old_value;
    }

    public int getNewValue() {
        return this.new_value;
    }

    public void setCellX(int x) {
        this.cell_x = x;
    }

    public void setCellY(int y) {
        this.cell_y = y;
    }

    public void setOldValue(int value) {
        this.old_value = value;
    }

    public void setNewValue(int value) {
        this.new_value = value;
    }

    public boolean canReplace(UndoableCommand command) {
        boolean replace = false;
        if (command instanceof SetValueCommand) {
            SetValueCommand c = (SetValueCommand)command;
            if (c.cell_x == this.cell_x && c.cell_y == this.cell_y && this.new_value == c.new_value) {
                this.old_value = c.old_value;
                replace = true;
            }
        }
        return replace;
    }

    protected void switchVars() {
        int temp = this.old_value;
        this.old_value = this.new_value;
        this.new_value = temp;
    }
}

