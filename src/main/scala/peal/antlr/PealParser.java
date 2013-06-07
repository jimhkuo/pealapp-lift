// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/Peal.g 2013-06-07 07:49:07

package peal.antlr;
import peal.domain.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class PealParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMPARE", "IDENT", "NUMBER", "WS", "'('", "')'", "'+'", "','", "'<'", "'<='", "'='", "'default'", "'if'", "'max'", "'min'"
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
    public static final int COMPARE=4;
    public static final int IDENT=5;
    public static final int NUMBER=6;
    public static final int WS=7;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public PealParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public PealParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return PealParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/jkuo/PealApp-lift/antlr/Peal.g"; }


    public static class pred_return extends ParserRuleReturnScope {
        public Double i;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pred"
    // /Users/jkuo/PealApp-lift/antlr/Peal.g:17:1: pred returns [Double i] : (id1= IDENT '=' id2= IDENT |id3= IDENT );
    public final PealParser.pred_return pred() throws RecognitionException {
        PealParser.pred_return retval = new PealParser.pred_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token id1=null;
        Token id2=null;
        Token id3=null;
        Token char_literal1=null;

        Object id1_tree=null;
        Object id2_tree=null;
        Object id3_tree=null;
        Object char_literal1_tree=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:18:2: (id1= IDENT '=' id2= IDENT |id3= IDENT )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==IDENT) ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1==14) ) {
                    alt1=1;
                }
                else if ( (LA1_1==NUMBER) ) {
                    alt1=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }
            switch (alt1) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:18:4: id1= IDENT '=' id2= IDENT
                    {
                    root_0 = (Object)adaptor.nil();


                    id1=(Token)match(input,IDENT,FOLLOW_IDENT_in_pred52); 
                    id1_tree = 
                    (Object)adaptor.create(id1)
                    ;
                    adaptor.addChild(root_0, id1_tree);


                    char_literal1=(Token)match(input,14,FOLLOW_14_in_pred54); 
                    char_literal1_tree = 
                    (Object)adaptor.create(char_literal1)
                    ;
                    adaptor.addChild(root_0, char_literal1_tree);


                    id2=(Token)match(input,IDENT,FOLLOW_IDENT_in_pred58); 
                    id2_tree = 
                    (Object)adaptor.create(id2)
                    ;
                    adaptor.addChild(root_0, id2_tree);


                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:19:4: id3= IDENT
                    {
                    root_0 = (Object)adaptor.nil();


                    id3=(Token)match(input,IDENT,FOLLOW_IDENT_in_pred66); 
                    id3_tree = 
                    (Object)adaptor.create(id3)
                    ;
                    adaptor.addChild(root_0, id3_tree);


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
    // $ANTLR end "pred"


    public static class rule_return extends ParserRuleReturnScope {
        public Double i;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rule"
    // /Users/jkuo/PealApp-lift/antlr/Peal.g:22:1: rule returns [Double i] : '(' 'if' pred NUMBER ')' ;
    public final PealParser.rule_return rule() throws RecognitionException {
        PealParser.rule_return retval = new PealParser.rule_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal2=null;
        Token string_literal3=null;
        Token NUMBER5=null;
        Token char_literal6=null;
        PealParser.pred_return pred4 =null;


        Object char_literal2_tree=null;
        Object string_literal3_tree=null;
        Object NUMBER5_tree=null;
        Object char_literal6_tree=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:23:2: ( '(' 'if' pred NUMBER ')' )
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:23:4: '(' 'if' pred NUMBER ')'
            {
            root_0 = (Object)adaptor.nil();


            char_literal2=(Token)match(input,8,FOLLOW_8_in_rule81); 
            char_literal2_tree = 
            (Object)adaptor.create(char_literal2)
            ;
            adaptor.addChild(root_0, char_literal2_tree);


            string_literal3=(Token)match(input,16,FOLLOW_16_in_rule83); 
            string_literal3_tree = 
            (Object)adaptor.create(string_literal3)
            ;
            adaptor.addChild(root_0, string_literal3_tree);


            pushFollow(FOLLOW_pred_in_rule85);
            pred4=pred();

            state._fsp--;

            adaptor.addChild(root_0, pred4.getTree());

            NUMBER5=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_rule87); 
            NUMBER5_tree = 
            (Object)adaptor.create(NUMBER5)
            ;
            adaptor.addChild(root_0, NUMBER5_tree);


            char_literal6=(Token)match(input,9,FOLLOW_9_in_rule89); 
            char_literal6_tree = 
            (Object)adaptor.create(char_literal6)
            ;
            adaptor.addChild(root_0, char_literal6_tree);


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
    // $ANTLR end "rule"


    public static class cond_return extends ParserRuleReturnScope {
        public Boolean e;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "cond"
    // /Users/jkuo/PealApp-lift/antlr/Peal.g:26:1: cond returns [Boolean e] : ( NUMBER '<' pSet | pSet '<=' NUMBER );
    public final PealParser.cond_return cond() throws RecognitionException {
        PealParser.cond_return retval = new PealParser.cond_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token NUMBER7=null;
        Token char_literal8=null;
        Token string_literal11=null;
        Token NUMBER12=null;
        PealParser.pSet_return pSet9 =null;

        PealParser.pSet_return pSet10 =null;


        Object NUMBER7_tree=null;
        Object char_literal8_tree=null;
        Object string_literal11_tree=null;
        Object NUMBER12_tree=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:27:2: ( NUMBER '<' pSet | pSet '<=' NUMBER )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==NUMBER) ) {
                alt2=1;
            }
            else if ( (LA2_0==10||(LA2_0 >= 17 && LA2_0 <= 18)) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:27:4: NUMBER '<' pSet
                    {
                    root_0 = (Object)adaptor.nil();


                    NUMBER7=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_cond105); 
                    NUMBER7_tree = 
                    (Object)adaptor.create(NUMBER7)
                    ;
                    adaptor.addChild(root_0, NUMBER7_tree);


                    char_literal8=(Token)match(input,12,FOLLOW_12_in_cond107); 
                    char_literal8_tree = 
                    (Object)adaptor.create(char_literal8)
                    ;
                    adaptor.addChild(root_0, char_literal8_tree);


                    pushFollow(FOLLOW_pSet_in_cond109);
                    pSet9=pSet();

                    state._fsp--;

                    adaptor.addChild(root_0, pSet9.getTree());

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:28:4: pSet '<=' NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pSet_in_cond114);
                    pSet10=pSet();

                    state._fsp--;

                    adaptor.addChild(root_0, pSet10.getTree());

                    string_literal11=(Token)match(input,13,FOLLOW_13_in_cond116); 
                    string_literal11_tree = 
                    (Object)adaptor.create(string_literal11)
                    ;
                    adaptor.addChild(root_0, string_literal11_tree);


                    NUMBER12=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_cond118); 
                    NUMBER12_tree = 
                    (Object)adaptor.create(NUMBER12)
                    ;
                    adaptor.addChild(root_0, NUMBER12_tree);


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
    // $ANTLR end "cond"


    public static class pol_return extends ParserRuleReturnScope {
        public Double i;
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pol"
    // /Users/jkuo/PealApp-lift/antlr/Peal.g:31:1: pol returns [Double i] : ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER );
    public final PealParser.pol_return pol() throws RecognitionException {
        PealParser.pol_return retval = new PealParser.pol_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal13=null;
        Token char_literal14=null;
        Token char_literal16=null;
        Token string_literal17=null;
        Token NUMBER18=null;
        Token string_literal19=null;
        Token char_literal20=null;
        Token char_literal22=null;
        Token string_literal23=null;
        Token NUMBER24=null;
        Token string_literal25=null;
        Token char_literal26=null;
        Token char_literal28=null;
        Token string_literal29=null;
        Token NUMBER30=null;
        PealParser.rule_return rule15 =null;

        PealParser.rule_return rule21 =null;

        PealParser.rule_return rule27 =null;


        Object char_literal13_tree=null;
        Object char_literal14_tree=null;
        Object char_literal16_tree=null;
        Object string_literal17_tree=null;
        Object NUMBER18_tree=null;
        Object string_literal19_tree=null;
        Object char_literal20_tree=null;
        Object char_literal22_tree=null;
        Object string_literal23_tree=null;
        Object NUMBER24_tree=null;
        Object string_literal25_tree=null;
        Object char_literal26_tree=null;
        Object char_literal28_tree=null;
        Object string_literal29_tree=null;
        Object NUMBER30_tree=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:32:2: ( '+' '(' ( rule )* ')' 'default' NUMBER | 'max' '(' ( rule )* ')' 'default' NUMBER | 'min' '(' ( rule )* ')' 'default' NUMBER )
            int alt6=3;
            switch ( input.LA(1) ) {
            case 10:
                {
                alt6=1;
                }
                break;
            case 17:
                {
                alt6=2;
                }
                break;
            case 18:
                {
                alt6=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }

            switch (alt6) {
                case 1 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:32:4: '+' '(' ( rule )* ')' 'default' NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal13=(Token)match(input,10,FOLLOW_10_in_pol133); 
                    char_literal13_tree = 
                    (Object)adaptor.create(char_literal13)
                    ;
                    adaptor.addChild(root_0, char_literal13_tree);


                    char_literal14=(Token)match(input,8,FOLLOW_8_in_pol135); 
                    char_literal14_tree = 
                    (Object)adaptor.create(char_literal14)
                    ;
                    adaptor.addChild(root_0, char_literal14_tree);


                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:32:12: ( rule )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==8) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/Peal.g:32:13: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol138);
                    	    rule15=rule();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, rule15.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    char_literal16=(Token)match(input,9,FOLLOW_9_in_pol142); 
                    char_literal16_tree = 
                    (Object)adaptor.create(char_literal16)
                    ;
                    adaptor.addChild(root_0, char_literal16_tree);


                    string_literal17=(Token)match(input,15,FOLLOW_15_in_pol144); 
                    string_literal17_tree = 
                    (Object)adaptor.create(string_literal17)
                    ;
                    adaptor.addChild(root_0, string_literal17_tree);


                    NUMBER18=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol146); 
                    NUMBER18_tree = 
                    (Object)adaptor.create(NUMBER18)
                    ;
                    adaptor.addChild(root_0, NUMBER18_tree);


                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:33:4: 'max' '(' ( rule )* ')' 'default' NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    string_literal19=(Token)match(input,17,FOLLOW_17_in_pol151); 
                    string_literal19_tree = 
                    (Object)adaptor.create(string_literal19)
                    ;
                    adaptor.addChild(root_0, string_literal19_tree);


                    char_literal20=(Token)match(input,8,FOLLOW_8_in_pol153); 
                    char_literal20_tree = 
                    (Object)adaptor.create(char_literal20)
                    ;
                    adaptor.addChild(root_0, char_literal20_tree);


                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:33:14: ( rule )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==8) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/Peal.g:33:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol156);
                    	    rule21=rule();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, rule21.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    char_literal22=(Token)match(input,9,FOLLOW_9_in_pol160); 
                    char_literal22_tree = 
                    (Object)adaptor.create(char_literal22)
                    ;
                    adaptor.addChild(root_0, char_literal22_tree);


                    string_literal23=(Token)match(input,15,FOLLOW_15_in_pol162); 
                    string_literal23_tree = 
                    (Object)adaptor.create(string_literal23)
                    ;
                    adaptor.addChild(root_0, string_literal23_tree);


                    NUMBER24=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol164); 
                    NUMBER24_tree = 
                    (Object)adaptor.create(NUMBER24)
                    ;
                    adaptor.addChild(root_0, NUMBER24_tree);


                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:34:4: 'min' '(' ( rule )* ')' 'default' NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    string_literal25=(Token)match(input,18,FOLLOW_18_in_pol169); 
                    string_literal25_tree = 
                    (Object)adaptor.create(string_literal25)
                    ;
                    adaptor.addChild(root_0, string_literal25_tree);


                    char_literal26=(Token)match(input,8,FOLLOW_8_in_pol171); 
                    char_literal26_tree = 
                    (Object)adaptor.create(char_literal26)
                    ;
                    adaptor.addChild(root_0, char_literal26_tree);


                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:34:14: ( rule )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==8) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // /Users/jkuo/PealApp-lift/antlr/Peal.g:34:15: rule
                    	    {
                    	    pushFollow(FOLLOW_rule_in_pol174);
                    	    rule27=rule();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, rule27.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    char_literal28=(Token)match(input,9,FOLLOW_9_in_pol178); 
                    char_literal28_tree = 
                    (Object)adaptor.create(char_literal28)
                    ;
                    adaptor.addChild(root_0, char_literal28_tree);


                    string_literal29=(Token)match(input,15,FOLLOW_15_in_pol180); 
                    string_literal29_tree = 
                    (Object)adaptor.create(string_literal29)
                    ;
                    adaptor.addChild(root_0, string_literal29_tree);


                    NUMBER30=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_pol182); 
                    NUMBER30_tree = 
                    (Object)adaptor.create(NUMBER30)
                    ;
                    adaptor.addChild(root_0, NUMBER30_tree);


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
    // $ANTLR end "pol"


    public static class pSet_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pSet"
    // /Users/jkuo/PealApp-lift/antlr/Peal.g:37:1: pSet : ( pol | 'max' '(' pol ',' pol ')' | 'min' '(' pol ',' pol ')' );
    public final PealParser.pSet_return pSet() throws RecognitionException {
        PealParser.pSet_return retval = new PealParser.pSet_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token string_literal32=null;
        Token char_literal33=null;
        Token char_literal35=null;
        Token char_literal37=null;
        Token string_literal38=null;
        Token char_literal39=null;
        Token char_literal41=null;
        Token char_literal43=null;
        PealParser.pol_return pol31 =null;

        PealParser.pol_return pol34 =null;

        PealParser.pol_return pol36 =null;

        PealParser.pol_return pol40 =null;

        PealParser.pol_return pol42 =null;


        Object string_literal32_tree=null;
        Object char_literal33_tree=null;
        Object char_literal35_tree=null;
        Object char_literal37_tree=null;
        Object string_literal38_tree=null;
        Object char_literal39_tree=null;
        Object char_literal41_tree=null;
        Object char_literal43_tree=null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:37:9: ( pol | 'max' '(' pol ',' pol ')' | 'min' '(' pol ',' pol ')' )
            int alt7=3;
            switch ( input.LA(1) ) {
            case 10:
                {
                alt7=1;
                }
                break;
            case 17:
                {
                int LA7_2 = input.LA(2);

                if ( (LA7_2==8) ) {
                    int LA7_4 = input.LA(3);

                    if ( ((LA7_4 >= 8 && LA7_4 <= 9)) ) {
                        alt7=1;
                    }
                    else if ( (LA7_4==10||(LA7_4 >= 17 && LA7_4 <= 18)) ) {
                        alt7=2;
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
            case 18:
                {
                int LA7_3 = input.LA(2);

                if ( (LA7_3==8) ) {
                    int LA7_5 = input.LA(3);

                    if ( ((LA7_5 >= 8 && LA7_5 <= 9)) ) {
                        alt7=1;
                    }
                    else if ( (LA7_5==10||(LA7_5 >= 17 && LA7_5 <= 18)) ) {
                        alt7=3;
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
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:37:11: pol
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pol_in_pSet195);
                    pol31=pol();

                    state._fsp--;

                    adaptor.addChild(root_0, pol31.getTree());

                    }
                    break;
                case 2 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:38:4: 'max' '(' pol ',' pol ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    string_literal32=(Token)match(input,17,FOLLOW_17_in_pSet201); 
                    string_literal32_tree = 
                    (Object)adaptor.create(string_literal32)
                    ;
                    adaptor.addChild(root_0, string_literal32_tree);


                    char_literal33=(Token)match(input,8,FOLLOW_8_in_pSet203); 
                    char_literal33_tree = 
                    (Object)adaptor.create(char_literal33)
                    ;
                    adaptor.addChild(root_0, char_literal33_tree);


                    pushFollow(FOLLOW_pol_in_pSet205);
                    pol34=pol();

                    state._fsp--;

                    adaptor.addChild(root_0, pol34.getTree());

                    char_literal35=(Token)match(input,11,FOLLOW_11_in_pSet207); 
                    char_literal35_tree = 
                    (Object)adaptor.create(char_literal35)
                    ;
                    adaptor.addChild(root_0, char_literal35_tree);


                    pushFollow(FOLLOW_pol_in_pSet209);
                    pol36=pol();

                    state._fsp--;

                    adaptor.addChild(root_0, pol36.getTree());

                    char_literal37=(Token)match(input,9,FOLLOW_9_in_pSet211); 
                    char_literal37_tree = 
                    (Object)adaptor.create(char_literal37)
                    ;
                    adaptor.addChild(root_0, char_literal37_tree);


                    }
                    break;
                case 3 :
                    // /Users/jkuo/PealApp-lift/antlr/Peal.g:39:4: 'min' '(' pol ',' pol ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    string_literal38=(Token)match(input,18,FOLLOW_18_in_pSet216); 
                    string_literal38_tree = 
                    (Object)adaptor.create(string_literal38)
                    ;
                    adaptor.addChild(root_0, string_literal38_tree);


                    char_literal39=(Token)match(input,8,FOLLOW_8_in_pSet218); 
                    char_literal39_tree = 
                    (Object)adaptor.create(char_literal39)
                    ;
                    adaptor.addChild(root_0, char_literal39_tree);


                    pushFollow(FOLLOW_pol_in_pSet220);
                    pol40=pol();

                    state._fsp--;

                    adaptor.addChild(root_0, pol40.getTree());

                    char_literal41=(Token)match(input,11,FOLLOW_11_in_pSet222); 
                    char_literal41_tree = 
                    (Object)adaptor.create(char_literal41)
                    ;
                    adaptor.addChild(root_0, char_literal41_tree);


                    pushFollow(FOLLOW_pol_in_pSet224);
                    pol42=pol();

                    state._fsp--;

                    adaptor.addChild(root_0, pol42.getTree());

                    char_literal43=(Token)match(input,9,FOLLOW_9_in_pSet226); 
                    char_literal43_tree = 
                    (Object)adaptor.create(char_literal43)
                    ;
                    adaptor.addChild(root_0, char_literal43_tree);


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
    // $ANTLR end "pSet"

    // Delegated rules


 

    public static final BitSet FOLLOW_IDENT_in_pred52 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_pred54 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENT_in_pred58 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_pred66 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_rule81 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_rule83 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_pred_in_rule85 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_rule87 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_rule89 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_cond105 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_cond107 = new BitSet(new long[]{0x0000000000060400L});
    public static final BitSet FOLLOW_pSet_in_cond109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pSet_in_cond114 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_cond116 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_cond118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_pol133 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_pol135 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_rule_in_pol138 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_9_in_pol142 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_pol144 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_pol146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_pol151 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_pol153 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_rule_in_pol156 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_9_in_pol160 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_pol162 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_pol164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pol169 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_pol171 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_rule_in_pol174 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_9_in_pol178 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_pol180 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_pol182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pol_in_pSet195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_pSet201 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_pSet203 = new BitSet(new long[]{0x0000000000060400L});
    public static final BitSet FOLLOW_pol_in_pSet205 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_pSet207 = new BitSet(new long[]{0x0000000000060400L});
    public static final BitSet FOLLOW_pol_in_pSet209 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_pSet216 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_pSet218 = new BitSet(new long[]{0x0000000000060400L});
    public static final BitSet FOLLOW_pol_in_pSet220 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_pSet222 = new BitSet(new long[]{0x0000000000060400L});
    public static final BitSet FOLLOW_pol_in_pSet224 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pSet226 = new BitSet(new long[]{0x0000000000000002L});

}