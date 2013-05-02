module Protocol where

import Agda.Syntax.Parser
import Agda.Syntax.Concrete
import Agda.Syntax.Position
import Agda.Syntax.Concrete.Name
import Agda.Syntax.Common
import Agda.Syntax.Fixity
import Agda.Syntax.Notation
import Agda.Syntax.Literal
import Data.List
import Data.Maybe
import SExpr
nameToSExpr :: Name -> SExpr
nameToSExpr (Name range listOfNamePart) = 
     SList [(Atom "Name"), 
     rangeToSExpr range, 
     SList $ map namePartToSExpr listOfNamePart]
nameToSExpr (NoName range nameId) = 
     SList [(Atom "NoName"), 
     rangeToSExpr range, 
     nameIdToSExpr nameId]

qNameToSExpr :: QName -> SExpr
qNameToSExpr (Qual name qName) = 
     SList [(Atom "Qual"), 
     nameToSExpr name, 
     qNameToSExpr qName]
qNameToSExpr (QName name) = 
     SList [(Atom "QName"), 
     nameToSExpr name]

intervalToSExpr :: Interval -> SExpr
intervalToSExpr (Interval position position1) = 
     SList [(Atom "Interval"), 
     positionToSExpr position, 
     positionToSExpr position1]

rangeToSExpr :: Range -> SExpr
rangeToSExpr (Range listOfInterval) = 
     SList [(Atom "Range"), 
     SList $ map intervalToSExpr listOfInterval]

inductionToSExpr :: Induction -> SExpr
inductionToSExpr (Inductive) = 
     SList [(Atom "Inductive")]
inductionToSExpr (CoInductive) = 
     SList [(Atom "CoInductive")]

relevanceToSExpr :: Relevance -> SExpr
relevanceToSExpr (Relevant) = 
     SList [(Atom "Relevant")]
relevanceToSExpr (NonStrict) = 
     SList [(Atom "NonStrict")]
relevanceToSExpr (Irrelevant) = 
     SList [(Atom "Irrelevant")]
relevanceToSExpr (Forced) = 
     SList [(Atom "Forced")]
relevanceToSExpr (UnusedArg) = 
     SList [(Atom "UnusedArg")]

exprToSExpr :: Expr -> SExpr
exprToSExpr (Ident qName) = 
     SList [(Atom "Ident"), 
     qNameToSExpr qName]
exprToSExpr (Lit literal) = 
     SList [(Atom "Lit"), 
     literalToSExpr literal]
exprToSExpr (QuestionMark range maybeNat) = 
     SList [(Atom "QuestionMark"), 
     rangeToSExpr range, 
     maybeNatToSExpr maybeNat]
exprToSExpr (Underscore range maybeString) = 
     SList [(Atom "Underscore"), 
     rangeToSExpr range, 
     maybeStringToSExpr maybeString]
exprToSExpr (RawApp range listOfExpr) = 
     SList [(Atom "RawApp"), 
     rangeToSExpr range, 
     SList $ map exprToSExpr listOfExpr]
exprToSExpr (App range expr namedArgExpr) = 
     SList [(Atom "App"), 
     rangeToSExpr range, 
     exprToSExpr expr, 
     namedArgExprToSExpr namedArgExpr]
exprToSExpr (OpApp range qName listOfOpAppExpr) = 
     SList [(Atom "OpApp"), 
     rangeToSExpr range, 
     qNameToSExpr qName, 
     SList $ map opAppExprToSExpr listOfOpAppExpr]
exprToSExpr (WithApp range expr listOfExpr) = 
     SList [(Atom "WithApp"), 
     rangeToSExpr range, 
     exprToSExpr expr, 
     SList $ map exprToSExpr listOfExpr]
exprToSExpr (HiddenArg range namedStringExpr) = 
     SList [(Atom "HiddenArg"), 
     rangeToSExpr range, 
     namedStringExprToSExpr namedStringExpr]
exprToSExpr (InstanceArg range namedStringExpr) = 
     SList [(Atom "InstanceArg"), 
     rangeToSExpr range, 
     namedStringExprToSExpr namedStringExpr]
