/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.Command;
import sudoku.gui.UserData;

public class ResetCommand
implements Command {
    private UserData old_userdata = null;
    private UserData new_userdata = null;

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

