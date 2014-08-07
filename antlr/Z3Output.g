grammar Z3Output;

options {
language = Java;
}

@header {
package peal.antlr;

import org.antlr.runtime.*;
import peal.domain.z3.Assignment;
import peal.domain.z3.Model;
import peal.domain.z3.Sat$;
import peal.domain.z3.Unsat$;
import peal.domain.z3.Unknown$;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
}

@members {
private boolean ignore = false;

@Override
public void reportError(RecognitionException e) {
	throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
}

}

@lexer::header {
package peal.antlr;
}

@lexer::members {
private boolean ignore = false;
}

results returns	[Map<String, Model> r]
@init {r = new HashMap<String, Model>();}
	:((Z3ERROR)+)? 
	(
		'Result of analysis [' id0=IDENT '=' id1=IDENT '?' id2=IDENT (IDENT)? ']:' (m=model) { r.put($id0.text, $m.m);}
	| 
		'Result of vacuity check [' id0=IDENT '=' id1=IDENT '?' id2=IDENT (IDENT)? ']:' (m=model) { r.put($id0.text, $m.m);}
	)+
	;
	
model returns [Model m]
@init{ List<Assignment> l = new ArrayList<Assignment>(); }
	: 'sat' '(model' (assignment {l.add($assignment.a);})+ ')' { $m = new Model(Sat$.MODULE$, l);}
	| 'unknown' '(model' (assignment {l.add($assignment.a);})+ ')' { $m = new Model(Unknown$.MODULE$, l);}	
	| 'unsat' UNSATMESSAGE { $m = new Model(Unsat$.MODULE$, l);}
	;

assignment 	returns [Assignment a]
	:'(define-fun' id0=IDENT '(' ('(' (IDENT)+ ')')* ')' id1=IDENT id2=value')' {ignore = false; $a = new Assignment($id0.text, $id1.text, $id2.s);}	
	|'(declare-fun' id0=IDENT '('')' id1=IDENT')' {$a = new Assignment($id0.text, $id1.text, "");}	
	|'(forall' {ignore = true;}
	(IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' | '/')* {$a = new Assignment("", "", "");}	
	;	

value returns [String s]
	: IDENT {$s = $IDENT.text;}
	| NUMBER {$s = $NUMBER.text;}
	| '(' '-' unary ')' {$s = "-" + $unary.s;}
	| '(' '/' lhs=NUMBER rhs=NUMBER ')' {$s = $lhs.text + "/" + $rhs.text;}
	| '('IDENT ('(' (IDENT)+ ')')?')' {$s = "";}
	| '(ite' component component component ')' {$s = "";}
	;	
	
component 
	: '(''=' IDENT value')'		
	| '(and' (component)+ ')'
	| value
	; 
	
unary returns [String s]
	: IDENT {$s = $IDENT.text;}
	| NUMBER {$s = $NUMBER.text;}
	| value {$s = $value.s;}
	;

error :	 Z3ERROR;


NUMBER : ('.'|'0'..'9'|'-'|'E')+ {if(ignore) skip();};
IDENT : ('a'..'z' | 'A'..'Z' | '\'')( '!' | '_' | 'a'..'z' | 'A'..'Z' | '0'..'9'| '\'')* {if(ignore) skip();};
WS : (' ' | '\t' | '\n' | '\r' | '\f')+ { $channel = HIDDEN;};
UNSATMESSAGE : '(error "line ' NUMBER ' column ' NUMBER': model is not available")';
Z3ERROR :	 '(error "line ' NUMBER ' column ' NUMBER ': invalid declaration, constant \'' IDENT '\' (whith the given signature) already declared")';

