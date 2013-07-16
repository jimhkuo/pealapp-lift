// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-07-16 10:19:28

package peal.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PealProgramLexer extends Lexer {
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
    public static final int NEWLINE=5;
    public static final int NUMBER=6;
    public static final int OPERATOR=7;
    public static final int WS=8;

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

    // $ANTLR start "T__9"
    public final void mT__9() throws RecognitionException {
        try {
            int _type = T__9;
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
    // $ANTLR end "T__9"

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:15:7: ( ')' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:15:9: ')'
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
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
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
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
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
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
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
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
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
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
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
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
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
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:23:7: ( 'DOMAIN_SPECIFICS' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:23:9: 'DOMAIN_SPECIFICS'
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:24:7: ( 'always_false?' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:24:9: 'always_false?'
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
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:25:7: ( 'always_true?' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:25:9: 'always_true?'
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
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:26:7: ( 'default' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:26:9: 'default'
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
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:27:7: ( 'different?' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:27:9: 'different?'
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
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:28:7: ( 'equivalent?' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:28:9: 'equivalent?'
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
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:29:7: ( 'max' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:29:9: 'max'
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
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:30:7: ( 'min' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:30:9: 'min'
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
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:31:7: ( 'satisfiable?' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:31:9: 'satisfiable?'
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
    // $ANTLR end "T__26"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:85:8: ( ( '.' | '0' .. '9' | '-' | 'E' )+ )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:85:10: ( '.' | '0' .. '9' | '-' | 'E' )+
            {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:85:10: ( '.' | '0' .. '9' | '-' | 'E' )+
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

    // $ANTLR start "OPERATOR"
    public final void mOPERATOR() throws RecognitionException {
        try {
            int _type = OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:86:10: ( ( '>' | '>=' | '<' | '<=' | '+' | '=' | '(' | ')' ) )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:86:12: ( '>' | '>=' | '<' | '<=' | '+' | '=' | '(' | ')' )
            {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:86:12: ( '>' | '>=' | '<' | '<=' | '+' | '=' | '(' | ')' )
            int alt2=8;
            switch ( input.LA(1) ) {
            case '>':
                {
                int LA2_1 = input.LA(2);

                if ( (LA2_1=='=') ) {
                    alt2=2;
                }
                else {
                    alt2=1;
                }
                }
                break;
            case '<':
                {
                int LA2_2 = input.LA(2);

                if ( (LA2_2=='=') ) {
                    alt2=4;
                }
                else {
                    alt2=3;
                }
                }
                break;
            case '+':
                {
                alt2=5;
                }
                break;
            case '=':
                {
                alt2=6;
                }
                break;
            case '(':
                {
                alt2=7;
                }
                break;
            case ')':
                {
                alt2=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:86:13: '>'
                    {
                    match('>'); 

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:86:19: '>='
                    {
                    match(">="); 



                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:86:26: '<'
                    {
                    match('<'); 

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:86:32: '<='
                    {
                    match("<="); 



                    }
                    break;
                case 5 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:86:39: '+'
                    {
                    match('+'); 

                    }
                    break;
                case 6 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:86:45: '='
                    {
                    match('='); 

                    }
                    break;
                case 7 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:86:51: '('
                    {
                    match('('); 

                    }
                    break;
                case 8 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:86:57: ')'
                    {
                    match(')'); 

                    }
                    break;

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
    // $ANTLR end "OPERATOR"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:87:8: ( ( '\\r' )? '\\n' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:87:9: ( '\\r' )? '\\n'
            {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:87:9: ( '\\r' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\r') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:87:9: '\\r'
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:88:7: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:88:9: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:88:30: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '0' && LA4_0 <= '9')||(LA4_0 >= 'A' && LA4_0 <= 'Z')||(LA4_0 >= 'a' && LA4_0 <= 'z')) ) {
                    alt4=1;
                }


                switch (alt4) {
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
            	    break loop4;
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:89:4: ( ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+ )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:89:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
            {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:89:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0 >= '\t' && LA5_0 <= '\n')||(LA5_0 >= '\f' && LA5_0 <= '\r')||LA5_0==' ') ) {
                    alt5=1;
                }


                switch (alt5) {
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
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
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
        // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:8: ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | NUMBER | OPERATOR | NEWLINE | IDENT | WS )
        int alt6=23;
        alt6 = dfa6.predict(input);
        switch (alt6) {
            case 1 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:10: T__9
                {
                mT__9(); 


                }
                break;
            case 2 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:15: T__10
                {
                mT__10(); 


                }
                break;
            case 3 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:21: T__11
                {
                mT__11(); 


                }
                break;
            case 4 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:27: T__12
                {
                mT__12(); 


                }
                break;
            case 5 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:33: T__13
                {
                mT__13(); 


                }
                break;
            case 6 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:39: T__14
                {
                mT__14(); 


                }
                break;
            case 7 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:45: T__15
                {
                mT__15(); 


                }
                break;
            case 8 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:51: T__16
                {
                mT__16(); 


                }
                break;
            case 9 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:57: T__17
                {
                mT__17(); 


                }
                break;
            case 10 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:63: T__18
                {
                mT__18(); 


                }
                break;
            case 11 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:69: T__19
                {
                mT__19(); 


                }
                break;
            case 12 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:75: T__20
                {
                mT__20(); 


                }
                break;
            case 13 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:81: T__21
                {
                mT__21(); 


                }
                break;
            case 14 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:87: T__22
                {
                mT__22(); 


                }
                break;
            case 15 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:93: T__23
                {
                mT__23(); 


                }
                break;
            case 16 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:99: T__24
                {
                mT__24(); 


                }
                break;
            case 17 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:105: T__25
                {
                mT__25(); 


                }
                break;
            case 18 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:111: T__26
                {
                mT__26(); 


                }
                break;
            case 19 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:117: NUMBER
                {
                mNUMBER(); 


                }
                break;
            case 20 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:124: OPERATOR
                {
                mOPERATOR(); 


                }
                break;
            case 21 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:133: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 22 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:141: IDENT
                {
                mIDENT(); 


                }
                break;
            case 23 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:147: WS
                {
                mWS(); 


                }
                break;

        }

    }


    protected DFA6 dfa6 = new DFA6(this);
    static final String DFA6_eotS =
        "\6\uffff\1\32\1\uffff\7\24\1\23\1\uffff\1\25\1\46\11\uffff\11\24"+
        "\1\23\2\uffff\6\24\1\67\1\70\7\24\2\uffff\20\24\2\uffff\1\122\3"+
        "\24\1\126\3\uffff\3\24\1\uffff\3\24\1\uffff\2\24\1\uffff\1\24\1"+
        "\uffff";
    static final String DFA6_eofS =
        "\140\uffff";
    static final String DFA6_minS =
        "\1\11\5\uffff\1\75\1\uffff\1\116\1\117\1\154\1\145\1\161\2\141\1"+
        "\60\1\uffff\1\12\1\11\11\uffff\1\101\1\115\1\167\2\146\1\165\1\170"+
        "\1\156\1\164\1\60\2\uffff\1\114\1\101\2\141\1\146\1\151\2\60\1\151"+
        "\1\131\1\111\1\171\1\165\1\145\1\166\2\uffff\1\163\1\123\1\116\1"+
        "\163\1\154\1\162\1\141\1\146\1\105\2\137\1\164\1\145\1\154\1\151"+
        "\1\123\1\uffff\1\146\1\60\1\156\1\145\1\141\1\60\3\uffff\1\164\1"+
        "\156\1\142\1\uffff\1\77\1\164\1\154\1\uffff\1\77\1\145\1\uffff\1"+
        "\77\1\uffff";
    static final String DFA6_maxS =
        "\1\172\5\uffff\1\75\1\uffff\1\116\1\117\1\154\1\151\1\161\1\151"+
        "\1\141\1\172\1\uffff\1\12\1\40\11\uffff\1\101\1\115\1\167\2\146"+
        "\1\165\1\170\1\156\1\164\1\172\2\uffff\1\114\1\101\2\141\1\146\1"+
        "\151\2\172\1\151\1\131\1\111\1\171\1\165\1\145\1\166\2\uffff\1\163"+
        "\1\123\1\116\1\163\1\154\1\162\1\141\1\146\1\105\2\137\1\164\1\145"+
        "\1\154\1\151\1\123\1\uffff\1\164\1\172\1\156\1\145\1\141\1\172\3"+
        "\uffff\1\164\1\156\1\142\1\uffff\1\77\1\164\1\154\1\uffff\1\77\1"+
        "\145\1\uffff\1\77\1\uffff";
    static final String DFA6_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\uffff\1\10\10\uffff\1\24\2\uffff"+
        "\1\23\1\26\1\27\1\1\1\2\1\4\1\7\1\6\1\10\12\uffff\1\25\1\7\17\uffff"+
        "\1\20\1\21\20\uffff\1\12\6\uffff\1\13\1\14\1\15\3\uffff\1\11\3\uffff"+
        "\1\16\2\uffff\1\17\1\uffff\1\22";
    static final String DFA6_specialS =
        "\140\uffff}>";
    static final String[] DFA6_transitionS = {
            "\1\25\1\22\1\uffff\1\25\1\21\22\uffff\1\25\7\uffff\1\1\1\2\1"+
            "\3\1\4\1\5\2\23\1\uffff\12\23\2\uffff\1\6\1\7\1\20\2\uffff\1"+
            "\10\2\24\1\11\1\17\25\24\6\uffff\1\12\2\24\1\13\1\14\7\24\1"+
            "\15\5\24\1\16\7\24",
            "",
            "",
            "",
            "",
            "",
            "\1\31",
            "",
            "\1\34",
            "\1\35",
            "\1\36",
            "\1\37\3\uffff\1\40",
            "\1\41",
            "\1\42\7\uffff\1\43",
            "\1\44",
            "\12\45\7\uffff\4\24\1\45\25\24\6\uffff\32\24",
            "",
            "\1\22",
            "\2\25\1\uffff\2\25\22\uffff\1\25",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\50",
            "\1\51",
            "\1\52",
            "\1\53",
            "\1\54",
            "\1\55",
            "\1\56",
            "\1\57",
            "\1\60",
            "\12\45\7\uffff\4\24\1\45\25\24\6\uffff\32\24",
            "",
            "",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\12\24\7\uffff\32\24\6\uffff\32\24",
            "\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "",
            "",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\1\104",
            "\1\105",
            "\1\106",
            "\1\107",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "",
            "\1\120\15\uffff\1\121",
            "\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\123",
            "\1\124",
            "\1\125",
            "\12\24\7\uffff\32\24\6\uffff\32\24",
            "",
            "",
            "",
            "\1\127",
            "\1\130",
            "\1\131",
            "",
            "\1\132",
            "\1\133",
            "\1\134",
            "",
            "\1\135",
            "\1\136",
            "",
            "\1\137",
            ""
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | NUMBER | OPERATOR | NEWLINE | IDENT | WS );";
        }
    }
 

}