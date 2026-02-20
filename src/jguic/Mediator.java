/*
 * Decompiled with CFR 0.152.
 */
package jguic;

import java.util.Observable;
import jguic.Command;
import jguic.MediatorExtension;

public abstract class Mediator
extends Observable {
    public void addExtension(MediatorExtension ext) {
        this.addObserver(ext);
    }

    public void deleteExtension(MediatorExtension ext) {
        this.deleteObserver(ext);
    }

    protected final void send() {
        this.setChanged();
        this.notifyObservers();
    }

    protected final void send(Object o) {
        this.setChanged();
        this.notifyObservers(o);
    }

    protected final void sendCommand(Command c) {
        this.setChanged();
        this.notifyObservers(c);
    }

    protected abstract void handle(Command var1);
}

