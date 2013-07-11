// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-07-11 18:50:25

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMPARE", "IDENT", "NEWLINE", "NUMBER", "WS", "'('", "')'", "'*'", "'+'", "','", "'<'", "'<='", "'='", "'ANALYSES'", "'always_false?'", "'always_true?'", "'default'", "'difference?'", "'equivalence?'", "'max'", "'min'", "'satisfiable?'"
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
    List<Rule> l = new ArrayList<Rule>();

    @Override
    public void reportError(RecognitionException e) {
    	throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
    }




    // $ANTLR start "program"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:37:1: program : (id1= IDENT '=' pol )* (id2= IDENT '=' pSet )+ (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' num= NUMBER '<' id2= IDENT )+ ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalence?' id1= IDENT id2= IDENT |id0= IDENT '=' 'difference?' id1= IDENT id2= IDENT )+ )? ;
    public final void program() throws RecognitionException {
        Token id1=null;
        Token id2=null;
        Token id0=null;
        Token num=null;
        Pol pol1 =null;

        PolicySet pSet2 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:38:2: ( (id1= IDENT '=' pol )* (id2= IDENT '=' pSet )+ (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' num= NUMBER '<' id2= IDENT )+ ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalence?' id1= IDENT id2= IDENT |id0= IDENT '=' 'difference?' id1= IDENT id2= IDENT )+ )? )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:40:2: (id1= IDENT '=' pol )* (id2= IDENT '=' pSet )+ (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' num= NUMBER '<' id2= IDENT )+ ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalence?' id1= IDENT id2= IDENT |id0= IDENT '=' 'difference?' id1= IDENT id2= IDENT )+ )?
            {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:40:2: (id1= IDENT '=' pol )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==IDENT) ) {
                    int LA1_1 = input.LA(2);

                    if ( (LA1_1==16) ) {
                        switch ( input.LA(3) ) {
                        case 23:
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
                        case 24:
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
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:40:3: id1= IDENT '=' pol
            	    {
            	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program52); 

            	    match(input,16,FOLLOW_16_in_program54); 

            	    pushFollow(FOLLOW_pol_in_program56);
            	    pol1=pol();

            	    state._fsp--;


            	    pols.put((id1!=null?id1.getText():null), pol1);

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:41:2: (id2= IDENT '=' pSet )+
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
                        else if ( ((LA2_2 >= 23 && LA2_2 <= 24)) ) {
                            alt2=1;
                        }


                    }


                }


                switch (alt2) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:41:3: id2= IDENT '=' pSet
            	    {
            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program66); 

            	    match(input,16,FOLLOW_16_in_program68); 

            	    pushFollow(FOLLOW_pSet_in_program70);
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


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:42:2: (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' num= NUMBER '<' id2= IDENT )+
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
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:43:2: id0= IDENT '=' id2= IDENT '<=' num= NUMBER
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program82); 

            	    match(input,16,FOLLOW_16_in_program84); 

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program88); 

            	    match(input,15,FOLLOW_15_in_program90); 

            	    num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program94); 

            	    Condition cond = new LessThanThCondition(pSets.get((id2!=null?id2.getText():null)), Double.valueOf((num!=null?num.getText():null))); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;
            	case 2 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:45:2: id0= IDENT '=' num= NUMBER '<' id2= IDENT
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program108); 

            	    match(input,16,FOLLOW_16_in_program110); 

            	    num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program114); 

            	    match(input,14,FOLLOW_14_in_program116); 

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program120); 

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


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:2: ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalence?' id1= IDENT id2= IDENT |id0= IDENT '=' 'difference?' id1= IDENT id2= IDENT )+ )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==17) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:3: 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalence?' id1= IDENT id2= IDENT |id0= IDENT '=' 'difference?' id1= IDENT id2= IDENT )+
                    {
                    match(input,17,FOLLOW_17_in_program130); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:2: (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalence?' id1= IDENT id2= IDENT |id0= IDENT '=' 'difference?' id1= IDENT id2= IDENT )+
                    int cnt4=0;
                    loop4:
                    do {
                        int alt4=6;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==IDENT) ) {
                            int LA4_2 = input.LA(2);

                            if ( (LA4_2==16) ) {
                                switch ( input.LA(3) ) {
                                case 19:
                                    {
                                    alt4=1;
                                    }
                                    break;
                                case 18:
                                    {
                                    alt4=2;
                                    }
                                    break;
                                case 25:
                                    {
                                    alt4=3;
                                    }
                                    break;
                                case 22:
                                    {
                                    alt4=4;
                                    }
                                    break;
                                case 21:
                                    {
                                    alt4=5;
                                    }
                                    break;

                                }

                            }


                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:3: id0= IDENT '=' 'always_true?' id1= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program136); 

                    	    match(input,16,FOLLOW_16_in_program138); 

                    	    match(input,19,FOLLOW_19_in_program140); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program144); 

                    	    AnalysisGenerator analysis = new AlwaysTrue((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 2 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:49:3: id0= IDENT '=' 'always_false?' id1= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program152); 

                    	    match(input,16,FOLLOW_16_in_program154); 

                    	    match(input,18,FOLLOW_18_in_program156); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program160); 

                    	    AnalysisGenerator analysis = new AlwaysFalse((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 3 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:50:3: id0= IDENT '=' 'satisfiable?' id1= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program168); 

                    	    match(input,16,FOLLOW_16_in_program170); 

                    	    match(input,25,FOLLOW_25_in_program172); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program176); 

                    	    AnalysisGenerator analysis = new Satisfiable((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 4 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:51:3: id0= IDENT '=' 'equivalence?' id1= IDENT id2= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program185); 

                    	    match(input,16,FOLLOW_16_in_program187); 

                    	    match(input,22,FOLLOW_22_in_program189); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program193); 

                    	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program197); 

                    	    AnalysisGenerator analysis = new Equivalence((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 5 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:52:3: id0= IDENT '=' 'difference?' id1= IDENT id2= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program206); 

                    	    match(input,16,FOLLOW_16_in_program208); 

                    	    match(input,21,FOLLOW_21_in_program210); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program214); 

                    	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program218); 

                    	    AnalysisGenerator analysis = new Difference((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt4 >= 1 ) break loop4;
                                EarlyExitException eee =
                                    new EarlyExitException(4, input);
                                throw eee;
                        }
                        cnt4++;
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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:58:1: pSet returns [PolicySet t] : (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' id4= pSet ')' );
    public final PolicySet pSet() throws RecognitionException {
        PolicySet t = null;


        Token id1=null;
        Token id2=null;
        Token id3=null;
        PolicySet id4 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:2: (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' id4= pSet ')' )
            int alt6=5;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt6=1;
                }
                break;
            case 23:
                {
                int LA6_2 = input.LA(2);

                if ( (LA6_2==9) ) {
                    int LA6_4 = input.LA(3);

                    if ( (LA6_4==IDENT) ) {
                        int LA6_6 = input.LA(4);

                        if ( (LA6_6==13) ) {
                            int LA6_8 = input.LA(5);

                            if ( (LA6_8==IDENT) ) {
                                int LA6_10 = input.LA(6);

                                if ( (LA6_10==10) ) {
                                    alt6=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 6, 10, input);

                                    throw nvae;

                                }
                            }
                            else if ( ((LA6_8 >= 23 && LA6_8 <= 24)) ) {
                                alt6=3;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 6, 8, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 6, 6, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 4, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 2, input);

                    throw nvae;

                }
                }
                break;
            case 24:
                {
                int LA6_3 = input.LA(2);

                if ( (LA6_3==9) ) {
                    int LA6_5 = input.LA(3);

                    if ( (LA6_5==IDENT) ) {
                        int LA6_7 = input.LA(4);

                        if ( (LA6_7==13) ) {
                            int LA6_9 = input.LA(5);

                            if ( (LA6_9==IDENT) ) {
                                int LA6_12 = input.LA(6);

                                if ( (LA6_12==10) ) {
                                    alt6=4;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 6, 12, input);

                                    throw nvae;

                                }
                            }
                            else if ( ((LA6_9 >= 23 && LA6_9 <= 24)) ) {
                                alt6=5;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 6, 9, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 6, 7, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 5, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 3, input);

                    throw nvae;

                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }

            switch (alt6) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:4: id1= IDENT
                    {
                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet250); 

                    t = new BasicPolicySet(pols.get((id1!=null?id1.getText():null)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:60:4: 'max' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,23,FOLLOW_23_in_pSet257); 

                    match(input,9,FOLLOW_9_in_pSet259); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet263); 

                    match(input,13,FOLLOW_13_in_pSet265); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet269); 

                    match(input,10,FOLLOW_10_in_pSet271); 

                    t = new MaxPolicySet(new BasicPolicySet(pols.get((id1!=null?id1.getText():null))), new BasicPolicySet(pols.get((id2!=null?id2.getText():null))));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:61:4: 'max' '(' id3= IDENT ',' id4= pSet ')'
                    {
                    match(input,23,FOLLOW_23_in_pSet278); 

                    match(input,9,FOLLOW_9_in_pSet280); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet284); 

                    match(input,13,FOLLOW_13_in_pSet286); 

                    pushFollow(FOLLOW_pSet_in_pSet290);
                    id4=pSet();

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet292); 

                    t = new MaxPolicySet(new BasicPolicySet(pols.get((id3!=null?id3.getText():null))), id4);

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:62:4: 'min' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,24,FOLLOW_24_in_pSet299); 

                    match(input,9,FOLLOW_9_in_pSet301); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet305); 

                    match(input,13,FOLLOW_13_in_pSet307); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet311); 

                    match(input,10,FOLLOW_10_in_pSet313); 

                    t = new MinPolicySet(new BasicPolicySet(pols.get((id1!=null?id1.getText():null))), new BasicPolicySet(pols.get((id2!=null?id2.getText():null))));

                    }
                    break;
                case 5 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:63:4: 'min' '(' id3= IDENT ',' id4= pSet ')'
                    {
                    match(input,24,FOLLOW_24_in_pSet320); 

                    match(input,9,FOLLOW_9_in_pSet322); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet326); 

                    match(input,13,FOLLOW_13_in_pSet328); 

                    pushFollow(FOLLOW_pSet_in_pSet332);
                    id4=pSet();

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet334); 

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:66:1: pol returns [Pol p] : ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER | '*' '(' ( rule )* ')' 'default' NUMBER );
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:68:2: ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER | '*' '(' ( rule )* ')' 'default' NUMBER )
            int alt11=4;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt11=1;
                }
                break;
            case 23:
                {
                alt11=2;
                }
                break;
            case 24:
                {
                alt11=3;
                }
                break;
            case 11:
                {
                alt11=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }

            switch (alt11) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:68:5: '+' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,12,FOLLOW_12_in_pol358); 

                    match(input,9,FOLLOW_9_in_pol360); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:68:13: ( rule )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==9) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:68:14: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol363);
                    	    rule3=rule();

                    	    state._fsp--;


                    	    l.add(rule3);

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol369); 

                    match(input,20,FOLLOW_20_in_pol371); 

                    NUMBER4=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol373); 

                    p = new Pol(l, Plus$.MODULE$, Double.valueOf((NUMBER4!=null?NUMBER4.getText():null)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:69:4: 'max' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,23,FOLLOW_23_in_pol380); 

                    match(input,9,FOLLOW_9_in_pol382); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:69:14: ( rule )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==9) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:69:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol385);
                    	    rule5=rule();

                    	    state._fsp--;


                    	    l.add(rule5);

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol391); 

                    match(input,20,FOLLOW_20_in_pol393); 

                    NUMBER6=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol395); 

                    p = new Pol(l, Max$.MODULE$, Double.valueOf((NUMBER6!=null?NUMBER6.getText():null)));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:70:4: 'min' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,24,FOLLOW_24_in_pol402); 

                    match(input,9,FOLLOW_9_in_pol404); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:70:14: ( rule )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==9) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:70:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol407);
                    	    rule7=rule();

                    	    state._fsp--;


                    	    l.add(rule7);

                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol413); 

                    match(input,20,FOLLOW_20_in_pol415); 

                    NUMBER8=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol417); 

                    p = new Pol(l, Min$.MODULE$, Double.valueOf((NUMBER8!=null?NUMBER8.getText():null)));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:4: '*' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,11,FOLLOW_11_in_pol425); 

                    match(input,9,FOLLOW_9_in_pol427); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:12: ( rule )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==9) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:13: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol430);
                    	    rule9=rule();

                    	    state._fsp--;


                    	    l.add(rule9);

                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol436); 

                    match(input,20,FOLLOW_20_in_pol438); 

                    NUMBER10=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol440); 

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:1: rule returns [Rule r] : '(' IDENT NUMBER ')' ;
    public final Rule rule() throws RecognitionException {
        Rule r = null;


        Token IDENT11=null;
        Token NUMBER12=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:75:2: ( '(' IDENT NUMBER ')' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:75:4: '(' IDENT NUMBER ')'
            {
            match(input,9,FOLLOW_9_in_rule458); 

            IDENT11=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule460); 

            NUMBER12=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule462); 

            match(input,10,FOLLOW_10_in_rule464); 

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


 

    public static final BitSet FOLLOW_IDENT_in_program52 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program54 = new BitSet(new long[]{0x0000000001801800L});
    public static final BitSet FOLLOW_pol_in_program56 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program66 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program68 = new BitSet(new long[]{0x0000000001800020L});
    public static final BitSet FOLLOW_pSet_in_program70 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program82 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program84 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program88 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program90 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_program94 = new BitSet(new long[]{0x0000000000020022L});
    public static final BitSet FOLLOW_IDENT_in_program108 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program110 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_program114 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program116 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program120 = new BitSet(new long[]{0x0000000000020022L});
    public static final BitSet FOLLOW_17_in_program130 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program136 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program138 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_program140 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program144 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_program152 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program154 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_program156 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program160 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_program168 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program170 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_program172 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program176 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_program185 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program187 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_program189 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program193 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program197 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_program206 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program208 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_program210 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program214 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program218 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_pSet250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_pSet257 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet259 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet263 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet265 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet269 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_pSet278 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet280 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet284 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet286 = new BitSet(new long[]{0x0000000001800020L});
    public static final BitSet FOLLOW_pSet_in_pSet290 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_pSet299 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet301 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet305 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet307 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet311 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_pSet320 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet322 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet326 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet328 = new BitSet(new long[]{0x0000000001800020L});
    public static final BitSet FOLLOW_pSet_in_pSet332 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_pol358 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol360 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol363 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol369 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_pol371 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_pol380 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol382 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol385 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol391 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_pol393 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_pol402 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol404 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol407 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol413 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_pol415 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_pol425 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol427 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol430 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol436 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_pol438 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule458 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_rule460 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_rule462 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule464 = new BitSet(new long[]{0x0000000000000002L});

}