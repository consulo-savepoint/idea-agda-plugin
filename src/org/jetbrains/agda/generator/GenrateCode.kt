package org.jetbrains.agda.generator

import java.util.ArrayList
import com.intellij.util.containers.hash.HashSet
import java.io.FileWriter
import java.util.LinkedHashMap
import java.io.File

fun List<String>.concatenate() : String {
    val builder : StringBuilder = StringBuilder()
    this.forEach { builder.append(it) }
    return builder.toString();
}

fun typesToFunction(aType : Type, usedGenerators : MutableSet<Type>) : String {
    if (aType.names.get(0) == "ListOf") {
        return "SList $ map " + typesToFunction(Type(aType.names.subList(1, aType.names.size)), usedGenerators)
    } else{
        val function = decapFirst(aType.names.concatenate()) + "ToSExpr"
        usedGenerators.add(aType)
        return function
    }
}

fun decapFirst(str : String) = Character.toLowerCase(str.charAt(0)).toString() + str.substring(1);


fun getConstructorArguments(constructor : Constructor) : LinkedHashMap<String, Type> {
    val argsMap = LinkedHashMap<String, Type>()
    for (arg in constructor.args) {
        val argName = decapFirst(arg.names.concatenate())
        if (argsMap.containsKey(argName)) {
            var index = 1;
            while (argsMap.containsKey(argName + index)) {
                index++
            }
            argsMap.put(argName + index, arg)
        } else {
            argsMap.put(argName, arg)
        }

    }
    return argsMap;
}

fun getCodeFor(data : Data, createdGenerators : MutableSet<Type>,  usedGenerators : MutableSet<Type>) : String {
    val builder : StringBuilder = StringBuilder()
    val functionName = data.getFunctionName()
    createdGenerators.add(Type(listOf(data.name)))
    builder.append(functionName).append(" :: ").append(data.name).append(" -> SExpr\n")
    for (constructor in data.constructors) {
        builder.append(functionName).append(" (").append(constructor.name)
        val argsMap = getConstructorArguments(constructor)
        for (argName in argsMap.keySet()) {
            builder.append(" ").append(argName)
        }

        builder.append(") = \n")
        builder.append("     SList [(Atom \"" + constructor.name + "\")")
        for ((arg, aType) in argsMap) {
            builder.append(", \n").append("     " + typesToFunction(aType, usedGenerators) + " " + arg)
        }
        builder.append("]")
        builder.append("\n")
    }
    return builder.toString()
}

fun createDummyFunctions(aType : Type) : String {
    val builder : StringBuilder = StringBuilder()
    val decap = decapFirst(aType.names.concatenate())
    builder.append(decap + "ToSExpr :: " + aType + " -> SExpr").append("\n")
    builder.append(decap + "ToSExpr " + decap + " = " + "SList [(Atom \"_" + aType.names.concatenate() + "_\")]").append("\n\n");
    return builder.toString();
}

fun writeHaskellProtocol() {
    val createdGenerators = HashSet<Type>()
    val usedGenerators = HashSet<Type>()

    val writer = FileWriter("./haskell/Protocol.hs")
    writer.write("module Protocol where\n\n")
    for (dataDeclaration in protocol1.protocolElement) {
        when (dataDeclaration) {
            is HaskellImport ->
                writer.write("import " + dataDeclaration.module + "\n")

            is ExternalConverter ->
                createdGenerators.add(Type(listOf(dataDeclaration.name)))
            is Data -> {
                writer.write(getCodeFor(dataDeclaration, createdGenerators, usedGenerators))
                writer.write("\n")
            }
            else -> {
            }
        }

    }

    usedGenerators.removeAll(createdGenerators);
    for (dataDeclaration in usedGenerators) {
        writer.write(createDummyFunctions(dataDeclaration))
    }
    writer.close()
}

fun writeKotlinProtocol() {
    val createdGenerators = HashSet<Type>()
    val usedGenerators = HashSet<Type>()


    for (dataDeclaration in protocol1.protocolElement) {
        when (dataDeclaration) {
            is Data -> {
                val name = dataDeclaration.name + "Type"
                val writer = FileWriter(File("./ast", name + ".kt"))
                writer.write("package org.jetbrains.agda.ast\n\n")

                writer.write("public open class " + name + "() {}\n\n")



                for (constructor in dataDeclaration.constructors) {
                    val argsMap = getConstructorArguments(constructor)
                    var argsString = ""
                    for ((name, arg) in argsMap) {
                        if (arg.names.size == 1) {
                            argsString += "val " + name + " : " + arg.names.first;
                        } else {
                            argsString += "val " + name + " : Any";
                        }
                    }
                    writer.write("public class " + constructor.name + "() : " + name + "() {}\n")
                }
                writer.close()
            }
            else -> {
            }
        }
    }
}

public fun main(args : Array<String>) {
    writeHaskellProtocol()
    writeKotlinProtocol()
}