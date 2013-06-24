// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-06-24 14:40:05

package peal.antlr;
import java.util.*;
import peal.domain.*;
import peal.*;
import org.antlr.runtime.BitSet;
import peal.synthesis.TopSet;
import peal.domain.operator.*;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PealProgramParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMPARE", "IDENT", "NEWLINE", "NUMBER", "WS", "'('", "')'", "'+'", "','", "'<='", "'='", "'cond'", "'default'", "'max'", "'min'", "'pSet'"
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
    public TopSet pSet = null;

    //need to override the default error reporting
    @Override
    public void reportError(RecognitionException e) {
    	throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
    }




    // $ANTLR start "program"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:42:1: program : 'cond' '=' 'pSet' '<=' n= NUMBER (id5= IDENT '=' pol )* ( 'pSet' '=' 'max' '(' id3= IDENT ',' id4= IDENT ')' | 'pSet' '=' 'min' '(' id3= IDENT ',' id4= IDENT ')' | 'pSet' '=' id3= IDENT ) ;
    public final void program() throws RecognitionException {
        Token n=null;
        Token id5=null;
        Token id3=null;
        Token id4=null;
        Pol pol1 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:43:2: ( 'cond' '=' 'pSet' '<=' n= NUMBER (id5= IDENT '=' pol )* ( 'pSet' '=' 'max' '(' id3= IDENT ',' id4= IDENT ')' | 'pSet' '=' 'min' '(' id3= IDENT ',' id4= IDENT ')' | 'pSet' '=' id3= IDENT ) )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:43:4: 'cond' '=' 'pSet' '<=' n= NUMBER (id5= IDENT '=' pol )* ( 'pSet' '=' 'max' '(' id3= IDENT ',' id4= IDENT ')' | 'pSet' '=' 'min' '(' id3= IDENT ',' id4= IDENT ')' | 'pSet' '=' id3= IDENT )
            {
            match(input,15,FOLLOW_15_in_program52); 

            match(input,14,FOLLOW_14_in_program54); 

            match(input,19,FOLLOW_19_in_program56); 

            match(input,13,FOLLOW_13_in_program58); 

            n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program62); 

            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:44:2: (id5= IDENT '=' pol )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==IDENT) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:44:3: id5= IDENT '=' pol
            	    {
            	    id5=(Token)match(input,IDENT,FOLLOW_IDENT_in_program69); 

            	    match(input,14,FOLLOW_14_in_program71); 

            	    pushFollow(FOLLOW_pol_in_program73);
            	    pol1=pol();

            	    state._fsp--;


            	    pols.put((id5!=null?id5.getText():null), pol1);

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:45:4: ( 'pSet' '=' 'max' '(' id3= IDENT ',' id4= IDENT ')' | 'pSet' '=' 'min' '(' id3= IDENT ',' id4= IDENT ')' | 'pSet' '=' id3= IDENT )
            int alt2=3;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==19) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==14) ) {
                    switch ( input.LA(3) ) {
                    case 17:
                        {
                        alt2=1;
                        }
                        break;
                    case 18:
                        {
                        alt2=2;
                        }
                        break;
                    case IDENT:
                        {
                        alt2=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 2, input);

                        throw nvae;

                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

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
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:46:4: 'pSet' '=' 'max' '(' id3= IDENT ',' id4= IDENT ')'
                    {
                    match(input,19,FOLLOW_19_in_program87); 

                    match(input,14,FOLLOW_14_in_program89); 

                    match(input,17,FOLLOW_17_in_program91); 

                    match(input,9,FOLLOW_9_in_program93); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_program97); 

                    match(input,12,FOLLOW_12_in_program99); 

                    id4=(Token)match(input,IDENT,FOLLOW_IDENT_in_program103); 

                    match(input,10,FOLLOW_10_in_program105); 

                    pSet = new MaxLessThanTh(pols.get((id3!=null?id3.getText():null)), pols.get((id4!=null?id4.getText():null)), Double.valueOf((n!=null?n.getText():null)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:4: 'pSet' '=' 'min' '(' id3= IDENT ',' id4= IDENT ')'
                    {
                    match(input,19,FOLLOW_19_in_program119); 

                    match(input,14,FOLLOW_14_in_program121); 

                    match(input,18,FOLLOW_18_in_program123); 

                    match(input,9,FOLLOW_9_in_program125); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_program129); 

                    match(input,12,FOLLOW_12_in_program131); 

                    id4=(Token)match(input,IDENT,FOLLOW_IDENT_in_program135); 

                    match(input,10,FOLLOW_10_in_program137); 

                    pSet = new MinLessThanTh(pols.get((id3!=null?id3.getText():null)), pols.get((id4!=null?id4.getText():null)), Double.valueOf((n!=null?n.getText():null)));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:50:4: 'pSet' '=' id3= IDENT
                    {
                    match(input,19,FOLLOW_19_in_program149); 

                    match(input,14,FOLLOW_14_in_program151); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_program155); 

                    pSet = new PolLessThanTh(pols.get((id3!=null?id3.getText():null)), Double.valueOf((n!=null?n.getText():null)));

                    }
                    break;

            }


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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:54:1: pol returns [Pol p] : ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER );
    public final Pol pol() throws RecognitionException {
        Pol p = null;


        Token NUMBER3=null;
        Token NUMBER5=null;
        Token NUMBER7=null;
        Rule rule2 =null;

        Rule rule4 =null;

        Rule rule6 =null;


        l = new ArrayList<Rule>(); 
        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:56:2: ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER )
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
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:56:5: '+' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,11,FOLLOW_11_in_pol182); 

                    match(input,9,FOLLOW_9_in_pol184); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:56:13: ( rule )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==9) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:56:14: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol187);
                    	    rule2=rule();

                    	    state._fsp--;


                    	    l.add(rule2);

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol193); 

                    match(input,16,FOLLOW_16_in_pol195); 

                    NUMBER3=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol197); 

                    p = new Pol(l, Plus$.MODULE$, Double.valueOf((NUMBER3!=null?NUMBER3.getText():null)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:57:4: 'max' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,17,FOLLOW_17_in_pol204); 

                    match(input,9,FOLLOW_9_in_pol206); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:57:14: ( rule )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==9) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:57:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol209);
                    	    rule4=rule();

                    	    state._fsp--;


                    	    l.add(rule4);

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol215); 

                    match(input,16,FOLLOW_16_in_pol217); 

                    NUMBER5=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol219); 

                    p = new Pol(l, Max$.MODULE$, Double.valueOf((NUMBER5!=null?NUMBER5.getText():null)));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:58:4: 'min' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,18,FOLLOW_18_in_pol226); 

                    match(input,9,FOLLOW_9_in_pol228); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:58:14: ( rule )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==9) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:58:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol231);
                    	    rule6=rule();

                    	    state._fsp--;


                    	    l.add(rule6);

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol237); 

                    match(input,16,FOLLOW_16_in_pol239); 

                    NUMBER7=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol241); 

                    p = new Pol(l, Min$.MODULE$, Double.valueOf((NUMBER7!=null?NUMBER7.getText():null)));

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:61:1: rule returns [Rule r] : '(' IDENT NUMBER ')' ;
    public final Rule rule() throws RecognitionException {
        Rule r = null;


        Token IDENT8=null;
        Token NUMBER9=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:62:2: ( '(' IDENT NUMBER ')' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:62:4: '(' IDENT NUMBER ')'
            {
            match(input,9,FOLLOW_9_in_rule260); 

            IDENT8=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule262); 

            NUMBER9=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule264); 

            match(input,10,FOLLOW_10_in_rule266); 

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


 

    public static final BitSet FOLLOW_15_in_program52 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program54 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_program56 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_program58 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_program62 = new BitSet(new long[]{0x0000000000080020L});
    public static final BitSet FOLLOW_IDENT_in_program69 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program71 = new BitSet(new long[]{0x0000000000060800L});
    public static final BitSet FOLLOW_pol_in_program73 = new BitSet(new long[]{0x0000000000080020L});
    public static final BitSet FOLLOW_19_in_program87 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program89 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_program91 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_program93 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program97 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_program99 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program103 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_program105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_program119 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program121 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_program123 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_program125 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program129 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_program131 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program135 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_program137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_program149 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program151 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_pol182 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol184 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol187 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol193 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pol195 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_pol204 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol206 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol209 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol215 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pol217 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pol226 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol228 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol231 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol237 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pol239 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule260 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_rule262 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_rule264 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule266 = new BitSet(new long[]{0x0000000000000002L});

}