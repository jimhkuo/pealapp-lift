// $ANTLR 3.5.1 /Users/jkuo/PealApp-lift/antlr/Z3Output.g 2014-05-22 11:37:57

package peal.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class Z3OutputLexer extends Lexer {
	public static final int EOF=-1;
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
	public static final int IDENT=4;
	public static final int NUMBER=5;
	public static final int WS=6;
	public static final int Z3ERROR=7;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public Z3OutputLexer() {} 
	public Z3OutputLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public Z3OutputLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "/Users/jkuo/PealApp-lift/antlr/Z3Output.g"; }

	// $ANTLR start "T__8"
	public final void mT__8() throws RecognitionException {
		try {
			int _type = T__8;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:11:6: ( '(' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:11:8: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__8"

	// $ANTLR start "T__9"
	public final void mT__9() throws RecognitionException {
		try {
			int _type = T__9;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:12:6: ( '()' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:12:8: '()'
			{
			match("()"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__9"

	// $ANTLR start "T__10"
	public final void mT__10() throws RecognitionException {
		try {
			int _type = T__10;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:13:7: ( '(declare-fun' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:13:9: '(declare-fun'
			{
			match("(declare-fun"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__10"

	// $ANTLR start "T__11"
	public final void mT__11() throws RecognitionException {
		try {
			int _type = T__11;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:14:7: ( '(define-fun' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:14:9: '(define-fun'
			{
			match("(define-fun"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__11"

	// $ANTLR start "T__12"
	public final void mT__12() throws RecognitionException {
		try {
			int _type = T__12;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:15:7: ( '(model' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:15:9: '(model'
			{
			match("(model"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__12"

	// $ANTLR start "T__13"
	public final void mT__13() throws RecognitionException {
		try {
			int _type = T__13;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:16:7: ( ')' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:16:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__13"

	// $ANTLR start "T__14"
	public final void mT__14() throws RecognitionException {
		try {
			int _type = T__14;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:17:7: ( '-' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:17:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__14"

	// $ANTLR start "T__15"
	public final void mT__15() throws RecognitionException {
		try {
			int _type = T__15;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:18:7: ( '/' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:18:9: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__15"

	// $ANTLR start "T__16"
	public final void mT__16() throws RecognitionException {
		try {
			int _type = T__16;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:19:7: ( '=' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:19:9: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__16"

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException {
		try {
			int _type = T__17;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:20:7: ( '?' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:20:9: '?'
			{
			match('?'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__17"

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException {
		try {
			int _type = T__18;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:21:7: ( 'Result of analysis [' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:21:9: 'Result of analysis ['
			{
			match("Result of analysis ["); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:22:7: ( ']:' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:22:9: ']:'
			{
			match("]:"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:23:7: ( 'sat' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:23:9: 'sat'
			{
			match("sat"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__20"

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:24:7: ( 'unsat' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:24:9: 'unsat'
			{
			match("unsat"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__21"

	// $ANTLR start "NUMBER"
	public final void mNUMBER() throws RecognitionException {
		try {
			int _type = NUMBER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:67:8: ( ( '.' | '0' .. '9' | '-' | 'E' )+ )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:67:10: ( '.' | '0' .. '9' | '-' | 'E' )+
			{
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:67:10: ( '.' | '0' .. '9' | '-' | 'E' )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '-' && LA1_0 <= '.')||(LA1_0 >= '0' && LA1_0 <= '9')||LA1_0=='E') ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:
					{
					if ( (input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||input.LA(1)=='E' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
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

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NUMBER"

	// $ANTLR start "IDENT"
	public final void mIDENT() throws RecognitionException {
		try {
			int _type = IDENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:68:7: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( '!' | '_' | 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:68:9: ( 'a' .. 'z' | 'A' .. 'Z' ) ( '!' | '_' | 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:68:30: ( '!' | '_' | 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0=='!'||(LA2_0 >= '0' && LA2_0 <= '9')||(LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:
					{
					if ( input.LA(1)=='!'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop2;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IDENT"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:69:4: ( ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+ )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:69:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
			{
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:69:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '\t' && LA3_0 <= '\n')||(LA3_0 >= '\f' && LA3_0 <= '\r')||LA3_0==' ') ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||(input.LA(1) >= '\f' && input.LA(1) <= '\r')||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			 _channel = HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "Z3ERROR"
	public final void mZ3ERROR() throws RecognitionException {
		try {
			int _type = Z3ERROR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:70:9: ( '(error \"line ' NUMBER ' column ' NUMBER ': model is not available\")' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:70:11: '(error \"line ' NUMBER ' column ' NUMBER ': model is not available\")'
			{
			match("(error \"line "); 

			mNUMBER(); 

			match(" column "); 

			mNUMBER(); 

			match(": model is not available\")"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Z3ERROR"

	@Override
	public void mTokens() throws RecognitionException {
		// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:8: ( T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | NUMBER | IDENT | WS | Z3ERROR )
		int alt4=18;
		alt4 = dfa4.predict(input);
		switch (alt4) {
			case 1 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:10: T__8
				{
				mT__8(); 

				}
				break;
			case 2 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:15: T__9
				{
				mT__9(); 

				}
				break;
			case 3 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:20: T__10
				{
				mT__10(); 

				}
				break;
			case 4 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:26: T__11
				{
				mT__11(); 

				}
				break;
			case 5 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:32: T__12
				{
				mT__12(); 

				}
				break;
			case 6 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:38: T__13
				{
				mT__13(); 

				}
				break;
			case 7 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:44: T__14
				{
				mT__14(); 

				}
				break;
			case 8 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:50: T__15
				{
				mT__15(); 

				}
				break;
			case 9 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:56: T__16
				{
				mT__16(); 

				}
				break;
			case 10 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:62: T__17
				{
				mT__17(); 

				}
				break;
			case 11 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:68: T__18
				{
				mT__18(); 

				}
				break;
			case 12 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:74: T__19
				{
				mT__19(); 

				}
				break;
			case 13 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:80: T__20
				{
				mT__20(); 

				}
				break;
			case 14 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:86: T__21
				{
				mT__21(); 

				}
				break;
			case 15 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:92: NUMBER
				{
				mNUMBER(); 

				}
				break;
			case 16 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:99: IDENT
				{
				mIDENT(); 

				}
				break;
			case 17 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:105: WS
				{
				mWS(); 

				}
				break;
			case 18 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:108: Z3ERROR
				{
				mZ3ERROR(); 

				}
				break;

		}
	}


	protected DFA4 dfa4 = new DFA4(this);
	static final String DFA4_eotS =
		"\1\uffff\1\23\1\uffff\1\24\3\uffff\1\15\1\uffff\2\15\1\14\11\uffff\3\15"+
		"\1\14\1\uffff\1\15\1\40\1\15\2\uffff\1\15\1\uffff\2\15\1\45\1\15\2\uffff";
	static final String DFA4_eofS =
		"\47\uffff";
	static final String DFA4_minS =
		"\1\11\1\51\1\uffff\1\55\3\uffff\1\145\1\uffff\1\141\1\156\1\41\4\uffff"+
		"\1\145\4\uffff\1\163\1\164\1\163\1\41\1\143\1\165\1\41\1\141\2\uffff\1"+
		"\154\1\uffff\2\164\1\41\1\40\2\uffff";
	static final String DFA4_maxS =
		"\1\172\1\155\1\uffff\1\105\3\uffff\1\145\1\uffff\1\141\1\156\1\172\4\uffff"+
		"\1\145\4\uffff\1\163\1\164\1\163\1\172\1\146\1\165\1\172\1\141\2\uffff"+
		"\1\154\1\uffff\2\164\1\172\1\40\2\uffff";
	static final String DFA4_acceptS =
		"\2\uffff\1\6\1\uffff\1\10\1\11\1\12\1\uffff\1\14\3\uffff\1\17\1\20\1\21"+
		"\1\2\1\uffff\1\5\1\22\1\1\1\7\10\uffff\1\3\1\4\1\uffff\1\15\4\uffff\1"+
		"\16\1\13";
	static final String DFA4_specialS =
		"\47\uffff}>";
	static final String[] DFA4_transitionS = {
			"\2\16\1\uffff\2\16\22\uffff\1\16\7\uffff\1\1\1\2\3\uffff\1\3\1\14\1\4"+
			"\12\14\3\uffff\1\5\1\uffff\1\6\1\uffff\4\15\1\13\14\15\1\7\10\15\2\uffff"+
			"\1\10\3\uffff\22\15\1\11\1\15\1\12\5\15",
			"\1\17\72\uffff\1\20\1\22\7\uffff\1\21",
			"",
			"\2\14\1\uffff\12\14\13\uffff\1\14",
			"",
			"",
			"",
			"\1\25",
			"",
			"\1\26",
			"\1\27",
			"\1\15\16\uffff\12\30\7\uffff\4\15\1\30\25\15\4\uffff\1\15\1\uffff\32"+
			"\15",
			"",
			"",
			"",
			"",
			"\1\31",
			"",
			"",
			"",
			"",
			"\1\32",
			"\1\33",
			"\1\34",
			"\1\15\16\uffff\12\30\7\uffff\4\15\1\30\25\15\4\uffff\1\15\1\uffff\32"+
			"\15",
			"\1\35\2\uffff\1\36",
			"\1\37",
			"\1\15\16\uffff\12\15\7\uffff\32\15\4\uffff\1\15\1\uffff\32\15",
			"\1\41",
			"",
			"",
			"\1\42",
			"",
			"\1\43",
			"\1\44",
			"\1\15\16\uffff\12\15\7\uffff\32\15\4\uffff\1\15\1\uffff\32\15",
			"\1\46",
			"",
			""
	};

	static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
	static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
	static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
	static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
	static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
	static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
	static final short[][] DFA4_transition;

	static {
		int numStates = DFA4_transitionS.length;
		DFA4_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
		}
	}

	protected class DFA4 extends DFA {

		public DFA4(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 4;
			this.eot = DFA4_eot;
			this.eof = DFA4_eof;
			this.min = DFA4_min;
			this.max = DFA4_max;
			this.accept = DFA4_accept;
			this.special = DFA4_special;
			this.transition = DFA4_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | NUMBER | IDENT | WS | Z3ERROR );";
		}
	}

}