exprToSExpr (Lam range listOfLamBinding expr) = 
     SList [(Atom "Lam"), 
     rangeToSExpr range, 
     SList $ map lamBindingToSExpr listOfLamBinding, 
     exprToSExpr expr]
exprToSExpr (AbsurdLam range hiding) = 
     SList [(Atom "AbsurdLam"), 
     rangeToSExpr range, 
     hidingToSExpr hiding]
exprToSExpr (ExtendedLam range listOfLHS_RHS_WhereClause) = 
     SList [(Atom "ExtendedLam"), 
     rangeToSExpr range, 
     SList $ map lHS_RHS_WhereClauseToSExpr listOfLHS_RHS_WhereClause]
exprToSExpr (Fun range expr expr1) = 
     SList [(Atom "Fun"), 
     rangeToSExpr range, 
     exprToSExpr expr, 
     exprToSExpr expr1]
exprToSExpr (Pi telescope expr) = 
     SList [(Atom "Pi"), 
     telescopeToSExpr telescope, 
     exprToSExpr expr]
exprToSExpr (Set range) = 
     SList [(Atom "Set"), 
     rangeToSExpr range]
exprToSExpr (Prop range) = 
     SList [(Atom "Prop"), 
     rangeToSExpr range]
exprToSExpr (SetN range integer) = 
     SList [(Atom "SetN"), 
     rangeToSExpr range, 
     integerToSExpr integer]
exprToSExpr (Rec range listOfName_Expr) = 
     SList [(Atom "Rec"), 
     rangeToSExpr range, 
     SList $ map name_ExprToSExpr listOfName_Expr]
exprToSExpr (RecUpdate range expr listOfName_Expr) = 
     SList [(Atom "RecUpdate"), 
     rangeToSExpr range, 
     exprToSExpr expr, 
     SList $ map name_ExprToSExpr listOfName_Expr]
exprToSExpr (Let range listOfDeclaration expr) = 
     SList [(Atom "Let"), 
     rangeToSExpr range, 
     SList $ map declarationToSExpr listOfDeclaration, 
     exprToSExpr expr]
exprToSExpr (Paren range expr) = 
     SList [(Atom "Paren"), 
     rangeToSExpr range, 
     exprToSExpr expr]
exprToSExpr (Absurd range) = 
     SList [(Atom "Absurd"), 
     rangeToSExpr range]
exprToSExpr (As range name expr) = 
     SList [(Atom "As"), 
     rangeToSExpr range, 
     nameToSExpr name, 
     exprToSExpr expr]
exprToSExpr (Dot range expr) = 
     SList [(Atom "Dot"), 
     rangeToSExpr range, 
     exprToSExpr expr]
exprToSExpr (ETel telescope) = 
     SList [(Atom "ETel"), 
     telescopeToSExpr telescope]
exprToSExpr (QuoteGoal range name expr) = 
     SList [(Atom "QuoteGoal"), 
     rangeToSExpr range, 
     nameToSExpr name, 
     exprToSExpr expr]
exprToSExpr (Quote range) = 
     SList [(Atom "Quote"), 
     rangeToSExpr range]
exprToSExpr (QuoteTerm range) = 
     SList [(Atom "QuoteTerm"), 
     rangeToSExpr range]
exprToSExpr (Unquote range) = 
     SList [(Atom "Unquote"), 
     rangeToSExpr range]
exprToSExpr (DontCare expr) = 
     SList [(Atom "DontCare"), 
     exprToSExpr expr]

lHSToSExpr :: LHS -> SExpr
lHSToSExpr (LHS pattern listOfPattern listOfRewriteEqn listOfWithExpr) = 
     SList [(Atom "LHS"), 
     patternToSExpr pattern, 
     SList $ map patternToSExpr listOfPattern, 
     SList $ map rewriteEqnToSExpr listOfRewriteEqn, 
     SList $ map withExprToSExpr listOfWithExpr]
lHSToSExpr (Ellipsis range listOfPattern listOfRewriteEqn listOfWithExpr) = 
     SList [(Atom "Ellipsis"), 
     rangeToSExpr range, 
     SList $ map patternToSExpr listOfPattern, 
     SList $ map rewriteEqnToSExpr listOfRewriteEqn, 
     SList $ map withExprToSExpr listOfWithExpr]

