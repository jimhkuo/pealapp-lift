// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-06-09 12:05:23

package peal.antlr;
import java.util.*;
import peal.domain.*;
import org.antlr.runtime.BitSet;



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



    // $ANTLR start "program"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:31:1: program : 'cond' '=' id1= IDENT '<=' NUMBER (id2= IDENT '=' 'max' '(' id3= IDENT ',' id4= IDENT ')' |id2= IDENT '=' 'min' '(' id3= IDENT ',' id4= IDENT ')' ) (id5= IDENT '=' pol )* ;
    public final void program() throws RecognitionException {
        Token id1=null;
        Token id2=null;
        Token id3=null;
        Token id4=null;
        Token id5=null;
        Pol pol1 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:31:9: ( 'cond' '=' id1= IDENT '<=' NUMBER (id2= IDENT '=' 'max' '(' id3= IDENT ',' id4= IDENT ')' |id2= IDENT '=' 'min' '(' id3= IDENT ',' id4= IDENT ')' ) (id5= IDENT '=' pol )* )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:31:11: 'cond' '=' id1= IDENT '<=' NUMBER (id2= IDENT '=' 'max' '(' id3= IDENT ',' id4= IDENT ')' |id2= IDENT '=' 'min' '(' id3= IDENT ',' id4= IDENT ')' ) (id5= IDENT '=' pol )*
            {
            match(input,15,FOLLOW_15_in_program50); 

            match(input,14,FOLLOW_14_in_program52); 

            id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program56); 

            match(input,13,FOLLOW_13_in_program58); 

            match(input,NUMBER,FOLLOW_NUMBER_in_program60); 

            l = new ArrayList<Rule>();

            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:32:3: (id2= IDENT '=' 'max' '(' id3= IDENT ',' id4= IDENT ')' |id2= IDENT '=' 'min' '(' id3= IDENT ',' id4= IDENT ')' )
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
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:32:4: id2= IDENT '=' 'max' '(' id3= IDENT ',' id4= IDENT ')'
                    {
                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program69); 

                    match(input,14,FOLLOW_14_in_program71); 

                    match(input,17,FOLLOW_17_in_program73); 

                    match(input,9,FOLLOW_9_in_program75); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_program79); 

                    match(input,12,FOLLOW_12_in_program81); 

                    id4=(Token)match(input,IDENT,FOLLOW_IDENT_in_program85); 

                    match(input,10,FOLLOW_10_in_program87); 

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:32:58: id2= IDENT '=' 'min' '(' id3= IDENT ',' id4= IDENT ')'
                    {
                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program93); 

                    match(input,14,FOLLOW_14_in_program95); 

                    match(input,18,FOLLOW_18_in_program97); 

                    match(input,9,FOLLOW_9_in_program99); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_program103); 

                    match(input,12,FOLLOW_12_in_program105); 

                    id4=(Token)match(input,IDENT,FOLLOW_IDENT_in_program109); 

                    match(input,10,FOLLOW_10_in_program111); 

                    }
                    break;

            }


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:33:3: (id5= IDENT '=' pol )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==IDENT) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:33:4: id5= IDENT '=' pol
            	    {
            	    id5=(Token)match(input,IDENT,FOLLOW_IDENT_in_program119); 

            	    match(input,14,FOLLOW_14_in_program121); 

            	    pushFollow(FOLLOW_pol_in_program123);
            	    pol1=pol();

            	    state._fsp--;


            	    pols.put((id5!=null?id5.getText():null), pol1);

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:36:1: pol returns [Pol p] : ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER );
    public final Pol pol() throws RecognitionException {
        Pol p = null;


        Token NUMBER3=null;
        Token NUMBER5=null;
        Token NUMBER7=null;
        Rule rule2 =null;

        Rule rule4 =null;

        Rule rule6 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:37:2: ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER )
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
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:37:5: '+' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,11,FOLLOW_11_in_pol144); 

                    match(input,9,FOLLOW_9_in_pol146); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:37:13: ( rule )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==9) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:37:14: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol149);
                    	    rule2=rule();

                    	    state._fsp--;


                    	    l.add(rule2);

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol155); 

                    match(input,16,FOLLOW_16_in_pol157); 

                    NUMBER3=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol159); 

                    p = new Pol(new ArrayList<Rule>(l), Double.valueOf((NUMBER3!=null?NUMBER3.getText():null)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:38:4: 'max' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,17,FOLLOW_17_in_pol166); 

                    match(input,9,FOLLOW_9_in_pol168); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:38:14: ( rule )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==9) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:38:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol171);
                    	    rule4=rule();

                    	    state._fsp--;


                    	    l.add(rule4);

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol177); 

                    match(input,16,FOLLOW_16_in_pol179); 

                    NUMBER5=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol181); 

                    p = new Pol(new ArrayList<Rule>(l), Double.valueOf((NUMBER5!=null?NUMBER5.getText():null)));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:39:4: 'min' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,18,FOLLOW_18_in_pol188); 

                    match(input,9,FOLLOW_9_in_pol190); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:39:14: ( rule )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==9) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:39:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol193);
                    	    rule6=rule();

                    	    state._fsp--;


                    	    l.add(rule6);

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol199); 

                    match(input,16,FOLLOW_16_in_pol201); 

                    NUMBER7=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol203); 

                    p = new Pol(new ArrayList<Rule>(l), Double.valueOf((NUMBER7!=null?NUMBER7.getText():null)));

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:42:1: rule returns [Rule r] : '(' IDENT NUMBER ')' ;
    public final Rule rule() throws RecognitionException {
        Rule r = null;


        Token IDENT8=null;
        Token NUMBER9=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:43:2: ( '(' IDENT NUMBER ')' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:43:4: '(' IDENT NUMBER ')'
            {
            match(input,9,FOLLOW_9_in_rule222); 

            IDENT8=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule224); 

            NUMBER9=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule226); 

            match(input,10,FOLLOW_10_in_rule228); 

            r = new Rule(new Predicate((IDENT8!=null?IDENT8.getText():null), ""),Double.valueOf((NUMBER9!=null?NUMBER9.getText():null)));

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
    public static final BitSet FOLLOW_IDENT_in_program69 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program71 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_program73 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_program75 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program79 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_program81 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program85 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_program87 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_program93 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program95 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_program97 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_program99 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program103 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_program105 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program109 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_program111 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_program119 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program121 = new BitSet(new long[]{0x0000000000060800L});
    public static final BitSet FOLLOW_pol_in_program123 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_11_in_pol144 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol146 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol149 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol155 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pol157 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_pol166 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol168 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol171 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol177 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pol179 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pol188 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol190 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol193 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol199 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pol201 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule222 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_rule224 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_rule226 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule228 = new BitSet(new long[]{0x0000000000000002L});

}