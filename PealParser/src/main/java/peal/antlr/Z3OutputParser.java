// $ANTLR 3.5.1 /Users/jkuo/PealApp-lift/antlr/Z3Output.g 2014-06-20 15:09:18

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
		"WS", "Z3ERROR", "'('", "'()'", "'(declare-fun'", "'(define-fun'", "'(model'", 
		"')'", "'-'", "'/'", "'='", "'?'", "'Result of analysis ['", "']:'", "'sat'", 
		"'unsat'"
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




	@Override
	public void reportError(RecognitionException e) {
		throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
	}




	// $ANTLR start "results"
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:38:1: results returns [Map<String, Model> r] : ( ( Z3ERROR )+ )? ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+ ;
	public final Map<String, Model> results() throws RecognitionException {
		Map<String, Model> r = null;


		Token id0=null;
		Token id1=null;
		Token id2=null;
		Model m =null;

		r = new HashMap<String, Model>();
		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:40:2: ( ( ( Z3ERROR )+ )? ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+ )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:40:3: ( ( Z3ERROR )+ )? ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+
			{
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:40:3: ( ( Z3ERROR )+ )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==Z3ERROR) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:40:4: ( Z3ERROR )+
					{
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:40:4: ( Z3ERROR )+
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
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:40:5: Z3ERROR
							{
							match(input,Z3ERROR,FOLLOW_Z3ERROR_in_results56); 
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

			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:40:17: ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==19) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:41:2: 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model )
					{
					match(input,19,FOLLOW_19_in_results65); 
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_results69); 
					match(input,17,FOLLOW_17_in_results71); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_results75); 
					match(input,18,FOLLOW_18_in_results77); 
					id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_results81); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:41:63: ( IDENT )?
					int alt3=2;
					int LA3_0 = input.LA(1);
					if ( (LA3_0==IDENT) ) {
						alt3=1;
					}
					switch (alt3) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:41:64: IDENT
							{
							match(input,IDENT,FOLLOW_IDENT_in_results84); 
							}
							break;

					}

					match(input,20,FOLLOW_20_in_results88); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:42:2: (m= model )
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:42:3: m= model
					{
					pushFollow(FOLLOW_model_in_results94);
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:46:1: model returns [Model m] : ( 'sat' '(model' ( assignment )+ ')' | 'unsat' UNSATMESSAGE );
	public final Model model() throws RecognitionException {
		Model m = null;


		Assignment assignment1 =null;

		 List<Assignment> l = new ArrayList<Assignment>(); 
		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:48:2: ( 'sat' '(model' ( assignment )+ ')' | 'unsat' UNSATMESSAGE )
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==21) ) {
				alt6=1;
			}
			else if ( (LA6_0==22) ) {
				alt6=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}

			switch (alt6) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:48:4: 'sat' '(model' ( assignment )+ ')'
					{
					match(input,21,FOLLOW_21_in_model121); 
					match(input,13,FOLLOW_13_in_model123); 
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:48:19: ( assignment )+
					int cnt5=0;
					loop5:
					while (true) {
						int alt5=2;
						int LA5_0 = input.LA(1);
						if ( ((LA5_0 >= 11 && LA5_0 <= 12)) ) {
							alt5=1;
						}

						switch (alt5) {
						case 1 :
							// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:48:20: assignment
							{
							pushFollow(FOLLOW_assignment_in_model126);
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

					match(input,14,FOLLOW_14_in_model132); 
					 m = new Model(Sat$.MODULE$, l);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:49:4: 'unsat' UNSATMESSAGE
					{
					match(input,22,FOLLOW_22_in_model139); 
					match(input,UNSATMESSAGE,FOLLOW_UNSATMESSAGE_in_model141); 
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:52:1: assignment returns [Assignment a] : ( '(define-fun' id0= IDENT '()' id1= IDENT id2= value ')' | '(declare-fun' id0= IDENT '()' id1= IDENT ')' );
	public final Assignment assignment() throws RecognitionException {
		Assignment a = null;


		Token id0=null;
		Token id1=null;
		String id2 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:53:2: ( '(define-fun' id0= IDENT '()' id1= IDENT id2= value ')' | '(declare-fun' id0= IDENT '()' id1= IDENT ')' )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==12) ) {
				alt7=1;
			}
			else if ( (LA7_0==11) ) {
				alt7=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:53:3: '(define-fun' id0= IDENT '()' id1= IDENT id2= value ')'
					{
					match(input,12,FOLLOW_12_in_assignment158); 
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment162); 
					match(input,10,FOLLOW_10_in_assignment164); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment168); 
					pushFollow(FOLLOW_value_in_assignment172);
					id2=value();
					state._fsp--;

					match(input,14,FOLLOW_14_in_assignment173); 
					a = new Assignment((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), id2);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:54:3: '(declare-fun' id0= IDENT '()' id1= IDENT ')'
					{
					match(input,11,FOLLOW_11_in_assignment180); 
					id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment184); 
					match(input,10,FOLLOW_10_in_assignment186); 
					id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_assignment190); 
					match(input,14,FOLLOW_14_in_assignment191); 
					a = new Assignment((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), "");
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:58:1: value returns [String s] : ( IDENT | NUMBER | '(' '-' unary ')' | '(' '/' lhs= NUMBER rhs= NUMBER ')' );
	public final String value() throws RecognitionException {
		String s = null;


		Token lhs=null;
		Token rhs=null;
		Token IDENT2=null;
		Token NUMBER3=null;
		String unary4 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:59:2: ( IDENT | NUMBER | '(' '-' unary ')' | '(' '/' lhs= NUMBER rhs= NUMBER ')' )
			int alt8=4;
			switch ( input.LA(1) ) {
			case IDENT:
				{
				alt8=1;
				}
				break;
			case NUMBER:
				{
				alt8=2;
				}
				break;
			case 9:
				{
				int LA8_3 = input.LA(2);
				if ( (LA8_3==15) ) {
					alt8=3;
				}
				else if ( (LA8_3==16) ) {
					alt8=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 8, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}
			switch (alt8) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:59:4: IDENT
					{
					IDENT2=(Token)match(input,IDENT,FOLLOW_IDENT_in_value211); 
					s = (IDENT2!=null?IDENT2.getText():null);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:60:4: NUMBER
					{
					NUMBER3=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value218); 
					s = (NUMBER3!=null?NUMBER3.getText():null);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:61:4: '(' '-' unary ')'
					{
					match(input,9,FOLLOW_9_in_value225); 
					match(input,15,FOLLOW_15_in_value227); 
					pushFollow(FOLLOW_unary_in_value229);
					unary4=unary();
					state._fsp--;

					match(input,14,FOLLOW_14_in_value231); 
					s = "-" + unary4;
					}
					break;
				case 4 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:62:4: '(' '/' lhs= NUMBER rhs= NUMBER ')'
					{
					match(input,9,FOLLOW_9_in_value238); 
					match(input,16,FOLLOW_16_in_value240); 
					lhs=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value244); 
					rhs=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value248); 
					match(input,14,FOLLOW_14_in_value250); 
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:65:1: unary returns [String s] : ( IDENT | NUMBER | value );
	public final String unary() throws RecognitionException {
		String s = null;


		Token IDENT5=null;
		Token NUMBER6=null;
		String value7 =null;

		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:66:2: ( IDENT | NUMBER | value )
			int alt9=3;
			switch ( input.LA(1) ) {
			case IDENT:
				{
				alt9=1;
				}
				break;
			case NUMBER:
				{
				alt9=2;
				}
				break;
			case 9:
				{
				alt9=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}
			switch (alt9) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:66:4: IDENT
					{
					IDENT5=(Token)match(input,IDENT,FOLLOW_IDENT_in_unary268); 
					s = (IDENT5!=null?IDENT5.getText():null);
					}
					break;
				case 2 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:67:4: NUMBER
					{
					NUMBER6=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_unary275); 
					s = (NUMBER6!=null?NUMBER6.getText():null);
					}
					break;
				case 3 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:68:4: value
					{
					pushFollow(FOLLOW_value_in_unary282);
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
	// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:71:1: error : Z3ERROR ;
	public final void error() throws RecognitionException {
		try {
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:71:7: ( Z3ERROR )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:71:10: Z3ERROR
			{
			match(input,Z3ERROR,FOLLOW_Z3ERROR_in_error295); 
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



	public static final BitSet FOLLOW_Z3ERROR_in_results56 = new BitSet(new long[]{0x0000000000080100L});
	public static final BitSet FOLLOW_19_in_results65 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results69 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_results71 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results75 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_18_in_results77 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_results81 = new BitSet(new long[]{0x0000000000100010L});
	public static final BitSet FOLLOW_IDENT_in_results84 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_results88 = new BitSet(new long[]{0x0000000000600000L});
	public static final BitSet FOLLOW_model_in_results94 = new BitSet(new long[]{0x0000000000080002L});
	public static final BitSet FOLLOW_21_in_model121 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_model123 = new BitSet(new long[]{0x0000000000001800L});
	public static final BitSet FOLLOW_assignment_in_model126 = new BitSet(new long[]{0x0000000000005800L});
	public static final BitSet FOLLOW_14_in_model132 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_model139 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_UNSATMESSAGE_in_model141 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_12_in_assignment158 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment162 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_assignment164 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment168 = new BitSet(new long[]{0x0000000000000230L});
	public static final BitSet FOLLOW_value_in_assignment172 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_assignment173 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_11_in_assignment180 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment184 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_assignment186 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_IDENT_in_assignment190 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_assignment191 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_value211 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_value218 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_value225 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_value227 = new BitSet(new long[]{0x0000000000000230L});
	public static final BitSet FOLLOW_unary_in_value229 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_value231 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_value238 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_value240 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_value244 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_value248 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_value250 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_unary268 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_unary275 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_value_in_unary282 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Z3ERROR_in_error295 = new BitSet(new long[]{0x0000000000000002L});
}
