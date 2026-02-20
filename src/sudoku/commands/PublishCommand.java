/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.Command;
import sudoku.gui.UserData;

public class PublishCommand
implements Command {
    private UserData userdata = null;
    private String filename = "";
    private boolean valid = false;
    private boolean published = false;

    public boolean isPublished() {
        return this.published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isValid() {
        return this.valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
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

    public void execute() throws Exception {
    }
}

