module SExpr where

import Agda.Syntax.Position
import Agda.Syntax.Concrete
import Agda.Syntax.Concrete.Name

data SExpr = Atom String | SList [SExpr] | SStr String

positionToSExpr :: Position -> SExpr
positionToSExpr position = SList [Atom "Position", Atom $ show $ posPos position]

namePartToSExpr :: NamePart -> SExpr
namePartToSExpr Hole = SList [(Atom "Hole")]
namePartToSExpr (Id strings) =  SList [(Atom "Id"), (SStr strings)]

lHS_RHS_WhereClauseToSExpr :: (LHS, RHS, WhereClause) -> SExpr
lHS_RHS_WhereClauseToSExpr (lHS, rHS, whereClause) = SList [(Atom "(LHS, RHS, WhereClause)")]

name_ExprToSExpr :: (Name, Expr) -> SExpr
name_ExprToSExpr (name, expr) = SList [(Atom "(Name, Expr)")] 

