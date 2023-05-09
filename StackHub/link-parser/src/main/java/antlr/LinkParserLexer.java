// Generated from .\LinkParser.g4 by ANTLR 4.12.0

package antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class LinkParserLexer extends Lexer {

    public static final int
        COLON = 1, SLASH = 2, HTTP = 3, HTTPS = 4, GITHUB = 5, STACKOVERFLOW = 6, QUESTIONS = 7,
        NUM = 8, WS = 9, ANY = 10;
    public static final String[] ruleNames = makeRuleNames();
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
        "\u0004\u0000\na\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001" +
            "\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004" +
            "\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007" +
            "\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0001\u0000" +
            "\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002" +
            "\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003" +
            "\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004" +
            "\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004" +
            "\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005" +
            "\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005" +
            "\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005" +
            "\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006" +
            "\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006" +
            "\u0001\u0006\u0001\u0006\u0001\u0007\u0004\u0007R\b\u0007\u000b\u0007" +
            "\f\u0007S\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0004\t\\\b" +
            "\t\u000b\t\f\t]\u0001\n\u0001\n\u0000\u0000\u000b\u0001\u0001\u0003\u0002" +
            "\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013" +
            "\n\u0015\u0000\u0001\u0000\u0003\u0002\u0000\t\t  \u0003\u000009AZaz&" +
            "\u0000--19\u0661\u0669\u06f1\u06f9\u07c1\u07c9\u0967\u096f\u09e7\u09ef" +
            "\u0a67\u0a6f\u0ae7\u0aef\u0b67\u0b6f\u0be7\u0bef\u0c67\u0c6f\u0ce7\u0cef" +
            "\u0d67\u0d6f\u0de7\u0def\u0e51\u0e59\u0ed1\u0ed9\u0f21\u0f29\u1041\u1049" +
            "\u1091\u1099\u17e1\u17e9\u1811\u1819\u1947\u194f\u19d1\u19d9\u1a81\u1a89" +
            "\u1a91\u1a99\u1b51\u1b59\u1bb1\u1bb9\u1c41\u1c49\u1c51\u1c59\u8000\ua621" +
            "\u8000\ua629\u8000\ua8d1\u8000\ua8d9\u8000\ua901\u8000\ua909\u8000\ua9d1" +
            "\u8000\ua9d9\u8000\ua9f1\u8000\ua9f9\u8000\uaa51\u8000\uaa59\u8000\uabf1" +
            "\u8000\uabf9\u8000\uff11\u8000\uff19b\u0000\u0001\u0001\u0000\u0000\u0000" +
            "\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000" +
            "\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000" +
            "\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f" +
            "\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013" +
            "\u0001\u0000\u0000\u0000\u0001\u0017\u0001\u0000\u0000\u0000\u0003\u0019" +
            "\u0001\u0000\u0000\u0000\u0005\u001b\u0001\u0000\u0000\u0000\u0007 \u0001" +
            "\u0000\u0000\u0000\t&\u0001\u0000\u0000\u0000\u000b2\u0001\u0000\u0000" +
            "\u0000\rE\u0001\u0000\u0000\u0000\u000fQ\u0001\u0000\u0000\u0000\u0011" +
            "U\u0001\u0000\u0000\u0000\u0013[\u0001\u0000\u0000\u0000\u0015_\u0001" +
            "\u0000\u0000\u0000\u0017\u0018\u0005:\u0000\u0000\u0018\u0002\u0001\u0000" +
            "\u0000\u0000\u0019\u001a\u0005/\u0000\u0000\u001a\u0004\u0001\u0000\u0000" +
            "\u0000\u001b\u001c\u0005h\u0000\u0000\u001c\u001d\u0005t\u0000\u0000\u001d" +
            "\u001e\u0005t\u0000\u0000\u001e\u001f\u0005p\u0000\u0000\u001f\u0006\u0001" +
            "\u0000\u0000\u0000 !\u0005h\u0000\u0000!\"\u0005t\u0000\u0000\"#\u0005" +
            "t\u0000\u0000#$\u0005p\u0000\u0000$%\u0005s\u0000\u0000%\b\u0001\u0000" +
            "\u0000\u0000&\'\u0005g\u0000\u0000\'(\u0005i\u0000\u0000()\u0005t\u0000" +
            "\u0000)*\u0005h\u0000\u0000*+\u0005u\u0000\u0000+,\u0005b\u0000\u0000" +
            ",-\u0005.\u0000\u0000-.\u0005c\u0000\u0000./\u0005o\u0000\u0000/0\u0005" +
            "m\u0000\u000001\u0005/\u0000\u00001\n\u0001\u0000\u0000\u000023\u0005" +
            "s\u0000\u000034\u0005t\u0000\u000045\u0005a\u0000\u000056\u0005c\u0000" +
            "\u000067\u0005k\u0000\u000078\u0005o\u0000\u000089\u0005v\u0000\u0000" +
            "9:\u0005e\u0000\u0000:;\u0005r\u0000\u0000;<\u0005f\u0000\u0000<=\u0005" +
            "l\u0000\u0000=>\u0005o\u0000\u0000>?\u0005w\u0000\u0000?@\u0005.\u0000" +
            "\u0000@A\u0005c\u0000\u0000AB\u0005o\u0000\u0000BC\u0005m\u0000\u0000" +
            "CD\u0005/\u0000\u0000D\f\u0001\u0000\u0000\u0000EF\u0005q\u0000\u0000" +
            "FG\u0005u\u0000\u0000GH\u0005e\u0000\u0000HI\u0005s\u0000\u0000IJ\u0005" +
            "t\u0000\u0000JK\u0005i\u0000\u0000KL\u0005o\u0000\u0000LM\u0005n\u0000" +
            "\u0000MN\u0005s\u0000\u0000NO\u0005/\u0000\u0000O\u000e\u0001\u0000\u0000" +
            "\u0000PR\u000209\u0000QP\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000" +
            "SQ\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000T\u0010\u0001\u0000" +
            "\u0000\u0000UV\u0007\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000WX\u0006" +
            "\b\u0000\u0000X\u0012\u0001\u0000\u0000\u0000Y\\\u0007\u0001\u0000\u0000" +
            "Z\\\u0003\u0015\n\u0000[Y\u0001\u0000\u0000\u0000[Z\u0001\u0000\u0000" +
            "\u0000\\]\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000]^\u0001\u0000" +
            "\u0000\u0000^\u0014\u0001\u0000\u0000\u0000_`\u0007\u0002\u0000\u0000" +
            "`\u0016\u0001\u0000\u0000\u0000\u0004\u0000S[]\u0001\u0006\u0000\u0000";
    public static final ATN _ATN =
        new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
        new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = makeLiteralNames();
    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);
    public static String[] channelNames = {
        "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
    };
    public static String[] modeNames = {
        "DEFAULT_MODE"
    };

    static {
        RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION);
    }

    static {
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }

    public LinkParserLexer(CharStream input) {
        super(input);
        _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    private static String[] makeRuleNames() {
        return new String[]{
            "COLON", "SLASH", "HTTP", "HTTPS", "GITHUB", "STACKOVERFLOW", "QUESTIONS",
            "NUM", "WS", "ANY", "UNICODE_CLASS_ND_NoZeros"
        };
    }

    private static String[] makeLiteralNames() {
        return new String[]{
            null, "':'", "'/'", "'http'", "'https'", "'github.com/'", "'stackoverflow.com/'",
            "'questions/'"
        };
    }

    private static String[] makeSymbolicNames() {
        return new String[]{
            null, "COLON", "SLASH", "HTTP", "HTTPS", "GITHUB", "STACKOVERFLOW", "QUESTIONS",
            "NUM", "WS", "ANY"
        };
    }

    @Override
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    @Override
    public String getGrammarFileName() {
        return "LinkParser.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public String[] getChannelNames() {
        return channelNames;
    }

    @Override
    public String[] getModeNames() {
        return modeNames;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }
}