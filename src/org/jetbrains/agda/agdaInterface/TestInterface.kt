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
import org.jetbrains.agda.parser.ast.ASTNode
import org.jetbrains.agda.parser.ast.getASTNode

/**
 * @author Evgeny.Kurbatsky
 */

public class AgdaInteface() {

    public fun run(fileContent : String) : String {
        val rt = Runtime.getRuntime()
        val agdaPath : String = "C:\\Users\\Atsky\\Dropbox\\agda-plugin\\haskell\\.dist-buildwrapper\\dist\\build\\agda-interface\\agda-interface.exe"
        //val agdaPath : String = "/home/atsky/Dropbox/agda-plugin/haskell/cabal-dev/bin/agda-interface"

        val process : Process = rt.exec(agdaPath)
        val input : InputStream = process.getInputStream()!!
        val writer : Writer = OutputStreamWriter(process.getOutputStream() as OutputStream, "utf8");

        writer.write(fileContent)
        writer.flush()
        writer.close()

        val result = StringBuilder()
        val reader = BufferedReader(InputStreamReader(input, "utf8"))
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
    val fileContent : String = FileUtil.readFile(File("C:/Users/Atsky/Dropbox/agda/src/experiments/test.agda"))!!
    val expression = AgdaInteface().run(fileContent)

    val parser = LispParser(expression)
    val sexpression = parser.parseExpression()
    val node = getASTNode(sexpression.get(1)!!.get(1)!!.get(0)!!)
    System.out.println(node)
}