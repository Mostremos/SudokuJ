/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.Command;
import sudoku.core.Difficulty;
import sudoku.core.Grid;

public class NewGridCommand
implements Command {
    private Grid grid;
    private int mode;
    private Difficulty difficulty;
    private boolean isDifficultySet;

    public NewGridCommand(Grid grid, int mode) {
        this.construct();
        this.grid = grid;
        this.mode = mode;
    }

    public NewGridCommand() {
        this.construct();
    }

    private void construct() {
        this.grid = null;
        this.mode = 0;
        this.difficulty = Difficulty.EASY;
        this.isDifficultySet = false;
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.isDifficultySet = true;
    }

    public boolean isDifficultySet() {
        return this.isDifficultySet;
    }

    public void execute() throws Exception {
    }
}

