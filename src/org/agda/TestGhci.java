package org.agda;

import org.agda.ghci.AgdaExternalAnnotation;
import org.agda.ghci.GHCIProcess;
import org.agda.lisp.LispParser;
import org.agda.lisp.SExpression;
import org.agda.util.FileUtil;

import java.io.*;
import java.util.List;

public class TestGhci {
    public static void main(String[] args) {
        try {
            GHCIProcess process = new GHCIProcess();
            process.init();

            File agdaFile = new File("C:\\Users\\Atsky\\Dropbox\\agda\\src\\test.agda");
            File temp = new File("C:\\Users\\Atsky\\Dropbox\\agda\\src\\test.txt");

            String cmd = "ioTCM \"" + esc(agdaFile.getPath()) + "\" (Just \"" + esc(temp.getPath()) + "\") (cmd_load \"" + esc(agdaFile.getPath()) + "\" [])\n";
            System.out.println(cmd);

            String result = process.execute(cmd);
            System.out.println("[" + result + "]");

            process.stop();

            SExpression expr = new LispParser(FileUtil.readFile(temp)).parseExpression();
            List<AgdaExternalAnnotation> annotations = AgdaExternalAnnotation.parse(expr);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static String esc(String path) {
        return path.replace("\\", "\\\\");
    }
}
