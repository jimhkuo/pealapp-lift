// $ANTLR 3.5.1 /Users/jkuo/PealApp-lift/antlr/Z3Output.g 2014-07-21 10:33:49

package peal.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class Z3OutputLexer extends Lexer {
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

	private boolean ignore = false;


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

	// $ANTLR start "T__9"
	public final void mT__9() throws RecognitionException {
		try {
			int _type = T__9;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:14:6: ( '(' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:14:8: '('
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
	// $ANTLR end "T__9"

	// $ANTLR start "T__10"
	public final void mT__10() throws RecognitionException {
		try {
			int _type = T__10;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:15:7: ( '(declare-fun' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:15:9: '(declare-fun'
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
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:16:7: ( '(define-fun' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:16:9: '(define-fun'
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
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:17:7: ( '(forall' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:17:9: '(forall'
			{
			match("(forall"); 

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
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:18:7: ( '(ite' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:18:9: '(ite'
			{
			match("(ite"); 

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
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:19:7: ( '(model' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:19:9: '(model'
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
	// $ANTLR end "T__14"

	// $ANTLR start "T__15"
	public final void mT__15() throws RecognitionException {
		try {
			int _type = T__15;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:20:7: ( ')' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:20:9: ')'
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
	// $ANTLR end "T__15"

	// $ANTLR start "T__16"
	public final void mT__16() throws RecognitionException {
		try {
			int _type = T__16;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:21:7: ( '*' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:21:9: '*'
			{
			match('*'); 
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
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:22:7: ( '+' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:22:9: '+'
			{
			match('+'); 
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
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:23:7: ( '-' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:23:9: '-'
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
	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:24:7: ( '/' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:24:9: '/'
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
	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:25:7: ( '<' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:25:9: '<'
			{
			match('<'); 
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
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:26:7: ( '<=' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:26:9: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:27:7: ( '=' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:27:9: '='
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
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:28:7: ( '?' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:28:9: '?'
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
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:29:7: ( 'Result of analysis [' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:29:9: 'Result of analysis ['
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
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:30:7: ( 'Result of vacuity check [' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:30:9: 'Result of vacuity check ['
			{
			match("Result of vacuity check ["); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:31:7: ( ']:' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:31:9: ']:'
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
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:32:7: ( 'sat' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:32:9: 'sat'
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
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:33:7: ( 'unsat' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:33:9: 'unsat'
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
	// $ANTLR end "T__28"

	// $ANTLR start "NUMBER"
	public final void mNUMBER() throws RecognitionException {
		try {
			int _type = NUMBER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:81:8: ( ( '.' | '0' .. '9' | '-' | 'E' )+ )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:81:10: ( '.' | '0' .. '9' | '-' | 'E' )+
			{
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:81:10: ( '.' | '0' .. '9' | '-' | 'E' )+
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

			if(ignore) skip();
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
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:82:7: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( '!' | '_' | 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:82:9: ( 'a' .. 'z' | 'A' .. 'Z' ) ( '!' | '_' | 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:82:30: ( '!' | '_' | 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
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

			if(ignore) skip();
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
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:83:4: ( ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+ )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:83:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
			{
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:83:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
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

	// $ANTLR start "UNSATMESSAGE"
	public final void mUNSATMESSAGE() throws RecognitionException {
		try {
			int _type = UNSATMESSAGE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:84:14: ( '(error \"line ' NUMBER ' column ' NUMBER ': model is not available\")' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:84:16: '(error \"line ' NUMBER ' column ' NUMBER ': model is not available\")'
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
	// $ANTLR end "UNSATMESSAGE"

	// $ANTLR start "Z3ERROR"
	public final void mZ3ERROR() throws RecognitionException {
		try {
			int _type = Z3ERROR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:85:9: ( '(error \"line ' NUMBER ' column ' NUMBER ': invalid declaration, constant \\'' IDENT '\\' (whith the given signature) already declared\")' )
			// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:85:12: '(error \"line ' NUMBER ' column ' NUMBER ': invalid declaration, constant \\'' IDENT '\\' (whith the given signature) already declared\")'
			{
			match("(error \"line "); 

			mNUMBER(); 

			match(" column "); 

			mNUMBER(); 

			match(": invalid declaration, constant '"); 

			mIDENT(); 

			match("' (whith the given signature) already declared\")"); 

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
		// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:8: ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | NUMBER | IDENT | WS | UNSATMESSAGE | Z3ERROR )
		int alt4=25;
		alt4 = dfa4.predict(input);
		switch (alt4) {
			case 1 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:10: T__9
				{
				mT__9(); 

				}
				break;
			case 2 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:15: T__10
				{
				mT__10(); 

				}
				break;
			case 3 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:21: T__11
				{
				mT__11(); 

				}
				break;
			case 4 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:27: T__12
				{
				mT__12(); 

				}
				break;
			case 5 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:33: T__13
				{
				mT__13(); 

				}
				break;
			case 6 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:39: T__14
				{
				mT__14(); 

				}
				break;
			case 7 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:45: T__15
				{
				mT__15(); 

				}
				break;
			case 8 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:51: T__16
				{
				mT__16(); 

				}
				break;
			case 9 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:57: T__17
				{
				mT__17(); 

				}
				break;
			case 10 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:63: T__18
				{
				mT__18(); 

				}
				break;
			case 11 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:69: T__19
				{
				mT__19(); 

				}
				break;
			case 12 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:75: T__20
				{
				mT__20(); 

				}
				break;
			case 13 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:81: T__21
				{
				mT__21(); 

				}
				break;
			case 14 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:87: T__22
				{
				mT__22(); 

				}
				break;
			case 15 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:93: T__23
				{
				mT__23(); 

				}
				break;
			case 16 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:99: T__24
				{
				mT__24(); 

				}
				break;
			case 17 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:105: T__25
				{
				mT__25(); 

				}
				break;
			case 18 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:111: T__26
				{
				mT__26(); 

				}
				break;
			case 19 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:117: T__27
				{
				mT__27(); 

				}
				break;
			case 20 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:123: T__28
				{
				mT__28(); 

				}
				break;
			case 21 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:129: NUMBER
				{
				mNUMBER(); 

				}
				break;
			case 22 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:136: IDENT
				{
				mIDENT(); 

				}
				break;
			case 23 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:142: WS
				{
				mWS(); 

				}
				break;
			case 24 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:145: UNSATMESSAGE
				{
				mUNSATMESSAGE(); 

				}
				break;
			case 25 :
				// /Users/jkuo/PealApp-lift/antlr/Z3Output.g:1:158: Z3ERROR
				{
				mZ3ERROR(); 

				}
				break;

		}
	}


	protected DFA4 dfa4 = new DFA4(this);
	static final String DFA4_eotS =
		"\1\uffff\1\27\3\uffff\1\30\1\uffff\1\32\2\uffff\1\20\1\uffff\2\20\1\17"+
		"\14\uffff\3\20\1\17\2\uffff\1\20\1\50\1\20\3\uffff\1\20\1\uffff\1\20\1"+
		"\uffff\1\20\1\57\1\uffff\1\20\34\uffff";
	static final String DFA4_eofS =
		"\113\uffff";
	static final String DFA4_minS =
		"\1\11\1\144\3\uffff\1\55\1\uffff\1\75\2\uffff\1\145\1\uffff\1\141\1\156"+
		"\1\41\3\uffff\1\145\3\uffff\1\162\4\uffff\1\163\1\164\1\163\1\41\1\143"+
		"\1\162\1\165\1\41\1\141\2\uffff\1\157\1\154\1\uffff\1\164\1\162\1\164"+
		"\1\41\2\40\1\uffff\1\42\1\157\1\154\1\146\1\151\1\40\1\156\1\141\1\145"+
		"\2\uffff\1\40\1\55\1\40\1\143\1\157\1\154\1\165\1\155\1\156\1\40\2\55"+
		"\1\40\1\151\2\uffff";
	static final String DFA4_maxS =
		"\1\172\1\155\3\uffff\1\105\1\uffff\1\75\2\uffff\1\145\1\uffff\1\141\1"+
		"\156\1\172\3\uffff\1\145\3\uffff\1\162\4\uffff\1\163\1\164\1\163\1\172"+
		"\1\146\1\162\1\165\1\172\1\141\2\uffff\1\157\1\154\1\uffff\1\164\1\162"+
		"\1\164\1\172\2\40\1\uffff\1\42\1\157\1\154\1\146\1\151\1\40\1\156\1\166"+
		"\1\145\2\uffff\1\40\2\105\1\143\1\157\1\154\1\165\1\155\1\156\1\40\2\105"+
		"\1\40\1\155\2\uffff";
	static final String DFA4_acceptS =
		"\2\uffff\1\7\1\10\1\11\1\uffff\1\13\1\uffff\1\16\1\17\1\uffff\1\22\3\uffff"+
		"\1\25\1\26\1\27\1\uffff\1\4\1\5\1\6\1\uffff\1\1\1\12\1\15\1\14\11\uffff"+
		"\1\2\1\3\2\uffff\1\23\6\uffff\1\24\11\uffff\1\20\1\21\16\uffff\1\30\1"+
		"\31";
	static final String DFA4_specialS =
		"\113\uffff}>";
	static final String[] DFA4_transitionS = {
			"\2\21\1\uffff\2\21\22\uffff\1\21\7\uffff\1\1\1\2\1\3\1\4\1\uffff\1\5"+
			"\1\17\1\6\12\17\2\uffff\1\7\1\10\1\uffff\1\11\1\uffff\4\20\1\16\14\20"+
			"\1\12\10\20\2\uffff\1\13\3\uffff\22\20\1\14\1\20\1\15\5\20",
			"\1\22\1\26\1\23\2\uffff\1\24\3\uffff\1\25",
			"",
			"",
			"",
			"\2\17\1\uffff\12\17\13\uffff\1\17",
			"",
			"\1\31",
			"",
			"",
			"\1\33",
			"",
			"\1\34",
			"\1\35",
			"\1\20\16\uffff\12\36\7\uffff\4\20\1\36\25\20\4\uffff\1\20\1\uffff\32"+
			"\20",
			"",
			"",
			"",
			"\1\37",
			"",
			"",
			"",
			"\1\40",
			"",
			"",
			"",
			"",
			"\1\41",
			"\1\42",
			"\1\43",
			"\1\20\16\uffff\12\36\7\uffff\4\20\1\36\25\20\4\uffff\1\20\1\uffff\32"+
			"\20",
			"\1\44\2\uffff\1\45",
			"\1\46",
			"\1\47",
			"\1\20\16\uffff\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
			"\1\51",
			"",
			"",
			"\1\52",
			"\1\53",
			"",
			"\1\54",
			"\1\55",
			"\1\56",
			"\1\20\16\uffff\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
			"\1\60",
			"\1\61",
			"",
			"\1\62",
			"\1\63",
			"\1\64",
			"\1\65",
			"\1\66",
			"\1\67",
			"\1\70",
			"\1\71\24\uffff\1\72",
			"\1\73",
			"",
			"",
			"\1\74",
			"\2\75\1\uffff\12\75\13\uffff\1\75",
			"\1\76\14\uffff\2\75\1\uffff\12\75\13\uffff\1\75",
			"\1\77",
			"\1\100",
			"\1\101",
			"\1\102",
			"\1\103",
			"\1\104",
			"\1\105",
			"\2\106\1\uffff\12\106\13\uffff\1\106",
			"\2\106\1\uffff\12\106\1\107\12\uffff\1\106",
			"\1\110",
			"\1\112\3\uffff\1\111",
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
			return "1:1: Tokens : ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | NUMBER | IDENT | WS | UNSATMESSAGE | Z3ERROR );";
		}
	}

}
