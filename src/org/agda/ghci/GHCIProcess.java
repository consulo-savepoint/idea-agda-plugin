package org.agda.ghci;


import org.agda.lisp.LispParser;
import org.agda.lisp.SExpression;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class GHCIProcess {
    private OutputStreamWriter myWriter;
    private Process myProcess;
    private InputStream myInput;

    public GHCIProcess() {
    }

    public static String readData(InputStream input, int timeout, String s) throws IOException {
        StringBuilder builder = new StringBuilder();
        long time = System.currentTimeMillis();
        byte[] bigBuffer = new byte[0];
        while (true) {
            int available = input.available();
            if (available == 0) {
                if (bigBuffer.length > 0) {
                    ByteArrayInputStream stream = new ByteArrayInputStream(bigBuffer);
                    InputStreamReader inputStreamReader = new InputStreamReader(stream, "UTF-8");
                    int ch;
                    while ((ch = inputStreamReader.read()) != -1) {
                        builder.append((char) ch);
                    }
                    String str = builder.toString();
                    bigBuffer = new byte[0];
                    if (str.contains(s)) {
                        break;
                    }
                    continue;
                }
                long diff = System.currentTimeMillis() - time;
                if (diff > timeout) {
                    System.out.println("Time out: " + diff  );
                    break;
                } else {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                time = System.currentTimeMillis();

                final byte[] newBigBuffer = new byte[bigBuffer.length + available];
                System.arraycopy(bigBuffer, 0, newBigBuffer, 0, bigBuffer.length);
                input.read(newBigBuffer, bigBuffer.length, available);

                bigBuffer = newBigBuffer;
            }
        }
        return builder.toString();
    }

    public void init() throws IOException {
        Runtime rt = Runtime.getRuntime();

        myProcess = rt.exec("ghci");

        myInput = myProcess.getInputStream();
        myWriter = new OutputStreamWriter(myProcess.getOutputStream());

        GHCIProcess.readData(myInput, 10000, ">");

        execute(":set -package Agda\n", ">");
        execute(":module Agda.Interaction.GhciTop\n", ">");
    }

    public String execute(String cmd) throws IOException {
        return execute(cmd, "Agda.Interaction.GhciTop>");
    }

    public String execute(String cmd, String waitFor) throws IOException {
        myWriter.write(cmd);
        myWriter.flush();
        return GHCIProcess.readData(myInput, 10000, waitFor);
    }

    public void stop() {
        try {
            write(":q\n");
            GHCIProcess.readData(myInput, 60000, "Leaving GHCi.");
            myProcess.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static List<SExpression> getResults(String text) {
        List<SExpression> expressions = new ArrayList<SExpression>();
        while (true) {
            int index = text.indexOf("agda2_mode_code");
            if (index < 0) {
                return expressions;
            }
            index += "agda2_mode_code".length() + 1;
            text = text.substring(index);
            expressions.add(new LispParser(text).parseExpression());
        }
    }

    public void write(String text) throws IOException {
        myWriter.write(text);
        myWriter.flush();
    }

    public Process getProcess() {
        return myProcess;
    }

    public InputStream getInput() {
        return myInput;
    }
}
