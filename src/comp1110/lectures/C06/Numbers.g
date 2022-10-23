/* grammar for use with antlr (antlr.org) */

grammar Numbers;

@parser::header { package comp1110.lectures.C06; }
@lexer::header { package comp1110.lectures.C06; }

/* real = integer | (['-'] natural '.' {digit} nzdigit EOF); */
real    : integer | ('-'? natural '.' digit* nzdigit EOF);
integer : ZERO | ('-'? nzdigit digit*) EOF ;
natural : ZERO | (nzdigit digit*) ;
nzdigit : NZDIGIT ;
digit   : ZERO | nzdigit ;

NZDIGIT : '1' .. '9' ;
ZERO    : '0' ;
