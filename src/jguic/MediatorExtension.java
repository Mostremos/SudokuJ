/*
 * Decompiled with CFR 0.152.
 */
package jguic;

import java.util.Observable;
import java.util.Observer;
import jguic.Command;
import jguic.Mediator;

public abstract class MediatorExtension
extends Mediator
implements Observer {
    protected Mediator parent;

    public MediatorExtension(Mediator parent) {
        this.parent = parent;
        this.parent.addExtension(this);
    }

    public void update(Observable obs, Object o) {
        if (obs instanceof Mediator) {
            if (o instanceof Command) {
                this.receiveCommand((Mediator)obs, (Command)o);
            } else {
                this.receive((Mediator)obs, o);
            }
        }
    }

    public void receive(Mediator mediator, Object o) {
        this.send(o);
    }

    public void receiveCommand(Mediator mediator, Command command) {
        this.sendCommand(command);
    }

    public void handle(Command command) {
        this.parent.handle(command);
    }
}

