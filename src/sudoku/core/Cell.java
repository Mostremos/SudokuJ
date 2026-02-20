/*
 * Decompiled with CFR 0.152.
 */
package sudoku.core;

import java.io.Serializable;

public abstract class Cell
implements Serializable {
    protected int value;

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        if (value < 10 && value >= 0) {
            this.value = value;
        }
    }

    public abstract Cell clone();
}

