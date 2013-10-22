grammar PealProgram;

options {
language = Java;
}

@header {
package peal.antlr;
import java.util.*;
import peal.domain.*;
import peal.antlr.util.*;
import peal.*;
import org.antlr.runtime.BitSet;
import peal.synthesis.*;
import peal.synthesis.analysis.*;
import peal.domain.operator.*;
import scala.math.BigDecimal;
import scala.util.*;
}

@members {
public Map<String, Pol> pols = new HashMap<String, Pol>();
public Map<String, Condition> conds = new HashMap<String, Condition>();
public Map<String, AnalysisGenerator> analyses = new HashMap<String, AnalysisGenerator>();
public Map<String, PolicySet> pSets = new HashMap<String, PolicySet>();
private Map<String, String> pSetScores = new HashMap<String, String>();
private List<Rule> l = null;
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

program	
	: 
	('POLICIES')?
	(pol {pols.put($pol.p.getPolicyName(), $pol.p);})*
	('POLICY_SETS')?
	(id2=IDENT '=' pSet { pSets.put($id2.text, $pSet.t);})+
	('CONDITIONS')?
	(
	id0=IDENT '=' id2=IDENT '<=' num=NUMBER {Condition cond = new LessThanThCondition(pSets.get($id2.text), new Left<BigDecimal,PolicySet>(BigDecimal.valueOf(Double.valueOf($num.text)))); conds.put($id0.text, cond);}
    	|
	id0=IDENT '=' id2=IDENT '<=' id3=IDENT {Condition cond = new LessThanThCondition(pSets.get($id2.text), new Right<BigDecimal,PolicySet>(pSets.get($id3.text))); conds.put($id0.text, cond);}
    	|
	id0=IDENT '=' num=NUMBER '<' id2=IDENT {Condition cond = new GreaterThanThCondition(pSets.get($id2.text), BigDecimal.valueOf(Double.valueOf($num.text))); conds.put($id0.text, cond);}
	|
	id0=IDENT '=' '!' id1=IDENT {Condition cond = new NotCondition($id1.text); conds.put($id0.text, cond);}
	|
	id0=IDENT '=' id1=IDENT '&&' id2=IDENT {Condition cond = new AndCondition($id1.text, $id2.text); conds.put($id0.text, cond);}
	|
	id0=IDENT '=' id1=IDENT '||' id2=IDENT {Condition cond = new OrCondition($id1.text, $id2.text); conds.put($id0.text, cond);}
	|
	id0 =IDENT '=' 'true' {Condition cond = new TrueCondition(); conds.put($id0.text, cond);}
	|
	id0 =IDENT '=' 'false' {Condition cond = new FalseCondition(); conds.put($id0.text, cond);}
	)+
	('DOMAIN_SPECIFICS' {ignore = true;}
	(IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*)?
	('ANALYSES' {ignore = false;}
	(id0=IDENT '=' 'always_true?' id1=IDENT {AnalysisGenerator analysis = new AlwaysTrue($id0.text, $id1.text); analyses.put($id0.text, analysis);}
	|id0=IDENT '=' 'always_false?' id1=IDENT {AnalysisGenerator analysis = new AlwaysFalse($id0.text, $id1.text); analyses.put($id0.text, analysis);}
	|id0=IDENT '=' 'satisfiable?' id1=IDENT {AnalysisGenerator analysis = new Satisfiable($id0.text, $id1.text); analyses.put($id0.text, analysis);}	
	|id0=IDENT '=' 'equivalent?' id1=IDENT id2=IDENT {AnalysisGenerator analysis = new Equivalent($id0.text, $id1.text, $id2.text); analyses.put($id0.text, analysis);}	
	|id0=IDENT '=' 'different?' id1=IDENT id2=IDENT {AnalysisGenerator analysis = new Different($id0.text, $id1.text, $id2.text); analyses.put($id0.text, analysis);}		
	|id0=IDENT '=' 'implies?' id1=IDENT id2=IDENT {AnalysisGenerator analysis = new Implies($id0.text, $id1.text, $id2.text); analyses.put($id0.text, analysis);}		
	)+
	)?
	;

pSet  returns [PolicySet t] 
	: id1=IDENT {$t = new BasicPolicySet(pols.get($id1.text));}
	| 'max' '(' id1=IDENT ',' id2=IDENT ')' {$t = new MaxPolicySet(PolicyResolver.getFromOr(pols, pSets, $id1.text), PolicyResolver.getFromOr(pols, pSets, $id2.text));}
	| 'min' '(' id1=IDENT ',' id2=IDENT ')' {$t = new MinPolicySet(PolicyResolver.getFromOr(pols, pSets, $id1.text), PolicyResolver.getFromOr(pols, pSets, $id2.text));}
	;

pol	returns [Pol p] 
@init {l = new ArrayList<Rule>(); }
	: id1=IDENT '=' o=operator '(' (rule {l.add($rule.r);})* ')' 'default' (n=NUMBER {$p = new Pol(l, OperatorResolver.apply($o.text), new Left<BigDecimal,Variable>(BigDecimal.valueOf(Double.valueOf($n.text))), $id1.text);}
									|
									n=NUMBER '*' id2=IDENT {$p = new Pol(l, OperatorResolver.apply($o.text), new Right<BigDecimal,Variable>(new Variable(BigDecimal.valueOf(Double.valueOf($n.text)), $id2.text)), $id1.text);}
									|
									id2=IDENT {$p = new Pol(l, OperatorResolver.apply($o.text), new Right<BigDecimal,Variable>(new Variable(BigDecimal.valueOf(1), $id2.text)), $id1.text);}
									|
									id2=IDENT '*' n=NUMBER {$p = new Pol(l, OperatorResolver.apply($o.text), new Right<BigDecimal,Variable>(new Variable(BigDecimal.valueOf(Double.valueOf($n.text)), $id2.text)), $id1.text);}
									)
	;

rule 	returns [Rule r]
	: '(' IDENT NUMBER ')' {$r = new Rule(new Predicate($IDENT.text),new Left<BigDecimal,Variable>(BigDecimal.valueOf(Double.valueOf($NUMBER.text))));}
	| '(' id0=IDENT id1=IDENT')' {$r = new Rule(new Predicate($id0.text),new Right<BigDecimal,Variable>(new Variable(BigDecimal.valueOf(1), $id1.text)));}
	| '(' id0=IDENT n=NUMBER '*' id1=IDENT')' {$r = new Rule(new Predicate($id0.text),new Right<BigDecimal,Variable>(new Variable(BigDecimal.valueOf(Double.valueOf($n.text)), $id1.text)));}
	| '(' id0=IDENT id1=IDENT '*' n=NUMBER ')' {$r = new Rule(new Predicate($id0.text),new Right<BigDecimal,Variable>(new Variable(BigDecimal.valueOf(Double.valueOf($n.text)), $id1.text)));}
	;

operator : 'max' | 'min' | '+' | '*';

NUMBER : ('.'|'0'..'9'|'-'|'E')+ {if(ignore) skip();};
IDENT : ('a'..'z' | 'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9' | '_')* {if(ignore) skip();};
WS : (' ' | '\t' | '\n' | '\r' | '\f')+ { $channel = HIDDEN;};
	
//NEWLINE:'\r'? '\n' { $channel = HIDDEN;};