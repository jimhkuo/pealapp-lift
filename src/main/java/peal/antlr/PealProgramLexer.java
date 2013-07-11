// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-07-11 11:28:11

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
    public static final int COMPARE=4;
    public static final int IDENT=5;
    public static final int NEWLINE=6;
    public static final int NUMBER=7;
    public static final int WS=8;

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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:11:6: ( '(' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:11:8: '('
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:12:7: ( ')' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:12:9: ')'
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:13:7: ( '*' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:13:9: '*'
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:14:7: ( '+' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:14:9: '+'
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:15:7: ( ',' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:15:9: ','
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:16:7: ( '<' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:16:9: '<'
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:17:7: ( '<=' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:17:9: '<='
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:18:7: ( '=' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:18:9: '='
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:19:7: ( 'ANALYSES' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:19:9: 'ANALYSES'
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:20:7: ( 'default' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:20:9: 'default'
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
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:21:7: ( 'max' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:21:9: 'max'
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
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:22:7: ( 'min' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:22:9: 'min'
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
    // $ANTLR end "T__20"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:70:8: ( ( '.' | '0' .. '9' | '-' | 'E' )+ )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:70:10: ( '.' | '0' .. '9' | '-' | 'E' )+
            {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:70:10: ( '.' | '0' .. '9' | '-' | 'E' )+
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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "COMPARE"
    public final void mCOMPARE() throws RecognitionException {
        try {
            int _type = COMPARE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:9: ( ( '>' | '>=' | '<' | '<=' ) )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:11: ( '>' | '>=' | '<' | '<=' )
            {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:11: ( '>' | '>=' | '<' | '<=' )
            int alt2=4;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='>') ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1=='=') ) {
                    alt2=2;
                }
                else {
                    alt2=1;
                }
            }
            else if ( (LA2_0=='<') ) {
                int LA2_2 = input.LA(2);

                if ( (LA2_2=='=') ) {
                    alt2=4;
                }
                else {
                    alt2=3;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:12: '>'
                    {
                    match('>'); 

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:18: '>='
                    {
                    match(">="); 



                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:25: '<'
                    {
                    match('<'); 

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:31: '<='
                    {
                    match("<="); 



                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMPARE"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:8: ( ( '\\r' )? '\\n' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:9: ( '\\r' )? '\\n'
            {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:9: ( '\\r' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\r') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:9: '\\r'
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:73:7: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:73:9: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:73:30: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:4: ( ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+ )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
            {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
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
        // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:8: ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | NUMBER | COMPARE | NEWLINE | IDENT | WS )
        int alt6=17;
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
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:81: NUMBER
                {
                mNUMBER(); 


                }
                break;
            case 14 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:88: COMPARE
                {
                mCOMPARE(); 


                }
                break;
            case 15 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:96: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 16 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:104: IDENT
                {
                mIDENT(); 


                }
                break;
            case 17 :
                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:1:110: WS
                {
                mWS(); 


                }
                break;

        }

    }


    protected DFA6 dfa6 = new DFA6(this);
    static final String DFA6_eotS =
        "\6\uffff\1\23\1\uffff\3\20\1\17\1\uffff\1\21\1\31\5\uffff\4\20\1"+
        "\17\2\uffff\2\20\1\41\1\42\2\20\2\uffff\5\20\1\52\1\53\2\uffff";
    static final String DFA6_eofS =
        "\54\uffff";
    static final String DFA6_minS =
        "\1\11\5\uffff\1\75\1\uffff\1\116\1\145\1\141\1\60\1\uffff\1\12\1"+
        "\11\5\uffff\1\101\1\146\1\170\1\156\1\60\2\uffff\1\114\1\141\2\60"+
        "\1\131\1\165\2\uffff\1\123\1\154\1\105\1\164\1\123\2\60\2\uffff";
    static final String DFA6_maxS =
        "\1\172\5\uffff\1\75\1\uffff\1\116\1\145\1\151\1\172\1\uffff\1\12"+
        "\1\40\5\uffff\1\101\1\146\1\170\1\156\1\172\2\uffff\1\114\1\141"+
        "\2\172\1\131\1\165\2\uffff\1\123\1\154\1\105\1\164\1\123\2\172\2"+
        "\uffff";
    static final String DFA6_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\uffff\1\10\4\uffff\1\16\2\uffff\1"+
        "\15\1\20\1\21\1\7\1\6\5\uffff\1\17\1\7\6\uffff\1\13\1\14\7\uffff"+
        "\1\12\1\11";
    static final String DFA6_specialS =
        "\54\uffff}>";
    static final String[] DFA6_transitionS = {
            "\1\21\1\16\1\uffff\1\21\1\15\22\uffff\1\21\7\uffff\1\1\1\2\1"+
            "\3\1\4\1\5\2\17\1\uffff\12\17\2\uffff\1\6\1\7\1\14\2\uffff\1"+
            "\10\3\20\1\13\25\20\6\uffff\3\20\1\11\10\20\1\12\15\20",
            "",
            "",
            "",
            "",
            "",
            "\1\22",
            "",
            "\1\24",
            "\1\25",
            "\1\26\7\uffff\1\27",
            "\12\30\7\uffff\4\20\1\30\25\20\6\uffff\32\20",
            "",
            "\1\16",
            "\2\21\1\uffff\2\21\22\uffff\1\21",
            "",
            "",
            "",
            "",
            "",
            "\1\33",
            "\1\34",
            "\1\35",
            "\1\36",
            "\12\30\7\uffff\4\20\1\30\25\20\6\uffff\32\20",
            "",
            "",
            "\1\37",
            "\1\40",
            "\12\20\7\uffff\32\20\6\uffff\32\20",
            "\12\20\7\uffff\32\20\6\uffff\32\20",
            "\1\43",
            "\1\44",
            "",
            "",
            "\1\45",
            "\1\46",
            "\1\47",
            "\1\50",
            "\1\51",
            "\12\20\7\uffff\32\20\6\uffff\32\20",
            "\12\20\7\uffff\32\20\6\uffff\32\20",
            "",
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
            return "1:1: Tokens : ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | NUMBER | COMPARE | NEWLINE | IDENT | WS );";
        }
    }
 

}