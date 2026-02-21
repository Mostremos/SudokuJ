package sudoku.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Internacionalización (i18n) para SudokuJ.
 * Soporta: español (es), inglés (en), francés (fr), portugués (pt),
 * chino (zh), japonés (ja), ruso (ru).
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
        es.put("menu.creator_tools", "Al crear grilla:");
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
        es.put("about.text", "SudokuJ fue creado en 2007 como proyecto CPOO de 4º año de Informática del INSA de Rennes. Autores originales: Romain Huet y Nicolas Raynaud. Distribuido bajo licencia GNU GPL 2.0.\n\nActualizado 19-02-2026 por https://github.com/Mostremos/SudokuJ\n\nSudoku Java 2.2.0 recuperado y reversionado con mejoras, para Java moderno. Compatible con Windows, Linux y macOS.\n\nEste trabajo se basa en el código del SudokuJ 1.0.1 de los autores Romain Huet y Nicolas Raynaud, abandonado desde 2007 en http://code.google.com/archive/p/sudokuj07/ donde pueden encontrarse las fuentes y compilaciones originales.");
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
        en.put("menu.creator_tools", "When creating grid:");
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
        en.put("about.text", "SudokuJ was created in 2007 as a CPOO project for 4th year Computer Science at INSA Rennes. Original authors: Romain Huet and Nicolas Raynaud. Distributed under GNU GPL 2.0 license.\n\nUpdated 19-02-2026 by https://github.com/Mostremos/SudokuJ\n\nSudoku Java 2.2.0 recovered and reversioned with improvements for modern Java. Compatible with Windows, Linux and macOS.\n\nThis work is based on SudokuJ 1.0.1 code by Romain Huet and Nicolas Raynaud, abandoned since 2007 at http://code.google.com/archive/p/sudokuj07/ where original sources and builds can be found.");
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
        fr.put("menu.creator_tools", "Lors de la création:");
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
        fr.put("about.text", "SudokuJ a été créé en 2007 dans le cadre du projet CPOO de 4e année Informatique de l'INSA de Rennes. Auteurs originaux : Romain Huet et Nicolas Raynaud. Distribué sous licence GNU GPL 2.0.\n\nMis à jour le 19-02-2026 par https://github.com/Mostremos/SudokuJ\n\nSudoku Java 2.2.0 récupéré et réversionné avec des améliorations pour Java moderne. Compatible Windows, Linux et macOS.\n\nCe travail est basé sur le code SudokuJ 1.0.1 des auteurs Romain Huet et Nicolas Raynaud, abandonné depuis 2007 sur http://code.google.com/archive/p/sudokuj07/ où se trouvent les sources et compilations originales.");
        fr.put("title.mode_game", "Mode Jeu");
        fr.put("title.mode_creator", "Mode Création");
        fr.put("help.rules", "Une grille de SudokuJ est constituée d'un carré de neuf cases de côté, subdivisé en neuf régions identiques. La règle : chaque ligne, colonne et région ne doit contenir qu'une seule fois les chiffres de 1 à 9.\n\nPour indiquer une valeur, sélectionnez le chiffre dans le panneau supérieur (molette ou clic gauche), puis clic gauche dans la case. Clic droit pour ajouter une note.\n\nVous pouvez surligner les cases ayant la même valeur. SudokuJ inclut pause, sauvegarde et mode Créateur.");
        fr.put("msg.hint_ok", "Indice appliqué");
        fr.put("msg.hint_no", "La grille n'a pas de solution ou est déjà complète");
        STRINGS.put("fr", fr);

        // Português
        Map<String, String> pt = new HashMap<>();
        pt.put("menu.file", "Arquivo");
        pt.put("menu.new_grid", "Nova grade");
        pt.put("menu.easy", "Fácil");
        pt.put("menu.medium", "Médio");
        pt.put("menu.hard", "Difícil");
        pt.put("menu.import_grid", "Importar grade...");
        pt.put("menu.reset", "Reiniciar");
        pt.put("menu.save_game", "Salvar partida");
        pt.put("menu.load_game", "Carregar partida");
        pt.put("menu.new_creator", "Nova grade criador");
        pt.put("menu.publish", "Publicar grade");
        pt.put("menu.save_grid", "Salvar grade");
        pt.put("menu.load_grid", "Carregar grade");
        pt.put("menu.exit", "Sair");
        pt.put("menu.actions", "Ações");
        pt.put("menu.hint", "Dica");
        pt.put("menu.show_solution", "Mostrar solução");
        pt.put("menu.check_solution", "Verificar solução");
        pt.put("menu.creator_tools", "Ao criar grade:");
        pt.put("menu.find_solution", "Encontrar solução");
        pt.put("menu.validate", "Validar grade");
        pt.put("menu.pause", "Pausar");
        pt.put("menu.resume", "Continuar");
        pt.put("menu.undo", "Desfazer");
        pt.put("menu.redo", "Refazer");
        pt.put("menu.options", "Opções");
        pt.put("menu.possibilities_numbers", "Mostrar notas com números");
        pt.put("menu.possibilities_dots", "Mostrar notas com pontos");
        pt.put("menu.background", "Escolher imagem de fundo...");
        pt.put("menu.language", "Idioma");
        pt.put("menu.lang_english", "English");
        pt.put("menu.lang_spanish", "Español");
        pt.put("menu.lang_french", "Français");
        pt.put("menu.lang_portuguese", "Português");
        pt.put("menu.lang_chinese", "中文");
        pt.put("menu.lang_japanese", "日本語");
        pt.put("menu.lang_russian", "Русский");
        pt.put("menu.help", "Ajuda");
        pt.put("menu.help_topic", "Tópico de ajuda");
        pt.put("menu.about", "Sobre SudokuJ...");
        pt.put("status.cells_left", "CÉLULAS RESTANTES");
        pt.put("status.clock", "RELOGIO");
        pt.put("toolbar.new_grid", "Nova grade");
        pt.put("help.title", "Ajuda SudokuJ");
        pt.put("about.title", "Sobre SudokuJ...");
        pt.put("about.text", "SudokuJ foi criado em 2007 como projeto CPOO do 4º ano de Informática do INSA de Rennes. Autores originais: Romain Huet e Nicolas Raynaud. Distribuído sob licença GNU GPL 2.0.\n\nAtualizado em 19-02-2026 por https://github.com/Mostremos/SudokuJ\n\nSudoku Java 2.2.0 recuperado e reversionado com melhorias para Java moderno. Compatível com Windows, Linux e macOS.");
        pt.put("title.mode_game", "Modo Jogo");
        pt.put("title.mode_creator", "Modo Criação");
        pt.put("help.rules", "Uma grade SudokuJ consiste em um quadrado 9x9 dividido em 9 regiões idênticas. A regra: cada linha, coluna e região deve conter os números 1-9 exatamente uma vez.\n\nPara inserir um valor, selecione o número no painel superior (roda do mouse ou clique esquerdo) e depois clique na célula. Clique direito para adicionar uma nota.\n\nVocê pode destacar células com o mesmo valor. SudokuJ inclui pausa, salvar e carregar partidas, e modo Criador.");
        pt.put("msg.hint_ok", "Dica aplicada");
        pt.put("msg.hint_no", "A grade não tem solução ou já está completa");
        STRINGS.put("pt", pt);

        // 中文 (Chino simplificado)
        Map<String, String> zh = new HashMap<>();
        zh.put("menu.file", "文件");
        zh.put("menu.new_grid", "新棋盘");
        zh.put("menu.easy", "简单");
        zh.put("menu.medium", "中等");
        zh.put("menu.hard", "困难");
        zh.put("menu.import_grid", "导入棋盘...");
        zh.put("menu.reset", "重置");
        zh.put("menu.save_game", "保存游戏");
        zh.put("menu.load_game", "加载游戏");
        zh.put("menu.new_creator", "新建创建者棋盘");
        zh.put("menu.publish", "发布棋盘");
        zh.put("menu.save_grid", "保存棋盘");
        zh.put("menu.load_grid", "加载棋盘");
        zh.put("menu.exit", "退出");
        zh.put("menu.actions", "操作");
        zh.put("menu.hint", "提示");
        zh.put("menu.show_solution", "显示答案");
        zh.put("menu.check_solution", "验证答案");
        zh.put("menu.creator_tools", "创建棋盘时：");
        zh.put("menu.find_solution", "查找答案");
        zh.put("menu.validate", "验证棋盘");
        zh.put("menu.pause", "暂停");
        zh.put("menu.resume", "继续");
        zh.put("menu.undo", "撤销");
        zh.put("menu.redo", "重做");
        zh.put("menu.options", "选项");
        zh.put("menu.possibilities_numbers", "用数字显示笔记");
        zh.put("menu.possibilities_dots", "用圆点显示笔记");
        zh.put("menu.background", "选择背景图片...");
        zh.put("menu.language", "语言");
        zh.put("menu.lang_english", "English");
        zh.put("menu.lang_spanish", "Español");
        zh.put("menu.lang_french", "Français");
        zh.put("menu.lang_portuguese", "Português");
        zh.put("menu.lang_chinese", "中文");
        zh.put("menu.lang_japanese", "日本語");
        zh.put("menu.lang_russian", "Русский");
        zh.put("menu.help", "帮助");
        zh.put("menu.help_topic", "帮助主题");
        zh.put("menu.about", "关于 SudokuJ...");
        zh.put("status.cells_left", "剩余格数");
        zh.put("status.clock", "计时");
        zh.put("toolbar.new_grid", "新棋盘");
        zh.put("help.title", "SudokuJ 帮助");
        zh.put("about.title", "关于 SudokuJ...");
        zh.put("about.text", "SudokuJ 于2007年由INSA雷恩信息学院四年级CPOO项目创建。原作者：Romain Huet 和 Nicolas Raynaud。GNU GPL 2.0 许可。\n\n2026年2月19日由 https://github.com/Mostremos 更新\n\nSudoku Java 2.2.0 恢复并改进，适用于现代Java。兼容 Windows、Linux 和 macOS。");
        zh.put("title.mode_game", "游戏模式");
        zh.put("title.mode_creator", "创建模式");
        zh.put("help.rules", "SudokuJ 棋盘由9x9方格组成，分为9个相同区域。规则：每行、每列和每个区域必须包含1-9各一次。\n\n输入数字：在顶部面板选择数字（鼠标滚轮或左键），然后左键点击格子。右键添加笔记。\n\n可高亮相同数字的格子。SudokuJ 包含暂停、保存/加载和创建者模式。");
        zh.put("msg.hint_ok", "已应用提示");
        zh.put("msg.hint_no", "棋盘无解或已完成");
        STRINGS.put("zh", zh);

        // 日本語
        Map<String, String> ja = new HashMap<>();
        ja.put("menu.file", "ファイル");
        ja.put("menu.new_grid", "新規グリッド");
        ja.put("menu.easy", "簡単");
        ja.put("menu.medium", "普通");
        ja.put("menu.hard", "難しい");
        ja.put("menu.import_grid", "グリッドを読み込む...");
        ja.put("menu.reset", "リセット");
        ja.put("menu.save_game", "ゲームを保存");
        ja.put("menu.load_game", "ゲームを読み込む");
        ja.put("menu.new_creator", "新規作成者グリッド");
        ja.put("menu.publish", "グリッドを公開");
        ja.put("menu.save_grid", "グリッドを保存");
        ja.put("menu.load_grid", "グリッドを読み込む");
        ja.put("menu.exit", "終了");
        ja.put("menu.actions", "アクション");
        ja.put("menu.hint", "ヒント");
        ja.put("menu.show_solution", "解答を表示");
        ja.put("menu.check_solution", "解答を確認");
        ja.put("menu.creator_tools", "作成時：");
        ja.put("menu.find_solution", "解答を探す");
        ja.put("menu.validate", "グリッドを検証");
        ja.put("menu.pause", "一時停止");
        ja.put("menu.resume", "再開");
        ja.put("menu.undo", "元に戻す");
        ja.put("menu.redo", "やり直す");
        ja.put("menu.options", "オプション");
        ja.put("menu.possibilities_numbers", "メモを数字で表示");
        ja.put("menu.possibilities_dots", "メモをドットで表示");
        ja.put("menu.background", "背景画像を選択...");
        ja.put("menu.language", "言語");
        ja.put("menu.lang_english", "English");
        ja.put("menu.lang_spanish", "Español");
        ja.put("menu.lang_french", "Français");
        ja.put("menu.lang_portuguese", "Português");
        ja.put("menu.lang_chinese", "中文");
        ja.put("menu.lang_japanese", "日本語");
        ja.put("menu.lang_russian", "Русский");
        ja.put("menu.help", "ヘルプ");
        ja.put("menu.help_topic", "ヘルプトピック");
        ja.put("menu.about", "SudokuJ について...");
        ja.put("status.cells_left", "残りマス");
        ja.put("status.clock", "タイマー");
        ja.put("toolbar.new_grid", "新規グリッド");
        ja.put("help.title", "SudokuJ ヘルプ");
        ja.put("about.title", "SudokuJ について...");
        ja.put("about.text", "SudokuJは2007年にINSAレンヌ情報学部4年次CPOOプロジェクトとして作成されました。原作者：Romain Huet、Nicolas Raynaud。GNU GPL 2.0ライセンス。\n\n2026年2月19日 https://github.com/Mostremos により更新\n\nSudoku Java 2.2.0 現代Java向けに復元・改良。Windows、Linux、macOS対応。");
        ja.put("title.mode_game", "ゲームモード");
        ja.put("title.mode_creator", "作成モード");
        ja.put("help.rules", "SudokuJグリッドは9×9の正方形で、9つの同一領域に分かれています。ルール：各行、各列、各領域に1-9を1回ずつ含める。\n\n値を入力：上部パネルで数字を選択（マウスホイールまたは左クリック）、セルを左クリック。右クリックでメモ追加。\n\n同じ数字のマスをハイライト可能。SudokuJには一時停止、保存/読み込み、作成者モードが含まれます。");
        ja.put("msg.hint_ok", "ヒントを適用しました");
        ja.put("msg.hint_no", "グリッドに解がありません、または既に完成しています");
        STRINGS.put("ja", ja);

        // Русский
        Map<String, String> ru = new HashMap<>();
        ru.put("menu.file", "Файл");
        ru.put("menu.new_grid", "Новая сетка");
        ru.put("menu.easy", "Лёгкий");
        ru.put("menu.medium", "Средний");
        ru.put("menu.hard", "Сложный");
        ru.put("menu.import_grid", "Импорт сетки...");
        ru.put("menu.reset", "Сброс");
        ru.put("menu.save_game", "Сохранить игру");
        ru.put("menu.load_game", "Загрузить игру");
        ru.put("menu.new_creator", "Новая сетка создателя");
        ru.put("menu.publish", "Опубликовать сетку");
        ru.put("menu.save_grid", "Сохранить сетку");
        ru.put("menu.load_grid", "Загрузить сетку");
        ru.put("menu.exit", "Выход");
        ru.put("menu.actions", "Действия");
        ru.put("menu.hint", "Подсказка");
        ru.put("menu.show_solution", "Показать решение");
        ru.put("menu.check_solution", "Проверить решение");
        ru.put("menu.creator_tools", "При создании сетки:");
        ru.put("menu.find_solution", "Найти решение");
        ru.put("menu.validate", "Проверить сетку");
        ru.put("menu.pause", "Пауза");
        ru.put("menu.resume", "Продолжить");
        ru.put("menu.undo", "Отменить");
        ru.put("menu.redo", "Повторить");
        ru.put("menu.options", "Параметры");
        ru.put("menu.possibilities_numbers", "Показывать заметки цифрами");
        ru.put("menu.possibilities_dots", "Показывать заметки точками");
        ru.put("menu.background", "Выбрать фоновое изображение...");
        ru.put("menu.language", "Язык");
        ru.put("menu.lang_english", "English");
        ru.put("menu.lang_spanish", "Español");
        ru.put("menu.lang_french", "Français");
        ru.put("menu.lang_portuguese", "Português");
        ru.put("menu.lang_chinese", "中文");
        ru.put("menu.lang_japanese", "日本語");
        ru.put("menu.lang_russian", "Русский");
        ru.put("menu.help", "Справка");
        ru.put("menu.help_topic", "Тема справки");
        ru.put("menu.about", "О SudokuJ...");
        ru.put("status.cells_left", "ОСТАЛОСЬ КЛЕТОК");
        ru.put("status.clock", "ЧАСЫ");
        ru.put("toolbar.new_grid", "Новая сетка");
        ru.put("help.title", "Справка SudokuJ");
        ru.put("about.title", "О SudokuJ...");
        ru.put("about.text", "SudokuJ создан в 2007 году как проект CPOO 4-го курса информатики INSA Ренна. Авторы: Romain Huet и Nicolas Raynaud. Лицензия GNU GPL 2.0.\n\nОбновлено 19-02-2026 https://github.com/Mostremos\n\nSudoku Java 2.2.0 восстановлен и улучшен для современной Java. Совместим с Windows, Linux и macOS.");
        ru.put("title.mode_game", "Режим игры");
        ru.put("title.mode_creator", "Режим создания");
        ru.put("help.rules", "Сетка SudokuJ состоит из квадрата 9×9, разделённого на 9 одинаковых областей. Правило: каждая строка, столбец и область должны содержать числа 1-9 по одному разу.\n\nВвод значения: выберите число на верхней панели (колёсико мыши или левый клик), затем клик по ячейке. Правый клик — добавить заметку.\n\nМожно подсвечивать ячейки с одинаковыми числами. SudokuJ включает паузу, сохранение/загрузку и режим создателя.");
        ru.put("msg.hint_ok", "Подсказка применена");
        ru.put("msg.hint_no", "Сетка не имеет решения или уже заполнена");
        STRINGS.put("ru", ru);

        // Añadir lang_portuguese etc a es, en, fr para el submenú
        es.put("menu.lang_portuguese", "Português");
        es.put("menu.lang_chinese", "中文");
        es.put("menu.lang_japanese", "日本語");
        es.put("menu.lang_russian", "Русский");
        en.put("menu.lang_portuguese", "Português");
        en.put("menu.lang_chinese", "中文");
        en.put("menu.lang_japanese", "日本語");
        en.put("menu.lang_russian", "Русский");
        fr.put("menu.lang_portuguese", "Português");
        fr.put("menu.lang_chinese", "中文");
        fr.put("menu.lang_japanese", "日本語");
        fr.put("menu.lang_russian", "Русский");
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
