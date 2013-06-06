grammar Peal;

options {
language = Java;
output=AST;
}

@header {
package peal.antlr;
}

@lexer::header {
package peal.antlr;
}

pred : id1=IDENT '=' id2=IDENT 
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
//	| 'ma' '(' (rule)* ')' 'default' NUMBER
//	| 'mi' '(' (rule)* ')' 'default' NUMBER
	;



pSet    : pol 
	| 'max' '(' exp ',' exp ')'
	;

exp 	:	mult ('+' mult)*
	;

mult1	: pol ('*' pol)
	;

//pSet1	: (pol|pSet)
//	;


//pSet2 	: (pol)
//	;
term    : ex1=NUMBER 
	|'(' ex2=expression ')'
	|'max' '(' ex3=expression ',' ex4=expression  ')'
	|'min' '(' ex5=expression ',' ex6=expression  ')'
	; 

expression 
	: ex1=mult ('+' ex2=mult )*
	; 

mult	: ex1=term ('*' ex2=term )*
	; 

NUMBER : ('.'|'0'..'9'|'-'|'E')+;
COMPARE : ('>' | '>=' | '<' | '<=');
IDENT : ('a'..'z' | 'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')*;
WS : (' ' | '\t' | '\n' | '\r' | '\f')+ { $channel = HIDDEN;};