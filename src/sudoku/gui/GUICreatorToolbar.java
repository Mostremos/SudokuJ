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
import sudoku.commands.SetLanguageCommand;
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
    private JButton swing_newGrid;
    private JButton swing_saveGrid;
    private JButton swing_loadGrid;
    private JButton swing_publishGrid;
    private JButton swing_findSolution;
    private JButton swing_showGrid;
    private JButton swing_validateGrid;

    public GUICreatorToolbar(Mediator parent) {
        super(parent);
        this.swing_newGrid = new JButton(new ImageIcon("images/blank_document.png"));
        this.swing_newGrid.setToolTipText(sudoku.util.I18n.get("toolbar.new_grid"));
        this.swing_newGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUICreatorToolbar.this.handle(new NewGridCommand(new Grid(), 1));
            }
        });
        this.swing_toolbar.add(this.swing_newGrid);
        this.swing_saveGrid = new JButton(new ImageIcon("images/disk.png"));
        this.swing_saveGrid.setToolTipText(sudoku.util.I18n.get("menu.save_grid"));
        this.swing_saveGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUICreatorToolbar.this.handle(new SaveCommand(1));
            }
        });
        this.swing_toolbar.add(this.swing_saveGrid);
        this.swing_loadGrid = new JButton(new ImageIcon("images/open_dir.png"));
        this.swing_loadGrid.setToolTipText(sudoku.util.I18n.get("menu.load_grid"));
        this.swing_loadGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUICreatorToolbar.this.handle(new LoadCommand(1));
            }
        });
        this.swing_toolbar.add(this.swing_loadGrid);
        this.swing_publishGrid = new JButton(new ImageIcon("images/grid_disk.png"));
        this.swing_publishGrid.setToolTipText(sudoku.util.I18n.get("menu.publish"));
        this.swing_publishGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUICreatorToolbar.this.handle(new PublishCommand());
            }
        });
        this.swing_toolbar.add(this.swing_publishGrid);
        this.swing_toolbar.addSeparator();
        this.swing_findSolution = new JButton(new ImageIcon("images/grid_question.png"));
        this.swing_findSolution.setToolTipText(sudoku.util.I18n.get("menu.find_solution"));
        this.swing_findSolution.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUICreatorToolbar.this.handle(new FindSolutionCommand());
            }
        });
        this.swing_toolbar.add(this.swing_findSolution);
        this.swing_showGrid = new JButton(new ImageIcon("images/grid_full.png"));
        this.swing_showGrid.setToolTipText(sudoku.util.I18n.get("menu.show_solution"));
        this.swing_showGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUICreatorToolbar.this.handle(new ShowSolutionCommand());
            }
        });
        this.swing_toolbar.add(this.swing_showGrid);
        this.swing_validateGrid = new JButton(new ImageIcon("images/grid_check.png"));
        this.swing_validateGrid.setToolTipText(sudoku.util.I18n.get("menu.validate"));
        this.swing_validateGrid.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUICreatorToolbar.this.handle(new ValidateGridCommand());
            }
        });
        this.swing_toolbar.add(this.swing_validateGrid);
        this.swing_toolbar.addSeparator();
        this.swing_undo = new JButton(new ImageIcon("images/arrow_left.png"));
        this.swing_undo.setToolTipText(sudoku.util.I18n.get("menu.undo"));
        this.swing_undo.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                GUICreatorToolbar.this.handle(new Undo());
            }
        });
        this.swing_toolbar.add(this.swing_undo);
        this.swing_redo = new JButton(new ImageIcon("images/arrow_right.png"));
        this.swing_redo.setToolTipText(sudoku.util.I18n.get("menu.redo"));
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

    private void refreshLanguage() {
        this.swing_newGrid.setToolTipText(sudoku.util.I18n.get("toolbar.new_grid"));
        this.swing_saveGrid.setToolTipText(sudoku.util.I18n.get("menu.save_grid"));
        this.swing_loadGrid.setToolTipText(sudoku.util.I18n.get("menu.load_grid"));
        this.swing_publishGrid.setToolTipText(sudoku.util.I18n.get("menu.publish"));
        this.swing_findSolution.setToolTipText(sudoku.util.I18n.get("menu.find_solution"));
        this.swing_showGrid.setToolTipText(sudoku.util.I18n.get("menu.show_solution"));
        this.swing_validateGrid.setToolTipText(sudoku.util.I18n.get("menu.validate"));
        this.swing_undo.setToolTipText(sudoku.util.I18n.get("menu.undo"));
        this.swing_redo.setToolTipText(sudoku.util.I18n.get("menu.redo"));
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
        if (command instanceof SetLanguageCommand) {
            this.refreshLanguage();
        }
        super.receiveCommand(mediator, command);
    }
}

