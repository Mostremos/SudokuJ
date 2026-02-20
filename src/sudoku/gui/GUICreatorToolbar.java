/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import jguic.Command;
import jguic.Mediator;
import jguic.util.DesactivateRedo;
import jguic.util.DesactivateUndo;
import jguic.util.Redo;
import jguic.util.Undo;
import jguic.util.UndoableCommand;
import sudoku.commands.FindSolutionCommand;
import sudoku.commands.LoadCommand;
import sudoku.commands.NewGridCommand;
import sudoku.commands.PublishCommand;
import sudoku.commands.SaveCommand;
import sudoku.commands.ShowSolutionCommand;
import sudoku.commands.ValidateGridCommand;
import sudoku.core.Grid;
import sudoku.gui.GUIToolbar;

public class GUICreatorToolbar
extends GUIToolbar {
    private JToolBar swing_toolbar = new JToolBar();
    private JButton swing_undo;
    private JButton swing_redo;

    public GUICreatorToolbar(Mediator parent) {
        super(parent);
        JButton newGrid = new JButton(new ImageIcon("images/blank_document.png"));
        newGrid.setToolTipText(sudoku.util.I18n.get("toolbar.new_grid"));
        newGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUICreatorToolbar.this.handle(new NewGridCommand(new Grid(), 1));
            }
        });
        this.swing_toolbar.add(newGrid);
        JButton saveGrid = new JButton(new ImageIcon("images/disk.png"));
        saveGrid.setToolTipText("Sauvegarder la grille");
        saveGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUICreatorToolbar.this.handle(new SaveCommand(1));
            }
        });
        this.swing_toolbar.add(saveGrid);
        JButton loadGrid = new JButton(new ImageIcon("images/open_dir.png"));
        loadGrid.setToolTipText("Charger une grille");
        loadGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUICreatorToolbar.this.handle(new LoadCommand(1));
            }
        });
        this.swing_toolbar.add(loadGrid);
        JButton publishGrid = new JButton(new ImageIcon("images/grid_disk.png"));
        publishGrid.setToolTipText("Publier la grille");
        publishGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUICreatorToolbar.this.handle(new PublishCommand());
            }
        });
        this.swing_toolbar.add(publishGrid);
        this.swing_toolbar.addSeparator();
        JButton findSolution = new JButton(new ImageIcon("images/grid_question.png"));
        findSolution.setToolTipText("Trouver une solution");
        findSolution.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUICreatorToolbar.this.handle(new FindSolutionCommand());
            }
        });
        this.swing_toolbar.add(findSolution);
        JButton showGrid = new JButton(new ImageIcon("images/grid_full.png"));
        showGrid.setToolTipText("Afficher la solution");
        showGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUICreatorToolbar.this.handle(new ShowSolutionCommand());
            }
        });
        this.swing_toolbar.add(showGrid);
        JButton validateGrid = new JButton(new ImageIcon("images/grid_check.png"));
        validateGrid.setToolTipText("Valider la grille");
        validateGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUICreatorToolbar.this.handle(new ValidateGridCommand());
            }
        });
        this.swing_toolbar.add(validateGrid);
        this.swing_toolbar.addSeparator();
        this.swing_undo = new JButton(new ImageIcon("images/arrow_left.png"));
        this.swing_undo.setToolTipText("Annuler");
        this.swing_undo.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUICreatorToolbar.this.handle(new Undo());
            }
        });
        this.swing_toolbar.add(this.swing_undo);
        this.swing_redo = new JButton(new ImageIcon("images/arrow_right.png"));
        this.swing_redo.setToolTipText("Refaire");
        this.swing_redo.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUICreatorToolbar.this.handle(new Redo());
            }
        });
        this.swing_toolbar.add(this.swing_redo);
    }

    public JToolBar getJToolBar() {
        return this.swing_toolbar;
    }

    public void receiveCommand(Mediator mediator, Command command) {
        if (command instanceof DesactivateUndo) {
            this.swing_undo.setEnabled(false);
        } else if (command instanceof DesactivateRedo) {
            this.swing_redo.setEnabled(false);
        } else if (command instanceof UndoableCommand) {
            this.swing_undo.setEnabled(true);
            this.swing_redo.setEnabled(true);
        }
        super.receiveCommand(mediator, command);
    }
}

