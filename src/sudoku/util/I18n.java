package sudoku.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Internacionalización (i18n) para SudokuJ.
 * Soporta: inglés (en), español (es), francés (fr).
 */
public class I18n {
    private static final Map<String, Map<String, String>> STRINGS = new HashMap<>();
    private static String currentLang = "es"; // Por defecto español

    static {
        // Español
        Map<String, String> es = new HashMap<>();
        es.put("menu.file", "Archivo");
        es.put("menu.new_grid", "Nueva grilla");
        es.put("menu.easy", "Fácil");
        es.put("menu.medium", "Medio");
        es.put("menu.hard", "Difícil");
        es.put("menu.import_grid", "Importar grilla...");
        es.put("menu.reset", "Reiniciar");
        es.put("menu.save_game", "Guardar partida");
        es.put("menu.load_game", "Cargar partida");
        es.put("menu.new_creator", "Nueva grilla creador");
        es.put("menu.publish", "Publicar grilla");
        es.put("menu.save_grid", "Guardar grilla");
        es.put("menu.load_grid", "Cargar grilla");
        es.put("menu.exit", "Salir");
        es.put("menu.actions", "Acciones");
        es.put("menu.hint", "Pista");
        es.put("menu.show_solution", "Mostrar solución");
        es.put("menu.check_solution", "Verificar solución");
        es.put("menu.find_solution", "Encontrar solución");
        es.put("menu.validate", "Validar grilla");
        es.put("menu.pause", "Pausa");
        es.put("menu.resume", "Reanudar");
        es.put("menu.undo", "Deshacer");
        es.put("menu.redo", "Rehacer");
        es.put("menu.options", "Opciones");
        es.put("menu.possibilities_numbers", "Mostrar notas con números");
        es.put("menu.possibilities_dots", "Mostrar notas con puntos");
        es.put("menu.background", "Elegir imagen de fondo...");
        es.put("menu.language", "Idioma");
        es.put("menu.lang_english", "English");
        es.put("menu.lang_spanish", "Español");
        es.put("menu.lang_french", "Français");
        es.put("menu.help", "Ayuda");
        es.put("menu.help_topic", "Tema de ayuda");
        es.put("menu.about", "Acerca de SudokuJ...");
        es.put("status.cells_left", "CASILLAS RESTANTES");
        es.put("status.clock", "RELOJ");
        es.put("toolbar.new_grid", "Nueva grilla");
        es.put("help.title", "Ayuda de SudokuJ");
        es.put("about.title", "Acerca de SudokuJ...");
        es.put("about.text", "SudokuJ fue creado en 2007 como proyecto CPOO de 4º año de Informática del INSA de Rennes. Autores originales: Romain Huet y Nicolas Raynaud. Distribuido bajo licencia GNU GPL 2.0.\n\nActualizado 19-02-2026 por https://github.com/Mostremos/SudokuJ\n\nSudoku Java 2.1.0 recuperado y reversionado con mejoras, para Java moderno. Compatible con Windows, Linux y macOS.\n\nEste trabajo se basa en el código del SudokuJ 1.0.1 de los autores Romain Huet y Nicolas Raynaud, abandonado desde 2007 en http://code.google.com/archive/p/sudokuj07/ donde pueden encontrarse las fuentes y compilaciones originales.");
        es.put("title.mode_game", "Modo Juego");
        es.put("title.mode_creator", "Modo Creación");
        es.put("help.rules", "Una grilla de SudokuJ está formada por un cuadrado de 9 casillas por lado, subdividido en 9 regiones iguales. La regla del juego: cada fila, columna y región debe contener solo una vez los números del 1 al 9.\n\nPara indicar un valor, selecciona el número en el panel superior (rueda del ratón o clic izquierdo) y luego clic izquierdo en la casilla. Clic derecho para añadir una nota o posibilidad.\n\nPuedes resaltar casillas con el mismo valor. Clic derecho en un número del panel lo resalta en la grilla. SudokuJ incluye pausa, guardar y cargar partidas, y modo Creador para crear tus propias grillas.");
        es.put("msg.hint_ok", "Pista aplicada");
        es.put("msg.hint_no", "La grilla no tiene solución o ya está completa");
        STRINGS.put("es", es);

        // English
        Map<String, String> en = new HashMap<>();
        en.put("menu.file", "File");
        en.put("menu.new_grid", "New grid");
        en.put("menu.easy", "Easy");
        en.put("menu.medium", "Medium");
        en.put("menu.hard", "Hard");
        en.put("menu.import_grid", "Import grid...");
        en.put("menu.reset", "Reset");
        en.put("menu.save_game", "Save game");
        en.put("menu.load_game", "Load game");
        en.put("menu.new_creator", "New creator grid");
        en.put("menu.publish", "Publish grid");
        en.put("menu.save_grid", "Save grid");
        en.put("menu.load_grid", "Load grid");
        en.put("menu.exit", "Exit");
        en.put("menu.actions", "Actions");
        en.put("menu.hint", "Hint");
        en.put("menu.show_solution", "Show solution");
        en.put("menu.check_solution", "Check solution");
        en.put("menu.find_solution", "Find solution");
        en.put("menu.validate", "Validate grid");
        en.put("menu.pause", "Pause");
        en.put("menu.resume", "Resume");
        en.put("menu.undo", "Undo");
        en.put("menu.redo", "Redo");
        en.put("menu.options", "Options");
        en.put("menu.possibilities_numbers", "Show notes with numbers");
        en.put("menu.possibilities_dots", "Show notes with dots");
        en.put("menu.background", "Choose background image...");
        en.put("menu.language", "Language");
        en.put("menu.lang_english", "English");
        en.put("menu.lang_spanish", "Español");
        en.put("menu.lang_french", "Français");
        en.put("menu.help", "Help");
        en.put("menu.help_topic", "Help topic");
        en.put("menu.about", "About SudokuJ...");
        en.put("status.cells_left", "CELLS REMAINING");
        en.put("status.clock", "CLOCK");
        en.put("toolbar.new_grid", "New grid");
        en.put("help.title", "SudokuJ Help");
        en.put("about.title", "About SudokuJ...");
        en.put("about.text", "SudokuJ was created in 2007 as a CPOO project for 4th year Computer Science at INSA Rennes. Original authors: Romain Huet and Nicolas Raynaud. Distributed under GNU GPL 2.0 license.\n\nUpdated 19-02-2026 by https://github.com/Mostremos/SudokuJ\n\nSudoku Java 2.1.0 recovered and reversioned with improvements for modern Java. Compatible with Windows, Linux and macOS.\n\nThis work is based on SudokuJ 1.0.1 code by Romain Huet and Nicolas Raynaud, abandoned since 2007 at http://code.google.com/archive/p/sudokuj07/ where original sources and builds can be found.");
        en.put("title.mode_game", "Game Mode");
        en.put("title.mode_creator", "Creator Mode");
        en.put("help.rules", "A SudokuJ grid consists of a 9x9 square divided into 9 identical regions. The rule: each row, column and region must contain numbers 1-9 exactly once.\n\nTo enter a value, select the number in the top panel (mouse wheel or left click), then left-click the cell. Right-click to add a note or possibility.\n\nYou can highlight cells with the same value. Right-click a number in the panel to highlight it in the grid. SudokuJ includes pause, save and load, and Creator mode for custom grids.");
        en.put("msg.hint_ok", "Hint applied");
        en.put("msg.hint_no", "The grid has no solution or is already complete");
        STRINGS.put("en", en);

        // Français (original)
        Map<String, String> fr = new HashMap<>();
        fr.put("menu.file", "Fichier");
        fr.put("menu.new_grid", "Nouvelle grille");
        fr.put("menu.easy", "Facile");
        fr.put("menu.medium", "Moyen");
        fr.put("menu.hard", "Difficile");
        fr.put("menu.import_grid", "Importer une grille...");
        fr.put("menu.reset", "Recommencer");
        fr.put("menu.save_game", "Sauvegarder la partie");
        fr.put("menu.load_game", "Charger la partie");
        fr.put("menu.new_creator", "Nouvelle grille créateur");
        fr.put("menu.publish", "Publier la grille");
        fr.put("menu.save_grid", "Enregistrer la grille");
        fr.put("menu.load_grid", "Charger une grille");
        fr.put("menu.exit", "Quitter");
        fr.put("menu.actions", "Actions");
        fr.put("menu.hint", "Indice");
        fr.put("menu.show_solution", "Afficher la solution");
        fr.put("menu.check_solution", "Vérifier la solution");
        fr.put("menu.find_solution", "Trouver une solution");
        fr.put("menu.validate", "Valider la grille");
        fr.put("menu.pause", "Pause");
        fr.put("menu.resume", "Reprendre");
        fr.put("menu.undo", "Annuler");
        fr.put("menu.redo", "Refaire");
        fr.put("menu.options", "Options");
        fr.put("menu.possibilities_numbers", "Afficher les possibilités avec des chiffres");
        fr.put("menu.possibilities_dots", "Afficher les possibilités avec des disques");
        fr.put("menu.background", "Choisir l'image d'arrière-plan...");
        fr.put("menu.language", "Langue");
        fr.put("menu.lang_english", "English");
        fr.put("menu.lang_spanish", "Español");
        fr.put("menu.lang_french", "Français");
        fr.put("menu.help", "?");
        fr.put("menu.help_topic", "Rubrique d'aide");
        fr.put("menu.about", "À propos de SudokuJ...");
        fr.put("status.cells_left", "CASES RESTANTES");
        fr.put("status.clock", "HORLOGE");
        fr.put("toolbar.new_grid", "Nouvelle grille");
        fr.put("help.title", "Rubrique d'aide de SudokuJ");
        fr.put("about.title", "À propos de SudokuJ...");
        fr.put("about.text", "SudokuJ a été créé en 2007 dans le cadre du projet CPOO de 4e année Informatique de l'INSA de Rennes. Auteurs originaux : Romain Huet et Nicolas Raynaud. Distribué sous licence GNU GPL 2.0.\n\nMis à jour le 19-02-2026 par https://github.com/Mostremos/SudokuJ\n\nSudoku Java 2.1.0 récupéré et réversionné avec des améliorations pour Java moderne. Compatible Windows, Linux et macOS.\n\nCe travail est basé sur le code SudokuJ 1.0.1 des auteurs Romain Huet et Nicolas Raynaud, abandonné depuis 2007 sur http://code.google.com/archive/p/sudokuj07/ où se trouvent les sources et compilations originales.");
        fr.put("title.mode_game", "Mode Jeu");
        fr.put("title.mode_creator", "Mode Création");
        fr.put("help.rules", "Une grille de SudokuJ est constituée d'un carré de neuf cases de côté, subdivisé en neuf régions identiques. La règle : chaque ligne, colonne et région ne doit contenir qu'une seule fois les chiffres de 1 à 9.\n\nPour indiquer une valeur, sélectionnez le chiffre dans le panneau supérieur (molette ou clic gauche), puis clic gauche dans la case. Clic droit pour ajouter une note.\n\nVous pouvez surligner les cases ayant la même valeur. SudokuJ inclut pause, sauvegarde et mode Créateur.");
        fr.put("msg.hint_ok", "Indice appliqué");
        fr.put("msg.hint_no", "La grille n'a pas de solution ou est déjà complète");
        STRINGS.put("fr", fr);
    }

    public static String get(String key) {
        Map<String, String> lang = STRINGS.get(currentLang);
        if (lang != null && lang.containsKey(key)) {
            return lang.get(key);
        }
        // Fallback a español
        Map<String, String> fallback = STRINGS.get("es");
        return fallback != null && fallback.containsKey(key) ? fallback.get(key) : key;
    }

    public static void setLanguage(String lang) {
        if (lang != null && STRINGS.containsKey(lang)) {
            currentLang = lang;
        }
    }

    public static String getLanguage() {
        return currentLang;
    }
}
