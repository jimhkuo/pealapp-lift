// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-07-16 09:59:25

package peal.antlr;
import java.util.*;
import peal.domain.*;
import peal.*;
import org.antlr.runtime.BitSet;
import peal.synthesis.*;
import peal.synthesis.analysis.*;
import peal.domain.operator.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PealProgramParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMPARE", "IDENT", "NEWLINE", "NUMBER", "WS", "'('", "')'", "'*'", "'+'", "','", "'<'", "'<='", "'='", "'ANALYSES'", "'DOMAIN_SPECIFICS'", "'always_false?'", "'always_true?'", "'default'", "'different?'", "'equivalent?'", "'max'", "'min'", "'satisfiable?'"
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
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
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


    public Map<String, Pol> pols = new HashMap<String, Pol>();
    public Map<String, Condition> conds = new HashMap<String, Condition>();
    public Map<String, AnalysisGenerator> analyses = new HashMap<String, AnalysisGenerator>();
    public Map<String, PolicySet> pSets = new HashMap<String, PolicySet>();
    private Map<String, String> pSetScores = new HashMap<String, String>();
    private List<Rule> l = null;
    private boolean ignore = false;


    @Override
    public void reportError(RecognitionException e) {
    	throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
    }




    // $ANTLR start "program"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:43:1: program : (id1= IDENT '=' pol )* (id2= IDENT '=' pSet )+ (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' num= NUMBER '<' id2= IDENT )+ ( 'DOMAIN_SPECIFICS' )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT )+ )? ;
    public final void program() throws RecognitionException {
        Token id1=null;
        Token id2=null;
        Token id0=null;
        Token num=null;
        Pol pol1 =null;

        PolicySet pSet2 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:44:2: ( (id1= IDENT '=' pol )* (id2= IDENT '=' pSet )+ (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' num= NUMBER '<' id2= IDENT )+ ( 'DOMAIN_SPECIFICS' )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT )+ )? )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:46:2: (id1= IDENT '=' pol )* (id2= IDENT '=' pSet )+ (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' num= NUMBER '<' id2= IDENT )+ ( 'DOMAIN_SPECIFICS' )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT )+ )?
            {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:46:2: (id1= IDENT '=' pol )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==IDENT) ) {
                    int LA1_1 = input.LA(2);

                    if ( (LA1_1==16) ) {
                        switch ( input.LA(3) ) {
                        case 24:
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
                        case 25:
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
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:46:3: id1= IDENT '=' pol
            	    {
            	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program61); 

            	    match(input,16,FOLLOW_16_in_program63); 

            	    pushFollow(FOLLOW_pol_in_program65);
            	    pol1=pol();

            	    state._fsp--;


            	    pols.put((id1!=null?id1.getText():null), pol1);

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:2: (id2= IDENT '=' pSet )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==IDENT) ) {
                    int LA2_1 = input.LA(2);

                    if ( (LA2_1==16) ) {
                        int LA2_2 = input.LA(3);

                        if ( (LA2_2==IDENT) ) {
                            int LA2_3 = input.LA(4);

                            if ( (LA2_3==IDENT) ) {
                                alt2=1;
                            }


                        }
                        else if ( ((LA2_2 >= 24 && LA2_2 <= 25)) ) {
                            alt2=1;
                        }


                    }


                }


                switch (alt2) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:3: id2= IDENT '=' pSet
            	    {
            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program75); 

            	    match(input,16,FOLLOW_16_in_program77); 

            	    pushFollow(FOLLOW_pSet_in_program79);
            	    pSet2=pSet();

            	    state._fsp--;


            	     pSets.put((id2!=null?id2.getText():null), pSet2);

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:2: (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' num= NUMBER '<' id2= IDENT )+
            int cnt3=0;
            loop3:
            do {
                int alt3=3;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==IDENT) ) {
                    int LA3_2 = input.LA(2);

                    if ( (LA3_2==16) ) {
                        int LA3_3 = input.LA(3);

                        if ( (LA3_3==IDENT) ) {
                            alt3=1;
                        }
                        else if ( (LA3_3==NUMBER) ) {
                            alt3=2;
                        }


                    }


                }


                switch (alt3) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:49:2: id0= IDENT '=' id2= IDENT '<=' num= NUMBER
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program91); 

            	    match(input,16,FOLLOW_16_in_program93); 

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program97); 

            	    match(input,15,FOLLOW_15_in_program99); 

            	    num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program103); 

            	    Condition cond = new LessThanThCondition(pSets.get((id2!=null?id2.getText():null)), Double.valueOf((num!=null?num.getText():null))); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;
            	case 2 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:51:2: id0= IDENT '=' num= NUMBER '<' id2= IDENT
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program117); 

            	    match(input,16,FOLLOW_16_in_program119); 

            	    num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program123); 

            	    match(input,14,FOLLOW_14_in_program125); 

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program129); 

            	    Condition cond = new GreaterThanThCondition(pSets.get((id2!=null?id2.getText():null)), Double.valueOf((num!=null?num.getText():null))); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:53:2: ( 'DOMAIN_SPECIFICS' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==18) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:53:3: 'DOMAIN_SPECIFICS'
                    {
                    match(input,18,FOLLOW_18_in_program139); 

                    ignore = true;

                    }
                    break;

            }


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:54:2: ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT )+ )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==17) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:54:3: 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT )+
                    {
                    match(input,17,FOLLOW_17_in_program148); 

                    ignore = false;

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:55:2: (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT )+
                    int cnt5=0;
                    loop5:
                    do {
                        int alt5=6;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==IDENT) ) {
                            int LA5_2 = input.LA(2);

                            if ( (LA5_2==16) ) {
                                switch ( input.LA(3) ) {
                                case 20:
                                    {
                                    alt5=1;
                                    }
                                    break;
                                case 19:
                                    {
                                    alt5=2;
                                    }
                                    break;
                                case 26:
                                    {
                                    alt5=3;
                                    }
                                    break;
                                case 23:
                                    {
                                    alt5=4;
                                    }
                                    break;
                                case 22:
                                    {
                                    alt5=5;
                                    }
                                    break;

                                }

                            }


                        }


                        switch (alt5) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:55:3: id0= IDENT '=' 'always_true?' id1= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program156); 

                    	    match(input,16,FOLLOW_16_in_program158); 

                    	    match(input,20,FOLLOW_20_in_program160); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program164); 

                    	    AnalysisGenerator analysis = new AlwaysTrue((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 2 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:56:3: id0= IDENT '=' 'always_false?' id1= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program172); 

                    	    match(input,16,FOLLOW_16_in_program174); 

                    	    match(input,19,FOLLOW_19_in_program176); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program180); 

                    	    AnalysisGenerator analysis = new AlwaysFalse((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 3 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:57:3: id0= IDENT '=' 'satisfiable?' id1= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program188); 

                    	    match(input,16,FOLLOW_16_in_program190); 

                    	    match(input,26,FOLLOW_26_in_program192); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program196); 

                    	    AnalysisGenerator analysis = new Satisfiable((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 4 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:58:3: id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program205); 

                    	    match(input,16,FOLLOW_16_in_program207); 

                    	    match(input,23,FOLLOW_23_in_program209); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program213); 

                    	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program217); 

                    	    AnalysisGenerator analysis = new Equivalent((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 5 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:3: id0= IDENT '=' 'different?' id1= IDENT id2= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program226); 

                    	    match(input,16,FOLLOW_16_in_program228); 

                    	    match(input,22,FOLLOW_22_in_program230); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program234); 

                    	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program238); 

                    	    AnalysisGenerator analysis = new Different((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

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



    // $ANTLR start "pSet"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:64:1: pSet returns [PolicySet t] : (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' id4= pSet ')' );
    public final PolicySet pSet() throws RecognitionException {
        PolicySet t = null;


        Token id1=null;
        Token id2=null;
        Token id3=null;
        PolicySet id4 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:65:2: (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' id4= pSet ')' )
            int alt7=5;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt7=1;
                }
                break;
            case 24:
                {
                int LA7_2 = input.LA(2);

                if ( (LA7_2==9) ) {
                    int LA7_4 = input.LA(3);

                    if ( (LA7_4==IDENT) ) {
                        int LA7_6 = input.LA(4);

                        if ( (LA7_6==13) ) {
                            int LA7_8 = input.LA(5);

                            if ( (LA7_8==IDENT) ) {
                                int LA7_10 = input.LA(6);

                                if ( (LA7_10==10) ) {
                                    alt7=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 7, 10, input);

                                    throw nvae;

                                }
                            }
                            else if ( ((LA7_8 >= 24 && LA7_8 <= 25)) ) {
                                alt7=3;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 7, 8, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 7, 6, input);

                            throw nvae;

                        }
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
            case 25:
                {
                int LA7_3 = input.LA(2);

                if ( (LA7_3==9) ) {
                    int LA7_5 = input.LA(3);

                    if ( (LA7_5==IDENT) ) {
                        int LA7_7 = input.LA(4);

                        if ( (LA7_7==13) ) {
                            int LA7_9 = input.LA(5);

                            if ( (LA7_9==IDENT) ) {
                                int LA7_12 = input.LA(6);

                                if ( (LA7_12==10) ) {
                                    alt7=4;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 7, 12, input);

                                    throw nvae;

                                }
                            }
                            else if ( ((LA7_9 >= 24 && LA7_9 <= 25)) ) {
                                alt7=5;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 7, 9, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 7, 7, input);

                            throw nvae;

                        }
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
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:65:4: id1= IDENT
                    {
                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet269); 

                    t = new BasicPolicySet(pols.get((id1!=null?id1.getText():null)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:66:4: 'max' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,24,FOLLOW_24_in_pSet276); 

                    match(input,9,FOLLOW_9_in_pSet278); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet282); 

                    match(input,13,FOLLOW_13_in_pSet284); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet288); 

                    match(input,10,FOLLOW_10_in_pSet290); 

                    t = new MaxPolicySet(new BasicPolicySet(pols.get((id1!=null?id1.getText():null))), new BasicPolicySet(pols.get((id2!=null?id2.getText():null))));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:67:4: 'max' '(' id3= IDENT ',' id4= pSet ')'
                    {
                    match(input,24,FOLLOW_24_in_pSet297); 

                    match(input,9,FOLLOW_9_in_pSet299); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet303); 

                    match(input,13,FOLLOW_13_in_pSet305); 

                    pushFollow(FOLLOW_pSet_in_pSet309);
                    id4=pSet();

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet311); 

                    t = new MaxPolicySet(new BasicPolicySet(pols.get((id3!=null?id3.getText():null))), id4);

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:68:4: 'min' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,25,FOLLOW_25_in_pSet318); 

                    match(input,9,FOLLOW_9_in_pSet320); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet324); 

                    match(input,13,FOLLOW_13_in_pSet326); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet330); 

                    match(input,10,FOLLOW_10_in_pSet332); 

                    t = new MinPolicySet(new BasicPolicySet(pols.get((id1!=null?id1.getText():null))), new BasicPolicySet(pols.get((id2!=null?id2.getText():null))));

                    }
                    break;
                case 5 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:69:4: 'min' '(' id3= IDENT ',' id4= pSet ')'
                    {
                    match(input,25,FOLLOW_25_in_pSet339); 

                    match(input,9,FOLLOW_9_in_pSet341); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet345); 

                    match(input,13,FOLLOW_13_in_pSet347); 

                    pushFollow(FOLLOW_pSet_in_pSet351);
                    id4=pSet();

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet353); 

                    t = new MinPolicySet(new BasicPolicySet(pols.get((id3!=null?id3.getText():null))), id4);

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:1: pol returns [Pol p] : ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER | '*' '(' ( rule )* ')' 'default' NUMBER );
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:2: ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER | '*' '(' ( rule )* ')' 'default' NUMBER )
            int alt12=4;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt12=1;
                }
                break;
            case 24:
                {
                alt12=2;
                }
                break;
            case 25:
                {
                alt12=3;
                }
                break;
            case 11:
                {
                alt12=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }

            switch (alt12) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:5: '+' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,12,FOLLOW_12_in_pol377); 

                    match(input,9,FOLLOW_9_in_pol379); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:13: ( rule )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==9) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:14: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol382);
                    	    rule3=rule();

                    	    state._fsp--;


                    	    l.add(rule3);

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol388); 

                    match(input,21,FOLLOW_21_in_pol390); 

                    NUMBER4=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol392); 

                    p = new Pol(l, Plus$.MODULE$, Double.valueOf((NUMBER4!=null?NUMBER4.getText():null)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:75:4: 'max' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,24,FOLLOW_24_in_pol399); 

                    match(input,9,FOLLOW_9_in_pol401); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:75:14: ( rule )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==9) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:75:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol404);
                    	    rule5=rule();

                    	    state._fsp--;


                    	    l.add(rule5);

                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol410); 

                    match(input,21,FOLLOW_21_in_pol412); 

                    NUMBER6=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol414); 

                    p = new Pol(l, Max$.MODULE$, Double.valueOf((NUMBER6!=null?NUMBER6.getText():null)));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:76:4: 'min' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,25,FOLLOW_25_in_pol421); 

                    match(input,9,FOLLOW_9_in_pol423); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:76:14: ( rule )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==9) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:76:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol426);
                    	    rule7=rule();

                    	    state._fsp--;


                    	    l.add(rule7);

                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol432); 

                    match(input,21,FOLLOW_21_in_pol434); 

                    NUMBER8=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol436); 

                    p = new Pol(l, Min$.MODULE$, Double.valueOf((NUMBER8!=null?NUMBER8.getText():null)));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:77:4: '*' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,11,FOLLOW_11_in_pol444); 

                    match(input,9,FOLLOW_9_in_pol446); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:77:12: ( rule )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==9) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:77:13: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol449);
                    	    rule9=rule();

                    	    state._fsp--;


                    	    l.add(rule9);

                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol455); 

                    match(input,21,FOLLOW_21_in_pol457); 

                    NUMBER10=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol459); 

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:80:1: rule returns [Rule r] : '(' IDENT NUMBER ')' ;
    public final Rule rule() throws RecognitionException {
        Rule r = null;


        Token IDENT11=null;
        Token NUMBER12=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:81:2: ( '(' IDENT NUMBER ')' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:81:4: '(' IDENT NUMBER ')'
            {
            match(input,9,FOLLOW_9_in_rule477); 

            IDENT11=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule479); 

            NUMBER12=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule481); 

            match(input,10,FOLLOW_10_in_rule483); 

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


 

    public static final BitSet FOLLOW_IDENT_in_program61 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program63 = new BitSet(new long[]{0x0000000003001800L});
    public static final BitSet FOLLOW_pol_in_program65 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program75 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program77 = new BitSet(new long[]{0x0000000003000020L});
    public static final BitSet FOLLOW_pSet_in_program79 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program91 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program93 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program97 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program99 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_program103 = new BitSet(new long[]{0x0000000000060022L});
    public static final BitSet FOLLOW_IDENT_in_program117 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program119 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_program123 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program125 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program129 = new BitSet(new long[]{0x0000000000060022L});
    public static final BitSet FOLLOW_18_in_program139 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_program148 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program156 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program158 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_program160 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program164 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_program172 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program174 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_program176 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program180 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_program188 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program190 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_program192 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program196 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_program205 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program207 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_program209 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program213 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program217 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_program226 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program228 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_program230 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program234 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program238 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_pSet269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_pSet276 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet278 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet282 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet284 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet288 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_pSet297 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet299 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet303 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet305 = new BitSet(new long[]{0x0000000003000020L});
    public static final BitSet FOLLOW_pSet_in_pSet309 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_pSet318 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet320 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet324 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet326 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet330 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_pSet339 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet341 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet345 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet347 = new BitSet(new long[]{0x0000000003000020L});
    public static final BitSet FOLLOW_pSet_in_pSet351 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_pol377 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol379 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol382 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol388 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_pol390 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_pol399 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol401 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol404 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol410 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_pol412 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_pol421 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol423 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol426 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol432 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_pol434 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_pol444 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol446 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol449 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol455 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_pol457 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule477 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_rule479 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_rule481 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule483 = new BitSet(new long[]{0x0000000000000002L});

}