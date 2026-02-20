/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import sudoku.commands.DefaultSudokuCommand;

public class SetPossibilityCommand
extends DefaultSudokuCommand {
    private int cell_x;
    private int cell_y;
    private int possibility;
    private boolean active;
    private int old_value;
    private int new_value;

    public SetPossibilityCommand(int cx, int cy) {
        this.construct();
        this.cell_x = cx;
        this.cell_y = cy;
    }

    public SetPossibilityCommand() {
        this.construct();
    }

    private void construct() {
        this.cell_x = 0;
        this.cell_y = 0;
        this.possibility = 0;
        this.active = false;
        this.old_value = 0;
        this.new_value = 0;
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

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPossibility() {
        return this.possibility;
    }

    public void setPossibility(int p) {
        this.possibility = p;
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

    protected void switchVars() {
        this.active = !this.active;
        int temp = this.old_value;
        this.old_value = this.new_value;
        this.new_value = temp;
    }
}

