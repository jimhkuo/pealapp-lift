// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-09-19 10:50:20

package peal.antlr;
import java.util.*;
import peal.domain.*;
import peal.antlr.util.*;
import peal.*;
import org.antlr.runtime.BitSet;
import peal.synthesis.*;
import peal.synthesis.analysis.*;
import peal.domain.operator.*;
import scala.math.BigDecimal;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PealProgramParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENT", "NEWLINE", "NUMBER", "WS", "'('", "')'", "'*'", "'+'", "','", "'<'", "'<='", "'='", "'ANALYSES'", "'CONDITIONS'", "'DOMAIN_SPECIFICS'", "'POLICIES'", "'POLICY_SETS'", "'always_false?'", "'always_true?'", "'default'", "'different?'", "'equivalent?'", "'max'", "'min'", "'satisfiable?'"
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
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int IDENT=4;
    public static final int NEWLINE=5;
    public static final int NUMBER=6;
    public static final int WS=7;

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:45:1: program : ( 'POLICIES' )? ( pol )* ( 'POLICY_SETS' )? (id2= IDENT '=' pSet )+ ( 'CONDITIONS' )? (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' num= NUMBER '<' id2= IDENT )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT )+ )? ;
    public final void program() throws RecognitionException {
        Token id2=null;
        Token id0=null;
        Token num=null;
        Token id1=null;
        Pol pol1 =null;

        PolicySet pSet2 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:46:2: ( ( 'POLICIES' )? ( pol )* ( 'POLICY_SETS' )? (id2= IDENT '=' pSet )+ ( 'CONDITIONS' )? (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' num= NUMBER '<' id2= IDENT )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT )+ )? )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:2: ( 'POLICIES' )? ( pol )* ( 'POLICY_SETS' )? (id2= IDENT '=' pSet )+ ( 'CONDITIONS' )? (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' num= NUMBER '<' id2= IDENT )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT )+ )?
            {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:2: ( 'POLICIES' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==19) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:3: 'POLICIES'
                    {
                    match(input,19,FOLLOW_19_in_program57); 

                    }
                    break;

            }


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:2: ( pol )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==IDENT) ) {
                    int LA2_2 = input.LA(2);

                    if ( (LA2_2==15) ) {
                        switch ( input.LA(3) ) {
                        case 10:
                        case 11:
                            {
                            alt2=1;
                            }
                            break;
                        case 26:
                            {
                            int LA2_5 = input.LA(4);

                            if ( (LA2_5==8) ) {
                                int LA2_7 = input.LA(5);

                                if ( ((LA2_7 >= 8 && LA2_7 <= 9)) ) {
                                    alt2=1;
                                }


                            }


                            }
                            break;
                        case 27:
                            {
                            int LA2_6 = input.LA(4);

                            if ( (LA2_6==8) ) {
                                int LA2_8 = input.LA(5);

                                if ( ((LA2_8 >= 8 && LA2_8 <= 9)) ) {
                                    alt2=1;
                                }


                            }


                            }
                            break;

                        }

                    }


                }


                switch (alt2) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:3: pol
            	    {
            	    pushFollow(FOLLOW_pol_in_program63);
            	    pol1=pol();

            	    state._fsp--;


            	    pols.put(pol1.getName(), pol1);

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:49:2: ( 'POLICY_SETS' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==20) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:49:3: 'POLICY_SETS'
                    {
                    match(input,20,FOLLOW_20_in_program71); 

                    }
                    break;

            }


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:50:2: (id2= IDENT '=' pSet )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==IDENT) ) {
                    int LA4_2 = input.LA(2);

                    if ( (LA4_2==15) ) {
                        int LA4_3 = input.LA(3);

                        if ( (LA4_3==IDENT) ) {
                            int LA4_4 = input.LA(4);

                            if ( (LA4_4==IDENT||LA4_4==17) ) {
                                alt4=1;
                            }


                        }
                        else if ( ((LA4_3 >= 26 && LA4_3 <= 27)) ) {
                            alt4=1;
                        }


                    }


                }


                switch (alt4) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:50:3: id2= IDENT '=' pSet
            	    {
            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program79); 

            	    match(input,15,FOLLOW_15_in_program81); 

            	    pushFollow(FOLLOW_pSet_in_program83);
            	    pSet2=pSet();

            	    state._fsp--;


            	     pSets.put((id2!=null?id2.getText():null), pSet2);

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


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:51:2: ( 'CONDITIONS' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==17) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:51:3: 'CONDITIONS'
                    {
                    match(input,17,FOLLOW_17_in_program91); 

                    }
                    break;

            }


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:52:2: (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' num= NUMBER '<' id2= IDENT )+
            int cnt6=0;
            loop6:
            do {
                int alt6=3;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==IDENT) ) {
                    int LA6_2 = input.LA(2);

                    if ( (LA6_2==15) ) {
                        int LA6_3 = input.LA(3);

                        if ( (LA6_3==IDENT) ) {
                            alt6=1;
                        }
                        else if ( (LA6_3==NUMBER) ) {
                            alt6=2;
                        }


                    }


                }


                switch (alt6) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:53:2: id0= IDENT '=' id2= IDENT '<=' num= NUMBER
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program101); 

            	    match(input,15,FOLLOW_15_in_program103); 

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program107); 

            	    match(input,14,FOLLOW_14_in_program109); 

            	    num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program113); 

            	    Condition cond = new LessThanThCondition(pSets.get((id2!=null?id2.getText():null)), BigDecimal.valueOf(Double.valueOf((num!=null?num.getText():null)))); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;
            	case 2 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:55:2: id0= IDENT '=' num= NUMBER '<' id2= IDENT
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program127); 

            	    match(input,15,FOLLOW_15_in_program129); 

            	    num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program133); 

            	    match(input,13,FOLLOW_13_in_program135); 

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program139); 

            	    Condition cond = new GreaterThanThCondition(pSets.get((id2!=null?id2.getText():null)), BigDecimal.valueOf(Double.valueOf((num!=null?num.getText():null)))); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:57:2: ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==18) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:57:3: 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
                    {
                    match(input,18,FOLLOW_18_in_program149); 

                    ignore = true;

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:58:2: ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==IDENT||LA7_0==NUMBER||(LA7_0 >= 8 && LA7_0 <= 11)||(LA7_0 >= 13 && LA7_0 <= 15)) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:
                    	    {
                    	    if ( input.LA(1)==IDENT||input.LA(1)==NUMBER||(input.LA(1) >= 8 && input.LA(1) <= 11)||(input.LA(1) >= 13 && input.LA(1) <= 15) ) {
                    	        input.consume();
                    	        state.errorRecovery=false;
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    }
                    break;

            }


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:2: ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT )+ )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==16) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:3: 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT )+
                    {
                    match(input,16,FOLLOW_16_in_program196); 

                    ignore = false;

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:60:2: (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=6;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==IDENT) ) {
                            int LA9_2 = input.LA(2);

                            if ( (LA9_2==15) ) {
                                switch ( input.LA(3) ) {
                                case 22:
                                    {
                                    alt9=1;
                                    }
                                    break;
                                case 21:
                                    {
                                    alt9=2;
                                    }
                                    break;
                                case 28:
                                    {
                                    alt9=3;
                                    }
                                    break;
                                case 25:
                                    {
                                    alt9=4;
                                    }
                                    break;
                                case 24:
                                    {
                                    alt9=5;
                                    }
                                    break;

                                }

                            }


                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:60:3: id0= IDENT '=' 'always_true?' id1= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program204); 

                    	    match(input,15,FOLLOW_15_in_program206); 

                    	    match(input,22,FOLLOW_22_in_program208); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program212); 

                    	    AnalysisGenerator analysis = new AlwaysTrue((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 2 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:61:3: id0= IDENT '=' 'always_false?' id1= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program220); 

                    	    match(input,15,FOLLOW_15_in_program222); 

                    	    match(input,21,FOLLOW_21_in_program224); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program228); 

                    	    AnalysisGenerator analysis = new AlwaysFalse((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 3 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:62:3: id0= IDENT '=' 'satisfiable?' id1= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program236); 

                    	    match(input,15,FOLLOW_15_in_program238); 

                    	    match(input,28,FOLLOW_28_in_program240); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program244); 

                    	    AnalysisGenerator analysis = new Satisfiable((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 4 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:63:3: id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program253); 

                    	    match(input,15,FOLLOW_15_in_program255); 

                    	    match(input,25,FOLLOW_25_in_program257); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program261); 

                    	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program265); 

                    	    AnalysisGenerator analysis = new Equivalent((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 5 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:64:3: id0= IDENT '=' 'different?' id1= IDENT id2= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program274); 

                    	    match(input,15,FOLLOW_15_in_program276); 

                    	    match(input,24,FOLLOW_24_in_program278); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program282); 

                    	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program286); 

                    	    AnalysisGenerator analysis = new Different((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt9 >= 1 ) break loop9;
                                EarlyExitException eee =
                                    new EarlyExitException(9, input);
                                throw eee;
                        }
                        cnt9++;
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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:69:1: pSet returns [PolicySet t] : (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' );
    public final PolicySet pSet() throws RecognitionException {
        PolicySet t = null;


        Token id1=null;
        Token id2=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:70:2: (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' )
            int alt11=3;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt11=1;
                }
                break;
            case 26:
                {
                alt11=2;
                }
                break;
            case 27:
                {
                alt11=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }

            switch (alt11) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:70:4: id1= IDENT
                    {
                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet317); 

                    t = new BasicPolicySet(pols.get((id1!=null?id1.getText():null)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:4: 'max' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,26,FOLLOW_26_in_pSet324); 

                    match(input,8,FOLLOW_8_in_pSet326); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet330); 

                    match(input,12,FOLLOW_12_in_pSet332); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet336); 

                    match(input,9,FOLLOW_9_in_pSet338); 

                    t = new MaxPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null)));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:4: 'min' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,27,FOLLOW_27_in_pSet345); 

                    match(input,8,FOLLOW_8_in_pSet347); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet351); 

                    match(input,12,FOLLOW_12_in_pSet353); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet357); 

                    match(input,9,FOLLOW_9_in_pSet359); 

                    t = new MinPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null)));

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:75:1: pol returns [Pol p] : (id1= IDENT '=' '+' '(' ( rule )* ')' 'default' NUMBER |id1= IDENT '=' 'max' '(' ( rule )* ')' 'default' NUMBER |id1= IDENT '=' 'min' '(' ( rule )* ')' 'default' NUMBER |id1= IDENT '=' '*' '(' ( rule )* ')' 'default' NUMBER );
    public final Pol pol() throws RecognitionException {
        Pol p = null;


        Token id1=null;
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:77:2: (id1= IDENT '=' '+' '(' ( rule )* ')' 'default' NUMBER |id1= IDENT '=' 'max' '(' ( rule )* ')' 'default' NUMBER |id1= IDENT '=' 'min' '(' ( rule )* ')' 'default' NUMBER |id1= IDENT '=' '*' '(' ( rule )* ')' 'default' NUMBER )
            int alt16=4;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==IDENT) ) {
                int LA16_1 = input.LA(2);

                if ( (LA16_1==15) ) {
                    switch ( input.LA(3) ) {
                    case 11:
                        {
                        alt16=1;
                        }
                        break;
                    case 26:
                        {
                        alt16=2;
                        }
                        break;
                    case 27:
                        {
                        alt16=3;
                        }
                        break;
                    case 10:
                        {
                        alt16=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 16, 2, input);

                        throw nvae;

                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 16, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }
            switch (alt16) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:77:3: id1= IDENT '=' '+' '(' ( rule )* ')' 'default' NUMBER
                    {
                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pol383); 

                    match(input,15,FOLLOW_15_in_pol385); 

                    match(input,11,FOLLOW_11_in_pol389); 

                    match(input,8,FOLLOW_8_in_pol391); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:77:27: ( rule )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==8) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:77:28: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol394);
                    	    rule3=rule();

                    	    state._fsp--;


                    	    l.add(rule3);

                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);


                    match(input,9,FOLLOW_9_in_pol400); 

                    match(input,23,FOLLOW_23_in_pol402); 

                    NUMBER4=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol404); 

                    p = new Pol(l, Plus$.MODULE$, BigDecimal.valueOf(Double.valueOf((NUMBER4!=null?NUMBER4.getText():null))), (id1!=null?id1.getText():null));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:78:3: id1= IDENT '=' 'max' '(' ( rule )* ')' 'default' NUMBER
                    {
                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pol412); 

                    match(input,15,FOLLOW_15_in_pol414); 

                    match(input,26,FOLLOW_26_in_pol417); 

                    match(input,8,FOLLOW_8_in_pol419); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:78:28: ( rule )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==8) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:78:29: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol422);
                    	    rule5=rule();

                    	    state._fsp--;


                    	    l.add(rule5);

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);


                    match(input,9,FOLLOW_9_in_pol428); 

                    match(input,23,FOLLOW_23_in_pol430); 

                    NUMBER6=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol432); 

                    p = new Pol(l, Max$.MODULE$, BigDecimal.valueOf(Double.valueOf((NUMBER6!=null?NUMBER6.getText():null))),  (id1!=null?id1.getText():null));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:79:3: id1= IDENT '=' 'min' '(' ( rule )* ')' 'default' NUMBER
                    {
                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pol440); 

                    match(input,15,FOLLOW_15_in_pol442); 

                    match(input,27,FOLLOW_27_in_pol445); 

                    match(input,8,FOLLOW_8_in_pol447); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:79:28: ( rule )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==8) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:79:29: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol450);
                    	    rule7=rule();

                    	    state._fsp--;


                    	    l.add(rule7);

                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);


                    match(input,9,FOLLOW_9_in_pol456); 

                    match(input,23,FOLLOW_23_in_pol458); 

                    NUMBER8=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol460); 

                    p = new Pol(l, Min$.MODULE$, BigDecimal.valueOf(Double.valueOf((NUMBER8!=null?NUMBER8.getText():null))), (id1!=null?id1.getText():null));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:80:3: id1= IDENT '=' '*' '(' ( rule )* ')' 'default' NUMBER
                    {
                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pol469); 

                    match(input,15,FOLLOW_15_in_pol471); 

                    match(input,10,FOLLOW_10_in_pol474); 

                    match(input,8,FOLLOW_8_in_pol476); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:80:26: ( rule )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==8) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:80:27: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol479);
                    	    rule9=rule();

                    	    state._fsp--;


                    	    l.add(rule9);

                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);


                    match(input,9,FOLLOW_9_in_pol485); 

                    match(input,23,FOLLOW_23_in_pol487); 

                    NUMBER10=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol489); 

                    p = new Pol(l, Mul$.MODULE$, BigDecimal.valueOf(Double.valueOf((NUMBER10!=null?NUMBER10.getText():null))), (id1!=null?id1.getText():null));

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:83:1: rule returns [Rule r] : ( '(' IDENT NUMBER ')' | '(' id0= IDENT id1= IDENT ')' );
    public final Rule rule() throws RecognitionException {
        Rule r = null;


        Token id0=null;
        Token id1=null;
        Token IDENT11=null;
        Token NUMBER12=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:84:2: ( '(' IDENT NUMBER ')' | '(' id0= IDENT id1= IDENT ')' )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==8) ) {
                int LA17_1 = input.LA(2);

                if ( (LA17_1==IDENT) ) {
                    int LA17_2 = input.LA(3);

                    if ( (LA17_2==NUMBER) ) {
                        alt17=1;
                    }
                    else if ( (LA17_2==IDENT) ) {
                        alt17=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 17, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }
            switch (alt17) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:84:4: '(' IDENT NUMBER ')'
                    {
                    match(input,8,FOLLOW_8_in_rule507); 

                    IDENT11=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule509); 

                    NUMBER12=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule511); 

                    match(input,9,FOLLOW_9_in_rule513); 

                    r = new Rule(new Predicate((IDENT11!=null?IDENT11.getText():null)),BigDecimal.valueOf(Double.valueOf((NUMBER12!=null?NUMBER12.getText():null))), "");

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:85:4: '(' id0= IDENT id1= IDENT ')'
                    {
                    match(input,8,FOLLOW_8_in_rule520); 

                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule524); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule528); 

                    match(input,9,FOLLOW_9_in_rule529); 

                    r = new Rule(new Predicate((id0!=null?id0.getText():null)),BigDecimal.valueOf(Double.valueOf(-9999.0)), (id1!=null?id1.getText():null));

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
        return r;
    }
    // $ANTLR end "rule"

    // Delegated rules


 

    public static final BitSet FOLLOW_19_in_program57 = new BitSet(new long[]{0x0000000000100010L});
    public static final BitSet FOLLOW_pol_in_program63 = new BitSet(new long[]{0x0000000000100010L});
    public static final BitSet FOLLOW_20_in_program71 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program79 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program81 = new BitSet(new long[]{0x000000000C000010L});
    public static final BitSet FOLLOW_pSet_in_program83 = new BitSet(new long[]{0x0000000000020010L});
    public static final BitSet FOLLOW_17_in_program91 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program101 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program103 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program107 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program109 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_program113 = new BitSet(new long[]{0x0000000000050012L});
    public static final BitSet FOLLOW_IDENT_in_program127 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program129 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_program133 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_program135 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program139 = new BitSet(new long[]{0x0000000000050012L});
    public static final BitSet FOLLOW_18_in_program149 = new BitSet(new long[]{0x000000000001EF52L});
    public static final BitSet FOLLOW_16_in_program196 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program204 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program206 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_program208 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program212 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_IDENT_in_program220 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program222 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_program224 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program228 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_IDENT_in_program236 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program238 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_program240 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program244 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_IDENT_in_program253 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program255 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_program257 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program261 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program265 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_IDENT_in_program274 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program276 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_program278 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program282 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program286 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_IDENT_in_pSet317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_pSet324 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_pSet326 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_pSet330 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_pSet332 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_pSet336 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_pSet345 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_pSet347 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_pSet351 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_pSet353 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_pSet357 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pol383 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_pol385 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_pol389 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_pol391 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_rule_in_pol394 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_9_in_pol400 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_pol402 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_pol404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pol412 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_pol414 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_pol417 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_pol419 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_rule_in_pol422 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_9_in_pol428 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_pol430 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_pol432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pol440 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_pol442 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_pol445 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_pol447 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_rule_in_pol450 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_9_in_pol456 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_pol458 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_pol460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pol469 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_pol471 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pol474 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_pol476 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_rule_in_pol479 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_9_in_pol485 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_pol487 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_pol489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_rule507 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_rule509 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_rule511 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_rule513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_rule520 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_rule524 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_rule528 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_rule529 = new BitSet(new long[]{0x0000000000000002L});

}