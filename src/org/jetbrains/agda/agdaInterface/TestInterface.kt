package org.jetbrains.agda.agdaInterface

import java.io.OutputStreamWriter
import java.io.InputStream
import java.io.Writer
import java.io.OutputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import org.jetbrains.agda.lisp.LispParser
import java.io.File
import org.jetbrains.agda.util.FileUtil

/**
 * @author Evgeny.Kurbatsky
 */

public class AgdaInteface() {
    var myProcess : Process? = null;
    var myInput : InputStream? = null;
    var myWriter : Writer? = null;

    public fun run(fileContent : String) : String {
        val rt = Runtime.getRuntime()
        val agdaPath : String = "C:\\Users\\Atsky\\Dropbox\\agda-plugin\\haskell\\.dist-buildwrapper\\dist\\build\\agda-interface\\agda-interface.exe"
        //val agdaPath : String = "/home/atsky/Dropbox/agda-plugin/haskell/cabal-dev/bin/agda-interface"

        myProcess = rt.exec(agdaPath)
        myInput = myProcess!!.getInputStream()
        myWriter = OutputStreamWriter(myProcess!!.getOutputStream() as OutputStream, "utf8")

        myWriter!!.write(fileContent)
        myWriter!!.flush()
        myWriter!!.close()

        val result = StringBuilder()
        val reader = BufferedReader(InputStreamReader(myInput!!, "utf8"))
        while (true) {
            val line = reader.readLine()
            if (line != null) {
                result.append(line + "\n")
            } else {
                break;
            }
        }
        return result.toString()
    }


}


public fun main(args : Array<String>) {
    val fileContent : String = FileUtil.readFile(File("/home/atsky/Dropbox/agda/src/experiments/test.agda"))!!
    val expression = AgdaInteface().run(fileContent)

    val parser = LispParser(expression)
    val sexpression = parser.parseExpression()
    System.out.println(expression)
}