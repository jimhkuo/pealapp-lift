grammar Peal;

options {
language = Java;
}

@header {
package peal.antlr;
}

@lexer::header {
package peal.antlr;
}

pred returns [Boolean e]
	: 'trust' id1=COMPARE id2=NUMBER
	;



NUMBER : ('.'|'0'..'9'|'-'|'E')+;
COMPARE : ('>' | '>=' | '<' | '<=');
IDENT : ('a'..'z' | 'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')*;
WS : (' ' | '\t' | '\n' | '\r' | '\f')+ { $channel = HIDDEN;};