// $ANTLR 3.5.1 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2014-09-11 08:38:52

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


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class PealProgramParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENT", "NUMBER", "WS", "'!'", 
		"'&&'", "'('", "')'", "'*'", "'+'", "','", "'/'", "'<'", "'<='", "'='", 
		"'ANALYSES'", "'CONDITIONS'", "'DOMAIN_SPECIFICS'", "'POLICIES'", "'POLICY_SETS'", 
		"'['", "']'", "'always_false?'", "'always_true?'", "'default'", "'different?'", 
		"'equivalent?'", "'false'", "'implies?'", "'max'", "'min'", "'satisfiable?'", 
		"'true'", "'||'"
	};
	public static final int EOF=-1;
	public static final int T__7=7;
	public static final int T__8=8;
	public static final int T__9=9;
	public static final int T__10=10;
	public static final int T__11=11;
	public static final int T__12=12;
	public static final int T__13=13;
	public static final int T__14=14;
	public static final int T__15=15;
	public static final int T__16=16;
	public static final int T__17=17;
	public static final int T__18=18;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int T__21=21;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int T__36=36;
	public static final int IDENT=4;
	public static final int NUMBER=5;
	public static final int WS=6;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public PealProgramParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public PealProgramParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return PealProgramParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/Users/jkuo/PealApp-lift/antlr/PealProgram.g"; }


	public Map<String, Pol> pols = new HashMap<String, Pol>();
	public Map<String, Condition> conds = new HashMap<String, Condition>();
	public Map<String, AnalysisGenerator> analyses = new HashMap<String, AnalysisGenerator>();
	public Map<String, PolicySet> pSets = new HashMap<String, PolicySet>();
	private Map<String, String> pSetScores = new HashMap<String, String>();
	private List<Rule> l = null;
	private Map<String, Object> combined = null;
	private boolean ignore = false;


	@Override
	public void reportError(RecognitionException e) {
		throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
	}

	private void catchError(Map someSet, String messageBody, String... objs) {
		for (String obj : objs) {
			if (!someSet.containsKey(obj)) {
				throw new RuntimeException(messageBody.replaceFirst("[$]", obj));
			}
		}
	}

	private void findInPredicates(Map pols, String messageBody, String... objs) {
		for (String obj : objs) {
			for (Object pol : pols.values()) {
				for (Object rule : ((Pol) pol).rules()) {
					if (((Rule) rule).q().name().equals(obj)) {
						return;
					}
				}
			}
		
			throw new RuntimeException(messageBody.replaceFirst("[$]", obj));
		}
	}




	// $ANTLR start "program"
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:69:1: program : ( 'POLICIES' ) ( pol )* ( 'POLICY_SETS' ) ( pSet )+ ( ( 'CONDITIONS' ) (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' |id0= IDENT '=' id1= IDENT )+ )? ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' | '/' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )? ;
	public final void program() throws RecognitionException {
		Token id0=null;
		Token id2=null;
		Token num=null;
		Token id3=null;
		Token id1=null;
		Pol pol1 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:70:2: ( ( 'POLICIES' ) ( pol )* ( 'POLICY_SETS' ) ( pSet )+ ( ( 'CONDITIONS' ) (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' |id0= IDENT '=' id1= IDENT )+ )? ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' | '/' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )? )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:2: ( 'POLICIES' ) ( pol )* ( 'POLICY_SETS' ) ( pSet )+ ( ( 'CONDITIONS' ) (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' |id0= IDENT '=' id1= IDENT )+ )? ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' | '/' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )?
			{
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:2: ( 'POLICIES' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:3: 'POLICIES'
			{
			match(input,21,FOLLOW_21_in_program57); 
			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:2: ( pol )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==IDENT) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:3: pol
					{
					pushFollow(FOLLOW_pol_in_program62);
					pol1=pol();
					state._fsp--;

					pols.put(pol1.getPolicyName(), pol1);
					}
					break;

				default :
					break loop1;
				}
			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:73:2: ( 'POLICY_SETS' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:73:3: 'POLICY_SETS'
			{
			match(input,22,FOLLOW_22_in_program70); 
			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:2: ( pSet )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==IDENT) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:3: pSet
					{
					pushFollow(FOLLOW_pSet_in_program75);
					pSet();
					state._fsp--;

					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:75:2: ( ( 'CONDITIONS' ) (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' |id0= IDENT '=' id1= IDENT )+ )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==19) ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:75:3: ( 'CONDITIONS' ) (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' |id0= IDENT '=' id1= IDENT )+
					{
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:75:3: ( 'CONDITIONS' )
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:75:4: 'CONDITIONS'
					{
					match(input,19,FOLLOW_19_in_program82); 
					}

					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:76:2: (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' |id0= IDENT '=' id1= IDENT )+
					int cnt3=0;
					loop3:
					while (true) {
						int alt3=11;
						int LA3_0 = input.LA(1);
						if ( (LA3_0==IDENT) ) {
							int LA3_2 = input.LA(2);
							if ( (LA3_2==17) ) {
								switch ( input.LA(3) ) {
								case IDENT:
									{
									switch ( input.LA(4) ) {
									case 16:
										{
										int LA3_9 = input.LA(5);
										if ( (LA3_9==NUMBER) ) {
											alt3=1;
										}
										else if ( (LA3_9==IDENT) ) {
											alt3=2;
										}

										}
										break;
									case 15:
										{
										alt3=4;
										}
										break;
									case 8:
										{
										alt3=6;
										}
										break;
									case 36:
										{
										alt3=7;
										}
										break;
									case EOF:
									case IDENT:
									case 18:
									case 20:
										{
										alt3=10;
										}
										break;
									}
									}
									break;
								case NUMBER:
									{
									alt3=3;
									}
									break;
								case 7:
									{
									alt3=5;
									}
									break;
								case 35:
									{
									alt3=8;
									}
									break;
								case 30:
									{
									alt3=9;
									}
									break;
								}
							}

						}

						switch (alt3) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:77:2: id0= IDENT '=' id2= IDENT '<=' num= NUMBER
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program91); 
							match(input,17,FOLLOW_17_in_program93); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program97); 
							match(input,16,FOLLOW_16_in_program99); 
							num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program103); 
							catchError(pSets, "PolicySet $ is not declared but is used on line \""+ (id0!=null?id0.getText():null) + " = " + (id2!=null?id2.getText():null) + " <= " + (num!=null?num.getText():null) + "\"", (id2!=null?id2.getText():null)); Condition cond = new LessThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Left<BigDecimal,PolicySet>(BigDecimal.valueOf(Double.valueOf((num!=null?num.getText():null))))); conds.put((id0!=null?id0.getText():null), cond);
							}
							break;
						case 2 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:79:2: id0= IDENT '=' id2= IDENT '<=' id3= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program117); 
							match(input,17,FOLLOW_17_in_program119); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program123); 
							match(input,16,FOLLOW_16_in_program125); 
							id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_program129); 
							catchError(pSets, "PolicySet $ is not declared but is used on line \""+(id0!=null?id0.getText():null) + " = " + (id2!=null?id2.getText():null) + " <= " + (id3!=null?id3.getText():null)+ "\"", (id2!=null?id2.getText():null), (id3!=null?id3.getText():null)); Condition cond = new LessThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Right<BigDecimal,PolicySet>(pSets.get((id3!=null?id3.getText():null)))); conds.put((id0!=null?id0.getText():null), cond);
							}
							break;
						case 3 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:81:2: id0= IDENT '=' num= NUMBER '<' id2= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program143); 
							match(input,17,FOLLOW_17_in_program145); 
							num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program149); 
							match(input,15,FOLLOW_15_in_program151); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program155); 
							catchError(pSets, "PolicySet $ is not declared but is used on line \""+(id0!=null?id0.getText():null) + " = " + (num!=null?num.getText():null) + " < " + (id2!=null?id2.getText():null)+ "\"", (id2!=null?id2.getText():null)); Condition cond = new GreaterThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Left<BigDecimal,PolicySet>(BigDecimal.valueOf(Double.valueOf((num!=null?num.getText():null))))); conds.put((id0!=null?id0.getText():null), cond);
							}
							break;
						case 4 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:83:2: id0= IDENT '=' id3= IDENT '<' id2= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program165); 
							match(input,17,FOLLOW_17_in_program167); 
							id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_program171); 
							match(input,15,FOLLOW_15_in_program173); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program177); 
							catchError(pSets,"PolicySet $ is not declared but is used on line \""+(id0!=null?id0.getText():null) + " = " + (id3!=null?id3.getText():null) + " < " + (id2!=null?id2.getText():null)+ "\"", (id3!=null?id3.getText():null), (id2!=null?id2.getText():null)); Condition cond = new GreaterThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Right<BigDecimal,PolicySet>(pSets.get((id3!=null?id3.getText():null)))); conds.put((id0!=null?id0.getText():null), cond);
							}
							break;
						case 5 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:85:2: id0= IDENT '=' '!' id1= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program191); 
							match(input,17,FOLLOW_17_in_program193); 
							match(input,7,FOLLOW_7_in_program195); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program199); 
							catchError(conds, "Condition $ is not declared but is used on line \""+(id0!=null?id0.getText():null) + " = !" + (id1!=null?id1.getText():null)+ "\"", (id1!=null?id1.getText():null)); Condition cond = new NotCondition((id1!=null?id1.getText():null)); conds.put((id0!=null?id0.getText():null), cond);
							}
							break;
						case 6 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:87:2: id0= IDENT '=' id1= IDENT '&&' id2= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program209); 
							match(input,17,FOLLOW_17_in_program211); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program215); 
							match(input,8,FOLLOW_8_in_program217); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program221); 
							catchError(conds, "Condition $ is not declared but is used on line \""+(id0!=null?id0.getText():null) + " = " + (id1!=null?id1.getText():null) + " && " + (id2!=null?id2.getText():null)+ "\"", (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); Condition cond = new AndCondition((id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); conds.put((id0!=null?id0.getText():null), cond);
							}
							break;
						case 7 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:89:2: id0= IDENT '=' id1= IDENT '||' id2= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program231); 
							match(input,17,FOLLOW_17_in_program233); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program237); 
							match(input,36,FOLLOW_36_in_program239); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program243); 
							catchError(conds, "Condition $ is not declared but is used on line \""+(id0!=null?id0.getText():null) + " = " + (id1!=null?id1.getText():null) + " || " + (id2!=null?id2.getText():null)+ "\"", (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); Condition cond = new OrCondition((id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); conds.put((id0!=null?id0.getText():null), cond);
							}
							break;
						case 8 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:91:2: id0= IDENT '=' 'true'
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program254); 
							match(input,17,FOLLOW_17_in_program256); 
							match(input,35,FOLLOW_35_in_program258); 
							Condition cond = new TrueCondition(); conds.put((id0!=null?id0.getText():null), cond);
							}
							break;
						case 9 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:93:2: id0= IDENT '=' 'false'
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program269); 
							match(input,17,FOLLOW_17_in_program271); 
							match(input,30,FOLLOW_30_in_program273); 
							Condition cond = new FalseCondition(); conds.put((id0!=null?id0.getText():null), cond);
							}
							break;
						case 10 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:95:2: id0= IDENT '=' id1= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program284); 
							match(input,17,FOLLOW_17_in_program286); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program290); 
							findInPredicates(pols, "Predicate $ has not appeared in policies prior to this point but is on line \"" + (id0!=null?id0.getText():null) + " = " + (id1!=null?id1.getText():null) + "\"", (id1!=null?id1.getText():null)); Condition cond = new PredicateCondition((id1!=null?id1.getText():null)); conds.put((id0!=null?id0.getText():null), cond);
							}
							break;

						default :
							if ( cnt3 >= 1 ) break loop3;
							EarlyExitException eee = new EarlyExitException(3, input);
							throw eee;
						}
						cnt3++;
					}

					}
					break;

			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:97:2: ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' | '/' )* )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==20) ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:97:3: 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' | '/' )*
					{
					match(input,20,FOLLOW_20_in_program302); 
					ignore = true;
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:98:2: ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' | '/' )*
					loop5:
					while (true) {
						int alt5=2;
						int LA5_0 = input.LA(1);
						if ( ((LA5_0 >= IDENT && LA5_0 <= NUMBER)||(LA5_0 >= 9 && LA5_0 <= 12)||(LA5_0 >= 14 && LA5_0 <= 17)) ) {
							alt5=1;
						}

						switch (alt5) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:
							{
							if ( (input.LA(1) >= IDENT && input.LA(1) <= NUMBER)||(input.LA(1) >= 9 && input.LA(1) <= 12)||(input.LA(1) >= 14 && input.LA(1) <= 17) ) {
								input.consume();
								state.errorRecovery=false;
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							}
							break;

						default :
							break loop5;
						}
					}

					}
					break;

			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:99:2: ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )?
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==18) ) {
				alt8=1;
			}
			switch (alt8) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:99:3: 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+
					{
					match(input,18,FOLLOW_18_in_program353); 
					ignore = false;
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:100:2: (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+
					int cnt7=0;
					loop7:
					while (true) {
						int alt7=7;
						int LA7_0 = input.LA(1);
						if ( (LA7_0==IDENT) ) {
							int LA7_2 = input.LA(2);
							if ( (LA7_2==17) ) {
								switch ( input.LA(3) ) {
								case 26:
									{
									alt7=1;
									}
									break;
								case 25:
									{
									alt7=2;
									}
									break;
								case 34:
									{
									alt7=3;
									}
									break;
								case 29:
									{
									alt7=4;
									}
									break;
								case 28:
									{
									alt7=5;
									}
									break;
								case 31:
									{
									alt7=6;
									}
									break;
								}
							}

						}

						switch (alt7) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:100:3: id0= IDENT '=' 'always_true?' id1= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program361); 
							match(input,17,FOLLOW_17_in_program363); 
							match(input,26,FOLLOW_26_in_program365); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program369); 
							catchError(conds, "Condition $ is not declared but is used on line \"" + (id0!=null?id0.getText():null) + " = always_true? " + (id1!=null?id1.getText():null)+ "\"", (id1!=null?id1.getText():null)); AnalysisGenerator analysis = new AlwaysTrue((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 2 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:101:3: id0= IDENT '=' 'always_false?' id1= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program377); 
							match(input,17,FOLLOW_17_in_program379); 
							match(input,25,FOLLOW_25_in_program381); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program385); 
							catchError(conds, "Condition $ is not declared but is used on line \"" + (id0!=null?id0.getText():null) + " = always_false? " + (id1!=null?id1.getText():null)+ "\"", (id1!=null?id1.getText():null)); AnalysisGenerator analysis = new AlwaysFalse((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 3 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:102:3: id0= IDENT '=' 'satisfiable?' id1= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program393); 
							match(input,17,FOLLOW_17_in_program395); 
							match(input,34,FOLLOW_34_in_program397); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program401); 
							catchError(conds, "Condition $ is not declared but is used on line \"" + (id0!=null?id0.getText():null) + " = satisfiable? " + (id1!=null?id1.getText():null)+ "\"", (id1!=null?id1.getText():null)); AnalysisGenerator analysis = new Satisfiable((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 4 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:103:3: id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program410); 
							match(input,17,FOLLOW_17_in_program412); 
							match(input,29,FOLLOW_29_in_program414); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program418); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program422); 
							catchError(conds, "Condition $ is not declared but is used on line \"" + (id0!=null?id0.getText():null) + " = equivalent? " + (id1!=null?id1.getText():null)+ " " + (id2!=null?id2.getText():null) + "\"", (id1!=null?id1.getText():null), (id2!=null?id2.getText():null));  AnalysisGenerator analysis = new Equivalent((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 5 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:104:3: id0= IDENT '=' 'different?' id1= IDENT id2= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program431); 
							match(input,17,FOLLOW_17_in_program433); 
							match(input,28,FOLLOW_28_in_program435); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program439); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program443); 
							catchError(conds, "Condition $ is not declared but is used on line \"" + (id0!=null?id0.getText():null) + " = different? " + (id1!=null?id1.getText():null)+ " " + (id2!=null?id2.getText():null) + "\"", (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); AnalysisGenerator analysis = new Different((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 6 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:105:3: id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program453); 
							match(input,17,FOLLOW_17_in_program455); 
							match(input,31,FOLLOW_31_in_program457); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program461); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program465); 
							catchError(conds, "Condition $ is not declared but is used on line \"" + (id0!=null?id0.getText():null) + " = implies? " + (id1!=null?id1.getText():null)+ " " + (id2!=null?id2.getText():null) + "\"", (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); AnalysisGenerator analysis = new Implies((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;

						default :
							if ( cnt7 >= 1 ) break loop7;
							EarlyExitException eee = new EarlyExitException(7, input);
							throw eee;
						}
						cnt7++;
					}

					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "program"



	// $ANTLR start "pSet"
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:110:1: pSet : (id0= IDENT '=' id1= IDENT |id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' '+' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' '*' '(' id1= IDENT ',' id2= IDENT ')' );
	public final void pSet() throws RecognitionException {
		Token id0=null;
		Token id1=null;
		Token id2=null;

		combined = new HashMap<String, Object>(); combined.putAll(pols); combined.putAll(pSets); 
		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:112:2: (id0= IDENT '=' id1= IDENT |id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' '+' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' '*' '(' id1= IDENT ',' id2= IDENT ')' )
			int alt9=5;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==IDENT) ) {
				int LA9_1 = input.LA(2);
				if ( (LA9_1==17) ) {
					switch ( input.LA(3) ) {
					case IDENT:
						{
						alt9=1;
						}
						break;
					case 32:
						{
						alt9=2;
						}
						break;
					case 33:
						{
						alt9=3;
						}
						break;
					case 12:
						{
						alt9=4;
						}
						break;
					case 11:
						{
						alt9=5;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 9, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 9, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:112:3: id0= IDENT '=' id1= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet496); 
					match(input,17,FOLLOW_17_in_pSet498); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet502); 
					catchError(combined, "Policy $ is not declared but is used on line \"" + (id0!=null?id0.getText():null) + " = " + (id1!=null?id1.getText():null) + "\"", (id1!=null?id1.getText():null)); PolicySet p = new BasicPolicySet(pols.get((id1!=null?id1.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:113:3: id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet510); 
					match(input,17,FOLLOW_17_in_pSet512); 
					match(input,32,FOLLOW_32_in_pSet514); 
					match(input,9,FOLLOW_9_in_pSet516); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet520); 
					match(input,13,FOLLOW_13_in_pSet522); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet526); 
					match(input,10,FOLLOW_10_in_pSet528); 
					catchError(combined, "$ has not been declared as a policy or a policy set, but is used on line \"" + (id0!=null?id0.getText():null) + " = max(" + (id1!=null?id1.getText():null) + ", " +(id2!=null?id2.getText():null) + ")\"", (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); PolicySet p = new MaxPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:114:3: id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet536); 
					match(input,17,FOLLOW_17_in_pSet538); 
					match(input,33,FOLLOW_33_in_pSet540); 
					match(input,9,FOLLOW_9_in_pSet542); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet546); 
					match(input,13,FOLLOW_13_in_pSet548); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet552); 
					match(input,10,FOLLOW_10_in_pSet554); 
					catchError(combined, "$ has not been declared as a policy or a policy set, but is used on line \"" + (id0!=null?id0.getText():null) + " = min(" + (id1!=null?id1.getText():null) + ", " +(id2!=null?id2.getText():null) + ")\"", (id1!=null?id1.getText():null), (id2!=null?id2.getText():null));PolicySet p = new MinPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:115:3: id0= IDENT '=' '+' '(' id1= IDENT ',' id2= IDENT ')'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet562); 
					match(input,17,FOLLOW_17_in_pSet564); 
					match(input,12,FOLLOW_12_in_pSet566); 
					match(input,9,FOLLOW_9_in_pSet568); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet572); 
					match(input,13,FOLLOW_13_in_pSet574); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet578); 
					match(input,10,FOLLOW_10_in_pSet580); 
					catchError(combined, "$ has not been declared as a policy or a policy set, but is used on line \"" + (id0!=null?id0.getText():null) + " = +(" + (id1!=null?id1.getText():null) + ", " +(id2!=null?id2.getText():null) + ")\"", (id1!=null?id1.getText():null), (id2!=null?id2.getText():null));PolicySet p = new PlusPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);
					}
					break;
				case 5 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:116:3: id0= IDENT '=' '*' '(' id1= IDENT ',' id2= IDENT ')'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet588); 
					match(input,17,FOLLOW_17_in_pSet590); 
					match(input,11,FOLLOW_11_in_pSet592); 
					match(input,9,FOLLOW_9_in_pSet594); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet598); 
					match(input,13,FOLLOW_13_in_pSet600); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet604); 
					match(input,10,FOLLOW_10_in_pSet606); 
					catchError(combined, "$ has not been declared as a policy or a policy set, but is used on line \"" + (id0!=null?id0.getText():null) + " = *(" + (id1!=null?id1.getText():null) + ", " +(id2!=null?id2.getText():null) + ")\"", (id1!=null?id1.getText():null), (id2!=null?id2.getText():null));PolicySet p = new MulPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "pSet"



	// $ANTLR start "pol"
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:119:1: pol returns [Pol p] : id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' s= score ;
	public final Pol pol() throws RecognitionException {
		Pol p = null;


		Token id1=null;
		ParserRuleReturnScope o =null;
		Score s =null;
		Rule rule2 =null;

		l = new ArrayList<Rule>(); 
		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:121:2: (id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' s= score )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:121:4: id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' s= score
			{
			id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pol631); 
			match(input,17,FOLLOW_17_in_pol633); 
			pushFollow(FOLLOW_operator_in_pol637);
			o=operator();
			state._fsp--;

			match(input,9,FOLLOW_9_in_pol639); 
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:121:33: ( rule )*
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( (LA10_0==9) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:121:34: rule
					{
					pushFollow(FOLLOW_rule_in_pol642);
					rule2=rule();
					state._fsp--;

					l.add(rule2);
					}
					break;

				default :
					break loop10;
				}
			}

			match(input,10,FOLLOW_10_in_pol648); 
			match(input,27,FOLLOW_27_in_pol650); 
			pushFollow(FOLLOW_score_in_pol654);
			s=score();
			state._fsp--;

			p = new Pol(l, OperatorResolver.apply((o!=null?input.toString(o.start,o.stop):null)), s, (id1!=null?id1.getText():null));
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return p;
	}
	// $ANTLR end "pol"



	// $ANTLR start "rule"
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:125:1: rule returns [Rule r] : '(' id0= IDENT s= score ')' ;
	public final Rule rule() throws RecognitionException {
		Rule r = null;


		Token id0=null;
		Score s =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:126:2: ( '(' id0= IDENT s= score ')' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:126:4: '(' id0= IDENT s= score ')'
			{
			match(input,9,FOLLOW_9_in_rule673); 
			id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule677); 
			pushFollow(FOLLOW_score_in_rule681);
			s=score();
			state._fsp--;

			match(input,10,FOLLOW_10_in_rule682); 
			r = new Rule(new Predicate((id0!=null?id0.getText():null)),s);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return r;
	}
	// $ANTLR end "rule"



	// $ANTLR start "score"
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:129:1: score returns [Score s] : (r= raw_score |r= raw_score '[' n1= NUMBER ',' n2= NUMBER ']' |n= NUMBER |n= NUMBER '[' n1= NUMBER ',' n2= NUMBER ']' );
	public final Score score() throws RecognitionException {
		Score s = null;


		Token n1=null;
		Token n2=null;
		Token n=null;
		VariableFormula r =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:130:2: (r= raw_score |r= raw_score '[' n1= NUMBER ',' n2= NUMBER ']' |n= NUMBER |n= NUMBER '[' n1= NUMBER ',' n2= NUMBER ']' )
			int alt11=4;
			alt11 = dfa11.predict(input);
			switch (alt11) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:130:4: r= raw_score
					{
					pushFollow(FOLLOW_raw_score_in_score703);
					r=raw_score();
					state._fsp--;

					 s = new Score(new Right<BigDecimal,VariableFormula>(r), scala.Option.apply((ScoreRange) null));
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:131:4: r= raw_score '[' n1= NUMBER ',' n2= NUMBER ']'
					{
					pushFollow(FOLLOW_raw_score_in_score712);
					r=raw_score();
					state._fsp--;

					match(input,23,FOLLOW_23_in_score714); 
					n1=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score717); 
					match(input,13,FOLLOW_13_in_score719); 
					n2=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score723); 
					match(input,24,FOLLOW_24_in_score725); 
					 s = new Score(new Right<BigDecimal,VariableFormula>(r), scala.Option.apply(new ScoreRange(BigDecimal.valueOf(Double.valueOf((n1!=null?n1.getText():null))), BigDecimal.valueOf(Double.valueOf((n2!=null?n2.getText():null))))));
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:132:4: n= NUMBER
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score734); 
					s = new Score(new Left<BigDecimal,VariableFormula>(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null)))), scala.Option.apply((ScoreRange) null));
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:133:4: n= NUMBER '[' n1= NUMBER ',' n2= NUMBER ']'
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score743); 
					match(input,23,FOLLOW_23_in_score745); 
					n1=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score748); 
					match(input,13,FOLLOW_13_in_score750); 
					n2=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score754); 
					match(input,24,FOLLOW_24_in_score756); 
					s = new Score(new Left<BigDecimal,VariableFormula>(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null)))), scala.Option.apply(new ScoreRange(BigDecimal.valueOf(Double.valueOf((n1!=null?n1.getText():null))), BigDecimal.valueOf(Double.valueOf((n2!=null?n2.getText():null))))));
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return s;
	}
	// $ANTLR end "score"



	// $ANTLR start "raw_score"
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:136:1: raw_score returns [VariableFormula s] : m0= vmult ( '+' m= mult )* ;
	public final VariableFormula raw_score() throws RecognitionException {
		VariableFormula s = null;


		Multiplier m0 =null;
		Multiplier m =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:137:2: (m0= vmult ( '+' m= mult )* )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:137:4: m0= vmult ( '+' m= mult )*
			{
			pushFollow(FOLLOW_vmult_in_raw_score776);
			m0=vmult();
			state._fsp--;

			s = new VariableFormula().add(m0);
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:137:54: ( '+' m= mult )*
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( (LA12_0==12) ) {
					alt12=1;
				}

				switch (alt12) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:137:55: '+' m= mult
					{
					match(input,12,FOLLOW_12_in_raw_score781); 
					pushFollow(FOLLOW_mult_in_raw_score785);
					m=mult();
					state._fsp--;

					s.add(m);
					}
					break;

				default :
					break loop12;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return s;
	}
	// $ANTLR end "raw_score"



	// $ANTLR start "vmult"
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:140:1: vmult returns [Multiplier m] : (id1= IDENT '*' n= NUMBER |n= NUMBER '*' id1= IDENT |id1= IDENT );
	public final Multiplier vmult() throws RecognitionException {
		Multiplier m = null;


		Token id1=null;
		Token n=null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:141:2: (id1= IDENT '*' n= NUMBER |n= NUMBER '*' id1= IDENT |id1= IDENT )
			int alt13=3;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==IDENT) ) {
				int LA13_1 = input.LA(2);
				if ( (LA13_1==11) ) {
					alt13=1;
				}
				else if ( (LA13_1==IDENT||LA13_1==10||LA13_1==12||(LA13_1 >= 22 && LA13_1 <= 23)) ) {
					alt13=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 13, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA13_0==NUMBER) ) {
				alt13=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}

			switch (alt13) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:141:4: id1= IDENT '*' n= NUMBER
					{
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_vmult809); 
					match(input,11,FOLLOW_11_in_vmult811); 
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_vmult815); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null));
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:142:4: n= NUMBER '*' id1= IDENT
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_vmult824); 
					match(input,11,FOLLOW_11_in_vmult826); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_vmult830); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null));
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:143:4: id1= IDENT
					{
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_vmult839); 
					m = new Multiplier(BigDecimal.valueOf(1), (id1!=null?id1.getText():null));
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return m;
	}
	// $ANTLR end "vmult"



	// $ANTLR start "mult"
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:146:1: mult returns [Multiplier m] : (id1= IDENT '*' n= NUMBER |n= NUMBER '*' id1= IDENT |n= NUMBER |id1= IDENT );
	public final Multiplier mult() throws RecognitionException {
		Multiplier m = null;


		Token id1=null;
		Token n=null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:147:2: (id1= IDENT '*' n= NUMBER |n= NUMBER '*' id1= IDENT |n= NUMBER |id1= IDENT )
			int alt14=4;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==IDENT) ) {
				int LA14_1 = input.LA(2);
				if ( (LA14_1==11) ) {
					alt14=1;
				}
				else if ( (LA14_1==IDENT||LA14_1==10||LA14_1==12||(LA14_1 >= 22 && LA14_1 <= 23)) ) {
					alt14=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 14, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA14_0==NUMBER) ) {
				int LA14_2 = input.LA(2);
				if ( (LA14_2==11) ) {
					alt14=2;
				}
				else if ( (LA14_2==IDENT||LA14_2==10||LA14_2==12||(LA14_2 >= 22 && LA14_2 <= 23)) ) {
					alt14=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 14, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}

			switch (alt14) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:147:4: id1= IDENT '*' n= NUMBER
					{
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_mult858); 
					match(input,11,FOLLOW_11_in_mult860); 
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_mult864); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null));
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:148:4: n= NUMBER '*' id1= IDENT
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_mult873); 
					match(input,11,FOLLOW_11_in_mult875); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_mult879); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null));
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:149:4: n= NUMBER
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_mult888); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), "");
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:150:4: id1= IDENT
					{
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_mult897); 
					m = new Multiplier(BigDecimal.valueOf(1), (id1!=null?id1.getText():null));
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return m;
	}
	// $ANTLR end "mult"


	public static class operator_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "operator"
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:153:1: operator : ( 'max' | 'min' | '+' | '*' );
	public final PealProgramParser.operator_return operator() throws RecognitionException {
		PealProgramParser.operator_return retval = new PealProgramParser.operator_return();
		retval.start = input.LT(1);

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:153:10: ( 'max' | 'min' | '+' | '*' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:
			{
			if ( (input.LA(1) >= 11 && input.LA(1) <= 12)||(input.LA(1) >= 32 && input.LA(1) <= 33) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "operator"

	// Delegated rules


	protected DFA11 dfa11 = new DFA11(this);
	static final String DFA11_eotS =
		"\22\uffff";
	static final String DFA11_eofS =
		"\22\uffff";
	static final String DFA11_minS =
		"\3\4\1\5\1\4\2\uffff\1\4\2\uffff\4\4\1\5\3\4";
	static final String DFA11_maxS =
		"\1\5\2\27\2\5\2\uffff\1\4\2\uffff\4\27\1\5\1\4\2\27";
	static final String DFA11_acceptS =
		"\5\uffff\1\1\1\2\1\uffff\1\4\1\3\10\uffff";
	static final String DFA11_specialS =
		"\22\uffff}>";
	static final String[] DFA11_transitionS = {
			"\1\1\1\2",
			"\1\5\5\uffff\1\5\1\3\1\4\11\uffff\1\5\1\6",
			"\1\11\5\uffff\1\11\1\7\12\uffff\1\11\1\10",
			"\1\12",
			"\1\13\1\14",
			"",
			"",
			"\1\15",
			"",
			"",
			"\1\5\5\uffff\1\5\1\uffff\1\4\11\uffff\1\5\1\6",
			"\1\5\5\uffff\1\5\1\16\1\4\11\uffff\1\5\1\6",
			"\1\5\5\uffff\1\5\1\17\1\4\11\uffff\1\5\1\6",
			"\1\5\5\uffff\1\5\1\uffff\1\4\11\uffff\1\5\1\6",
			"\1\20",
			"\1\21",
			"\1\5\5\uffff\1\5\1\uffff\1\4\11\uffff\1\5\1\6",
			"\1\5\5\uffff\1\5\1\uffff\1\4\11\uffff\1\5\1\6"
	};

	static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
	static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
	static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
	static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
	static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
	static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
	static final short[][] DFA11_transition;

	static {
		int numStates = DFA11_transitionS.length;
		DFA11_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
		}
	}

	protected class DFA11 extends DFA {

		public DFA11(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 11;
			this.eot = DFA11_eot;
			this.eof = DFA11_eof;
			this.min = DFA11_min;
			this.max = DFA11_max;
			this.accept = DFA11_accept;
			this.special = DFA11_special;
			this.transition = DFA11_transition;
		}
		@Override
		public String getDescription() {
			return "129:1: score returns [Score s] : (r= raw_score |r= raw_score '[' n1= NUMBER ',' n2= NUMBER ']' |n= NUMBER |n= NUMBER '[' n1= NUMBER ',' n2= NUMBER ']' );";
		}
	}

	public static final BitSet FOLLOW_21_in_program57 = new BitSet(new long[]{0x0000000000400010L});
	public static final BitSet FOLLOW_pol_in_program62 = new BitSet(new long[]{0x0000000000400010L});
	public static final BitSet FOLLOW_22_in_program70 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_pSet_in_program75 = new BitSet(new long[]{0x00000000001C0012L});
	public static final BitSet FOLLOW_19_in_program82 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program91 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program93 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program97 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program99 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_program103 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program117 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program119 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program123 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program125 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program129 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program143 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program145 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_program149 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_program151 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program155 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program165 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program167 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program171 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_program173 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program177 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program191 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program193 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_7_in_program195 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program199 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program209 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program211 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program215 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_8_in_program217 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program221 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program231 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program233 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program237 = new BitSet(new long[]{0x0000001000000000L});
	public static final BitSet FOLLOW_36_in_program239 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program243 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program254 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program256 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_35_in_program258 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program269 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program271 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_30_in_program273 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program284 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program286 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program290 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_20_in_program302 = new BitSet(new long[]{0x000000000007DE32L});
	public static final BitSet FOLLOW_18_in_program353 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program361 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program363 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_26_in_program365 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program369 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program377 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program379 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_25_in_program381 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program385 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program393 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program395 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_34_in_program397 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program401 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program410 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program412 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_29_in_program414 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program418 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program422 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program431 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program433 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_28_in_program435 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program439 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program443 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program453 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program455 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_31_in_program457 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program461 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program465 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_pSet496 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_pSet498 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet502 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pSet510 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_pSet512 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_32_in_pSet514 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pSet516 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet520 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_pSet522 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet526 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_pSet528 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pSet536 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_pSet538 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_33_in_pSet540 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pSet542 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet546 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_pSet548 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet552 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_pSet554 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pSet562 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_pSet564 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_12_in_pSet566 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pSet568 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet572 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_pSet574 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet578 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_pSet580 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pSet588 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_pSet590 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_pSet592 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pSet594 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet598 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_pSet600 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet604 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_pSet606 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pol631 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_pol633 = new BitSet(new long[]{0x0000000300001800L});
	public static final BitSet FOLLOW_operator_in_pol637 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pol639 = new BitSet(new long[]{0x0000000000000600L});
	public static final BitSet FOLLOW_rule_in_pol642 = new BitSet(new long[]{0x0000000000000600L});
	public static final BitSet FOLLOW_10_in_pol648 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_pol650 = new BitSet(new long[]{0x0000000000000030L});
	public static final BitSet FOLLOW_score_in_pol654 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_rule673 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_rule677 = new BitSet(new long[]{0x0000000000000030L});
	public static final BitSet FOLLOW_score_in_rule681 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_rule682 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_raw_score_in_score703 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_raw_score_in_score712 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_score714 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_score717 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_score719 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_score723 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_score725 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_score734 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_score743 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_score745 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_score748 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_score750 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_score754 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_score756 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_vmult_in_raw_score776 = new BitSet(new long[]{0x0000000000001002L});
	public static final BitSet FOLLOW_12_in_raw_score781 = new BitSet(new long[]{0x0000000000000030L});
	public static final BitSet FOLLOW_mult_in_raw_score785 = new BitSet(new long[]{0x0000000000001002L});
	public static final BitSet FOLLOW_IDENT_in_vmult809 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_vmult811 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_vmult815 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_vmult824 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_vmult826 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_vmult830 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_vmult839 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_mult858 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_mult860 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_mult864 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_mult873 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_mult875 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_mult879 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_mult888 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_mult897 = new BitSet(new long[]{0x0000000000000002L});
}
