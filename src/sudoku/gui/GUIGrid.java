/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import javax.swing.JComponent;
import jguic.Mediator;
import jguic.MediatorExtension;

public abstract class GUIGrid
extends MediatorExtension {
    public GUIGrid(Mediator parent) {
        super(parent);
    }

    public abstract JComponent getJComponent();
}

