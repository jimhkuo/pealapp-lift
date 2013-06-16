// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-06-16 08:38:01

package peal.antlr;

import org.antlr.runtime.*;
import peal.MaxLessThanTh;
import peal.MinLessThanTh;
import peal.PolLessThanTh;
import peal.domain.Pol;
import peal.domain.Predicate;
import peal.domain.Rule;
import peal.synthesis.TopSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PealProgramParser extends Parser {
    public static final String[] tokenNames = new String[]{
            "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMPARE", "IDENT", "NEWLINE", "NUMBER", "WS", "'('", "')'", "'+'", "','", "'<='", "'='", "'cond'", "'default'", "'max'", "'min'"
    };

    public static final int EOF = -1;
    public static final int T__9 = 9;
    public static final int T__10 = 10;
    public static final int T__11 = 11;
    public static final int T__12 = 12;
    public static final int T__13 = 13;
    public static final int T__14 = 14;
    public static final int T__15 = 15;
    public static final int T__16 = 16;
    public static final int T__17 = 17;
    public static final int T__18 = 18;
    public static final int COMPARE = 4;
    public static final int IDENT = 5;
    public static final int NEWLINE = 6;
    public static final int NUMBER = 7;
    public static final int WS = 8;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[]{};
    }

    // delegators


    public PealProgramParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }

    public PealProgramParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() {
        return PealProgramParser.tokenNames;
    }

    public String getGrammarFileName() {
        return "/Users/jkuo/PealApp-lift/antlr/PealProgram.g";
    }


    Map<String, Pol> pols = new HashMap<String, Pol>();
    List<Rule> l = new ArrayList<Rule>();
    public TopSet pSet = null;

    //need to override the default error reporting
    @Override
    public void reportError(RecognitionException e) {
        throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames));
    }


    // $ANTLR start "program"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:41:1: program : 'cond' '=' id1= IDENT '<=' NUMBER (id5= IDENT '=' pol )* (id2= IDENT '=' 'max' '(' id3= IDENT ',' id4= IDENT ')' |id2= IDENT '=' 'min' '(' id3= IDENT ',' id4= IDENT ')' |id2= IDENT '=' id3= IDENT ) ;
    public final void program() throws RecognitionException {
        Token id1 = null;
        Token id5 = null;
        Token id2 = null;
        Token id3 = null;
        Token id4 = null;
        Token NUMBER2 = null;
        Pol pol1 = null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:41:9: ( 'cond' '=' id1= IDENT '<=' NUMBER (id5= IDENT '=' pol )* (id2= IDENT '=' 'max' '(' id3= IDENT ',' id4= IDENT ')' |id2= IDENT '=' 'min' '(' id3= IDENT ',' id4= IDENT ')' |id2= IDENT '=' id3= IDENT ) )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:41:11: 'cond' '=' id1= IDENT '<=' NUMBER (id5= IDENT '=' pol )* (id2= IDENT '=' 'max' '(' id3= IDENT ',' id4= IDENT ')' |id2= IDENT '=' 'min' '(' id3= IDENT ',' id4= IDENT ')' |id2= IDENT '=' id3= IDENT )
            {
                match(input, 15, FOLLOW_15_in_program50);

                match(input, 14, FOLLOW_14_in_program52);

                id1 = (Token) match(input, IDENT, FOLLOW_IDENT_in_program56);

                match(input, 13, FOLLOW_13_in_program58);

                NUMBER2 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_program60);

                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:42:3: (id5= IDENT '=' pol )*
                loop1:
                do {
                    int alt1 = 2;
                    int LA1_0 = input.LA(1);

                    if ((LA1_0 == IDENT)) {
                        int LA1_1 = input.LA(2);

                        if ((LA1_1 == 14)) {
                            switch (input.LA(3)) {
                                case 17: {
                                    int LA1_3 = input.LA(4);

                                    if ((LA1_3 == 9)) {
                                        int LA1_7 = input.LA(5);

                                        if (((LA1_7 >= 9 && LA1_7 <= 10))) {
                                            alt1 = 1;
                                        }


                                    }


                                }
                                break;
                                case 18: {
                                    int LA1_4 = input.LA(4);

                                    if ((LA1_4 == 9)) {
                                        int LA1_8 = input.LA(5);

                                        if (((LA1_8 >= 9 && LA1_8 <= 10))) {
                                            alt1 = 1;
                                        }


                                    }


                                }
                                break;
                                case 11: {
                                    alt1 = 1;
                                }
                                break;

                            }

                        }


                    }


                    switch (alt1) {
                        case 1:
                            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:42:4: id5= IDENT '=' pol
                        {
                            id5 = (Token) match(input, IDENT, FOLLOW_IDENT_in_program68);

                            match(input, 14, FOLLOW_14_in_program70);

                            pushFollow(FOLLOW_pol_in_program72);
                            pol1 = pol();

                            state._fsp--;


                            pols.put((id5 != null ? id5.getText() : null), pol1);

                        }
                        break;

                        default:
                            break loop1;
                    }
                } while (true);


                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:43:3: (id2= IDENT '=' 'max' '(' id3= IDENT ',' id4= IDENT ')' |id2= IDENT '=' 'min' '(' id3= IDENT ',' id4= IDENT ')' |id2= IDENT '=' id3= IDENT )
                int alt2 = 3;
                int LA2_0 = input.LA(1);

                if ((LA2_0 == IDENT)) {
                    int LA2_1 = input.LA(2);

                    if ((LA2_1 == 14)) {
                        switch (input.LA(3)) {
                            case 17: {
                                alt2 = 1;
                            }
                            break;
                            case 18: {
                                alt2 = 2;
                            }
                            break;
                            case IDENT: {
                                alt2 = 3;
                            }
                            break;
                            default:
                                NoViableAltException nvae =
                                        new NoViableAltException("", 2, 2, input);

                                throw nvae;

                        }

                    } else {
                        NoViableAltException nvae =
                                new NoViableAltException("", 2, 1, input);

                        throw nvae;

                    }
                } else {
                    NoViableAltException nvae =
                            new NoViableAltException("", 2, 0, input);

                    throw nvae;

                }
                switch (alt2) {
                    case 1:
                        // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:44:3: id2= IDENT '=' 'max' '(' id3= IDENT ',' id4= IDENT ')'
                    {
                        id2 = (Token) match(input, IDENT, FOLLOW_IDENT_in_program86);

                        match(input, 14, FOLLOW_14_in_program88);

                        match(input, 17, FOLLOW_17_in_program90);

                        match(input, 9, FOLLOW_9_in_program92);

                        id3 = (Token) match(input, IDENT, FOLLOW_IDENT_in_program96);

                        match(input, 12, FOLLOW_12_in_program98);

                        id4 = (Token) match(input, IDENT, FOLLOW_IDENT_in_program102);

                        match(input, 10, FOLLOW_10_in_program104);

                        pSet = new MaxLessThanTh(pols.get((id3 != null ? id3.getText() : null)), pols.get((id4 != null ? id4.getText() : null)), Double.valueOf((NUMBER2 != null ? NUMBER2.getText() : null)));

                    }
                    break;
                    case 2:
                        // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:3: id2= IDENT '=' 'min' '(' id3= IDENT ',' id4= IDENT ')'
                    {
                        id2 = (Token) match(input, IDENT, FOLLOW_IDENT_in_program122);

                        match(input, 14, FOLLOW_14_in_program124);

                        match(input, 18, FOLLOW_18_in_program126);

                        match(input, 9, FOLLOW_9_in_program128);

                        id3 = (Token) match(input, IDENT, FOLLOW_IDENT_in_program132);

                        match(input, 12, FOLLOW_12_in_program134);

                        id4 = (Token) match(input, IDENT, FOLLOW_IDENT_in_program138);

                        match(input, 10, FOLLOW_10_in_program140);

                        pSet = new MinLessThanTh(pols.get((id3 != null ? id3.getText() : null)), pols.get((id4 != null ? id4.getText() : null)), Double.valueOf((NUMBER2 != null ? NUMBER2.getText() : null)));

                    }
                    break;
                    case 3:
                        // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:50:3: id2= IDENT '=' id3= IDENT
                    {
                        id2 = (Token) match(input, IDENT, FOLLOW_IDENT_in_program155);

                        match(input, 14, FOLLOW_14_in_program157);

                        id3 = (Token) match(input, IDENT, FOLLOW_IDENT_in_program161);

                        pSet = new PolLessThanTh(pols.get((id3 != null ? id3.getText() : null)), Double.valueOf((NUMBER2 != null ? NUMBER2.getText() : null)));

                    }
                    break;

                }


            }

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        } finally {
            // do for sure before leaving
        }
        return;
    }
    // $ANTLR end "program"


    // $ANTLR start "pol"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:55:1: pol returns [Pol p] : ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER );
    public final Pol pol() throws RecognitionException {
        Pol p = null;


        Token NUMBER4 = null;
        Token NUMBER6 = null;
        Token NUMBER8 = null;
        Rule rule3 = null;

        Rule rule5 = null;

        Rule rule7 = null;


        l = new ArrayList<Rule>();
        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:57:2: ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER )
            int alt6 = 3;
            switch (input.LA(1)) {
                case 11: {
                    alt6 = 1;
                }
                break;
                case 17: {
                    alt6 = 2;
                }
                break;
                case 18: {
                    alt6 = 3;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 6, 0, input);

                    throw nvae;

            }

            switch (alt6) {
                case 1:
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:57:5: '+' '(' ( rule )* ')' 'default' NUMBER
                {
                    match(input, 11, FOLLOW_11_in_pol193);

                    match(input, 9, FOLLOW_9_in_pol195);

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:57:13: ( rule )*
                    loop3:
                    do {
                        int alt3 = 2;
                        int LA3_0 = input.LA(1);

                        if ((LA3_0 == 9)) {
                            alt3 = 1;
                        }


                        switch (alt3) {
                            case 1:
                                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:57:14: rule
                            {
                                pushFollow(FOLLOW_rule_in_pol198);
                                rule3 = rule();

                                state._fsp--;


                                l.add(rule3);

                            }
                            break;

                            default:
                                break loop3;
                        }
                    } while (true);


                    match(input, 10, FOLLOW_10_in_pol204);

                    match(input, 16, FOLLOW_16_in_pol206);

                    NUMBER4 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_pol208);

                    p = new Pol(l, Double.valueOf((NUMBER4 != null ? NUMBER4.getText() : null)));

                }
                break;
                case 2:
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:58:4: 'max' '(' ( rule )* ')' 'default' NUMBER
                {
                    match(input, 17, FOLLOW_17_in_pol215);

                    match(input, 9, FOLLOW_9_in_pol217);

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:58:14: ( rule )*
                    loop4:
                    do {
                        int alt4 = 2;
                        int LA4_0 = input.LA(1);

                        if ((LA4_0 == 9)) {
                            alt4 = 1;
                        }


                        switch (alt4) {
                            case 1:
                                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:58:15: rule
                            {
                                pushFollow(FOLLOW_rule_in_pol220);
                                rule5 = rule();

                                state._fsp--;


                                l.add(rule5);

                            }
                            break;

                            default:
                                break loop4;
                        }
                    } while (true);


                    match(input, 10, FOLLOW_10_in_pol226);

                    match(input, 16, FOLLOW_16_in_pol228);

                    NUMBER6 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_pol230);

                    p = new Pol(l, Double.valueOf((NUMBER6 != null ? NUMBER6.getText() : null)));

                }
                break;
                case 3:
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:4: 'min' '(' ( rule )* ')' 'default' NUMBER
                {
                    match(input, 18, FOLLOW_18_in_pol237);

                    match(input, 9, FOLLOW_9_in_pol239);

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:14: ( rule )*
                    loop5:
                    do {
                        int alt5 = 2;
                        int LA5_0 = input.LA(1);

                        if ((LA5_0 == 9)) {
                            alt5 = 1;
                        }


                        switch (alt5) {
                            case 1:
                                // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:15: rule
                            {
                                pushFollow(FOLLOW_rule_in_pol242);
                                rule7 = rule();

                                state._fsp--;


                                l.add(rule7);

                            }
                            break;

                            default:
                                break loop5;
                        }
                    } while (true);


                    match(input, 10, FOLLOW_10_in_pol248);

                    match(input, 16, FOLLOW_16_in_pol250);

                    NUMBER8 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_pol252);

                    p = new Pol(l, Double.valueOf((NUMBER8 != null ? NUMBER8.getText() : null)));

                }
                break;

            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        } finally {
            // do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "pol"


    // $ANTLR start "rule"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:62:1: rule returns [Rule r] : '(' IDENT NUMBER ')' ;
    public final Rule rule() throws RecognitionException {
        Rule r = null;


        Token IDENT9 = null;
        Token NUMBER10 = null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:63:2: ( '(' IDENT NUMBER ')' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:63:4: '(' IDENT NUMBER ')'
            {
                match(input, 9, FOLLOW_9_in_rule271);

                IDENT9 = (Token) match(input, IDENT, FOLLOW_IDENT_in_rule273);

                NUMBER10 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_rule275);

                match(input, 10, FOLLOW_10_in_rule277);

                r = new Rule(new Predicate((IDENT9 != null ? IDENT9.getText() : null), ""), Double.valueOf((NUMBER10 != null ? NUMBER10.getText() : null)));

            }

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        } finally {
            // do for sure before leaving
        }
        return r;
    }
    // $ANTLR end "rule"

    // Delegated rules


    public static final BitSet FOLLOW_15_in_program50 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program52 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program56 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_program58 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_program60 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program68 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program70 = new BitSet(new long[]{0x0000000000060800L});
    public static final BitSet FOLLOW_pol_in_program72 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program86 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program88 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_program90 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_program92 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program96 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_program98 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program102 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_program104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_program122 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program124 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_program126 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_program128 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program132 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_program134 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program138 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_program140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_program155 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program157 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_pol193 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol195 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol198 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol204 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pol206 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_pol215 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol217 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol220 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol226 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pol228 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pol237 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol239 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol242 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol248 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pol250 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule271 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_rule273 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_rule275 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule277 = new BitSet(new long[]{0x0000000000000002L});

}