/*
 * Decompiled with CFR 0.152.
 */
package sudoku.core;

import sudoku.core.Cell;

public class PlayedCell
extends Cell {
    private boolean[] possibilities;

    public PlayedCell() {
        this.init();
    }

    public PlayedCell(int value) {
        this.init();
        this.setValue(value);
    }

    private void init() {
        this.value = 0;
        this.possibilities = new boolean[9];
        int i = 0;
        while (i < 9) {
            this.possibilities[i] = false;
            i = (short)(i + 1);
        }
    }

    public void setPossibility(int figure, boolean active) {
        if (figure < 10 && figure > 0) {
            this.possibilities[figure - 1] = active;
        }
    }

    public boolean getPossibility(int figure) {
        return figure < 10 && figure > 0 && this.possibilities[figure - 1];
    }

    public boolean[] getPossibilities() {
        return this.possibilities;
    }

    public Cell clone() {
        return new PlayedCell(this.value);
    }
}

