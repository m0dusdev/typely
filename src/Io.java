import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * A class that deals with file Io
 * @author William March s4916313
 */

public  class Io {

    static String syntaxFromFile = "PLAIN";

    /**
     * Save - saves gets text from current Pane - saves text to file.
     */

    static void save(String toSave) {
        // add fiel referecen


        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(MainScreen.tabbedPane) == JFileChooser.APPROVE_OPTION) {

            try {
                File file = fileChooser.getSelectedFile();

                // create file
                PrintWriter out = new PrintWriter(file);

                // write string to file
                //out.write(toSave);
                out.flush();
                out.close();
            } catch (FileNotFoundException f){
                f.printStackTrace();
            }
        }
    }

    /**
     * Open File, pass to a new Note object
     * @throws IOException
     */
    static void open() throws IOException{
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(MainScreen.tabbedPane) == JFileChooser.APPROVE_OPTION) {

            // get selected file location  - add to String type list - strip "[]" - add to text area
            String path = fileChooser.getSelectedFile().getPath();

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

            // ADDMORE SYNTAX

                List<String> lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
                String toSend = lines.toString().replaceAll("^.|.$", "");

                // create new instance of the note class with text from file passed - add tab to tab pane
                MainScreen.handle("FILE 1", "SYNTAX_STYLE_LATEX", null, 1);
            }
        }
}}
