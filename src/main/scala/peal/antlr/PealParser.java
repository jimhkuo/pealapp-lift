// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/Peal.g 2013-06-07 11:47:31

package peal.antlr;
import peal.domain.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PealParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMPARE", "IDENT", "NUMBER", "WS", "'('", "')'", "'+'", "','", "'<'", "'<='", "'='", "'default'", "'if'", "'max'", "'min'"
    };

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
    public static final int COMPARE=4;
    public static final int IDENT=5;
    public static final int NUMBER=6;
    public static final int WS=7;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public PealParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public PealParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return PealParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/jkuo/PealApp-lift/antlr/Peal.g"; }



    // $ANTLR start "pred"
    // /Users/jkuo/PealApp-lift/antlr/Peal.g:16:1: pred returns [Double i] : (id1= IDENT '=' id2= IDENT |id3= IDENT );
    public final Double pred() throws RecognitionException {
        Double i = null;


        Token id1=null;
        Token id2=null;
        Token id3=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:17:2: (id1= IDENT '=' id2= IDENT |id3= IDENT )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==IDENT) ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1==14) ) {
                    alt1=1;
                }
                else if ( (LA1_1==NUMBER) ) {
                    alt1=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }
            switch (alt1) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:17:4: id1= IDENT '=' id2= IDENT
                    {
                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pred45); 

                    match(input,14,FOLLOW_14_in_pred47); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pred51); 

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:18:4: id3= IDENT
                    {
                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pred59); 

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
        return i;
    }
    // $ANTLR end "pred"



    // $ANTLR start "rule"
    // /Users/jkuo/PealApp-lift/antlr/Peal.g:21:1: rule returns [Double i] : '(' 'if' pred NUMBER ')' ;
    public final Double rule() throws RecognitionException {
        Double i = null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:22:2: ( '(' 'if' pred NUMBER ')' )
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:22:4: '(' 'if' pred NUMBER ')'
            {
            match(input,8,FOLLOW_8_in_rule74); 

            match(input,16,FOLLOW_16_in_rule76); 

            pushFollow(FOLLOW_pred_in_rule78);
            pred();

            state._fsp--;


            match(input,NUMBER,FOLLOW_NUMBER_in_rule80); 

            match(input,9,FOLLOW_9_in_rule82); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return i;
    }
    // $ANTLR end "rule"



    // $ANTLR start "cond"
    // /Users/jkuo/PealApp-lift/antlr/Peal.g:25:1: cond returns [Boolean e] : ( NUMBER '<' pSet | pSet '<=' NUMBER );
    public final Boolean cond() throws RecognitionException {
        Boolean e = null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:26:2: ( NUMBER '<' pSet | pSet '<=' NUMBER )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==NUMBER) ) {
                alt2=1;
            }
            else if ( (LA2_0==10||(LA2_0 >= 17 && LA2_0 <= 18)) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:26:4: NUMBER '<' pSet
                    {
                    match(input,NUMBER,FOLLOW_NUMBER_in_cond98); 

                    match(input,12,FOLLOW_12_in_cond100); 

                    pushFollow(FOLLOW_pSet_in_cond102);
                    pSet();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:27:4: pSet '<=' NUMBER
                    {
                    pushFollow(FOLLOW_pSet_in_cond107);
                    pSet();

                    state._fsp--;


                    match(input,13,FOLLOW_13_in_cond109); 

                    match(input,NUMBER,FOLLOW_NUMBER_in_cond111); 

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
        return e;
    }
    // $ANTLR end "cond"



    // $ANTLR start "pol"
    // /Users/jkuo/PealApp-lift/antlr/Peal.g:30:1: pol returns [Double i] : ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER );
    public final Double pol() throws RecognitionException {
        Double i = null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:31:2: ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER )
            int alt6=3;
            switch ( input.LA(1) ) {
            case 10:
                {
                alt6=1;
                }
                break;
            case 17:
                {
                alt6=2;
                }
                break;
            case 18:
                {
                alt6=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }

            switch (alt6) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:31:4: '+' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,10,FOLLOW_10_in_pol126); 

                    match(input,8,FOLLOW_8_in_pol128); 

                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:31:12: ( rule )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==8) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/Peal.g:31:13: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol131);
                    	    rule();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    match(input,9,FOLLOW_9_in_pol135); 

                    match(input,15,FOLLOW_15_in_pol137); 

                    match(input,NUMBER,FOLLOW_NUMBER_in_pol139); 

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:32:4: 'max' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,17,FOLLOW_17_in_pol144); 

                    match(input,8,FOLLOW_8_in_pol146); 

                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:32:14: ( rule )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==8) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/Peal.g:32:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol149);
                    	    rule();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    match(input,9,FOLLOW_9_in_pol153); 

                    match(input,15,FOLLOW_15_in_pol155); 

                    match(input,NUMBER,FOLLOW_NUMBER_in_pol157); 

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:33:4: 'min' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,18,FOLLOW_18_in_pol162); 

                    match(input,8,FOLLOW_8_in_pol164); 

                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:33:14: ( rule )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==8) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/Peal.g:33:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol167);
                    	    rule();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    match(input,9,FOLLOW_9_in_pol171); 

                    match(input,15,FOLLOW_15_in_pol173); 

                    match(input,NUMBER,FOLLOW_NUMBER_in_pol175); 

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
        return i;
    }
    // $ANTLR end "pol"



    // $ANTLR start "pSet"
    // /Users/jkuo/PealApp-lift/antlr/Peal.g:36:1: pSet : ( pol | 'max' '(' pol ',' pol ')' | 'min' '(' pol ',' pol ')' );
    public final void pSet() throws RecognitionException {
        try {
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:36:9: ( pol | 'max' '(' pol ',' pol ')' | 'min' '(' pol ',' pol ')' )
            int alt7=3;
            switch ( input.LA(1) ) {
            case 10:
                {
                alt7=1;
                }
                break;
            case 17:
                {
                int LA7_2 = input.LA(2);

                if ( (LA7_2==8) ) {
                    int LA7_4 = input.LA(3);

                    if ( ((LA7_4 >= 8 && LA7_4 <= 9)) ) {
                        alt7=1;
                    }
                    else if ( (LA7_4==10||(LA7_4 >= 17 && LA7_4 <= 18)) ) {
                        alt7=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 4, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 2, input);

                    throw nvae;

                }
                }
                break;
            case 18:
                {
                int LA7_3 = input.LA(2);

                if ( (LA7_3==8) ) {
                    int LA7_5 = input.LA(3);

                    if ( ((LA7_5 >= 8 && LA7_5 <= 9)) ) {
                        alt7=1;
                    }
                    else if ( (LA7_5==10||(LA7_5 >= 17 && LA7_5 <= 18)) ) {
                        alt7=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 5, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 3, input);

                    throw nvae;

                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }

            switch (alt7) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:36:11: pol
                    {
                    pushFollow(FOLLOW_pol_in_pSet188);
                    pol();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:37:4: 'max' '(' pol ',' pol ')'
                    {
                    match(input,17,FOLLOW_17_in_pSet194); 

                    match(input,8,FOLLOW_8_in_pSet196); 

                    pushFollow(FOLLOW_pol_in_pSet198);
                    pol();

                    state._fsp--;


                    match(input,11,FOLLOW_11_in_pSet200); 

                    pushFollow(FOLLOW_pol_in_pSet202);
                    pol();

                    state._fsp--;


                    match(input,9,FOLLOW_9_in_pSet204); 

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:38:4: 'min' '(' pol ',' pol ')'
                    {
                    match(input,18,FOLLOW_18_in_pSet209); 

                    match(input,8,FOLLOW_8_in_pSet211); 

                    pushFollow(FOLLOW_pol_in_pSet213);
                    pol();

                    state._fsp--;


                    match(input,11,FOLLOW_11_in_pSet215); 

                    pushFollow(FOLLOW_pol_in_pSet217);
                    pol();

                    state._fsp--;


                    match(input,9,FOLLOW_9_in_pSet219); 

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
        return ;
    }
    // $ANTLR end "pSet"

    // Delegated rules


 

    public static final BitSet FOLLOW_IDENT_in_pred45 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_pred47 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pred51 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pred59 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_rule74 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_rule76 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_pred_in_rule78 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_rule80 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_rule82 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_cond98 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_cond100 = new BitSet(new long[]{0x0000000000060400L});
    public static final BitSet FOLLOW_pSet_in_cond102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pSet_in_cond107 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_cond109 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_cond111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_pol126 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_pol128 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_rule_in_pol131 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_9_in_pol135 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_pol137 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_pol139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_pol144 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_pol146 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_rule_in_pol149 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_9_in_pol153 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_pol155 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_pol157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pol162 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_pol164 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_rule_in_pol167 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_9_in_pol171 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_pol173 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_pol175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pol_in_pSet188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_pSet194 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_pSet196 = new BitSet(new long[]{0x0000000000060400L});
    public static final BitSet FOLLOW_pol_in_pSet198 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_pSet200 = new BitSet(new long[]{0x0000000000060400L});
    public static final BitSet FOLLOW_pol_in_pSet202 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pSet209 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_pSet211 = new BitSet(new long[]{0x0000000000060400L});
    public static final BitSet FOLLOW_pol_in_pSet213 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_pSet215 = new BitSet(new long[]{0x0000000000060400L});
    public static final BitSet FOLLOW_pol_in_pSet217 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet219 = new BitSet(new long[]{0x0000000000000002L});

}