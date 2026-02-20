/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import javax.swing.JToolBar;
import jguic.Mediator;
import jguic.MediatorExtension;

public abstract class GUIToolbar
extends MediatorExtension {
    public GUIToolbar(Mediator parent) {
        super(parent);
    }

    public abstract JToolBar getJToolBar();
}

