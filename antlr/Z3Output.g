grammar Z3Output;

options {
language = Java;
}

@header {
package peal.antlr;
import java.util.*;
import peal.domain.z3.*;
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
results returns	[Map<String, Model> r]
@init {r = new HashMap<String, Model>();}
	: (
	'Result of analysis [' id0=IDENT '=' id1=IDENT '?' id2=IDENT (IDENT)? ']:'
	(m=model) { r.put($id0.text, $m.m);}
	)+
	;
	
model returns [Model m]
	: 'sat' '(model' (define)+ ')' { $m = new Model(Sat$.MODULE$, new ArrayList<Define>());}
	| 'unsat' Z3ERROR { $m = new Model(Unsat$.MODULE$, new ArrayList<Define>());}
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
