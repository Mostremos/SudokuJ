/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import java.io.Serializable;

public class Clock
implements Serializable {
    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;

    public int getHours() {
        return this.hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void addSecond() {
        ++this.seconds;
        if (this.seconds == 60) {
            this.seconds = 0;
            ++this.minutes;
        }
        if (this.minutes == 60) {
            this.minutes = 0;
            ++this.hours;
        }
    }

    public void reset() {
        this.seconds = 0;
        this.minutes = 0;
        this.hours = 0;
    }
}

