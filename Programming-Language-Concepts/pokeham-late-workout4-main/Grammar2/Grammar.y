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
Prog : FunCall ';' Prog   { ($1 : $3) }
     | FunCall ';'       { [$1] }
     ;

FunCall : id '(' FunCalls ')' { FunCall $1 $3 }
        | id                { Var $1 }
        |id '(' ')'        { FunCall $1 [] }
        ;

FunCalls : FunCall ',' FunCalls { ($1 : $3) }
         | FunCall              { [$1] }
         ;

{
parseError :: [Token] -> a
parseError tks = error ("Parse error: " ++ show tks)

}
