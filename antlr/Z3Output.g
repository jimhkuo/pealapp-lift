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

result	: (
	'Result of analysis [' IDENT '=' IDENT '?' IDENT (IDENT)? ']:'
	('unsat' Z3ERROR |'sat' model)
	)+
	;
	
model 	: '(model' (define)+ ')'
	;

define 	: '(define-fun' IDENT '()' IDENT IDENT')'	
	;	
	

NUMBER : ('.'|'0'..'9'|'-'|'E')+;
IDENT : ('a'..'z' | 'A'..'Z')( '_' | 'a'..'z' | 'A'..'Z' | '0'..'9')*;
WS : (' ' | '\t' | '\n' | '\r' | '\f')+ { $channel = HIDDEN;};
Z3ERROR	: '(error "line ' NUMBER ' column ' NUMBER': model is not available")';
//STRING 	: IDENT WS (IDENT | WS | '=' | '"' | '?')+;
