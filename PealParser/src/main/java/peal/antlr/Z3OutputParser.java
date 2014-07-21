// $ANTLR 3.5.1 /Users/jkuo/PealApp-lift/antlr/Z3Output.g 2014-07-21 15:24:40

package peal.antlr;

import org.antlr.runtime.*;
import peal.domain.z3.Assignment;
import peal.domain.z3.Model;
import peal.domain.z3.Sat$;
import peal.domain.z3.Unsat$;
import peal.domain.z3.Unknown$;

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
		"WS", "Z3ERROR", "'('", "'(and'", "'(declare-fun'", "'(define-fun'", "'(forall'", 
		"'(ite'", "'(model'", "')'", "'*'", "'+'", "'-'", "'/'", "'<'", "'<='", 
		"'='", "'?'", "'Result of analysis ['", "'Result of vacuity check ['", 
		"']:'", "'sat'", "'unknown'", "'unsat'"
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
	public static final int T__29=29;
	public static final int T__30=30;
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:41:1: results returns [Map<String, Model> r] : ( ( Z3ERROR )+ )? ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) | 'Result of vacuity check [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+ ;
	public final Map<String, Model> results() throws RecognitionException {
		Map<String, Model> r = null;


		Token id0=null;
		Token id1=null;
		Token id2=null;
		Model m =null;

		r = new HashMap<String, Model>();
		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:43:2: ( ( ( Z3ERROR )+ )? ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) | 'Result of vacuity check [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+ )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:43:3: ( ( Z3ERROR )+ )? ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) | 'Result of vacuity check [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+
			{
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:43:3: ( ( Z3ERROR )+ )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==Z3ERROR) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:43:4: ( Z3ERROR )+
					{
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:43:4: ( Z3ERROR )+
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
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:43:5: Z3ERROR
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

			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:44:2: ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) | 'Result of vacuity check [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+
			int cnt5=0;
			loop5:
			while (true) {
				int alt5=3;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==25) ) {
					alt5=1;
				}
				else if ( (LA5_0==26) ) {
					alt5=2;
				}

				switch (alt5) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:45:3: 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model )
					{
					match(input,25,FOLLOW_25_in_results75); 
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_results79); 
					match(input,23,FOLLOW_23_in_results81); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_results85); 
					match(input,24,FOLLOW_24_in_results87); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_results91); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:45:64: ( IDENT )?
					int alt3=2;
					int LA3_0 = input.LA(1);
					if ( (LA3_0==IDENT) ) {
						alt3=1;
					}
					switch (alt3) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:45:65: IDENT
							{
							match(input,IDENT,FOLLOW_IDENT_in_results94); 
							}
							break;

					}

					match(input,27,FOLLOW_27_in_results98); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:45:78: (m= model )
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:45:79: m= model
					{
					pushFollow(FOLLOW_model_in_results103);
					m=model();
					state._fsp--;

					}

					 r.put((id0!=null?id0.getText():null), m);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:47:3: 'Result of vacuity check [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model )
					{
					match(input,26,FOLLOW_26_in_results114); 
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_results118); 
					match(input,23,FOLLOW_23_in_results120); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_results124); 
					match(input,24,FOLLOW_24_in_results126); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_results130); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:47:69: ( IDENT )?
					int alt4=2;
					int LA4_0 = input.LA(1);
					if ( (LA4_0==IDENT) ) {
						alt4=1;
					}
					switch (alt4) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:47:70: IDENT
							{
							match(input,IDENT,FOLLOW_IDENT_in_results133); 
							}
							break;

					}

					match(input,27,FOLLOW_27_in_results137); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:47:83: (m= model )
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:47:84: m= model
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:51:1: model returns [Model m] : ( 'sat' '(model' ( assignment )+ ')' | 'unknown' '(model' ( assignment )+ ')' | 'unsat' UNSATMESSAGE );
	public final Model model() throws RecognitionException {
		Model m = null;


		Assignment assignment1 =null;
		Assignment assignment2 =null;

		 List<Assignment> l = new ArrayList<Assignment>(); 
		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:53:2: ( 'sat' '(model' ( assignment )+ ')' | 'unknown' '(model' ( assignment )+ ')' | 'unsat' UNSATMESSAGE )
			int alt8=3;
			switch ( input.LA(1) ) {
			case 28:
				{
				alt8=1;
				}
				break;
			case 29:
				{
				alt8=2;
				}
				break;
			case 30:
				{
				alt8=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}
			switch (alt8) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:53:4: 'sat' '(model' ( assignment )+ ')'
					{
					match(input,28,FOLLOW_28_in_model169); 
					match(input,15,FOLLOW_15_in_model171); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:53:19: ( assignment )+
					int cnt6=0;
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( ((LA6_0 >= 11 && LA6_0 <= 13)) ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:53:20: assignment
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

					match(input,16,FOLLOW_16_in_model180); 
					 m = new Model(Sat$.MODULE$, l);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:54:4: 'unknown' '(model' ( assignment )+ ')'
					{
					match(input,29,FOLLOW_29_in_model187); 
					match(input,15,FOLLOW_15_in_model189); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:54:23: ( assignment )+
					int cnt7=0;
					loop7:
					while (true) {
						int alt7=2;
						int LA7_0 = input.LA(1);
						if ( ((LA7_0 >= 11 && LA7_0 <= 13)) ) {
							alt7=1;
						}

						switch (alt7) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:54:24: assignment
							{
							pushFollow(FOLLOW_assignment_in_model192);
							assignment2=assignment();
							state._fsp--;

							l.add(assignment2);
							}
							break;

						default :
							if ( cnt7 >= 1 ) break loop7;
							EarlyExitException eee = new EarlyExitException(7, input);
							throw eee;
						}
						cnt7++;
					}

					match(input,16,FOLLOW_16_in_model198); 
					 m = new Model(Unknown$.MODULE$, l);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:55:4: 'unsat' UNSATMESSAGE
					{
					match(input,30,FOLLOW_30_in_model206); 
					match(input,UNSATMESSAGE,FOLLOW_UNSATMESSAGE_in_model208); 
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:58:1: assignment returns [Assignment a] : ( '(define-fun' id0= IDENT '(' ( '(' ( IDENT )+ ')' )* ')' id1= IDENT id2= value ')' | '(declare-fun' id0= IDENT '(' ')' id1= IDENT ')' | '(forall' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* );
	public final Assignment assignment() throws RecognitionException {
		Assignment a = null;


		Token id0=null;
		Token id1=null;
		String id2 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:59:2: ( '(define-fun' id0= IDENT '(' ( '(' ( IDENT )+ ')' )* ')' id1= IDENT id2= value ')' | '(declare-fun' id0= IDENT '(' ')' id1= IDENT ')' | '(forall' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )
			int alt12=3;
			switch ( input.LA(1) ) {
			case 12:
				{
				alt12=1;
				}
				break;
			case 11:
				{
				alt12=2;
				}
				break;
			case 13:
				{
				alt12=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}
			switch (alt12) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:59:3: '(define-fun' id0= IDENT '(' ( '(' ( IDENT )+ ')' )* ')' id1= IDENT id2= value ')'
					{
					match(input,12,FOLLOW_12_in_assignment225); 
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment229); 
					match(input,9,FOLLOW_9_in_assignment231); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:59:31: ( '(' ( IDENT )+ ')' )*
					loop10:
					while (true) {
						int alt10=2;
						int LA10_0 = input.LA(1);
						if ( (LA10_0==9) ) {
							alt10=1;
						}

						switch (alt10) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:59:32: '(' ( IDENT )+ ')'
							{
							match(input,9,FOLLOW_9_in_assignment234); 
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:59:36: ( IDENT )+
							int cnt9=0;
							loop9:
							while (true) {
								int alt9=2;
								int LA9_0 = input.LA(1);
								if ( (LA9_0==IDENT) ) {
									alt9=1;
								}

								switch (alt9) {
								case 1 :
									// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:59:37: IDENT
									{
									match(input,IDENT,FOLLOW_IDENT_in_assignment237); 
									}
									break;

								default :
									if ( cnt9 >= 1 ) break loop9;
									EarlyExitException eee = new EarlyExitException(9, input);
									throw eee;
								}
								cnt9++;
							}

							match(input,16,FOLLOW_16_in_assignment241); 
							}
							break;

						default :
							break loop10;
						}
					}

					match(input,16,FOLLOW_16_in_assignment245); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment249); 
					pushFollow(FOLLOW_value_in_assignment253);
					id2=value();
					state._fsp--;

					match(input,16,FOLLOW_16_in_assignment254); 
					ignore = false; a = new Assignment((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), id2);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:60:3: '(declare-fun' id0= IDENT '(' ')' id1= IDENT ')'
					{
					match(input,11,FOLLOW_11_in_assignment261); 
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment265); 
					match(input,9,FOLLOW_9_in_assignment267); 
					match(input,16,FOLLOW_16_in_assignment268); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment272); 
					match(input,16,FOLLOW_16_in_assignment273); 
					a = new Assignment((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), "");
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:61:3: '(forall' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
					{
					match(input,13,FOLLOW_13_in_assignment280); 
					ignore = true;
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:62:2: ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
					loop11:
					while (true) {
						int alt11=2;
						int LA11_0 = input.LA(1);
						if ( (LA11_0==16) ) {
							int LA11_1 = input.LA(2);
							if ( ((LA11_1 >= IDENT && LA11_1 <= NUMBER)||LA11_1==9||(LA11_1 >= 11 && LA11_1 <= 13)||(LA11_1 >= 16 && LA11_1 <= 18)||(LA11_1 >= 21 && LA11_1 <= 23)) ) {
								alt11=1;
							}

						}
						else if ( ((LA11_0 >= IDENT && LA11_0 <= NUMBER)||LA11_0==9||(LA11_0 >= 17 && LA11_0 <= 18)||(LA11_0 >= 21 && LA11_0 <= 23)) ) {
							alt11=1;
						}

						switch (alt11) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:
							{
							if ( (input.LA(1) >= IDENT && input.LA(1) <= NUMBER)||input.LA(1)==9||(input.LA(1) >= 16 && input.LA(1) <= 18)||(input.LA(1) >= 21 && input.LA(1) <= 23) ) {
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
							break loop11;
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:65:1: value returns [String s] : ( IDENT | NUMBER | '(' '-' unary ')' | '(' '/' lhs= NUMBER rhs= NUMBER ')' | '(' IDENT ( '(' ( IDENT )+ ')' )? ')' | '(ite' component component component ')' );
	public final String value() throws RecognitionException {
		String s = null;


		Token lhs=null;
		Token rhs=null;
		Token IDENT3=null;
		Token NUMBER4=null;
		String unary5 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:66:2: ( IDENT | NUMBER | '(' '-' unary ')' | '(' '/' lhs= NUMBER rhs= NUMBER ')' | '(' IDENT ( '(' ( IDENT )+ ')' )? ')' | '(ite' component component component ')' )
			int alt15=6;
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
				{
				switch ( input.LA(2) ) {
				case 19:
					{
					alt15=3;
					}
					break;
				case 20:
					{
					alt15=4;
					}
					break;
				case IDENT:
					{
					alt15=5;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 15, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case 14:
				{
				alt15=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}
			switch (alt15) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:66:4: IDENT
					{
					IDENT3=(Token)match(input,IDENT,FOLLOW_IDENT_in_value340); 
					s = (IDENT3!=null?IDENT3.getText():null);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:67:4: NUMBER
					{
					NUMBER4=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value347); 
					s = (NUMBER4!=null?NUMBER4.getText():null);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:68:4: '(' '-' unary ')'
					{
					match(input,9,FOLLOW_9_in_value354); 
					match(input,19,FOLLOW_19_in_value356); 
					pushFollow(FOLLOW_unary_in_value358);
					unary5=unary();
					state._fsp--;

					match(input,16,FOLLOW_16_in_value360); 
					s = "-" + unary5;
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:69:4: '(' '/' lhs= NUMBER rhs= NUMBER ')'
					{
					match(input,9,FOLLOW_9_in_value367); 
					match(input,20,FOLLOW_20_in_value369); 
					lhs=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value373); 
					rhs=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value377); 
					match(input,16,FOLLOW_16_in_value379); 
					s = (lhs!=null?lhs.getText():null) + "/" + (rhs!=null?rhs.getText():null);
					}
					break;
				case 5 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:70:4: '(' IDENT ( '(' ( IDENT )+ ')' )? ')'
					{
					match(input,9,FOLLOW_9_in_value386); 
					match(input,IDENT,FOLLOW_IDENT_in_value387); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:70:13: ( '(' ( IDENT )+ ')' )?
					int alt14=2;
					int LA14_0 = input.LA(1);
					if ( (LA14_0==9) ) {
						alt14=1;
					}
					switch (alt14) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:70:14: '(' ( IDENT )+ ')'
							{
							match(input,9,FOLLOW_9_in_value390); 
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:70:18: ( IDENT )+
							int cnt13=0;
							loop13:
							while (true) {
								int alt13=2;
								int LA13_0 = input.LA(1);
								if ( (LA13_0==IDENT) ) {
									alt13=1;
								}

								switch (alt13) {
								case 1 :
									// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:70:19: IDENT
									{
									match(input,IDENT,FOLLOW_IDENT_in_value393); 
									}
									break;

								default :
									if ( cnt13 >= 1 ) break loop13;
									EarlyExitException eee = new EarlyExitException(13, input);
									throw eee;
								}
								cnt13++;
							}

							match(input,16,FOLLOW_16_in_value397); 
							}
							break;

					}

					match(input,16,FOLLOW_16_in_value400); 
					s = "";
					}
					break;
				case 6 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:71:4: '(ite' component component component ')'
					{
					match(input,14,FOLLOW_14_in_value407); 
					pushFollow(FOLLOW_component_in_value409);
					component();
					state._fsp--;

					pushFollow(FOLLOW_component_in_value411);
					component();
					state._fsp--;

					pushFollow(FOLLOW_component_in_value413);
					component();
					state._fsp--;

					match(input,16,FOLLOW_16_in_value415); 
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



	// $ANTLR start "component"
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:74:1: component : ( '(' '=' IDENT value ')' | '(and' ( component )+ ')' | value );
	public final void component() throws RecognitionException {
		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:75:2: ( '(' '=' IDENT value ')' | '(and' ( component )+ ')' | value )
			int alt17=3;
			switch ( input.LA(1) ) {
			case 9:
				{
				int LA17_1 = input.LA(2);
				if ( (LA17_1==23) ) {
					alt17=1;
				}
				else if ( (LA17_1==IDENT||(LA17_1 >= 19 && LA17_1 <= 20)) ) {
					alt17=3;
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
				break;
			case 10:
				{
				alt17=2;
				}
				break;
			case IDENT:
			case NUMBER:
			case 14:
				{
				alt17=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}
			switch (alt17) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:75:4: '(' '=' IDENT value ')'
					{
					match(input,9,FOLLOW_9_in_component431); 
					match(input,23,FOLLOW_23_in_component432); 
					match(input,IDENT,FOLLOW_IDENT_in_component434); 
					pushFollow(FOLLOW_value_in_component436);
					value();
					state._fsp--;

					match(input,16,FOLLOW_16_in_component437); 
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:76:4: '(and' ( component )+ ')'
					{
					match(input,10,FOLLOW_10_in_component444); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:76:11: ( component )+
					int cnt16=0;
					loop16:
					while (true) {
						int alt16=2;
						int LA16_0 = input.LA(1);
						if ( ((LA16_0 >= IDENT && LA16_0 <= NUMBER)||(LA16_0 >= 9 && LA16_0 <= 10)||LA16_0==14) ) {
							alt16=1;
						}

						switch (alt16) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:76:12: component
							{
							pushFollow(FOLLOW_component_in_component447);
							component();
							state._fsp--;

							}
							break;

						default :
							if ( cnt16 >= 1 ) break loop16;
							EarlyExitException eee = new EarlyExitException(16, input);
							throw eee;
						}
						cnt16++;
					}

					match(input,16,FOLLOW_16_in_component451); 
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:77:4: value
					{
					pushFollow(FOLLOW_value_in_component456);
					value();
					state._fsp--;

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
	// $ANTLR end "component"



	// $ANTLR start "unary"
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:80:1: unary returns [String s] : ( IDENT | NUMBER | value );
	public final String unary() throws RecognitionException {
		String s = null;


		Token IDENT6=null;
		Token NUMBER7=null;
		String value8 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:81:2: ( IDENT | NUMBER | value )
			int alt18=3;
			switch ( input.LA(1) ) {
			case IDENT:
				{
				alt18=1;
				}
				break;
			case NUMBER:
				{
				alt18=2;
				}
				break;
			case 9:
			case 14:
				{
				alt18=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 18, 0, input);
				throw nvae;
			}
			switch (alt18) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:81:4: IDENT
					{
					IDENT6=(Token)match(input,IDENT,FOLLOW_IDENT_in_unary473); 
					s = (IDENT6!=null?IDENT6.getText():null);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:82:4: NUMBER
					{
					NUMBER7=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_unary480); 
					s = (NUMBER7!=null?NUMBER7.getText():null);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:83:4: value
					{
					pushFollow(FOLLOW_value_in_unary487);
					value8=value();
					state._fsp--;

					s = value8;
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:86:1: error : Z3ERROR ;
	public final void error() throws RecognitionException {
		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:86:7: ( Z3ERROR )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:86:10: Z3ERROR
			{
			match(input,Z3ERROR,FOLLOW_Z3ERROR_in_error500); 
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



	public static final BitSet FOLLOW_Z3ERROR_in_results63 = new BitSet(new long[]{0x0000000006000100L});
	public static final BitSet FOLLOW_25_in_results75 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results79 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_results81 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results85 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_results87 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results91 = new BitSet(new long[]{0x0000000008000010L});
	public static final BitSet FOLLOW_IDENT_in_results94 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_results98 = new BitSet(new long[]{0x0000000070000000L});
	public static final BitSet FOLLOW_model_in_results103 = new BitSet(new long[]{0x0000000006000002L});
	public static final BitSet FOLLOW_26_in_results114 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results118 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_results120 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results124 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_results126 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results130 = new BitSet(new long[]{0x0000000008000010L});
	public static final BitSet FOLLOW_IDENT_in_results133 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_results137 = new BitSet(new long[]{0x0000000070000000L});
	public static final BitSet FOLLOW_model_in_results142 = new BitSet(new long[]{0x0000000006000002L});
	public static final BitSet FOLLOW_28_in_model169 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_model171 = new BitSet(new long[]{0x0000000000003800L});
	public static final BitSet FOLLOW_assignment_in_model174 = new BitSet(new long[]{0x0000000000013800L});
	public static final BitSet FOLLOW_16_in_model180 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_29_in_model187 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_model189 = new BitSet(new long[]{0x0000000000003800L});
	public static final BitSet FOLLOW_assignment_in_model192 = new BitSet(new long[]{0x0000000000013800L});
	public static final BitSet FOLLOW_16_in_model198 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_30_in_model206 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_UNSATMESSAGE_in_model208 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_12_in_assignment225 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment229 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_assignment231 = new BitSet(new long[]{0x0000000000010200L});
	public static final BitSet FOLLOW_9_in_assignment234 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment237 = new BitSet(new long[]{0x0000000000010010L});
	public static final BitSet FOLLOW_16_in_assignment241 = new BitSet(new long[]{0x0000000000010200L});
	public static final BitSet FOLLOW_16_in_assignment245 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment249 = new BitSet(new long[]{0x0000000000004230L});
	public static final BitSet FOLLOW_value_in_assignment253 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_assignment254 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_11_in_assignment261 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment265 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_assignment267 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_assignment268 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment272 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_assignment273 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_13_in_assignment280 = new BitSet(new long[]{0x0000000000E70232L});
	public static final BitSet FOLLOW_IDENT_in_value340 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_value347 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_value354 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_value356 = new BitSet(new long[]{0x0000000000004230L});
	public static final BitSet FOLLOW_unary_in_value358 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_value360 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_value367 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_value369 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_value373 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_value377 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_value379 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_value386 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_value387 = new BitSet(new long[]{0x0000000000010200L});
	public static final BitSet FOLLOW_9_in_value390 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_value393 = new BitSet(new long[]{0x0000000000010010L});
	public static final BitSet FOLLOW_16_in_value397 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_value400 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_14_in_value407 = new BitSet(new long[]{0x0000000000004630L});
	public static final BitSet FOLLOW_component_in_value409 = new BitSet(new long[]{0x0000000000004630L});
	public static final BitSet FOLLOW_component_in_value411 = new BitSet(new long[]{0x0000000000004630L});
	public static final BitSet FOLLOW_component_in_value413 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_value415 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_component431 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_component432 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_component434 = new BitSet(new long[]{0x0000000000004230L});
	public static final BitSet FOLLOW_value_in_component436 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_component437 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_10_in_component444 = new BitSet(new long[]{0x0000000000004630L});
	public static final BitSet FOLLOW_component_in_component447 = new BitSet(new long[]{0x0000000000014630L});
	public static final BitSet FOLLOW_16_in_component451 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_value_in_component456 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_unary473 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_unary480 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_value_in_unary487 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Z3ERROR_in_error500 = new BitSet(new long[]{0x0000000000000002L});
}
