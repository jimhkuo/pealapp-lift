// $ANTLR 3.5.1 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2014-02-03 10:49:51

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
		"'CONDITIONS'", "'DOMAIN_SPECIFICS'", "'POLICIES'", "'POLICY_SETS'", "'['", 
		"']'", "'always_false?'", "'always_true?'", "'default'", "'different?'", 
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:46:1: program : ( 'POLICIES' ) ( pol )* ( 'POLICY_SETS' ) ( pSet )+ ( 'CONDITIONS' ) (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' |id0= IDENT '=' id1= IDENT )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )? ;
	public final void program() throws RecognitionException {
		Token id0=null;
		Token id2=null;
		Token num=null;
		Token id3=null;
		Token id1=null;
		Pol pol1 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:2: ( ( 'POLICIES' ) ( pol )* ( 'POLICY_SETS' ) ( pSet )+ ( 'CONDITIONS' ) (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' |id0= IDENT '=' id1= IDENT )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )? )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:2: ( 'POLICIES' ) ( pol )* ( 'POLICY_SETS' ) ( pSet )+ ( 'CONDITIONS' ) (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' |id0= IDENT '=' id1= IDENT )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )?
			{
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:2: ( 'POLICIES' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:3: 'POLICIES'
			{
			match(input,20,FOLLOW_20_in_program57); 
			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:49:2: ( pol )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==IDENT) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:49:3: pol
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

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:50:2: ( 'POLICY_SETS' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:50:3: 'POLICY_SETS'
			{
			match(input,21,FOLLOW_21_in_program70); 
			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:51:2: ( pSet )+
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
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:51:3: pSet
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

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:52:2: ( 'CONDITIONS' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:52:3: 'CONDITIONS'
			{
			match(input,18,FOLLOW_18_in_program81); 
			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:53:2: (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' |id0= IDENT '=' id1= IDENT )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=11;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==IDENT) ) {
					int LA3_2 = input.LA(2);
					if ( (LA3_2==16) ) {
						switch ( input.LA(3) ) {
						case IDENT:
							{
							switch ( input.LA(4) ) {
							case 15:
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
							case 14:
								{
								alt3=4;
								}
								break;
							case 8:
								{
								alt3=6;
								}
								break;
							case 35:
								{
								alt3=7;
								}
								break;
							case EOF:
							case IDENT:
							case 17:
							case 19:
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
						case 34:
							{
							alt3=8;
							}
							break;
						case 29:
							{
							alt3=9;
							}
							break;
						}
					}

				}

				switch (alt3) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:54:2: id0= IDENT '=' id2= IDENT '<=' num= NUMBER
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program90); 
					match(input,16,FOLLOW_16_in_program92); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program96); 
					match(input,15,FOLLOW_15_in_program98); 
					num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program102); 
					Condition cond = new LessThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Left<BigDecimal,PolicySet>(BigDecimal.valueOf(Double.valueOf((num!=null?num.getText():null))))); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:56:2: id0= IDENT '=' id2= IDENT '<=' id3= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program116); 
					match(input,16,FOLLOW_16_in_program118); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program122); 
					match(input,15,FOLLOW_15_in_program124); 
					id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_program128); 
					Condition cond = new LessThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Right<BigDecimal,PolicySet>(pSets.get((id3!=null?id3.getText():null)))); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:58:2: id0= IDENT '=' num= NUMBER '<' id2= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program142); 
					match(input,16,FOLLOW_16_in_program144); 
					num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program148); 
					match(input,14,FOLLOW_14_in_program150); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program154); 
					Condition cond = new GreaterThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Left<BigDecimal,PolicySet>(BigDecimal.valueOf(Double.valueOf((num!=null?num.getText():null))))); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:60:2: id0= IDENT '=' id3= IDENT '<' id2= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program164); 
					match(input,16,FOLLOW_16_in_program166); 
					id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_program170); 
					match(input,14,FOLLOW_14_in_program172); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program176); 
					Condition cond = new GreaterThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Right<BigDecimal,PolicySet>(pSets.get((id3!=null?id3.getText():null)))); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 5 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:62:2: id0= IDENT '=' '!' id1= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program190); 
					match(input,16,FOLLOW_16_in_program192); 
					match(input,7,FOLLOW_7_in_program194); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program198); 
					Condition cond = new NotCondition((id1!=null?id1.getText():null)); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 6 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:64:2: id0= IDENT '=' id1= IDENT '&&' id2= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program208); 
					match(input,16,FOLLOW_16_in_program210); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program214); 
					match(input,8,FOLLOW_8_in_program216); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program220); 
					Condition cond = new AndCondition((id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 7 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:66:2: id0= IDENT '=' id1= IDENT '||' id2= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program230); 
					match(input,16,FOLLOW_16_in_program232); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program236); 
					match(input,35,FOLLOW_35_in_program238); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program242); 
					Condition cond = new OrCondition((id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 8 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:68:2: id0= IDENT '=' 'true'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program253); 
					match(input,16,FOLLOW_16_in_program255); 
					match(input,34,FOLLOW_34_in_program257); 
					Condition cond = new TrueCondition(); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 9 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:70:2: id0= IDENT '=' 'false'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program268); 
					match(input,16,FOLLOW_16_in_program270); 
					match(input,29,FOLLOW_29_in_program272); 
					Condition cond = new FalseCondition(); conds.put((id0!=null?id0.getText():null), cond);
					}
					break;
				case 10 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:2: id0= IDENT '=' id1= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program283); 
					match(input,16,FOLLOW_16_in_program285); 
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

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:2: ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==19) ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:3: 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
					{
					match(input,19,FOLLOW_19_in_program299); 
					ignore = true;
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:75:2: ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( ((LA4_0 >= IDENT && LA4_0 <= NUMBER)||(LA4_0 >= 9 && LA4_0 <= 12)||(LA4_0 >= 14 && LA4_0 <= 16)) ) {
							alt4=1;
						}

						switch (alt4) {
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
							break loop4;
						}
					}

					}
					break;

			}

			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:76:2: ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==17) ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:76:3: 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+
					{
					match(input,17,FOLLOW_17_in_program346); 
					ignore = false;
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:77:2: (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+
					int cnt6=0;
					loop6:
					while (true) {
						int alt6=7;
						int LA6_0 = input.LA(1);
						if ( (LA6_0==IDENT) ) {
							int LA6_2 = input.LA(2);
							if ( (LA6_2==16) ) {
								switch ( input.LA(3) ) {
								case 25:
									{
									alt6=1;
									}
									break;
								case 24:
									{
									alt6=2;
									}
									break;
								case 33:
									{
									alt6=3;
									}
									break;
								case 28:
									{
									alt6=4;
									}
									break;
								case 27:
									{
									alt6=5;
									}
									break;
								case 30:
									{
									alt6=6;
									}
									break;
								}
							}

						}

						switch (alt6) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:77:3: id0= IDENT '=' 'always_true?' id1= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program354); 
							match(input,16,FOLLOW_16_in_program356); 
							match(input,25,FOLLOW_25_in_program358); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program362); 
							AnalysisGenerator analysis = new AlwaysTrue((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 2 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:78:3: id0= IDENT '=' 'always_false?' id1= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program370); 
							match(input,16,FOLLOW_16_in_program372); 
							match(input,24,FOLLOW_24_in_program374); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program378); 
							AnalysisGenerator analysis = new AlwaysFalse((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 3 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:79:3: id0= IDENT '=' 'satisfiable?' id1= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program386); 
							match(input,16,FOLLOW_16_in_program388); 
							match(input,33,FOLLOW_33_in_program390); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program394); 
							AnalysisGenerator analysis = new Satisfiable((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 4 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:80:3: id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program403); 
							match(input,16,FOLLOW_16_in_program405); 
							match(input,28,FOLLOW_28_in_program407); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program411); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program415); 
							AnalysisGenerator analysis = new Equivalent((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 5 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:81:3: id0= IDENT '=' 'different?' id1= IDENT id2= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program424); 
							match(input,16,FOLLOW_16_in_program426); 
							match(input,27,FOLLOW_27_in_program428); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program432); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program436); 
							AnalysisGenerator analysis = new Different((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);
							}
							break;
						case 6 :
							// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:82:3: id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT
							{
							id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program446); 
							match(input,16,FOLLOW_16_in_program448); 
							match(input,30,FOLLOW_30_in_program450); 
							id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program454); 
							id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program458); 
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:87:1: pSet : (id0= IDENT '=' id1= IDENT |id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' '+' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' '*' '(' id1= IDENT ',' id2= IDENT ')' );
	public final void pSet() throws RecognitionException {
		Token id0=null;
		Token id1=null;
		Token id2=null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:88:2: (id0= IDENT '=' id1= IDENT |id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' '+' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' '*' '(' id1= IDENT ',' id2= IDENT ')' )
			int alt8=5;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==IDENT) ) {
				int LA8_1 = input.LA(2);
				if ( (LA8_1==16) ) {
					switch ( input.LA(3) ) {
					case IDENT:
						{
						alt8=1;
						}
						break;
					case 31:
						{
						alt8=2;
						}
						break;
					case 32:
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
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:88:3: id0= IDENT '=' id1= IDENT
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet484); 
					match(input,16,FOLLOW_16_in_pSet486); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet490); 
					PolicySet p = new BasicPolicySet(pols.get((id1!=null?id1.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:89:3: id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet498); 
					match(input,16,FOLLOW_16_in_pSet500); 
					match(input,31,FOLLOW_31_in_pSet502); 
					match(input,9,FOLLOW_9_in_pSet504); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet508); 
					match(input,13,FOLLOW_13_in_pSet510); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet514); 
					match(input,10,FOLLOW_10_in_pSet516); 
					PolicySet p = new MaxPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:90:3: id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet524); 
					match(input,16,FOLLOW_16_in_pSet526); 
					match(input,32,FOLLOW_32_in_pSet528); 
					match(input,9,FOLLOW_9_in_pSet530); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet534); 
					match(input,13,FOLLOW_13_in_pSet536); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet540); 
					match(input,10,FOLLOW_10_in_pSet542); 
					PolicySet p = new MinPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:91:3: id0= IDENT '=' '+' '(' id1= IDENT ',' id2= IDENT ')'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet550); 
					match(input,16,FOLLOW_16_in_pSet552); 
					match(input,12,FOLLOW_12_in_pSet554); 
					match(input,9,FOLLOW_9_in_pSet556); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet560); 
					match(input,13,FOLLOW_13_in_pSet562); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet566); 
					match(input,10,FOLLOW_10_in_pSet568); 
					PolicySet p = new PlusPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);
					}
					break;
				case 5 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:92:3: id0= IDENT '=' '*' '(' id1= IDENT ',' id2= IDENT ')'
					{
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet576); 
					match(input,16,FOLLOW_16_in_pSet578); 
					match(input,11,FOLLOW_11_in_pSet580); 
					match(input,9,FOLLOW_9_in_pSet582); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet586); 
					match(input,13,FOLLOW_13_in_pSet588); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet592); 
					match(input,10,FOLLOW_10_in_pSet594); 
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:95:1: pol returns [Pol p] : id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' (n= NUMBER |s= raw_score ) ;
	public final Pol pol() throws RecognitionException {
		Pol p = null;


		Token id1=null;
		Token n=null;
		ParserRuleReturnScope o =null;
		VariableFormula s =null;
		Rule rule2 =null;

		l = new ArrayList<Rule>(); 
		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:97:2: (id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' (n= NUMBER |s= raw_score ) )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:97:4: id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' (n= NUMBER |s= raw_score )
			{
			id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pol619); 
			match(input,16,FOLLOW_16_in_pol621); 
			pushFollow(FOLLOW_operator_in_pol625);
			o=operator();
			state._fsp--;

			match(input,9,FOLLOW_9_in_pol627); 
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:97:33: ( rule )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0==9) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:97:34: rule
					{
					pushFollow(FOLLOW_rule_in_pol630);
					rule2=rule();
					state._fsp--;

					l.add(rule2);
					}
					break;

				default :
					break loop9;
				}
			}

			match(input,10,FOLLOW_10_in_pol636); 
			match(input,26,FOLLOW_26_in_pol638); 
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:98:2: (n= NUMBER |s= raw_score )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==NUMBER) ) {
				int LA10_1 = input.LA(2);
				if ( (LA10_1==11) ) {
					alt10=2;
				}
				else if ( (LA10_1==IDENT||LA10_1==21) ) {
					alt10=1;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 10, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA10_0==IDENT) ) {
				alt10=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:99:3: n= NUMBER
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol648); 
					p = new Pol(l, OperatorResolver.apply((o!=null?input.toString(o.start,o.stop):null)), new Left<BigDecimal,VariableFormula>(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null)))), (id1!=null?id1.getText():null));
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:101:3: s= raw_score
					{
					pushFollow(FOLLOW_raw_score_in_pol660);
					s=raw_score();
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:105:1: rule returns [Rule r] : '(' id0= IDENT s= score ')' ;
	public final Rule rule() throws RecognitionException {
		Rule r = null;


		Token id0=null;
		Score s =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:106:2: ( '(' id0= IDENT s= score ')' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:106:4: '(' id0= IDENT s= score ')'
			{
			match(input,9,FOLLOW_9_in_rule690); 
			id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule694); 
			pushFollow(FOLLOW_score_in_rule698);
			s=score();
			state._fsp--;

			match(input,10,FOLLOW_10_in_rule699); 
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:109:1: score returns [Score s] : (r= raw_score |r= raw_score '[' n1= NUMBER ',' n2= NUMBER ']' |n= NUMBER |n= NUMBER '[' n1= NUMBER ',' n2= NUMBER ']' );
	public final Score score() throws RecognitionException {
		Score s = null;


		Token n1=null;
		Token n2=null;
		Token n=null;
		VariableFormula r =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:110:2: (r= raw_score |r= raw_score '[' n1= NUMBER ',' n2= NUMBER ']' |n= NUMBER |n= NUMBER '[' n1= NUMBER ',' n2= NUMBER ']' )
			int alt11=4;
			alt11 = dfa11.predict(input);
			switch (alt11) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:110:4: r= raw_score
					{
					pushFollow(FOLLOW_raw_score_in_score720);
					r=raw_score();
					state._fsp--;

					 s = new Score(new Right<BigDecimal,VariableFormula>(r), scala.Option.apply((ScoreRange) null));
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:111:4: r= raw_score '[' n1= NUMBER ',' n2= NUMBER ']'
					{
					pushFollow(FOLLOW_raw_score_in_score729);
					r=raw_score();
					state._fsp--;

					match(input,22,FOLLOW_22_in_score731); 
					n1=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score734); 
					match(input,13,FOLLOW_13_in_score736); 
					n2=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score740); 
					match(input,23,FOLLOW_23_in_score742); 
					 s = new Score(new Right<BigDecimal,VariableFormula>(r), scala.Option.apply(new ScoreRange(BigDecimal.valueOf(Double.valueOf((n1!=null?n1.getText():null))), BigDecimal.valueOf(Double.valueOf((n2!=null?n2.getText():null))))));
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:112:4: n= NUMBER
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score751); 
					s = new Score(new Left<BigDecimal,VariableFormula>(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null)))), scala.Option.apply((ScoreRange) null));
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:113:4: n= NUMBER '[' n1= NUMBER ',' n2= NUMBER ']'
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score760); 
					match(input,22,FOLLOW_22_in_score762); 
					n1=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score765); 
					match(input,13,FOLLOW_13_in_score767); 
					n2=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_score771); 
					match(input,23,FOLLOW_23_in_score773); 
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:116:1: raw_score returns [VariableFormula s] : m0= vmult ( '+' m= mult )* ;
	public final VariableFormula raw_score() throws RecognitionException {
		VariableFormula s = null;


		Multiplier m0 =null;
		Multiplier m =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:117:2: (m0= vmult ( '+' m= mult )* )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:117:4: m0= vmult ( '+' m= mult )*
			{
			pushFollow(FOLLOW_vmult_in_raw_score793);
			m0=vmult();
			state._fsp--;

			s = new VariableFormula().add(m0);
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:117:54: ( '+' m= mult )*
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( (LA12_0==12) ) {
					alt12=1;
				}

				switch (alt12) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:117:55: '+' m= mult
					{
					match(input,12,FOLLOW_12_in_raw_score798); 
					pushFollow(FOLLOW_mult_in_raw_score802);
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:120:1: vmult returns [Multiplier m] : (id1= IDENT '*' n= NUMBER |n= NUMBER '*' id1= IDENT |id1= IDENT );
	public final Multiplier vmult() throws RecognitionException {
		Multiplier m = null;


		Token id1=null;
		Token n=null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:121:2: (id1= IDENT '*' n= NUMBER |n= NUMBER '*' id1= IDENT |id1= IDENT )
			int alt13=3;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==IDENT) ) {
				int LA13_1 = input.LA(2);
				if ( (LA13_1==11) ) {
					alt13=1;
				}
				else if ( (LA13_1==IDENT||LA13_1==10||LA13_1==12||(LA13_1 >= 21 && LA13_1 <= 22)) ) {
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
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:121:4: id1= IDENT '*' n= NUMBER
					{
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_vmult826); 
					match(input,11,FOLLOW_11_in_vmult828); 
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_vmult832); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null));
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:122:4: n= NUMBER '*' id1= IDENT
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_vmult841); 
					match(input,11,FOLLOW_11_in_vmult843); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_vmult847); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null));
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:123:4: id1= IDENT
					{
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_vmult856); 
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:126:1: mult returns [Multiplier m] : (id1= IDENT '*' n= NUMBER |n= NUMBER '*' id1= IDENT |n= NUMBER |id1= IDENT );
	public final Multiplier mult() throws RecognitionException {
		Multiplier m = null;


		Token id1=null;
		Token n=null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:127:2: (id1= IDENT '*' n= NUMBER |n= NUMBER '*' id1= IDENT |n= NUMBER |id1= IDENT )
			int alt14=4;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==IDENT) ) {
				int LA14_1 = input.LA(2);
				if ( (LA14_1==11) ) {
					alt14=1;
				}
				else if ( (LA14_1==IDENT||LA14_1==10||LA14_1==12||(LA14_1 >= 21 && LA14_1 <= 22)) ) {
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
				else if ( (LA14_2==IDENT||LA14_2==10||LA14_2==12||(LA14_2 >= 21 && LA14_2 <= 22)) ) {
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
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:127:4: id1= IDENT '*' n= NUMBER
					{
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_mult875); 
					match(input,11,FOLLOW_11_in_mult877); 
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_mult881); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null));
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:128:4: n= NUMBER '*' id1= IDENT
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_mult890); 
					match(input,11,FOLLOW_11_in_mult892); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_mult896); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null));
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:129:4: n= NUMBER
					{
					n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_mult905); 
					m = new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), "");
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:130:4: id1= IDENT
					{
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_mult914); 
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
	// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:133:1: operator : ( 'max' | 'min' | '+' | '*' );
	public final PealProgramParser.operator_return operator() throws RecognitionException {
		PealProgramParser.operator_return retval = new PealProgramParser.operator_return();
		retval.start = input.LT(1);

		try {
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:133:10: ( 'max' | 'min' | '+' | '*' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:
			{
			if ( (input.LA(1) >= 11 && input.LA(1) <= 12)||(input.LA(1) >= 31 && input.LA(1) <= 32) ) {
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
		"\1\4\2\12\1\5\1\4\2\uffff\1\4\2\uffff\4\12\1\5\1\4\2\12";
	static final String DFA11_maxS =
		"\1\5\2\26\2\5\2\uffff\1\4\2\uffff\4\26\1\5\1\4\2\26";
	static final String DFA11_acceptS =
		"\5\uffff\1\1\1\2\1\uffff\1\4\1\3\10\uffff";
	static final String DFA11_specialS =
		"\22\uffff}>";
	static final String[] DFA11_transitionS = {
			"\1\1\1\2",
			"\1\5\1\3\1\4\11\uffff\1\6",
			"\1\11\1\7\12\uffff\1\10",
			"\1\12",
			"\1\13\1\14",
			"",
			"",
			"\1\15",
			"",
			"",
			"\1\5\1\uffff\1\4\11\uffff\1\6",
			"\1\5\1\16\1\4\11\uffff\1\6",
			"\1\5\1\17\1\4\11\uffff\1\6",
			"\1\5\1\uffff\1\4\11\uffff\1\6",
			"\1\20",
			"\1\21",
			"\1\5\1\uffff\1\4\11\uffff\1\6",
			"\1\5\1\uffff\1\4\11\uffff\1\6"
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
			return "109:1: score returns [Score s] : (r= raw_score |r= raw_score '[' n1= NUMBER ',' n2= NUMBER ']' |n= NUMBER |n= NUMBER '[' n1= NUMBER ',' n2= NUMBER ']' );";
		}
	}

	public static final BitSet FOLLOW_20_in_program57 = new BitSet(new long[]{0x0000000000200010L});
	public static final BitSet FOLLOW_pol_in_program62 = new BitSet(new long[]{0x0000000000200010L});
	public static final BitSet FOLLOW_21_in_program70 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_pSet_in_program75 = new BitSet(new long[]{0x0000000000040010L});
	public static final BitSet FOLLOW_18_in_program81 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program90 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program92 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program96 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_program98 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_program102 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_IDENT_in_program116 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program118 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program122 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_program124 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program128 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_IDENT_in_program142 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program144 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_program148 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_program150 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program154 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_IDENT_in_program164 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program166 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program170 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_program172 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program176 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_IDENT_in_program190 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program192 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_7_in_program194 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program198 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_IDENT_in_program208 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program210 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program214 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_8_in_program216 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program220 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_IDENT_in_program230 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program232 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program236 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_35_in_program238 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program242 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_IDENT_in_program253 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program255 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_34_in_program257 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_IDENT_in_program268 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program270 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_29_in_program272 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_IDENT_in_program283 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program285 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program289 = new BitSet(new long[]{0x00000000000A0012L});
	public static final BitSet FOLLOW_19_in_program299 = new BitSet(new long[]{0x000000000003DE32L});
	public static final BitSet FOLLOW_17_in_program346 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program354 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program356 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_25_in_program358 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program362 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program370 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program372 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_program374 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program378 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program386 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program388 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_33_in_program390 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program394 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program403 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program405 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_28_in_program407 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program411 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program415 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program424 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program426 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_program428 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program432 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program436 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_program446 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_program448 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_30_in_program450 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program454 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_program458 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_IDENT_in_pSet484 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_pSet486 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet490 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pSet498 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_pSet500 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_31_in_pSet502 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pSet504 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet508 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_pSet510 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet514 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_pSet516 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pSet524 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_pSet526 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_32_in_pSet528 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pSet530 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet534 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_pSet536 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet540 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_pSet542 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pSet550 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_pSet552 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_12_in_pSet554 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pSet556 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet560 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_pSet562 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet566 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_pSet568 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pSet576 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_pSet578 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_pSet580 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pSet582 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet586 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_pSet588 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_pSet592 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_pSet594 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_pol619 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_pol621 = new BitSet(new long[]{0x0000000180001800L});
	public static final BitSet FOLLOW_operator_in_pol625 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_pol627 = new BitSet(new long[]{0x0000000000000600L});
	public static final BitSet FOLLOW_rule_in_pol630 = new BitSet(new long[]{0x0000000000000600L});
	public static final BitSet FOLLOW_10_in_pol636 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_26_in_pol638 = new BitSet(new long[]{0x0000000000000030L});
	public static final BitSet FOLLOW_NUMBER_in_pol648 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_raw_score_in_pol660 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_rule690 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_rule694 = new BitSet(new long[]{0x0000000000000030L});
	public static final BitSet FOLLOW_score_in_rule698 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_rule699 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_raw_score_in_score720 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_raw_score_in_score729 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_score731 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_score734 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_score736 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_score740 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_score742 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_score751 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_score760 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_score762 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_score765 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_score767 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_score771 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_score773 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_vmult_in_raw_score793 = new BitSet(new long[]{0x0000000000001002L});
	public static final BitSet FOLLOW_12_in_raw_score798 = new BitSet(new long[]{0x0000000000000030L});
	public static final BitSet FOLLOW_mult_in_raw_score802 = new BitSet(new long[]{0x0000000000001002L});
	public static final BitSet FOLLOW_IDENT_in_vmult826 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_vmult828 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_vmult832 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_vmult841 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_vmult843 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_vmult847 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_vmult856 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_mult875 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_mult877 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_mult881 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_mult890 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_mult892 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_mult896 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_mult905 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_mult914 = new BitSet(new long[]{0x0000000000000002L});
}
