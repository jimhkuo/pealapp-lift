// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-07-26 14:23:49

package peal.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PealProgramLexer extends Lexer {
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
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int IDENT=4;
    public static final int NEWLINE=5;
    public static final int NUMBER=6;
    public static final int WS=7;

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
    public String getGrammarFileName() { return "/Users/jkuo/PealApp-lift/antlr/PealProgram.g"; }

    // $ANTLR start "T__8"
    public final void mT__8() throws RecognitionException {
        try {
            int _type = T__8;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:14:6: ( '(' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:14:8: '('
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:15:6: ( ')' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:15:8: ')'
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
    // $ANTLR end "T__9"

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:16:7: ( '*' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:16:9: '*'
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
    // $ANTLR end "T__10"

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:17:7: ( '+' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:17:9: '+'
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
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:18:7: ( ',' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:18:9: ','
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
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:19:7: ( '<' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:19:9: '<'
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
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:20:7: ( '<=' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:20:9: '<='
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
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:21:7: ( '=' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:21:9: '='
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
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:22:7: ( 'ANALYSES' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:22:9: 'ANALYSES'
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
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:23:7: ( 'CONDITIONS' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:23:9: 'CONDITIONS'
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
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:24:7: ( 'DOMAIN_SPECIFICS' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:24:9: 'DOMAIN_SPECIFICS'
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
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:25:7: ( 'POLICIES' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:25:9: 'POLICIES'
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
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:26:7: ( 'POLICY_SETS' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:26:9: 'POLICY_SETS'
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
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:27:7: ( 'always_false?' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:27:9: 'always_false?'
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
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:28:7: ( 'always_true?' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:28:9: 'always_true?'
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
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:29:7: ( 'default' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:29:9: 'default'
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
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:30:7: ( 'different?' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:30:9: 'different?'
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
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:31:7: ( 'equivalent?' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:31:9: 'equivalent?'
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
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:32:7: ( 'max' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:32:9: 'max'
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
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:33:7: ( 'min' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:33:9: 'min'
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
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:34:7: ( 'satisfiable?' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:34:9: 'satisfiable?'
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
    // $ANTLR end "T__28"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:90:8: ( ( '.' | '0' .. '9' | '-' | 'E' )+ )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:90:10: ( '.' | '0' .. '9' | '-' | 'E' )+
            {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:90:10: ( '.' | '0' .. '9' | '-' | 'E' )+
            int cnt1=0;
            loop1:
            do {
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
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


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

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:91:8: ( ( '\\r' )? '\\n' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:91:9: ( '\\r' )? '\\n'
            {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:91:9: ( '\\r' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='\r') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:91:9: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

             _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "IDENT"
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:92:7: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:92:9: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:92:30: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= '0' && LA3_0 <= '9')||(LA3_0 >= 'A' && LA3_0 <= 'Z')||(LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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
            	    break loop3;
                }
            } while (true);


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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:93:4: ( ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+ )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:93:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
            {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:93:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '\t' && LA4_0 <= '\n')||(LA4_0 >= '\f' && LA4_0 <= '\r')||LA4_0==' ') ) {
                    alt4=1;
                }


                switch (alt4) {
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
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


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

    public void mTokens() throws RecognitionException {
        // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:8: ( T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | NUMBER | NEWLINE | IDENT | WS )
        int alt5=25;
        alt5 = dfa5.predict(input);
        switch (alt5) {
            case 1 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:10: T__8
                {
                mT__8(); 


                }
                break;
            case 2 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:15: T__9
                {
                mT__9(); 


                }
                break;
            case 3 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:20: T__10
                {
                mT__10(); 


                }
                break;
            case 4 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:26: T__11
                {
                mT__11(); 


                }
                break;
            case 5 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:32: T__12
                {
                mT__12(); 


                }
                break;
            case 6 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:38: T__13
                {
                mT__13(); 


                }
                break;
            case 7 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:44: T__14
                {
                mT__14(); 


                }
                break;
            case 8 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:50: T__15
                {
                mT__15(); 


                }
                break;
            case 9 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:56: T__16
                {
                mT__16(); 


                }
                break;
            case 10 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:62: T__17
                {
                mT__17(); 


                }
                break;
            case 11 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:68: T__18
                {
                mT__18(); 


                }
                break;
            case 12 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:74: T__19
                {
                mT__19(); 


                }
                break;
            case 13 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:80: T__20
                {
                mT__20(); 


                }
                break;
            case 14 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:86: T__21
                {
                mT__21(); 


                }
                break;
            case 15 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:92: T__22
                {
                mT__22(); 


                }
                break;
            case 16 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:98: T__23
                {
                mT__23(); 


                }
                break;
            case 17 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:104: T__24
                {
                mT__24(); 


                }
                break;
            case 18 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:110: T__25
                {
                mT__25(); 


                }
                break;
            case 19 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:116: T__26
                {
                mT__26(); 


                }
                break;
            case 20 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:122: T__27
                {
                mT__27(); 


                }
                break;
            case 21 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:128: T__28
                {
                mT__28(); 


                }
                break;
            case 22 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:134: NUMBER
                {
                mNUMBER(); 


                }
                break;
            case 23 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:141: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 24 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:149: IDENT
                {
                mIDENT(); 


                }
                break;
            case 25 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:155: WS
                {
                mWS(); 


                }
                break;

        }

    }


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\6\uffff\1\30\1\uffff\11\25\1\24\1\26\1\45\5\uffff\13\25\1\24\1"+
        "\uffff\10\25\1\71\1\72\11\25\2\uffff\26\25\1\uffff\1\25\2\uffff"+
        "\1\136\3\25\1\142\1\25\1\144\3\uffff\3\25\1\uffff\1\25\1\uffff\3"+
        "\25\1\154\1\uffff\2\25\2\uffff\1\25\1\uffff";
    static final String DFA5_eofS =
        "\160\uffff";
    static final String DFA5_minS =
        "\1\11\5\uffff\1\75\1\uffff\1\116\3\117\1\154\1\145\1\161\2\141\1"+
        "\60\1\12\1\11\5\uffff\1\101\1\116\1\115\1\114\1\167\2\146\1\165"+
        "\1\170\1\156\1\164\1\60\1\uffff\1\114\1\104\1\101\1\111\2\141\1"+
        "\146\1\151\2\60\1\151\1\131\2\111\1\103\1\171\1\165\1\145\1\166"+
        "\2\uffff\1\163\1\123\1\124\1\116\1\111\1\163\1\154\1\162\1\141\1"+
        "\146\1\105\1\111\1\137\1\105\2\137\1\164\1\145\1\154\1\151\1\123"+
        "\1\117\1\uffff\1\123\1\uffff\1\146\1\60\1\156\1\145\1\141\1\60\1"+
        "\116\1\60\3\uffff\1\164\1\156\1\142\1\uffff\1\123\1\uffff\1\77\1"+
        "\164\1\154\1\60\1\uffff\1\77\1\145\2\uffff\1\77\1\uffff";
    static final String DFA5_maxS =
        "\1\172\5\uffff\1\75\1\uffff\1\116\3\117\1\154\1\151\1\161\1\151"+
        "\1\141\1\172\1\12\1\40\5\uffff\1\101\1\116\1\115\1\114\1\167\2\146"+
        "\1\165\1\170\1\156\1\164\1\172\1\uffff\1\114\1\104\1\101\1\111\2"+
        "\141\1\146\1\151\2\172\1\151\1\131\2\111\1\103\1\171\1\165\1\145"+
        "\1\166\2\uffff\1\163\1\123\1\124\1\116\1\131\1\163\1\154\1\162\1"+
        "\141\1\146\1\105\1\111\1\137\1\105\2\137\1\164\1\145\1\154\1\151"+
        "\1\123\1\117\1\uffff\1\123\1\uffff\1\164\1\172\1\156\1\145\1\141"+
        "\1\172\1\116\1\172\3\uffff\1\164\1\156\1\142\1\uffff\1\123\1\uffff"+
        "\1\77\1\164\1\154\1\172\1\uffff\1\77\1\145\2\uffff\1\77\1\uffff";
    static final String DFA5_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\uffff\1\10\14\uffff\1\26\1\30\1\31"+
        "\1\7\1\6\14\uffff\1\27\23\uffff\1\23\1\24\26\uffff\1\13\1\uffff"+
        "\1\15\10\uffff\1\16\1\17\1\20\3\uffff\1\11\1\uffff\1\14\4\uffff"+
        "\1\21\2\uffff\1\12\1\22\1\uffff\1\25";
    static final String DFA5_specialS =
        "\160\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\26\1\23\1\uffff\1\26\1\22\22\uffff\1\26\7\uffff\1\1\1\2\1"+
            "\3\1\4\1\5\2\24\1\uffff\12\24\2\uffff\1\6\1\7\3\uffff\1\10\1"+
            "\25\1\11\1\12\1\21\12\25\1\13\12\25\6\uffff\1\14\2\25\1\15\1"+
            "\16\7\25\1\17\5\25\1\20\7\25",
            "",
            "",
            "",
            "",
            "",
            "\1\27",
            "",
            "\1\31",
            "\1\32",
            "\1\33",
            "\1\34",
            "\1\35",
            "\1\36\3\uffff\1\37",
            "\1\40",
            "\1\41\7\uffff\1\42",
            "\1\43",
            "\12\44\7\uffff\4\25\1\44\25\25\6\uffff\32\25",
            "\1\23",
            "\2\26\1\uffff\2\26\22\uffff\1\26",
            "",
            "",
            "",
            "",
            "",
            "\1\46",
            "\1\47",
            "\1\50",
            "\1\51",
            "\1\52",
            "\1\53",
            "\1\54",
            "\1\55",
            "\1\56",
            "\1\57",
            "\1\60",
            "\12\44\7\uffff\4\25\1\44\25\25\6\uffff\32\25",
            "",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\12\25\7\uffff\32\25\6\uffff\32\25",
            "\12\25\7\uffff\32\25\6\uffff\32\25",
            "\1\73",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "",
            "",
            "\1\104",
            "\1\105",
            "\1\106",
            "\1\107",
            "\1\110\17\uffff\1\111",
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
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
            "",
            "\1\133",
            "",
            "\1\134\15\uffff\1\135",
            "\12\25\7\uffff\32\25\6\uffff\32\25",
            "\1\137",
            "\1\140",
            "\1\141",
            "\12\25\7\uffff\32\25\6\uffff\32\25",
            "\1\143",
            "\12\25\7\uffff\32\25\6\uffff\32\25",
            "",
            "",
            "",
            "\1\145",
            "\1\146",
            "\1\147",
            "",
            "\1\150",
            "",
            "\1\151",
            "\1\152",
            "\1\153",
            "\12\25\7\uffff\32\25\6\uffff\32\25",
            "",
            "\1\155",
            "\1\156",
            "",
            "",
            "\1\157",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | NUMBER | NEWLINE | IDENT | WS );";
        }
    }
 

}