rHSToSExpr :: RHS -> SExpr
rHSToSExpr (AbsurdRHS) = 
     SList [(Atom "AbsurdRHS")]
rHSToSExpr (RHS expr) = 
     SList [(Atom "RHS"), 
     exprToSExpr expr]

whereClauseToSExpr :: WhereClause -> SExpr
whereClauseToSExpr (NoWhere) = 
     SList [(Atom "NoWhere")]
whereClauseToSExpr (AnyWhere listOfDeclaration) = 
     SList [(Atom "AnyWhere"), 
     SList $ map declarationToSExpr listOfDeclaration]
whereClauseToSExpr (SomeWhere name listOfDeclaration) = 
     SList [(Atom "SomeWhere"), 
     nameToSExpr name, 
     SList $ map declarationToSExpr listOfDeclaration]

patternToSExpr :: Pattern -> SExpr
patternToSExpr (IdentP qName) = 
     SList [(Atom "IdentP"), 
     qNameToSExpr qName]
patternToSExpr (AppP pattern namedArgPattern) = 
     SList [(Atom "AppP"), 
     patternToSExpr pattern, 
     namedArgPatternToSExpr namedArgPattern]
patternToSExpr (RawAppP range listOfPattern) = 
     SList [(Atom "RawAppP"), 
     rangeToSExpr range, 
     SList $ map patternToSExpr listOfPattern]
patternToSExpr (OpAppP range qName listOfPattern) = 
     SList [(Atom "OpAppP"), 
     rangeToSExpr range, 
     qNameToSExpr qName, 
     SList $ map patternToSExpr listOfPattern]
patternToSExpr (HiddenP range namedStringPattern) = 
     SList [(Atom "HiddenP"), 
     rangeToSExpr range, 
     namedStringPatternToSExpr namedStringPattern]
patternToSExpr (InstanceP range namedStringPattern) = 
     SList [(Atom "InstanceP"), 
     rangeToSExpr range, 
     namedStringPatternToSExpr namedStringPattern]
patternToSExpr (ParenP range pattern) = 
     SList [(Atom "ParenP"), 
     rangeToSExpr range, 
     patternToSExpr pattern]
patternToSExpr (WildP range) = 
     SList [(Atom "WildP"), 
     rangeToSExpr range]
patternToSExpr (AbsurdP range) = 
     SList [(Atom "AbsurdP"), 
     rangeToSExpr range]
patternToSExpr (AsP range name pattern) = 
     SList [(Atom "AsP"), 
     rangeToSExpr range, 
     nameToSExpr name, 
     patternToSExpr pattern]
patternToSExpr (DotP range expr) = 
     SList [(Atom "DotP"), 
     rangeToSExpr range, 
     exprToSExpr expr]
patternToSExpr (LitP literal) = 
     SList [(Atom "LitP"), 
     literalToSExpr literal]

declarationToSExpr :: Declaration -> SExpr
declarationToSExpr (TypeSig relevance name expr) = 
     SList [(Atom "TypeSig"), 
     relevanceToSExpr relevance, 
     nameToSExpr name, 
     exprToSExpr expr]
declarationToSExpr (Field name argExpr) = 
     SList [(Atom "Field"), 
     nameToSExpr name, 
     argExprToSExpr argExpr]
declarationToSExpr (FunClause lHS rHS whereClause) = 
     SList [(Atom "FunClause"), 
     lHSToSExpr lHS, 
     rHSToSExpr rHS, 
     whereClauseToSExpr whereClause]
declarationToSExpr (DataSig range induction name listOfLamBinding expr) = 
     SList [(Atom "DataSig"), 
     rangeToSExpr range, 
     inductionToSExpr induction, 
     nameToSExpr name, 
     SList $ map lamBindingToSExpr listOfLamBinding, 
     exprToSExpr expr]
