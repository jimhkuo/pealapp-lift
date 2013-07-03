// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-07-03 15:43:59

package peal.antlr;
import java.util.*;
import peal.domain.*;
import peal.*;
import org.antlr.runtime.BitSet;
import peal.synthesis.pSet;
import peal.domain.operator.*;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PealProgramParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMPARE", "IDENT", "NEWLINE", "NUMBER", "WS", "'('", "')'", "'*'", "'+'", "','", "'<='", "'='", "'cond'", "'default'", "'max'", "'min'"
    };

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
    public static final int COMPARE=4;
    public static final int IDENT=5;
    public static final int NEWLINE=6;
    public static final int NUMBER=7;
    public static final int WS=8;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public PealProgramParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public PealProgramParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return PealProgramParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/jkuo/PealApp-lift/antlr/PealProgram.g"; }


    Map<String, Pol> pols = new HashMap<String, Pol>();
    List<Rule> l = new ArrayList<Rule>();
    String n = null;
    public pSet pSet = null;

    //need to override the default error reporting
    @Override
    public void reportError(RecognitionException e) {
    	throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
    }




    // $ANTLR start "program"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:36:1: program : 'cond' '=' id0= IDENT '<=' num= NUMBER (id1= IDENT '=' pol )* id0= IDENT '=' pSet ;
    public final void program() throws RecognitionException {
        Token id0=null;
        Token num=null;
        Token id1=null;
        Pol pol1 =null;

        pSet pSet2 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:37:2: ( 'cond' '=' id0= IDENT '<=' num= NUMBER (id1= IDENT '=' pol )* id0= IDENT '=' pSet )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:37:4: 'cond' '=' id0= IDENT '<=' num= NUMBER (id1= IDENT '=' pol )* id0= IDENT '=' pSet
            {
            match(input,16,FOLLOW_16_in_program45); 

            match(input,15,FOLLOW_15_in_program47); 

            id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program51); 

            match(input,14,FOLLOW_14_in_program53); 

            num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program57); 

            n = (num!=null?num.getText():null);

            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:38:2: (id1= IDENT '=' pol )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==IDENT) ) {
                    int LA1_1 = input.LA(2);

                    if ( (LA1_1==15) ) {
                        switch ( input.LA(3) ) {
                        case 18:
                            {
                            int LA1_4 = input.LA(4);

                            if ( (LA1_4==9) ) {
                                int LA1_7 = input.LA(5);

                                if ( ((LA1_7 >= 9 && LA1_7 <= 10)) ) {
                                    alt1=1;
                                }


                            }


                            }
                            break;
                        case 19:
                            {
                            int LA1_5 = input.LA(4);

                            if ( (LA1_5==9) ) {
                                int LA1_8 = input.LA(5);

                                if ( ((LA1_8 >= 9 && LA1_8 <= 10)) ) {
                                    alt1=1;
                                }


                            }


                            }
                            break;
                        case 11:
                        case 12:
                            {
                            alt1=1;
                            }
                            break;

                        }

                    }


                }


                switch (alt1) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:38:3: id1= IDENT '=' pol
            	    {
            	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program65); 

            	    match(input,15,FOLLOW_15_in_program67); 

            	    pushFollow(FOLLOW_pol_in_program69);
            	    pol1=pol();

            	    state._fsp--;


            	    pols.put((id1!=null?id1.getText():null), pol1);

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program78); 

            match(input,15,FOLLOW_15_in_program80); 

            pushFollow(FOLLOW_pSet_in_program82);
            pSet2=pSet();

            state._fsp--;


             pSet = pSet2;

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
    // $ANTLR end "program"



    // $ANTLR start "pSet"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:42:1: pSet returns [pSet t] : (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' pSet ')' );
    public final pSet pSet() throws RecognitionException {
        pSet t = null;


        Token id1=null;
        Token id2=null;
        Token id3=null;
        pSet id4 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:43:2: (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' pSet ')' )
            int alt2=5;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt2=1;
                }
                break;
            case 18:
                {
                int LA2_2 = input.LA(2);

                if ( (LA2_2==9) ) {
                    int LA2_4 = input.LA(3);

                    if ( (LA2_4==IDENT) ) {
                        int LA2_6 = input.LA(4);

                        if ( (LA2_6==13) ) {
                            int LA2_8 = input.LA(5);

                            if ( (LA2_8==IDENT) ) {
                                int LA2_10 = input.LA(6);

                                if ( (LA2_10==10) ) {
                                    alt2=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 2, 10, input);

                                    throw nvae;

                                }
                            }
                            else if ( ((LA2_8 >= 18 && LA2_8 <= 19)) ) {
                                alt2=3;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 2, 8, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 2, 6, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 4, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 2, input);

                    throw nvae;

                }
                }
                break;
            case 19:
                {
                int LA2_3 = input.LA(2);

                if ( (LA2_3==9) ) {
                    int LA2_5 = input.LA(3);

                    if ( (LA2_5==IDENT) ) {
                        int LA2_7 = input.LA(4);

                        if ( (LA2_7==13) ) {
                            int LA2_9 = input.LA(5);

                            if ( (LA2_9==IDENT) ) {
                                int LA2_12 = input.LA(6);

                                if ( (LA2_12==10) ) {
                                    alt2=4;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 2, 12, input);

                                    throw nvae;

                                }
                            }
                            else if ( ((LA2_9 >= 18 && LA2_9 <= 19)) ) {
                                alt2=5;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 2, 9, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 2, 7, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 5, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 3, input);

                    throw nvae;

                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:43:4: id1= IDENT
                    {
                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet105); 

                    t = new PolLessThanTh(pols.get((id1!=null?id1.getText():null)), Double.valueOf(n));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:44:4: 'max' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,18,FOLLOW_18_in_pSet112); 

                    match(input,9,FOLLOW_9_in_pSet114); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet118); 

                    match(input,13,FOLLOW_13_in_pSet120); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet124); 

                    match(input,10,FOLLOW_10_in_pSet126); 

                    t = new MaxLessThanTh(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf(n));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:45:4: 'max' '(' id3= IDENT ',' id4= pSet ')'
                    {
                    match(input,18,FOLLOW_18_in_pSet133); 

                    match(input,9,FOLLOW_9_in_pSet135); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet139); 

                    match(input,13,FOLLOW_13_in_pSet141); 

                    pushFollow(FOLLOW_pSet_in_pSet145);
                    id4=pSet();

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet147); 

                    t = new MaxLessThanTh(pols.get((id3!=null?id3.getText():null)), id4, Double.valueOf(n));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:46:4: 'min' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,19,FOLLOW_19_in_pSet154); 

                    match(input,9,FOLLOW_9_in_pSet156); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet160); 

                    match(input,13,FOLLOW_13_in_pSet162); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet166); 

                    match(input,10,FOLLOW_10_in_pSet168); 

                    t = new MinLessThanTh(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf(n));

                    }
                    break;
                case 5 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:4: 'min' '(' id3= IDENT ',' pSet ')'
                    {
                    match(input,19,FOLLOW_19_in_pSet175); 

                    match(input,9,FOLLOW_9_in_pSet177); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet181); 

                    match(input,13,FOLLOW_13_in_pSet183); 

                    pushFollow(FOLLOW_pSet_in_pSet185);
                    pSet();

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet187); 

                    t = new MinLessThanTh(pols.get((id3!=null?id3.getText():null)), id4, Double.valueOf(n));

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
        return t;
    }
    // $ANTLR end "pSet"



    // $ANTLR start "pol"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:50:1: pol returns [Pol p] : ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER | '*' '(' ( rule )* ')' 'default' NUMBER );
    public final Pol pol() throws RecognitionException {
        Pol p = null;


        Token NUMBER4=null;
        Token NUMBER6=null;
        Token NUMBER8=null;
        Token NUMBER10=null;
        Rule rule3 =null;

        Rule rule5 =null;

        Rule rule7 =null;

        Rule rule9 =null;


        l = new ArrayList<Rule>(); 
        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:52:2: ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER | '*' '(' ( rule )* ')' 'default' NUMBER )
            int alt7=4;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt7=1;
                }
                break;
            case 18:
                {
                alt7=2;
                }
                break;
            case 19:
                {
                alt7=3;
                }
                break;
            case 11:
                {
                alt7=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }

            switch (alt7) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:52:5: '+' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,12,FOLLOW_12_in_pol211); 

                    match(input,9,FOLLOW_9_in_pol213); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:52:13: ( rule )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==9) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:52:14: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol216);
                    	    rule3=rule();

                    	    state._fsp--;


                    	    l.add(rule3);

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol222); 

                    match(input,17,FOLLOW_17_in_pol224); 

                    NUMBER4=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol226); 

                    p = new Pol(l, Plus$.MODULE$, Double.valueOf((NUMBER4!=null?NUMBER4.getText():null)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:53:4: 'max' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,18,FOLLOW_18_in_pol233); 

                    match(input,9,FOLLOW_9_in_pol235); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:53:14: ( rule )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==9) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:53:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol238);
                    	    rule5=rule();

                    	    state._fsp--;


                    	    l.add(rule5);

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol244); 

                    match(input,17,FOLLOW_17_in_pol246); 

                    NUMBER6=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol248); 

                    p = new Pol(l, Max$.MODULE$, Double.valueOf((NUMBER6!=null?NUMBER6.getText():null)));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:54:4: 'min' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,19,FOLLOW_19_in_pol255); 

                    match(input,9,FOLLOW_9_in_pol257); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:54:14: ( rule )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==9) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:54:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol260);
                    	    rule7=rule();

                    	    state._fsp--;


                    	    l.add(rule7);

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol266); 

                    match(input,17,FOLLOW_17_in_pol268); 

                    NUMBER8=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol270); 

                    p = new Pol(l, Min$.MODULE$, Double.valueOf((NUMBER8!=null?NUMBER8.getText():null)));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:55:4: '*' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,11,FOLLOW_11_in_pol278); 

                    match(input,9,FOLLOW_9_in_pol280); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:55:12: ( rule )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==9) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:55:13: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol283);
                    	    rule9=rule();

                    	    state._fsp--;


                    	    l.add(rule9);

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol289); 

                    match(input,17,FOLLOW_17_in_pol291); 

                    NUMBER10=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol293); 

                    p = new Pol(l, Mul$.MODULE$, Double.valueOf((NUMBER10!=null?NUMBER10.getText():null)));

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
        return p;
    }
    // $ANTLR end "pol"



    // $ANTLR start "rule"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:58:1: rule returns [Rule r] : '(' IDENT NUMBER ')' ;
    public final Rule rule() throws RecognitionException {
        Rule r = null;


        Token IDENT11=null;
        Token NUMBER12=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:2: ( '(' IDENT NUMBER ')' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:4: '(' IDENT NUMBER ')'
            {
            match(input,9,FOLLOW_9_in_rule311); 

            IDENT11=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule313); 

            NUMBER12=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule315); 

            match(input,10,FOLLOW_10_in_rule317); 

            r = new Rule(new Predicate((IDENT11!=null?IDENT11.getText():null), ""),Double.valueOf((NUMBER12!=null?NUMBER12.getText():null)));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return r;
    }
    // $ANTLR end "rule"

    // Delegated rules


 

    public static final BitSet FOLLOW_16_in_program45 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program47 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program51 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program53 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_program57 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program65 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program67 = new BitSet(new long[]{0x00000000000C1800L});
    public static final BitSet FOLLOW_pol_in_program69 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program78 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program80 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet_in_program82 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pSet105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pSet112 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet114 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet118 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet120 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet124 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pSet133 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet135 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet139 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet141 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet_in_pSet145 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pSet154 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet156 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet160 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet162 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet166 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pSet175 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet177 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet181 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet183 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet_in_pSet185 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_pol211 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol213 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol216 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol222 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol224 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pol233 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol235 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol238 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol244 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol246 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pol255 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol257 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol260 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol266 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol268 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_pol278 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol280 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol283 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol289 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol291 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule311 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_rule313 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_rule315 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule317 = new BitSet(new long[]{0x0000000000000002L});

}