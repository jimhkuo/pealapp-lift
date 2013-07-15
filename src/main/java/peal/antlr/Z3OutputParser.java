// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/Z3Output.g 2013-07-15 13:40:37

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENT", "NUMBER", "WS", "Z3ERROR", "'()'", "'(define-fun'", "'(model'", "')'", "'='", "'?'", "'Result of analysis ['", "']:'", "'sat'", "'unsat'"
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

                if ( (LA2_0==14) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:36:2: 'Result of analysis [' id0= IDENT '=' id1= IDENT '?' id2= IDENT ( IDENT )? ']:' (m= model )
            	    {
            	    match(input,14,FOLLOW_14_in_results57); 

            	    id0=(Token)match(input,IDENT,FOLLOW_IDENT_in_results61); 

            	    match(input,12,FOLLOW_12_in_results63); 

            	    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_results67); 

            	    match(input,13,FOLLOW_13_in_results69); 

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


            	    match(input,15,FOLLOW_15_in_results80); 

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


        try {
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:42:2: ( 'sat' '(model' ( define )+ ')' | 'unsat' Z3ERROR )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==16) ) {
                alt4=1;
            }
            else if ( (LA4_0==17) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:42:4: 'sat' '(model' ( define )+ ')'
                    {
                    match(input,16,FOLLOW_16_in_model109); 

                    match(input,10,FOLLOW_10_in_model111); 

                    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:42:19: ( define )+
                    int cnt3=0;
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==9) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:42:20: define
                    	    {
                    	    pushFollow(FOLLOW_define_in_model114);
                    	    define();

                    	    state._fsp--;


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


                    match(input,11,FOLLOW_11_in_model118); 

                     m = new Model(Sat$.MODULE$, new ArrayList<Define>());

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:43:4: 'unsat' Z3ERROR
                    {
                    match(input,17,FOLLOW_17_in_model125); 

                    match(input,Z3ERROR,FOLLOW_Z3ERROR_in_model127); 

                     m = new Model(Unsat$.MODULE$, new ArrayList<Define>());

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
    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:47:1: define : '(define-fun' IDENT '()' IDENT IDENT ')' ;
    public final void define() throws RecognitionException {
        try {
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:48:2: ( '(define-fun' IDENT '()' IDENT IDENT ')' )
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:48:4: '(define-fun' IDENT '()' IDENT IDENT ')'
            {
            match(input,9,FOLLOW_9_in_define143); 

            match(input,IDENT,FOLLOW_IDENT_in_define145); 

            match(input,8,FOLLOW_8_in_define147); 

            match(input,IDENT,FOLLOW_IDENT_in_define149); 

            match(input,IDENT,FOLLOW_IDENT_in_define151); 

            match(input,11,FOLLOW_11_in_define152); 

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
    // $ANTLR end "define"

    // Delegated rules


 

    public static final BitSet FOLLOW_14_in_results57 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_results61 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_results63 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_results67 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_results69 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_results73 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_IDENT_in_results76 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_results80 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_model_in_results86 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_16_in_model109 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_model111 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_define_in_model114 = new BitSet(new long[]{0x0000000000000A00L});
    public static final BitSet FOLLOW_11_in_model118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_model125 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_Z3ERROR_in_model127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_define143 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_define145 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_define147 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_define149 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_define151 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_define152 = new BitSet(new long[]{0x0000000000000002L});

}