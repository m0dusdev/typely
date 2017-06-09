import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

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

        // file menu icons
        ImageIcon pyImg = new ImageIcon("images/pycon..jpg");
        ImageIcon javaImg = new ImageIcon("images/javacon..jpg");
        ImageIcon htmlImg = new ImageIcon("images/htmlicon..jpg");
        ImageIcon cssImg = new ImageIcon("images/cssicon..jpg");
        ImageIcon cImg = new ImageIcon("images/ccon..jpg");
        ImageIcon latexImg = new ImageIcon("images/latexicon...jpg");


        JMenu fMenu; // file
        JMenu tMenu; // tab
        JMenu hMenu; // help

        // file menu
        fMenu = new JMenu(" File ");
        fMenu.setMnemonic(KeyEvent.VK_F);

        // new item
        JMenuItem nMenui = new JMenu(" New");
        nMenui.setMnemonic(KeyEvent.VK_N);

        JMenuItem pythonItem = new JMenuItem("Python file", pyImg);
        nMenui.add(pythonItem);
        pythonItem.addActionListener((e)-> MainScreen.handle("temp.py", "SYNTAX_STYLE_PYTHON", "#!/usr/bin/env python\n\n" +
                "# -*- coding: utf-8 -*-\n" +
                "\n" +
                "\"\"\"This is a awesome\n" +
                "        python script!\"\"\"\n\n" ));


        JMenuItem javaItem = new JMenuItem("Java file", javaImg);
        nMenui.add(javaItem);
        javaItem.addActionListener((e)-> MainScreen.handle("Main.java", "SYNTAX_STYLE_JAVA", "public class Main {\n" +
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

        JMenuItem htmlItem = new JMenuItem("HTML file", htmlImg);
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

        JMenuItem cssItem = new JMenuItem("CSS file", cssImg);
        nMenui.add(cssItem);
        cssItem.addActionListener((e -> MainScreen.handle("style.css", "SYNTAX_STYLE_CSS", "")));

        JMenuItem latexItem = new JMenuItem("LaTeX file", latexImg);
        nMenui.add(latexItem);
        latexItem.addActionListener((e -> MainScreen.handle("page.tex", "SYNTAX_STYLE_LATEX", "\\documentclass{article}\n" +
                "\n" +
                "\\usepackage{fancyhdr} % Required for custom headers\n" +
                "\\usepackage{lastpage} % Required to determine the last page for the footer\n" +
                "\\usepackage{extramarks} % Required for headers and footers\n" +
                "\\usepackage{graphicx} % Required to insert images\n" +
                "\\usepackage{lipsum} % Used for inserting dummy 'Lorem ipsum' text into the template\n" +
                "\n" +
                "% Margins\n" +
                "\\topmargin=-0.45in\n" +
                "\\evensidemargin=0in\n" +
                "\\oddsidemargin=0in\n" +
                "\\textwidth=6.5in\n" +
                "\\textheight=9.0in\n" +
                "\\headsep=0.25in \n" +
                "\n" +
                "\\linespread{1.1} % Line spacing\n" +
                "\n" +
                "% Set up the header and footer\n" +
                "\\pagestyle{fancy}\n" +
                "\\lhead{\\hmwkAuthorName} % Top left header\n" +
                "\\chead{\\hmwkClass\\ (\\hmwkClassInstructor\\ \\hmwkClassTime): \\hmwkTitle} % Top center header\n" +
                "\\rhead{\\firstxmark} % Top right header\n" +
                "\\lfoot{\\lastxmark} % Bottom left footer\n" +
                "\\cfoot{} % Bottom center footer\n" +
                "\\rfoot{Page\\ \\thepage\\ of\\ \\pageref{LastPage}} % Bottom right footer\n" +
                "\\renewcommand\\headrulewidth{0.4pt} % Size of the header rule\n" +
                "\\renewcommand\\footrulewidth{0.4pt} % Size of the footer rule\n" +
                "\n" +
                "\\setlength\\parindent{0pt} % Removes all indentation from paragraphs\n" +
                "\n" +
                "%----------------------------------------------------------------------------------------\n" +
                "%\tDOCUMENT STRUCTURE COMMANDS\n" +
                "%\tSkip this unless you know what you're doing\n" +
                "%----------------------------------------------------------------------------------------\n" +
                "\n" +
                "% Header and footer for when a page split occurs within a problem environment\n" +
                "\\newcommand{\\enterProblemHeader}[1]{\n" +
                "\\nobreak\\extramarks{#1}{#1 continued on next page\\ldots}\\nobreak\n" +
                "\\nobreak\\extramarks{#1 (continued)}{#1 continued on next page\\ldots}\\nobreak\n" +
                "}\n" +
                "\n" +
                "% Header and footer for when a page split occurs between problem environments\n" +
                "\\newcommand{\\exitProblemHeader}[1]{\n" +
                "\\nobreak\\extramarks{#1 (continued)}{#1 continued on next page\\ldots}\\nobreak\n" +
                "\\nobreak\\extramarks{#1}{}\\nobreak\n" +
                "}\n" +
                "\n" +
                "\\setcounter{secnumdepth}{0} % Removes default section numbers\n" +
                "\\newcounter{homeworkProblemCounter} % Creates a counter to keep track of the number of problems\n" +
                "\n" +
                "\\newcommand{\\homeworkProblemName}{}\n" +
                "\\newenvironment{homeworkProblem}[1][Problem \\arabic{homeworkProblemCounter}]{ % Makes a new environment called homeworkProblem which takes 1 argument (custom name) but the default is \"Problem #\"\n" +
                "\\stepcounter{homeworkProblemCounter} % Increase counter for number of problems\n" +
                "\\renewcommand{\\homeworkProblemName}{#1} % Assign \\homeworkProblemName the name of the problem\n" +
                "\\section{\\homeworkProblemName} % Make a section in the document with the custom problem count\n" +
                "\\enterProblemHeader{\\homeworkProblemName} % Header and footer within the environment\n" +
                "}{\n" +
                "\\exitProblemHeader{\\homeworkProblemName} % Header and footer after the environment\n" +
                "}\n" +
                "\n" +
                "\\newcommand{\\problemAnswer}[1]{ % Defines the problem answer command with the content as the only argument\n" +
                "\\noindent\\framebox[\\columnwidth][c]{\\begin{minipage}{0.98\\columnwidth}#1\\end{minipage}} % Makes the box around the problem answer and puts the content inside\n" +
                "}\n" +
                "\n" +
                "\\newcommand{\\homeworkSectionName}{}\n" +
                "\\newenvironment{homeworkSection}[1]{ % New environment for sections within homework problems, takes 1 argument - the name of the section\n" +
                "\\renewcommand{\\homeworkSectionName}{#1} % Assign \\homeworkSectionName to the name of the section from the environment argument\n" +
                "\\subsection{\\homeworkSectionName} % Make a subsection with the custom name of the subsection\n" +
                "\\enterProblemHeader{\\homeworkProblemName\\ [\\homeworkSectionName]} % Header and footer within the environment\n" +
                "}{\n" +
                "\\enterProblemHeader{\\homeworkProblemName} % Header and footer after the environment\n" +
                "}\n" +
                "   \n" +
                "%----------------------------------------------------------------------------------------\n" +
                "%\tNAME AND CLASS SECTION\n" +
                "%----------------------------------------------------------------------------------------\n" +
                "\n" +
                "\\newcommand{\\hmwkTitle}{Assignment\\ \\#1} % Assignment title\n" +
                "\\newcommand{\\hmwkDueDate}{Monday,\\ January\\ 1,\\ 2012} % Due date\n" +
                "\\newcommand{\\hmwkClass}{BIO\\ 101} % Course/class\n" +
                "\\newcommand{\\hmwkClassTime}{10:30am} % Class/lecture time\n" +
                "\\newcommand{\\hmwkClassInstructor}{Jones} % Teacher/lecturer\n" +
                "\\newcommand{\\hmwkAuthorName}{John Smith} % Your name\n" +
                "\n" +
                "%----------------------------------------------------------------------------------------\n" +
                "%\tTITLE PAGE\n" +
                "%----------------------------------------------------------------------------------------\n" +
                "\n" +
                "\\title{\n" +
                "\\vspace{2in}\n" +
                "\\textmd{\\textbf{\\hmwkClass:\\ \\hmwkTitle}}\\\\\n" +
                "\\normalsize\\vspace{0.1in}\\small{Due\\ on\\ \\hmwkDueDate}\\\\\n" +
                "\\vspace{0.1in}\\large{\\textit{\\hmwkClassInstructor\\ \\hmwkClassTime}}\n" +
                "\\vspace{3in}\n" +
                "}\n" +
                "\n" +
                "\\author{\\textbf{\\hmwkAuthorName}}\n" +
                "\\date{} % Insert date here if you want it to appear below your name\n" +
                "\n" +
                "%----------------------------------------------------------------------------------------\n" +
                "\n" +
                "\\begin{document}\n" +
                "\n" +
                "\\maketitle\n" +
                "\n" +
                "%----------------------------------------------------------------------------------------\n" +
                "%\tTABLE OF CONTENTS\n" +
                "%----------------------------------------------------------------------------------------\n" +
                "\n" +
                "%\\setcounter{tocdepth}{1} % Uncomment this line if you don't want subsections listed in the ToC\n" +
                "\n" +
                "\\newpage\n" +
                "\\tableofcontents\n" +
                "\\newpage\n" +
                "\n" +
                "%----------------------------------------------------------------------------------------\n" +
                "%\tPROBLEM 1\n" +
                "%----------------------------------------------------------------------------------------\n" +
                "\n" +
                "% To have just one problem per page, simply put a \\clearpage after each problem\n" +
                "\n" +
                "\\begin{homeworkProblem}\n" +
                "\\lipsum[1]\\vspace{10pt} % Question\n" +
                "\n" +
                "\\problemAnswer{ % Answer\n" +
                "\\begin{center}\n" +
                "\\includegraphics[width=0.75\\columnwidth]{example_figure} % Example image\n" +
                "\\end{center}\n" +
                "\n" +
                "\\lipsum[2]\n" +
                "}\n" +
                "\\end{homeworkProblem}\n" +
                "\n" +
                "%----------------------------------------------------------------------------------------\n" +
                "%\tPROBLEM 2\n" +
                "%----------------------------------------------------------------------------------------\n" +
                "\n" +
                "\\begin{homeworkProblem}[Exercise \\#\\arabic{homeworkProblemCounter}] % Custom section title\n" +
                "\\lipsum[3] % Question\n" +
                "\n" +
                "%--------------------------------------------\n" +
                "\n" +
                "\\begin{homeworkSection}{(a)} % Section within problem\n" +
                "\\lipsum[4]\\vspace{10pt} % Question\n" +
                "\n" +
                "\\problemAnswer{ % Answer\n" +
                "\\lipsum[5]\n" +
                "}\n" +
                "\\end{homeworkSection}\n" +
                "\n" +
                "%--------------------------------------------\n" +
                "\n" +
                "\\begin{homeworkSection}{(b)} % Section within problem\n" +
                "\\problemAnswer{ % Answer\n" +
                "\\lipsum[6]\n" +
                "}\n" +
                "\\end{homeworkSection}\n" +
                "\n" +
                "%--------------------------------------------\n" +
                "\n" +
                "\\end{homeworkProblem}\n" +
                "\n" +
                "%----------------------------------------------------------------------------------------\n" +
                "%\tPROBLEM 3\n" +
                "%----------------------------------------------------------------------------------------\n" +
                "\n" +
                "\\begin{homeworkProblem}[Prob. \\Roman{homeworkProblemCounter}] % Roman numerals\n" +
                "\n" +
                "%--------------------------------------------\n" +
                "\n" +
                "\\begin{homeworkSection}{\\homeworkProblemName:~(a)} % Using the problem name elsewhere\n" +
                "\\problemAnswer{ % Answer\n" +
                "\\lipsum[7]\n" +
                "}\n" +
                "\\end{homeworkSection}\n" +
                "\n" +
                "%--------------------------------------------\n" +
                "\n" +
                "\\begin{homeworkSection}{\\homeworkProblemName:~(b)}\n" +
                "\\lipsum[8]\\vspace{10pt} % Question\n" +
                "\n" +
                "\\problemAnswer{ % Answer\n" +
                "\\lipsum[9]\n" +
                "}\n" +
                "\\end{homeworkSection}\n" +
                "\n" +
                "%--------------------------------------------\n" +
                "\n" +
                "\\end{homeworkProblem}\n" +
                "\n" +
                "%----------------------------------------------------------------------------------------\n" +
                "%\tPROBLEM 4\n" +
                "%----------------------------------------------------------------------------------------\n" +
                "\n" +
                "\\begin{homeworkProblem}[Prob. \\Roman{homeworkProblemCounter}] % Roman numerals\n" +
                "\\problemAnswer{ % Answer\n" +
                "\\lipsum[10]\n" +
                "}\n" +
                "\\end{homeworkProblem}\n" +
                "\n" +
                "%----------------------------------------------------------------------------------------\n" +
                "\n" +
                "\\end{document}\n")));


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
            MainScreen.getRarea().setText("");
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
                MainScreen.current--;
            } else if (option == 1) {
                System.out.print(option);
                MainScreen.tabbedPane.remove(MainScreen.current);
                MainScreen.current--;
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

        // add menus to bar
        add(fMenu);

        add(tMenu);

        add(hMenu);
    }
}
