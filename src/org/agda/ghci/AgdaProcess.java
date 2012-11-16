package org.agda.ghci;


import org.agda.lisp.LispParser;
import org.agda.lisp.SExpression;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AgdaProcess {
    private OutputStreamWriter myWriter;
    private Process myProcess;
    private InputStream myInput;

    public AgdaProcess() {
    }

    public static void readData(InputStream input, int timeout, String finish, Callback callback) throws IOException {
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
                    while (str.contains("\n")) {
                        String start = str.substring(0, str.indexOf("\n") + 1);
                        if (!callback.call(start)) {
                            break;
                        }
                        String end2 = str.substring(str.indexOf("\n") + 1);
                        builder = new StringBuilder();
                        builder.append(end2);
                        str = builder.toString();
                    }
                    if (str.contains(finish)) {
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
    }

    public void init() throws IOException {
        Runtime rt = Runtime.getRuntime();

        myProcess = rt.exec("C:\\Users\\Atsky\\Application Data\\cabal\\bin\\agda.exe --interaction");

        myInput = myProcess.getInputStream();
        myWriter = new OutputStreamWriter(myProcess.getOutputStream());

        AgdaProcess.readData(myInput, 10000, "Agda2>");
    }

    private static String readData(InputStream input, int timeout, String finish) throws IOException {
        final StringBuilder builder = new StringBuilder();
        readData(input, timeout, finish, new Callback() {
            @Override
            public boolean call(String command) {
                builder.append(command + "\n");
                return true;
            }
        });
        return builder.toString();
    }

    public synchronized String execute(String cmd) throws IOException {
        return execute(cmd, "Agda2>");
    }

    public synchronized String execute(String cmd, String waitFor) throws IOException {
        myWriter.write(cmd);
        myWriter.flush();
        return AgdaProcess.readData(myInput, 10000, waitFor);
    }

    public void stop() {
        try {
            myWriter.flush();
            myWriter.close();
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


    public void execute(String cmd, Callback callback) throws IOException {
        myWriter.write(cmd);
        myWriter.flush();

        readData(myInput, 10000, "Agda2>", callback);
    }

    public static interface Callback {
        public boolean call(String command);
    }
}
