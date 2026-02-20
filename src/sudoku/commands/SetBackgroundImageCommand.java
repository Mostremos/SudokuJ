/*
 * Decompiled with CFR 0.152.
 */
package sudoku.commands;

import jguic.Command;

public class SetBackgroundImageCommand
implements Command {
    private String imageName = "";

    public String getImageName() {
        return this.imageName;
    }

    public void setImageName(String name) {
        this.imageName = name;
    }

    public void execute() throws Exception {
    }
}

