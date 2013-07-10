// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-07-10 11:18:37

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMPARE", "IDENT", "NEWLINE", "NUMBER", "WS", "'('", "')'", "'*'", "'+'", "','", "'<'", "'<='", "'='", "'default'", "'max'", "'min'"
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


    public Map<String, Pol> pols = new HashMap<String, Pol>();
    public Map<String, String> conds = new HashMap<String, String>();
    public Map<String, pSet> pSets = new HashMap<String, pSet>();
    List<Rule> l = new ArrayList<Rule>();
    String n = null;

    //need to override the default error reporting
    @Override
    public void reportError(RecognitionException e) {
    	throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
    }




    // $ANTLR start "program"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:37:1: program : (id0= IDENT '=' id2= IDENT '<=' num= NUMBER (id1= IDENT '=' pol )* (id2= IDENT '=' pSet )+ |id0= IDENT '=' num= NUMBER '<' id2= IDENT (id1= IDENT '=' pol )* (id2= IDENT '=' pSet1 )+ );
    public final void program() throws RecognitionException {
        Token id0=null;
        Token id2=null;
        Token num=null;
        Token id1=null;
        Pol pol1 =null;

        pSet pSet2 =null;

        Pol pol3 =null;

        pSet pSet14 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:38:2: (id0= IDENT '=' id2= IDENT '<=' num= NUMBER (id1= IDENT '=' pol )* (id2= IDENT '=' pSet )+ |id0= IDENT '=' num= NUMBER '<' id2= IDENT (id1= IDENT '=' pol )* (id2= IDENT '=' pSet1 )+ )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==IDENT) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==16) ) {
                    int LA5_2 = input.LA(3);

                    if ( (LA5_2==IDENT) ) {
                        alt5=1;
                    }
                    else if ( (LA5_2==NUMBER) ) {
                        alt5=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:38:4: id0= IDENT '=' id2= IDENT '<=' num= NUMBER (id1= IDENT '=' pol )* (id2= IDENT '=' pSet )+
                    {
                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program47); 

                    match(input,16,FOLLOW_16_in_program49); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program53); 

                    match(input,15,FOLLOW_15_in_program55); 

                    num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program59); 

                    n = (num!=null?num.getText():null); conds.put((id0!=null?id0.getText():null), (id2!=null?id2.getText():null));

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:39:2: (id1= IDENT '=' pol )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==IDENT) ) {
                            int LA1_1 = input.LA(2);

                            if ( (LA1_1==16) ) {
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
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:39:3: id1= IDENT '=' pol
                    	    {
                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program67); 

                    	    match(input,16,FOLLOW_16_in_program69); 

                    	    pushFollow(FOLLOW_pol_in_program71);
                    	    pol1=pol();

                    	    state._fsp--;


                    	    pols.put((id1!=null?id1.getText():null), pol1);

                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:40:2: (id2= IDENT '=' pSet )+
                    int cnt2=0;
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==IDENT) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:40:3: id2= IDENT '=' pSet
                    	    {
                    	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program81); 

                    	    match(input,16,FOLLOW_16_in_program83); 

                    	    pushFollow(FOLLOW_pSet_in_program85);
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


                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:42:3: id0= IDENT '=' num= NUMBER '<' id2= IDENT (id1= IDENT '=' pol )* (id2= IDENT '=' pSet1 )+
                    {
                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program98); 

                    match(input,16,FOLLOW_16_in_program100); 

                    num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program104); 

                    match(input,14,FOLLOW_14_in_program106); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program110); 

                    n = (num!=null?num.getText():null); conds.put((id0!=null?id0.getText():null), (id2!=null?id2.getText():null));

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:43:2: (id1= IDENT '=' pol )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==IDENT) ) {
                            int LA3_1 = input.LA(2);

                            if ( (LA3_1==16) ) {
                                switch ( input.LA(3) ) {
                                case 18:
                                    {
                                    int LA3_4 = input.LA(4);

                                    if ( (LA3_4==9) ) {
                                        int LA3_7 = input.LA(5);

                                        if ( ((LA3_7 >= 9 && LA3_7 <= 10)) ) {
                                            alt3=1;
                                        }


                                    }


                                    }
                                    break;
                                case 19:
                                    {
                                    int LA3_5 = input.LA(4);

                                    if ( (LA3_5==9) ) {
                                        int LA3_8 = input.LA(5);

                                        if ( ((LA3_8 >= 9 && LA3_8 <= 10)) ) {
                                            alt3=1;
                                        }


                                    }


                                    }
                                    break;
                                case 11:
                                case 12:
                                    {
                                    alt3=1;
                                    }
                                    break;

                                }

                            }


                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:43:3: id1= IDENT '=' pol
                    	    {
                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program118); 

                    	    match(input,16,FOLLOW_16_in_program120); 

                    	    pushFollow(FOLLOW_pol_in_program122);
                    	    pol3=pol();

                    	    state._fsp--;


                    	    pols.put((id1!=null?id1.getText():null), pol3);

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:44:2: (id2= IDENT '=' pSet1 )+
                    int cnt4=0;
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==IDENT) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:44:3: id2= IDENT '=' pSet1
                    	    {
                    	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program132); 

                    	    match(input,16,FOLLOW_16_in_program134); 

                    	    pushFollow(FOLLOW_pSet1_in_program136);
                    	    pSet14=pSet1();

                    	    state._fsp--;


                    	     pSets.put((id2!=null?id2.getText():null), pSet14);

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:1: pSet returns [pSet t] : (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' id4= pSet ')' );
    public final pSet pSet() throws RecognitionException {
        pSet t = null;


        Token id1=null;
        Token id2=null;
        Token id3=null;
        pSet id4 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:2: (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' id4= pSet ')' )
            int alt6=5;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt6=1;
                }
                break;
            case 18:
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
                            else if ( ((LA6_8 >= 18 && LA6_8 <= 19)) ) {
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
            case 19:
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
                            else if ( ((LA6_9 >= 18 && LA6_9 <= 19)) ) {
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
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:4: id1= IDENT
                    {
                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet161); 

                    t = new PolLessThanTh(pols.get((id1!=null?id1.getText():null)), Double.valueOf(n));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:49:4: 'max' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,18,FOLLOW_18_in_pSet168); 

                    match(input,9,FOLLOW_9_in_pSet170); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet174); 

                    match(input,13,FOLLOW_13_in_pSet176); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet180); 

                    match(input,10,FOLLOW_10_in_pSet182); 

                    t = new MaxLessThanTh(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf(n));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:50:4: 'max' '(' id3= IDENT ',' id4= pSet ')'
                    {
                    match(input,18,FOLLOW_18_in_pSet189); 

                    match(input,9,FOLLOW_9_in_pSet191); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet195); 

                    match(input,13,FOLLOW_13_in_pSet197); 

                    pushFollow(FOLLOW_pSet_in_pSet201);
                    id4=pSet();

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet203); 

                    t = new MaxLessThanTh(pols.get((id3!=null?id3.getText():null)), id4, Double.valueOf(n));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:51:4: 'min' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,19,FOLLOW_19_in_pSet210); 

                    match(input,9,FOLLOW_9_in_pSet212); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet216); 

                    match(input,13,FOLLOW_13_in_pSet218); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet222); 

                    match(input,10,FOLLOW_10_in_pSet224); 

                    t = new MinLessThanTh(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf(n));

                    }
                    break;
                case 5 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:52:4: 'min' '(' id3= IDENT ',' id4= pSet ')'
                    {
                    match(input,19,FOLLOW_19_in_pSet231); 

                    match(input,9,FOLLOW_9_in_pSet233); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet237); 

                    match(input,13,FOLLOW_13_in_pSet239); 

                    pushFollow(FOLLOW_pSet_in_pSet243);
                    id4=pSet();

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet245); 

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



    // $ANTLR start "pSet1"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:55:1: pSet1 returns [pSet t] : (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet1 ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' id4= pSet1 ')' );
    public final pSet pSet1() throws RecognitionException {
        pSet t = null;


        Token id1=null;
        Token id2=null;
        Token id3=null;
        pSet id4 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:56:2: (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet1 ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' id4= pSet1 ')' )
            int alt7=5;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt7=1;
                }
                break;
            case 18:
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
                            else if ( ((LA7_8 >= 18 && LA7_8 <= 19)) ) {
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
            case 19:
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
                            else if ( ((LA7_9 >= 18 && LA7_9 <= 19)) ) {
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
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:56:4: id1= IDENT
                    {
                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1268); 

                    t = new ThLessThanPol(pols.get((id1!=null?id1.getText():null)), Double.valueOf(n));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:57:4: 'max' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,18,FOLLOW_18_in_pSet1275); 

                    match(input,9,FOLLOW_9_in_pSet1277); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1281); 

                    match(input,13,FOLLOW_13_in_pSet1283); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1287); 

                    match(input,10,FOLLOW_10_in_pSet1289); 

                    t = new ThLessThanMax(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf(n));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:58:4: 'max' '(' id3= IDENT ',' id4= pSet1 ')'
                    {
                    match(input,18,FOLLOW_18_in_pSet1296); 

                    match(input,9,FOLLOW_9_in_pSet1298); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1302); 

                    match(input,13,FOLLOW_13_in_pSet1304); 

                    pushFollow(FOLLOW_pSet1_in_pSet1308);
                    id4=pSet1();

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet1310); 

                    t = new ThLessThanMax(pols.get((id3!=null?id3.getText():null)), id4, Double.valueOf(n));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:4: 'min' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,19,FOLLOW_19_in_pSet1317); 

                    match(input,9,FOLLOW_9_in_pSet1319); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1323); 

                    match(input,13,FOLLOW_13_in_pSet1325); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1329); 

                    match(input,10,FOLLOW_10_in_pSet1331); 

                    t = new ThLessThanMin(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf(n));

                    }
                    break;
                case 5 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:60:4: 'min' '(' id3= IDENT ',' id4= pSet1 ')'
                    {
                    match(input,19,FOLLOW_19_in_pSet1338); 

                    match(input,9,FOLLOW_9_in_pSet1340); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1344); 

                    match(input,13,FOLLOW_13_in_pSet1346); 

                    pushFollow(FOLLOW_pSet1_in_pSet1350);
                    id4=pSet1();

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet1352); 

                    t = new ThLessThanMin(pols.get((id3!=null?id3.getText():null)), id4, Double.valueOf(n));

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
    // $ANTLR end "pSet1"



    // $ANTLR start "pol"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:63:1: pol returns [Pol p] : ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER | '*' '(' ( rule )* ')' 'default' NUMBER );
    public final Pol pol() throws RecognitionException {
        Pol p = null;


        Token NUMBER6=null;
        Token NUMBER8=null;
        Token NUMBER10=null;
        Token NUMBER12=null;
        Rule rule5 =null;

        Rule rule7 =null;

        Rule rule9 =null;

        Rule rule11 =null;


        l = new ArrayList<Rule>(); 
        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:65:2: ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER | '*' '(' ( rule )* ')' 'default' NUMBER )
            int alt12=4;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt12=1;
                }
                break;
            case 18:
                {
                alt12=2;
                }
                break;
            case 19:
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
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:65:5: '+' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,12,FOLLOW_12_in_pol376); 

                    match(input,9,FOLLOW_9_in_pol378); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:65:13: ( rule )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==9) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:65:14: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol381);
                    	    rule5=rule();

                    	    state._fsp--;


                    	    l.add(rule5);

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol387); 

                    match(input,17,FOLLOW_17_in_pol389); 

                    NUMBER6=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol391); 

                    p = new Pol(l, Plus$.MODULE$, Double.valueOf((NUMBER6!=null?NUMBER6.getText():null)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:66:4: 'max' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,18,FOLLOW_18_in_pol398); 

                    match(input,9,FOLLOW_9_in_pol400); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:66:14: ( rule )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==9) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:66:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol403);
                    	    rule7=rule();

                    	    state._fsp--;


                    	    l.add(rule7);

                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol409); 

                    match(input,17,FOLLOW_17_in_pol411); 

                    NUMBER8=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol413); 

                    p = new Pol(l, Max$.MODULE$, Double.valueOf((NUMBER8!=null?NUMBER8.getText():null)));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:67:4: 'min' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,19,FOLLOW_19_in_pol420); 

                    match(input,9,FOLLOW_9_in_pol422); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:67:14: ( rule )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==9) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:67:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol425);
                    	    rule9=rule();

                    	    state._fsp--;


                    	    l.add(rule9);

                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol431); 

                    match(input,17,FOLLOW_17_in_pol433); 

                    NUMBER10=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol435); 

                    p = new Pol(l, Min$.MODULE$, Double.valueOf((NUMBER10!=null?NUMBER10.getText():null)));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:68:4: '*' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,11,FOLLOW_11_in_pol443); 

                    match(input,9,FOLLOW_9_in_pol445); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:68:12: ( rule )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==9) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:68:13: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol448);
                    	    rule11=rule();

                    	    state._fsp--;


                    	    l.add(rule11);

                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol454); 

                    match(input,17,FOLLOW_17_in_pol456); 

                    NUMBER12=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol458); 

                    p = new Pol(l, Mul$.MODULE$, Double.valueOf((NUMBER12!=null?NUMBER12.getText():null)));

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:1: rule returns [Rule r] : '(' IDENT NUMBER ')' ;
    public final Rule rule() throws RecognitionException {
        Rule r = null;


        Token IDENT13=null;
        Token NUMBER14=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:2: ( '(' IDENT NUMBER ')' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:4: '(' IDENT NUMBER ')'
            {
            match(input,9,FOLLOW_9_in_rule476); 

            IDENT13=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule478); 

            NUMBER14=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule480); 

            match(input,10,FOLLOW_10_in_rule482); 

            r = new Rule(new Predicate((IDENT13!=null?IDENT13.getText():null), ""),Double.valueOf((NUMBER14!=null?NUMBER14.getText():null)));

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


 

    public static final BitSet FOLLOW_IDENT_in_program47 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program49 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program53 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program55 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_program59 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program67 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program69 = new BitSet(new long[]{0x00000000000C1800L});
    public static final BitSet FOLLOW_pol_in_program71 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program81 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program83 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet_in_program85 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_program98 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program100 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_program104 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program106 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program110 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program118 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program120 = new BitSet(new long[]{0x00000000000C1800L});
    public static final BitSet FOLLOW_pol_in_program122 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program132 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program134 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet1_in_program136 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_pSet161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pSet168 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet170 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet174 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet176 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet180 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pSet189 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet191 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet195 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet197 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet_in_pSet201 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pSet210 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet212 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet216 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet218 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet222 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pSet231 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet233 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet237 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet239 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet_in_pSet243 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pSet1268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pSet1275 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet1277 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1281 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet1283 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1287 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet1289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pSet1296 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet1298 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1302 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet1304 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet1_in_pSet1308 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet1310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pSet1317 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet1319 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1323 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet1325 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1329 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet1331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pSet1338 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet1340 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1344 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet1346 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet1_in_pSet1350 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet1352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_pol376 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol378 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol381 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol387 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol389 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pol398 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol400 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol403 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol409 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol411 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pol420 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol422 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol425 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol431 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol433 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_pol443 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol445 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol448 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol454 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol456 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule476 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_rule478 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_rule480 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule482 = new BitSet(new long[]{0x0000000000000002L});

}