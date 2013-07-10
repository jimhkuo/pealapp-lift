// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-07-10 12:11:47

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
    private Map<String, String> pSetScores = new HashMap<String, String>();
    List<Rule> l = new ArrayList<Rule>();

    //need to override the default error reporting
    @Override
    public void reportError(RecognitionException e) {
    	throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
    }




    // $ANTLR start "program"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:37:1: program : (id1= IDENT '=' pol )* (id0= IDENT '=' id2= IDENT '<=' num= NUMBER id2= IDENT '=' pSet[$id2.text] |id0= IDENT '=' num= NUMBER '<' id2= IDENT id2= IDENT '=' pSet1[$id2.text] )+ ;
    public final void program() throws RecognitionException {
        Token id1=null;
        Token id0=null;
        Token id2=null;
        Token num=null;
        Pol pol1 =null;

        pSet pSet2 =null;

        pSet pSet13 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:38:2: ( (id1= IDENT '=' pol )* (id0= IDENT '=' id2= IDENT '<=' num= NUMBER id2= IDENT '=' pSet[$id2.text] |id0= IDENT '=' num= NUMBER '<' id2= IDENT id2= IDENT '=' pSet1[$id2.text] )+ )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:40:2: (id1= IDENT '=' pol )* (id0= IDENT '=' id2= IDENT '<=' num= NUMBER id2= IDENT '=' pSet[$id2.text] |id0= IDENT '=' num= NUMBER '<' id2= IDENT id2= IDENT '=' pSet1[$id2.text] )+
            {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:40:2: (id1= IDENT '=' pol )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==IDENT) ) {
                    int LA1_1 = input.LA(2);

                    if ( (LA1_1==16) ) {
                        int LA1_2 = input.LA(3);

                        if ( ((LA1_2 >= 11 && LA1_2 <= 12)||(LA1_2 >= 18 && LA1_2 <= 19)) ) {
                            alt1=1;
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


            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:42:2: (id0= IDENT '=' id2= IDENT '<=' num= NUMBER id2= IDENT '=' pSet[$id2.text] |id0= IDENT '=' num= NUMBER '<' id2= IDENT id2= IDENT '=' pSet1[$id2.text] )+
            int cnt2=0;
            loop2:
            do {
                int alt2=3;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==IDENT) ) {
                    int LA2_2 = input.LA(2);

                    if ( (LA2_2==16) ) {
                        int LA2_3 = input.LA(3);

                        if ( (LA2_3==IDENT) ) {
                            alt2=1;
                        }
                        else if ( (LA2_3==NUMBER) ) {
                            alt2=2;
                        }


                    }


                }


                switch (alt2) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:43:2: id0= IDENT '=' id2= IDENT '<=' num= NUMBER id2= IDENT '=' pSet[$id2.text]
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program71); 

            	    match(input,16,FOLLOW_16_in_program73); 

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program77); 

            	    match(input,15,FOLLOW_15_in_program79); 

            	    num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program83); 

            	    pSetScores.put((id2!=null?id2.getText():null), (num!=null?num.getText():null)); conds.put((id0!=null?id0.getText():null), (id2!=null?id2.getText():null));

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program90); 

            	    match(input,16,FOLLOW_16_in_program92); 

            	    pushFollow(FOLLOW_pSet_in_program94);
            	    pSet2=pSet((id2!=null?id2.getText():null));

            	    state._fsp--;


            	     pSets.put((id2!=null?id2.getText():null), pSet2);

            	    }
            	    break;
            	case 2 :
            	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:46:2: id0= IDENT '=' num= NUMBER '<' id2= IDENT id2= IDENT '=' pSet1[$id2.text]
            	    {
            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_program105); 

            	    match(input,16,FOLLOW_16_in_program107); 

            	    num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program111); 

            	    match(input,14,FOLLOW_14_in_program113); 

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program117); 

            	    pSetScores.put((id2!=null?id2.getText():null), (num!=null?num.getText():null)); conds.put((id0!=null?id0.getText():null), (id2!=null?id2.getText():null));

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program124); 

            	    match(input,16,FOLLOW_16_in_program126); 

            	    pushFollow(FOLLOW_pSet1_in_program128);
            	    pSet13=pSet1((id2!=null?id2.getText():null));

            	    state._fsp--;


            	     pSets.put((id2!=null?id2.getText():null), pSet13);

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:51:1: pSet[String s] returns [pSet t] : (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet[s] ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' id4= pSet[s] ')' );
    public final pSet pSet(String s) throws RecognitionException {
        pSet t = null;


        Token id1=null;
        Token id2=null;
        Token id3=null;
        pSet id4 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:52:2: (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet[s] ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' id4= pSet[s] ')' )
            int alt3=5;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt3=1;
                }
                break;
            case 18:
                {
                int LA3_2 = input.LA(2);

                if ( (LA3_2==9) ) {
                    int LA3_4 = input.LA(3);

                    if ( (LA3_4==IDENT) ) {
                        int LA3_6 = input.LA(4);

                        if ( (LA3_6==13) ) {
                            int LA3_8 = input.LA(5);

                            if ( (LA3_8==IDENT) ) {
                                int LA3_10 = input.LA(6);

                                if ( (LA3_10==10) ) {
                                    alt3=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 3, 10, input);

                                    throw nvae;

                                }
                            }
                            else if ( ((LA3_8 >= 18 && LA3_8 <= 19)) ) {
                                alt3=3;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 3, 8, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 3, 6, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 4, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 2, input);

                    throw nvae;

                }
                }
                break;
            case 19:
                {
                int LA3_3 = input.LA(2);

                if ( (LA3_3==9) ) {
                    int LA3_5 = input.LA(3);

                    if ( (LA3_5==IDENT) ) {
                        int LA3_7 = input.LA(4);

                        if ( (LA3_7==13) ) {
                            int LA3_9 = input.LA(5);

                            if ( (LA3_9==IDENT) ) {
                                int LA3_12 = input.LA(6);

                                if ( (LA3_12==10) ) {
                                    alt3=4;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 3, 12, input);

                                    throw nvae;

                                }
                            }
                            else if ( ((LA3_9 >= 18 && LA3_9 <= 19)) ) {
                                alt3=5;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 3, 9, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 3, 7, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 5, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 3, input);

                    throw nvae;

                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:52:4: id1= IDENT
                    {
                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet155); 

                    t = new PolLessThanTh(pols.get((id1!=null?id1.getText():null)), Double.valueOf(pSetScores.get(s)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:53:4: 'max' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,18,FOLLOW_18_in_pSet162); 

                    match(input,9,FOLLOW_9_in_pSet164); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet168); 

                    match(input,13,FOLLOW_13_in_pSet170); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet174); 

                    match(input,10,FOLLOW_10_in_pSet176); 

                    t = new MaxLessThanTh(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf(pSetScores.get(s)));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:54:4: 'max' '(' id3= IDENT ',' id4= pSet[s] ')'
                    {
                    match(input,18,FOLLOW_18_in_pSet183); 

                    match(input,9,FOLLOW_9_in_pSet185); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet189); 

                    match(input,13,FOLLOW_13_in_pSet191); 

                    pushFollow(FOLLOW_pSet_in_pSet195);
                    id4=pSet(s);

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet198); 

                    t = new MaxLessThanTh(pols.get((id3!=null?id3.getText():null)), id4, Double.valueOf(pSetScores.get(s)));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:55:4: 'min' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,19,FOLLOW_19_in_pSet205); 

                    match(input,9,FOLLOW_9_in_pSet207); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet211); 

                    match(input,13,FOLLOW_13_in_pSet213); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet217); 

                    match(input,10,FOLLOW_10_in_pSet219); 

                    t = new MinLessThanTh(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf(pSetScores.get(s)));

                    }
                    break;
                case 5 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:56:4: 'min' '(' id3= IDENT ',' id4= pSet[s] ')'
                    {
                    match(input,19,FOLLOW_19_in_pSet226); 

                    match(input,9,FOLLOW_9_in_pSet228); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet232); 

                    match(input,13,FOLLOW_13_in_pSet234); 

                    pushFollow(FOLLOW_pSet_in_pSet238);
                    id4=pSet(s);

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet241); 

                    t = new MinLessThanTh(pols.get((id3!=null?id3.getText():null)), id4, Double.valueOf(pSetScores.get(s)));

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:1: pSet1[String s] returns [pSet t] : (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet1[s] ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' id4= pSet1[s] ')' );
    public final pSet pSet1(String s) throws RecognitionException {
        pSet t = null;


        Token id1=null;
        Token id2=null;
        Token id3=null;
        pSet id4 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:60:2: (id1= IDENT | 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'max' '(' id3= IDENT ',' id4= pSet1[s] ')' | 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'min' '(' id3= IDENT ',' id4= pSet1[s] ')' )
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
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:60:4: id1= IDENT
                    {
                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1263); 

                    t = new ThLessThanPol(pols.get((id1!=null?id1.getText():null)), Double.valueOf(pSetScores.get(s)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:61:4: 'max' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,18,FOLLOW_18_in_pSet1270); 

                    match(input,9,FOLLOW_9_in_pSet1272); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1276); 

                    match(input,13,FOLLOW_13_in_pSet1278); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1282); 

                    match(input,10,FOLLOW_10_in_pSet1284); 

                    t = new ThLessThanMax(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf(pSetScores.get(s)));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:62:4: 'max' '(' id3= IDENT ',' id4= pSet1[s] ')'
                    {
                    match(input,18,FOLLOW_18_in_pSet1291); 

                    match(input,9,FOLLOW_9_in_pSet1293); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1297); 

                    match(input,13,FOLLOW_13_in_pSet1299); 

                    pushFollow(FOLLOW_pSet1_in_pSet1303);
                    id4=pSet1(s);

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet1306); 

                    t = new ThLessThanMax(pols.get((id3!=null?id3.getText():null)), id4, Double.valueOf(pSetScores.get(s)));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:63:4: 'min' '(' id1= IDENT ',' id2= IDENT ')'
                    {
                    match(input,19,FOLLOW_19_in_pSet1313); 

                    match(input,9,FOLLOW_9_in_pSet1315); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1319); 

                    match(input,13,FOLLOW_13_in_pSet1321); 

                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1325); 

                    match(input,10,FOLLOW_10_in_pSet1327); 

                    t = new ThLessThanMin(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf(pSetScores.get(s)));

                    }
                    break;
                case 5 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:64:4: 'min' '(' id3= IDENT ',' id4= pSet1[s] ')'
                    {
                    match(input,19,FOLLOW_19_in_pSet1334); 

                    match(input,9,FOLLOW_9_in_pSet1336); 

                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pSet1340); 

                    match(input,13,FOLLOW_13_in_pSet1342); 

                    pushFollow(FOLLOW_pSet1_in_pSet1346);
                    id4=pSet1(s);

                    state._fsp--;


                    match(input,10,FOLLOW_10_in_pSet1349); 

                    t = new ThLessThanMin(pols.get((id3!=null?id3.getText():null)), id4, Double.valueOf(pSetScores.get(s)));

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:67:1: pol returns [Pol p] : ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER | '*' '(' ( rule )* ')' 'default' NUMBER );
    public final Pol pol() throws RecognitionException {
        Pol p = null;


        Token NUMBER5=null;
        Token NUMBER7=null;
        Token NUMBER9=null;
        Token NUMBER11=null;
        Rule rule4 =null;

        Rule rule6 =null;

        Rule rule8 =null;

        Rule rule10 =null;


        l = new ArrayList<Rule>(); 
        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:69:2: ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER | '*' '(' ( rule )* ')' 'default' NUMBER )
            int alt9=4;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt9=1;
                }
                break;
            case 18:
                {
                alt9=2;
                }
                break;
            case 19:
                {
                alt9=3;
                }
                break;
            case 11:
                {
                alt9=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }

            switch (alt9) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:69:5: '+' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,12,FOLLOW_12_in_pol373); 

                    match(input,9,FOLLOW_9_in_pol375); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:69:13: ( rule )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==9) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:69:14: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol378);
                    	    rule4=rule();

                    	    state._fsp--;


                    	    l.add(rule4);

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol384); 

                    match(input,17,FOLLOW_17_in_pol386); 

                    NUMBER5=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol388); 

                    p = new Pol(l, Plus$.MODULE$, Double.valueOf((NUMBER5!=null?NUMBER5.getText():null)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:70:4: 'max' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,18,FOLLOW_18_in_pol395); 

                    match(input,9,FOLLOW_9_in_pol397); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:70:14: ( rule )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==9) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:70:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol400);
                    	    rule6=rule();

                    	    state._fsp--;


                    	    l.add(rule6);

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol406); 

                    match(input,17,FOLLOW_17_in_pol408); 

                    NUMBER7=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol410); 

                    p = new Pol(l, Max$.MODULE$, Double.valueOf((NUMBER7!=null?NUMBER7.getText():null)));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:4: 'min' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,19,FOLLOW_19_in_pol417); 

                    match(input,9,FOLLOW_9_in_pol419); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:14: ( rule )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==9) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:71:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol422);
                    	    rule8=rule();

                    	    state._fsp--;


                    	    l.add(rule8);

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol428); 

                    match(input,17,FOLLOW_17_in_pol430); 

                    NUMBER9=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol432); 

                    p = new Pol(l, Min$.MODULE$, Double.valueOf((NUMBER9!=null?NUMBER9.getText():null)));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:4: '*' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,11,FOLLOW_11_in_pol440); 

                    match(input,9,FOLLOW_9_in_pol442); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:12: ( rule )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==9) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:72:13: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol445);
                    	    rule10=rule();

                    	    state._fsp--;


                    	    l.add(rule10);

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol451); 

                    match(input,17,FOLLOW_17_in_pol453); 

                    NUMBER11=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol455); 

                    p = new Pol(l, Mul$.MODULE$, Double.valueOf((NUMBER11!=null?NUMBER11.getText():null)));

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:75:1: rule returns [Rule r] : '(' IDENT NUMBER ')' ;
    public final Rule rule() throws RecognitionException {
        Rule r = null;


        Token IDENT12=null;
        Token NUMBER13=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:76:2: ( '(' IDENT NUMBER ')' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:76:4: '(' IDENT NUMBER ')'
            {
            match(input,9,FOLLOW_9_in_rule473); 

            IDENT12=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule475); 

            NUMBER13=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule477); 

            match(input,10,FOLLOW_10_in_rule479); 

            r = new Rule(new Predicate((IDENT12!=null?IDENT12.getText():null), ""),Double.valueOf((NUMBER13!=null?NUMBER13.getText():null)));

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
    public static final BitSet FOLLOW_16_in_program54 = new BitSet(new long[]{0x00000000000C1800L});
    public static final BitSet FOLLOW_pol_in_program56 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program71 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program73 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program77 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program79 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_program83 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program90 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program92 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet_in_program94 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENT_in_program105 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program107 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_program111 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program113 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program117 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program124 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program126 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet1_in_program128 = new BitSet(new long[]{0x0000000000000022L});
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
    public static final BitSet FOLLOW_10_in_pSet198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pSet205 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet207 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet211 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet213 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet217 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pSet226 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet228 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet232 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet234 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet_in_pSet238 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pSet1263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pSet1270 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet1272 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1276 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet1278 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1282 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet1284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pSet1291 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet1293 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1297 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet1299 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet1_in_pSet1303 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet1306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pSet1313 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet1315 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1319 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet1321 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1325 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet1327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pSet1334 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet1336 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pSet1340 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_pSet1342 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_pSet1_in_pSet1346 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pSet1349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_pol373 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol375 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol378 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol384 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol386 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pol395 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol397 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol400 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol406 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol408 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pol417 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol419 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol422 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol428 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol430 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_pol440 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol442 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol445 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol451 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pol453 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule473 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_rule475 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_rule477 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule479 = new BitSet(new long[]{0x0000000000000002L});

}