--import Agda.Syntax.Parser
import Agda.Syntax.Concrete
import Agda.Syntax.Position
import Agda.Syntax.Concrete.Name 
import Agda.Syntax.Common 

import qualified Agda.Syntax.Parser.Monad as M
import qualified Agda.Syntax.Parser.Parser as P 
import Agda.Syntax.Parser.Lexer
import System.IO
import System.Environment 
import Data.List
import Data.Maybe
import Protocol
import SExpr

parse :: M.Parser a -> String -> M.ParseResult a
parse p = M.parse (M.defaultParseFlags { M.parseKeepComments = False }) [normal] p

main = do
        hSetBuffering stdout NoBuffering
        hSetEncoding stdin utf8
        hSetEncoding stdout utf8
        x <- getContents      
        m <- return $ parse P.moduleParser x
        putStrLn $ sExprToString $ parseResultToSExpr m


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


parseResultToSExpr :: M.ParseResult Module -> SExpr
parseResultToSExpr (M.ParseOk _ x)     = SList [(Atom "ParseOk"), moduleToSExpr x]
parseResultToSExpr (M.ParseFailed err) = SList [(Atom "ParseFailed")]

--pragmaToSExpr :: Pragma -> SExpr
--pragmaToSExpr pragma = SList [Atom "Pragma"]

moduleToSExpr :: Module -> SExpr
moduleToSExpr (pragmas, declarations) = SList [
                          (SList $ map pragmaToSExpr pragmas),
                          (SList $ map declarationToSExpr declarations) ]
