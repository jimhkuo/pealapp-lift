// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/Peal.g 2013-06-03 11:18:20

package peal.antlr;


import org.antlr.runtime.*;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PealParser extends Parser {
    public static final String[] tokenNames = new String[]{
            "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMPARE", "IDENT", "NUMBER", "WS", "'+'", "'<'", "'<='", "'='", "'default'", "'if'", "'max'", "'min'"
    };

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
    public Parser[] getDelegates() {
        return new Parser[]{};
    }

    // delegators


    public PealParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }

    public PealParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() {
        return PealParser.tokenNames;
    }

    public String getGrammarFileName() {
        return "/Users/jkuo/PealApp-lift/antlr/Peal.g";
    }


    // $ANTLR start "pred"
    // /Users/jkuo/PealApp-lift/antlr/Peal.g:15:1: pred returns [Boolean e] : (id1= IDENT '=' id2= IDENT |id3= IDENT );
    public final Boolean pred() throws RecognitionException {
        Boolean e = null;


        Token id1 = null;
        Token id2 = null;
        Token id3 = null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:16:2: (id1= IDENT '=' id2= IDENT |id3= IDENT )
            int alt1 = 2;
            int LA1_0 = input.LA(1);

            if ((LA1_0 == IDENT)) {
                int LA1_1 = input.LA(2);

                if ((LA1_1 == 11)) {
                    alt1 = 1;
                } else if ((LA1_1 == NUMBER)) {
                    alt1 = 2;
                } else {
                    NoViableAltException nvae =
                            new NoViableAltException("", 1, 1, input);

                    throw nvae;

                }
            } else {
                NoViableAltException nvae =
                        new NoViableAltException("", 1, 0, input);

                throw nvae;

            }
            switch (alt1) {
                case 1:
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:16:4: id1= IDENT '=' id2= IDENT
                {
                    id1 = (Token) match(input, IDENT, FOLLOW_IDENT_in_pred44);

                    match(input, 11, FOLLOW_11_in_pred46);

                    id2 = (Token) match(input, IDENT, FOLLOW_IDENT_in_pred50);

                }
                break;
                case 2:
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:17:4: id3= IDENT
                {
                    id3 = (Token) match(input, IDENT, FOLLOW_IDENT_in_pred58);

                }
                break;

            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        } finally {
            // do for sure before leaving
        }
        return e;
    }
    // $ANTLR end "pred"


    // $ANTLR start "rule"
    // /Users/jkuo/PealApp-lift/antlr/Peal.g:20:1: rule returns [Integer i] : 'if' pred NUMBER ;
    public final Integer rule() throws RecognitionException {
        Integer i = null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:21:2: ( 'if' pred NUMBER )
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:21:4: 'if' pred NUMBER
            {
                match(input, 13, FOLLOW_13_in_rule73);

                pushFollow(FOLLOW_pred_in_rule75);
                pred();

                state._fsp--;


                match(input, NUMBER, FOLLOW_NUMBER_in_rule77);

            }

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        } finally {
            // do for sure before leaving
        }
        return i;
    }
    // $ANTLR end "rule"


    // $ANTLR start "pol"
    // /Users/jkuo/PealApp-lift/antlr/Peal.g:24:1: pol returns [Integer i] : ( '+' ( rule )* 'default' NUMBER | 'max' ( rule )* 'default' NUMBER | 'min' ( rule )* 'default' NUMBER );
    public final Integer pol() throws RecognitionException {
        Integer i = null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:25:2: ( '+' ( rule )* 'default' NUMBER | 'max' ( rule )* 'default' NUMBER | 'min' ( rule )* 'default' NUMBER )
            int alt5 = 3;
            switch (input.LA(1)) {
                case 8: {
                    alt5 = 1;
                }
                break;
                case 14: {
                    alt5 = 2;
                }
                break;
                case 15: {
                    alt5 = 3;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 5, 0, input);

                    throw nvae;

            }

            switch (alt5) {
                case 1:
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:25:4: '+' ( rule )* 'default' NUMBER
                {
                    match(input, 8, FOLLOW_8_in_pol92);

                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:25:8: ( rule )*
                    loop2:
                    do {
                        int alt2 = 2;
                        int LA2_0 = input.LA(1);

                        if ((LA2_0 == 13)) {
                            alt2 = 1;
                        }


                        switch (alt2) {
                            case 1:
                                // /Users/jkuo/PealApp-lift/antlr/Peal.g:25:9: rule
                            {
                                pushFollow(FOLLOW_rule_in_pol95);
                                rule();

                                state._fsp--;


                            }
                            break;

                            default:
                                break loop2;
                        }
                    } while (true);


                    match(input, 12, FOLLOW_12_in_pol99);

                    match(input, NUMBER, FOLLOW_NUMBER_in_pol101);

                }
                break;
                case 2:
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:26:4: 'max' ( rule )* 'default' NUMBER
                {
                    match(input, 14, FOLLOW_14_in_pol106);

                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:26:10: ( rule )*
                    loop3:
                    do {
                        int alt3 = 2;
                        int LA3_0 = input.LA(1);

                        if ((LA3_0 == 13)) {
                            alt3 = 1;
                        }


                        switch (alt3) {
                            case 1:
                                // /Users/jkuo/PealApp-lift/antlr/Peal.g:26:11: rule
                            {
                                pushFollow(FOLLOW_rule_in_pol109);
                                rule();

                                state._fsp--;


                            }
                            break;

                            default:
                                break loop3;
                        }
                    } while (true);


                    match(input, 12, FOLLOW_12_in_pol113);

                    match(input, NUMBER, FOLLOW_NUMBER_in_pol115);

                }
                break;
                case 3:
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:27:4: 'min' ( rule )* 'default' NUMBER
                {
                    match(input, 15, FOLLOW_15_in_pol120);

                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:27:10: ( rule )*
                    loop4:
                    do {
                        int alt4 = 2;
                        int LA4_0 = input.LA(1);

                        if ((LA4_0 == 13)) {
                            alt4 = 1;
                        }


                        switch (alt4) {
                            case 1:
                                // /Users/jkuo/PealApp-lift/antlr/Peal.g:27:11: rule
                            {
                                pushFollow(FOLLOW_rule_in_pol123);
                                rule();

                                state._fsp--;


                            }
                            break;

                            default:
                                break loop4;
                        }
                    } while (true);


                    match(input, 12, FOLLOW_12_in_pol127);

                    match(input, NUMBER, FOLLOW_NUMBER_in_pol129);

                }
                break;

            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        } finally {
            // do for sure before leaving
        }
        return i;
    }
    // $ANTLR end "pol"


    // $ANTLR start "pSet"
    // /Users/jkuo/PealApp-lift/antlr/Peal.g:30:1: pSet returns [Integer i] : pol ;
    public final Integer pSet() throws RecognitionException {
        Integer i = null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:31:2: ( pol )
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:31:4: pol
            {
                pushFollow(FOLLOW_pol_in_pSet144);
                pol();

                state._fsp--;


            }

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        } finally {
            // do for sure before leaving
        }
        return i;
    }
    // $ANTLR end "pSet"


    // $ANTLR start "cond"
    // /Users/jkuo/PealApp-lift/antlr/Peal.g:34:1: cond returns [Boolean e] : ( NUMBER '<' pSet | pSet '<=' NUMBER );
    public final Boolean cond() throws RecognitionException {
        Boolean e = null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:35:2: ( NUMBER '<' pSet | pSet '<=' NUMBER )
            int alt6 = 2;
            int LA6_0 = input.LA(1);

            if ((LA6_0 == NUMBER)) {
                alt6 = 1;
            } else if ((LA6_0 == 8 || (LA6_0 >= 14 && LA6_0 <= 15))) {
                alt6 = 2;
            } else {
                NoViableAltException nvae =
                        new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1:
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:35:4: NUMBER '<' pSet
                {
                    match(input, NUMBER, FOLLOW_NUMBER_in_cond160);

                    match(input, 9, FOLLOW_9_in_cond162);

                    pushFollow(FOLLOW_pSet_in_cond164);
                    pSet();

                    state._fsp--;


                }
                break;
                case 2:
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:36:4: pSet '<=' NUMBER
                {
                    pushFollow(FOLLOW_pSet_in_cond169);
                    pSet();

                    state._fsp--;


                    match(input, 10, FOLLOW_10_in_cond171);

                    match(input, NUMBER, FOLLOW_NUMBER_in_cond173);

                }
                break;

            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        } finally {
            // do for sure before leaving
        }
        return e;
    }
    // $ANTLR end "cond"

    // Delegated rules


    public static final BitSet FOLLOW_IDENT_in_pred44 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_pred46 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pred50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pred58 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule73 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_pred_in_rule75 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_rule77 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_pol92 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_rule_in_pol95 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_12_in_pol99 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_pol101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_pol106 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_rule_in_pol109 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_12_in_pol113 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_pol115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_pol120 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_rule_in_pol123 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_12_in_pol127 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_pol129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pol_in_pSet144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_cond160 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_cond162 = new BitSet(new long[]{0x000000000000C100L});
    public static final BitSet FOLLOW_pSet_in_cond164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pSet_in_cond169 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_cond171 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_cond173 = new BitSet(new long[]{0x0000000000000002L});

}