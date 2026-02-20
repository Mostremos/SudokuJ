/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import java.awt.Color;

public class HighlightManager {
    private Color[] colors = new Color[10];
    private boolean[] free;
    private final int MAX = 10;

    public HighlightManager() {
        this.colors[0] = new Color(253, 210, 200);
        this.colors[1] = new Color(215, 254, 188);
        this.colors[2] = new Color(210, 244, 255);
        this.colors[3] = new Color(255, 215, 241);
        this.colors[4] = new Color(253, 255, 215);
        this.colors[5] = new Color(230, 225, 255);
        this.colors[6] = new Color(233, 233, 233);
        this.colors[7] = new Color(242, 228, 187);
        this.colors[8] = new Color(209, 250, 227);
        this.colors[9] = new Color(255, 236, 217);
        this.free = new boolean[10];
        int j = 0;
        while (j < 10) {
            this.free[j] = true;
            j = (short)(j + 1);
        }
    }

    public synchronized Color lockColor() {
        int i = 0;
        while (i < 10) {
            if (this.free[i]) {
                this.free[i] = false;
                return this.colors[i];
            }
            i = (short)(i + 1);
        }
        return null;
    }

    public void unlockColor(Color c) {
        int i = 0;
        while (i < 10) {
            if (this.colors[i].equals(c)) {
                this.free[i] = true;
            }
            i = (short)(i + 1);
        }
    }
}

