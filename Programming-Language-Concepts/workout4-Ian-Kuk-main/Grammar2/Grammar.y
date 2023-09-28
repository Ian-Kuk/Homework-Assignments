{
module Grammar where
import Tokens
import Prog
}

%name parseProg
%tokentype { Token }
%error { parseError }

%token
    '('  { TokenLParen }
    ')'  { TokenRParen }
    ';'  { TokenSemi}
    ','  { TokenComma}
    id  { TokenId $$ }
%%

-- replace with your productions:
Prog : FunCallList ';' { $1 }

FunCallList : FunCall { [$1] }
            | FunCallList ';' FunCall { $1 ++ [$3] }

FunCall : id { Var $1 }
        | id '(' ')' { FunCall $1 [] }
        | id '(' FunCallParams ')' { FunCall $1 $3 }

FunCallParams : FunCall { [$1] }
              | FunCallParams ',' FunCall { $1 ++ [$3] }

{

parseError :: [Token] -> a
parseError tks = error ("Parse error: " ++ show tks)

}
