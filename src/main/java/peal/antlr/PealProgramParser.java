// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-07-10 10:35:51

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
    List<Rule> l = new ArrayList<Rule>();
    String n = null;
    public pSet pSet = null;

    //need to override the default error reporting
    @Override
    public void reportError(RecognitionException e) {
    	throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
    }




    // $ANTLR start "program"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:36:1: program : (id0= IDENT '=' id2= IDENT '<=' num= NUMBER (id1= IDENT '=' pol )* id2= IDENT '=' pSet |id0= IDENT '=' num= NUMBER '<' id2= IDENT (id1= IDENT '=' pol )* id2= IDENT '=' pSet1 );
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:37:2: (id0= IDENT '=' id2= IDENT '<=' num= NUMBER (id1= IDENT '=' pol )* id2= IDENT '=' pSet |id0= IDENT '=' num= NUMBER '<' id2= IDENT (id1= IDENT '=' pol )* id2= IDENT '=' pSet1 )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==IDENT) ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==16) ) {
                    int LA3_2 = input.LA(3);

                    if ( (LA3_2==IDENT) ) {
                        alt3=1;
                    }
                    else if ( (LA3_2==NUMBER) ) {
                        alt3=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }
            switch (alt3) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:37:4: id0= IDENT '=' id2= IDENT '<=' num= NUMBER (id1= IDENT '=' pol )* id2= IDENT '=' pSet
                    {
                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program47); 

                    match(input,16,FOLLOW_16_in_program49); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program53); 

                    match(input,15,FOLLOW_15_in_program55); 

                    num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program59); 

                    n = (num!=null?num.getText():null); pols.put((id0!=null?id0.getText():null), new Pol(new ArrayList<Rule>(),Plus$.MODULE$,-1));

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:38:2: (id1= IDENT '=' pol )*
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
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:38:3: id1= IDENT '=' pol
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


                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program80); 

                    match(input,16,FOLLOW_16_in_program82); 

                    pushFollow(FOLLOW_pSet_in_program84);
                    pSet2=pSet();

                    state._fsp--;


                     pSet = pSet2;

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:41:3: id0= IDENT '=' num= NUMBER '<' id2= IDENT (id1= IDENT '=' pol )* id2= IDENT '=' pSet1
                    {
                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program95); 

                    match(input,16,FOLLOW_16_in_program97); 

                    num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program101); 

                    match(input,14,FOLLOW_14_in_program103); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program107); 

                    n = (num!=null?num.getText():null); pols.put((id0!=null?id0.getText():null), new Pol(new ArrayList<Rule>(),Plus$.MODULE$,-1));

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:42:2: (id1= IDENT '=' pol )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==IDENT) ) {
                            int LA2_1 = input.LA(2);

                            if ( (LA2_1==16) ) {
                                switch ( input.LA(3) ) {
                                case 18:
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
                                case 19:
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
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:42:3: id1= IDENT '=' pol
                    	    {
                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program115); 

                    	    match(input,16,FOLLOW_16_in_program117); 

                    	    pushFollow(FOLLOW_pol_in_program119);
                    	    pol3=pol();

                    	    state._fsp--;


                    	    pols.put((id1!=null?id1.getText():null), pol3);

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program128); 

                    match(input,16,FOLLOW_16_in_program130); 

                    pushFollow(FOLLOW_pSet1_in_program132);
                    pSet14=pSet1();

                    state._fsp--;


                     pSet = pSet14;

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:46:1: pSet returns [pSet t] : (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' id4= pSet ')' );
    public final pSet pSet() throws RecognitionException {
        pSet t = null;


        Token id1=null;
        Token id2=null;
        Token id3=null;
        pSet id4 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:2: (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' id4= pSet ')' )
            int alt4=5;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt4=1;
                }
                break;
            case 18:
                {
                int LA4_2 = input.LA(2);

                if ( (LA4_2==9) ) {
                    int LA4_4 = input.LA(3);

                    if ( (LA4_4==IDENT) ) {
                        int LA4_6 = input.LA(4);

                        if ( (LA4_6==13) ) {
                            int LA4_8 = input.LA(5);

                            if ( (LA4_8==IDENT) ) {
                                int LA4_10 = input.LA(6);

                                if ( (LA4_10==10) ) {
                                    alt4=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 4, 10, input);

                                    throw nvae;

                                }
                            }
                            else if ( ((LA4_8 >= 18 && LA4_8 <= 19)) ) {
                                alt4=3;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 4, 8, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 4, 6, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 4, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 2, input);

                    throw nvae;

                }
                }
                break;
            case 19:
                {
                int LA4_3 = input.LA(2);

                if ( (LA4_3==9) ) {
                    int LA4_5 = input.LA(3);

                    if ( (LA4_5==IDENT) ) {
                        int LA4_7 = input.LA(4);

                        if ( (LA4_7==13) ) {
                            int LA4_9 = input.LA(5);

                            if ( (LA4_9==IDENT) ) {
                                int LA4_12 = input.LA(6);

                                if ( (LA4_12==10) ) {
                                    alt4=4;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 4, 12, input);

                                    throw nvae;

                                }
                            }
                            else if ( ((LA4_9 >= 18 && LA4_9 <= 19)) ) {
                                alt4=5;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 4, 9, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 4, 7, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 5, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 3, input);

                    throw nvae;

                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:4: id1= IDENT
                    {
                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet155); 

                    t = new PolLessThanTh(pols.get((id1!=null?id1.getText():null)), Double.valueOf(n));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:4: 'max' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,18,FOLLOW_18_in_pSet162); 

                    match(input,9,FOLLOW_9_in_pSet164); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet168); 

                    match(input,13,FOLLOW_13_in_pSet170); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet174); 

                    match(input,10,FOLLOW_10_in_pSet176); 

                    t = new MaxLessThanTh(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf(n));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:49:4: 'max' '(' id3= IDENT ',' id4= pSet ')'
                    {
                    match(input,18,FOLLOW_18_in_pSet183); 

                    match(input,9,FOLLOW_9_in_pSet185); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet189); 

                    match(input,13,FOLLOW_13_in_pSet191); 

                    pushFollow(FOLLOW_pSet_in_pSet195);
                    id4=pSet();

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet197); 

                    t = new MaxLessThanTh(pols.get((id3!=null?id3.getText():null)), id4, Double.valueOf(n));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:50:4: 'min' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,19,FOLLOW_19_in_pSet204); 

                    match(input,9,FOLLOW_9_in_pSet206); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet210); 

                    match(input,13,FOLLOW_13_in_pSet212); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet216); 

                    match(input,10,FOLLOW_10_in_pSet218); 

                    t = new MinLessThanTh(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf(n));

                    }
                    break;
                case 5 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:51:4: 'min' '(' id3= IDENT ',' id4= pSet ')'
                    {
                    match(input,19,FOLLOW_19_in_pSet225); 

                    match(input,9,FOLLOW_9_in_pSet227); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet231); 

                    match(input,13,FOLLOW_13_in_pSet233); 

                    pushFollow(FOLLOW_pSet_in_pSet237);
                    id4=pSet();

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet239); 

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:54:1: pSet1 returns [pSet t] : (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet1 ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' id4= pSet1 ')' );
    public final pSet pSet1() throws RecognitionException {
        pSet t = null;


        Token id1=null;
        Token id2=null;
        Token id3=null;
        pSet id4 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:55:2: (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet1 ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' id4= pSet1 ')' )
            int alt5=5;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt5=1;
                }
                break;
            case 18:
                {
                int LA5_2 = input.LA(2);

                if ( (LA5_2==9) ) {
                    int LA5_4 = input.LA(3);

                    if ( (LA5_4==IDENT) ) {
                        int LA5_6 = input.LA(4);

                        if ( (LA5_6==13) ) {
                            int LA5_8 = input.LA(5);

                            if ( (LA5_8==IDENT) ) {
                                int LA5_10 = input.LA(6);

                                if ( (LA5_10==10) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 5, 10, input);

                                    throw nvae;

                                }
                            }
                            else if ( ((LA5_8 >= 18 && LA5_8 <= 19)) ) {
                                alt5=3;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 5, 8, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 5, 6, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 4, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 2, input);

                    throw nvae;

                }
                }
                break;
            case 19:
                {
                int LA5_3 = input.LA(2);

                if ( (LA5_3==9) ) {
                    int LA5_5 = input.LA(3);

                    if ( (LA5_5==IDENT) ) {
                        int LA5_7 = input.LA(4);

                        if ( (LA5_7==13) ) {
                            int LA5_9 = input.LA(5);

                            if ( (LA5_9==IDENT) ) {
                                int LA5_12 = input.LA(6);

                                if ( (LA5_12==10) ) {
                                    alt5=4;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 5, 12, input);

                                    throw nvae;

                                }
                            }
                            else if ( ((LA5_9 >= 18 && LA5_9 <= 19)) ) {
                                alt5=5;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 5, 9, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 5, 7, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 5, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 3, input);

                    throw nvae;

                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:55:4: id1= IDENT
                    {
                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1262); 

                    t = new ThLessThanPol(pols.get((id1!=null?id1.getText():null)), Double.valueOf(n));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:56:4: 'max' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,18,FOLLOW_18_in_pSet1269); 

                    match(input,9,FOLLOW_9_in_pSet1271); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1275); 

                    match(input,13,FOLLOW_13_in_pSet1277); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1281); 

                    match(input,10,FOLLOW_10_in_pSet1283); 

                    t = new ThLessThanMax(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf(n));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:57:4: 'max' '(' id3= IDENT ',' id4= pSet1 ')'
                    {
                    match(input,18,FOLLOW_18_in_pSet1290); 

                    match(input,9,FOLLOW_9_in_pSet1292); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1296); 

                    match(input,13,FOLLOW_13_in_pSet1298); 

                    pushFollow(FOLLOW_pSet1_in_pSet1302);
                    id4=pSet1();

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet1304); 

                    t = new ThLessThanMax(pols.get((id3!=null?id3.getText():null)), id4, Double.valueOf(n));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:58:4: 'min' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,19,FOLLOW_19_in_pSet1311); 

                    match(input,9,FOLLOW_9_in_pSet1313); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1317); 

                    match(input,13,FOLLOW_13_in_pSet1319); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1323); 

                    match(input,10,FOLLOW_10_in_pSet1325); 

                    t = new ThLessThanMin(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf(n));

                    }
                    break;
                case 5 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:4: 'min' '(' id3= IDENT ',' id4= pSet1 ')'
                    {
                    match(input,19,FOLLOW_19_in_pSet1332); 

                    match(input,9,FOLLOW_9_in_pSet1334); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1338); 

                    match(input,13,FOLLOW_13_in_pSet1340); 

                    pushFollow(FOLLOW_pSet1_in_pSet1344);
                    id4=pSet1();

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet1346); 

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:62:1: pol returns [Pol p] : ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER | '*' '(' ( rule )* ')' 'default' NUMBER );
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:64:2: ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER | '*' '(' ( rule )* ')' 'default' NUMBER )
            int alt10=4;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt10=1;
                }
                break;
            case 18:
                {
                alt10=2;
                }
                break;
            case 19:
                {
                alt10=3;
                }
                break;
            case 11:
                {
                alt10=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }

            switch (alt10) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:64:5: '+' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,12,FOLLOW_12_in_pol370); 

                    match(input,9,FOLLOW_9_in_pol372); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:64:13: ( rule )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==9) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:64:14: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol375);
                    	    rule5=rule();

                    	    state._fsp--;


                    	    l.add(rule5);

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol381); 

                    match(input,17,FOLLOW_17_in_pol383); 

                    NUMBER6=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol385); 

                    p = new Pol(l, Plus$.MODULE$, Double.valueOf((NUMBER6!=null?NUMBER6.getText():null)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:65:4: 'max' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,18,FOLLOW_18_in_pol392); 

                    match(input,9,FOLLOW_9_in_pol394); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:65:14: ( rule )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==9) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:65:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol397);
                    	    rule7=rule();

                    	    state._fsp--;


                    	    l.add(rule7);

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol403); 

                    match(input,17,FOLLOW_17_in_pol405); 

                    NUMBER8=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol407); 

                    p = new Pol(l, Max$.MODULE$, Double.valueOf((NUMBER8!=null?NUMBER8.getText():null)));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:66:4: 'min' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,19,FOLLOW_19_in_pol414); 

                    match(input,9,FOLLOW_9_in_pol416); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:66:14: ( rule )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==9) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:66:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol419);
                    	    rule9=rule();

                    	    state._fsp--;


                    	    l.add(rule9);

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol425); 

                    match(input,17,FOLLOW_17_in_pol427); 

                    NUMBER10=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol429); 

                    p = new Pol(l, Min$.MODULE$, Double.valueOf((NUMBER10!=null?NUMBER10.getText():null)));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:67:4: '*' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,11,FOLLOW_11_in_pol437); 

                    match(input,9,FOLLOW_9_in_pol439); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:67:12: ( rule )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==9) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:67:13: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol442);
                    	    rule11=rule();

                    	    state._fsp--;


                    	    l.add(rule11);

                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol448); 

                    match(input,17,FOLLOW_17_in_pol450); 

                    NUMBER12=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol452); 

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:70:1: rule returns [Rule r] : '(' IDENT NUMBER ')' ;
    public final Rule rule() throws RecognitionException {
        Rule r = null;


        Token IDENT13=null;
        Token NUMBER14=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:2: ( '(' IDENT NUMBER ')' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:4: '(' IDENT NUMBER ')'
            {
            match(input,9,FOLLOW_9_in_rule470); 

            IDENT13=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule472); 

            NUMBER14=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule474); 

            match(input,10,FOLLOW_10_in_rule476); 

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
    public static final BitSet FOLLOW_IDENT_in_program80 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program82 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet_in_program84 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_program95 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program97 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_program101 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program103 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program107 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program115 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program117 = new BitSet(new long[]{0x00000000000C1800L});
    public static final BitSet FOLLOW_pol_in_program119 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program128 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program130 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet1_in_program132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pSet155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pSet162 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet164 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet168 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet170 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet174 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pSet183 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet185 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet189 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet191 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet_in_pSet195 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pSet204 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet206 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet210 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet212 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet216 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pSet225 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet227 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet231 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet233 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet_in_pSet237 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pSet1262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pSet1269 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet1271 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1275 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet1277 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1281 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet1283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pSet1290 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet1292 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1296 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet1298 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet1_in_pSet1302 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet1304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pSet1311 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet1313 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1317 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet1319 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1323 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet1325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pSet1332 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet1334 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1338 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet1340 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet1_in_pSet1344 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet1346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_pol370 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol372 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol375 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol381 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol383 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pol392 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol394 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol397 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol403 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol405 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pol414 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol416 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol419 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol425 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol427 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_pol437 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol439 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol442 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol448 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol450 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule470 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_rule472 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_rule474 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule476 = new BitSet(new long[]{0x0000000000000002L});

}