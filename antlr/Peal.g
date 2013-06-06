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
	: id1=IDENT '=' id2=IDENT 
	| id3=IDENT
	;

rule returns [Double i]
	: '(' 'if' pred NUMBER ')' 
	;

pol returns [Double i]
	: '+' '(' (rule)* ')' 'default' NUMBER
	| 'max' '(' (rule)* ')' 'default' NUMBER
	| 'min' '(' (rule)* ')' 'default' NUMBER
	;

pSet returns [Double i]
	: pol 
	| 'max' '('id1=pol',' id2=pSet')'
	| 'min' '('id3=pSet',' id4=pSet')'
	;

pSet1 	: pSet
	;

cond returns [Boolean e]
	: NUMBER '<' pSet
	| pSet '<=' NUMBER
	;

NUMBER : ('.'|'0'..'9'|'-'|'E')+;
COMPARE : ('>' | '>=' | '<' | '<=');
IDENT : ('a'..'z' | 'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')*;
WS : (' ' | '\t' | '\n' | '\r' | '\f')+ { $channel = HIDDEN;};