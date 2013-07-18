// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/Z3Output.g 2013-07-18 15:42:58

package peal.antlr;
import java.util.*;
import peal.domain.z3.*;
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
public class Z3OutputParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENT", "NUMBER", "WS", "Z3ERROR", "'('", "'()'", "'(define-fun'", "'(model'", "')'", "'='", "'?'", "'Result of analysis ['", "']:'", "'sat'", "'unsat'"
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
    public static final int IDENT=4;
    public static final int NUMBER=5;
    public static final int WS=6;
    public static final int Z3ERROR=7;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public Z3OutputParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public Z3OutputParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return Z3OutputParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/jkuo/PealApp-lift/antlr/Z3Output.g"; }




    @Override
    public void reportError(RecognitionException e) {
    	throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
    }




    // $ANTLR start "results"
    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:33:1: results returns [Map<String, Model> r] : ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+ ;
    public final Map<String, Model> results() throws RecognitionException {
        Map<String, Model> r = null;


        Token id0=null;
        Token id1=null;
        Token id2=null;
        Model m =null;


        r = new HashMap<String, Model>();
        try {
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:35:2: ( ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+ )
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:35:4: ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+
            {
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:35:4: ( 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model ) )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==15) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:36:2: 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model )
            	    {
            	    match(input,15,FOLLOW_15_in_results57); 

            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_results61); 

            	    match(input,13,FOLLOW_13_in_results63); 

            	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_results67); 

            	    match(input,14,FOLLOW_14_in_results69); 

            	    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_results73); 

            	    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:36:63: ( IDENT )?
            	    int alt1=2;
            	    int LA1_0 = input.LA(1);

            	    if ( (LA1_0==IDENT) ) {
            	        alt1=1;
            	    }
            	    switch (alt1) {
            	        case 1 :
            	            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:36:64: IDENT
            	            {
            	            match(input,IDENT,FOLLOW_IDENT_in_results76); 

            	            }
            	            break;

            	    }


            	    match(input,16,FOLLOW_16_in_results80); 

            	    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:37:2: (m= model )
            	    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:37:3: m= model
            	    {
            	    pushFollow(FOLLOW_model_in_results86);
            	    m=model();

            	    state._fsp--;


            	    }


            	     r.put((id0!=null?id0.getText():null), m);

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
        return r;
    }
    // $ANTLR end "results"



    // $ANTLR start "model"
    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:41:1: model returns [Model m] : ( 'sat' '(model' ( define )+ ')' | 'unsat' Z3ERROR );
    public final Model model() throws RecognitionException {
        Model m = null;


        Define define1 =null;


         List<Define> l = new ArrayList<Define>(); 
        try {
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:43:2: ( 'sat' '(model' ( define )+ ')' | 'unsat' Z3ERROR )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==17) ) {
                alt4=1;
            }
            else if ( (LA4_0==18) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:43:4: 'sat' '(model' ( define )+ ')'
                    {
                    match(input,17,FOLLOW_17_in_model113); 

                    match(input,11,FOLLOW_11_in_model115); 

                    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:43:19: ( define )+
                    int cnt3=0;
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==10) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:43:20: define
                    	    {
                    	    pushFollow(FOLLOW_define_in_model118);
                    	    define1=define();

                    	    state._fsp--;


                    	    l.add(define1);

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


                    match(input,12,FOLLOW_12_in_model124); 

                     m = new Model(Sat$.MODULE$, l);

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:44:4: 'unsat' Z3ERROR
                    {
                    match(input,18,FOLLOW_18_in_model131); 

                    match(input,Z3ERROR,FOLLOW_Z3ERROR_in_model133); 

                     m = new Model(Unsat$.MODULE$, l);

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
        return m;
    }
    // $ANTLR end "model"



    // $ANTLR start "define"
    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:48:1: define returns [Define d] : ( '(define-fun' id0= IDENT '()' id1= IDENT id4= value ')' | '(define-fun' id0= IDENT '()' id1= IDENT '(' id2= NUMBER id3= NUMBER ')' ')' );
    public final Define define() throws RecognitionException {
        Define d = null;


        Token id0=null;
        Token id1=null;
        Token id2=null;
        Token id3=null;
        String id4 =null;


        try {
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:49:2: ( '(define-fun' id0= IDENT '()' id1= IDENT id4= value ')' | '(define-fun' id0= IDENT '()' id1= IDENT '(' id2= NUMBER id3= NUMBER ')' ')' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==10) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==IDENT) ) {
                    int LA5_2 = input.LA(3);

                    if ( (LA5_2==9) ) {
                        int LA5_3 = input.LA(4);

                        if ( (LA5_3==IDENT) ) {
                            int LA5_4 = input.LA(5);

                            if ( (LA5_4==8) ) {
                                alt5=2;
                            }
                            else if ( ((LA5_4 >= IDENT && LA5_4 <= NUMBER)) ) {
                                alt5=1;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 5, 4, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 5, 3, input);

                            throw nvae;

                        }
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
                    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:49:4: '(define-fun' id0= IDENT '()' id1= IDENT id4= value ')'
                    {
                    match(input,10,FOLLOW_10_in_define152); 

                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_define156); 

                    match(input,9,FOLLOW_9_in_define158); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_define162); 

                    pushFollow(FOLLOW_value_in_define166);
                    id4=value();

                    state._fsp--;


                    match(input,12,FOLLOW_12_in_define167); 

                    d = new Define((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), id4);

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:51:4: '(define-fun' id0= IDENT '()' id1= IDENT '(' id2= NUMBER id3= NUMBER ')' ')'
                    {
                    match(input,10,FOLLOW_10_in_define176); 

                    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_define180); 

                    match(input,9,FOLLOW_9_in_define182); 

                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_define186); 

                    match(input,8,FOLLOW_8_in_define188); 

                    id2=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_define192); 

                    id3=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_define196); 

                    match(input,12,FOLLOW_12_in_define197); 

                    match(input,12,FOLLOW_12_in_define198); 

                    d = new Define((id0!=null?id0.getText():null), (id1!=null?id1.getText():null), (id2!=null?id2.getText():null) + (id3!=null?id3.getText():null));

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
        return d;
    }
    // $ANTLR end "define"



    // $ANTLR start "value"
    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:55:1: value returns [String s] : ( IDENT | NUMBER );
    public final String value() throws RecognitionException {
        String s = null;


        Token IDENT2=null;
        Token NUMBER3=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:56:2: ( IDENT | NUMBER )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==IDENT) ) {
                alt6=1;
            }
            else if ( (LA6_0==NUMBER) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:56:4: IDENT
                    {
                    IDENT2=(Token)match(input,IDENT,FOLLOW_IDENT_in_value220); 

                    s = (IDENT2!=null?IDENT2.getText():null);

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:57:4: NUMBER
                    {
                    NUMBER3=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value227); 

                    s = (NUMBER3!=null?NUMBER3.getText():null);

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
        return s;
    }
    // $ANTLR end "value"

    // Delegated rules


 

    public static final BitSet FOLLOW_15_in_results57 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_results61 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_results63 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_results67 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_results69 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_results73 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_IDENT_in_results76 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_results80 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_model_in_results86 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_17_in_model113 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_model115 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_define_in_model118 = new BitSet(new long[]{0x0000000000001400L});
    public static final BitSet FOLLOW_12_in_model124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_model131 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_Z3ERROR_in_model133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_define152 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_define156 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_define158 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_define162 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_value_in_define166 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_define167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_define176 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_define180 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_define182 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_define186 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_define188 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_define192 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_define196 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_define197 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_define198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_value220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_value227 = new BitSet(new long[]{0x0000000000000002L});

}