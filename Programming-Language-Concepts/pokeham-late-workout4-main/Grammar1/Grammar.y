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
Tp : UnitType                { $1 }
   | ArrowType               { $1 }
   | ParenType               { $1 }

UnitType : '(' ')'    { Unit }

ArrowType : Tp '->' Tp { Arrow $1 $3 }

ParenType : '(' Tp ')'   { $2 }

{

parseError :: [Token] -> a
parseError tks = error ("Parse error: " ++ show tks)

}
