// $ANTLR 3.5.1 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2014-08-04 11:46:58

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
	private boolean ignore = false;


	@Override
	public void reportError(RecognitionException e) {
		throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
	}

	private void catchError(Map someSet, String header, String messageBody, String... objs) {
		for (String obj : objs) {
			if (!someSet.containsKey(obj)) {
				throw new RuntimeException(header + " " + obj + " is not declared in " + messageBody);
			}
		}
	}




	// $ANTLR start "program"
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:54:1: program : ( 'POLICIES' ) ( pol )* ( 'POLICY_SETS' ) ( pSet )+ ( 'CONDITIONS' ) (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' |id0= IDENT '=' id1= IDENT )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' | '/' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )? ;
	public final void program() throws RecognitionException {
		Token id0=null;
		Token id2=null;
		Token num=null;
		Token id3=null;
		Token id1=null;
		Pol pol1 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:55:2: ( ( 'POLICIES' ) ( pol )* ( 'POLICY_SETS' ) ( pSet )+ ( 'CONDITIONS' ) (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' |id0= IDENT '=' id1= IDENT )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' | '/' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )? )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:56:2: ( 'POLICIES' ) ( pol )* ( 'POLICY_SETS' ) ( pSet )+ ( 'CONDITIONS' ) (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' |id0= IDENT '=' id1= IDENT )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' | '/' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )?
			{
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:56:2: ( 'POLICIES' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:56:3: 'POLICIES'
			{
			match(input,21,FOLLOW_21_in_program57); 
			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:57:2: ( pol )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==IDENT) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:57:3: pol
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

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:58:2: ( 'POLICY_SETS' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:58:3: 'POLICY_SETS'
			{
			match(input,22,FOLLOW_22_in_program70); 
			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:2: ( pSet )+
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
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:3: pSet
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

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:60:2: ( 'CONDITIONS' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:60:3: 'CONDITIONS'
			{
			match(input,19,FOLLOW_19_in_program81); 
			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:61:2: (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' |id0= IDENT '=' id1= IDENT )+
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
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:62:2: id0= IDENT '=' id2= IDENT '<=' num= NUMBER
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program90); 
					match(input,17,FOLLOW_17_in_program92); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program96); 
					match(input,16,FOLLOW_16_in_program98); 
					num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program102); 
					catchError(pSets, "PolicySet", (id0!=null?id0.getText():null) + " = " + (id2!=null?id2.getText():null) + " <= " + (num!=null?num.getText():null) , (id2!=null?id2.getText():null)); Condition cond = new LessThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Left<BigDecimal,PolicySet>(BigDecimal.valueOf(Double.valueOf((num!=null?num.getText():null))))); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:64:2: id0= IDENT '=' id2= IDENT '<=' id3= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program116); 
					match(input,17,FOLLOW_17_in_program118); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program122); 
					match(input,16,FOLLOW_16_in_program124); 
					id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_program128); 
					catchError(pSets, "PolicySet",(id0!=null?id0.getText():null) + " = " + (id2!=null?id2.getText():null) + " <= " + (id3!=null?id3.getText():null), (id2!=null?id2.getText():null), (id3!=null?id3.getText():null)); Condition cond = new LessThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Right<BigDecimal,PolicySet>(pSets.get((id3!=null?id3.getText():null)))); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:66:2: id0= IDENT '=' num= NUMBER '<' id2= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program142); 
					match(input,17,FOLLOW_17_in_program144); 
					num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program148); 
					match(input,15,FOLLOW_15_in_program150); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program154); 
					catchError(pSets, "PolicySet",(id0!=null?id0.getText():null) + " = " + (num!=null?num.getText():null) + " < " + (id2!=null?id2.getText():null), (id2!=null?id2.getText():null)); Condition cond = new GreaterThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Left<BigDecimal,PolicySet>(BigDecimal.valueOf(Double.valueOf((num!=null?num.getText():null))))); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:68:2: id0= IDENT '=' id3= IDENT '<' id2= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program164); 
					match(input,17,FOLLOW_17_in_program166); 
					id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_program170); 
					match(input,15,FOLLOW_15_in_program172); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program176); 
					catchError(pSets,"PolicySet", (id0!=null?id0.getText():null) + " = " + (id3!=null?id3.getText():null) + " < " + (id2!=null?id2.getText():null), (id3!=null?id3.getText():null), (id2!=null?id2.getText():null)); Condition cond = new GreaterThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Right<BigDecimal,PolicySet>(pSets.get((id3!=null?id3.getText():null)))); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 5 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:70:2: id0= IDENT '=' '!' id1= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program190); 
					match(input,17,FOLLOW_17_in_program192); 
					match(input,7,FOLLOW_7_in_program194); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program198); 
					catchError(conds, "Condition",(id0!=null?id0.getText():null) + " = !" + (id1!=null?id1.getText():null), (id1!=null?id1.getText():null)); Condition cond = new NotCondition((id1!=null?id1.getText():null)); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 6 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:2: id0= IDENT '=' id1= IDENT '&&' id2= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program208); 
					match(input,17,FOLLOW_17_in_program210); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program214); 
					match(input,8,FOLLOW_8_in_program216); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program220); 
					catchError(conds, "Condition",(id0!=null?id0.getText():null) + " = " + (id1!=null?id1.getText():null) + " && " + (id2!=null?id2.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); Condition cond = new AndCondition((id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 7 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:2: id0= IDENT '=' id1= IDENT '||' id2= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program230); 
					match(input,17,FOLLOW_17_in_program232); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program236); 
					match(input,36,FOLLOW_36_in_program238); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program242); 
					catchError(conds, "Condition",(id0!=null?id0.getText():null) + " = " + (id1!=null?id1.getText():null) + " || " + (id2!=null?id2.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); Condition cond = new OrCondition((id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 8 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:76:2: id0= IDENT '=' 'true'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program253); 
					match(input,17,FOLLOW_17_in_program255); 
					match(input,35,FOLLOW_35_in_program257); 
					Condition cond = new TrueCondition(); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 9 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:78:2: id0= IDENT '=' 'false'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program268); 
					match(input,17,FOLLOW_17_in_program270); 
					match(input,30,FOLLOW_30_in_program272); 
					Condition cond = new FalseCondition(); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 10 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:80:2: id0= IDENT '=' id1= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program283); 
					match(input,17,FOLLOW_17_in_program285); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program289); 
					Condition cond = new PredicateCondition((id1!=null?id1.getText():null)); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:82:2: ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' | '/' )* )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==20) ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:82:3: 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' | '/' )*
					{
					match(input,20,FOLLOW_20_in_program299); 
					ignore = true;
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:83:2: ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' | '/' )*
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( ((LA4_0 >= IDENT && LA4_0 <= NUMBER)||(LA4_0 >= 9 && LA4_0 <= 12)||(LA4_0 >= 14 && LA4_0 <= 17)) ) {
							alt4=1;
						}

						switch (alt4) {
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
							break loop4;
						}
					}

					}
					break;

			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:84:2: ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==18) ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:84:3: 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+
					{
					match(input,18,FOLLOW_18_in_program350); 
					ignore = false;
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:85:2: (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+
					int cnt6=0;
					loop6:
					while (true) {
						int alt6=7;
						int LA6_0 = input.LA(1);
						if ( (LA6_0==IDENT) ) {
							int LA6_2 = input.LA(2);
							if ( (LA6_2==17) ) {
								switch ( input.LA(3) ) {
								case 26:
									{
									alt6=1;
									}
									break;
								case 25:
									{
									alt6=2;
									}
									break;
								case 34:
									{
									alt6=3;
									}
									break;
								case 29:
									{
									alt6=4;
									}
									break;
								case 28:
									{
									alt6=5;
									}
									break;
								case 31:
									{
									alt6=6;
									}
									break;
								}
							}

						}

						switch (alt6) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:85:3: id0= IDENT '=' 'always_true?' id1= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program358); 
							match(input,17,FOLLOW_17_in_program360); 
							match(input,26,FOLLOW_26_in_program362); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program366); 
							AnalysisGenerator analysis = new AlwaysTrue((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 2 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:86:3: id0= IDENT '=' 'always_false?' id1= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program374); 
							match(input,17,FOLLOW_17_in_program376); 
							match(input,25,FOLLOW_25_in_program378); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program382); 
							AnalysisGenerator analysis = new AlwaysFalse((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 3 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:87:3: id0= IDENT '=' 'satisfiable?' id1= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program390); 
							match(input,17,FOLLOW_17_in_program392); 
							match(input,34,FOLLOW_34_in_program394); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program398); 
							AnalysisGenerator analysis = new Satisfiable((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 4 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:88:3: id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program407); 
							match(input,17,FOLLOW_17_in_program409); 
							match(input,29,FOLLOW_29_in_program411); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program415); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program419); 
							AnalysisGenerator analysis = new Equivalent((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 5 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:89:3: id0= IDENT '=' 'different?' id1= IDENT id2= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program428); 
							match(input,17,FOLLOW_17_in_program430); 
							match(input,28,FOLLOW_28_in_program432); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program436); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program440); 
							AnalysisGenerator analysis = new Different((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 6 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:90:3: id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program450); 
							match(input,17,FOLLOW_17_in_program452); 
							match(input,31,FOLLOW_31_in_program454); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program458); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program462); 
							AnalysisGenerator analysis = new Implies((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;

						default :
							if ( cnt6 >= 1 ) break loop6;
							EarlyExitException eee = new EarlyExitException(6, input);
							throw eee;
						}
						cnt6++;
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:95:1: pSet : (id0= IDENT '=' id1= IDENT |id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' '+' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' '*' '(' id1= IDENT ',' id2= IDENT ')' );
	public final void pSet() throws RecognitionException {
		Token id0=null;
		Token id1=null;
		Token id2=null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:96:2: (id0= IDENT '=' id1= IDENT |id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' '+' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' '*' '(' id1= IDENT ',' id2= IDENT ')' )
			int alt8=5;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==IDENT) ) {
				int LA8_1 = input.LA(2);
				if ( (LA8_1==17) ) {
					switch ( input.LA(3) ) {
					case IDENT:
						{
						alt8=1;
						}
						break;
					case 32:
						{
						alt8=2;
						}
						break;
					case 33:
						{
						alt8=3;
						}
						break;
					case 12:
						{
						alt8=4;
						}
						break;
					case 11:
						{
						alt8=5;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 8, 2, input);
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
							new NoViableAltException("", 8, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}

			switch (alt8) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:96:3: id0= IDENT '=' id1= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet488); 
					match(input,17,FOLLOW_17_in_pSet490); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet494); 
					PolicySet p = new BasicPolicySet(pols.get((id1!=null?id1.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:97:3: id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet502); 
					match(input,17,FOLLOW_17_in_pSet504); 
					match(input,32,FOLLOW_32_in_pSet506); 
					match(input,9,FOLLOW_9_in_pSet508); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet512); 
					match(input,13,FOLLOW_13_in_pSet514); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet518); 
					match(input,10,FOLLOW_10_in_pSet520); 
					PolicySet p = new MaxPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:98:3: id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet528); 
					match(input,17,FOLLOW_17_in_pSet530); 
					match(input,33,FOLLOW_33_in_pSet532); 
					match(input,9,FOLLOW_9_in_pSet534); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet538); 
					match(input,13,FOLLOW_13_in_pSet540); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet544); 
					match(input,10,FOLLOW_10_in_pSet546); 
					PolicySet p = new MinPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:99:3: id0= IDENT '=' '+' '(' id1= IDENT ',' id2= IDENT ')'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet554); 
					match(input,17,FOLLOW_17_in_pSet556); 
					match(input,12,FOLLOW_12_in_pSet558); 
					match(input,9,FOLLOW_9_in_pSet560); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet564); 
					match(input,13,FOLLOW_13_in_pSet566); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet570); 
					match(input,10,FOLLOW_10_in_pSet572); 
					PolicySet p = new PlusPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);
					}
					break;
				case 5 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:100:3: id0= IDENT '=' '*' '(' id1= IDENT ',' id2= IDENT ')'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet580); 
					match(input,17,FOLLOW_17_in_pSet582); 
					match(input,11,FOLLOW_11_in_pSet584); 
					match(input,9,FOLLOW_9_in_pSet586); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet590); 
					match(input,13,FOLLOW_13_in_pSet592); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet596); 
					match(input,10,FOLLOW_10_in_pSet598); 
					PolicySet p = new MulPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:103:1: pol returns [Pol p] : id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' s= score ;
	public final Pol pol() throws RecognitionException {
		Pol p = null;


		Token id1=null;
		ParserRuleReturnScope o =null;
		Score s =null;
		Rule rule2 =null;

		l = new ArrayList<Rule>(); 
		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:105:2: (id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' s= score )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:105:4: id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' s= score
			{
			id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pol623); 
			match(input,17,FOLLOW_17_in_pol625); 
			pushFollow(FOLLOW_operator_in_pol629);
			o=operator();
			state._fsp--;

			match(input,9,FOLLOW_9_in_pol631); 
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:105:33: ( rule )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0==9) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:105:34: rule
					{
					pushFollow(FOLLOW_rule_in_pol634);
					rule2=rule();
					state._fsp--;

					l.add(rule2);
					}
					break;

				default :
					break loop9;
				}
			}

			match(input,10,FOLLOW_10_in_pol640); 
			match(input,27,FOLLOW_27_in_pol642); 
			pushFollow(FOLLOW_score_in_pol646);
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:109:1: rule returns [Rule r] : '(' id0= IDENT s= score ')' ;
	public final Rule rule() throws RecognitionException {
		Rule r = null;


		Token id0=null;
		Score s =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:110:2: ( '(' id0= IDENT s= score ')' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:110:4: '(' id0= IDENT s= score ')'
			{
			match(input,9,FOLLOW_9_in_rule665); 
			id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule669); 
			pushFollow(FOLLOW_score_in_rule673);
			s=score();
			state._fsp--;

			match(input,10,FOLLOW_10_in_rule674); 
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:113:1: score returns [Score s] : (r= raw_score |r= raw_score '[' n1= NUMBER ',' n2= NUMBER ']' |n= NUMBER |n= NUMBER '[' n1= NUMBER ',' n2= NUMBER ']' );
	public final Score score() throws RecognitionException {
		Score s = null;


		Token n1=null;
		Token n2=null;
		Token n=null;
		VariableFormula r =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:114:2: (r= raw_score |r= raw_score '[' n1= NUMBER ',' n2= NUMBER ']' |n= NUMBER |n= NUMBER '[' n1= NUMBER ',' n2= NUMBER ']' )
			int alt10=4;
			alt10 = dfa10.predict(input);
			switch (alt10) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:114:4: r= raw_score
					{
					pushFollow(FOLLOW_raw_score_in_score695);
					r=raw_score();
					state._fsp--;

					 s = new Score(new Right<BigDecimal,VariableFormula>(r), scala.Option.apply((ScoreRange) null));
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:115:4: r= raw_score '[' n1= NUMBER ',' n2= NUMBER ']'
					{
					pushFollow(FOLLOW_raw_score_in_score704);
					r=raw_score();
					state._fsp--;

					match(input,23,FOLLOW_23_in_score706); 
					n1=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score709); 
					match(input,13,FOLLOW_13_in_score711); 
					n2=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score715); 
					match(input,24,FOLLOW_24_in_score717); 
					 s = new Score(new Right<BigDecimal,VariableFormula>(r), scala.Option.apply(new ScoreRange(BigDecimal.valueOf(Double.valueOf((n1!=null?n1.getText():null))), BigDecimal.valueOf(Double.valueOf((n2!=null?n2.getText():null))))));
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:116:4: n= NUMBER
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score726); 
					s = new Score(new Left<BigDecimal,VariableFormula>(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null)))), scala.Option.apply((ScoreRange) null));
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:117:4: n= NUMBER '[' n1= NUMBER ',' n2= NUMBER ']'
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score735); 
					match(input,23,FOLLOW_23_in_score737); 
					n1=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score740); 
					match(input,13,FOLLOW_13_in_score742); 
					n2=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score746); 
					match(input,24,FOLLOW_24_in_score748); 
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:120:1: raw_score returns [VariableFormula s] : m0= vmult ( '+' m= mult )* ;
	public final VariableFormula raw_score() throws RecognitionException {
		VariableFormula s = null;


		Multiplier m0 =null;
		Multiplier m =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:121:2: (m0= vmult ( '+' m= mult )* )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:121:4: m0= vmult ( '+' m= mult )*
			{
			pushFollow(FOLLOW_vmult_in_raw_score768);
			m0=vmult();
			state._fsp--;

			s = new VariableFormula().add(m0);
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:121:54: ( '+' m= mult )*
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( (LA11_0==12) ) {
					alt11=1;
				}

				switch (alt11) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:121:55: '+' m= mult
					{
					match(input,12,FOLLOW_12_in_raw_score773); 
					pushFollow(FOLLOW_mult_in_raw_score777);
					m=mult();
					state._fsp--;

					s.add(m);
					}
					break;

				default :
					break loop11;
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:124:1: vmult returns [Multiplier m] : (id1= IDENT '*' n= NUMBER |n= NUMBER '*' id1= IDENT |id1= IDENT );
	public final Multiplier vmult() throws RecognitionException {
		Multiplier m = null;


		Token id1=null;
		Token n=null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:125:2: (id1= IDENT '*' n= NUMBER |n= NUMBER '*' id1= IDENT |id1= IDENT )
			int alt12=3;
			int LA12_0 = input.LA(1);
			if ( (LA12_0==IDENT) ) {
				int LA12_1 = input.LA(2);
				if ( (LA12_1==11) ) {
					alt12=1;
				}
				else if ( (LA12_1==IDENT||LA12_1==10||LA12_1==12||(LA12_1 >= 22 && LA12_1 <= 23)) ) {
					alt12=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 12, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA12_0==NUMBER) ) {
				alt12=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}

			switch (alt12) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:125:4: id1= IDENT '*' n= NUMBER
					{
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_vmult801); 
					match(input,11,FOLLOW_11_in_vmult803); 
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_vmult807); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null));
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:126:4: n= NUMBER '*' id1= IDENT
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_vmult816); 
					match(input,11,FOLLOW_11_in_vmult818); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_vmult822); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null));
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:127:4: id1= IDENT
					{
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_vmult831); 
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:130:1: mult returns [Multiplier m] : (id1= IDENT '*' n= NUMBER |n= NUMBER '*' id1= IDENT |n= NUMBER |id1= IDENT );
	public final Multiplier mult() throws RecognitionException {
		Multiplier m = null;


		Token id1=null;
		Token n=null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:131:2: (id1= IDENT '*' n= NUMBER |n= NUMBER '*' id1= IDENT |n= NUMBER |id1= IDENT )
			int alt13=4;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==IDENT) ) {
				int LA13_1 = input.LA(2);
				if ( (LA13_1==11) ) {
					alt13=1;
				}
				else if ( (LA13_1==IDENT||LA13_1==10||LA13_1==12||(LA13_1 >= 22 && LA13_1 <= 23)) ) {
					alt13=4;
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
				int LA13_2 = input.LA(2);
				if ( (LA13_2==11) ) {
					alt13=2;
				}
				else if ( (LA13_2==IDENT||LA13_2==10||LA13_2==12||(LA13_2 >= 22 && LA13_2 <= 23)) ) {
					alt13=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 13, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}

			switch (alt13) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:131:4: id1= IDENT '*' n= NUMBER
					{
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_mult850); 
					match(input,11,FOLLOW_11_in_mult852); 
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_mult856); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null));
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:132:4: n= NUMBER '*' id1= IDENT
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_mult865); 
					match(input,11,FOLLOW_11_in_mult867); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_mult871); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null));
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:133:4: n= NUMBER
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_mult880); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), "");
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:134:4: id1= IDENT
					{
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_mult889); 
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:137:1: operator : ( 'max' | 'min' | '+' | '*' );
	public final PealProgramParser.operator_return operator() throws RecognitionException {
		PealProgramParser.operator_return retval = new PealProgramParser.operator_return();
		retval.start = input.LT(1);

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:137:10: ( 'max' | 'min' | '+' | '*' )
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


	protected DFA10 dfa10 = new DFA10(this);
	static final String DFA10_eotS =
		"\22\uffff";
	static final String DFA10_eofS =
		"\22\uffff";
	static final String DFA10_minS =
		"\3\4\1\5\1\4\2\uffff\1\4\2\uffff\4\4\1\5\3\4";
	static final String DFA10_maxS =
		"\1\5\2\27\2\5\2\uffff\1\4\2\uffff\4\27\1\5\1\4\2\27";
	static final String DFA10_acceptS =
		"\5\uffff\1\1\1\2\1\uffff\1\4\1\3\10\uffff";
	static final String DFA10_specialS =
		"\22\uffff}>";
	static final String[] DFA10_transitionS = {
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

	static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
	static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
	static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
	static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
	static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
	static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
	static final short[][] DFA10_transition;

	static {
		int numStates = DFA10_transitionS.length;
		DFA10_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
		}
	}

	protected class DFA10 extends DFA {

		public DFA10(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 10;
			this.eot = DFA10_eot;
			this.eof = DFA10_eof;
			this.min = DFA10_min;
			this.max = DFA10_max;
			this.accept = DFA10_accept;
			this.special = DFA10_special;
			this.transition = DFA10_transition;
		}
		@Override
		public String getDescription() {
			return "113:1: score returns [Score s] : (r= raw_score |r= raw_score '[' n1= NUMBER ',' n2= NUMBER ']' |n= NUMBER |n= NUMBER '[' n1= NUMBER ',' n2= NUMBER ']' );";
		}
	}

	public static final BitSet FOLLOW_21_in_program57 = new BitSet(new long[]{0x0000000000400010L});
	public static final BitSet FOLLOW_pol_in_program62 = new BitSet(new long[]{0x0000000000400010L});
	public static final BitSet FOLLOW_22_in_program70 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_pSet_in_program75 = new BitSet(new long[]{0x0000000000080010L});
	public static final BitSet FOLLOW_19_in_program81 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program90 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program92 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program96 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program98 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_program102 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program116 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program118 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program122 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program124 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program128 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program142 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program144 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_program148 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_program150 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program154 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program164 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program166 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program170 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_program172 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program176 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program190 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program192 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_7_in_program194 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program198 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program208 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program210 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program214 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_8_in_program216 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program220 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program230 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program232 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program236 = new BitSet(new long[]{0x0000001000000000L});
	public static final BitSet FOLLOW_36_in_program238 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program242 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program253 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program255 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_35_in_program257 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program268 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program270 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_30_in_program272 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_IDENT_in_program283 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program285 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program289 = new BitSet(new long[]{0x0000000000140012L});
	public static final BitSet FOLLOW_20_in_program299 = new BitSet(new long[]{0x000000000007DE32L});
	public static final BitSet FOLLOW_18_in_program350 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program358 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program360 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_26_in_program362 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program366 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program374 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program376 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_25_in_program378 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program382 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program390 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program392 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_34_in_program394 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program398 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program407 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program409 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_29_in_program411 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program415 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program419 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program428 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program430 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_28_in_program432 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program436 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program440 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program450 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_program452 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_31_in_program454 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program458 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program462 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_pSet488 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_pSet490 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet494 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pSet502 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_pSet504 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_32_in_pSet506 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pSet508 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet512 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_pSet514 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet518 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_pSet520 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pSet528 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_pSet530 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_33_in_pSet532 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pSet534 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet538 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_pSet540 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet544 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_pSet546 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pSet554 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_pSet556 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_12_in_pSet558 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pSet560 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet564 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_pSet566 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet570 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_pSet572 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pSet580 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_pSet582 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_pSet584 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pSet586 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet590 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_pSet592 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet596 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_pSet598 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pol623 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_pol625 = new BitSet(new long[]{0x0000000300001800L});
	public static final BitSet FOLLOW_operator_in_pol629 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pol631 = new BitSet(new long[]{0x0000000000000600L});
	public static final BitSet FOLLOW_rule_in_pol634 = new BitSet(new long[]{0x0000000000000600L});
	public static final BitSet FOLLOW_10_in_pol640 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_pol642 = new BitSet(new long[]{0x0000000000000030L});
	public static final BitSet FOLLOW_score_in_pol646 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_rule665 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_rule669 = new BitSet(new long[]{0x0000000000000030L});
	public static final BitSet FOLLOW_score_in_rule673 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_rule674 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_raw_score_in_score695 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_raw_score_in_score704 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_score706 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_score709 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_score711 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_score715 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_score717 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_score726 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_score735 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_score737 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_score740 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_score742 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_score746 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_score748 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_vmult_in_raw_score768 = new BitSet(new long[]{0x0000000000001002L});
	public static final BitSet FOLLOW_12_in_raw_score773 = new BitSet(new long[]{0x0000000000000030L});
	public static final BitSet FOLLOW_mult_in_raw_score777 = new BitSet(new long[]{0x0000000000001002L});
	public static final BitSet FOLLOW_IDENT_in_vmult801 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_vmult803 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_vmult807 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_vmult816 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_vmult818 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_vmult822 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_vmult831 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_mult850 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_mult852 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_mult856 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_mult865 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_mult867 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_mult871 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_mult880 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_mult889 = new BitSet(new long[]{0x0000000000000002L});
}
