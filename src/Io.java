import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.filechooser.FileFilter;


/**
 * A class that deals with file Io
 * @author William March s4916313
 */

public  class Io {

    static String syntaxFromFile = "PLAIN";
    static String toSave;

    /**
     * Save - saves gets text from current Pane - saves text to file.
     */

    static void save() {

        // obtain reference of currently selected RSyntax
        toSave = MainScreen.currentR.getText();





        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(MainScreen.tabbedPane) == JFileChooser.APPROVE_OPTION) {

            try {
                File file = fileChooser.getSelectedFile();

                // create file
                PrintWriter out = new PrintWriter(file);

                // write string to file
                out.write(toSave);
                out.flush();
                out.close();
            } catch (FileNotFoundException f){
                f.printStackTrace();
            } finally {
                JInternalFrame je;
                je = (JInternalFrame) MainScreen.tabbedPane.getComponentAt(MainScreen.current);
            }
        }
    }

    /**
     * Open File, pass to a new Note object
     * @throws IOException
     */
    static void open() throws IOException{

        JFileChooser fileChooser;


        fileChooser = new JFileChooser("c:");
        fileChooser.setDialogType(1);


        if (fileChooser.showOpenDialog(MainScreen.tabbedPane) == JFileChooser.APPROVE_OPTION) {

            // get selected file location  - add to String type list - strip "[]" - add to text area
            String path = fileChooser.getSelectedFile().getPath();

            File tempFile = fileChooser.getSelectedFile();

            System.out.println(path);


            List<String> lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());


            StringBuilder buffer = new StringBuilder();
            for (String Final : lines) {
                buffer.append(Final + "\n");
            }


            String fileText = buffer.toString();

            System.err.print(buffer);

            if(path.contains(".py")){
                syntaxFromFile = "SYNTAX_STYLE_PYTHON";

            }else if(path.contains(".java")){
                syntaxFromFile = "SYNTAX_STYLE_JAVA";

            }else if(path.contains(".html")){
                syntaxFromFile = "SYNTAX_STYLE_HTML";

            }else if(path.contains(".css")){
                syntaxFromFile = "SYNTAX_STYLE_CSS";

            }else if(path.contains(".txt")){
                syntaxFromFile = "SYNTAX_STYLE_PLAIN";

            } else if (path.contains(".h")) {
                syntaxFromFile = "SYNTAX_STYLE_C";
            }

            MainScreen.tabbedPane.add(path, new Note(fileText, syntaxFromFile, false));

            MainScreen.tabbedPane.setSelectedIndex(MainScreen.current + 1);




        }
}}
