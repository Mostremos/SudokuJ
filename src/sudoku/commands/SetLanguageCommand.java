package sudoku.commands;

import jguic.Command;

/**
 * Comando para cambiar el idioma de la aplicación.
 */
public class SetLanguageCommand implements Command {
    private String language;

    public SetLanguageCommand(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public void execute() throws Exception {
        // El manejo se hace en GUIMain
    }
}
