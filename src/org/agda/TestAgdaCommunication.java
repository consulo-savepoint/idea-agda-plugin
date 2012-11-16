package org.agda;

import org.agda.ghci.AgdaProcess;
import org.agda.ghci.AgdaSyntaxAnnotation;
import org.agda.lisp.LispParser;
import org.agda.lisp.SExpression;
import org.agda.util.FileUtil;

import java.io.*;
import java.util.List;

public class TestAgdaCommunication {
    public static void main(String[] args) {
        try {
            AgdaProcess process = new AgdaProcess();
            process.init();

            File agdaFile = new File("C:\\Users\\Atsky\\Dropbox\\agda\\src\\experiments\\binary.agda");

            String cmd = "IOTCM \"" + esc(agdaFile.getPath()) + "\" NonInteractive Indirect ( Cmd_load_highlighting_info  \"" + esc(agdaFile.getPath()) + "\")\n";
            process.execute(cmd);

            cmd = "IOTCM \"" + esc(agdaFile.getPath()) + "\" NonInteractive Indirect ( Cmd_load \"" + esc(agdaFile.getPath()) + "\" [\".\"] )\n";
            System.out.println(cmd);

            process.execute(cmd, new AgdaProcess.Callback() {

                @Override
                public boolean call(String command) {
                    if (command.contains("agda2-highlight-load-and-delete-action")) {
                        SExpression expr = new LispParser(command).parseExpression();
                        try {
                            File file = new File(expr.getValue(1));
                            String data = FileUtil.readFile(file);
                            file.delete();
                            //System.out.println("{" + data + "}");
                        } catch (IOException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    } else {
                        System.out.println("[" + command + "]");
                    }
                    return true;
                }
            });

            process.stop();
            /*
            SExpression expr = new LispParser(FileUtil.readFile(temp)).parseExpression();
            List<AgdaSyntaxAnnotation> annotations = AgdaSyntaxAnnotation.parse(expr);
            */
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static String esc(String path) {
        return path.replace("\\", "\\\\");
    }
}
