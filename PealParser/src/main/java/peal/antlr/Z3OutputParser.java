// $ANTLR 3.5.1 /Users/jkuo/PealApp-lift/antlr/Z3Output.g 2014-07-05 09:33:55

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
		"'(model'", "')'", "'*'", "'+'", "'-'", "'/'", "'<'", "'<='", "'='", "'?'", 
		"'Result of analysis ['", "']:'", "'sat'", "'unsat'"
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
				if ( (LA4_0==23) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:43:2: 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model )
					{
					match(input,23,FOLLOW_23_in_results72); 
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_results76); 
					match(input,21,FOLLOW_21_in_results78); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_results82); 
					match(input,22,FOLLOW_22_in_results84); 
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

					match(input,24,FOLLOW_24_in_results95); 
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
			if ( (LA6_0==25) ) {
				alt6=1;
			}
			else if ( (LA6_0==26) ) {
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
					match(input,25,FOLLOW_25_in_model128); 
					match(input,13,FOLLOW_13_in_model130); 
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

					match(input,14,FOLLOW_14_in_model139); 
					 m = new Model(Sat$.MODULE$, l);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:51:4: 'unsat' UNSATMESSAGE
					{
					match(input,26,FOLLOW_26_in_model146); 
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

							match(input,14,FOLLOW_14_in_assignment181); 
							}
							break;

					}

					match(input,14,FOLLOW_14_in_assignment185); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment189); 
					pushFollow(FOLLOW_value_in_assignment193);
					id2=value();
					state._fsp--;

					match(input,14,FOLLOW_14_in_assignment194); 
					ignore = false; a = new Assignment((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), id2);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:56:3: '(declare-fun' id0= IDENT '(' ')' id1= IDENT ')'
					{
					match(input,10,FOLLOW_10_in_assignment201); 
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment205); 
					match(input,9,FOLLOW_9_in_assignment207); 
					match(input,14,FOLLOW_14_in_assignment208); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment212); 
					match(input,14,FOLLOW_14_in_assignment213); 
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:57:3: '(forall' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
					{
					match(input,12,FOLLOW_12_in_assignment218); 
					ignore = true;
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:58:2: ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
					loop9:
					while (true) {
						int alt9=2;
						int LA9_0 = input.LA(1);
						if ( (LA9_0==14) ) {
							int LA9_1 = input.LA(2);
							if ( ((LA9_1 >= IDENT && LA9_1 <= NUMBER)||(LA9_1 >= 9 && LA9_1 <= 12)||(LA9_1 >= 14 && LA9_1 <= 16)||(LA9_1 >= 19 && LA9_1 <= 21)) ) {
								alt9=1;
							}

						}
						else if ( ((LA9_0 >= IDENT && LA9_0 <= NUMBER)||LA9_0==9||(LA9_0 >= 15 && LA9_0 <= 16)||(LA9_0 >= 19 && LA9_0 <= 21)) ) {
							alt9=1;
						}

						switch (alt9) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:
							{
							if ( (input.LA(1) >= IDENT && input.LA(1) <= NUMBER)||input.LA(1)==9||(input.LA(1) >= 14 && input.LA(1) <= 16)||(input.LA(1) >= 19 && input.LA(1) <= 21) ) {
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:61:1: value returns [String s] : ( IDENT | NUMBER | '(' '-' unary ')' | '(' '/' lhs= NUMBER rhs= NUMBER ')' );
	public final String value() throws RecognitionException {
		String s = null;


		Token lhs=null;
		Token rhs=null;
		Token IDENT2=null;
		Token NUMBER3=null;
		String unary4 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:62:2: ( IDENT | NUMBER | '(' '-' unary ')' | '(' '/' lhs= NUMBER rhs= NUMBER ')' )
			int alt11=4;
			switch ( input.LA(1) ) {
			case IDENT:
				{
				alt11=1;
				}
				break;
			case NUMBER:
				{
				alt11=2;
				}
				break;
			case 9:
				{
				int LA11_3 = input.LA(2);
				if ( (LA11_3==17) ) {
					alt11=3;
				}
				else if ( (LA11_3==18) ) {
					alt11=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 11, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:62:4: IDENT
					{
					IDENT2=(Token)match(input,IDENT,FOLLOW_IDENT_in_value275); 
					s = (IDENT2!=null?IDENT2.getText():null);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:63:4: NUMBER
					{
					NUMBER3=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value282); 
					s = (NUMBER3!=null?NUMBER3.getText():null);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:64:4: '(' '-' unary ')'
					{
					match(input,9,FOLLOW_9_in_value289); 
					match(input,17,FOLLOW_17_in_value291); 
					pushFollow(FOLLOW_unary_in_value293);
					unary4=unary();
					state._fsp--;

					match(input,14,FOLLOW_14_in_value295); 
					s = "-" + unary4;
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:65:4: '(' '/' lhs= NUMBER rhs= NUMBER ')'
					{
					match(input,9,FOLLOW_9_in_value302); 
					match(input,18,FOLLOW_18_in_value304); 
					lhs=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value308); 
					rhs=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value312); 
					match(input,14,FOLLOW_14_in_value314); 
					s = (lhs!=null?lhs.getText():null) + "/" + (rhs!=null?rhs.getText():null);
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:68:1: unary returns [String s] : ( IDENT | NUMBER | value );
	public final String unary() throws RecognitionException {
		String s = null;


		Token IDENT5=null;
		Token NUMBER6=null;
		String value7 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:69:2: ( IDENT | NUMBER | value )
			int alt12=3;
			switch ( input.LA(1) ) {
			case IDENT:
				{
				alt12=1;
				}
				break;
			case NUMBER:
				{
				alt12=2;
				}
				break;
			case 9:
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
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:69:4: IDENT
					{
					IDENT5=(Token)match(input,IDENT,FOLLOW_IDENT_in_unary332); 
					s = (IDENT5!=null?IDENT5.getText():null);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:70:4: NUMBER
					{
					NUMBER6=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_unary339); 
					s = (NUMBER6!=null?NUMBER6.getText():null);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:71:4: value
					{
					pushFollow(FOLLOW_value_in_unary346);
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:74:1: error : Z3ERROR ;
	public final void error() throws RecognitionException {
		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:74:7: ( Z3ERROR )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:74:10: Z3ERROR
			{
			match(input,Z3ERROR,FOLLOW_Z3ERROR_in_error359); 
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



	public static final BitSet FOLLOW_Z3ERROR_in_results63 = new BitSet(new long[]{0x0000000000800100L});
	public static final BitSet FOLLOW_23_in_results72 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results76 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_21_in_results78 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results82 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_results84 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results88 = new BitSet(new long[]{0x0000000001000010L});
	public static final BitSet FOLLOW_IDENT_in_results91 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_results95 = new BitSet(new long[]{0x0000000006000000L});
	public static final BitSet FOLLOW_model_in_results101 = new BitSet(new long[]{0x0000000000800002L});
	public static final BitSet FOLLOW_25_in_model128 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_model130 = new BitSet(new long[]{0x0000000000001C00L});
	public static final BitSet FOLLOW_assignment_in_model133 = new BitSet(new long[]{0x0000000000005C00L});
	public static final BitSet FOLLOW_14_in_model139 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_26_in_model146 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_UNSATMESSAGE_in_model148 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_11_in_assignment165 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment169 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_assignment171 = new BitSet(new long[]{0x0000000000004200L});
	public static final BitSet FOLLOW_9_in_assignment174 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment177 = new BitSet(new long[]{0x0000000000004010L});
	public static final BitSet FOLLOW_14_in_assignment181 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_assignment185 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment189 = new BitSet(new long[]{0x0000000000000230L});
	public static final BitSet FOLLOW_value_in_assignment193 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_assignment194 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_10_in_assignment201 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment205 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_assignment207 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_assignment208 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment212 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_assignment213 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_12_in_assignment218 = new BitSet(new long[]{0x000000000039C232L});
	public static final BitSet FOLLOW_IDENT_in_value275 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_value282 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_value289 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_value291 = new BitSet(new long[]{0x0000000000000230L});
	public static final BitSet FOLLOW_unary_in_value293 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_value295 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_value302 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_18_in_value304 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_value308 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_value312 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_value314 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_unary332 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_unary339 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_value_in_unary346 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Z3ERROR_in_error359 = new BitSet(new long[]{0x0000000000000002L});
}
