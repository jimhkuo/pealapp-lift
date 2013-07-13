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
import peal.synthesis.*;
import peal.synthesis.analysis.*;
import peal.domain.operator.*;
}

@members {
public Map<String, Pol> pols = new HashMap<String, Pol>();
public Map<String, Condition> conds = new HashMap<String, Condition>();
public Map<String, AnalysisGenerator> analyses = new HashMap<String, AnalysisGenerator>();
public Map<String, PolicySet> pSets = new HashMap<String, PolicySet>();
private Map<String, String> pSetScores = new HashMap<String, String>();
List<Rule> l = new ArrayList<Rule>();

@Override
public void reportError(RecognitionException e) {
	throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
}

}

@lexer::header {
package peal.antlr;
}

program	
	: 
	
	(id1=IDENT '=' pol {pols.put($id1.text, $pol.p);})*
	(id2=IDENT '=' pSet { pSets.put($id2.text, $pSet.t);})+
//	(id2=IDENT '=' pSet[$id2.text] { pSets.put($id2.text, $pSet.t);})+
	(
	id0=IDENT '=' id2=IDENT '<=' num=NUMBER {Condition cond = new LessThanThCondition(pSets.get($id2.text), Double.valueOf($num.text)); conds.put($id0.text, cond);}
    	|
	id0=IDENT '=' num=NUMBER '<' id2=IDENT {Condition cond = new GreaterThanThCondition(pSets.get($id2.text), Double.valueOf($num.text)); conds.put($id0.text, cond);}
	)+
	('ANALYSES'
	(id0=IDENT '=' 'always_true?' id1=IDENT {AnalysisGenerator analysis = new AlwaysTrue($id0.text, $id1.text); analyses.put($id0.text, analysis);}
	|id0=IDENT '=' 'always_false?' id1=IDENT {AnalysisGenerator analysis = new AlwaysFalse($id0.text, $id1.text); analyses.put($id0.text, analysis);}
	|id0=IDENT '=' 'satisfiable?' id1=IDENT {AnalysisGenerator analysis = new Satisfiable($id0.text, $id1.text); analyses.put($id0.text, analysis);}	
	|id0=IDENT '=' 'equivalent?' id1=IDENT id2=IDENT {AnalysisGenerator analysis = new Equivalent($id0.text, $id1.text, $id2.text); analyses.put($id0.text, analysis);}	
	|id0=IDENT '=' 'different?' id1=IDENT id2=IDENT {AnalysisGenerator analysis = new Different($id0.text, $id1.text, $id2.text); analyses.put($id0.text, analysis);}		
	)+
	)?
	;

//pSet [String s] returns [PolicySet t] 
pSet  returns [PolicySet t] 
	: id1=IDENT {$t = new BasicPolicySet(pols.get($id1.text));}
	| 'max' '(' id1=IDENT ',' id2=IDENT ')' {$t = new MaxPolicySet(new BasicPolicySet(pols.get($id1.text)), new BasicPolicySet(pols.get($id2.text)));}
	| 'max' '(' id3=IDENT ',' id4=pSet ')' {$t = new MaxPolicySet(new BasicPolicySet(pols.get($id3.text)), $id4.t);}
	| 'min' '(' id1=IDENT ',' id2=IDENT ')' {$t = new MinPolicySet(new BasicPolicySet(pols.get($id1.text)), new BasicPolicySet(pols.get($id2.text)));}
	| 'min' '(' id3=IDENT ',' id4=pSet ')' {$t = new MinPolicySet(new BasicPolicySet(pols.get($id3.text)), $id4.t);}
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