import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import sun.applet.Main;

import javax.swing.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * This class builds a menu bar and handles the actions associated with it.
 * @author William March - s4916313
 */

public class Menu extends JMenuBar{
    // Create menu;
    public  Menu() {

        // file menu icons


        JMenu fMenu;         // file
        JMenu tMenu;        //  tab
        JMenu hMenu;       //   help
        JMenuItem sMenu;  //    syntax

        // file menu
        fMenu = new JMenu(" File ");
        fMenu.setMnemonic(KeyEvent.VK_F);

        // new item
        JMenuItem nMenui = new JMenu(" New");
        nMenui.setMnemonic(KeyEvent.VK_N);

        JMenuItem plainItem = new JMenuItem("Text file");
        nMenui.add(plainItem);
        plainItem.addActionListener((e -> MainScreen.handle("file.txt", "SYNTAX_STYLE_PLAIN", "")));


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

        JMenuItem fromclipSyntax = new JMenuItem("From clipboard");
        fromclipSyntax.addActionListener((e) -> {
            try {
                Io.cliboardToTab();

            } catch (IOException io) {
                // never happens
            } catch (UnsupportedFlavorException fl) {
                // ^^
            }
        });
        nMenui.add(new JSeparator());
        nMenui.add(fromclipSyntax);


        // new files

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
            CloseDialog c = new CloseDialog();

            int option = c.show();
            if (option == 0) {
                System.out.print(option);
                Io.save();

                MainScreen.tabbedPane.remove(MainScreen.current);

            } else if (option == 1) {
                System.out.print(option);
                MainScreen.tabbedPane.remove(MainScreen.current);
            } else {

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


        JMenuItem xmlSyntax = new JMenuItem("XML");
        xmlSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
        });
        sMenu.add(xmlSyntax);


        JMenuItem latexSyntax = new JMenuItem("LaTeX");
        latexSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX);
        });
        sMenu.add(latexSyntax);


        JMenuItem cssSyntax = new JMenuItem("CSS");
        cssSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
        });
        sMenu.add(cssSyntax);

        JMenuItem htmlSyntax = new JMenuItem("HTML");
        htmlSyntax.addActionListener((e) -> {
            MainScreen.currentR.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
        });
        sMenu.add(htmlSyntax);


        // add menus to bar
        add(fMenu);

        add(tMenu);

        add(hMenu);

        add(sMenu);
    }
}
