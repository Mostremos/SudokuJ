/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.Command;
import sudoku.gui.UserData;

public class LoadCommand
implements Command {
    private UserData old_userdata;
    private UserData new_userdata;
    private int mode;
    private String filename;
    private boolean load;
    private boolean valid;

    public LoadCommand(int mode) {
        this.construct();
        this.mode = mode;
    }

    public LoadCommand() {
        this.construct();
    }

    private void construct() {
        this.old_userdata = null;
        this.new_userdata = null;
        this.mode = 0;
        this.filename = "";
        this.load = false;
        this.valid = false;
    }

    public boolean isLoad() {
        return this.load;
    }

    public void setLoad(boolean load) {
        this.load = load;
    }

    public boolean isValid() {
        return this.valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
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

    public UserData getNewUserData() {
        return this.new_userdata;
    }

    public void setNewUserData(UserData userdata) {
        this.new_userdata = userdata;
    }

    public UserData getOldUserData() {
        return this.old_userdata;
    }

    public void setOldUserData(UserData userdata) {
        this.old_userdata = userdata;
    }

    public void execute() throws Exception {
    }
}

