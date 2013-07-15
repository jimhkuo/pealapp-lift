grammar Z3Output;

options {
language = Java;
output=AST;
}

@header {
package peal.antlr;
import java.util.*;
import peal.domain.*;
import peal.*;
import org.antlr.runtime.BitSet;
import peal.synthesis.*;
import peal.synthesis.analysis.*;
import peal.domain.operator.*;
}

@members {

@Override
public void reportError(RecognitionException e) {
	throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
}

}

@lexer::header {
package peal.antlr;
}

//it builds a map of results
results	: (
	'Result of analysis [' IDENT '=' IDENT '?' IDENT (IDENT)? ']:'
	(model)
	)+
	;
	
model 
//@init {m = new Model();}
	: 'sat' '(model' (define)+ ')'
	| 'unsat' Z3ERROR 
//	: '(model' (define[$m])+ ')'
	;

define 	
	: '(define-fun' IDENT '()' IDENT IDENT')'	
	//: '(define-fun' IDENT '()' IDENT IDENT')' {//add defs to model}	
	;	
	

NUMBER : ('.'|'0'..'9'|'-'|'E')+;
IDENT : ('a'..'z' | 'A'..'Z')( '_' | 'a'..'z' | 'A'..'Z' | '0'..'9')*;
WS : (' ' | '\t' | '\n' | '\r' | '\f')+ { $channel = HIDDEN;};
Z3ERROR	: '(error "line ' NUMBER ' column ' NUMBER': model is not available")';
//STRING 	: IDENT WS (IDENT | WS | '=' | '"' | '?')+;
