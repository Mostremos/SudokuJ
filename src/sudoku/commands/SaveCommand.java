/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.Command;
import sudoku.gui.UserData;

public class SaveCommand
implements Command {
    private UserData userdata;
    private int mode;
    private String filename;
    private boolean saved;

    public SaveCommand(int mode) {
        this.construct();
        this.mode = mode;
    }

    public SaveCommand() {
        this.construct();
    }

    private void construct() {
        this.mode = 1;
        this.filename = "";
        this.saved = false;
        this.userdata = null;
    }

    public boolean isSaved() {
        return this.saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public UserData getUserData() {
        return this.userdata;
    }

    public void setUserData(UserData userdata) {
        this.userdata = userdata;
    }

    public String getFileName() {
        return this.filename;
    }

    public void setFileName(String filename) {
        this.filename = filename;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void execute() throws Exception {
    }
}

