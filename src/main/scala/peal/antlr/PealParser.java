// $ANTLR 3.4 /Users/jkuo/PealApp-lift/antlr/Peal.g 2013-06-03 10:27:37

package peal.antlr;


import org.antlr.runtime.*;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PealParser extends Parser {
    public static final String[] tokenNames = new String[]{
            "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMPARE", "IDENT", "NUMBER", "WS", "'trust'"
    };

    public static final int EOF = -1;
    public static final int T__8 = 8;
    public static final int COMPARE = 4;
    public static final int IDENT = 5;
    public static final int NUMBER = 6;
    public static final int WS = 7;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[]{};
    }

    // delegators


    public PealParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }

    public PealParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() {
        return PealParser.tokenNames;
    }

    public String getGrammarFileName() {
        return "/Users/jkuo/PealApp-lift/antlr/Peal.g";
    }


    // $ANTLR start "pred"
    // /Users/jkuo/PealApp-lift/antlr/Peal.g:15:1: pred returns [Boolean e] : 'trust' id1= COMPARE id2= NUMBER ;
    public final Boolean pred() throws RecognitionException {
        Boolean e = null;


        Token id1 = null;
        Token id2 = null;

        try {
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:16:2: ( 'trust' id1= COMPARE id2= NUMBER )
            // /Users/jkuo/PealApp-lift/antlr/Peal.g:16:4: 'trust' id1= COMPARE id2= NUMBER
            {
                match(input, 8, FOLLOW_8_in_pred42);

                id1 = (Token) match(input, COMPARE, FOLLOW_COMPARE_in_pred46);

                id2 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_pred50);

            }

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        } finally {
            // do for sure before leaving
        }
        return e;
    }
    // $ANTLR end "pred"

    // Delegated rules


    public static final BitSet FOLLOW_8_in_pred42 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_COMPARE_in_pred46 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_pred50 = new BitSet(new long[]{0x0000000000000002L});

}