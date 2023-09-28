{
module Grammar where
import Tokens
import Tp
}

%name parseTp
%tokentype { Token }
%error { parseError }

%token
    '('  { TokenLParen }
    ')'  { TokenRParen }
    '->'  { TokenArrow}

%right '->'

%%

-- replace this dummy production with your productions:
Tp : 

'('')'      { Unit }
| Tp '->' Tp { Arrow $1 $3}
| '(' Tp ')' {$2}

{

parseError :: [Token] -> a
parseError tks = error ("Parse error: " ++ show tks)

}
