package org.agda.ghci;


import com.intellij.openapi.project.Project;
import org.agda.lisp.LispParser;
import org.agda.lisp.SExpression;
import org.agda.util.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LaunchAgda {

    public static boolean parseResult(List<SExpression> results, List<AgdaExternalAnnotation> messages) {
        String errorMessage = null;
        for (SExpression expression: results) {
            if ("Checked".equals(expression.get(1).getValue())) {
                return true;
            }
            if ("*Error*".equals(expression.get(1).getValue())) {
                errorMessage = expression.get(2).getValue();
            }
            if ("*All Goals*".equals(expression.get(1).getValue())) {
                messages.addAll(getGoals(expression.getValue(2)));
            }
            if ("annotation-goto".equals(expression.get(0).getValue())) {
                int index = Integer.parseInt(expression.get(3).getValue());
                messages.add(new AgdaCompilerMessage(index - 1, index - 1, errorMessage));
            }
        }
        return false;
    }

    private static List<AgdaExternalAnnotation> getGoals(String data) {
        List<AgdaExternalAnnotation> annotations = new ArrayList<AgdaExternalAnnotation>();
        int index = 0;
        for (String str: data.split("\n")) {
            int i = str.indexOf(": ");
            if (i > 0) {
                str = str.substring(i + 2);
                annotations.add(new GoalAnnotation(index, str));
            }
            index++;
        }
        return annotations;
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

    public static List<AgdaExternalAnnotation> load(String path, Project project) {

        try {
            GhciProjectComponent component = project.getComponent(GhciProjectComponent.class);
            if (component == null) {
                return null;
            }

            File agdaFile = new File(path);
            File tempFile = new File(path + ".tmp");

            String cmd = "ioTCM \"" + esc(agdaFile.getPath()) + "\" (Just \"" + esc(tempFile.getPath()) + "\") (cmd_load \"" + esc(agdaFile.getPath()) + "\" [])\n";
            System.out.println(cmd);

            String text = component.execute(cmd);

            System.out.println("[" + text + "]");
            List<SExpression> results = GHCIProcess.getResults(text);

            List<AgdaExternalAnnotation> messages = new ArrayList<AgdaExternalAnnotation>();
            LaunchAgda.parseResult(results, messages);
            if (tempFile.exists()) {
                SExpression expression = new LispParser(FileUtil.readFile(tempFile)).parseExpression();
                messages.addAll(AgdaSyntaxAnnotation.parse(expression));
                tempFile.delete();
            }
            return messages;
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}
