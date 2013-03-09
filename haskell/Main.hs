import Agda.Syntax.Parser
import Agda.Syntax.Concrete
import Agda.Syntax.Position
import Agda.Syntax.Concrete.Name 
import Agda.Syntax.Common 


import Data.List
import Data.Maybe


main = do
        x <- readFile "test.agda"
        m <- parse moduleParser x
        putStrLn $ sExprToString $ moduleToSExpr m


data SExpr = Atom String | SList [SExpr] | SStr String

sExprToString = sExprToString' " "

sExprToString' :: String -> SExpr -> String
sExprToString' indent (Atom str)   = str
sExprToString' indent (SStr str)   = "\"" ++ str ++ "\""
sExprToString' indent (SList args) = fromMaybe def (sExprToStringShort (SList args)) where
                            def = "(" ++ (intercalate 
                                          (" \n" ++ indent) 
                                          (map (sExprToString' (indent ++ " ")) args)) ++ ")"

maybeListToMaybe :: [Maybe a] -> Maybe [a]
maybeListToMaybe [] = Just []
maybeListToMaybe (Nothing  : tail) = Nothing
maybeListToMaybe ((Just a) : tail) = do 
                                        rest <- maybeListToMaybe tail
                                        return (a : rest)

sExprToStringShort :: SExpr -> Maybe String
sExprToStringShort (Atom str)   = Just str
sExprToStringShort (SStr str)   = Just ("\"" ++ str ++ "\"")
sExprToStringShort (SList args) = do 
                                prev <- maybeListToMaybe $ map sExprToStringShort args
                                let t = "(" ++ (intercalate " " prev) ++ ")" in
                                  if (length t < 60) then (Just t) else Nothing 
                                  

class SExprPresentable a where
        sexpr :: a -> SExpr


lamBindingToSExpr (DomainFree hiding relevance boundName) = SList [Atom "DomainFree"]
lamBindingToSExpr (DomainFull typedBindings)              = SList [Atom "DomainFull"]


rangeToSExpr :: Range -> SExpr
rangeToSExpr (Range intervals) = SList [Atom "Range", SList $ map intervalToSExpr intervals]

positionToSExpr :: Position -> SExpr
positionToSExpr position = SList [Atom "position", Atom $ show (posPos position)]

intervalToSExpr :: Interval -> SExpr
intervalToSExpr (Interval iStart iEnd) = SList [Atom "Interval", positionToSExpr iStart, positionToSExpr iEnd]

moduleToSExpr :: Module -> SExpr
moduleToSExpr (pragmas, declarations) = SList [
                          (SList $ map pragmaToSExpr pragmas),
                          (SList $ map declarationToSExpr declarations) ]


pragmaToSExpr :: Pragma -> SExpr
pragmaToSExpr progma = Atom "Pragma"

namePartToSExpr :: NamePart -> SExpr
namePartToSExpr Hole         = Atom "Hole"
namePartToSExpr (Id string)  = (SList [Atom "Id", SStr string])

nameIdToSExpr :: NameId -> SExpr
nameIdToSExpr (NameId v1 v2) = (SList [Atom "NameId", Atom $ show v1, Atom $ show v2])

nameToSExpr :: Name -> SExpr
nameToSExpr (Name   range nameParts) = SList [(Atom "Name"), rangeToSExpr range, SList $ map namePartToSExpr nameParts]
nameToSExpr (NoName range nameId)    = SList [(Atom "NoName"), rangeToSExpr range, nameIdToSExpr nameId ]

qnameToSExpr :: QName -> SExpr
qnameToSExpr (Qual  name qName) = SList [Atom "Qual", nameToSExpr name, qnameToSExpr qName]
qnameToSExpr (QName name)       = SList [Atom "QName", nameToSExpr name]

typedBindingToSExpr :: TypedBindings -> SExpr
typedBindingToSExpr typedBinding = SList [Atom "TypedBindings"]

inductionToSExpr :: Induction -> SExpr
inductionToSExpr induction = SList [Atom "Induction"]

exprToSExpr :: Expr -> SExpr
exprToSExpr expression = SList [Atom "Expr"]

maybeExprToSExpr :: Maybe Expr -> SExpr
maybeExprToSExpr Nothing     = Atom "Nothing"
maybeExprToSExpr (Just expr) = SList [Atom "Just", exprToSExpr expr]

constructorToSExpr :: Constructor -> SExpr
constructorToSExpr constructor = SList [ Atom "Constructor" ]

{-
TypeSig Relevance Name Expr -- ^ Axioms and functions can be irrelevant.
        | Field Name (Arg Expr) -- ^ Record field, can be hidden and/or irrelevant.
        | FunClause LHS RHS WhereClause
        | DataSig     !Range Induction Name [LamBinding] Expr -- ^ lone data signature in mutual block
        | Data        !Range Ind uction Name [LamBinding] (Maybe Expr) [Constructor]
        | Reco rdSig   !Range Name [LamBinding] Expr -- ^ lone record signature in mutual block
        | Record      !Range Name (Maybe Induction) (Maybe Name) [LamBinding] (Maybe Expr) [Declaration]
          -- ^ The optional name is a name for the record constructor.
        | Infix Fixity [Name]
        | Syntax      Name Notation -- ^ notation declaration for a name
        | PatternSyn  !Range Name [Name] Pattern
        | Mutual      !Range [Declaration]
        | Abstract    !Range [Declaration]
        | Private     !Range [Declaration]
        | Postulate   !Range [TypeSignature]
        | Primitive   !Range [TypeSignature]
        | Open        !Range QName ImportDirective
        | Import      !Range QName (Maybe AsName) OpenShortHand ImportDirective
        | ModuleMacro !Range  Name ModuleApplication OpenShortHand ImportDirective
        | Module      !Range QName [TypedBindings] [Declaration]
        | Pragma      Pragma
-}

declarationToSExpr :: Declaration -> SExpr
declarationToSExpr (Module range qName typedBindings declarations) = SList [
                (Atom "Module"),
                (rangeToSExpr range),
                (qnameToSExpr qName),
                SList $ map typedBindingToSExpr typedBindings,
                SList $ map declarationToSExpr declarations
                ]        
declarationToSExpr (Data range induction name lamBindings maybeExpr constructors) = SList [
                (Atom "Data"),
                (rangeToSExpr range),
                (inductionToSExpr induction), 
                (nameToSExpr name),
                SList $ map lamBindingToSExpr lamBindings,
                maybeExprToSExpr maybeExpr,
                SList $ map constructorToSExpr constructors
                ]
declarationToSExpr declaration = Atom "Declaration"
