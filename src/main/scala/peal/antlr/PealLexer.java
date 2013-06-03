// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/Peal.g 2013-06-03 11:18:20

package peal.antlr;


import org.antlr.runtime.*;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PealLexer extends Lexer {
    public static final int EOF = -1;
    public static final int T__8 = 8;
    public static final int T__9 = 9;
    public static final int T__10 = 10;
    public static final int T__11 = 11;
    public static final int T__12 = 12;
    public static final int T__13 = 13;
    public static final int T__14 = 14;
    public static final int T__15 = 15;
    public static final int COMPARE = 4;
    public static final int IDENT = 5;
    public static final int NUMBER = 6;
    public static final int WS = 7;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[]{};
    }

    public PealLexer() {
    }

    public PealLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }

    public PealLexer(CharStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String getGrammarFileName() {
        return "/Users/jkuo/PealApp-lift/antlr/Peal.g";
    }

    // $ANTLR start "T__8"
    public final void mT__8() throws RecognitionException {
        try {
            int _type = T__8;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:11:6: ( '+' )
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:11:8: '+'
            {
                match('+');

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__8"

    // $ANTLR start "T__9"
    public final void mT__9() throws RecognitionException {
        try {
            int _type = T__9;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:12:6: ( '<' )
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:12:8: '<'
            {
                match('<');

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__9"

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:13:7: ( '<=' )
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:13:9: '<='
            {
                match("<=");


            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__10"

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:14:7: ( '=' )
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:14:9: '='
            {
                match('=');

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:15:7: ( 'default' )
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:15:9: 'default'
            {
                match("default");


            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:16:7: ( 'if' )
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:16:9: 'if'
            {
                match("if");


            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:17:7: ( 'max' )
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:17:9: 'max'
            {
                match("max");


            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:18:7: ( 'min' )
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:18:9: 'min'
            {
                match("min");


            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:39:8: ( ( '.' | '0' .. '9' | '-' | 'E' )+ )
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:39:10: ( '.' | '0' .. '9' | '-' | 'E' )+
            {
                // /Users/jkuo/PealApp-lift/antlr/Peal.g:39:10: ( '.' | '0' .. '9' | '-' | 'E' )+
                int cnt1 = 0;
                loop1:
                do {
                    int alt1 = 2;
                    int LA1_0 = input.LA(1);

                    if (((LA1_0 >= '-' && LA1_0 <= '.') || (LA1_0 >= '0' && LA1_0 <= '9') || LA1_0 == 'E')) {
                        alt1 = 1;
                    }


                    switch (alt1) {
                        case 1:
                            // /Users/jkuo/PealApp-lift/antlr/Peal.g:
                        {
                            if ((input.LA(1) >= '-' && input.LA(1) <= '.') || (input.LA(1) >= '0' && input.LA(1) <= '9') || input.LA(1) == 'E') {
                                input.consume();
                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            if (cnt1 >= 1) break loop1;
                            EarlyExitException eee =
                                    new EarlyExitException(1, input);
                            throw eee;
                    }
                    cnt1++;
                } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "COMPARE"
    public final void mCOMPARE() throws RecognitionException {
        try {
            int _type = COMPARE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:40:9: ( ( '>' | '>=' | '<' | '<=' ) )
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:40:11: ( '>' | '>=' | '<' | '<=' )
            {
                // /Users/jkuo/PealApp-lift/antlr/Peal.g:40:11: ( '>' | '>=' | '<' | '<=' )
                int alt2 = 4;
                int LA2_0 = input.LA(1);

                if ((LA2_0 == '>')) {
                    int LA2_1 = input.LA(2);

                    if ((LA2_1 == '=')) {
                        alt2 = 2;
                    } else {
                        alt2 = 1;
                    }
                } else if ((LA2_0 == '<')) {
                    int LA2_2 = input.LA(2);

                    if ((LA2_2 == '=')) {
                        alt2 = 4;
                    } else {
                        alt2 = 3;
                    }
                } else {
                    NoViableAltException nvae =
                            new NoViableAltException("", 2, 0, input);

                    throw nvae;

                }
                switch (alt2) {
                    case 1:
                        // /Users/jkuo/PealApp-lift/antlr/Peal.g:40:12: '>'
                    {
                        match('>');

                    }
                    break;
                    case 2:
                        // /Users/jkuo/PealApp-lift/antlr/Peal.g:40:18: '>='
                    {
                        match(">=");


                    }
                    break;
                    case 3:
                        // /Users/jkuo/PealApp-lift/antlr/Peal.g:40:25: '<'
                    {
                        match('<');

                    }
                    break;
                    case 4:
                        // /Users/jkuo/PealApp-lift/antlr/Peal.g:40:31: '<='
                    {
                        match("<=");


                    }
                    break;

                }


            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "COMPARE"

    // $ANTLR start "IDENT"
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:41:7: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:41:9: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            {
                if ((input.LA(1) >= 'A' && input.LA(1) <= 'Z') || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
                    input.consume();
                } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }


                // /Users/jkuo/PealApp-lift/antlr/Peal.g:41:30: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
                loop3:
                do {
                    int alt3 = 2;
                    int LA3_0 = input.LA(1);

                    if (((LA3_0 >= '0' && LA3_0 <= '9') || (LA3_0 >= 'A' && LA3_0 <= 'Z') || (LA3_0 >= 'a' && LA3_0 <= 'z'))) {
                        alt3 = 1;
                    }


                    switch (alt3) {
                        case 1:
                            // /Users/jkuo/PealApp-lift/antlr/Peal.g:
                        {
                            if ((input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
                                input.consume();
                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            break loop3;
                    }
                } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "IDENT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:42:4: ( ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+ )
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:42:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
            {
                // /Users/jkuo/PealApp-lift/antlr/Peal.g:42:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
                int cnt4 = 0;
                loop4:
                do {
                    int alt4 = 2;
                    int LA4_0 = input.LA(1);

                    if (((LA4_0 >= '\t' && LA4_0 <= '\n') || (LA4_0 >= '\f' && LA4_0 <= '\r') || LA4_0 == ' ')) {
                        alt4 = 1;
                    }


                    switch (alt4) {
                        case 1:
                            // /Users/jkuo/PealApp-lift/antlr/Peal.g:
                        {
                            if ((input.LA(1) >= '\t' && input.LA(1) <= '\n') || (input.LA(1) >= '\f' && input.LA(1) <= '\r') || input.LA(1) == ' ') {
                                input.consume();
                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            if (cnt4 >= 1) break loop4;
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
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // /Users/jkuo/PealApp-lift/antlr/Peal.g:1:8: ( T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | NUMBER | COMPARE | IDENT | WS )
        int alt5 = 12;
        alt5 = dfa5.predict(input);
        switch (alt5) {
            case 1:
                // /Users/jkuo/PealApp-lift/antlr/Peal.g:1:10: T__8
            {
                mT__8();


            }
            break;
            case 2:
                // /Users/jkuo/PealApp-lift/antlr/Peal.g:1:15: T__9
            {
                mT__9();


            }
            break;
            case 3:
                // /Users/jkuo/PealApp-lift/antlr/Peal.g:1:20: T__10
            {
                mT__10();


            }
            break;
            case 4:
                // /Users/jkuo/PealApp-lift/antlr/Peal.g:1:26: T__11
            {
                mT__11();


            }
            break;
            case 5:
                // /Users/jkuo/PealApp-lift/antlr/Peal.g:1:32: T__12
            {
                mT__12();


            }
            break;
            case 6:
                // /Users/jkuo/PealApp-lift/antlr/Peal.g:1:38: T__13
            {
                mT__13();


            }
            break;
            case 7:
                // /Users/jkuo/PealApp-lift/antlr/Peal.g:1:44: T__14
            {
                mT__14();


            }
            break;
            case 8:
                // /Users/jkuo/PealApp-lift/antlr/Peal.g:1:50: T__15
            {
                mT__15();


            }
            break;
            case 9:
                // /Users/jkuo/PealApp-lift/antlr/Peal.g:1:56: NUMBER
            {
                mNUMBER();


            }
            break;
            case 10:
                // /Users/jkuo/PealApp-lift/antlr/Peal.g:1:63: COMPARE
            {
                mCOMPARE();


            }
            break;
            case 11:
                // /Users/jkuo/PealApp-lift/antlr/Peal.g:1:71: IDENT
            {
                mIDENT();


            }
            break;
            case 12:
                // /Users/jkuo/PealApp-lift/antlr/Peal.g:1:77: WS
            {
                mWS();


            }
            break;

        }

    }


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
            "\2\uffff\1\15\1\uffff\3\12\1\11\6\uffff\1\12\1\25\2\12\1\11\1\uffff" +
                    "\1\12\1\uffff\1\31\1\32\1\12\2\uffff\2\12\1\36\1\uffff";
    static final String DFA5_eofS =
            "\37\uffff";
    static final String DFA5_minS =
            "\1\11\1\uffff\1\75\1\uffff\1\145\1\146\1\141\1\60\6\uffff\1\146" +
                    "\1\60\1\170\1\156\1\60\1\uffff\1\141\1\uffff\2\60\1\165\2\uffff" +
                    "\1\154\1\164\1\60\1\uffff";
    static final String DFA5_maxS =
            "\1\172\1\uffff\1\75\1\uffff\1\145\1\146\1\151\1\172\6\uffff\1\146" +
                    "\1\172\1\170\1\156\1\172\1\uffff\1\141\1\uffff\2\172\1\165\2\uffff" +
                    "\1\154\1\164\1\172\1\uffff";
    static final String DFA5_acceptS =
            "\1\uffff\1\1\1\uffff\1\4\4\uffff\1\12\1\11\1\13\1\14\1\3\1\2\5\uffff" +
                    "\1\3\1\uffff\1\6\3\uffff\1\7\1\10\3\uffff\1\5";
    static final String DFA5_specialS =
            "\37\uffff}>";
    static final String[] DFA5_transitionS = {
            "\2\13\1\uffff\2\13\22\uffff\1\13\12\uffff\1\1\1\uffff\2\11\1" +
                    "\uffff\12\11\2\uffff\1\2\1\3\1\10\2\uffff\4\12\1\7\25\12\6\uffff" +
                    "\3\12\1\4\4\12\1\5\3\12\1\6\15\12",
            "",
            "\1\14",
            "",
            "\1\16",
            "\1\17",
            "\1\20\7\uffff\1\21",
            "\12\22\7\uffff\4\12\1\22\25\12\6\uffff\32\12",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\24",
            "\12\12\7\uffff\32\12\6\uffff\32\12",
            "\1\26",
            "\1\27",
            "\12\22\7\uffff\4\12\1\22\25\12\6\uffff\32\12",
            "",
            "\1\30",
            "",
            "\12\12\7\uffff\32\12\6\uffff\32\12",
            "\12\12\7\uffff\32\12\6\uffff\32\12",
            "\1\33",
            "",
            "",
            "\1\34",
            "\1\35",
            "\12\12\7\uffff\32\12\6\uffff\32\12",
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
        for (int i = 0; i < numStates; i++) {
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
            return "1:1: Tokens : ( T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | NUMBER | COMPARE | IDENT | WS );";
        }
    }


}