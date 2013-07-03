// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-07-03 15:40:53

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
                        int LA1_2 = input.LA(3);

                        if ( (LA1_2==18) ) {
                            int LA1_4 = input.LA(4);

                            if ( (LA1_4==9) ) {
                                int LA1_6 = input.LA(5);

                                if ( ((LA1_6 >= 9 && LA1_6 <= 10)) ) {
                                    alt1=1;
                                }


                            }


                        }
                        else if ( ((LA1_2 >= 11 && LA1_2 <= 12)||LA1_2==19) ) {
                            alt1=1;
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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:42:1: pSet returns [pSet t] : (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet ')' );
    public final pSet pSet() throws RecognitionException {
        pSet t = null;


        Token id1=null;
        Token id2=null;
        Token id3=null;
        pSet id4 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:43:2: (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet ')' )
            int alt2=3;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==IDENT) ) {
                alt2=1;
            }
            else if ( (LA2_0==18) ) {
                int LA2_2 = input.LA(2);

                if ( (LA2_2==9) ) {
                    int LA2_3 = input.LA(3);

                    if ( (LA2_3==IDENT) ) {
                        int LA2_4 = input.LA(4);

                        if ( (LA2_4==13) ) {
                            int LA2_5 = input.LA(5);

                            if ( (LA2_5==IDENT) ) {
                                int LA2_6 = input.LA(6);

                                if ( (LA2_6==10) ) {
                                    alt2=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 2, 6, input);

                                    throw nvae;

                                }
                            }
                            else if ( (LA2_5==18) ) {
                                alt2=3;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 2, 5, input);

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
                            new NoViableAltException("", 2, 3, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 2, input);

                    throw nvae;

                }
            }
            else {
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
                    match(input,12,FOLLOW_12_in_pol173); 

                    match(input,9,FOLLOW_9_in_pol175); 

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
                    	    pushFollow(FOLLOW_rule_in_pol178);
                    	    rule3=rule();

                    	    state._fsp--;


                    	    l.add(rule3);

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol184); 

                    match(input,17,FOLLOW_17_in_pol186); 

                    NUMBER4=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol188); 

                    p = new Pol(l, Plus$.MODULE$, Double.valueOf((NUMBER4!=null?NUMBER4.getText():null)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:53:4: 'max' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,18,FOLLOW_18_in_pol195); 

                    match(input,9,FOLLOW_9_in_pol197); 

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
                    	    pushFollow(FOLLOW_rule_in_pol200);
                    	    rule5=rule();

                    	    state._fsp--;


                    	    l.add(rule5);

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol206); 

                    match(input,17,FOLLOW_17_in_pol208); 

                    NUMBER6=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol210); 

                    p = new Pol(l, Max$.MODULE$, Double.valueOf((NUMBER6!=null?NUMBER6.getText():null)));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:54:4: 'min' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,19,FOLLOW_19_in_pol217); 

                    match(input,9,FOLLOW_9_in_pol219); 

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
                    	    pushFollow(FOLLOW_rule_in_pol222);
                    	    rule7=rule();

                    	    state._fsp--;


                    	    l.add(rule7);

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol228); 

                    match(input,17,FOLLOW_17_in_pol230); 

                    NUMBER8=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol232); 

                    p = new Pol(l, Min$.MODULE$, Double.valueOf((NUMBER8!=null?NUMBER8.getText():null)));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:55:4: '*' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,11,FOLLOW_11_in_pol240); 

                    match(input,9,FOLLOW_9_in_pol242); 

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
                    	    pushFollow(FOLLOW_rule_in_pol245);
                    	    rule9=rule();

                    	    state._fsp--;


                    	    l.add(rule9);

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol251); 

                    match(input,17,FOLLOW_17_in_pol253); 

                    NUMBER10=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol255); 

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
            match(input,9,FOLLOW_9_in_rule273); 

            IDENT11=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule275); 

            NUMBER12=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule277); 

            match(input,10,FOLLOW_10_in_rule279); 

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
    public static final BitSet FOLLOW_15_in_program80 = new BitSet(new long[]{0x0000000000040020L});
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
    public static final BitSet FOLLOW_13_in_pSet141 = new BitSet(new long[]{0x0000000000040020L});
    public static final BitSet FOLLOW_pSet_in_pSet145 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_pol173 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol175 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol178 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol184 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol186 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pol195 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol197 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol200 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol206 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol208 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pol217 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol219 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol222 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol228 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol230 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_pol240 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol242 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol245 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol251 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol253 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule273 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_rule275 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_rule277 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule279 = new BitSet(new long[]{0x0000000000000002L});

}