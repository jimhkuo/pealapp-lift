// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-10-23 10:39:25

package peal.antlr;
import java.util.*;
import peal.domain.*;
import peal.antlr.util.*;
import org.antlr.runtime.BitSet;
import peal.synthesis.*;
import peal.synthesis.analysis.*;
import peal.domain.operator.*;
import scala.math.BigDecimal;
import scala.util.*;


import org.antlr.runtime.*;

import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PealProgramParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENT", "NUMBER", "WS", "'!'", "'&&'", "'('", "')'", "'*'", "'+'", "','", "'<'", "'<='", "'='", "'ANALYSES'", "'CONDITIONS'", "'DOMAIN_SPECIFICS'", "'POLICIES'", "'POLICY_SETS'", "'always_false?'", "'always_true?'", "'default'", "'different?'", "'equivalent?'", "'false'", "'implies?'", "'max'", "'min'", "'satisfiable?'", "'true'", "'||'"
    };

    public static final int EOF=-1;
    public static final int T__7=7;
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
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int IDENT=4;
    public static final int NUMBER=5;
    public static final int WS=6;

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:46:1: program : ( 'POLICIES' )? ( pol )* ( 'POLICY_SETS' )? ( pSet )+ ( 'CONDITIONS' )? (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )? ;
    public final void program() throws RecognitionException {
        Token id0=null;
        Token id2=null;
        Token num=null;
        Token id3=null;
        Token id1=null;
        Pol pol1 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:2: ( ( 'POLICIES' )? ( pol )* ( 'POLICY_SETS' )? ( pSet )+ ( 'CONDITIONS' )? (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )? )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:2: ( 'POLICIES' )? ( pol )* ( 'POLICY_SETS' )? ( pSet )+ ( 'CONDITIONS' )? (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )?
            {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:2: ( 'POLICIES' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==20) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:3: 'POLICIES'
                    {
                    match(input,20,FOLLOW_20_in_program57); 

                    }
                    break;

            }


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:49:2: ( pol )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==IDENT) ) {
                    int LA2_2 = input.LA(2);

                    if ( (LA2_2==16) ) {
                        switch ( input.LA(3) ) {
                        case 29:
                            {
                            int LA2_4 = input.LA(4);

                            if ( (LA2_4==9) ) {
                                int LA2_7 = input.LA(5);

                                if ( ((LA2_7 >= 9 && LA2_7 <= 10)) ) {
                                    alt2=1;
                                }


                            }


                            }
                            break;
                        case 30:
                            {
                            int LA2_5 = input.LA(4);

                            if ( (LA2_5==9) ) {
                                int LA2_8 = input.LA(5);

                                if ( ((LA2_8 >= 9 && LA2_8 <= 10)) ) {
                                    alt2=1;
                                }


                            }


                            }
                            break;
                        case 11:
                        case 12:
                            {
                            alt2=1;
                            }
                            break;

                        }

                    }


                }


                switch (alt2) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:49:3: pol
            	    {
            	    pushFollow(FOLLOW_pol_in_program63);
            	    pol1=pol();

            	    state._fsp--;


            	    pols.put(pol1.getPolicyName(), pol1);

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:50:2: ( 'POLICY_SETS' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==21) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:50:3: 'POLICY_SETS'
                    {
                    match(input,21,FOLLOW_21_in_program71); 

                    }
                    break;

            }


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:51:2: ( pSet )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==IDENT) ) {
                    int LA4_2 = input.LA(2);

                    if ( (LA4_2==16) ) {
                        int LA4_3 = input.LA(3);

                        if ( (LA4_3==IDENT) ) {
                            int LA4_4 = input.LA(4);

                            if ( (LA4_4==IDENT||LA4_4==18) ) {
                                alt4=1;
                            }


                        }
                        else if ( ((LA4_3 >= 29 && LA4_3 <= 30)) ) {
                            alt4=1;
                        }


                    }


                }


                switch (alt4) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:51:3: pSet
            	    {
            	    pushFollow(FOLLOW_pSet_in_program77);
            	    pSet();

            	    state._fsp--;


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


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:53:2: ( 'CONDITIONS' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==18) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:53:3: 'CONDITIONS'
                    {
                    match(input,18,FOLLOW_18_in_program84); 

                    }
                    break;

            }


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:54:2: (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' id3= IDENT '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=10;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==IDENT) ) {
                    int LA6_2 = input.LA(2);

                    if ( (LA6_2==16) ) {
                        switch ( input.LA(3) ) {
                        case IDENT:
                            {
                            switch ( input.LA(4) ) {
                            case 15:
                                {
                                int LA6_9 = input.LA(5);

                                if ( (LA6_9==NUMBER) ) {
                                    alt6=1;
                                }
                                else if ( (LA6_9==IDENT) ) {
                                    alt6=2;
                                }


                                }
                                break;
                            case 14:
                                {
                                alt6=4;
                                }
                                break;
                            case 8:
                                {
                                alt6=6;
                                }
                                break;
                            case 33:
                                {
                                alt6=7;
                                }
                                break;

                            }

                            }
                            break;
                        case NUMBER:
                            {
                            alt6=3;
                            }
                            break;
                        case 7:
                            {
                            alt6=5;
                            }
                            break;
                        case 32:
                            {
                            alt6=8;
                            }
                            break;
                        case 27:
                            {
                            alt6=9;
                            }
                            break;

                        }

                    }


                }


                switch (alt6) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:55:2: id0= IDENT '=' id2= IDENT '<=' num= NUMBER
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program94); 

            	    match(input,16,FOLLOW_16_in_program96); 

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program100); 

            	    match(input,15,FOLLOW_15_in_program102); 

            	    num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program106); 

            	    Condition cond = new LessThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Left<BigDecimal,PolicySet>(BigDecimal.valueOf(Double.valueOf((num!=null?num.getText():null))))); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;
            	case 2 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:57:2: id0= IDENT '=' id2= IDENT '<=' id3= IDENT
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program120); 

            	    match(input,16,FOLLOW_16_in_program122); 

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program126); 

            	    match(input,15,FOLLOW_15_in_program128); 

            	    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_program132); 

            	    Condition cond = new LessThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Right<BigDecimal,PolicySet>(pSets.get((id3!=null?id3.getText():null)))); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;
            	case 3 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:2: id0= IDENT '=' num= NUMBER '<' id2= IDENT
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program146); 

            	    match(input,16,FOLLOW_16_in_program148); 

            	    num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program152); 

            	    match(input,14,FOLLOW_14_in_program154); 

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program158); 

            	    Condition cond = new GreaterThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Left<BigDecimal,PolicySet>(BigDecimal.valueOf(Double.valueOf((num!=null?num.getText():null))))); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;
            	case 4 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:61:2: id0= IDENT '=' id3= IDENT '<' id2= IDENT
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program168); 

            	    match(input,16,FOLLOW_16_in_program170); 

            	    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_program174); 

            	    match(input,14,FOLLOW_14_in_program176); 

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program180); 

            	    Condition cond = new GreaterThanThCondition(pSets.get((id2!=null?id2.getText():null)), new Right<BigDecimal,PolicySet>(pSets.get((id3!=null?id3.getText():null)))); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;
            	case 5 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:63:2: id0= IDENT '=' '!' id1= IDENT
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program194); 

            	    match(input,16,FOLLOW_16_in_program196); 

            	    match(input,7,FOLLOW_7_in_program198); 

            	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program202); 

            	    Condition cond = new NotCondition((id1!=null?id1.getText():null)); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;
            	case 6 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:65:2: id0= IDENT '=' id1= IDENT '&&' id2= IDENT
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program212); 

            	    match(input,16,FOLLOW_16_in_program214); 

            	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program218); 

            	    match(input,8,FOLLOW_8_in_program220); 

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program224); 

            	    Condition cond = new AndCondition((id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;
            	case 7 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:67:2: id0= IDENT '=' id1= IDENT '||' id2= IDENT
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program234); 

            	    match(input,16,FOLLOW_16_in_program236); 

            	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program240); 

            	    match(input,33,FOLLOW_33_in_program242); 

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program246); 

            	    Condition cond = new OrCondition((id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;
            	case 8 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:69:2: id0= IDENT '=' 'true'
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program257); 

            	    match(input,16,FOLLOW_16_in_program259); 

            	    match(input,32,FOLLOW_32_in_program261); 

            	    Condition cond = new TrueCondition(); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;
            	case 9 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:2: id0= IDENT '=' 'false'
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program272); 

            	    match(input,16,FOLLOW_16_in_program274); 

            	    match(input,27,FOLLOW_27_in_program276); 

            	    Condition cond = new FalseCondition(); conds.put((id0!=null?id0.getText():null), cond);

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


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:73:2: ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==19) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:73:3: 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
                    {
                    match(input,19,FOLLOW_19_in_program286); 

                    ignore = true;

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:2: ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0 >= IDENT && LA7_0 <= NUMBER)||(LA7_0 >= 9 && LA7_0 <= 12)||(LA7_0 >= 14 && LA7_0 <= 16)) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:
                    	    {
                    	    if ( (input.LA(1) >= IDENT && input.LA(1) <= NUMBER)||(input.LA(1) >= 9 && input.LA(1) <= 12)||(input.LA(1) >= 14 && input.LA(1) <= 16) ) {
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


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:75:2: ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==17) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:75:3: 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+
                    {
                    match(input,17,FOLLOW_17_in_program333); 

                    ignore = false;

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:76:2: (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=7;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==IDENT) ) {
                            int LA9_2 = input.LA(2);

                            if ( (LA9_2==16) ) {
                                switch ( input.LA(3) ) {
                                case 23:
                                    {
                                    alt9=1;
                                    }
                                    break;
                                case 22:
                                    {
                                    alt9=2;
                                    }
                                    break;
                                case 31:
                                    {
                                    alt9=3;
                                    }
                                    break;
                                case 26:
                                    {
                                    alt9=4;
                                    }
                                    break;
                                case 25:
                                    {
                                    alt9=5;
                                    }
                                    break;
                                case 28:
                                    {
                                    alt9=6;
                                    }
                                    break;

                                }

                            }


                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:76:3: id0= IDENT '=' 'always_true?' id1= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program341); 

                    	    match(input,16,FOLLOW_16_in_program343); 

                    	    match(input,23,FOLLOW_23_in_program345); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program349); 

                    	    AnalysisGenerator analysis = new AlwaysTrue((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 2 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:77:3: id0= IDENT '=' 'always_false?' id1= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program357); 

                    	    match(input,16,FOLLOW_16_in_program359); 

                    	    match(input,22,FOLLOW_22_in_program361); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program365); 

                    	    AnalysisGenerator analysis = new AlwaysFalse((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 3 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:78:3: id0= IDENT '=' 'satisfiable?' id1= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program373); 

                    	    match(input,16,FOLLOW_16_in_program375); 

                    	    match(input,31,FOLLOW_31_in_program377); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program381); 

                    	    AnalysisGenerator analysis = new Satisfiable((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 4 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:79:3: id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program390); 

                    	    match(input,16,FOLLOW_16_in_program392); 

                    	    match(input,26,FOLLOW_26_in_program394); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program398); 

                    	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program402); 

                    	    AnalysisGenerator analysis = new Equivalent((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 5 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:80:3: id0= IDENT '=' 'different?' id1= IDENT id2= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program411); 

                    	    match(input,16,FOLLOW_16_in_program413); 

                    	    match(input,25,FOLLOW_25_in_program415); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program419); 

                    	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program423); 

                    	    AnalysisGenerator analysis = new Different((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 6 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:81:3: id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program433); 

                    	    match(input,16,FOLLOW_16_in_program435); 

                    	    match(input,28,FOLLOW_28_in_program437); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program441); 

                    	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program445); 

                    	    AnalysisGenerator analysis = new Implies((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:86:1: pSet : (id0= IDENT '=' id1= IDENT |id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' );
    public final void pSet() throws RecognitionException {
        Token id0=null;
        Token id1=null;
        Token id2=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:87:2: (id0= IDENT '=' id1= IDENT |id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' )
            int alt11=3;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==IDENT) ) {
                int LA11_1 = input.LA(2);

                if ( (LA11_1==16) ) {
                    switch ( input.LA(3) ) {
                    case IDENT:
                        {
                        alt11=1;
                        }
                        break;
                    case 29:
                        {
                        alt11=2;
                        }
                        break;
                    case 30:
                        {
                        alt11=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 2, input);

                        throw nvae;

                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }
            switch (alt11) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:87:3: id0= IDENT '=' id1= IDENT
                    {
                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet471); 

                    match(input,16,FOLLOW_16_in_pSet473); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet477); 

                    PolicySet p = new BasicPolicySet(pols.get((id1!=null?id1.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:88:3: id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet485); 

                    match(input,16,FOLLOW_16_in_pSet487); 

                    match(input,29,FOLLOW_29_in_pSet489); 

                    match(input,9,FOLLOW_9_in_pSet491); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet495); 

                    match(input,13,FOLLOW_13_in_pSet497); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet501); 

                    match(input,10,FOLLOW_10_in_pSet503); 

                    PolicySet p = new MaxPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:89:3: id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet511); 

                    match(input,16,FOLLOW_16_in_pSet513); 

                    match(input,30,FOLLOW_30_in_pSet515); 

                    match(input,9,FOLLOW_9_in_pSet517); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet521); 

                    match(input,13,FOLLOW_13_in_pSet523); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet527); 

                    match(input,10,FOLLOW_10_in_pSet529); 

                    PolicySet p = new MinPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null)), (id0!=null?id0.getText():null)); pSets.put((id0!=null?id0.getText():null), p);

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



    // $ANTLR start "pol"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:92:1: pol returns [Pol p] : id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' (n= NUMBER |n= NUMBER '*' id2= IDENT |id2= IDENT |id2= IDENT '*' n= NUMBER ) ;
    public final Pol pol() throws RecognitionException {
        Pol p = null;


        Token id1=null;
        Token n=null;
        Token id2=null;
        PealProgramParser.operator_return o =null;

        Rule rule2 =null;


        l = new ArrayList<Rule>(); 
        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:94:2: (id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' (n= NUMBER |n= NUMBER '*' id2= IDENT |id2= IDENT |id2= IDENT '*' n= NUMBER ) )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:94:4: id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' (n= NUMBER |n= NUMBER '*' id2= IDENT |id2= IDENT |id2= IDENT '*' n= NUMBER )
            {
            id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pol554); 

            match(input,16,FOLLOW_16_in_pol556); 

            pushFollow(FOLLOW_operator_in_pol560);
            o=operator();

            state._fsp--;


            match(input,9,FOLLOW_9_in_pol562); 

            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:94:33: ( rule )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==9) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:94:34: rule
            	    {
            	    pushFollow(FOLLOW_rule_in_pol565);
            	    rule2=rule();

            	    state._fsp--;


            	    l.add(rule2);

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            match(input,10,FOLLOW_10_in_pol571); 

            match(input,24,FOLLOW_24_in_pol573); 

            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:94:73: (n= NUMBER |n= NUMBER '*' id2= IDENT |id2= IDENT |id2= IDENT '*' n= NUMBER )
            int alt13=4;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==NUMBER) ) {
                int LA13_1 = input.LA(2);

                if ( (LA13_1==11) ) {
                    alt13=2;
                }
                else if ( (LA13_1==IDENT||LA13_1==21) ) {
                    alt13=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA13_0==IDENT) ) {
                int LA13_2 = input.LA(2);

                if ( (LA13_2==11) ) {
                    alt13=4;
                }
                else if ( (LA13_2==IDENT||LA13_2==21) ) {
                    alt13=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 2, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }
            switch (alt13) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:94:74: n= NUMBER
                    {
                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol578); 

                    p = new Pol(l, OperatorResolver.apply((o!=null?input.toString(o.start,o.stop):null)), new Left<BigDecimal, Multiplier>(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null)))), (id1!=null?id1.getText():null));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:96:10: n= NUMBER '*' id2= IDENT
                    {
                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol604); 

                    match(input,11,FOLLOW_11_in_pol606); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pol610); 

                    p = new Pol(l, OperatorResolver.apply((o!=null?input.toString(o.start,o.stop):null)), new Right<BigDecimal, Multiplier>(new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id2!=null?id2.getText():null))), (id1!=null?id1.getText():null));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:98:10: id2= IDENT
                    {
                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pol636); 

                    p = new Pol(l, OperatorResolver.apply((o!=null?input.toString(o.start,o.stop):null)), new Right<BigDecimal, Multiplier>(new Multiplier(BigDecimal.valueOf(1), (id2!=null?id2.getText():null))), (id1!=null?id1.getText():null));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:100:10: id2= IDENT '*' n= NUMBER
                    {
                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pol662); 

                    match(input,11,FOLLOW_11_in_pol664); 

                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol668); 

                    p = new Pol(l, OperatorResolver.apply((o!=null?input.toString(o.start,o.stop):null)), new Right<BigDecimal, Multiplier>(new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id2!=null?id2.getText():null))), (id1!=null?id1.getText():null));

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
        return p;
    }
    // $ANTLR end "pol"



    // $ANTLR start "rule"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:104:1: rule returns [Rule r] : ( '(' IDENT NUMBER ')' | '(' id0= IDENT id1= IDENT ')' | '(' id0= IDENT n= NUMBER '*' id1= IDENT ')' | '(' id0= IDENT id1= IDENT '*' n= NUMBER ')' );
    public final Rule rule() throws RecognitionException {
        Rule r = null;


        Token id0=null;
        Token id1=null;
        Token n=null;
        Token IDENT3=null;
        Token NUMBER4=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:105:2: ( '(' IDENT NUMBER ')' | '(' id0= IDENT id1= IDENT ')' | '(' id0= IDENT n= NUMBER '*' id1= IDENT ')' | '(' id0= IDENT id1= IDENT '*' n= NUMBER ')' )
            int alt14=4;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==9) ) {
                int LA14_1 = input.LA(2);

                if ( (LA14_1==IDENT) ) {
                    int LA14_2 = input.LA(3);

                    if ( (LA14_2==NUMBER) ) {
                        int LA14_3 = input.LA(4);

                        if ( (LA14_3==10) ) {
                            alt14=1;
                        }
                        else if ( (LA14_3==11) ) {
                            alt14=3;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 14, 3, input);

                            throw nvae;

                        }
                    }
                    else if ( (LA14_2==IDENT) ) {
                        int LA14_4 = input.LA(4);

                        if ( (LA14_4==10) ) {
                            alt14=2;
                        }
                        else if ( (LA14_4==11) ) {
                            alt14=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 14, 4, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 14, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }
            switch (alt14) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:105:4: '(' IDENT NUMBER ')'
                    {
                    match(input,9,FOLLOW_9_in_rule697); 

                    IDENT3=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule699); 

                    NUMBER4=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule701); 

                    match(input,10,FOLLOW_10_in_rule703); 

                    r = new Rule(new Predicate((IDENT3!=null?IDENT3.getText():null)),new Left<BigDecimal, Multiplier>(BigDecimal.valueOf(Double.valueOf((NUMBER4!=null?NUMBER4.getText():null)))));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:106:4: '(' id0= IDENT id1= IDENT ')'
                    {
                    match(input,9,FOLLOW_9_in_rule710); 

                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule714); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule718); 

                    match(input,10,FOLLOW_10_in_rule719); 

                    r = new Rule(new Predicate((id0!=null?id0.getText():null)),new Right<BigDecimal, Multiplier>(new Multiplier(BigDecimal.valueOf(1), (id1!=null?id1.getText():null))));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:107:4: '(' id0= IDENT n= NUMBER '*' id1= IDENT ')'
                    {
                    match(input,9,FOLLOW_9_in_rule726); 

                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule730); 

                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule734); 

                    match(input,11,FOLLOW_11_in_rule736); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule740); 

                    match(input,10,FOLLOW_10_in_rule741); 

                    r = new Rule(new Predicate((id0!=null?id0.getText():null)),new Right<BigDecimal, Multiplier>(new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null))));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:108:4: '(' id0= IDENT id1= IDENT '*' n= NUMBER ')'
                    {
                    match(input,9,FOLLOW_9_in_rule748); 

                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule752); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule756); 

                    match(input,11,FOLLOW_11_in_rule758); 

                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule762); 

                    match(input,10,FOLLOW_10_in_rule764); 

                    r = new Rule(new Predicate((id0!=null?id0.getText():null)),new Right<BigDecimal, Multiplier>(new Multiplier(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null))));

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


    public static class operator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "operator"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:111:1: operator : ( 'max' | 'min' | '+' | '*' );
    public final PealProgramParser.operator_return operator() throws RecognitionException {
        PealProgramParser.operator_return retval = new PealProgramParser.operator_return();
        retval.start = input.LT(1);


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:111:10: ( 'max' | 'min' | '+' | '*' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:
            {
            if ( (input.LA(1) >= 11 && input.LA(1) <= 12)||(input.LA(1) >= 29 && input.LA(1) <= 30) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "operator"

    // Delegated rules


 

    public static final BitSet FOLLOW_20_in_program57 = new BitSet(new long[]{0x0000000000200010L});
    public static final BitSet FOLLOW_pol_in_program63 = new BitSet(new long[]{0x0000000000200010L});
    public static final BitSet FOLLOW_21_in_program71 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_pSet_in_program77 = new BitSet(new long[]{0x0000000000040010L});
    public static final BitSet FOLLOW_18_in_program84 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program94 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program96 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program100 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program102 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_program106 = new BitSet(new long[]{0x00000000000A0012L});
    public static final BitSet FOLLOW_IDENT_in_program120 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program122 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program126 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program128 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program132 = new BitSet(new long[]{0x00000000000A0012L});
    public static final BitSet FOLLOW_IDENT_in_program146 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program148 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_program152 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program154 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program158 = new BitSet(new long[]{0x00000000000A0012L});
    public static final BitSet FOLLOW_IDENT_in_program168 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program170 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program174 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program176 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program180 = new BitSet(new long[]{0x00000000000A0012L});
    public static final BitSet FOLLOW_IDENT_in_program194 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program196 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_7_in_program198 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program202 = new BitSet(new long[]{0x00000000000A0012L});
    public static final BitSet FOLLOW_IDENT_in_program212 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program214 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program218 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_program220 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program224 = new BitSet(new long[]{0x00000000000A0012L});
    public static final BitSet FOLLOW_IDENT_in_program234 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program236 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program240 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_program242 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program246 = new BitSet(new long[]{0x00000000000A0012L});
    public static final BitSet FOLLOW_IDENT_in_program257 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program259 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_program261 = new BitSet(new long[]{0x00000000000A0012L});
    public static final BitSet FOLLOW_IDENT_in_program272 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program274 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_program276 = new BitSet(new long[]{0x00000000000A0012L});
    public static final BitSet FOLLOW_19_in_program286 = new BitSet(new long[]{0x000000000003DE32L});
    public static final BitSet FOLLOW_17_in_program333 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program341 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program343 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_program345 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program349 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_IDENT_in_program357 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program359 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_program361 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program365 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_IDENT_in_program373 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program375 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_program377 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program381 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_IDENT_in_program390 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program392 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_program394 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program398 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program402 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_IDENT_in_program411 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program413 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_program415 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program419 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program423 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_IDENT_in_program433 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program435 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_program437 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program441 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program445 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_IDENT_in_pSet471 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pSet473 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_pSet477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pSet485 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pSet487 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_pSet489 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet491 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_pSet495 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet497 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_pSet501 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pSet511 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pSet513 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_pSet515 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet517 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_pSet521 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet523 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_pSet527 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pol554 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pol556 = new BitSet(new long[]{0x0000000060001800L});
    public static final BitSet FOLLOW_operator_in_pol560 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol562 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol565 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol571 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_pol573 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_NUMBER_in_pol578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_pol604 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_pol606 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_pol610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pol636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pol662 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_pol664 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_pol668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule697 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_rule699 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_rule701 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule710 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_rule714 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_rule718 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule726 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_rule730 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_rule734 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_rule736 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_rule740 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule748 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_rule752 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_rule756 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_rule758 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_rule762 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule764 = new BitSet(new long[]{0x0000000000000002L});

}