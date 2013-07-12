// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/Z3Output.g 2013-07-12 16:41:19

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ERROR", "IDENT", "NEWLINE", "NUMBER", "WS", "'()'", "'(define-fun'", "'(model'", "')'", "'='", "'?'", "'Result of analysis ['", "']:'", "'sat'", "'unsat'"
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
    public static final int ERROR=4;
    public static final int IDENT=5;
    public static final int NEWLINE=6;
    public static final int NUMBER=7;
    public static final int WS=8;

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
    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:32:1: results : ( 'Result of analysis [' IDENT '=' IDENT '?' IDENT ( IDENT )? ']:' ( 'unsat' ERROR | 'sat' model ) )+ ;
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
        Token string_literal9=null;
        Token ERROR10=null;
        Token string_literal11=null;
        Z3OutputParser.model_return model12 =null;


        Object string_literal1_tree=null;
        Object IDENT2_tree=null;
        Object char_literal3_tree=null;
        Object IDENT4_tree=null;
        Object char_literal5_tree=null;
        Object IDENT6_tree=null;
        Object IDENT7_tree=null;
        Object string_literal8_tree=null;
        Object string_literal9_tree=null;
        Object ERROR10_tree=null;
        Object string_literal11_tree=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:32:9: ( ( 'Result of analysis [' IDENT '=' IDENT '?' IDENT ( IDENT )? ']:' ( 'unsat' ERROR | 'sat' model ) )+ )
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:32:11: ( 'Result of analysis [' IDENT '=' IDENT '?' IDENT ( IDENT )? ']:' ( 'unsat' ERROR | 'sat' model ) )+
            {
            root_0 = (Object)adaptor.nil();


            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:32:11: ( 'Result of analysis [' IDENT '=' IDENT '?' IDENT ( IDENT )? ']:' ( 'unsat' ERROR | 'sat' model ) )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==15) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:33:2: 'Result of analysis [' IDENT '=' IDENT '?' IDENT ( IDENT )? ']:' ( 'unsat' ERROR | 'sat' model )
            	    {
            	    string_literal1=(Token)match(input,15,FOLLOW_15_in_results51); 
            	    string_literal1_tree = 
            	    (Object)adaptor.create(string_literal1)
            	    ;
            	    adaptor.addChild(root_0, string_literal1_tree);


            	    IDENT2=(Token)match(input,IDENT,FOLLOW_IDENT_in_results53); 
            	    IDENT2_tree = 
            	    (Object)adaptor.create(IDENT2)
            	    ;
            	    adaptor.addChild(root_0, IDENT2_tree);


            	    char_literal3=(Token)match(input,13,FOLLOW_13_in_results55); 
            	    char_literal3_tree = 
            	    (Object)adaptor.create(char_literal3)
            	    ;
            	    adaptor.addChild(root_0, char_literal3_tree);


            	    IDENT4=(Token)match(input,IDENT,FOLLOW_IDENT_in_results57); 
            	    IDENT4_tree = 
            	    (Object)adaptor.create(IDENT4)
            	    ;
            	    adaptor.addChild(root_0, IDENT4_tree);


            	    char_literal5=(Token)match(input,14,FOLLOW_14_in_results59); 
            	    char_literal5_tree = 
            	    (Object)adaptor.create(char_literal5)
            	    ;
            	    adaptor.addChild(root_0, char_literal5_tree);


            	    IDENT6=(Token)match(input,IDENT,FOLLOW_IDENT_in_results61); 
            	    IDENT6_tree = 
            	    (Object)adaptor.create(IDENT6)
            	    ;
            	    adaptor.addChild(root_0, IDENT6_tree);


            	    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:33:51: ( IDENT )?
            	    int alt1=2;
            	    int LA1_0 = input.LA(1);

            	    if ( (LA1_0==IDENT) ) {
            	        alt1=1;
            	    }
            	    switch (alt1) {
            	        case 1 :
            	            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:33:52: IDENT
            	            {
            	            IDENT7=(Token)match(input,IDENT,FOLLOW_IDENT_in_results64); 
            	            IDENT7_tree = 
            	            (Object)adaptor.create(IDENT7)
            	            ;
            	            adaptor.addChild(root_0, IDENT7_tree);


            	            }
            	            break;

            	    }


            	    string_literal8=(Token)match(input,16,FOLLOW_16_in_results68); 
            	    string_literal8_tree = 
            	    (Object)adaptor.create(string_literal8)
            	    ;
            	    adaptor.addChild(root_0, string_literal8_tree);


            	    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:34:2: ( 'unsat' ERROR | 'sat' model )
            	    int alt2=2;
            	    int LA2_0 = input.LA(1);

            	    if ( (LA2_0==18) ) {
            	        alt2=1;
            	    }
            	    else if ( (LA2_0==17) ) {
            	        alt2=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 2, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt2) {
            	        case 1 :
            	            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:34:3: 'unsat' ERROR
            	            {
            	            string_literal9=(Token)match(input,18,FOLLOW_18_in_results72); 
            	            string_literal9_tree = 
            	            (Object)adaptor.create(string_literal9)
            	            ;
            	            adaptor.addChild(root_0, string_literal9_tree);


            	            ERROR10=(Token)match(input,ERROR,FOLLOW_ERROR_in_results74); 
            	            ERROR10_tree = 
            	            (Object)adaptor.create(ERROR10)
            	            ;
            	            adaptor.addChild(root_0, ERROR10_tree);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:34:18: 'sat' model
            	            {
            	            string_literal11=(Token)match(input,17,FOLLOW_17_in_results77); 
            	            string_literal11_tree = 
            	            (Object)adaptor.create(string_literal11)
            	            ;
            	            adaptor.addChild(root_0, string_literal11_tree);


            	            pushFollow(FOLLOW_model_in_results79);
            	            model12=model();

            	            state._fsp--;

            	            adaptor.addChild(root_0, model12.getTree());

            	            }
            	            break;

            	    }


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
    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:38:1: model : '(model' ( define )+ ')' ;
    public final Z3OutputParser.model_return model() throws RecognitionException {
        Z3OutputParser.model_return retval = new Z3OutputParser.model_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token string_literal13=null;
        Token char_literal15=null;
        Z3OutputParser.define_return define14 =null;


        Object string_literal13_tree=null;
        Object char_literal15_tree=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:38:8: ( '(model' ( define )+ ')' )
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:38:10: '(model' ( define )+ ')'
            {
            root_0 = (Object)adaptor.nil();


            string_literal13=(Token)match(input,11,FOLLOW_11_in_model96); 
            string_literal13_tree = 
            (Object)adaptor.create(string_literal13)
            ;
            adaptor.addChild(root_0, string_literal13_tree);


            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:38:19: ( define )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==10) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:38:20: define
            	    {
            	    pushFollow(FOLLOW_define_in_model99);
            	    define14=define();

            	    state._fsp--;

            	    adaptor.addChild(root_0, define14.getTree());

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


            char_literal15=(Token)match(input,12,FOLLOW_12_in_model103); 
            char_literal15_tree = 
            (Object)adaptor.create(char_literal15)
            ;
            adaptor.addChild(root_0, char_literal15_tree);


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
    // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:41:1: define : '(define-fun' IDENT '()' IDENT IDENT ')' ;
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
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:41:9: ( '(define-fun' IDENT '()' IDENT IDENT ')' )
            // /Users/jkuo/PealApp-lift/antlr/Z3Output.g:41:11: '(define-fun' IDENT '()' IDENT IDENT ')'
            {
            root_0 = (Object)adaptor.nil();


            string_literal16=(Token)match(input,10,FOLLOW_10_in_define114); 
            string_literal16_tree = 
            (Object)adaptor.create(string_literal16)
            ;
            adaptor.addChild(root_0, string_literal16_tree);


            IDENT17=(Token)match(input,IDENT,FOLLOW_IDENT_in_define116); 
            IDENT17_tree = 
            (Object)adaptor.create(IDENT17)
            ;
            adaptor.addChild(root_0, IDENT17_tree);


            string_literal18=(Token)match(input,9,FOLLOW_9_in_define118); 
            string_literal18_tree = 
            (Object)adaptor.create(string_literal18)
            ;
            adaptor.addChild(root_0, string_literal18_tree);


            IDENT19=(Token)match(input,IDENT,FOLLOW_IDENT_in_define120); 
            IDENT19_tree = 
            (Object)adaptor.create(IDENT19)
            ;
            adaptor.addChild(root_0, IDENT19_tree);


            IDENT20=(Token)match(input,IDENT,FOLLOW_IDENT_in_define122); 
            IDENT20_tree = 
            (Object)adaptor.create(IDENT20)
            ;
            adaptor.addChild(root_0, IDENT20_tree);


            char_literal21=(Token)match(input,12,FOLLOW_12_in_define123); 
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


 

    public static final BitSet FOLLOW_15_in_results51 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_results53 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_results55 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_results57 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_results59 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_results61 = new BitSet(new long[]{0x0000000000010020L});
    public static final BitSet FOLLOW_IDENT_in_results64 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_results68 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_18_in_results72 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ERROR_in_results74 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_17_in_results77 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_model_in_results79 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_11_in_model96 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_define_in_model99 = new BitSet(new long[]{0x0000000000001400L});
    public static final BitSet FOLLOW_12_in_model103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_define114 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_define116 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_define118 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_define120 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_define122 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_define123 = new BitSet(new long[]{0x0000000000000002L});

}