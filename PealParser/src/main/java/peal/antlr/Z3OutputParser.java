// $ANTLR 3.5.1 /Users/jkuo/PealApp-lift/antlr/Z3Output.g 2014-07-05 10:35:46

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
		"'='", "'?'", "'Result of analysis ['", "']:'", "'sat'", "'unsat'"
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:40:1: results returns [Map<String, Model> r] : ( ( Z3ERROR )+ )? ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+ ;
	public final Map<String, Model> results() throws RecognitionException {
		Map<String, Model> r = null;


		Token id0=null;
		Token id1=null;
		Token id2=null;
		Model m =null;

		r = new HashMap<String, Model>();
		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:42:2: ( ( ( Z3ERROR )+ )? ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+ )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:42:3: ( ( Z3ERROR )+ )? ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+
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

			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:42:17: ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==24) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:43:2: 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model )
					{
					match(input,24,FOLLOW_24_in_results72); 
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_results76); 
					match(input,22,FOLLOW_22_in_results78); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_results82); 
					match(input,23,FOLLOW_23_in_results84); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_results88); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:43:63: ( IDENT )?
					int alt3=2;
					int LA3_0 = input.LA(1);
					if ( (LA3_0==IDENT) ) {
						alt3=1;
					}
					switch (alt3) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:43:64: IDENT
							{
							match(input,IDENT,FOLLOW_IDENT_in_results91); 
							}
							break;

					}

					match(input,25,FOLLOW_25_in_results95); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:44:2: (m= model )
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:44:3: m= model
					{
					pushFollow(FOLLOW_model_in_results101);
					m=model();
					state._fsp--;

					}

					 r.put((id0!=null?id0.getText():null), m);
					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:48:1: model returns [Model m] : ( 'sat' '(model' ( assignment )+ ')' | 'unsat' UNSATMESSAGE );
	public final Model model() throws RecognitionException {
		Model m = null;


		Assignment assignment1 =null;

		 List<Assignment> l = new ArrayList<Assignment>(); 
		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:50:2: ( 'sat' '(model' ( assignment )+ ')' | 'unsat' UNSATMESSAGE )
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==26) ) {
				alt6=1;
			}
			else if ( (LA6_0==27) ) {
				alt6=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}

			switch (alt6) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:50:4: 'sat' '(model' ( assignment )+ ')'
					{
					match(input,26,FOLLOW_26_in_model128); 
					match(input,14,FOLLOW_14_in_model130); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:50:19: ( assignment )+
					int cnt5=0;
					loop5:
					while (true) {
						int alt5=2;
						int LA5_0 = input.LA(1);
						if ( ((LA5_0 >= 10 && LA5_0 <= 12)) ) {
							alt5=1;
						}

						switch (alt5) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:50:20: assignment
							{
							pushFollow(FOLLOW_assignment_in_model133);
							assignment1=assignment();
							state._fsp--;

							l.add(assignment1);
							}
							break;

						default :
							if ( cnt5 >= 1 ) break loop5;
							EarlyExitException eee = new EarlyExitException(5, input);
							throw eee;
						}
						cnt5++;
					}

					match(input,15,FOLLOW_15_in_model139); 
					 m = new Model(Sat$.MODULE$, l);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:51:4: 'unsat' UNSATMESSAGE
					{
					match(input,27,FOLLOW_27_in_model146); 
					match(input,UNSATMESSAGE,FOLLOW_UNSATMESSAGE_in_model148); 
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:54:1: assignment returns [Assignment a] : ( '(define-fun' id0= IDENT '(' ( '(' ( IDENT )+ ')' )? ')' id1= IDENT id2= value ')' | '(declare-fun' id0= IDENT '(' ')' id1= IDENT ')' | '(forall' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* );
	public final Assignment assignment() throws RecognitionException {
		Assignment a = null;


		Token id0=null;
		Token id1=null;
		String id2 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:55:2: ( '(define-fun' id0= IDENT '(' ( '(' ( IDENT )+ ')' )? ')' id1= IDENT id2= value ')' | '(declare-fun' id0= IDENT '(' ')' id1= IDENT ')' | '(forall' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )
			int alt10=3;
			switch ( input.LA(1) ) {
			case 11:
				{
				alt10=1;
				}
				break;
			case 10:
				{
				alt10=2;
				}
				break;
			case 12:
				{
				alt10=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}
			switch (alt10) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:55:3: '(define-fun' id0= IDENT '(' ( '(' ( IDENT )+ ')' )? ')' id1= IDENT id2= value ')'
					{
					match(input,11,FOLLOW_11_in_assignment165); 
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment169); 
					match(input,9,FOLLOW_9_in_assignment171); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:55:31: ( '(' ( IDENT )+ ')' )?
					int alt8=2;
					int LA8_0 = input.LA(1);
					if ( (LA8_0==9) ) {
						alt8=1;
					}
					switch (alt8) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:55:32: '(' ( IDENT )+ ')'
							{
							match(input,9,FOLLOW_9_in_assignment174); 
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:55:36: ( IDENT )+
							int cnt7=0;
							loop7:
							while (true) {
								int alt7=2;
								int LA7_0 = input.LA(1);
								if ( (LA7_0==IDENT) ) {
									alt7=1;
								}

								switch (alt7) {
								case 1 :
									// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:55:37: IDENT
									{
									match(input,IDENT,FOLLOW_IDENT_in_assignment177); 
									}
									break;

								default :
									if ( cnt7 >= 1 ) break loop7;
									EarlyExitException eee = new EarlyExitException(7, input);
									throw eee;
								}
								cnt7++;
							}

							match(input,15,FOLLOW_15_in_assignment181); 
							}
							break;

					}

					match(input,15,FOLLOW_15_in_assignment185); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment189); 
					pushFollow(FOLLOW_value_in_assignment193);
					id2=value();
					state._fsp--;

					match(input,15,FOLLOW_15_in_assignment194); 
					ignore = false; a = new Assignment((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), id2);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:56:3: '(declare-fun' id0= IDENT '(' ')' id1= IDENT ')'
					{
					match(input,10,FOLLOW_10_in_assignment201); 
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment205); 
					match(input,9,FOLLOW_9_in_assignment207); 
					match(input,15,FOLLOW_15_in_assignment208); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment212); 
					match(input,15,FOLLOW_15_in_assignment213); 
					a = new Assignment((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), "");
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:57:3: '(forall' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
					{
					match(input,12,FOLLOW_12_in_assignment220); 
					ignore = true;
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:58:2: ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
					loop9:
					while (true) {
						int alt9=2;
						int LA9_0 = input.LA(1);
						if ( (LA9_0==15) ) {
							int LA9_1 = input.LA(2);
							if ( ((LA9_1 >= IDENT && LA9_1 <= NUMBER)||(LA9_1 >= 9 && LA9_1 <= 12)||(LA9_1 >= 15 && LA9_1 <= 17)||(LA9_1 >= 20 && LA9_1 <= 22)) ) {
								alt9=1;
							}

						}
						else if ( ((LA9_0 >= IDENT && LA9_0 <= NUMBER)||LA9_0==9||(LA9_0 >= 16 && LA9_0 <= 17)||(LA9_0 >= 20 && LA9_0 <= 22)) ) {
							alt9=1;
						}

						switch (alt9) {
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
							break loop9;
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:61:1: value returns [String s] : ( IDENT | NUMBER | '(' '-' unary ')' | '(' '/' lhs= NUMBER rhs= NUMBER ')' | '(' IDENT ( '(' ( IDENT )+ ')' )? ')' | '(ite' '(' '=' IDENT IDENT ')' value value ')' );
	public final String value() throws RecognitionException {
		String s = null;


		Token lhs=null;
		Token rhs=null;
		Token IDENT2=null;
		Token NUMBER3=null;
		String unary4 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:62:2: ( IDENT | NUMBER | '(' '-' unary ')' | '(' '/' lhs= NUMBER rhs= NUMBER ')' | '(' IDENT ( '(' ( IDENT )+ ')' )? ')' | '(ite' '(' '=' IDENT IDENT ')' value value ')' )
			int alt13=6;
			switch ( input.LA(1) ) {
			case IDENT:
				{
				alt13=1;
				}
				break;
			case NUMBER:
				{
				alt13=2;
				}
				break;
			case 9:
				{
				switch ( input.LA(2) ) {
				case 18:
					{
					alt13=3;
					}
					break;
				case 19:
					{
					alt13=4;
					}
					break;
				case IDENT:
					{
					alt13=5;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 13, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case 13:
				{
				alt13=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}
			switch (alt13) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:62:4: IDENT
					{
					IDENT2=(Token)match(input,IDENT,FOLLOW_IDENT_in_value280); 
					s = (IDENT2!=null?IDENT2.getText():null);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:63:4: NUMBER
					{
					NUMBER3=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value287); 
					s = (NUMBER3!=null?NUMBER3.getText():null);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:64:4: '(' '-' unary ')'
					{
					match(input,9,FOLLOW_9_in_value294); 
					match(input,18,FOLLOW_18_in_value296); 
					pushFollow(FOLLOW_unary_in_value298);
					unary4=unary();
					state._fsp--;

					match(input,15,FOLLOW_15_in_value300); 
					s = "-" + unary4;
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:65:4: '(' '/' lhs= NUMBER rhs= NUMBER ')'
					{
					match(input,9,FOLLOW_9_in_value307); 
					match(input,19,FOLLOW_19_in_value309); 
					lhs=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value313); 
					rhs=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value317); 
					match(input,15,FOLLOW_15_in_value319); 
					s = (lhs!=null?lhs.getText():null) + "/" + (rhs!=null?rhs.getText():null);
					}
					break;
				case 5 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:66:4: '(' IDENT ( '(' ( IDENT )+ ')' )? ')'
					{
					match(input,9,FOLLOW_9_in_value326); 
					match(input,IDENT,FOLLOW_IDENT_in_value327); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:66:13: ( '(' ( IDENT )+ ')' )?
					int alt12=2;
					int LA12_0 = input.LA(1);
					if ( (LA12_0==9) ) {
						alt12=1;
					}
					switch (alt12) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:66:14: '(' ( IDENT )+ ')'
							{
							match(input,9,FOLLOW_9_in_value330); 
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:66:18: ( IDENT )+
							int cnt11=0;
							loop11:
							while (true) {
								int alt11=2;
								int LA11_0 = input.LA(1);
								if ( (LA11_0==IDENT) ) {
									alt11=1;
								}

								switch (alt11) {
								case 1 :
									// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:66:19: IDENT
									{
									match(input,IDENT,FOLLOW_IDENT_in_value333); 
									}
									break;

								default :
									if ( cnt11 >= 1 ) break loop11;
									EarlyExitException eee = new EarlyExitException(11, input);
									throw eee;
								}
								cnt11++;
							}

							match(input,15,FOLLOW_15_in_value337); 
							}
							break;

					}

					match(input,15,FOLLOW_15_in_value340); 
					s = "";
					}
					break;
				case 6 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:67:4: '(ite' '(' '=' IDENT IDENT ')' value value ')'
					{
					match(input,13,FOLLOW_13_in_value347); 
					match(input,9,FOLLOW_9_in_value349); 
					match(input,22,FOLLOW_22_in_value350); 
					match(input,IDENT,FOLLOW_IDENT_in_value352); 
					match(input,IDENT,FOLLOW_IDENT_in_value354); 
					match(input,15,FOLLOW_15_in_value355); 
					pushFollow(FOLLOW_value_in_value357);
					value();
					state._fsp--;

					pushFollow(FOLLOW_value_in_value359);
					value();
					state._fsp--;

					match(input,15,FOLLOW_15_in_value361); 
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:70:1: unary returns [String s] : ( IDENT | NUMBER | value );
	public final String unary() throws RecognitionException {
		String s = null;


		Token IDENT5=null;
		Token NUMBER6=null;
		String value7 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:71:2: ( IDENT | NUMBER | value )
			int alt14=3;
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
			case 13:
				{
				alt14=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}
			switch (alt14) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:71:4: IDENT
					{
					IDENT5=(Token)match(input,IDENT,FOLLOW_IDENT_in_unary379); 
					s = (IDENT5!=null?IDENT5.getText():null);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:72:4: NUMBER
					{
					NUMBER6=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_unary386); 
					s = (NUMBER6!=null?NUMBER6.getText():null);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:73:4: value
					{
					pushFollow(FOLLOW_value_in_unary393);
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:76:1: error : Z3ERROR ;
	public final void error() throws RecognitionException {
		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:76:7: ( Z3ERROR )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:76:10: Z3ERROR
			{
			match(input,Z3ERROR,FOLLOW_Z3ERROR_in_error406); 
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



	public static final BitSet FOLLOW_Z3ERROR_in_results63 = new BitSet(new long[]{0x0000000001000100L});
	public static final BitSet FOLLOW_24_in_results72 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results76 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_results78 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results82 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_results84 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results88 = new BitSet(new long[]{0x0000000002000010L});
	public static final BitSet FOLLOW_IDENT_in_results91 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_25_in_results95 = new BitSet(new long[]{0x000000000C000000L});
	public static final BitSet FOLLOW_model_in_results101 = new BitSet(new long[]{0x0000000001000002L});
	public static final BitSet FOLLOW_26_in_model128 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_model130 = new BitSet(new long[]{0x0000000000001C00L});
	public static final BitSet FOLLOW_assignment_in_model133 = new BitSet(new long[]{0x0000000000009C00L});
	public static final BitSet FOLLOW_15_in_model139 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_27_in_model146 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_UNSATMESSAGE_in_model148 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_11_in_assignment165 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment169 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_assignment171 = new BitSet(new long[]{0x0000000000008200L});
	public static final BitSet FOLLOW_9_in_assignment174 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment177 = new BitSet(new long[]{0x0000000000008010L});
	public static final BitSet FOLLOW_15_in_assignment181 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_assignment185 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment189 = new BitSet(new long[]{0x0000000000002230L});
	public static final BitSet FOLLOW_value_in_assignment193 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_assignment194 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_10_in_assignment201 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment205 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_assignment207 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_assignment208 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment212 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_assignment213 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_12_in_assignment220 = new BitSet(new long[]{0x0000000000738232L});
	public static final BitSet FOLLOW_IDENT_in_value280 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_value287 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_value294 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_18_in_value296 = new BitSet(new long[]{0x0000000000002230L});
	public static final BitSet FOLLOW_unary_in_value298 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_value300 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_value307 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_value309 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_value313 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_value317 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_value319 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_value326 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_value327 = new BitSet(new long[]{0x0000000000008200L});
	public static final BitSet FOLLOW_9_in_value330 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_value333 = new BitSet(new long[]{0x0000000000008010L});
	public static final BitSet FOLLOW_15_in_value337 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_value340 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_13_in_value347 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_value349 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_value350 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_value352 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_value354 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_value355 = new BitSet(new long[]{0x0000000000002230L});
	public static final BitSet FOLLOW_value_in_value357 = new BitSet(new long[]{0x0000000000002230L});
	public static final BitSet FOLLOW_value_in_value359 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_value361 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_unary379 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_unary386 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_value_in_unary393 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Z3ERROR_in_error406 = new BitSet(new long[]{0x0000000000000002L});
}
