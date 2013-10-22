// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-10-22 13:35:39

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
import scala.util.*;


import org.antlr.runtime.*;
import java.util.Stack;
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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:46:1: program : ( 'POLICIES' )? ( pol )* ( 'POLICY_SETS' )? ( pSet )+ ( 'CONDITIONS' )? (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )? ;
    public final void program() throws RecognitionException {
        Token id0=null;
        Token id2=null;
        Token num=null;
        Token id3=null;
        Token id1=null;
        Pol pol1 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:2: ( ( 'POLICIES' )? ( pol )* ( 'POLICY_SETS' )? ( pSet )+ ( 'CONDITIONS' )? (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )? )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:2: ( 'POLICIES' )? ( pol )* ( 'POLICY_SETS' )? ( pSet )+ ( 'CONDITIONS' )? (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' )+ ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )? ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )?
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


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:54:2: (id0= IDENT '=' id2= IDENT '<=' num= NUMBER |id0= IDENT '=' id2= IDENT '<=' id3= IDENT |id0= IDENT '=' num= NUMBER '<' id2= IDENT |id0= IDENT '=' '!' id1= IDENT |id0= IDENT '=' id1= IDENT '&&' id2= IDENT |id0= IDENT '=' id1= IDENT '||' id2= IDENT |id0= IDENT '=' 'true' |id0= IDENT '=' 'false' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=9;
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
                            case 8:
                                {
                                alt6=5;
                                }
                                break;
                            case 33:
                                {
                                alt6=6;
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
                            alt6=4;
                            }
                            break;
                        case 32:
                            {
                            alt6=7;
                            }
                            break;
                        case 27:
                            {
                            alt6=8;
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

            	    Condition cond = new GreaterThanThCondition(pSets.get((id2!=null?id2.getText():null)), BigDecimal.valueOf(Double.valueOf((num!=null?num.getText():null)))); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;
            	case 4 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:61:2: id0= IDENT '=' '!' id1= IDENT
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program168); 

            	    match(input,16,FOLLOW_16_in_program170); 

            	    match(input,7,FOLLOW_7_in_program172); 

            	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program176); 

            	    Condition cond = new NotCondition((id1!=null?id1.getText():null)); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;
            	case 5 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:63:2: id0= IDENT '=' id1= IDENT '&&' id2= IDENT
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program186); 

            	    match(input,16,FOLLOW_16_in_program188); 

            	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program192); 

            	    match(input,8,FOLLOW_8_in_program194); 

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program198); 

            	    Condition cond = new AndCondition((id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;
            	case 6 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:65:2: id0= IDENT '=' id1= IDENT '||' id2= IDENT
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program208); 

            	    match(input,16,FOLLOW_16_in_program210); 

            	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program214); 

            	    match(input,33,FOLLOW_33_in_program216); 

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program220); 

            	    Condition cond = new OrCondition((id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;
            	case 7 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:67:2: id0= IDENT '=' 'true'
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program231); 

            	    match(input,16,FOLLOW_16_in_program233); 

            	    match(input,32,FOLLOW_32_in_program235); 

            	    Condition cond = new TrueCondition(); conds.put((id0!=null?id0.getText():null), cond);

            	    }
            	    break;
            	case 8 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:69:2: id0= IDENT '=' 'false'
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program246); 

            	    match(input,16,FOLLOW_16_in_program248); 

            	    match(input,27,FOLLOW_27_in_program250); 

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


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:2: ( 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )* )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==19) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:3: 'DOMAIN_SPECIFICS' ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
                    {
                    match(input,19,FOLLOW_19_in_program260); 

                    ignore = true;

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:2: ( IDENT | NUMBER | '+' | '*' | '=' | '(' | ')' | '<' | '<=' )*
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


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:73:2: ( 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+ )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==17) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:73:3: 'ANALYSES' (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+
                    {
                    match(input,17,FOLLOW_17_in_program307); 

                    ignore = false;

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:2: (id0= IDENT '=' 'always_true?' id1= IDENT |id0= IDENT '=' 'always_false?' id1= IDENT |id0= IDENT '=' 'satisfiable?' id1= IDENT |id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT |id0= IDENT '=' 'different?' id1= IDENT id2= IDENT |id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT )+
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
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:74:3: id0= IDENT '=' 'always_true?' id1= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program315); 

                    	    match(input,16,FOLLOW_16_in_program317); 

                    	    match(input,23,FOLLOW_23_in_program319); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program323); 

                    	    AnalysisGenerator analysis = new AlwaysTrue((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 2 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:75:3: id0= IDENT '=' 'always_false?' id1= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program331); 

                    	    match(input,16,FOLLOW_16_in_program333); 

                    	    match(input,22,FOLLOW_22_in_program335); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program339); 

                    	    AnalysisGenerator analysis = new AlwaysFalse((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 3 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:76:3: id0= IDENT '=' 'satisfiable?' id1= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program347); 

                    	    match(input,16,FOLLOW_16_in_program349); 

                    	    match(input,31,FOLLOW_31_in_program351); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program355); 

                    	    AnalysisGenerator analysis = new Satisfiable((id0!=null?id0.getText():null), (id1!=null?id1.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 4 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:77:3: id0= IDENT '=' 'equivalent?' id1= IDENT id2= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program364); 

                    	    match(input,16,FOLLOW_16_in_program366); 

                    	    match(input,26,FOLLOW_26_in_program368); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program372); 

                    	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program376); 

                    	    AnalysisGenerator analysis = new Equivalent((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 5 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:78:3: id0= IDENT '=' 'different?' id1= IDENT id2= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program385); 

                    	    match(input,16,FOLLOW_16_in_program387); 

                    	    match(input,25,FOLLOW_25_in_program389); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program393); 

                    	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program397); 

                    	    AnalysisGenerator analysis = new Different((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null)); analyses.put((id0!=null?id0.getText():null), analysis);

                    	    }
                    	    break;
                    	case 6 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:79:3: id0= IDENT '=' 'implies?' id1= IDENT id2= IDENT
                    	    {
                    	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program407); 

                    	    match(input,16,FOLLOW_16_in_program409); 

                    	    match(input,28,FOLLOW_28_in_program411); 

                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program415); 

                    	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program419); 

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:84:1: pSet : (id0= IDENT '=' id1= IDENT |id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' );
    public final void pSet() throws RecognitionException {
        Token id0=null;
        Token id1=null;
        Token id2=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:85:2: (id0= IDENT '=' id1= IDENT |id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' |id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' )
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
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:85:3: id0= IDENT '=' id1= IDENT
                    {
                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet445); 

                    match(input,16,FOLLOW_16_in_pSet447); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet451); 

                    PolicySet p = new BasicPolicySet(pols.get((id1!=null?id1.getText():null))); pSets.put((id0!=null?id0.getText():null), p);

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:86:3: id0= IDENT '=' 'max' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet459); 

                    match(input,16,FOLLOW_16_in_pSet461); 

                    match(input,29,FOLLOW_29_in_pSet463); 

                    match(input,9,FOLLOW_9_in_pSet465); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet469); 

                    match(input,13,FOLLOW_13_in_pSet471); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet475); 

                    match(input,10,FOLLOW_10_in_pSet477); 

                    PolicySet p = new MaxPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null))); pSets.put((id0!=null?id0.getText():null), p);

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:87:3: id0= IDENT '=' 'min' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet485); 

                    match(input,16,FOLLOW_16_in_pSet487); 

                    match(input,30,FOLLOW_30_in_pSet489); 

                    match(input,9,FOLLOW_9_in_pSet491); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet495); 

                    match(input,13,FOLLOW_13_in_pSet497); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet501); 

                    match(input,10,FOLLOW_10_in_pSet503); 

                    PolicySet p = new MinPolicySet(PolicyResolver.getFromOr(pols, pSets, (id1!=null?id1.getText():null)), PolicyResolver.getFromOr(pols, pSets, (id2!=null?id2.getText():null))); pSets.put((id0!=null?id0.getText():null), p);

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:90:1: pol returns [Pol p] : id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' (n= NUMBER |n= NUMBER '*' id2= IDENT |id2= IDENT |id2= IDENT '*' n= NUMBER ) ;
    public final Pol pol() throws RecognitionException {
        Pol p = null;


        Token id1=null;
        Token n=null;
        Token id2=null;
        PealProgramParser.operator_return o =null;

        Rule rule2 =null;


        l = new ArrayList<Rule>(); 
        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:92:2: (id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' (n= NUMBER |n= NUMBER '*' id2= IDENT |id2= IDENT |id2= IDENT '*' n= NUMBER ) )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:92:4: id1= IDENT '=' o= operator '(' ( rule )* ')' 'default' (n= NUMBER |n= NUMBER '*' id2= IDENT |id2= IDENT |id2= IDENT '*' n= NUMBER )
            {
            id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pol528); 

            match(input,16,FOLLOW_16_in_pol530); 

            pushFollow(FOLLOW_operator_in_pol534);
            o=operator();

            state._fsp--;


            match(input,9,FOLLOW_9_in_pol536); 

            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:92:33: ( rule )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==9) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:92:34: rule
            	    {
            	    pushFollow(FOLLOW_rule_in_pol539);
            	    rule2=rule();

            	    state._fsp--;


            	    l.add(rule2);

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            match(input,10,FOLLOW_10_in_pol545); 

            match(input,24,FOLLOW_24_in_pol547); 

            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:92:73: (n= NUMBER |n= NUMBER '*' id2= IDENT |id2= IDENT |id2= IDENT '*' n= NUMBER )
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
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:92:74: n= NUMBER
                    {
                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol552); 

                    p = new Pol(l, OperatorResolver.apply((o!=null?input.toString(o.start,o.stop):null)), new Left<BigDecimal,Variable>(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null)))), (id1!=null?id1.getText():null));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:94:10: n= NUMBER '*' id2= IDENT
                    {
                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol578); 

                    match(input,11,FOLLOW_11_in_pol580); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pol584); 

                    p = new Pol(l, OperatorResolver.apply((o!=null?input.toString(o.start,o.stop):null)), new Right<BigDecimal,Variable>(new Variable(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id2!=null?id2.getText():null))), (id1!=null?id1.getText():null));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:96:10: id2= IDENT
                    {
                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pol610); 

                    p = new Pol(l, OperatorResolver.apply((o!=null?input.toString(o.start,o.stop):null)), new Right<BigDecimal,Variable>(new Variable(BigDecimal.valueOf(1), (id2!=null?id2.getText():null))), (id1!=null?id1.getText():null));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:98:10: id2= IDENT '*' n= NUMBER
                    {
                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pol636); 

                    match(input,11,FOLLOW_11_in_pol638); 

                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol642); 

                    p = new Pol(l, OperatorResolver.apply((o!=null?input.toString(o.start,o.stop):null)), new Right<BigDecimal,Variable>(new Variable(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id2!=null?id2.getText():null))), (id1!=null?id1.getText():null));

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:102:1: rule returns [Rule r] : ( '(' IDENT NUMBER ')' | '(' id0= IDENT id1= IDENT ')' | '(' id0= IDENT n= NUMBER '*' id1= IDENT ')' | '(' id0= IDENT id1= IDENT '*' n= NUMBER ')' );
    public final Rule rule() throws RecognitionException {
        Rule r = null;


        Token id0=null;
        Token id1=null;
        Token n=null;
        Token IDENT3=null;
        Token NUMBER4=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:103:2: ( '(' IDENT NUMBER ')' | '(' id0= IDENT id1= IDENT ')' | '(' id0= IDENT n= NUMBER '*' id1= IDENT ')' | '(' id0= IDENT id1= IDENT '*' n= NUMBER ')' )
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
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:103:4: '(' IDENT NUMBER ')'
                    {
                    match(input,9,FOLLOW_9_in_rule671); 

                    IDENT3=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule673); 

                    NUMBER4=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule675); 

                    match(input,10,FOLLOW_10_in_rule677); 

                    r = new Rule(new Predicate((IDENT3!=null?IDENT3.getText():null)),new Left<BigDecimal,Variable>(BigDecimal.valueOf(Double.valueOf((NUMBER4!=null?NUMBER4.getText():null)))));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:104:4: '(' id0= IDENT id1= IDENT ')'
                    {
                    match(input,9,FOLLOW_9_in_rule684); 

                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule688); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule692); 

                    match(input,10,FOLLOW_10_in_rule693); 

                    r = new Rule(new Predicate((id0!=null?id0.getText():null)),new Right<BigDecimal,Variable>(new Variable(BigDecimal.valueOf(1), (id1!=null?id1.getText():null))));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:105:4: '(' id0= IDENT n= NUMBER '*' id1= IDENT ')'
                    {
                    match(input,9,FOLLOW_9_in_rule700); 

                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule704); 

                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule708); 

                    match(input,11,FOLLOW_11_in_rule710); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule714); 

                    match(input,10,FOLLOW_10_in_rule715); 

                    r = new Rule(new Predicate((id0!=null?id0.getText():null)),new Right<BigDecimal,Variable>(new Variable(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null))));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:106:4: '(' id0= IDENT id1= IDENT '*' n= NUMBER ')'
                    {
                    match(input,9,FOLLOW_9_in_rule722); 

                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule726); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule730); 

                    match(input,11,FOLLOW_11_in_rule732); 

                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule736); 

                    match(input,10,FOLLOW_10_in_rule738); 

                    r = new Rule(new Predicate((id0!=null?id0.getText():null)),new Right<BigDecimal,Variable>(new Variable(BigDecimal.valueOf(Double.valueOf((n!=null?n.getText():null))), (id1!=null?id1.getText():null))));

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:109:1: operator : ( 'max' | 'min' | '+' | '*' );
    public final PealProgramParser.operator_return operator() throws RecognitionException {
        PealProgramParser.operator_return retval = new PealProgramParser.operator_return();
        retval.start = input.LT(1);


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:109:10: ( 'max' | 'min' | '+' | '*' )
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
    public static final BitSet FOLLOW_16_in_program170 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_7_in_program172 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program176 = new BitSet(new long[]{0x00000000000A0012L});
    public static final BitSet FOLLOW_IDENT_in_program186 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program188 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program192 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_program194 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program198 = new BitSet(new long[]{0x00000000000A0012L});
    public static final BitSet FOLLOW_IDENT_in_program208 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program210 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program214 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_program216 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program220 = new BitSet(new long[]{0x00000000000A0012L});
    public static final BitSet FOLLOW_IDENT_in_program231 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program233 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_program235 = new BitSet(new long[]{0x00000000000A0012L});
    public static final BitSet FOLLOW_IDENT_in_program246 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program248 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_program250 = new BitSet(new long[]{0x00000000000A0012L});
    public static final BitSet FOLLOW_19_in_program260 = new BitSet(new long[]{0x000000000003DE32L});
    public static final BitSet FOLLOW_17_in_program307 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program315 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program317 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_program319 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program323 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_IDENT_in_program331 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program333 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_program335 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program339 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_IDENT_in_program347 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program349 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_program351 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program355 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_IDENT_in_program364 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program366 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_program368 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program372 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program376 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_IDENT_in_program385 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program387 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_program389 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program393 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program397 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_IDENT_in_program407 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program409 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_program411 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program415 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_program419 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_IDENT_in_pSet445 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pSet447 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_pSet451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pSet459 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pSet461 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_pSet463 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet465 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_pSet469 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet471 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_pSet475 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pSet485 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pSet487 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_pSet489 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet491 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_pSet495 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet497 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_pSet501 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pol528 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_pol530 = new BitSet(new long[]{0x0000000060001800L});
    public static final BitSet FOLLOW_operator_in_pol534 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol536 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol539 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol545 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_pol547 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_NUMBER_in_pol552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_pol578 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_pol580 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_pol584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pol610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pol636 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_pol638 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_pol642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule671 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_rule673 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_rule675 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule684 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_rule688 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_rule692 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule700 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_rule704 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_rule708 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_rule710 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_rule714 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule722 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_rule726 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_rule730 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_rule732 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_rule736 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule738 = new BitSet(new long[]{0x0000000000000002L});

}