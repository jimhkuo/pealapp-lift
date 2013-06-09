grammar PealProgram;

options {
language = Java;
}

@header {
package peal.antlr;
import java.util.HashMap;
import java.util.Map;
import peal.domain.*;
import org.antlr.runtime.BitSet;

}

@members {
Map<String, Pol> pols = new HashMap<String, Pol>();
scala.collection.mutable.ListBuffer<Rule> l = new scala.collection.mutable.ListBuffer<Rule>();
}

@lexer::header {
package peal.antlr;
}

//want to deal with
//cond = pSet <= 0.5
//pSet = max(b1, b2)
//b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1
//b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0

//use a map to store these values
program	: 'cond' '=' id1=IDENT '<=' NUMBER {l =  new scala.collection.mutable.ListBuffer<Rule>();}
  (id2=IDENT '=' 'max' '(' id3=IDENT ',' id4=IDENT ')' | id2=IDENT '=' 'min' '(' id3=IDENT ',' id4=IDENT ')')
  (id5=IDENT '=' pol {pols.put($id5.text, $pol.p);})*
	;

pol	returns [Pol p] 
	:  '+' '(' (rule {l.append($rule.r);})* ')' 'default' NUMBER {$p = new Pol(l.toList(), Double.valueOf($NUMBER.text));}
	| 'max' '(' (rule {l.append($rule.r);})* ')' 'default' NUMBER {$p = new Pol(l.toList(), Double.valueOf($NUMBER.text));}
	| 'min' '(' (rule {l.append($rule.r);})* ')' 'default' NUMBER {$p = new Pol(l.toList(), Double.valueOf($NUMBER.text));} //need to map string to double
	;

rule 	returns [Rule r]
	: '(' IDENT NUMBER ')' {$r = new Rule(new Predicate($IDENT.text, ""),Double.valueOf($NUMBER.text));}
	;

//cond returns [Boolean e]
//	: NUMBER '<' pSet
//	| pSet '<=' NUMBER
//	;

//pol returns [Double i]
//	: '+' '(' (rule)* ')' 'default' NUMBER
//	| 'max' '(' (rule)* ')' 'default' NUMBER
//	| 'min' '(' (rule)* ')' 'default' NUMBER
//	;

//pSet    : pol 
//	| 'max' '(' pol ',' pol ')'
//	| 'min' '(' pol ',' pol ')'
//	;


NUMBER : ('.'|'0'..'9'|'-'|'E')+;
COMPARE : ('>' | '>=' | '<' | '<=');
NEWLINE:'\r'? '\n' { $channel = HIDDEN;};
IDENT : ('a'..'z' | 'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')*;
WS : (' ' | '\t' | '\n' | '\r' | '\f')+ { $channel = HIDDEN;};