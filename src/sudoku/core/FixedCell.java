/*
 * Decompiled with CFR 0.152.
 */
package sudoku.core;

import sudoku.core.Cell;

public class FixedCell
extends Cell {
    public FixedCell(int value) {
        this.value = value;
    }

    public void setValue(int value) {
    }

    public Cell clone() {
        return new FixedCell(this.value);
    }
}

