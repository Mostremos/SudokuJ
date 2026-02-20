/*
 * Decompiled with CFR 0.152.
 */
package sudoku.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileFilter;
import jguic.Command;
import jguic.util.DesactivateRedo;
import jguic.util.DesactivateUndo;
import jguic.util.MainMediator;
import jguic.util.UndoableCommand;
import sudoku.commands.CheckSolutionCommand;
import sudoku.commands.ExitCommand;
import sudoku.commands.HintCommand;
import sudoku.commands.FindSolutionCommand;
import sudoku.commands.LoadCommand;
import sudoku.commands.NewGridCommand;
import sudoku.commands.PlayPauseCommand;
import sudoku.commands.PublishCommand;
import sudoku.commands.ResetCommand;
import sudoku.commands.SaveCommand;
import sudoku.commands.SetActiveFigureCommand;
import sudoku.commands.SetBackgroundImageCommand;
import sudoku.commands.SetCirclesCommand;
import sudoku.commands.SetUserDataCommand;
import sudoku.commands.SetValueCommand;
import sudoku.commands.ShowSolutionCommand;
import sudoku.commands.ValidateGridCommand;
import sudoku.core.Difficulty;
import sudoku.core.FixedCell;
import sudoku.core.Grid;
import sudoku.commands.SetLanguageCommand;
import sudoku.gui.FileExtensionFilter;
import sudoku.gui.GUICreatorToolbar;
import sudoku.gui.GUIGameZone;
import sudoku.gui.GUIMenu;
import sudoku.gui.GUIPlayerToolbar;
import sudoku.gui.Options;
import sudoku.gui.UserData;
import sudoku.util.I18n;

