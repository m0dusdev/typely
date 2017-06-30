import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import sun.applet.Main;

import javax.swing.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class builds a menu bar and handles the actions associated with it.
 * @author William March -
 */

public class Menu extends JMenuBar{
    // Create menu;
    public  Menu() {

        // file menu icons

        JMenu fMenu;         // file
        JMenu tMenu;        //  tab
        JMenu hMenu;       //   help
        JMenu rMenu;      //    run
        JMenuItem sMenu; //     syntax


        // file menu
        fMenu = new JMenu(" File ");
        fMenu.setMnemonic(KeyEvent.VK_F);

        // new item
        JMenuItem nMenui = new JMenu(" New");
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

            }
        });


        nMenui.add(fromclipSyntax);
        nMenui.add(new JSeparator());

        JMenuItem fileItem = new JMenuItem("File");
        nMenui.add(fileItem);
        fileItem.addActionListener((e -> MainScreen.handle("file", "SYNTAX_STLYE_NONE", "")));

        // TEXT FILE ITEM
        JMenuItem textItem = new JMenuItem("Text file");
        nMenui.add(textItem);
        textItem.addActionListener((e -> MainScreen.handle("file.txt", "SYNTAX_STYLE_PLAIN", "")));


        JMenuItem pythonItem = new JMenuItem("Python file");
        nMenui.add(pythonItem);
        pythonItem.addActionListener((e)-> MainScreen.handle("temp.py", "SYNTAX_STYLE_PYTHON", "#!/usr/bin/env python\n\n" +
                "# -*- coding: utf-8 -*-\n" +
                "\n" +
                "\"\"\"This is a awesome\n" +
                "        python script!\"\"\"\n\n" ));


        JMenu javaItem = new JMenu("Java file");

        JMenuItem javaSwingItem = new JMenuItem("Swing template");
        javaItem.add(javaSwingItem);
        javaSwingItem.addActionListener((e) -> MainScreen.handle("Frame.java", "SYNTAX_STYLE_JAVA", "\n" +
                "import java.awt.event.WindowAdapter;\n" +
                "import java.awt.event.WindowEvent;\n" +
                "import javax.swing.*;\n" +
                "\n" +
                "\n" +
                "\n" +
                "public class Frame extends JFrame {\n" +
                "\n" +
                "    private JPanel panel;\n" +
                "\n" +
                "    public Frame(){\n" +
                "        super(\"gui\"); // title\n" +
                "\n" +
                "        setSize(800,600);\n" +
                "        setDefaultCloseOperation(3); // exit on close\n" +
                "\n" +
                "        panel = new JPanel();\n" +
                "        // on window close\n" +
                "        this.addWindowListener(new WindowAdapter() {\n" +
                "            @Override\n" +
                "            public void windowClosed(WindowEvent e) {\n" +
                "                super.windowOpened(e);\n" +
                "\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        this.add(panel, SwingConstants.CENTER);\n" +
                "        this.setJMenuBar(new Menu());\n" +
                "\n" +
                "\n" +
                "        setVisible(true);\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    // JmenuBar\n" +
                "    class Menu extends JMenuBar {\n" +
                "        Menu(){\n" +
                "            // File item\n" +
                "            JMenu file = new JMenu(\"File\");\n" +
                "\n" +
                "                // Inside file\n" +
                "                JMenuItem item1 = new JMenuItem(\"item 1\");\n" +
                "                item1.addActionListener((e)-> System.err.print(\"Action\"));\n" +
                "                file.add(item1);\n" +
                "\n" +
                "            // Add file to class\n" +
                "            this.add(file);\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "\n" +
                "    public static void main(String[] args){\n" +
                "        SwingUtilities.invokeLater(()-> {\n" +
                "            // set appropriate look and feel\n" +
                "            try {\n" +
                "                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());\n" +
                "            } catch (ClassNotFoundException ex) {\n" +
                "            } catch (InstantiationException ex) {\n" +
                "            } catch (IllegalAccessException ex) {\n" +
                "            } catch (UnsupportedLookAndFeelException ex) {\n" +
                "            }\n" +
                "\n" +
                "            new Frame();\n" +
                "        });\n" +
                "    }\n" +
                "\n" +
                "}"));

        JMenuItem javaDialogItem = new JMenuItem("Java Dialog");
        javaDialogItem.addActionListener((e) -> {
            MainScreen.handle("About.java", "JAVA", "import javax.swing.*;\n" +
                    "import java.awt.*;\n" +
                    "\n" +
                    "/**\n" +
                    " *      DIALOG FOR \"\"\n" +
                    " */\n" +
                    "\n" +
                    "public class About extends JDialog   {\n" +
                    "\n" +
                    "    private JLabel jt;\n" +
                    "\n" +
                    "    About() {\n" +
                    "        setLayout(new BorderLayout());\n" +
                    "        setTitle(\"About\");\n" +
                    "        setSize(500,150);\n" +
                    "        setLocationRelativeTo(null);\n" +
                    "        setVisible(true);\n" +
                    "        setResizable(false);\n" +
                    "        jt= new JLabel();\n" +
                    "        this.setDefaultCloseOperation(1); // Hide on close\n" +
                    "        jt.setFont(new Font(\"Futura\", Font.ITALIC, 25));\n" +
                    "        jt.setBackground(Color.decode(\"#EEEEEE\"));\n" +
                    "        jt.setForeground(Color.decode(\"#212121\"));\n" +
                    "        jt.setText(\"\");\n" +
                    "        add(jt, SwingConstants.CENTER);\n" +
                    "    }\n" +
                    "}");
            MainScreen.currentR.setCaretPosition(599);
        });
        javaItem.add(javaDialogItem);


        nMenui.add(javaItem);

        JMenuItem htmlItem = new JMenuItem("HTML file");
        nMenui.add(htmlItem);
        htmlItem.addActionListener((e -> MainScreen.handle("index.html", "SYNTAX_STYLE_HTML", "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<body>\n" +
                "\n" +
                "\t\t<h1>My First Heading</h1>\n" +
                "\n" +
                "\t\t<p>My first paragraph.</p>\n" +
                "\n" +
                "\t</body>\n" +
                "</html>\n")));


        JMenuItem cssItem = new JMenuItem("CSS file");
        nMenui.add(cssItem);
        cssItem.addActionListener((e -> MainScreen.handle("style.css", "SYNTAX_STYLE_CSS", "")));

        JMenuItem latexItem = new JMenuItem("LaTeX file");
        nMenui.add(latexItem);
        latexItem.addActionListener((e -> MainScreen.handle("page.tex", "SYNTAX_STYLE_LATEX", "/begin document")));

        JMenuItem xmlItem = new JMenuItem("XML file");
        nMenui.add(xmlItem);
        xmlItem.addActionListener((e -> MainScreen.handle("schema.xml", "SYNTAX_STYLE_XML", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>")));

        // add
        fMenu.add(nMenui);
        fMenu.add(new JSeparator());

        // exit menu item
        JMenuItem eMenuI = new JMenuItem(" Exit ");
        fMenu.add(eMenuI);

        // exit menu listen
        eMenuI.addActionListener((e)-> System.exit(0));

        // save  item
        JMenuItem sMenui = new JMenuItem(" Save ");
        fMenu.add(sMenui);

        // save listen
        sMenui.addActionListener(event -> {
            Io.save();
        });

        // open item
        JMenuItem oMenui = new JMenuItem(" Open ");
        fMenu.add(oMenui);

        // open listen
        oMenui.addActionListener(event -> {
            try{
                Io.open();
            } catch(IOException ie){

            }

        });

        // Preferences
        JMenuItem prefItem = new JMenuItem("Preferences");
        fMenu.add(new JSeparator());
        fMenu.add(prefItem);
        prefItem.addActionListener((e -> {
            String stylePath = "C:\\Users\\pc\\Documents\\Ed-itPreferences.txt";
            try {
                java.util.List<String> lines = Files.readAllLines(Paths.get(stylePath), Charset.defaultCharset());
                StringBuilder styleBuffer = new StringBuilder();

                // add lines from file to new buffer
                for (String temp : lines) {
                    styleBuffer.append(temp + "\n");
                }

                // add buffer to new tab
                MainScreen.handle("Ed-itPreferences.txt", "SYNTAX_STYLE_CSS", styleBuffer.toString());
                MainScreen.currentR.setCaretPosition(0);
            } catch (IOException ioe) {
                //
                SwingUtilities.invokeLater(() -> new CloseDialog("No prefs file found", "Current path is "
                        + stylePath, true));
            }

        }));


        // Tab menu
        tMenu = new JMenu("Tab");
        tMenu.setMnemonic(KeyEvent.VK_T);

        // clear tab menu item
        JMenuItem clMenui = new JMenuItem("Clear Tab");

        // listen
        clMenui.addActionListener((e -> {
            MainScreen.currentR.setText("");
        }));

        // add
        tMenu.add(clMenui);

        // Close tab item
        JMenuItem cMenui = new JMenuItem("Close Tab ");
        // listen
        cMenui.addActionListener((e -> {
            CloseDialog c = new CloseDialog("Save tab ?",
                    "Tab might not be saved");

            int option = c.show();
            if (option == 0) {
                // yes option
                    Io.save();
                    MainScreen.tabbedPane.removeTabAt(MainScreen.current);
                System.out.print("\nyes\n");

            } else if (option == 1) {
                // no
                    MainScreen.tabbedPane.removeTabAt(MainScreen.current);
                System.out.print("\nno\n");
            }



        }));

        // add
        tMenu.add(cMenui);

        hMenu = new JMenu("Help");

        //about item
        JMenuItem aMenui = new JMenuItem(" About ");

        // listen
        aMenui.addActionListener((e)-> new About());

        // add
        hMenu.add(aMenui);
        sMenu = new JMenu("Syntax");

        //SYNTAX ADDED HERE
        JMenuItem actionSyntax = new JMenuItem("Actionscript");
        actionSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_ACTIONSCRIPT);
        });
        sMenu.add(actionSyntax);


        JMenuItem x86assSyntax = new JMenuItem("x86 Assember");
        x86assSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_X86);
        });
        sMenu.add(x86assSyntax);

        JMenuItem bytecodeSyntax = new JMenuItem("Bytecode");
        bytecodeSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_BBCODE);
        });
        sMenu.add(bytecodeSyntax);

        JMenuItem bashSyntax = new JMenuItem("Bash/Unix");
        bashSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL);
        });
        sMenu.add(bashSyntax);

        JMenuItem cSyntax = new JMenuItem("C");
        cSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
        });
        sMenu.add(cSyntax);

        JMenuItem cppSyntax = new JMenuItem("C++");
        cppSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
        });
        sMenu.add(cppSyntax);

        JMenuItem csharpSyntax = new JMenuItem("C#");
        cppSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSHARP);
        });
        sMenu.add(cppSyntax);

        JMenuItem clojureSyntax = new JMenuItem("Clojure");
        clojureSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CLOJURE);
        });
        sMenu.add(clojureSyntax);

        JMenuItem cssSyntax = new JMenuItem("CSS");
        cssSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
        });
        sMenu.add(cssSyntax);

        JMenuItem dSyntax = new JMenuItem("D");
        dSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_D);
        });
        sMenu.add(dSyntax);

        JMenuItem dockerSyntax = new JMenuItem("DockerFile");
        dockerSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DOCKERFILE);
        });
        sMenu.add(dockerSyntax);

        JMenuItem dartSyntax = new JMenuItem("Dart");
        dartSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DOCKERFILE);
        });
        sMenu.add(dockerSyntax);


        JMenuItem groovySyntax = new JMenuItem("Groovy");
        groovySyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GROOVY);
        });
        sMenu.add(groovySyntax);

        JMenuItem javasSyntax = new JMenuItem("JavaScript");
        javasSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        });
        sMenu.add(javasSyntax);

        JMenuItem jsonSyntax = new JMenuItem("JSON");
        jsonSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON_WITH_COMMENTS);
        });
        sMenu.add(jsonSyntax);


        JMenuItem javaSyntax = new JMenuItem("Java");
        javaSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        });
        sMenu.add(javaSyntax);


        JMenuItem pythonSyntax = new JMenuItem("Python");
        pythonSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
        });
        sMenu.add(pythonSyntax);


        JMenuItem phpSyntax = new JMenuItem("PHP");
        phpSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PHP);
        });
        sMenu.add(phpSyntax);


        JMenuItem xmlSyntax = new JMenuItem("XML");
        xmlSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
        });
        sMenu.add(xmlSyntax);

        JMenuItem mxmlSyntax = new JMenuItem("MXML");
        mxmlSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_MXML);
        });
        sMenu.add(mxmlSyntax);

        JMenuItem yamlSyntax = new JMenuItem("YAML");
        yamlSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_YAML);
        });
        sMenu.add(yamlSyntax);

        JMenuItem latexSyntax = new JMenuItem("LaTeX");
        latexSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX);
        });
        sMenu.add(latexSyntax);

        JMenuItem htmlSyntax = new JMenuItem("HTML");
        htmlSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
        });
        sMenu.add(htmlSyntax);

        JMenuItem rubySyntax = new JMenuItem("Ruby");
        rubySyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_RUBY);
        });
        sMenu.add(rubySyntax);

        JMenuItem sqlSyntax = new JMenuItem("SQL");
        sqlSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
        });
        sMenu.add(sqlSyntax);


        JMenuItem plainSyntax = new JMenuItem("Plain/Text");
        plainSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
        });
        sMenu.add(plainSyntax);


        //RUN MENU
        rMenu = new JMenu("Run ->");

        JMenuItem javaCompileAndRun = new JMenuItem("Complie and run java");
        javaCompileAndRun.addActionListener((e -> {
            try {
                Io.compileJava();
            } catch (IOException c) {

            } catch (InterruptedException in) {
                // wrong
            } catch (Exception exp) {
            }
        }));

        rMenu.add(javaCompileAndRun);


        // add menus to bar
        add(fMenu);

        add(tMenu);

        add(hMenu);

        add(sMenu);

        add(rMenu);
    }
}
