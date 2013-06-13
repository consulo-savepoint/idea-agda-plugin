package org.jetbrains.agda.generator

import java.util.ArrayList

class Type(val names: List<String>) {
    fun equals(other : Any) =
        if (other is Type) {
            names.equals(other.names)
        } else {
            false
        }

    fun hashCode() = names.hashCode()

    fun toString() : String {
        var result = ""
        for (name in names) {
            if (name == names.first) {
                result += name;
            } else {
                result += " " + name;
            }
        }
        return result
    }
}

class Constructor(val name : String, val args : List<Type>) {

}

open class ProtocolElement() {

}

class HaskellImport(val module : String) : ProtocolElement() {

}

class ExternalConverter(val name : String) : ProtocolElement() {

}

class Data(val name : String, val constructors : List<Constructor>) : ProtocolElement() {
    fun getFunctionName() =
        decapFirst(name) + "ToSExpr"

}

public class Protocol(val protocolElement: MutableList<ProtocolElement>) {

}

public fun Protocol.import(name : String) : Unit {
    this.protocolElement.add(HaskellImport(name))
}

public fun Protocol.external(name : String) : Unit {
    this.protocolElement.add(ExternalConverter(name))
}

public fun Protocol.data(name : String, body : MutableList<Constructor>.() -> Unit) : Unit {
    val constructors = ArrayList<Constructor>()
    constructors.body();
    this.protocolElement.add(Data(name, constructors));
}

fun protocol(body : Protocol.() -> Unit) : Protocol {
    val protocol = Protocol(ArrayList<ProtocolElement>())
    protocol.body()
    return protocol
}

fun list(aType : String) = Type(listOf("ListOf", aType))
fun maybe(aType : String) = Type(listOf("Maybe", aType))

