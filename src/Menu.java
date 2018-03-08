import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import javax.swing.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class builds a menu bar and handles the actions associated with it.
 * @author William March -
 */
 class Menu extends JMenuBar{
    // Create menu;


    Menu() {
        // file menu icons



        JMenu fMenu;         // file
        JMenu tMenu;        //  tab
        JMenu hMenu;       //   help
        JMenuItem sMenu;  //    syntax
        JMenu rMenu;     //     run
        JMenu tsMenu;   //      tab spaces




        // file menu
        fMenu = new JMenu(" File ");
        fMenu.setMnemonic(KeyEvent.VK_F);

        // new item
        JMenuItem nMenui = new JMenu(" New ");
        nMenui.setMnemonic(KeyEvent.VK_N);

        // From Clipboard action listener
        JMenuItem fromclipSyntax = new JMenuItem("From clipboard");
        fromclipSyntax.addActionListener((e) -> {
            try {
                Io.cliboardToTab();

            } catch (IOException io) {
                // never happens
                io.printStackTrace();
            } catch (UnsupportedFlavorException fl) {
                fl.getCause();
            }
        });


        nMenui.add(fromclipSyntax);
        nMenui.add(new JSeparator());

        JMenuItem fileItem = new JMenuItem("File");
        nMenui.add(fileItem);
        fileItem.addActionListener((e -> MainScreen.newTab("file", "SYNTAX_STLYE_NONE", "")));

        // TEXT FILE ITEM
        JMenuItem textItem = new JMenuItem("Text file");
        nMenui.add(textItem);
        textItem.addActionListener((e -> MainScreen.newTab("file.txt", "SYNTAX_STYLE_PLAIN", "")));

        // Preferences
        JMenuItem prefItem = new JMenuItem("Preferences");
        fMenu.add(new JSeparator());
        fMenu.add(prefItem);
        prefItem.addActionListener((e -> {

            // try to get existing preferences file from user
            try {
                java.util.List<String> lines = Files.readAllLines(Paths.get(MainScreen.prefPath),
                        Charset.defaultCharset());
                StringBuilder styleBuffer = new StringBuilder();

                // add lines from file to new buffer
                for (String temp : lines) {
                    styleBuffer.append(temp);
                    styleBuffer.append("\n");
                }

                // add buffer to new tab
                MainScreen.newTab("Edit-Preferences.txt", "SYNTAX_STYLE_CSS",
                        styleBuffer.toString());
                MainScreen.getRSSyntaxarea().setCaretPosition(0);

                // if no preferences file was found, open a dialog and create a new preferences
                // file if the user chooses yes
            } catch (IOException ioe) {
                SwingUtilities.invokeLater(()-> {
                    CloseDialog cl = new CloseDialog("No preferences file found, would you like " +
                            "to create one ?",
                            "Error");

                    if (cl.show() == 0) {
                        try{
                            PrintWriter writer = new PrintWriter(MainScreen.prefPath, "UTF-8");
                            writer.println(
                                    "/*LOOK AND FEEL*/\n" +
                                            "Theme = dark\n\n"+
                                    "/*PREFERENCES*/\n" +
                                    "setCodeFoldingEnabled = true\n" +
                                    "setAnimateBracketMatching = true\n" +
                                    "setCloseCurlyBraces = true\n" +
                                    "setAutoIndentEnabled = true\n" +
                                    "setAntiAliasingEnabled = true\n" +
                                    "setDragEnabled = true\n" +
                                    "setRoundedSelectionEdges = false\n" +
                                    "setBracketMatchingEnabled = true\n" +
                                    "setAnimateBracketMatching = true\n" +
                                    "setCloseMarkupTags = true\n" +
                                    "setEOLMarkersVisible = false\n" +
                                    "setHighlightSecondaryLanguages = true\n" +
                                    "setMarkOccurrences = true\n");
                            writer.flush();
                            writer.close();


                            // inform user of new file creation and its location
                            SwingUtilities.invokeLater(()-> new CloseDialog("File was created at "+
                                    MainScreen.prefPath,"New preferences file created" , true));


                        } catch (IOException ioex) {
                            // do something
                        }
                    }

                });
            }
        }));

        // Tab menu
        tMenu = new JMenu("  Tab  ");
        tMenu.setMnemonic(KeyEvent.VK_T);
        tMenu.setToolTipText("Apply operations to the currently selected tab");

        // clear tab menu item
        JMenuItem clMenui = new JMenuItem("Clear");
        clMenui.setToolTipText("Clear tabs contents");
        // listen
        clMenui.addActionListener((e ->
            MainScreen.getRSSyntaxarea().setText("")));

        // add
        tMenu.add(clMenui);

        // Close tab item





        // close tabe without saving
        JMenuItem fcMenui = new JMenuItem("Close", KeyEvent.VK_T);
        KeyStroke ctrlXKeyStrokeforce = KeyStroke.getKeyStroke("control f");
        fcMenui.setAccelerator(ctrlXKeyStrokeforce);
        fcMenui.setToolTipText("Close tab without saving");

        // listen
        fcMenui.addActionListener((e -> MainScreen.tabbedPane.remove(MainScreen.current)));

        // add
        tMenu.add(fcMenui);

        // close and save current tab
        JMenuItem cMenui = new JMenuItem("Close and save", KeyEvent.VK_T);
        KeyStroke ctrlXKeyStroke = KeyStroke.getKeyStroke("control c");
        cMenui.setAccelerator(ctrlXKeyStroke);
        cMenui.setToolTipText("Close and save tab");

        // listen
        cMenui.addActionListener((e -> {
            Io.saveAs();
            MainScreen.tabbedPane.removeTabAt(MainScreen.current);
            System.out.print("\nyes\n");
        }));

        // add
        tMenu.add(cMenui);

        hMenu = new JMenu(" Help ");

        //about item
        JMenuItem aMenui = new JMenuItem(" About ");

        // listen
        aMenui.addActionListener((e)-> SwingUtilities.invokeLater(About::new));

        // add
        hMenu.add(aMenui);

        sMenu = new JMenu(" Syntax ");

        //SYNTAX ADDED HERE
        JMenuItem actionSyntax = new JMenuItem("Actionscript");
        actionSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_ACTIONSCRIPT));
        sMenu.add(actionSyntax);

        JMenuItem x86assSyntax = new JMenuItem("x86 Assember");
        x86assSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_X86));
        sMenu.add(x86assSyntax);

        JMenuItem bytecodeSyntax = new JMenuItem("Bytecode");
        bytecodeSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_BBCODE));
        sMenu.add(bytecodeSyntax);

        JMenuItem bashSyntax = new JMenuItem("Bash/Unix");
        bashSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL));
        sMenu.add(bashSyntax);

        JMenuItem cSyntax = new JMenuItem("C");
        cSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C));
        sMenu.add(cSyntax);

        JMenuItem cppSyntax = new JMenuItem("C++");
        cppSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS));
        sMenu.add(cppSyntax);

        JMenuItem csharpSyntax = new JMenuItem("C#");
        cppSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSHARP));
        sMenu.add(csharpSyntax);

        JMenuItem clojureSyntax = new JMenuItem("Clojure");
        clojureSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CLOJURE));
        sMenu.add(clojureSyntax);

        JMenuItem cssSyntax = new JMenuItem("CSS");
        cssSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS));
        sMenu.add(cssSyntax);

        JMenuItem dSyntax = new JMenuItem("D");
        dSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_D));
        sMenu.add(dSyntax);

        JMenuItem dockerSyntax = new JMenuItem("DockerFile");
        dockerSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DOCKERFILE));
        sMenu.add(dockerSyntax);

        JMenuItem dartSyntax = new JMenuItem("Dart");
        dartSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DOCKERFILE));
        sMenu.add(dockerSyntax);

        JMenuItem groovySyntax = new JMenuItem("Groovy");
        groovySyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GROOVY));
        sMenu.add(groovySyntax);

        JMenuItem javasSyntax = new JMenuItem("JavaScript");
        javasSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT));
        sMenu.add(javasSyntax);

        JMenuItem jsonSyntax = new JMenuItem("JSON");
        jsonSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON_WITH_COMMENTS));
        sMenu.add(jsonSyntax);

        JMenuItem javaSyntax = new JMenuItem("Java");
        javaSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA));
        sMenu.add(javaSyntax);

        JMenuItem pythonSyntax = new JMenuItem("Python");
        pythonSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON));
        sMenu.add(pythonSyntax);

        JMenuItem phpSyntax = new JMenuItem("PHP");
        phpSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PHP));
        sMenu.add(phpSyntax);

        JMenuItem xmlSyntax = new JMenuItem("XML");
        xmlSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML));
        sMenu.add(xmlSyntax);

        JMenuItem mxmlSyntax = new JMenuItem("MXML");
        mxmlSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_MXML));
        sMenu.add(mxmlSyntax);

        JMenuItem yamlSyntax = new JMenuItem("YAML");
        yamlSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_YAML));
        sMenu.add(yamlSyntax);

        JMenuItem latexSyntax = new JMenuItem("LaTeX");
        latexSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX));
        sMenu.add(latexSyntax);

        JMenuItem htmlSyntax = new JMenuItem("HTML");
        htmlSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML));
        sMenu.add(htmlSyntax);

        JMenuItem rubySyntax = new JMenuItem("Ruby");
        rubySyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_RUBY));
        sMenu.add(rubySyntax);

        JMenuItem sqlSyntax = new JMenuItem("SQL");
        sqlSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL));
        sMenu.add(sqlSyntax);


        JMenuItem plainSyntax = new JMenuItem("Plain/Text");
        plainSyntax.addActionListener((e) ->
            MainScreen.getRSSyntaxarea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE));
        sMenu.add(plainSyntax);


        //RUN MENU
        rMenu = new JMenu("  Run ->"  );

        JMenuItem javaCompileAndRun = new JMenuItem("Complie and run java");
        javaCompileAndRun.addActionListener((e -> {
            try {
                Io.compileJava();
            } catch (IOException c) {
                c.printStackTrace();
            } catch (InterruptedException in) {
                // wrong
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }));

        rMenu.add(javaCompileAndRun);

        // tab space menu
        tsMenu = new JMenu("  Tab spaces  ");

        JMenuItem fourItem = new JMenuItem("4");
        tsMenu.add(fourItem);
        fourItem.addActionListener((e -> MainScreen.getRSSyntaxarea().setTabSize(4)));

        JMenuItem eightItem = new JMenuItem("8");
        tsMenu.add(eightItem);
        eightItem.addActionListener((e -> MainScreen.getRSSyntaxarea().setTabSize(8)));

        JMenuItem userDefineItem = new JMenuItem("Custom");
        userDefineItem.addActionListener((e -> SwingUtilities.invokeLater(TabCustomDialog::new)));

        tsMenu.add(userDefineItem);

        JMenuItem testItem = new JMenuItem("test");
        testItem.addActionListener((e)-> Util.perfLooper());
        rMenu.add(testItem);

        // add menus to bar
        add(fMenu);

        add(tMenu);

        add(hMenu);

        add(sMenu);

        add(tsMenu);

        add(rMenu);



    }
}