declarationToSExpr (Data range induction name listOfLamBinding maybeExpr listOfDeclaration) = 
     SList [(Atom "Data"), 
     rangeToSExpr range, 
     inductionToSExpr induction, 
     nameToSExpr name, 
     SList $ map lamBindingToSExpr listOfLamBinding, 
     maybeExprToSExpr maybeExpr, 
     SList $ map declarationToSExpr listOfDeclaration]
declarationToSExpr (RecordSig range name listOfLamBinding expr) = 
     SList [(Atom "RecordSig"), 
     rangeToSExpr range, 
     nameToSExpr name, 
     SList $ map lamBindingToSExpr listOfLamBinding, 
     exprToSExpr expr]
declarationToSExpr (Record range name maybeInduction maybeName listOfLamBinding maybeExpr listOfDeclaration) = 
     SList [(Atom "Record"), 
     rangeToSExpr range, 
     nameToSExpr name, 
     maybeInductionToSExpr maybeInduction, 
     maybeNameToSExpr maybeName, 
     SList $ map lamBindingToSExpr listOfLamBinding, 
     maybeExprToSExpr maybeExpr, 
     SList $ map declarationToSExpr listOfDeclaration]
declarationToSExpr (Infix fixity listOfName) = 
     SList [(Atom "Infix"), 
     fixityToSExpr fixity, 
     SList $ map nameToSExpr listOfName]
declarationToSExpr (Syntax name notation) = 
     SList [(Atom "Syntax"), 
     nameToSExpr name, 
     notationToSExpr notation]
declarationToSExpr (PatternSyn range name listOfName pattern) = 
     SList [(Atom "PatternSyn"), 
     rangeToSExpr range, 
     nameToSExpr name, 
     SList $ map nameToSExpr listOfName, 
     patternToSExpr pattern]
declarationToSExpr (Mutual range listOfDeclaration) = 
     SList [(Atom "Mutual"), 
     rangeToSExpr range, 
     SList $ map declarationToSExpr listOfDeclaration]
declarationToSExpr (Abstract range listOfDeclaration) = 
     SList [(Atom "Abstract"), 
     rangeToSExpr range, 
     SList $ map declarationToSExpr listOfDeclaration]
declarationToSExpr (Private range listOfDeclaration) = 
     SList [(Atom "Private"), 
     rangeToSExpr range, 
     SList $ map declarationToSExpr listOfDeclaration]
declarationToSExpr (Postulate range listOfTypeSignature) = 
     SList [(Atom "Postulate"), 
     rangeToSExpr range, 
     SList $ map typeSignatureToSExpr listOfTypeSignature]
declarationToSExpr (Primitive range listOfTypeSignature) = 
     SList [(Atom "Primitive"), 
     rangeToSExpr range, 
     SList $ map typeSignatureToSExpr listOfTypeSignature]
declarationToSExpr (Open range qName importDirective) = 
     SList [(Atom "Open"), 
     rangeToSExpr range, 
     qNameToSExpr qName, 
     importDirectiveToSExpr importDirective]
declarationToSExpr (Import range qName maybeAsName openShortHand importDirective) = 
     SList [(Atom "Import"), 
     rangeToSExpr range, 
     qNameToSExpr qName, 
     maybeAsNameToSExpr maybeAsName, 
     openShortHandToSExpr openShortHand, 
     importDirectiveToSExpr importDirective]
declarationToSExpr (ModuleMacro range name moduleApplication openShortHand importDirective) = 
     SList [(Atom "ModuleMacro"), 
     rangeToSExpr range, 
     nameToSExpr name, 
     moduleApplicationToSExpr moduleApplication, 
     openShortHandToSExpr openShortHand, 
     importDirectiveToSExpr importDirective]
declarationToSExpr (Module range qName listOfTypedBindings listOfDeclaration) = 
     SList [(Atom "Module"), 
     rangeToSExpr range, 
     qNameToSExpr qName, 
     SList $ map typedBindingsToSExpr listOfTypedBindings, 
     SList $ map declarationToSExpr listOfDeclaration]
declarationToSExpr (Pragma pragma) = 
     SList [(Atom "Pragma"), 
     pragmaToSExpr pragma]

