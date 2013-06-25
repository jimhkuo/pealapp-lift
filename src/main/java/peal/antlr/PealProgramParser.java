// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/PealProgram.g 2013-06-25 10:06:08

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMPARE", "IDENT", "NEWLINE", "NUMBER", "WS", "'('", "')'", "'*'", "'+'", "','", "'<'", "'<='", "'='", "'cond'", "'default'", "'max'", "'min'", "'pSet'"
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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:35:1: program : ( 'cond' '=' 'pSet' '<=' n= NUMBER (id1= IDENT '=' pol )* ( 'pSet' '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'pSet' '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'pSet' '=' id1= IDENT ) | 'cond' '=' n= NUMBER '<' 'pSet' (id1= IDENT '=' pol )* ( 'pSet' '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'pSet' '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'pSet' '=' id1= IDENT ) );
    public final void program() throws RecognitionException {
        Token n=null;
        Token id1=null;
        Token id2=null;
        Pol pol1 =null;

        Pol pol2 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:36:2: ( 'cond' '=' 'pSet' '<=' n= NUMBER (id1= IDENT '=' pol )* ( 'pSet' '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'pSet' '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'pSet' '=' id1= IDENT ) | 'cond' '=' n= NUMBER '<' 'pSet' (id1= IDENT '=' pol )* ( 'pSet' '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'pSet' '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'pSet' '=' id1= IDENT ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==17) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==16) ) {
                    int LA5_2 = input.LA(3);

                    if ( (LA5_2==21) ) {
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
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:36:4: 'cond' '=' 'pSet' '<=' n= NUMBER (id1= IDENT '=' pol )* ( 'pSet' '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'pSet' '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'pSet' '=' id1= IDENT )
                    {
                    match(input,17,FOLLOW_17_in_program45); 

                    match(input,16,FOLLOW_16_in_program47); 

                    match(input,21,FOLLOW_21_in_program49); 

                    match(input,15,FOLLOW_15_in_program51); 

                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program55); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:37:2: (id1= IDENT '=' pol )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==IDENT) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:37:3: id1= IDENT '=' pol
                    	    {
                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program62); 

                    	    match(input,16,FOLLOW_16_in_program64); 

                    	    pushFollow(FOLLOW_pol_in_program66);
                    	    pol1=pol();

                    	    state._fsp--;


                    	    pols.put((id1!=null?id1.getText():null), pol1);

                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:38:4: ( 'pSet' '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'pSet' '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'pSet' '=' id1= IDENT )
                    int alt2=3;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==21) ) {
                        int LA2_1 = input.LA(2);

                        if ( (LA2_1==16) ) {
                            switch ( input.LA(3) ) {
                            case 19:
                                {
                                alt2=1;
                                }
                                break;
                            case 20:
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
                            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:39:4: 'pSet' '=' 'max' '(' id1= IDENT ',' id2= IDENT ')'
                            {
                            match(input,21,FOLLOW_21_in_program80); 

                            match(input,16,FOLLOW_16_in_program82); 

                            match(input,19,FOLLOW_19_in_program84); 

                            match(input,9,FOLLOW_9_in_program86); 

                            id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program90); 

                            match(input,13,FOLLOW_13_in_program92); 

                            id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program96); 

                            match(input,10,FOLLOW_10_in_program98); 

                            pSet = new MaxLessThanTh(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf((n!=null?n.getText():null)));

                            }
                            break;
                        case 2 :
                            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:41:4: 'pSet' '=' 'min' '(' id1= IDENT ',' id2= IDENT ')'
                            {
                            match(input,21,FOLLOW_21_in_program112); 

                            match(input,16,FOLLOW_16_in_program114); 

                            match(input,20,FOLLOW_20_in_program116); 

                            match(input,9,FOLLOW_9_in_program118); 

                            id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program122); 

                            match(input,13,FOLLOW_13_in_program124); 

                            id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program128); 

                            match(input,10,FOLLOW_10_in_program130); 

                            pSet = new MinLessThanTh(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf((n!=null?n.getText():null)));

                            }
                            break;
                        case 3 :
                            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:43:4: 'pSet' '=' id1= IDENT
                            {
                            match(input,21,FOLLOW_21_in_program142); 

                            match(input,16,FOLLOW_16_in_program144); 

                            id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program148); 

                            pSet = new PolLessThanTh(pols.get((id1!=null?id1.getText():null)), Double.valueOf((n!=null?n.getText():null)));

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:46:2: 'cond' '=' n= NUMBER '<' 'pSet' (id1= IDENT '=' pol )* ( 'pSet' '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'pSet' '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'pSet' '=' id1= IDENT )
                    {
                    match(input,17,FOLLOW_17_in_program159); 

                    match(input,16,FOLLOW_16_in_program161); 

                    n=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_program165); 

                    match(input,14,FOLLOW_14_in_program167); 

                    match(input,21,FOLLOW_21_in_program169); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:2: (id1= IDENT '=' pol )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==IDENT) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:47:3: id1= IDENT '=' pol
                    	    {
                    	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program175); 

                    	    match(input,16,FOLLOW_16_in_program177); 

                    	    pushFollow(FOLLOW_pol_in_program179);
                    	    pol2=pol();

                    	    state._fsp--;


                    	    pols.put((id1!=null?id1.getText():null), pol2);

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:48:4: ( 'pSet' '=' 'max' '(' id1= IDENT ',' id2= IDENT ')' | 'pSet' '=' 'min' '(' id1= IDENT ',' id2= IDENT ')' | 'pSet' '=' id1= IDENT )
                    int alt4=3;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==21) ) {
                        int LA4_1 = input.LA(2);

                        if ( (LA4_1==16) ) {
                            switch ( input.LA(3) ) {
                            case 19:
                                {
                                alt4=1;
                                }
                                break;
                            case 20:
                                {
                                alt4=2;
                                }
                                break;
                            case IDENT:
                                {
                                alt4=3;
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("", 4, 2, input);

                                throw nvae;

                            }

                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 4, 1, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 0, input);

                        throw nvae;

                    }
                    switch (alt4) {
                        case 1 :
                            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:49:4: 'pSet' '=' 'max' '(' id1= IDENT ',' id2= IDENT ')'
                            {
                            match(input,21,FOLLOW_21_in_program193); 

                            match(input,16,FOLLOW_16_in_program195); 

                            match(input,19,FOLLOW_19_in_program197); 

                            match(input,9,FOLLOW_9_in_program199); 

                            id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program203); 

                            match(input,13,FOLLOW_13_in_program205); 

                            id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program209); 

                            match(input,10,FOLLOW_10_in_program211); 

                            pSet = new ThLessThanMax(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf((n!=null?n.getText():null)));

                            }
                            break;
                        case 2 :
                            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:51:4: 'pSet' '=' 'min' '(' id1= IDENT ',' id2= IDENT ')'
                            {
                            match(input,21,FOLLOW_21_in_program225); 

                            match(input,16,FOLLOW_16_in_program227); 

                            match(input,20,FOLLOW_20_in_program229); 

                            match(input,9,FOLLOW_9_in_program231); 

                            id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program235); 

                            match(input,13,FOLLOW_13_in_program237); 

                            id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_program241); 

                            match(input,10,FOLLOW_10_in_program243); 

                            pSet = new ThLessThanMin(pols.get((id1!=null?id1.getText():null)), pols.get((id2!=null?id2.getText():null)), Double.valueOf((n!=null?n.getText():null)));

                            }
                            break;
                        case 3 :
                            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:53:4: 'pSet' '=' id1= IDENT
                            {
                            match(input,21,FOLLOW_21_in_program255); 

                            match(input,16,FOLLOW_16_in_program257); 

                            id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_program261); 

                            pSet = new ThLessThanPol(pols.get((id1!=null?id1.getText():null)), Double.valueOf((n!=null?n.getText():null)));

                            }
                            break;

                    }


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



    // $ANTLR start "pol"
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:57:1: pol returns [Pol p] : ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER | '*' '(' ( rule )* ')' 'default' NUMBER );
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
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:2: ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER | '*' '(' ( rule )* ')' 'default' NUMBER )
            int alt10=4;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt10=1;
                }
                break;
            case 19:
                {
                alt10=2;
                }
                break;
            case 20:
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
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:5: '+' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,12,FOLLOW_12_in_pol288); 

                    match(input,9,FOLLOW_9_in_pol290); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:13: ( rule )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==9) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:59:14: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol293);
                    	    rule3=rule();

                    	    state._fsp--;


                    	    l.add(rule3);

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol299); 

                    match(input,18,FOLLOW_18_in_pol301); 

                    NUMBER4=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol303); 

                    p = new Pol(l, Plus$.MODULE$, Double.valueOf((NUMBER4!=null?NUMBER4.getText():null)));

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:60:4: 'max' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,19,FOLLOW_19_in_pol310); 

                    match(input,9,FOLLOW_9_in_pol312); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:60:14: ( rule )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==9) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:60:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol315);
                    	    rule5=rule();

                    	    state._fsp--;


                    	    l.add(rule5);

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol321); 

                    match(input,18,FOLLOW_18_in_pol323); 

                    NUMBER6=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol325); 

                    p = new Pol(l, Max$.MODULE$, Double.valueOf((NUMBER6!=null?NUMBER6.getText():null)));

                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:61:4: 'min' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,20,FOLLOW_20_in_pol332); 

                    match(input,9,FOLLOW_9_in_pol334); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:61:14: ( rule )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==9) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:61:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol337);
                    	    rule7=rule();

                    	    state._fsp--;


                    	    l.add(rule7);

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol343); 

                    match(input,18,FOLLOW_18_in_pol345); 

                    NUMBER8=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol347); 

                    p = new Pol(l, Min$.MODULE$, Double.valueOf((NUMBER8!=null?NUMBER8.getText():null)));

                    }
                    break;
                case 4 :
                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:62:4: '*' '(' ( rule )* ')' 'default' NUMBER
                    {
                    match(input,11,FOLLOW_11_in_pol355); 

                    match(input,9,FOLLOW_9_in_pol357); 

                    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:62:12: ( rule )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==9) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:62:13: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol360);
                    	    rule9=rule();

                    	    state._fsp--;


                    	    l.add(rule9);

                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    match(input,10,FOLLOW_10_in_pol366); 

                    match(input,18,FOLLOW_18_in_pol368); 

                    NUMBER10=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol370); 

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
    // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:65:1: rule returns [Rule r] : '(' IDENT NUMBER ')' ;
    public final Rule rule() throws RecognitionException {
        Rule r = null;


        Token IDENT11=null;
        Token NUMBER12=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:66:2: ( '(' IDENT NUMBER ')' )
            // /Users/jkuo/PealApp-lift/antlr/PealProgram.g:66:4: '(' IDENT NUMBER ')'
            {
            match(input,9,FOLLOW_9_in_rule388); 

            IDENT11=(Token)match(input,IDENT,FOLLOW_IDENT_in_rule390); 

            NUMBER12=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule392); 

            match(input,10,FOLLOW_10_in_rule394); 

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


 

    public static final BitSet FOLLOW_17_in_program45 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program47 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_program49 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_program51 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_program55 = new BitSet(new long[]{0x0000000000200020L});
    public static final BitSet FOLLOW_IDENT_in_program62 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program64 = new BitSet(new long[]{0x0000000000181800L});
    public static final BitSet FOLLOW_pol_in_program66 = new BitSet(new long[]{0x0000000000200020L});
    public static final BitSet FOLLOW_21_in_program80 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program82 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_program84 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_program86 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program90 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_program92 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program96 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_program98 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_program112 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program114 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_program116 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_program118 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program122 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_program124 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program128 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_program130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_program142 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program144 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_program159 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program161 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_program165 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_program167 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_program169 = new BitSet(new long[]{0x0000000000200020L});
    public static final BitSet FOLLOW_IDENT_in_program175 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program177 = new BitSet(new long[]{0x0000000000181800L});
    public static final BitSet FOLLOW_pol_in_program179 = new BitSet(new long[]{0x0000000000200020L});
    public static final BitSet FOLLOW_21_in_program193 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program195 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_program197 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_program199 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program203 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_program205 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program209 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_program211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_program225 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program227 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_program229 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_program231 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program235 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_program237 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program241 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_program243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_program255 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_program257 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_program261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_pol288 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol290 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol293 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol299 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_pol301 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_pol310 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol312 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol315 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol321 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_pol323 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_pol332 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol334 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol337 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol343 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_pol345 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_pol355 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pol357 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_rule_in_pol360 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_10_in_pol366 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_pol368 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_pol370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_rule388 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_rule390 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NUMBER_in_rule392 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_rule394 = new BitSet(new long[]{0x0000000000000002L});

}