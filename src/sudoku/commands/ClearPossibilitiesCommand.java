/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.util.UndoableCommand;
import sudoku.commands.DefaultSudokuCommand;

public class ClearPossibilitiesCommand
extends DefaultSudokuCommand {
    private int cell_x;
    private int cell_y;
    private boolean[] old_possibilities;
    private boolean[] new_possibilities;
    private int old_value;
    private int new_value;

    public ClearPossibilitiesCommand(int cx, int cy) {
        this.construct();
        this.cell_x = cx;
        this.cell_y = cy;
    }

    public ClearPossibilitiesCommand() {
        this.construct();
    }

    private void construct() {
        this.cell_x = 0;
        this.cell_y = 0;
        this.old_value = 0;
        this.new_value = 0;
        this.old_possibilities = new boolean[9];
        this.new_possibilities = new boolean[9];
        int i = 0;
        while (i < 9) {
            this.old_possibilities[i] = false;
            this.new_possibilities[i] = false;
            ++i;
        }
    }

    public void setPossibilities(boolean[] possibilities) {
        this.old_possibilities = (boolean[])possibilities.clone();
    }

    public boolean[] getPossibilities() {
        return this.new_possibilities;
    }

    public int getCellX() {
        return this.cell_x;
    }

    public int getCellY() {
        return this.cell_y;
    }

    public void setCellX(int x) {
        this.cell_x = x;
    }

    public void setCellY(int y) {
        this.cell_y = y;
    }

    public int getOldValue() {
        return this.old_value;
    }

    public int getNewValue() {
        return this.new_value;
    }

    public void setOldValue(int value) {
        this.old_value = value;
    }

    public void setNewValue(int value) {
        this.new_value = value;
    }

    public boolean canReplace(UndoableCommand command) {
        boolean replace = false;
        if (command instanceof ClearPossibilitiesCommand) {
            ClearPossibilitiesCommand c = (ClearPossibilitiesCommand)command;
            if (c.cell_x == this.cell_x && c.cell_y == this.cell_y) {
                replace = true;
                int i = 0;
                while (i < 9) {
                    replace &= this.new_possibilities[i] == c.new_possibilities[i];
                    ++i;
                }
                if (replace) {
                    i = 0;
                    while (i < 9) {
                        this.old_possibilities[i] = c.old_possibilities[i];
                        ++i;
                    }
                }
            }
        }
        return replace;
    }

    protected void switchVars() {
        boolean[] tmp = this.new_possibilities;
        this.new_possibilities = this.old_possibilities;
        this.old_possibilities = tmp;
        int temp = this.old_value;
        this.old_value = this.new_value;
        this.new_value = temp;
    }
}