namedArgExprToSExpr :: NamedArg Expr -> SExpr
namedArgExprToSExpr namedArgExpr = SList [(Atom "_NamedArgExpr_")]

notationToSExpr :: Notation -> SExpr
notationToSExpr notation = SList [(Atom "_Notation_")]

typedBindingsToSExpr :: TypedBindings -> SExpr
typedBindingsToSExpr typedBindings = SList [(Atom "_TypedBindings_")]

fixityToSExpr :: Fixity -> SExpr
fixityToSExpr fixity = SList [(Atom "_Fixity_")]

importDirectiveToSExpr :: ImportDirective -> SExpr
importDirectiveToSExpr importDirective = SList [(Atom "_ImportDirective_")]

maybeStringToSExpr :: Maybe String -> SExpr
maybeStringToSExpr maybeString = SList [(Atom "_MaybeString_")]

maybeInductionToSExpr :: Maybe Induction -> SExpr
maybeInductionToSExpr maybeInduction = SList [(Atom "_MaybeInduction_")]

typeSignatureToSExpr :: TypeSignature -> SExpr
typeSignatureToSExpr typeSignature = SList [(Atom "_TypeSignature_")]

hidingToSExpr :: Hiding -> SExpr
hidingToSExpr hiding = SList [(Atom "_Hiding_")]

moduleApplicationToSExpr :: ModuleApplication -> SExpr
moduleApplicationToSExpr moduleApplication = SList [(Atom "_ModuleApplication_")]

maybeNameToSExpr :: Maybe Name -> SExpr
maybeNameToSExpr maybeName = SList [(Atom "_MaybeName_")]

namedStringPatternToSExpr :: Named String Pattern -> SExpr
namedStringPatternToSExpr namedStringPattern = SList [(Atom "_NamedStringPattern_")]

argExprToSExpr :: Arg Expr -> SExpr
argExprToSExpr argExpr = SList [(Atom "_ArgExpr_")]

nameIdToSExpr :: NameId -> SExpr
nameIdToSExpr nameId = SList [(Atom "_NameId_")]

namedArgPatternToSExpr :: NamedArg Pattern -> SExpr
namedArgPatternToSExpr namedArgPattern = SList [(Atom "_NamedArgPattern_")]

literalToSExpr :: Literal -> SExpr
literalToSExpr literal = SList [(Atom "_Literal_")]

maybeExprToSExpr :: Maybe Expr -> SExpr
maybeExprToSExpr maybeExpr = SList [(Atom "_MaybeExpr_")]

pragmaToSExpr :: Pragma -> SExpr
pragmaToSExpr pragma = SList [(Atom "_Pragma_")]

telescopeToSExpr :: Telescope -> SExpr
telescopeToSExpr telescope = SList [(Atom "_Telescope_")]

maybeAsNameToSExpr :: Maybe AsName -> SExpr
maybeAsNameToSExpr maybeAsName = SList [(Atom "_MaybeAsName_")]

withExprToSExpr :: WithExpr -> SExpr
withExprToSExpr withExpr = SList [(Atom "_WithExpr_")]

lamBindingToSExpr :: LamBinding -> SExpr
lamBindingToSExpr lamBinding = SList [(Atom "_LamBinding_")]

opAppExprToSExpr :: OpApp Expr -> SExpr
opAppExprToSExpr opAppExpr = SList [(Atom "_OpAppExpr_")]

openShortHandToSExpr :: OpenShortHand -> SExpr
openShortHandToSExpr openShortHand = SList [(Atom "_OpenShortHand_")]

maybeNatToSExpr :: Maybe Nat -> SExpr
maybeNatToSExpr maybeNat = SList [(Atom "_MaybeNat_")]

namedStringExprToSExpr :: Named String Expr -> SExpr
namedStringExprToSExpr namedStringExpr = SList [(Atom "_NamedStringExpr_")]

rewriteEqnToSExpr :: RewriteEqn -> SExpr
rewriteEqnToSExpr rewriteEqn = SList [(Atom "_RewriteEqn_")]

integerToSExpr :: Integer -> SExpr
integerToSExpr integer = SList [(Atom "_Integer_")]

