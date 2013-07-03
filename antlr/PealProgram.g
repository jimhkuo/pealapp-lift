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
import peal.synthesis.pSet;
import peal.domain.operator.*;

}

@members {
Map<String, Pol> pols = new HashMap<String, Pol>();
List<Rule> l = new ArrayList<Rule>();
String n = null;
public pSet pSet = null;

//need to override the default error reporting
@Override
public void reportError(RecognitionException e) {
	throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
}

}

@lexer::header {
package peal.antlr;
}

program	
	: 'cond' '=' id0=IDENT '<=' num=NUMBER {n = $num.text;}
	(id1=IDENT '=' pol {pols.put($id1.text, $pol.p);})*
	id0=IDENT '=' pSet { pSet = $pSet.t;}
	;

pSet    returns [pSet t] 
	: id1=IDENT {$t = new PolLessThanTh(pols.get($id1.text), Double.valueOf(n));}
	| 'max' '(' id1=IDENT ',' id2=IDENT ')' {$t = new MaxLessThanTh(pols.get($id1.text), pols.get($id2.text), Double.valueOf(n));}
	| 'max' '(' id3=IDENT ',' id4=pSet ')' {$t = new MaxLessThanTh(pols.get($id3.text), $id4.t, Double.valueOf(n));}
	| 'min' '(' id1=IDENT ',' id2=IDENT ')' {$t = new MinLessThanTh(pols.get($id1.text), pols.get($id2.text), Double.valueOf(n));}
	| 'min' '(' id3=IDENT ',' pSet ')' {$t = new MinLessThanTh(pols.get($id3.text), $id4.t, Double.valueOf(n));}
	;

pol	returns [Pol p] 
@init {l = new ArrayList<Rule>(); }
	:  '+' '(' (rule {l.add($rule.r);})* ')' 'default' NUMBER {$p = new Pol(l, Plus$.MODULE$, Double.valueOf($NUMBER.text));}
	| 'max' '(' (rule {l.add($rule.r);})* ')' 'default' NUMBER {$p = new Pol(l, Max$.MODULE$, Double.valueOf($NUMBER.text));}
	| 'min' '(' (rule {l.add($rule.r);})* ')' 'default' NUMBER {$p = new Pol(l, Min$.MODULE$, Double.valueOf($NUMBER.text));} 
	| '*' '(' (rule {l.add($rule.r);})* ')' 'default' NUMBER {$p = new Pol(l, Mul$.MODULE$, Double.valueOf($NUMBER.text));}
	;

rule 	returns [Rule r]
	: '(' IDENT NUMBER ')' {$r = new Rule(new Predicate($IDENT.text, ""),Double.valueOf($NUMBER.text));}
	;

NUMBER : ('.'|'0'..'9'|'-'|'E')+;
COMPARE : ('>' | '>=' | '<' | '<=');
NEWLINE:'\r'? '\n' { $channel = HIDDEN;};
IDENT : ('a'..'z' | 'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')*;
WS : (' ' | '\t' | '\n' | '\r' | '\f')+ { $channel = HIDDEN;};