// $ANTLR 3.5.1 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-12-03 13:14:05

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
		"'&&'", "'('", "')'", "'*'", "'+'", "','", "'<'", "'<='", "'='", "'ANALYSES'", 
		"'CONDITIONS'", "'DOMAIN_SPECIFICS'", "'POLICIES'", "'POLICY_SETS'", "'always_false?'", 
		"'always_true?'", "'default'", "'different?'", "'equivalent?'", "'false'", 
		"'implies?'", "'max'", "'min'", "'satisfiable?'", "'true'", "'||'"
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




	// $ANTLR start "program"
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:46:1: program : ( 'POLICIES' )? ( pol )* ( 'POLICY_SETS' )? ( pSet )+ ( 'CONDITIONS' )? (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )? ;
	public final void program() throws RecognitionException {
		Token id0=null;
		Token id2=null;
		Token num=null;
		Token id3=null;
		Token id1=null;
		Pol pol1 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:2: ( ( 'POLICIES' )? ( pol )* ( 'POLICY_SETS' )? ( pSet )+ ( 'CONDITIONS' )? (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )? )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:2: ( 'POLICIES' )? ( pol )* ( 'POLICY_SETS' )? ( pSet )+ ( 'CONDITIONS' )? (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )?
			{
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:2: ( 'POLICIES' )?
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0==20) ) {
				alt1=1;
			}
			switch (alt1) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:3: 'POLICIES'
					{
					match(input,20,FOLLOW_20_in_program57); 
					}
					break;

			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:49:2: ( pol )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==IDENT) ) {
					int LA2_2 = input.LA(2);
					if ( (LA2_2==16) ) {
						switch ( input.LA(3) ) {
						case 29:
							{
							int LA2_4 = input.LA(4);
							if ( (LA2_4==9) ) {
								int LA2_7 = input.LA(5);
								if ( ((LA2_7 >= 9 && LA2_7 <= 10)) ) {
									alt2=1;
								}

							}

							}
							break;
						case 30:
							{
							int LA2_5 = input.LA(4);
							if ( (LA2_5==9) ) {
								int LA2_8 = input.LA(5);
								if ( ((LA2_8 >= 9 && LA2_8 <= 10)) ) {
									alt2=1;
								}

							}

							}
							break;
						case 11:
						case 12:
							{
							alt2=1;
							}
							break;
						}
					}

				}

				switch (alt2) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:49:3: pol
					{
					pushFollow(FOLLOW_pol_in_program63);
					pol1=pol();
					state._fsp--;

					pols.put(pol1.getPolicyName(), pol1);
					}
					break;

				default :
					break loop2;
				}
			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:50:2: ( 'POLICY_SETS' )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==21) ) {
				alt3=1;
			}
			switch (alt3) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:50:3: 'POLICY_SETS'
					{
					match(input,21,FOLLOW_21_in_program71); 
					}
					break;

			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:51:2: ( pSet )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==IDENT) ) {
					int LA4_2 = input.LA(2);
					if ( (LA4_2==16) ) {
						int LA4_3 = input.LA(3);
						if ( (LA4_3==IDENT) ) {
							int LA4_4 = input.LA(4);
							if ( (LA4_4==IDENT||LA4_4==18) ) {
								alt4=1;
							}

						}
						else if ( ((LA4_3 >= 29 && LA4_3 <= 30)) ) {
							alt4=1;
						}

					}

				}

				switch (alt4) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:51:3: pSet
					{
					pushFollow(FOLLOW_pSet_in_program77);
					pSet();
					state._fsp--;

					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:52:2: ( 'CONDITIONS' )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==18) ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:52:3: 'CONDITIONS'
					{
					match(input,18,FOLLOW_18_in_program83); 
					}
					break;

			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:53:2: (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' )+
			int cnt6=0;
			loop6:
			while (true) {
				int alt6=10;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==IDENT) ) {
					int LA6_2 = input.LA(2);
					if ( (LA6_2==16) ) {
						switch ( input.LA(3) ) {
						case IDENT:
							{
							switch ( input.LA(4) ) {
							case 15:
								{
								int LA6_9 = input.LA(5);
								if ( (LA6_9==NUMBER) ) {
									alt6=1;
								}
								else if ( (LA6_9==IDENT) ) {
									alt6=2;
								}

								}
								break;
							case 14:
								{
								alt6=4;
								}
								break;
							case 8:
								{
								alt6=6;
								}
								break;
							case 33:
								{
								alt6=7;
								}
								break;
							}
							}
							break;
						case NUMBER:
							{
							alt6=3;
							}
							break;
						case 7:
							{
							alt6=5;
							}
							break;
						case 32:
							{
							alt6=8;
							}
							break;
						case 27:
							{
							alt6=9;
							}
							break;
						}
					}

				}

				switch (alt6) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:54:2: id0= IDENT '=' id2= IDENT '<=' num= NUMBER
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program93); 
					match(input,16,FOLLOW_16_in_program95); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program99); 
					match(input,15,FOLLOW_15_in_program101); 
					num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program105); 
					Condition cond = new LessThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Left<BigDecimal,PolicySet>(BigDecimal.valueOf(Double.valueOf((num!=null?num.getText():null))))); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:56:2: id0= IDENT '=' id2= IDENT '<=' id3= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program119); 
					match(input,16,FOLLOW_16_in_program121); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program125); 
					match(input,15,FOLLOW_15_in_program127); 
					id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_program131); 
					Condition cond = new LessThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Right<BigDecimal,PolicySet>(pSets.get((id3!=null?id3.getText():null)))); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:58:2: id0= IDENT '=' num= NUMBER '<' id2= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program145); 
					match(input,16,FOLLOW_16_in_program147); 
					num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program151); 
					match(input,14,FOLLOW_14_in_program153); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program157); 
					Condition cond = new GreaterThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Left<BigDecimal,PolicySet>(BigDecimal.valueOf(Double.valueOf((num!=null?num.getText():null))))); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:60:2: id0= IDENT '=' id3= IDENT '<' id2= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program167); 
					match(input,16,FOLLOW_16_in_program169); 
					id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_program173); 
					match(input,14,FOLLOW_14_in_program175); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program179); 
					Condition cond = new GreaterThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Right<BigDecimal,PolicySet>(pSets.get((id3!=null?id3.getText():null)))); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 5 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:62:2: id0= IDENT '=' '!' id1= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program193); 
					match(input,16,FOLLOW_16_in_program195); 
					match(input,7,FOLLOW_7_in_program197); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program201); 
					Condition cond = new NotCondition((id1!=null?id1.getText():null)); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 6 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:64:2: id0= IDENT '=' id1= IDENT '&&' id2= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program211); 
					match(input,16,FOLLOW_16_in_program213); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program217); 
					match(input,8,FOLLOW_8_in_program219); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program223); 
					Condition cond = new AndCondition((id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 7 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:66:2: id0= IDENT '=' id1= IDENT '||' id2= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program233); 
					match(input,16,FOLLOW_16_in_program235); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program239); 
					match(input,33,FOLLOW_33_in_program241); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program245); 
					Condition cond = new OrCondition((id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 8 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:68:2: id0= IDENT '=' 'true'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program256); 
					match(input,16,FOLLOW_16_in_program258); 
					match(input,32,FOLLOW_32_in_program260); 
					Condition cond = new TrueCondition(); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 9 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:70:2: id0= IDENT '=' 'false'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program271); 
					match(input,16,FOLLOW_16_in_program273); 
					match(input,27,FOLLOW_27_in_program275); 
					Condition cond = new FalseCondition(); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;

				default :
					if ( cnt6 >= 1 ) break loop6;
					EarlyExitException eee = new EarlyExitException(6, input);
					throw eee;
				}
				cnt6++;
			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:2: ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )?
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==19) ) {
				alt8=1;
			}
			switch (alt8) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:3: 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
					{
					match(input,19,FOLLOW_19_in_program285); 
					ignore = true;
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:73:2: ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
					loop7:
					while (true) {
						int alt7=2;
						int LA7_0 = input.LA(1);
						if ( ((LA7_0 >= IDENT && LA7_0 <= NUMBER)||(LA7_0 >= 9 && LA7_0 <= 12)||(LA7_0 >= 14 && LA7_0 <= 16)) ) {
							alt7=1;
						}

						switch (alt7) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:
							{
							if ( (input.LA(1) >= IDENT && input.LA(1) <= NUMBER)||(input.LA(1) >= 9 && input.LA(1) <= 12)||(input.LA(1) >= 14 && input.LA(1) <= 16) ) {
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
							break loop7;
						}
					}

					}
					break;

			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:2: ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )?
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==17) ) {
				alt10=1;
			}
			switch (alt10) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:3: 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+
					{
					match(input,17,FOLLOW_17_in_program332); 
					ignore = false;
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:75:2: (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+
					int cnt9=0;
					loop9:
					while (true) {
						int alt9=7;
						int LA9_0 = input.LA(1);
						if ( (LA9_0==IDENT) ) {
							int LA9_2 = input.LA(2);
							if ( (LA9_2==16) ) {
								switch ( input.LA(3) ) {
								case 23:
									{
									alt9=1;
									}
									break;
								case 22:
									{
									alt9=2;
									}
									break;
								case 31:
									{
									alt9=3;
									}
									break;
								case 26:
									{
									alt9=4;
									}
									break;
								case 25:
									{
									alt9=5;
									}
									break;
								case 28:
									{
									alt9=6;
									}
									break;
								}
							}

						}

						switch (alt9) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:75:3: id0= IDENT '=' 'always_true?' id1= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program340); 
							match(input,16,FOLLOW_16_in_program342); 
							match(input,23,FOLLOW_23_in_program344); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program348); 
							AnalysisGenerator analysis = new AlwaysTrue((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 2 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:76:3: id0= IDENT '=' 'always_false?' id1= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program356); 
							match(input,16,FOLLOW_16_in_program358); 
							match(input,22,FOLLOW_22_in_program360); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program364); 
							AnalysisGenerator analysis = new AlwaysFalse((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 3 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:77:3: id0= IDENT '=' 'satisfiable?' id1= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program372); 
							match(input,16,FOLLOW_16_in_program374); 
							match(input,31,FOLLOW_31_in_program376); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program380); 
							AnalysisGenerator analysis = new Satisfiable((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 4 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:78:3: id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program389); 
							match(input,16,FOLLOW_16_in_program391); 
							match(input,26,FOLLOW_26_in_program393); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program397); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program401); 
							AnalysisGenerator analysis = new Equivalent((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 5 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:79:3: id0= IDENT '=' 'different?' id1= IDENT id2= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program410); 
							match(input,16,FOLLOW_16_in_program412); 
							match(input,25,FOLLOW_25_in_program414); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program418); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program422); 
							AnalysisGenerator analysis = new Different((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 6 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:80:3: id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program432); 
							match(input,16,FOLLOW_16_in_program434); 
							match(input,28,FOLLOW_28_in_program436); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program440); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program444); 
							AnalysisGenerator analysis = new Implies((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;

						default :
							if ( cnt9 >= 1 ) break loop9;
							EarlyExitException eee = new EarlyExitException(9, input);
							throw eee;
						}
						cnt9++;
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:85:1: pSet : (id0= IDENT '=' id1= IDENT |id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' );
	public final void pSet() throws RecognitionException {
		Token id0=null;
		Token id1=null;
		Token id2=null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:86:2: (id0= IDENT '=' id1= IDENT |id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' )
			int alt11=3;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==IDENT) ) {
				int LA11_1 = input.LA(2);
				if ( (LA11_1==16) ) {
					switch ( input.LA(3) ) {
					case IDENT:
						{
						alt11=1;
						}
						break;
					case 29:
						{
						alt11=2;
						}
						break;
					case 30:
						{
						alt11=3;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 11, 2, input);
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
							new NoViableAltException("", 11, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}

			switch (alt11) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:86:3: id0= IDENT '=' id1= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet470); 
					match(input,16,FOLLOW_16_in_pSet472); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet476); 
					PolicySet p = new BasicPolicySet(pols.get((id1!=null?id1.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:87:3: id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet484); 
					match(input,16,FOLLOW_16_in_pSet486); 
					match(input,29,FOLLOW_29_in_pSet488); 
					match(input,9,FOLLOW_9_in_pSet490); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet494); 
					match(input,13,FOLLOW_13_in_pSet496); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet500); 
					match(input,10,FOLLOW_10_in_pSet502); 
					PolicySet p = new MaxPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:88:3: id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet510); 
					match(input,16,FOLLOW_16_in_pSet512); 
					match(input,30,FOLLOW_30_in_pSet514); 
					match(input,9,FOLLOW_9_in_pSet516); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet520); 
					match(input,13,FOLLOW_13_in_pSet522); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet526); 
					match(input,10,FOLLOW_10_in_pSet528); 
					PolicySet p = new MinPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:91:1: pol returns [Pol p] : id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' (n= NUMBER |s= score ) ;
	public final Pol pol() throws RecognitionException {
		Pol p = null;


		Token id1=null;
		Token n=null;
		ParserRuleReturnScope o =null;
		VariableFormula s =null;
		Rule rule2 =null;

		l = new ArrayList<Rule>(); 
		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:93:2: (id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' (n= NUMBER |s= score ) )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:93:4: id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' (n= NUMBER |s= score )
			{
			id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pol553); 
			match(input,16,FOLLOW_16_in_pol555); 
			pushFollow(FOLLOW_operator_in_pol559);
			o=operator();
			state._fsp--;

			match(input,9,FOLLOW_9_in_pol561); 
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:93:33: ( rule )*
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( (LA12_0==9) ) {
					alt12=1;
				}

				switch (alt12) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:93:34: rule
					{
					pushFollow(FOLLOW_rule_in_pol564);
					rule2=rule();
					state._fsp--;

					l.add(rule2);
					}
					break;

				default :
					break loop12;
				}
			}

			match(input,10,FOLLOW_10_in_pol570); 
			match(input,24,FOLLOW_24_in_pol572); 
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:93:73: (n= NUMBER |s= score )
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==NUMBER) ) {
				int LA13_1 = input.LA(2);
				if ( (LA13_1==11) ) {
					alt13=2;
				}
				else if ( (LA13_1==IDENT||LA13_1==21) ) {
					alt13=1;
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
			else if ( (LA13_0==IDENT) ) {
				alt13=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}

			switch (alt13) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:93:74: n= NUMBER
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol577); 
					p = new Pol(l, OperatorResolver.apply((o!=null?input.toString(o.start,o.stop):null)), new Left<BigDecimal,VariableFormula>(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null)))), (id1!=null?id1.getText():null));
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:95:2: s= score
					{
					pushFollow(FOLLOW_score_in_pol594);
					s=score();
					state._fsp--;

					p = new Pol(l, OperatorResolver.apply((o!=null?input.toString(o.start,o.stop):null)), new Right<BigDecimal,VariableFormula>(s), (id1!=null?id1.getText():null));
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
		return p;
	}
	// $ANTLR end "pol"



	// $ANTLR start "rule"
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:104:1: rule returns [Rule r] : ( '(' IDENT NUMBER ')' | '(' id0= IDENT s= score ')' );
	public final Rule rule() throws RecognitionException {
		Rule r = null;


		Token id0=null;
		Token IDENT3=null;
		Token NUMBER4=null;
		VariableFormula s =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:105:2: ( '(' IDENT NUMBER ')' | '(' id0= IDENT s= score ')' )
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==9) ) {
				int LA14_1 = input.LA(2);
				if ( (LA14_1==IDENT) ) {
					int LA14_2 = input.LA(3);
					if ( (LA14_2==NUMBER) ) {
						int LA14_3 = input.LA(4);
						if ( (LA14_3==10) ) {
							alt14=1;
						}
						else if ( (LA14_3==11) ) {
							alt14=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 14, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}
					else if ( (LA14_2==IDENT) ) {
						alt14=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 14, 2, input);
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
							new NoViableAltException("", 14, 1, input);
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
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:106:2: '(' IDENT NUMBER ')'
					{
					match(input,9,FOLLOW_9_in_rule638); 
					IDENT3=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule640); 
					NUMBER4=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule642); 
					match(input,10,FOLLOW_10_in_rule644); 
					r = new Rule(new Predicate((IDENT3!=null?IDENT3.getText():null)),new Left<BigDecimal,VariableFormula>(BigDecimal.valueOf(Double.valueOf((NUMBER4!=null?NUMBER4.getText():null)))));
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:109:2: '(' id0= IDENT s= score ')'
					{
					match(input,9,FOLLOW_9_in_rule654); 
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule658); 
					pushFollow(FOLLOW_score_in_rule662);
					s=score();
					state._fsp--;

					match(input,10,FOLLOW_10_in_rule663); 
					r = new Rule(new Predicate((id0!=null?id0.getText():null)),new Right<BigDecimal,VariableFormula>(s));
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
		return r;
	}
	// $ANTLR end "rule"



	// $ANTLR start "score"
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:113:1: score returns [VariableFormula s] : m0= vmult ( '+' m= mult )* ;
	public final VariableFormula score() throws RecognitionException {
		VariableFormula s = null;


		Multiplier m0 =null;
		Multiplier m =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:114:2: (m0= vmult ( '+' m= mult )* )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:114:4: m0= vmult ( '+' m= mult )*
			{
			pushFollow(FOLLOW_vmult_in_score684);
			m0=vmult();
			state._fsp--;

			s = new VariableFormula().add(m0);
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:114:54: ( '+' m= mult )*
			loop15:
			while (true) {
				int alt15=2;
				int LA15_0 = input.LA(1);
				if ( (LA15_0==12) ) {
					alt15=1;
				}

				switch (alt15) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:114:55: '+' m= mult
					{
					match(input,12,FOLLOW_12_in_score689); 
					pushFollow(FOLLOW_mult_in_score693);
					m=mult();
					state._fsp--;

					s.add(m);
					}
					break;

				default :
					break loop15;
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
	// $ANTLR end "score"



	// $ANTLR start "vmult"
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:117:1: vmult returns [Multiplier m] : (id1= IDENT '*' n= NUMBER |n= NUMBER '*' id1= IDENT |id1= IDENT );
	public final Multiplier vmult() throws RecognitionException {
		Multiplier m = null;


		Token id1=null;
		Token n=null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:118:2: (id1= IDENT '*' n= NUMBER |n= NUMBER '*' id1= IDENT |id1= IDENT )
			int alt16=3;
			int LA16_0 = input.LA(1);
			if ( (LA16_0==IDENT) ) {
				int LA16_1 = input.LA(2);
				if ( (LA16_1==11) ) {
					alt16=1;
				}
				else if ( (LA16_1==IDENT||LA16_1==10||LA16_1==12||LA16_1==21) ) {
					alt16=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 16, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA16_0==NUMBER) ) {
				alt16=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 16, 0, input);
				throw nvae;
			}

			switch (alt16) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:118:4: id1= IDENT '*' n= NUMBER
					{
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_vmult717); 
					match(input,11,FOLLOW_11_in_vmult719); 
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_vmult723); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null));
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:119:4: n= NUMBER '*' id1= IDENT
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_vmult732); 
					match(input,11,FOLLOW_11_in_vmult734); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_vmult738); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null));
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:121:4: id1= IDENT
					{
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_vmult748); 
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:124:1: mult returns [Multiplier m] : (id1= IDENT '*' n= NUMBER |n= NUMBER '*' id1= IDENT |n= NUMBER |id1= IDENT );
	public final Multiplier mult() throws RecognitionException {
		Multiplier m = null;


		Token id1=null;
		Token n=null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:125:2: (id1= IDENT '*' n= NUMBER |n= NUMBER '*' id1= IDENT |n= NUMBER |id1= IDENT )
			int alt17=4;
			int LA17_0 = input.LA(1);
			if ( (LA17_0==IDENT) ) {
				int LA17_1 = input.LA(2);
				if ( (LA17_1==11) ) {
					alt17=1;
				}
				else if ( (LA17_1==IDENT||LA17_1==10||LA17_1==12||LA17_1==21) ) {
					alt17=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 17, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA17_0==NUMBER) ) {
				int LA17_2 = input.LA(2);
				if ( (LA17_2==11) ) {
					alt17=2;
				}
				else if ( (LA17_2==IDENT||LA17_2==10||LA17_2==12||LA17_2==21) ) {
					alt17=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 17, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}

			switch (alt17) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:125:4: id1= IDENT '*' n= NUMBER
					{
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_mult767); 
					match(input,11,FOLLOW_11_in_mult769); 
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_mult773); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null));
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:126:4: n= NUMBER '*' id1= IDENT
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_mult782); 
					match(input,11,FOLLOW_11_in_mult784); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_mult788); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null));
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:127:4: n= NUMBER
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_mult797); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), "");
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:128:4: id1= IDENT
					{
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_mult806); 
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:131:1: operator : ( 'max' | 'min' | '+' | '*' );
	public final PealProgramParser.operator_return operator() throws RecognitionException {
		PealProgramParser.operator_return retval = new PealProgramParser.operator_return();
		retval.start = input.LT(1);

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:131:10: ( 'max' | 'min' | '+' | '*' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:
			{
			if ( (input.LA(1) >= 11 && input.LA(1) <= 12)||(input.LA(1) >= 29 && input.LA(1) <= 30) ) {
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



	public static final BitSet FOLLOW_20_in_program57 = new BitSet(new long[]{0x0000000000200010L});
	public static final BitSet FOLLOW_pol_in_program63 = new BitSet(new long[]{0x0000000000200010L});
	public static final BitSet FOLLOW_21_in_program71 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_pSet_in_program77 = new BitSet(new long[]{0x0000000000040010L});
	public static final BitSet FOLLOW_18_in_program83 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program93 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program95 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program99 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_program101 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_program105 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_IDENT_in_program119 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program121 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program125 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_program127 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program131 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_IDENT_in_program145 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program147 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_program151 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_program153 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program157 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_IDENT_in_program167 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program169 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program173 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_program175 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program179 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_IDENT_in_program193 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program195 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_7_in_program197 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program201 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_IDENT_in_program211 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program213 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program217 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_8_in_program219 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program223 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_IDENT_in_program233 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program235 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program239 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_33_in_program241 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program245 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_IDENT_in_program256 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program258 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_32_in_program260 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_IDENT_in_program271 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program273 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_program275 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_19_in_program285 = new BitSet(new long[]{0x000000000003DE32L});
	public static final BitSet FOLLOW_17_in_program332 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program340 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program342 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_program344 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program348 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program356 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program358 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_program360 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program364 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program372 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program374 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_31_in_program376 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program380 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program389 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program391 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_26_in_program393 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program397 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program401 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program410 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program412 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_25_in_program414 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program418 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program422 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program432 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program434 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_28_in_program436 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program440 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program444 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_pSet470 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_pSet472 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet476 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pSet484 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_pSet486 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_29_in_pSet488 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pSet490 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet494 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_pSet496 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet500 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_pSet502 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pSet510 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_pSet512 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_30_in_pSet514 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pSet516 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet520 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_pSet522 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet526 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_pSet528 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pol553 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_pol555 = new BitSet(new long[]{0x0000000060001800L});
	public static final BitSet FOLLOW_operator_in_pol559 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pol561 = new BitSet(new long[]{0x0000000000000600L});
	public static final BitSet FOLLOW_rule_in_pol564 = new BitSet(new long[]{0x0000000000000600L});
	public static final BitSet FOLLOW_10_in_pol570 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_pol572 = new BitSet(new long[]{0x0000000000000030L});
	public static final BitSet FOLLOW_NUMBER_in_pol577 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_score_in_pol594 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_rule638 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_rule640 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_rule642 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_rule644 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_rule654 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_rule658 = new BitSet(new long[]{0x0000000000000030L});
	public static final BitSet FOLLOW_score_in_rule662 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_rule663 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_vmult_in_score684 = new BitSet(new long[]{0x0000000000001002L});
	public static final BitSet FOLLOW_12_in_score689 = new BitSet(new long[]{0x0000000000000030L});
	public static final BitSet FOLLOW_mult_in_score693 = new BitSet(new long[]{0x0000000000001002L});
	public static final BitSet FOLLOW_IDENT_in_vmult717 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_vmult719 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_vmult723 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_vmult732 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_vmult734 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_vmult738 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_vmult748 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_mult767 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_mult769 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_mult773 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_mult782 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_mult784 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_mult788 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_mult797 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_mult806 = new BitSet(new long[]{0x0000000000000002L});
}
