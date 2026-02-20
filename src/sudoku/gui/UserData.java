/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import java.io.Serializable;
import sudoku.core.Difficulty;
import sudoku.core.Grid;
import sudoku.gui.Clock;

public class UserData
implements Serializable {
    public static final int PLAYER_MODE = 0;
    public static final int CREATOR_MODE = 1;
    private Grid grid;
    private int mode;
    private Clock clock;
    private boolean pause;
    private Difficulty difficulty;

    public UserData() {
        this.grid = new Grid();
        this.mode = 0;
        this.clock = new Clock();
        this.difficulty = Difficulty.EASY;
        this.pause = false;
    }

    public UserData(UserData userdata) {
        this.grid = new Grid(userdata.grid);
        this.mode = userdata.mode;
        this.clock = userdata.clock;
        this.difficulty = userdata.difficulty;
        this.pause = false;
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

    public Clock getClock() {
        return this.clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public boolean isPaused() {
        return this.pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}

