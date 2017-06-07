import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * This class builds a menu bar and handles the actions associated with it.
 * @author William March - s4916313
 */

public class Menu extends JMenuBar{
    // Create menu;
    public  Menu() {
        JMenu fMenu; // file
        JMenu tMenu; // tab
        JMenu hMenu; // help

        // file menu
        fMenu = new JMenu(" File ");
        fMenu.setMnemonic(KeyEvent.VK_F);

            // new item
            JMenuItem nMenui = new JMenu(" New");
            nMenui.setMnemonic(KeyEvent.VK_N);

                JMenuItem pythonItem = new JMenuItem("Python file");
                nMenui.add(pythonItem);
                pythonItem.addActionListener((e)-> MainScreen.newTab("temp.py", "SYNTAX_STYLE_PYTHON", "#!/usr/bin/env python\n\n" +
                        "# -*- coding: utf-8 -*-\n" +
                        "\n" +
                        "\"\"\"This is a awesome\n" +
                        "        python script!\"\"\"\n\n"));

                JMenuItem javaItem = new JMenuItem("Java file");
                nMenui.add(javaItem);
                javaItem.addActionListener((e)-> MainScreen.newTab("Main.java", "SYNTAX_STYLE_JAVA", "public class Main {\n" +
                        "\t\n" +
                        "\tpublic Main(){}\n" +
                        "\n" +
                        "\tpublic void greet() {\n" +
                        "\t\tsystem.out.println(\"Hello\");\t\n" +
                        "\t}\n" +
                        "\n" +
                        "\tpublic static void main(String[] args) {\n" +
                        "\t\tMain helloWorld = new Main();\n" +
                        "\t\thelloWorld.greet();\n" +
                        "\t}\n" +
                        "}"));

                JMenuItem htmlItem = new JMenuItem("HTML file");
                nMenui.add(htmlItem);
                htmlItem.addActionListener((e -> MainScreen.newTab("index.html", "SYNTAX_STYLE_HTML", "<!DOCTYPE html>\n" +
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
                cssItem.addActionListener((e -> MainScreen.newTab("style.css", "SYNTAX_STYLE_CSS", "")));






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
        //clMenui.addActionListener((e -> MainScreen.je.setText("")));

        // add
        tMenu.add(clMenui);

        // Close tab item
        JMenuItem cMenui = new JMenuItem("Close Tab ");
        // listen
        cMenui.addActionListener((e -> MainScreen.tabbedPane.remove(MainScreen.current)));

        // add
        tMenu.add(cMenui);

        hMenu = new JMenu("Help");

        //about item
       JMenuItem aMenui = new JMenuItem(" About ");

       // listen
       aMenui.addActionListener((e)-> new About());

       // add
       hMenu.add(aMenui);

       // add menus to bar
        add(fMenu);

        add(tMenu);

        add(hMenu);
    }
}