public class GUIMain
extends MainMediator {
    private UserData userdata;
    private Options options;
    private GUIMenu menu;
    private GUIPlayerToolbar player_toolbar = new GUIPlayerToolbar(this);
    private GUICreatorToolbar creator_toolbar = new GUICreatorToolbar(this);
    private GUIGameZone gamezone;
    private JFrame swing_main;
    private JFileChooser swing_filechooser;
    private JFileChooser swing_imagechooser = new JFileChooser("./background");
    private JPanel swing_toolbars;
    private static final String ID_PLAYER = "PLAYER";
    private static final String ID_CREATOR = "CREATOR";

    public GUIMain() {
        this.userdata = new UserData();
        this.options = new Options();
        I18n.setLanguage(this.options.getLanguage());
        this.menu = new GUIMenu(this);
        this.gamezone = new GUIGameZone(this, this.userdata);
        this.swing_main = new JFrame();
        this.swing_main.setIconImage(new ImageIcon("images/sudokuJ.png").getImage());
        this.swing_main.setSize(600, 677);
        this.swing_main.setResizable(false);
        this.swing_main.setJMenuBar(this.menu.getJMenuBar());
        this.swing_main.setLayout(new BorderLayout());
        this.swing_toolbars = new JPanel();
        this.swing_toolbars.setLayout(new CardLayout());
        JToolBar tbarp = this.player_toolbar.getJToolBar();
        tbarp.setFloatable(false);
        tbarp.setOrientation(0);
        this.swing_toolbars.add((Component)tbarp, ID_PLAYER);
        JToolBar tbarc = this.creator_toolbar.getJToolBar();
        tbarc.setFloatable(false);
        tbarc.setOrientation(0);
        this.swing_toolbars.add((Component)tbarc, ID_CREATOR);
        this.swing_main.add((Component)this.swing_toolbars, "North");
        JComponent gzone = this.gamezone.getJComponent();
        this.swing_main.add((Component)gzone, "Center");
        this.swing_filechooser = new JFileChooser();
        this.userdata.setDifficulty(this.options.getLevel());
        this.userdata.setMode(0);
        Grid g = new Grid();
        g.generate(this.userdata.getDifficulty());
        this.userdata.setGrid(g);
        SetUserDataCommand cmdNg = new SetUserDataCommand();
        cmdNg.setNewUserData(this.userdata);
        this.handle(cmdNg);
        SetBackgroundImageCommand cmdBg = new SetBackgroundImageCommand();
        cmdBg.setImageName(this.options.getBackgroungImage().length() > 0 ? this.options.getBackgroungImage() : "background/abstract1.jpg");
        this.handle(cmdBg);
        this.handle(new SetActiveFigureCommand(1));
        this.handle(new SetCirclesCommand(this.options.getCircles()));
        this.undoManager.clear();
        this.handle(new DesactivateUndo());
        this.handle(new DesactivateRedo());
    }

    public JFrame getJFrame() {
        return this.swing_main;
    }

    public boolean prepare(Command command) {
        SetValueCommand c;
        boolean continueCommand = true;
        if (command instanceof ExitCommand) {
            this.swing_main.dispose();
            System.exit(0);
        } else if (command instanceof SetUserDataCommand) {
            SetUserDataCommand c2 = (SetUserDataCommand)command;
            c2.setOldUserData(this.userdata);
        } else if (command instanceof NewGridCommand) {
            NewGridCommand c3 = (NewGridCommand)command;
            if (!c3.isDifficultySet() && c3.getMode() == 0) {
                Grid g = new Grid();
                g.generate(this.userdata.getDifficulty());
                c3.setGrid(g);
                c3.setDifficulty(this.userdata.getDifficulty());
            }
        } else if (command instanceof SaveCommand) {
            SaveCommand c4 = (SaveCommand)command;
            c4.setUserData(this.userdata);
            continueCommand = this.save(c4);
        } else if (command instanceof PublishCommand) {
            PublishCommand c5 = (PublishCommand)command;
            c5.setUserData(this.userdata);
            c5.setValid(this.userdata.getGrid().validate());
            continueCommand = this.publish(c5);
        } else if (command instanceof LoadCommand) {
            LoadCommand c6 = (LoadCommand)command;
            c6.setOldUserData(this.userdata);
            continueCommand = this.load(c6);
        } else if (command instanceof ResetCommand) {
            ResetCommand c7 = (ResetCommand)command;
            c7.setOldUserData(this.userdata);
            UserData u_reset = new UserData(this.userdata);
            u_reset.getGrid().reset();
            c7.setNewUserData(u_reset);
        } else if (command instanceof PlayPauseCommand) {
            ((PlayPauseCommand)command).setPause(!this.userdata.isPaused());
        } else if (command instanceof CheckSolutionCommand) {
            CheckSolutionCommand c8 = (CheckSolutionCommand)command;
            boolean valid = true;
            int i = 0;
            while (valid && i < 9) {
                int j = 0;
                while (valid && j < 9) {
                    if (this.userdata.getGrid().getCell(i, j).getValue() == 0) {
                        valid = false;
                    }
                    ++j;
                }
                ++i;
            }
            c8.setGridFull(valid);
            if (valid) {
                c8.setValid(this.userdata.getGrid().check());
            }
            c8.setGrid(this.userdata.getGrid());
        } else if (command instanceof ShowSolutionCommand) {
            ShowSolutionCommand c9 = (ShowSolutionCommand)command;
            boolean valid;
            if (this.userdata.getGrid().solve()) {
                valid = true;
            } else {
                valid = false;
            }
            c9.setHasSolution(valid);
        } else if (command instanceof FindSolutionCommand) {
            FindSolutionCommand c10 = (FindSolutionCommand)command;
            c10.setSolution(this.userdata.getGrid().solve());
        } else if (command instanceof HintCommand) {
            HintCommand cHint = (HintCommand)command;
            int[] hint = this.userdata.getGrid().getHint();
            cHint.setHint(hint);
            if (hint != null) {
                SetValueCommand svc = new SetValueCommand(hint[0] + 1, hint[1] + 1);
                svc.setOldValue(0);
                svc.setNewValue(hint[2]);
                this.handle(svc);
            }
        } else if (command instanceof ValidateGridCommand) {
            ValidateGridCommand c11 = (ValidateGridCommand)command;
            boolean hasNoDuplicates = this.userdata.getGrid().validate();
            boolean hasSolution = hasNoDuplicates && this.userdata.getGrid().solve();
            c11.setValid(hasNoDuplicates && hasSolution);
        } else if (command instanceof SetBackgroundImageCommand) {
            SetBackgroundImageCommand c12 = (SetBackgroundImageCommand)command;
            if (c12.getImageName().length() == 0) {
                continueCommand = this.changeBackgroundImage(c12);
            }
        } else if (command instanceof SetValueCommand && (c = (SetValueCommand)command).canReplace(this.undoManager.getCommand())) {
            continueCommand = false;
        }
        return continueCommand;
    }

    protected void executed(Command command) {
        if (command instanceof SetUserDataCommand) {
            SetUserDataCommand c = (SetUserDataCommand)command;
            this.userdata = c.getUserData();
            ((CardLayout)this.swing_toolbars.getLayout()).show(this.swing_toolbars, this.userdata.getMode() == 1 ? ID_CREATOR : ID_PLAYER);
            this.options.setLevel(this.userdata.getDifficulty());
            this.updateWindowTitle();
        } else if (command instanceof NewGridCommand) {
            NewGridCommand c = (NewGridCommand)command;
            this.userdata.setDifficulty(c.getDifficulty());
            SetUserDataCommand cm = new SetUserDataCommand();
            UserData newUD = new UserData();
            newUD.setGrid(c.getGrid());
            newUD.setMode(c.getMode());
            newUD.setDifficulty(c.getDifficulty());
            cm.setNewUserData(newUD);
            this.handle(cm);
        } else if (command instanceof LoadCommand) {
            LoadCommand c = (LoadCommand)command;
            if (c.isValid() && c.isLoad()) {
                SetUserDataCommand cm = new SetUserDataCommand();
                cm.setOldUserData(c.getOldUserData());
                cm.setNewUserData(c.getNewUserData());
                this.handle(cm);
            }
        } else if (command instanceof ResetCommand) {
            ResetCommand c = (ResetCommand)command;
            if (c.getNewUserData() != c.getOldUserData()) {
                SetUserDataCommand cm = new SetUserDataCommand();
                cm.setOldUserData(c.getOldUserData());
                cm.setNewUserData(c.getNewUserData());
                this.handle(cm);
            }
        } else if (command instanceof PlayPauseCommand) {
            this.userdata.setPause(((PlayPauseCommand)command).isPaused());
        } else if (command instanceof ShowSolutionCommand) {
            ShowSolutionCommand c = (ShowSolutionCommand)command;
            if (c.hasSolution()) {
                SetUserDataCommand cm = new SetUserDataCommand();
                cm.setOldUserData(this.userdata);
                UserData u_sol = new UserData(this.userdata);
                Grid g = u_sol.getGrid();
                int i = 0;
                while (i < 9) {
                    int j = 0;
                    while (j < 9) {
                        g.getCell(i, j).setValue(g.getSolution(i, j));
                        ++j;
                    }
                    ++i;
                }
                cm.setNewUserData(u_sol);
                this.handle(cm);
            }
        } else if (command instanceof SetCirclesCommand) {
            SetCirclesCommand c = (SetCirclesCommand)command;
            this.options.setCircles(c.isCircles());
        } else if (command instanceof SetLanguageCommand) {
            SetLanguageCommand c = (SetLanguageCommand)command;
            this.options.setLanguage(c.getLanguage());
            I18n.setLanguage(c.getLanguage());
            this.menu.refreshTexts();
            this.updateWindowTitle();
        } else if (command instanceof SetBackgroundImageCommand) {
            SetBackgroundImageCommand c = (SetBackgroundImageCommand)command;
            this.options.setBackgroundImage(c.getImageName());
        }
    }

    private void updateWindowTitle() {
        String title;
        if (this.userdata.getMode() == 0) {
            title = "SudokuJ - " + I18n.get("title.mode_game");
            if (this.userdata.getDifficulty() == Difficulty.EASY) {
                title += " - " + I18n.get("menu.easy");
            } else if (this.userdata.getDifficulty() == Difficulty.MEDIUM) {
                title += " - " + I18n.get("menu.medium");
            } else if (this.userdata.getDifficulty() == Difficulty.HARD) {
                title += " - " + I18n.get("menu.hard");
            }
        } else {
            title = "SudokuJ - " + I18n.get("title.mode_creator");
        }
        this.swing_main.setTitle(title);
    }

    private boolean save(SaveCommand command) {
        FileExtensionFilter filter;
        boolean ret = true;
        if (command.getMode() == 0) {
            filter = new FileExtensionFilter("Partie de SudokuJ (.sjg)", ".sjg");
            this.swing_filechooser.setDialogTitle("Sauvegarder la partie");
        } else {
            filter = new FileExtensionFilter("Grille de SudokuJ (.sjc)", ".sjc");
            this.swing_filechooser.setDialogTitle("Enregistrer la grille");
        }
        this.swing_filechooser.setFileFilter(filter);
        this.swing_filechooser.setFileHidingEnabled(false);
        int returnval = this.swing_filechooser.showSaveDialog(this.swing_main);
        if (returnval == 0) {
            File file = this.swing_filechooser.getSelectedFile();
            if (!((FileFilter)filter).accept(file)) {
                file = this.userdata.getMode() == 0 ? new File(String.valueOf(file.getAbsolutePath()) + ".sjg") : new File(String.valueOf(file.getAbsolutePath()) + ".sjc");
            }
            try {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(this.userdata);
                oos.flush();
                oos.close();
                command.setFileName(file.getAbsolutePath());
                command.setSaved(true);
            }
            catch (Exception e) {
                command.setSaved(false);
            }
        } else {
            ret = false;
        }
        return ret;
    }

    private boolean load(LoadCommand command) {
        FileExtensionFilter filter;
        boolean ret = true;
        if (command.getMode() == 0) {
            filter = new FileExtensionFilter("Partie de SudokuJ (.sjg)", ".sjg");
            this.swing_filechooser.setDialogTitle("Charger une partie");
        } else {
            filter = new FileExtensionFilter("Grille de SudokuJ (.sjc)", ".sjc");
            this.swing_filechooser.setDialogTitle("Charger une grille");
        }
        this.swing_filechooser.setFileFilter(filter);
        this.swing_filechooser.setFileHidingEnabled(true);
        int returnval = this.swing_filechooser.showOpenDialog(this.swing_main);
        if (returnval == 0) {
            File file = this.swing_filechooser.getSelectedFile();
            if (((FileFilter)filter).accept(file)) {
                try {
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Object o = ois.readObject();
                    command.setLoad(true);
                    if (o instanceof UserData) {
                        command.setNewUserData((UserData)o);
                        command.setFileName(file.getAbsolutePath());
                        command.setValid(true);
                    } else {
                        command.setValid(false);
                    }
                    ois.close();
                }
                catch (Exception e) {
                    command.setLoad(false);
                }
            }
        } else {
            ret = false;
        }
        return ret;
    }

    private boolean publish(PublishCommand command) {
        boolean ret = true;
        if (command.isValid()) {
            Grid g = command.getUserData().getGrid();
            Grid g_pub = new Grid();
            int i = 0;
            while (i < 9) {
                int j = 0;
                while (j < 9) {
                    int v = g.getCell(i, j).getValue();
                    if (v > 0) {
                        g_pub.setCell(i, j, new FixedCell(v));
                    } else {
                        g_pub.setCell(i, j, g.getCell(i, j));
                    }
                    ++j;
                }
                ++i;
            }
            g_pub.solve();
            UserData u_pub = new UserData();
            u_pub.setGrid(g_pub);
            u_pub.setMode(0);
            FileExtensionFilter filter = new FileExtensionFilter("Partie vierge de SudokuJ (.sjg)", ".sjg");
            this.swing_filechooser.setDialogTitle("Publier la grille");
            this.swing_filechooser.setFileFilter(filter);
            this.swing_filechooser.setFileHidingEnabled(false);
            int returnval = this.swing_filechooser.showSaveDialog(this.swing_main);
            if (returnval == 0) {
                File file = this.swing_filechooser.getSelectedFile();
                if (!((FileFilter)filter).accept(file)) {
                    file = new File(String.valueOf(file.getAbsolutePath()) + ".sjg");
                }
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(u_pub);
                    oos.flush();
                    oos.close();
                    command.setFileName(file.getAbsolutePath());
                    command.setPublished(true);
                }
                catch (Exception e) {
                    command.setPublished(false);
                }
            } else {
                ret = false;
            }
        }
        return ret;
    }

    private boolean changeBackgroundImage(SetBackgroundImageCommand command) {
        boolean ret = true;
        FileExtensionFilter filter = new FileExtensionFilter("Fichier image (*.png, *.jpg, *.jpeg, *.gif)");
        filter.addExtension(".png");
        filter.addExtension(".jpeg");
        filter.addExtension(".jpg");
        filter.addExtension(".gif");
        this.swing_imagechooser.setDialogTitle("S\u00e9lectionner une image d'arri\u00e8re-plan");
        this.swing_imagechooser.setFileFilter(filter);
        this.swing_imagechooser.setFileHidingEnabled(true);
        int returnval = this.swing_imagechooser.showOpenDialog(this.swing_main);
        if (returnval == 0) {
            File file = this.swing_imagechooser.getSelectedFile();
            if (file != null && filter.accept(file)) {
                command.setImageName(file.getAbsolutePath());
            }
        } else {
            ret = false;
        }
        return ret;
    }

    protected void redoOn(UndoableCommand command) {
        this.executed(command);
    }

    protected void undoOn(UndoableCommand command) {
        this.executed(command);
    }
}