fun MutableList<Constructor>.c(name : String, vararg args : Any) {
    this.add(Constructor(name, args.map { if (it is Type) (it as Type) else Type(listOf(it as String)) }))
}
val protocol1 = protocol {
  import("Agda.Syntax.Parser")
  import("Agda.Syntax.Concrete")
  import("Agda.Syntax.Position")
  import("Agda.Syntax.Concrete.Name")
  import("Agda.Syntax.Common")
  import("Agda.Syntax.Fixity")
  import("Agda.Syntax.Notation")
  import("Agda.Syntax.Literal")
  import("Data.List")
  import("Data.Maybe")
  import("SExpr")

  external("Position")
  external("NamePart")
  external("LHS_RHS_WhereClause")
  external("Name_Expr");

  data("Name") {
    c("Name",   "Range", list("NamePart"))
    c("NoName", "Range", "NameId")
  }

  data("QName") {
    c("Qual",  "Name", "QName")
    c("QName", "Name")
  }

  data("Interval") {
    c("Interval", "Position", "Position")
  }
  data("Range") {
      c("Range", list("Interval"))
  }
  data("Induction") {
      c("Inductive")
      c("CoInductive")
  }

  data("Relevance") {
    c("Relevant")    // ^ The argument is (possibly) relevant at compile-time.
    c("NonStrict")   // ^ The argument may never flow into evaluation position.
    c("Irrelevant")  // ^ The argument is irrelevant at compile- and runtime.
    c("Forced")      // ^ The argument can be skipped during equality checking
    c("UnusedArg")   // ^ The polarity checker has determined that this argument
  }


  data("Expr") {
      c("Ident", "QName")
      c("Lit",   "Literal")
      c("QuestionMark", "Range", maybe("Nat"))
      c("Underscore",   "Range", maybe("String"))
      c("RawApp", "Range", list("Expr"))
      c("App", "Range", "Expr", Type(listOf("NamedArg", "Expr")))
      c("OpApp", "Range", "QName", Type(listOf("ListOf", "OpApp", "Expr")))
      c("WithApp", "Range", "Expr", list("Expr"))
      c("HiddenArg", "Range", Type(listOf("Named", "String", "Expr")))
      c("InstanceArg", "Range", Type(listOf("Named", "String", "Expr")))
      c("Lam", "Range", list("LamBinding"), "Expr")
      c("AbsurdLam", "Range", "Hiding")
      c("ExtendedLam", "Range", list("LHS_RHS_WhereClause"))
      c("Fun", "Range", "Expr", "Expr")
      c("Pi", "Telescope", "Expr")
      c("Set", "Range")
      c("Prop", "Range")
      c("SetN", "Range", "Integer")
      c("Rec", "Range", list("Name_Expr"))
      c("RecUpdate", "Range", "Expr", Type(listOf("ListOf", "Name_Expr")))
      c("Let", "Range", list("Declaration"), "Expr")
      c("Paren", "Range", "Expr")
      c("Absurd", "Range")
      c("As", "Range", "Name", "Expr")
      c("Dot", "Range", "Expr")
      c("ETel", "Telescope")
      c("QuoteGoal", "Range", "Name", "Expr")
      c("Quote", "Range")
      c("QuoteTerm", "Range")
      c("Unquote", "Range")
      c("DontCare", "Expr")
  }

  data("LHS") {
      c("LHS", "Pattern", list("Pattern"), list("RewriteEqn"), list("WithExpr"))
      c("Ellipsis", "Range", list("Pattern"), list("RewriteEqn"), list("WithExpr"))
  }

  data("RHS") {
      c("AbsurdRHS")
      c("RHS", "Expr")
  }

  data("WhereClause") {
      c("NoWhere")
      c("AnyWhere", list("Declaration"))
      c("SomeWhere", "Name", list("Declaration"))
  }

  data("Pattern") {
      c("IdentP", "QName")
      c("AppP", "Pattern", Type(listOf("NamedArg", "Pattern")))
      c("RawAppP", "Range", list("Pattern"))
      c("OpAppP", "Range", "QName", list("Pattern"))
      c("HiddenP", "Range", Type(listOf("Named", "String", "Pattern")))
      c("InstanceP", "Range", Type(listOf("Named", "String", "Pattern")))
      c("ParenP", "Range", "Pattern")
      c("WildP", "Range")
      c("AbsurdP", "Range")
      c("AsP", "Range", "Name", "Pattern")
      c("DotP", "Range", "Expr")
      c("LitP", "Literal")
  }

  data("Declaration") {
    c("TypeSig",     "Relevance", "Name", "Expr")
    c("Field",       "Name", Type(listOf("Arg", "Expr")))
    c("FunClause",   "LHS", "RHS", "WhereClause")
    c("DataSig",     "Range", "Induction", "Name", list("LamBinding"), "Expr")
    c("Data",        "Range", "Induction", "Name", list("LamBinding"), maybe("Expr"), list("Declaration"))
    c("RecordSig",   "Range", "Name", list("LamBinding"), "Expr")
    c("Record",      "Range", "Name", maybe("Induction"), maybe("Name"), list("LamBinding"), maybe("Expr"), list("Declaration"))
    c("Infix",       "Fixity", list("Name"))
    c("Syntax",      "Name",  "Notation")
    c("PatternSyn",  "Range", "Name", list("Name"), "Pattern")
    c("Mutual",      "Range", list("Declaration"))
    c("Abstract",    "Range", list("Declaration"))
    c("Private",     "Range", list("Declaration"))
    c("Postulate",   "Range", list("TypeSignature"))
    c("Primitive",   "Range", list("TypeSignature"))
    c("Open",        "Range", "QName", "ImportDirective")
    c("Import",      "Range", "QName", maybe("AsName"), "OpenShortHand", "ImportDirective")
    c("ModuleMacro", "Range", "Name", "ModuleApplication", "OpenShortHand", "ImportDirective")
    c("Module",      "Range", "QName", list("TypedBindings"), list("Declaration"))
    c("Pragma",      "Pragma")
  }
}