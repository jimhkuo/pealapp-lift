grammar PealProgram;

options {
language = Java;
}

@header {
package peal.antlr;
import java.util.*;
import peal.domain.*;
import peal.*;
import org.antlr.runtime.BitSet;
import peal.synthesis.TopSet;
import peal.domain.operator.*;

}

@members {
Map<String, Pol> pols = new HashMap<String, Pol>();
List<Rule> l = new ArrayList<Rule>();
public TopSet pSet = null;

//need to override the default error reporting
@Override
public void reportError(RecognitionException e) {
	throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
}

}

@lexer::header {
package peal.antlr;
}

//want to deal with
//cond = pSet <= 0.5
//pSet = max(b1, b2)
//b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1
//b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0

//Need to insert operator
program	
	: 'cond' '=' id1=IDENT '<=' NUMBER 
	(id5=IDENT '=' pol {pols.put($id5.text, $pol.p);})*
  	(
  	id2=IDENT '=' 'max' '(' id3=IDENT ',' id4=IDENT ')' {pSet = new MaxLessThanTh(pols.get($id3.text), pols.get($id4.text), Double.valueOf($NUMBER.text));} 
	  | 
	id2=IDENT '=' 'min' '(' id3=IDENT ',' id4=IDENT ')' {pSet = new MinLessThanTh(pols.get($id3.text), pols.get($id4.text), Double.valueOf($NUMBER.text));}
	  |
	id2=IDENT '=' id3=IDENT {pSet = new PolLessThanTh(pols.get($id3.text), Double.valueOf($NUMBER.text));}
	)
	;

pol	returns [Pol p] 
@init {l = new ArrayList<Rule>(); }
	:  '+' '(' (rule {l.add($rule.r);})* ')' 'default' NUMBER {$p = new Pol(l, Plus$.MODULE$, Double.valueOf($NUMBER.text));}
	| 'max' '(' (rule {l.add($rule.r);})* ')' 'default' NUMBER {$p = new Pol(l, Max$.MODULE$, Double.valueOf($NUMBER.text));}
	| 'min' '(' (rule {l.add($rule.r);})* ')' 'default' NUMBER {$p = new Pol(l, Min$.MODULE$, Double.valueOf($NUMBER.text));} //need to map string to double
	;

rule 	returns [Rule r]
	: '(' IDENT NUMBER ')' {$r = new Rule(new Predicate($IDENT.text, ""),Double.valueOf($NUMBER.text));}
	;

NUMBER : ('.'|'0'..'9'|'-'|'E')+;
COMPARE : ('>' | '>=' | '<' | '<=');
NEWLINE:'\r'? '\n' { $channel = HIDDEN;};
IDENT : ('a'..'z' | 'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')*;
WS : (' ' | '\t' | '\n' | '\r' | '\f')+ { $channel = HIDDEN;};