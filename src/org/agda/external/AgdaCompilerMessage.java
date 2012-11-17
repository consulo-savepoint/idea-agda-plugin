package org.agda.external;

import com.intellij.openapi.editor.Document;

public class AgdaCompilerMessage extends AgdaExternalAnnotation {
    private String myText;
    private String myFile;
    private int myLineStart;
    private int myLineEnd;
    private int myOffsetStart;
    private int myOffsetEnd;

    public AgdaCompilerMessage(String text) {
        System.out.print("<<" + text + ">>");
        int index = text.indexOf("\\n");
        if (index != -1) {
            myText = text.substring(index + 1);
            myFile = text.substring(0, index);
            index = myFile.lastIndexOf(":");
            if (index != -1) {
                String range = myFile.substring(index + 1);
                parseRange(range);

                myFile = myFile.substring(0, index);
            }
        }
    }

    private void parseRange(String range) {
        if (range.split(",").length == 2) {
            String[] coordinates = range.split(",");
            myLineStart = myLineEnd = Integer.parseInt(coordinates[0]);
            String[] offsetRange = coordinates[1].split("-");
            myOffsetStart = Integer.parseInt(offsetRange[0]);
            myOffsetEnd = Integer.parseInt(offsetRange[1]);
        } else if (range.split(",").length == 3) {
            String[] ranges = range.split("-");
            String[] c1 = ranges[0].split(",");

            myLineStart = Integer.parseInt(c1[0]);
            myOffsetStart = Integer.parseInt(c1[1]);

            String[] c2 = ranges[1].split(",");

            myLineEnd = Integer.parseInt(c2[0]);
            myOffsetEnd= Integer.parseInt(c2[1]);
        }
    }

    public String getText() {
        return myText;
    }

    @Override
    public String toString() {
        return "[" + myText + "]";
    }

    public int getStart(Document document) {
        return document.getLineStartOffset(myLineStart - 1) + myOffsetStart - 1;
    }

    public int getEnd(Document document) {
        return document.getLineStartOffset(myLineEnd - 1) + myOffsetEnd - 1;
    }

    public boolean isValid() {
        return myLineStart > 0;
    }
}
