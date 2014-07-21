// $ANTLR 3.5.1 /Users/jkuo/PealApp-lift/antlr/Z3Output.g 2014-07-21 10:33:49

package peal.antlr;

import org.antlr.runtime.*;
import peal.domain.z3.Assignment;
import peal.domain.z3.Model;
import peal.domain.z3.Sat$;
import peal.domain.z3.Unsat$;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class Z3OutputParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENT", "NUMBER", "UNSATMESSAGE", 
		"WS", "Z3ERROR", "'('", "'(declare-fun'", "'(define-fun'", "'(forall'", 
		"'(ite'", "'(model'", "')'", "'*'", "'+'", "'-'", "'/'", "'<'", "'<='", 
		"'='", "'?'", "'Result of analysis ['", "'Result of vacuity check ['", 
		"']:'", "'sat'", "'unsat'"
	};
	public static final int EOF=-1;
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
	public static final int IDENT=4;
	public static final int NUMBER=5;
	public static final int UNSATMESSAGE=6;
	public static final int WS=7;
	public static final int Z3ERROR=8;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public Z3OutputParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public Z3OutputParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return Z3OutputParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/Users/jkuo/PealApp-lift/antlr/Z3Output.g"; }


	private boolean ignore = false;

	@Override
	public void reportError(RecognitionException e) {
		throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
	}




	// $ANTLR start "results"
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:40:1: results returns [Map<String, Model> r] : ( ( Z3ERROR )+ )? ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) | 'Result of vacuity check [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+ ;
	public final Map<String, Model> results() throws RecognitionException {
		Map<String, Model> r = null;


		Token id0=null;
		Token id1=null;
		Token id2=null;
		Model m =null;

		r = new HashMap<String, Model>();
		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:42:2: ( ( ( Z3ERROR )+ )? ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) | 'Result of vacuity check [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+ )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:42:3: ( ( Z3ERROR )+ )? ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) | 'Result of vacuity check [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+
			{
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:42:3: ( ( Z3ERROR )+ )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==Z3ERROR) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:42:4: ( Z3ERROR )+
					{
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:42:4: ( Z3ERROR )+
					int cnt1=0;
					loop1:
					while (true) {
						int alt1=2;
						int LA1_0 = input.LA(1);
						if ( (LA1_0==Z3ERROR) ) {
							alt1=1;
						}

						switch (alt1) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:42:5: Z3ERROR
							{
							match(input,Z3ERROR,FOLLOW_Z3ERROR_in_results63); 
							}
							break;

						default :
							if ( cnt1 >= 1 ) break loop1;
							EarlyExitException eee = new EarlyExitException(1, input);
							throw eee;
						}
						cnt1++;
					}

					}
					break;

			}

			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:43:2: ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) | 'Result of vacuity check [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+
			int cnt5=0;
			loop5:
			while (true) {
				int alt5=3;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==24) ) {
					alt5=1;
				}
				else if ( (LA5_0==25) ) {
					alt5=2;
				}

				switch (alt5) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:44:3: 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model )
					{
					match(input,24,FOLLOW_24_in_results75); 
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_results79); 
					match(input,22,FOLLOW_22_in_results81); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_results85); 
					match(input,23,FOLLOW_23_in_results87); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_results91); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:44:64: ( IDENT )?
					int alt3=2;
					int LA3_0 = input.LA(1);
					if ( (LA3_0==IDENT) ) {
						alt3=1;
					}
					switch (alt3) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:44:65: IDENT
							{
							match(input,IDENT,FOLLOW_IDENT_in_results94); 
							}
							break;

					}

					match(input,26,FOLLOW_26_in_results98); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:44:78: (m= model )
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:44:79: m= model
					{
					pushFollow(FOLLOW_model_in_results103);
					m=model();
					state._fsp--;

					}

					 r.put((id0!=null?id0.getText():null), m);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:46:3: 'Result of vacuity check [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model )
					{
					match(input,25,FOLLOW_25_in_results114); 
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_results118); 
					match(input,22,FOLLOW_22_in_results120); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_results124); 
					match(input,23,FOLLOW_23_in_results126); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_results130); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:46:69: ( IDENT )?
					int alt4=2;
					int LA4_0 = input.LA(1);
					if ( (LA4_0==IDENT) ) {
						alt4=1;
					}
					switch (alt4) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:46:70: IDENT
							{
							match(input,IDENT,FOLLOW_IDENT_in_results133); 
							}
							break;

					}

					match(input,26,FOLLOW_26_in_results137); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:46:83: (m= model )
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:46:84: m= model
					{
					pushFollow(FOLLOW_model_in_results142);
					m=model();
					state._fsp--;

					}

					 r.put((id0!=null?id0.getText():null), m);
					}
					break;

				default :
					if ( cnt5 >= 1 ) break loop5;
					EarlyExitException eee = new EarlyExitException(5, input);
					throw eee;
				}
				cnt5++;
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
		return r;
	}
	// $ANTLR end "results"



	// $ANTLR start "model"
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:50:1: model returns [Model m] : ( 'sat' '(model' ( assignment )+ ')' | 'unsat' UNSATMESSAGE );
	public final Model model() throws RecognitionException {
		Model m = null;


		Assignment assignment1 =null;

		 List<Assignment> l = new ArrayList<Assignment>(); 
		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:52:2: ( 'sat' '(model' ( assignment )+ ')' | 'unsat' UNSATMESSAGE )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==27) ) {
				alt7=1;
			}
			else if ( (LA7_0==28) ) {
				alt7=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:52:4: 'sat' '(model' ( assignment )+ ')'
					{
					match(input,27,FOLLOW_27_in_model169); 
					match(input,14,FOLLOW_14_in_model171); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:52:19: ( assignment )+
					int cnt6=0;
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( ((LA6_0 >= 10 && LA6_0 <= 12)) ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:52:20: assignment
							{
							pushFollow(FOLLOW_assignment_in_model174);
							assignment1=assignment();
							state._fsp--;

							l.add(assignment1);
							}
							break;

						default :
							if ( cnt6 >= 1 ) break loop6;
							EarlyExitException eee = new EarlyExitException(6, input);
							throw eee;
						}
						cnt6++;
					}

					match(input,15,FOLLOW_15_in_model180); 
					 m = new Model(Sat$.MODULE$, l);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:53:4: 'unsat' UNSATMESSAGE
					{
					match(input,28,FOLLOW_28_in_model187); 
					match(input,UNSATMESSAGE,FOLLOW_UNSATMESSAGE_in_model189); 
					 m = new Model(Unsat$.MODULE$, l);
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
	// $ANTLR end "model"



	// $ANTLR start "assignment"
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:56:1: assignment returns [Assignment a] : ( '(define-fun' id0= IDENT '(' ( '(' ( IDENT )+ ')' )? ')' id1= IDENT id2= value ')' | '(declare-fun' id0= IDENT '(' ')' id1= IDENT ')' | '(forall' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* );
	public final Assignment assignment() throws RecognitionException {
		Assignment a = null;


		Token id0=null;
		Token id1=null;
		String id2 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:57:2: ( '(define-fun' id0= IDENT '(' ( '(' ( IDENT )+ ')' )? ')' id1= IDENT id2= value ')' | '(declare-fun' id0= IDENT '(' ')' id1= IDENT ')' | '(forall' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )
			int alt11=3;
			switch ( input.LA(1) ) {
			case 11:
				{
				alt11=1;
				}
				break;
			case 10:
				{
				alt11=2;
				}
				break;
			case 12:
				{
				alt11=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:57:3: '(define-fun' id0= IDENT '(' ( '(' ( IDENT )+ ')' )? ')' id1= IDENT id2= value ')'
					{
					match(input,11,FOLLOW_11_in_assignment206); 
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment210); 
					match(input,9,FOLLOW_9_in_assignment212); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:57:31: ( '(' ( IDENT )+ ')' )?
					int alt9=2;
					int LA9_0 = input.LA(1);
					if ( (LA9_0==9) ) {
						alt9=1;
					}
					switch (alt9) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:57:32: '(' ( IDENT )+ ')'
							{
							match(input,9,FOLLOW_9_in_assignment215); 
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:57:36: ( IDENT )+
							int cnt8=0;
							loop8:
							while (true) {
								int alt8=2;
								int LA8_0 = input.LA(1);
								if ( (LA8_0==IDENT) ) {
									alt8=1;
								}

								switch (alt8) {
								case 1 :
									// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:57:37: IDENT
									{
									match(input,IDENT,FOLLOW_IDENT_in_assignment218); 
									}
									break;

								default :
									if ( cnt8 >= 1 ) break loop8;
									EarlyExitException eee = new EarlyExitException(8, input);
									throw eee;
								}
								cnt8++;
							}

							match(input,15,FOLLOW_15_in_assignment222); 
							}
							break;

					}

					match(input,15,FOLLOW_15_in_assignment226); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment230); 
					pushFollow(FOLLOW_value_in_assignment234);
					id2=value();
					state._fsp--;

					match(input,15,FOLLOW_15_in_assignment235); 
					ignore = false; a = new Assignment((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), id2);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:58:3: '(declare-fun' id0= IDENT '(' ')' id1= IDENT ')'
					{
					match(input,10,FOLLOW_10_in_assignment242); 
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment246); 
					match(input,9,FOLLOW_9_in_assignment248); 
					match(input,15,FOLLOW_15_in_assignment249); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment253); 
					match(input,15,FOLLOW_15_in_assignment254); 
					a = new Assignment((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), "");
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:59:3: '(forall' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
					{
					match(input,12,FOLLOW_12_in_assignment261); 
					ignore = true;
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:60:2: ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
					loop10:
					while (true) {
						int alt10=2;
						int LA10_0 = input.LA(1);
						if ( (LA10_0==15) ) {
							int LA10_1 = input.LA(2);
							if ( ((LA10_1 >= IDENT && LA10_1 <= NUMBER)||(LA10_1 >= 9 && LA10_1 <= 12)||(LA10_1 >= 15 && LA10_1 <= 17)||(LA10_1 >= 20 && LA10_1 <= 22)) ) {
								alt10=1;
							}

						}
						else if ( ((LA10_0 >= IDENT && LA10_0 <= NUMBER)||LA10_0==9||(LA10_0 >= 16 && LA10_0 <= 17)||(LA10_0 >= 20 && LA10_0 <= 22)) ) {
							alt10=1;
						}

						switch (alt10) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:
							{
							if ( (input.LA(1) >= IDENT && input.LA(1) <= NUMBER)||input.LA(1)==9||(input.LA(1) >= 15 && input.LA(1) <= 17)||(input.LA(1) >= 20 && input.LA(1) <= 22) ) {
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
							break loop10;
						}
					}

					a = new Assignment("", "", "");
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
		return a;
	}
	// $ANTLR end "assignment"



	// $ANTLR start "value"
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:63:1: value returns [String s] : ( IDENT | NUMBER | '(' '-' unary ')' | '(' '/' lhs= NUMBER rhs= NUMBER ')' | '(' IDENT ( '(' ( IDENT )+ ')' )? ')' | '(ite' '(' '=' IDENT IDENT ')' value value ')' );
	public final String value() throws RecognitionException {
		String s = null;


		Token lhs=null;
		Token rhs=null;
		Token IDENT2=null;
		Token NUMBER3=null;
		String unary4 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:64:2: ( IDENT | NUMBER | '(' '-' unary ')' | '(' '/' lhs= NUMBER rhs= NUMBER ')' | '(' IDENT ( '(' ( IDENT )+ ')' )? ')' | '(ite' '(' '=' IDENT IDENT ')' value value ')' )
			int alt14=6;
			switch ( input.LA(1) ) {
			case IDENT:
				{
				alt14=1;
				}
				break;
			case NUMBER:
				{
				alt14=2;
				}
				break;
			case 9:
				{
				switch ( input.LA(2) ) {
				case 18:
					{
					alt14=3;
					}
					break;
				case 19:
					{
					alt14=4;
					}
					break;
				case IDENT:
					{
					alt14=5;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 14, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case 13:
				{
				alt14=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}
			switch (alt14) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:64:4: IDENT
					{
					IDENT2=(Token)match(input,IDENT,FOLLOW_IDENT_in_value321); 
					s = (IDENT2!=null?IDENT2.getText():null);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:65:4: NUMBER
					{
					NUMBER3=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value328); 
					s = (NUMBER3!=null?NUMBER3.getText():null);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:66:4: '(' '-' unary ')'
					{
					match(input,9,FOLLOW_9_in_value335); 
					match(input,18,FOLLOW_18_in_value337); 
					pushFollow(FOLLOW_unary_in_value339);
					unary4=unary();
					state._fsp--;

					match(input,15,FOLLOW_15_in_value341); 
					s = "-" + unary4;
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:67:4: '(' '/' lhs= NUMBER rhs= NUMBER ')'
					{
					match(input,9,FOLLOW_9_in_value348); 
					match(input,19,FOLLOW_19_in_value350); 
					lhs=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value354); 
					rhs=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value358); 
					match(input,15,FOLLOW_15_in_value360); 
					s = (lhs!=null?lhs.getText():null) + "/" + (rhs!=null?rhs.getText():null);
					}
					break;
				case 5 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:68:4: '(' IDENT ( '(' ( IDENT )+ ')' )? ')'
					{
					match(input,9,FOLLOW_9_in_value367); 
					match(input,IDENT,FOLLOW_IDENT_in_value368); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:68:13: ( '(' ( IDENT )+ ')' )?
					int alt13=2;
					int LA13_0 = input.LA(1);
					if ( (LA13_0==9) ) {
						alt13=1;
					}
					switch (alt13) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:68:14: '(' ( IDENT )+ ')'
							{
							match(input,9,FOLLOW_9_in_value371); 
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:68:18: ( IDENT )+
							int cnt12=0;
							loop12:
							while (true) {
								int alt12=2;
								int LA12_0 = input.LA(1);
								if ( (LA12_0==IDENT) ) {
									alt12=1;
								}

								switch (alt12) {
								case 1 :
									// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:68:19: IDENT
									{
									match(input,IDENT,FOLLOW_IDENT_in_value374); 
									}
									break;

								default :
									if ( cnt12 >= 1 ) break loop12;
									EarlyExitException eee = new EarlyExitException(12, input);
									throw eee;
								}
								cnt12++;
							}

							match(input,15,FOLLOW_15_in_value378); 
							}
							break;

					}

					match(input,15,FOLLOW_15_in_value381); 
					s = "";
					}
					break;
				case 6 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:69:4: '(ite' '(' '=' IDENT IDENT ')' value value ')'
					{
					match(input,13,FOLLOW_13_in_value388); 
					match(input,9,FOLLOW_9_in_value390); 
					match(input,22,FOLLOW_22_in_value391); 
					match(input,IDENT,FOLLOW_IDENT_in_value393); 
					match(input,IDENT,FOLLOW_IDENT_in_value395); 
					match(input,15,FOLLOW_15_in_value396); 
					pushFollow(FOLLOW_value_in_value398);
					value();
					state._fsp--;

					pushFollow(FOLLOW_value_in_value400);
					value();
					state._fsp--;

					match(input,15,FOLLOW_15_in_value402); 
					s = "";
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
	// $ANTLR end "value"



	// $ANTLR start "unary"
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:72:1: unary returns [String s] : ( IDENT | NUMBER | value );
	public final String unary() throws RecognitionException {
		String s = null;


		Token IDENT5=null;
		Token NUMBER6=null;
		String value7 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:73:2: ( IDENT | NUMBER | value )
			int alt15=3;
			switch ( input.LA(1) ) {
			case IDENT:
				{
				alt15=1;
				}
				break;
			case NUMBER:
				{
				alt15=2;
				}
				break;
			case 9:
			case 13:
				{
				alt15=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}
			switch (alt15) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:73:4: IDENT
					{
					IDENT5=(Token)match(input,IDENT,FOLLOW_IDENT_in_unary420); 
					s = (IDENT5!=null?IDENT5.getText():null);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:74:4: NUMBER
					{
					NUMBER6=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_unary427); 
					s = (NUMBER6!=null?NUMBER6.getText():null);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:75:4: value
					{
					pushFollow(FOLLOW_value_in_unary434);
					value7=value();
					state._fsp--;

					s = value7;
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
	// $ANTLR end "unary"



	// $ANTLR start "error"
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:78:1: error : Z3ERROR ;
	public final void error() throws RecognitionException {
		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:78:7: ( Z3ERROR )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:78:10: Z3ERROR
			{
			match(input,Z3ERROR,FOLLOW_Z3ERROR_in_error447); 
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
	// $ANTLR end "error"

	// Delegated rules



	public static final BitSet FOLLOW_Z3ERROR_in_results63 = new BitSet(new long[]{0x0000000003000100L});
	public static final BitSet FOLLOW_24_in_results75 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results79 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_results81 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results85 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_results87 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results91 = new BitSet(new long[]{0x0000000004000010L});
	public static final BitSet FOLLOW_IDENT_in_results94 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_26_in_results98 = new BitSet(new long[]{0x0000000018000000L});
	public static final BitSet FOLLOW_model_in_results103 = new BitSet(new long[]{0x0000000003000002L});
	public static final BitSet FOLLOW_25_in_results114 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results118 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_results120 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results124 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_results126 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results130 = new BitSet(new long[]{0x0000000004000010L});
	public static final BitSet FOLLOW_IDENT_in_results133 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_26_in_results137 = new BitSet(new long[]{0x0000000018000000L});
	public static final BitSet FOLLOW_model_in_results142 = new BitSet(new long[]{0x0000000003000002L});
	public static final BitSet FOLLOW_27_in_model169 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_model171 = new BitSet(new long[]{0x0000000000001C00L});
	public static final BitSet FOLLOW_assignment_in_model174 = new BitSet(new long[]{0x0000000000009C00L});
	public static final BitSet FOLLOW_15_in_model180 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_28_in_model187 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_UNSATMESSAGE_in_model189 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_11_in_assignment206 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment210 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_assignment212 = new BitSet(new long[]{0x0000000000008200L});
	public static final BitSet FOLLOW_9_in_assignment215 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment218 = new BitSet(new long[]{0x0000000000008010L});
	public static final BitSet FOLLOW_15_in_assignment222 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_assignment226 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment230 = new BitSet(new long[]{0x0000000000002230L});
	public static final BitSet FOLLOW_value_in_assignment234 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_assignment235 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_10_in_assignment242 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment246 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_assignment248 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_assignment249 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment253 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_assignment254 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_12_in_assignment261 = new BitSet(new long[]{0x0000000000738232L});
	public static final BitSet FOLLOW_IDENT_in_value321 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_value328 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_value335 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_18_in_value337 = new BitSet(new long[]{0x0000000000002230L});
	public static final BitSet FOLLOW_unary_in_value339 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_value341 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_value348 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_value350 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_value354 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_value358 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_value360 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_value367 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_value368 = new BitSet(new long[]{0x0000000000008200L});
	public static final BitSet FOLLOW_9_in_value371 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_value374 = new BitSet(new long[]{0x0000000000008010L});
	public static final BitSet FOLLOW_15_in_value378 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_value381 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_13_in_value388 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_value390 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_value391 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_value393 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_value395 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_value396 = new BitSet(new long[]{0x0000000000002230L});
	public static final BitSet FOLLOW_value_in_value398 = new BitSet(new long[]{0x0000000000002230L});
	public static final BitSet FOLLOW_value_in_value400 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_value402 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_unary420 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_unary427 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_value_in_unary434 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Z3ERROR_in_error447 = new BitSet(new long[]{0x0000000000000002L});
}
