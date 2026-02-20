/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import sudoku.commands.DefaultSudokuCommand;
import sudoku.gui.UserData;

public class SetUserDataCommand
extends DefaultSudokuCommand {
    private UserData old_userdata = null;
    private UserData new_userdata = null;

    public UserData getUserData() {
        return this.new_userdata;
    }

    public void setNewUserData(UserData userdata) {
        this.new_userdata = userdata;
    }

    public void setOldUserData(UserData userdata) {
        this.old_userdata = userdata;
    }

    protected void switchVars() {
        UserData temp = this.old_userdata;
        this.old_userdata = this.new_userdata;
        this.new_userdata = temp;
    }
}

