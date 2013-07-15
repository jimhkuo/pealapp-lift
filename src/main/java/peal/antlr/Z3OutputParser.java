// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/Z3Output.g 2013-07-15 13:11:13

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

import org.antlr.runtime.tree.*;


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

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return Z3OutputParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/jkuo/PealApp-lift/antlr/Z3Output.g"; }



    @Override
    public void reportError(RecognitionException e) {
    	throw new RuntimeException(getErrorMessage(e, PealProgramParser.tokenNames)); 
    }



    public static class results_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "results"
    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:33:1: results : ( 'Result of analysis [' IDENT '=' IDENT '?' IDENT ( IDENT )? ']:' ( model ) )+ ;
    public final Z3OutputParser.results_return results() throws RecognitionException {
        Z3OutputParser.results_return retval = new Z3OutputParser.results_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token string_literal1=null;
        Token IDENT2=null;
        Token char_literal3=null;
        Token IDENT4=null;
        Token char_literal5=null;
        Token IDENT6=null;
        Token IDENT7=null;
        Token string_literal8=null;
        Z3OutputParser.model_return model9 =null;


        Object string_literal1_tree=null;
        Object IDENT2_tree=null;
        Object char_literal3_tree=null;
        Object IDENT4_tree=null;
        Object char_literal5_tree=null;
        Object IDENT6_tree=null;
        Object IDENT7_tree=null;
        Object string_literal8_tree=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:33:9: ( ( 'Result of analysis [' IDENT '=' IDENT '?' IDENT ( IDENT )? ']:' ( model ) )+ )
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:33:11: ( 'Result of analysis [' IDENT '=' IDENT '?' IDENT ( IDENT )? ']:' ( model ) )+
            {
            root_0 = (Object)adaptor.nil();


            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:33:11: ( 'Result of analysis [' IDENT '=' IDENT '?' IDENT ( IDENT )? ']:' ( model ) )+
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
            	    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:34:2: 'Result of analysis [' IDENT '=' IDENT '?' IDENT ( IDENT )? ']:' ( model )
            	    {
            	    string_literal1=(Token)match(input,14,FOLLOW_14_in_results52); 
            	    string_literal1_tree = 
            	    (Object)adaptor.create(string_literal1)
            	    ;
            	    adaptor.addChild(root_0, string_literal1_tree);


            	    IDENT2=(Token)match(input,IDENT,FOLLOW_IDENT_in_results54); 
            	    IDENT2_tree = 
            	    (Object)adaptor.create(IDENT2)
            	    ;
            	    adaptor.addChild(root_0, IDENT2_tree);


            	    char_literal3=(Token)match(input,12,FOLLOW_12_in_results56); 
            	    char_literal3_tree = 
            	    (Object)adaptor.create(char_literal3)
            	    ;
            	    adaptor.addChild(root_0, char_literal3_tree);


            	    IDENT4=(Token)match(input,IDENT,FOLLOW_IDENT_in_results58); 
            	    IDENT4_tree = 
            	    (Object)adaptor.create(IDENT4)
            	    ;
            	    adaptor.addChild(root_0, IDENT4_tree);


            	    char_literal5=(Token)match(input,13,FOLLOW_13_in_results60); 
            	    char_literal5_tree = 
            	    (Object)adaptor.create(char_literal5)
            	    ;
            	    adaptor.addChild(root_0, char_literal5_tree);


            	    IDENT6=(Token)match(input,IDENT,FOLLOW_IDENT_in_results62); 
            	    IDENT6_tree = 
            	    (Object)adaptor.create(IDENT6)
            	    ;
            	    adaptor.addChild(root_0, IDENT6_tree);


            	    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:34:51: ( IDENT )?
            	    int alt1=2;
            	    int LA1_0 = input.LA(1);

            	    if ( (LA1_0==IDENT) ) {
            	        alt1=1;
            	    }
            	    switch (alt1) {
            	        case 1 :
            	            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:34:52: IDENT
            	            {
            	            IDENT7=(Token)match(input,IDENT,FOLLOW_IDENT_in_results65); 
            	            IDENT7_tree = 
            	            (Object)adaptor.create(IDENT7)
            	            ;
            	            adaptor.addChild(root_0, IDENT7_tree);


            	            }
            	            break;

            	    }


            	    string_literal8=(Token)match(input,15,FOLLOW_15_in_results69); 
            	    string_literal8_tree = 
            	    (Object)adaptor.create(string_literal8)
            	    ;
            	    adaptor.addChild(root_0, string_literal8_tree);


            	    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:35:2: ( model )
            	    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:35:3: model
            	    {
            	    pushFollow(FOLLOW_model_in_results73);
            	    model9=model();

            	    state._fsp--;

            	    adaptor.addChild(root_0, model9.getTree());

            	    }


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

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "results"


    public static class model_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "model"
    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:39:1: model : ( 'sat' '(model' ( define )+ ')' | 'unsat' Z3ERROR );
    public final Z3OutputParser.model_return model() throws RecognitionException {
        Z3OutputParser.model_return retval = new Z3OutputParser.model_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token string_literal10=null;
        Token string_literal11=null;
        Token char_literal13=null;
        Token string_literal14=null;
        Token Z3ERROR15=null;
        Z3OutputParser.define_return define12 =null;


        Object string_literal10_tree=null;
        Object string_literal11_tree=null;
        Object char_literal13_tree=null;
        Object string_literal14_tree=null;
        Object Z3ERROR15_tree=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:41:2: ( 'sat' '(model' ( define )+ ')' | 'unsat' Z3ERROR )
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
                    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:41:4: 'sat' '(model' ( define )+ ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    string_literal10=(Token)match(input,16,FOLLOW_16_in_model92); 
                    string_literal10_tree = 
                    (Object)adaptor.create(string_literal10)
                    ;
                    adaptor.addChild(root_0, string_literal10_tree);


                    string_literal11=(Token)match(input,10,FOLLOW_10_in_model94); 
                    string_literal11_tree = 
                    (Object)adaptor.create(string_literal11)
                    ;
                    adaptor.addChild(root_0, string_literal11_tree);


                    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:41:19: ( define )+
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
                    	    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:41:20: define
                    	    {
                    	    pushFollow(FOLLOW_define_in_model97);
                    	    define12=define();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, define12.getTree());

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


                    char_literal13=(Token)match(input,11,FOLLOW_11_in_model101); 
                    char_literal13_tree = 
                    (Object)adaptor.create(char_literal13)
                    ;
                    adaptor.addChild(root_0, char_literal13_tree);


                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:42:4: 'unsat' Z3ERROR
                    {
                    root_0 = (Object)adaptor.nil();


                    string_literal14=(Token)match(input,17,FOLLOW_17_in_model106); 
                    string_literal14_tree = 
                    (Object)adaptor.create(string_literal14)
                    ;
                    adaptor.addChild(root_0, string_literal14_tree);


                    Z3ERROR15=(Token)match(input,Z3ERROR,FOLLOW_Z3ERROR_in_model108); 
                    Z3ERROR15_tree = 
                    (Object)adaptor.create(Z3ERROR15)
                    ;
                    adaptor.addChild(root_0, Z3ERROR15_tree);


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "model"


    public static class define_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "define"
    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:46:1: define : '(define-fun' IDENT '()' IDENT IDENT ')' ;
    public final Z3OutputParser.define_return define() throws RecognitionException {
        Z3OutputParser.define_return retval = new Z3OutputParser.define_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token string_literal16=null;
        Token IDENT17=null;
        Token string_literal18=null;
        Token IDENT19=null;
        Token IDENT20=null;
        Token char_literal21=null;

        Object string_literal16_tree=null;
        Object IDENT17_tree=null;
        Object string_literal18_tree=null;
        Object IDENT19_tree=null;
        Object IDENT20_tree=null;
        Object char_literal21_tree=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:47:2: ( '(define-fun' IDENT '()' IDENT IDENT ')' )
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:47:4: '(define-fun' IDENT '()' IDENT IDENT ')'
            {
            root_0 = (Object)adaptor.nil();


            string_literal16=(Token)match(input,9,FOLLOW_9_in_define123); 
            string_literal16_tree = 
            (Object)adaptor.create(string_literal16)
            ;
            adaptor.addChild(root_0, string_literal16_tree);


            IDENT17=(Token)match(input,IDENT,FOLLOW_IDENT_in_define125); 
            IDENT17_tree = 
            (Object)adaptor.create(IDENT17)
            ;
            adaptor.addChild(root_0, IDENT17_tree);


            string_literal18=(Token)match(input,8,FOLLOW_8_in_define127); 
            string_literal18_tree = 
            (Object)adaptor.create(string_literal18)
            ;
            adaptor.addChild(root_0, string_literal18_tree);


            IDENT19=(Token)match(input,IDENT,FOLLOW_IDENT_in_define129); 
            IDENT19_tree = 
            (Object)adaptor.create(IDENT19)
            ;
            adaptor.addChild(root_0, IDENT19_tree);


            IDENT20=(Token)match(input,IDENT,FOLLOW_IDENT_in_define131); 
            IDENT20_tree = 
            (Object)adaptor.create(IDENT20)
            ;
            adaptor.addChild(root_0, IDENT20_tree);


            char_literal21=(Token)match(input,11,FOLLOW_11_in_define132); 
            char_literal21_tree = 
            (Object)adaptor.create(char_literal21)
            ;
            adaptor.addChild(root_0, char_literal21_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "define"

    // Delegated rules


 

    public static final BitSet FOLLOW_14_in_results52 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_results54 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_results56 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_results58 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_results60 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_results62 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_IDENT_in_results65 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_results69 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_model_in_results73 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_16_in_model92 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_model94 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_define_in_model97 = new BitSet(new long[]{0x0000000000000A00L});
    public static final BitSet FOLLOW_11_in_model101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_model106 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_Z3ERROR_in_model108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_define123 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_define125 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_define127 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_define129 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_define131 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_define132 = new BitSet(new long[]{0x0000000000000002L});

}