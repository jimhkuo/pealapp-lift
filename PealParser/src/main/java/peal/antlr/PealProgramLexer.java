// $ANTLR 3.5.1 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2014-01-29 09:09:29

package peal.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class PealProgramLexer extends Lexer {
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

	private boolean ignore = false;


	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public PealProgramLexer() {} 
	public PealProgramLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public PealProgramLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "/Users/jkuo/PealApp-lift/antlr/PealProgram.g"; }

	// $ANTLR start "T__7"
	public final void mT__7() throws RecognitionException {
		try {
			int _type = T__7;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:14:6: ( '!' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:14:8: '!'
			{
			match('!'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__7"

	// $ANTLR start "T__8"
	public final void mT__8() throws RecognitionException {
		try {
			int _type = T__8;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:15:6: ( '&&' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:15:8: '&&'
			{
			match("&&"); 

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
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:16:6: ( '(' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:16:8: '('
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
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:17:7: ( ')' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:17:9: ')'
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
	// $ANTLR end "T__10"

	// $ANTLR start "T__11"
	public final void mT__11() throws RecognitionException {
		try {
			int _type = T__11;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:18:7: ( '*' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:18:9: '*'
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
	// $ANTLR end "T__11"

	// $ANTLR start "T__12"
	public final void mT__12() throws RecognitionException {
		try {
			int _type = T__12;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:19:7: ( '+' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:19:9: '+'
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
	// $ANTLR end "T__12"

	// $ANTLR start "T__13"
	public final void mT__13() throws RecognitionException {
		try {
			int _type = T__13;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:20:7: ( ',' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:20:9: ','
			{
			match(','); 
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
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:21:7: ( '<' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:21:9: '<'
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
	// $ANTLR end "T__14"

	// $ANTLR start "T__15"
	public final void mT__15() throws RecognitionException {
		try {
			int _type = T__15;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:22:7: ( '<=' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:22:9: '<='
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
	// $ANTLR end "T__15"

	// $ANTLR start "T__16"
	public final void mT__16() throws RecognitionException {
		try {
			int _type = T__16;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:23:7: ( '=' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:23:9: '='
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
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:24:7: ( 'ANALYSES' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:24:9: 'ANALYSES'
			{
			match("ANALYSES"); 

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
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:25:7: ( 'CONDITIONS' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:25:9: 'CONDITIONS'
			{
			match("CONDITIONS"); 

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
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:26:7: ( 'DOMAIN_SPECIFICS' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:26:9: 'DOMAIN_SPECIFICS'
			{
			match("DOMAIN_SPECIFICS"); 

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
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:27:7: ( 'POLICIES' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:27:9: 'POLICIES'
			{
			match("POLICIES"); 

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
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:28:7: ( 'POLICY_SETS' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:28:9: 'POLICY_SETS'
			{
			match("POLICY_SETS"); 

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
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:29:7: ( '[' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:29:9: '['
			{
			match('['); 
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
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:30:7: ( ']' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:30:9: ']'
			{
			match(']'); 
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
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:31:7: ( 'always_false?' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:31:9: 'always_false?'
			{
			match("always_false?"); 

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
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:32:7: ( 'always_true?' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:32:9: 'always_true?'
			{
			match("always_true?"); 

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
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:33:7: ( 'default' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:33:9: 'default'
			{
			match("default"); 

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
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:34:7: ( 'different?' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:34:9: 'different?'
			{
			match("different?"); 

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
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:35:7: ( 'equivalent?' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:35:9: 'equivalent?'
			{
			match("equivalent?"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:36:7: ( 'false' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:36:9: 'false'
			{
			match("false"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:37:7: ( 'implies?' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:37:9: 'implies?'
			{
			match("implies?"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:38:7: ( 'max' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:38:9: 'max'
			{
			match("max"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:39:7: ( 'min' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:39:9: 'min'
			{
			match("min"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:40:7: ( 'satisfiable?' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:40:9: 'satisfiable?'
			{
			match("satisfiable?"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__33"

	// $ANTLR start "T__34"
	public final void mT__34() throws RecognitionException {
		try {
			int _type = T__34;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:41:7: ( 'true' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:41:9: 'true'
			{
			match("true"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__34"

	// $ANTLR start "T__35"
	public final void mT__35() throws RecognitionException {
		try {
			int _type = T__35;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:42:7: ( '||' )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:42:9: '||'
			{
			match("||"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__35"

	// $ANTLR start "NUMBER"
	public final void mNUMBER() throws RecognitionException {
		try {
			int _type = NUMBER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:134:8: ( ( '.' | '0' .. '9' | '-' | 'E' )+ )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:134:10: ( '.' | '0' .. '9' | '-' | 'E' )+
			{
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:134:10: ( '.' | '0' .. '9' | '-' | 'E' )+
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
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:
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
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:135:7: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:135:9: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:135:30: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')||(LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:136:4: ( ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+ )
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:136:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
			{
			// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:136:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
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
					// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:
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

	@Override
	public void mTokens() throws RecognitionException {
		// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:8: ( T__7 | T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | NUMBER | IDENT | WS )
		int alt4=32;
		alt4 = dfa4.predict(input);
		switch (alt4) {
			case 1 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:10: T__7
				{
				mT__7(); 

				}
				break;
			case 2 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:15: T__8
				{
				mT__8(); 

				}
				break;
			case 3 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:20: T__9
				{
				mT__9(); 

				}
				break;
			case 4 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:25: T__10
				{
				mT__10(); 

				}
				break;
			case 5 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:31: T__11
				{
				mT__11(); 

				}
				break;
			case 6 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:37: T__12
				{
				mT__12(); 

				}
				break;
			case 7 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:43: T__13
				{
				mT__13(); 

				}
				break;
			case 8 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:49: T__14
				{
				mT__14(); 

				}
				break;
			case 9 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:55: T__15
				{
				mT__15(); 

				}
				break;
			case 10 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:61: T__16
				{
				mT__16(); 

				}
				break;
			case 11 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:67: T__17
				{
				mT__17(); 

				}
				break;
			case 12 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:73: T__18
				{
				mT__18(); 

				}
				break;
			case 13 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:79: T__19
				{
				mT__19(); 

				}
				break;
			case 14 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:85: T__20
				{
				mT__20(); 

				}
				break;
			case 15 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:91: T__21
				{
				mT__21(); 

				}
				break;
			case 16 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:97: T__22
				{
				mT__22(); 

				}
				break;
			case 17 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:103: T__23
				{
				mT__23(); 

				}
				break;
			case 18 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:109: T__24
				{
				mT__24(); 

				}
				break;
			case 19 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:115: T__25
				{
				mT__25(); 

				}
				break;
			case 20 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:121: T__26
				{
				mT__26(); 

				}
				break;
			case 21 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:127: T__27
				{
				mT__27(); 

				}
				break;
			case 22 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:133: T__28
				{
				mT__28(); 

				}
				break;
			case 23 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:139: T__29
				{
				mT__29(); 

				}
				break;
			case 24 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:145: T__30
				{
				mT__30(); 

				}
				break;
			case 25 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:151: T__31
				{
				mT__31(); 

				}
				break;
			case 26 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:157: T__32
				{
				mT__32(); 

				}
				break;
			case 27 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:163: T__33
				{
				mT__33(); 

				}
				break;
			case 28 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:169: T__34
				{
				mT__34(); 

				}
				break;
			case 29 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:175: T__35
				{
				mT__35(); 

				}
				break;
			case 30 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:181: NUMBER
				{
				mNUMBER(); 

				}
				break;
			case 31 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:188: IDENT
				{
				mIDENT(); 

				}
				break;
			case 32 :
				// /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:194: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA4 dfa4 = new DFA4(this);
	static final String DFA4_eotS =
		"\10\uffff\1\36\1\uffff\4\33\2\uffff\10\33\1\uffff\1\32\5\uffff\16\33\1"+
		"\32\12\33\1\106\1\107\14\33\2\uffff\1\33\1\125\10\33\1\137\2\33\1\uffff"+
		"\11\33\1\uffff\10\33\1\164\4\33\1\171\2\33\1\174\3\33\1\uffff\2\33\1\uffff"+
		"\1\33\1\uffff\2\33\1\uffff\6\33\1\u008b\4\33\1\uffff\2\33\1\uffff\1\33"+
		"\1\u0093\2\33\1\uffff\2\33\1\uffff\1\33\2\uffff\1\33\1\uffff\2\33\1\u009c"+
		"\1\uffff";
	static final String DFA4_eofS =
		"\u009d\uffff";
	static final String DFA4_minS =
		"\1\11\7\uffff\1\75\1\uffff\1\116\3\117\2\uffff\1\154\1\145\1\161\1\141"+
		"\1\155\2\141\1\162\1\uffff\1\60\5\uffff\1\101\1\116\1\115\1\114\1\167"+
		"\2\146\1\165\1\154\1\160\1\170\1\156\1\164\1\165\1\60\1\114\1\104\1\101"+
		"\1\111\2\141\1\146\1\151\1\163\1\154\2\60\1\151\1\145\1\131\2\111\1\103"+
		"\1\171\1\165\1\145\1\166\1\145\1\151\2\uffff\1\163\1\60\1\123\1\124\1"+
		"\116\1\111\1\163\1\154\1\162\1\141\1\60\1\145\1\146\1\uffff\1\105\1\111"+
		"\1\137\1\105\2\137\1\164\1\145\1\154\1\uffff\1\163\1\151\1\123\1\117\3"+
		"\123\1\146\1\60\1\156\1\145\1\77\1\141\1\60\1\116\1\120\1\60\1\105\1\141"+
		"\1\162\1\uffff\1\164\1\156\1\uffff\1\142\1\uffff\1\123\1\105\1\uffff\1"+
		"\124\1\154\1\165\1\77\1\164\1\154\1\60\1\103\1\123\1\163\1\145\1\uffff"+
		"\1\77\1\145\1\uffff\1\111\1\60\1\145\1\77\1\uffff\1\77\1\106\1\uffff\1"+
		"\77\2\uffff\1\111\1\uffff\1\103\1\123\1\60\1\uffff";
	static final String DFA4_maxS =
		"\1\174\7\uffff\1\75\1\uffff\1\116\3\117\2\uffff\1\154\1\151\1\161\1\141"+
		"\1\155\1\151\1\141\1\162\1\uffff\1\172\5\uffff\1\101\1\116\1\115\1\114"+
		"\1\167\2\146\1\165\1\154\1\160\1\170\1\156\1\164\1\165\1\172\1\114\1\104"+
		"\1\101\1\111\2\141\1\146\1\151\1\163\1\154\2\172\1\151\1\145\1\131\2\111"+
		"\1\103\1\171\1\165\1\145\1\166\1\145\1\151\2\uffff\1\163\1\172\1\123\1"+
		"\124\1\116\1\131\1\163\1\154\1\162\1\141\1\172\1\145\1\146\1\uffff\1\105"+
		"\1\111\1\137\1\105\2\137\1\164\1\145\1\154\1\uffff\1\163\1\151\1\123\1"+
		"\117\3\123\1\164\1\172\1\156\1\145\1\77\1\141\1\172\1\116\1\120\1\172"+
		"\1\105\1\141\1\162\1\uffff\1\164\1\156\1\uffff\1\142\1\uffff\1\123\1\105"+
		"\1\uffff\1\124\1\154\1\165\1\77\1\164\1\154\1\172\1\103\1\123\1\163\1"+
		"\145\1\uffff\1\77\1\145\1\uffff\1\111\1\172\1\145\1\77\1\uffff\1\77\1"+
		"\106\1\uffff\1\77\2\uffff\1\111\1\uffff\1\103\1\123\1\172\1\uffff";
	static final String DFA4_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\uffff\1\12\4\uffff\1\20\1\21\10"+
		"\uffff\1\35\1\uffff\1\36\1\37\1\40\1\11\1\10\47\uffff\1\31\1\32\15\uffff"+
		"\1\34\11\uffff\1\27\24\uffff\1\24\2\uffff\1\30\1\uffff\1\13\2\uffff\1"+
		"\16\13\uffff\1\25\2\uffff\1\14\4\uffff\1\26\2\uffff\1\17\1\uffff\1\23"+
		"\1\33\1\uffff\1\22\3\uffff\1\15";
	static final String DFA4_specialS =
		"\u009d\uffff}>";
	static final String[] DFA4_transitionS = {
			"\2\34\1\uffff\2\34\22\uffff\1\34\1\1\4\uffff\1\2\1\uffff\1\3\1\4\1\5"+
			"\1\6\1\7\2\32\1\uffff\12\32\2\uffff\1\10\1\11\3\uffff\1\12\1\33\1\13"+
			"\1\14\1\31\12\33\1\15\12\33\1\16\1\uffff\1\17\3\uffff\1\20\2\33\1\21"+
			"\1\22\1\23\2\33\1\24\3\33\1\25\5\33\1\26\1\27\6\33\1\uffff\1\30",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\35",
			"",
			"\1\37",
			"\1\40",
			"\1\41",
			"\1\42",
			"",
			"",
			"\1\43",
			"\1\44\3\uffff\1\45",
			"\1\46",
			"\1\47",
			"\1\50",
			"\1\51\7\uffff\1\52",
			"\1\53",
			"\1\54",
			"",
			"\12\55\7\uffff\4\33\1\55\25\33\4\uffff\1\33\1\uffff\32\33",
			"",
			"",
			"",
			"",
			"",
			"\1\56",
			"\1\57",
			"\1\60",
			"\1\61",
			"\1\62",
			"\1\63",
			"\1\64",
			"\1\65",
			"\1\66",
			"\1\67",
			"\1\70",
			"\1\71",
			"\1\72",
			"\1\73",
			"\12\55\7\uffff\4\33\1\55\25\33\4\uffff\1\33\1\uffff\32\33",
			"\1\74",
			"\1\75",
			"\1\76",
			"\1\77",
			"\1\100",
			"\1\101",
			"\1\102",
			"\1\103",
			"\1\104",
			"\1\105",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\110",
			"\1\111",
			"\1\112",
			"\1\113",
			"\1\114",
			"\1\115",
			"\1\116",
			"\1\117",
			"\1\120",
			"\1\121",
			"\1\122",
			"\1\123",
			"",
			"",
			"\1\124",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\126",
			"\1\127",
			"\1\130",
			"\1\131\17\uffff\1\132",
			"\1\133",
			"\1\134",
			"\1\135",
			"\1\136",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\140",
			"\1\141",
			"",
			"\1\142",
			"\1\143",
			"\1\144",
			"\1\145",
			"\1\146",
			"\1\147",
			"\1\150",
			"\1\151",
			"\1\152",
			"",
			"\1\153",
			"\1\154",
			"\1\155",
			"\1\156",
			"\1\157",
			"\1\160",
			"\1\161",
			"\1\162\15\uffff\1\163",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\165",
			"\1\166",
			"\1\167",
			"\1\170",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\172",
			"\1\173",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\175",
			"\1\176",
			"\1\177",
			"",
			"\1\u0080",
			"\1\u0081",
			"",
			"\1\u0082",
			"",
			"\1\u0083",
			"\1\u0084",
			"",
			"\1\u0085",
			"\1\u0086",
			"\1\u0087",
			"\1\u0088",
			"\1\u0089",
			"\1\u008a",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\u008c",
			"\1\u008d",
			"\1\u008e",
			"\1\u008f",
			"",
			"\1\u0090",
			"\1\u0091",
			"",
			"\1\u0092",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\u0094",
			"\1\u0095",
			"",
			"\1\u0096",
			"\1\u0097",
			"",
			"\1\u0098",
			"",
			"",
			"\1\u0099",
			"",
			"\1\u009a",
			"\1\u009b",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
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
			return "1:1: Tokens : ( T__7 | T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | NUMBER | IDENT | WS );";
		}
	}

}
