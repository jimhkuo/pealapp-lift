// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-06-11 10:18:23

package peal.antlr;
import java.util.*;
import peal.domain.*;
import peal.*;
import org.antlr.runtime.BitSet;
import peal.synthesis.TopSet;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PealProgramParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMPARE", "IDENT", "NEWLINE", "NUMBER", "WS", "'('", "')'", "'+'", "','", "'<='", "'='", "'cond'", "'default'", "'max'", "'min'"
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
    TopSet pSet = null;



    // $ANTLR start "program"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:34:1: program : 'cond' '=' id1= IDENT '<=' NUMBER (id2= IDENT '=' 'max' '(' id3= IDENT ',' id4= IDENT ')' |id2= IDENT '=' 'min' '(' id3= IDENT ',' id4= IDENT ')' ) (id5= IDENT '=' pol )* ;
    public final void program() throws RecognitionException {
        Token id1=null;
        Token id2=null;
        Token id3=null;
        Token id4=null;
        Token id5=null;
        Token NUMBER1=null;
        Pol pol2 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:34:9: ( 'cond' '=' id1= IDENT '<=' NUMBER (id2= IDENT '=' 'max' '(' id3= IDENT ',' id4= IDENT ')' |id2= IDENT '=' 'min' '(' id3= IDENT ',' id4= IDENT ')' ) (id5= IDENT '=' pol )* )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:34:11: 'cond' '=' id1= IDENT '<=' NUMBER (id2= IDENT '=' 'max' '(' id3= IDENT ',' id4= IDENT ')' |id2= IDENT '=' 'min' '(' id3= IDENT ',' id4= IDENT ')' ) (id5= IDENT '=' pol )*
            {
            match(input,15,FOLLOW_15_in_program50); 

            match(input,14,FOLLOW_14_in_program52); 

            id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program56); 

            match(input,13,FOLLOW_13_in_program58); 

            NUMBER1=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program60); 

            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:35:3: (id2= IDENT '=' 'max' '(' id3= IDENT ',' id4= IDENT ')' |id2= IDENT '=' 'min' '(' id3= IDENT ',' id4= IDENT ')' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==IDENT) ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1==14) ) {
                    int LA1_2 = input.LA(3);

                    if ( (LA1_2==17) ) {
                        alt1=1;
                    }
                    else if ( (LA1_2==18) ) {
                        alt1=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 1, 2, input);

                        throw nvae;

                    }
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
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:36:3: id2= IDENT '=' 'max' '(' id3= IDENT ',' id4= IDENT ')'
                    {
                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program71); 

                    match(input,14,FOLLOW_14_in_program73); 

                    match(input,17,FOLLOW_17_in_program75); 

                    match(input,9,FOLLOW_9_in_program77); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_program81); 

                    match(input,12,FOLLOW_12_in_program83); 

                    id4=(Token)match(input,IDENT,FOLLOW_IDENT_in_program87); 

                    match(input,10,FOLLOW_10_in_program89); 

                    pSet = new MaxLessThanTh(pols.get((id3!=null?id3.getText():null)),pols.get((id4!=null?id4.getText():null)), Double.valueOf((NUMBER1!=null?NUMBER1.getText():null)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:39:3: id2= IDENT '=' 'min' '(' id3= IDENT ',' id4= IDENT ')'
                    {
                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program107); 

                    match(input,14,FOLLOW_14_in_program109); 

                    match(input,18,FOLLOW_18_in_program111); 

                    match(input,9,FOLLOW_9_in_program113); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_program117); 

                    match(input,12,FOLLOW_12_in_program119); 

                    id4=(Token)match(input,IDENT,FOLLOW_IDENT_in_program123); 

                    match(input,10,FOLLOW_10_in_program125); 

                    pSet = new MinLessThanTh(pols.get((id3!=null?id3.getText():null)),pols.get((id4!=null?id4.getText():null)), Double.valueOf((NUMBER1!=null?NUMBER1.getText():null)));

                    }
                    break;

            }


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:42:3: (id5= IDENT '=' pol )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==IDENT) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:42:4: id5= IDENT '=' pol
            	    {
            	    id5=(Token)match(input,IDENT,FOLLOW_IDENT_in_program141); 

            	    match(input,14,FOLLOW_14_in_program143); 

            	    pushFollow(FOLLOW_pol_in_program145);
            	    pol2=pol();

            	    state._fsp--;


            	    pols.put((id5!=null?id5.getText():null), pol2);

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


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



    // $ANTLR start "pol"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:45:1: pol returns [Pol p] : ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER );
    public final Pol pol() throws RecognitionException {
        Pol p = null;


        Token NUMBER4=null;
        Token NUMBER6=null;
        Token NUMBER8=null;
        Rule rule3 =null;

        Rule rule5 =null;

        Rule rule7 =null;


        l = new ArrayList<Rule>(); 
        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:2: ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER )
            int alt6=3;
            switch ( input.LA(1) ) {
            case 11:
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
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:5: '+' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,11,FOLLOW_11_in_pol171); 

                    match(input,9,FOLLOW_9_in_pol173); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:13: ( rule )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==9) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:14: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol176);
                    	    rule3=rule();

                    	    state._fsp--;


                    	    l.add(rule3);

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol182); 

                    match(input,16,FOLLOW_16_in_pol184); 

                    NUMBER4=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol186); 

                    p = new Pol(l, Double.valueOf((NUMBER4!=null?NUMBER4.getText():null)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:4: 'max' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,17,FOLLOW_17_in_pol193); 

                    match(input,9,FOLLOW_9_in_pol195); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:14: ( rule )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==9) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol198);
                    	    rule5=rule();

                    	    state._fsp--;


                    	    l.add(rule5);

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol204); 

                    match(input,16,FOLLOW_16_in_pol206); 

                    NUMBER6=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol208); 

                    p = new Pol(l, Double.valueOf((NUMBER6!=null?NUMBER6.getText():null)));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:49:4: 'min' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,18,FOLLOW_18_in_pol215); 

                    match(input,9,FOLLOW_9_in_pol217); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:49:14: ( rule )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==9) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:49:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol220);
                    	    rule7=rule();

                    	    state._fsp--;


                    	    l.add(rule7);

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol226); 

                    match(input,16,FOLLOW_16_in_pol228); 

                    NUMBER8=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol230); 

                    p = new Pol(l, Double.valueOf((NUMBER8!=null?NUMBER8.getText():null)));

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:52:1: rule returns [Rule r] : '(' IDENT NUMBER ')' ;
    public final Rule rule() throws RecognitionException {
        Rule r = null;


        Token IDENT9=null;
        Token NUMBER10=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:53:2: ( '(' IDENT NUMBER ')' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:53:4: '(' IDENT NUMBER ')'
            {
            match(input,9,FOLLOW_9_in_rule249); 

            IDENT9=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule251); 

            NUMBER10=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule253); 

            match(input,10,FOLLOW_10_in_rule255); 

            r = new Rule(new Predicate((IDENT9!=null?IDENT9.getText():null), ""),Double.valueOf((NUMBER10!=null?NUMBER10.getText():null)));

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


 

    public static final BitSet FOLLOW_15_in_program50 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program52 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program56 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_program58 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_program60 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program71 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program73 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_program75 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_program77 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program81 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_program83 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program87 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_program89 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_program107 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program109 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_program111 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_program113 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program117 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_program119 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program123 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_program125 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_program141 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program143 = new BitSet(new long[]{0x0000000000060800L});
    public static final BitSet FOLLOW_pol_in_program145 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_11_in_pol171 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol173 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol176 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol182 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pol184 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_pol193 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol195 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol198 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol204 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pol206 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pol215 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol217 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol220 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol226 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pol228 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule249 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_rule251 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_rule253 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule255 = new BitSet(new long[]{0x0000000000000002L});

}