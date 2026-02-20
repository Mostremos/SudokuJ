/*
 * Decompiled with CFR 0.152.
 */
package sudoku.core;

import java.io.Serializable;
import sudoku.core.Grid;

public class UserData
implements Serializable {
    private Grid grid;
    private boolean[][][] possibilities;
    private Mode mode;

    public UserData() {
        this.grid = new Grid();
        this.possibilities = new boolean[9][9][9];
        this.mode = Mode.PLAYER;
    }

    public UserData(UserData userdata) {
        this.grid = new Grid(userdata.grid);
        this.possibilities = (boolean[][][])userdata.possibilities.clone();
        this.mode = userdata.mode;
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Mode getMode() {
        return this.mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public boolean[][][] getPossibilities() {
        return this.possibilities;
    }

    public void setPossibilities(boolean[][][] possibilities) {
        this.possibilities = possibilities;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum Mode {
        PLAYER,
        CREATOR;

    }
}

