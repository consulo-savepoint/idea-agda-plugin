package org.agda.ghci;


import org.agda.highlight.AgdaCompilerMessage;
import org.agda.lisp.LispParser;
import org.agda.lisp.SExpression;
import org.agda.util.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LaunchAgda {

    public static boolean parseResult(List<SExpression> results, List<AgdaCompilerMessage> messages) {
        String errorMessage = null;
        for (SExpression expression: results) {
            if ("Checked".equals(expression.get(1).getValue())) {
                return true;
            }
            if ("*Error*".equals(expression.get(1).getValue())) {
                errorMessage = expression.get(2).getValue();
            }
            if ("annotation-goto".equals(expression.get(0).getValue())) {
                int index = Integer.parseInt(expression.get(3).getValue());
                messages.add(new AgdaCompilerMessage(index - 1, index - 1, errorMessage));
            }
        }
        return false;
    }

    private static void addMessages(SExpression expression, ArrayList<AgdaCompilerMessage> messages) {
        for (SExpression children: expression.getChildren()) {
            List<SExpression> expressions = children.getChildren();
            int start = Integer.parseInt(expressions.get(0).getValue());
            int end = Integer.parseInt(expressions.get(1).getValue());
            String text = expressions.get(3).getValue();
            messages.add(new AgdaCompilerMessage(start, end, text));
        }
    }

    private static String esc(String path) {
        return path.replace("\\", "\\\\");
    }

    public static List<AgdaExternalAnnotation> load(String path, List<AgdaCompilerMessage> messages) {

        try {
            GHCIProcess ghciProcess = new GHCIProcess();
            ghciProcess.init();
            File agdaFile = new File(path);
            File tempFile = new File(path + ".tmp");

            String cmd = "ioTCM \"" + esc(agdaFile.getPath()) + "\" (Just \"" + esc(tempFile.getPath()) + "\") (cmd_load \"" + esc(agdaFile.getPath()) + "\" [])\n";

            String text = ghciProcess.execute(cmd);

            ghciProcess.stop();
            System.out.println("[" + text + "]");
            List<SExpression> results = GHCIProcess.getResults(text);

            boolean result = LaunchAgda.parseResult(results, messages);
            if (result) {
                SExpression expression = new LispParser(FileUtil.readFile(tempFile)).parseExpression();
                tempFile.delete();
                return AgdaExternalAnnotation.parse(expression);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}
