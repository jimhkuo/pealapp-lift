grammar Peal;

options {
language = Java;
}

@header {
package peal.antlr;
import peal.domain.*;
}

@lexer::header {
package peal.antlr;
}

//want to deal with
//cond = pSet <= 0.5
//pSet = max(b1, b2)
//b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1
//b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0
//q1 = true
//q2 = true
//q3 = true
//q4 = false
//q5 = true
//q6 = false

program : 'cond' '=' 'pSet' '<=' NUMBER
	'pSet' '=' ;

pred 	returns [Double i]
	: id1=IDENT '=' id2=IDENT 
	| id3=IDENT
	;

rule returns [Double i]
	: '(' 'if' pred NUMBER ')' 
	;

cond returns [Boolean e]
	: NUMBER '<' pSet
	| pSet '<=' NUMBER
	;

pol returns [Double i]
	: '+' '(' (rule)* ')' 'default' NUMBER
	| 'max' '(' (rule)* ')' 'default' NUMBER
	| 'min' '(' (rule)* ')' 'default' NUMBER
	;

pSet    : pol 
	| 'max' '(' pol ',' pol ')'
	| 'min' '(' pol ',' pol ')'
	;


NUMBER : ('.'|'0'..'9'|'-'|'E')+;
COMPARE : ('>' | '>=' | '<' | '<=');
IDENT : ('a'..'z' | 'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')*;
WS : (' ' | '\t' | '\n' | '\r' | '\f')+ { $channel = HIDDEN;};