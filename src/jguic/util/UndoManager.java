/*
 * Decompiled with CFR 0.152.
 */
package jguic.util;

import java.util.LinkedList;
import jguic.util.CannotRedoException;
import jguic.util.CannotUndoException;
import jguic.util.EmptyUndoListException;
import jguic.util.FirstUndoException;
import jguic.util.LastRedoException;
import jguic.util.UndoableCommand;

public class UndoManager {
    private LinkedList<UndoableCommand> commands;
    private int index = 0;
    private int max;

    public UndoManager() {
        this.max = 20;
        this.commands = new LinkedList();
    }

    public UndoManager(int max) {
        if (max >= 0) {
            this.max = max;
        }
        this.commands = new LinkedList();
    }

    public void clear() {
        this.commands.clear();
        this.index = 0;
    }

    /**
     * Agrega un comando al gestor de deshacer/rehacer.
     * CORREGIDO: Código descompilado tenía GOTO inválidos.
     */
    public void push(UndoableCommand command) {
        // Si hay comandos y el nuevo puede reemplazar el último
        if (this.index > 0 && command.canReplace(this.getCommand())) {
            // Reemplazar el último comando
            this.commands.removeLast();
            this.commands.add(command);
            return;
        }
        
        // Eliminar comandos futuros (después del índice actual)
        while (this.commands.size() > this.index) {
            this.commands.removeLast();
        }
        
        // Agregar el nuevo comando
        this.commands.add(command);
        ++this.index;
        
        // Si excede el límite máximo, eliminar el más antiguo
        if (this.index > this.max) {
            this.commands.removeFirst();
            --this.index;
        }
    }

    public UndoableCommand undo() throws EmptyUndoListException, FirstUndoException, CannotUndoException {
        if (this.commands.size() == 0) {
            throw new EmptyUndoListException();
        }
        if (this.index == 0) {
            throw new FirstUndoException();
        }
        --this.index;
        UndoableCommand command = this.commands.get(this.index);
        command.undo();
        return command;
    }

    public UndoableCommand redo() throws EmptyUndoListException, LastRedoException, CannotRedoException {
        int size = this.commands.size();
        if (size == 0) {
            throw new EmptyUndoListException();
        }
        if (this.index >= size) {
            throw new LastRedoException();
        }
        UndoableCommand command = null;
        command = this.commands.get(this.index);
        command.redo();
        ++this.index;
        return command;
    }

    public UndoableCommand getCommand() {
        UndoableCommand c = null;
        if (this.commands.size() > 0) {
            c = this.commands.get(this.index - 1);
        }
        return c;
    }

    public UndoableCommand pop() {
        UndoableCommand c = null;
        if (this.commands.size() > 0) {
            c = this.commands.removeLast();
            --this.index;
        }
        return c;
    }

    public boolean canUndo() {
        return this.index > 0 && this.commands.size() > 0 && this.commands.get(this.index - 1).canUndo();
    }

    public boolean canRedo() {
        int size = this.commands.size();
        return size > 0 && this.index < size && this.commands.get(this.index).canRedo();
    }

    public int getLimit() {
        return this.max;
    }

    public void setLimit(int m) {
        this.max = m;
    }

    public int count() {
        return this.commands.size();
    }

    public int index() {
        return this.index;
    }
}

