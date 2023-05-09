// Generated from .\LinkParser.g4 by ANTLR 4.12.0

package antlr;

import java.util.List;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class LinkParserParser extends Parser {

    public static final int
        COLON = 1, SLASH = 2, HTTP = 3, HTTPS = 4, GITHUB = 5, STACKOVERFLOW = 6, QUESTIONS = 7,
        NUM = 8, WS = 9, ANY = 10;
    public static final int
        RULE_link = 0, RULE_protocol = 1, RULE_server = 2, RULE_gitHub = 3, RULE_stackOverflow = 4,
        RULE_anyUsername = 5, RULE_anyRepository = 6, RULE_questionId = 7, RULE_anyQuestionName = 8;
    public static final String[] ruleNames = makeRuleNames();
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
        "\u0004\u0001\n7\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002" +
            "\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002" +
            "\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002" +
            "\b\u0007\b\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000" +
            "\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002" +
            "\u0003\u0002\u001e\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003" +
            "\u0001\u0003\u0003\u0003%\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004" +
            "\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004-\b\u0004\u0001\u0005" +
            "\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001" +
            "\b\u0001\b\u0000\u0000\t\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0000" +
            "\u0001\u0001\u0000\u0003\u00041\u0000\u0012\u0001\u0000\u0000\u0000\u0002" +
            "\u0018\u0001\u0000\u0000\u0000\u0004\u001d\u0001\u0000\u0000\u0000\u0006" +
            "\u001f\u0001\u0000\u0000\u0000\b&\u0001\u0000\u0000\u0000\n.\u0001\u0000" +
            "\u0000\u0000\f0\u0001\u0000\u0000\u0000\u000e2\u0001\u0000\u0000\u0000" +
            "\u00104\u0001\u0000\u0000\u0000\u0012\u0013\u0003\u0002\u0001\u0000\u0013" +
            "\u0014\u0005\u0001\u0000\u0000\u0014\u0015\u0005\u0002\u0000\u0000\u0015" +
            "\u0016\u0005\u0002\u0000\u0000\u0016\u0017\u0003\u0004\u0002\u0000\u0017" +
            "\u0001\u0001\u0000\u0000\u0000\u0018\u0019\u0007\u0000\u0000\u0000\u0019" +
            "\u0003\u0001\u0000\u0000\u0000\u001a\u001e\u0003\u0006\u0003\u0000\u001b" +
            "\u001e\u0003\b\u0004\u0000\u001c\u001e\u0005\n\u0000\u0000\u001d\u001a" +
            "\u0001\u0000\u0000\u0000\u001d\u001b\u0001\u0000\u0000\u0000\u001d\u001c" +
            "\u0001\u0000\u0000\u0000\u001e\u0005\u0001\u0000\u0000\u0000\u001f \u0005" +
            "\u0005\u0000\u0000 !\u0003\n\u0005\u0000!\"\u0005\u0002\u0000\u0000\"" +
            "$\u0003\f\u0006\u0000#%\u0005\u0002\u0000\u0000$#\u0001\u0000\u0000\u0000" +
            "$%\u0001\u0000\u0000\u0000%\u0007\u0001\u0000\u0000\u0000&\'\u0005\u0006" +
            "\u0000\u0000\'(\u0005\u0007\u0000\u0000()\u0003\u000e\u0007\u0000)*\u0005" +
            "\u0002\u0000\u0000*,\u0003\u0010\b\u0000+-\u0005\u0002\u0000\u0000,+\u0001" +
            "\u0000\u0000\u0000,-\u0001\u0000\u0000\u0000-\t\u0001\u0000\u0000\u0000" +
            "./\u0005\n\u0000\u0000/\u000b\u0001\u0000\u0000\u000001\u0005\n\u0000" +
            "\u00001\r\u0001\u0000\u0000\u000023\u0005\b\u0000\u00003\u000f\u0001\u0000" +
            "\u0000\u000045\u0005\n\u0000\u00005\u0011\u0001\u0000\u0000\u0000\u0003" +
            "\u001d$,";
    public static final ATN _ATN =
        new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
        new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = makeLiteralNames();
    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

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

    public LinkParserParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    private static String[] makeRuleNames() {
        return new String[]{
            "link", "protocol", "server", "gitHub", "stackOverflow", "anyUsername",
            "anyRepository", "questionId", "anyQuestionName"
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
    public ATN getATN() {
        return _ATN;
    }

    public final LinkContext link() throws RecognitionException {
        LinkContext _localctx = new LinkContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_link);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(18);
                protocol();
                setState(19);
                match(COLON);
                setState(20);
                match(SLASH);
                setState(21);
                match(SLASH);
                setState(22);
                server();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ProtocolContext protocol() throws RecognitionException {
        ProtocolContext _localctx = new ProtocolContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_protocol);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(24);
                _la = _input.LA(1);
                if (!(_la == HTTP || _la == HTTPS)) {
                    _errHandler.recoverInline(this);
                } else {
					if (_input.LA(1) == Token.EOF) {
						matchedEOF = true;
					}
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ServerContext server() throws RecognitionException {
        ServerContext _localctx = new ServerContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_server);
        try {
            setState(29);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case GITHUB:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(26);
                    gitHub();
                }
                break;
                case STACKOVERFLOW:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(27);
                    stackOverflow();
                }
                break;
                case ANY:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(28);
                    match(ANY);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final GitHubContext gitHub() throws RecognitionException {
        GitHubContext _localctx = new GitHubContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_gitHub);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(31);
                match(GITHUB);
                setState(32);
                anyUsername();
                setState(33);
                match(SLASH);
                setState(34);
                anyRepository();
                setState(36);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == SLASH) {
                    {
                        setState(35);
                        match(SLASH);
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final StackOverflowContext stackOverflow() throws RecognitionException {
        StackOverflowContext _localctx = new StackOverflowContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_stackOverflow);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(38);
                match(STACKOVERFLOW);
                setState(39);
                match(QUESTIONS);
                setState(40);
                questionId();
                setState(41);
                match(SLASH);
                setState(42);
                anyQuestionName();
                setState(44);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == SLASH) {
                    {
                        setState(43);
                        match(SLASH);
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final AnyUsernameContext anyUsername() throws RecognitionException {
        AnyUsernameContext _localctx = new AnyUsernameContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_anyUsername);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(46);
                match(ANY);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final AnyRepositoryContext anyRepository() throws RecognitionException {
        AnyRepositoryContext _localctx = new AnyRepositoryContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_anyRepository);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(48);
                match(ANY);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final QuestionIdContext questionId() throws RecognitionException {
        QuestionIdContext _localctx = new QuestionIdContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_questionId);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(50);
                match(NUM);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final AnyQuestionNameContext anyQuestionName() throws RecognitionException {
        AnyQuestionNameContext _localctx = new AnyQuestionNameContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_anyQuestionName);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(52);
                match(ANY);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class LinkContext extends ParserRuleContext {

        public LinkContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ProtocolContext protocol() {
            return getRuleContext(ProtocolContext.class, 0);
        }

        public TerminalNode COLON() {
            return getToken(LinkParserParser.COLON, 0);
        }

        public List<TerminalNode> SLASH() {
            return getTokens(LinkParserParser.SLASH);
        }

        public TerminalNode SLASH(int i) {
            return getToken(LinkParserParser.SLASH, i);
        }

        public ServerContext server() {
            return getRuleContext(ServerContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_link;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof LinkParserVisitor) {
				return ((LinkParserVisitor<? extends T>) visitor).visitLink(this);
			} else {
				return visitor.visitChildren(this);
			}
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ProtocolContext extends ParserRuleContext {

        public ProtocolContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode HTTPS() {
            return getToken(LinkParserParser.HTTPS, 0);
        }

        public TerminalNode HTTP() {
            return getToken(LinkParserParser.HTTP, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_protocol;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof LinkParserVisitor) {
				return ((LinkParserVisitor<? extends T>) visitor).visitProtocol(this);
			} else {
				return visitor.visitChildren(this);
			}
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ServerContext extends ParserRuleContext {

        public ServerContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public GitHubContext gitHub() {
            return getRuleContext(GitHubContext.class, 0);
        }

        public StackOverflowContext stackOverflow() {
            return getRuleContext(StackOverflowContext.class, 0);
        }

        public TerminalNode ANY() {
            return getToken(LinkParserParser.ANY, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_server;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof LinkParserVisitor) {
				return ((LinkParserVisitor<? extends T>) visitor).visitServer(this);
			} else {
				return visitor.visitChildren(this);
			}
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class GitHubContext extends ParserRuleContext {

        public GitHubContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode GITHUB() {
            return getToken(LinkParserParser.GITHUB, 0);
        }

        public AnyUsernameContext anyUsername() {
            return getRuleContext(AnyUsernameContext.class, 0);
        }

        public List<TerminalNode> SLASH() {
            return getTokens(LinkParserParser.SLASH);
        }

        public TerminalNode SLASH(int i) {
            return getToken(LinkParserParser.SLASH, i);
        }

        public AnyRepositoryContext anyRepository() {
            return getRuleContext(AnyRepositoryContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_gitHub;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof LinkParserVisitor) {
				return ((LinkParserVisitor<? extends T>) visitor).visitGitHub(this);
			} else {
				return visitor.visitChildren(this);
			}
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class StackOverflowContext extends ParserRuleContext {

        public StackOverflowContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STACKOVERFLOW() {
            return getToken(LinkParserParser.STACKOVERFLOW, 0);
        }

        public TerminalNode QUESTIONS() {
            return getToken(LinkParserParser.QUESTIONS, 0);
        }

        public QuestionIdContext questionId() {
            return getRuleContext(QuestionIdContext.class, 0);
        }

        public List<TerminalNode> SLASH() {
            return getTokens(LinkParserParser.SLASH);
        }

        public TerminalNode SLASH(int i) {
            return getToken(LinkParserParser.SLASH, i);
        }

        public AnyQuestionNameContext anyQuestionName() {
            return getRuleContext(AnyQuestionNameContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_stackOverflow;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof LinkParserVisitor) {
				return ((LinkParserVisitor<? extends T>) visitor).visitStackOverflow(this);
			} else {
				return visitor.visitChildren(this);
			}
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class AnyUsernameContext extends ParserRuleContext {

        public AnyUsernameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode ANY() {
            return getToken(LinkParserParser.ANY, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_anyUsername;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof LinkParserVisitor) {
				return ((LinkParserVisitor<? extends T>) visitor).visitAnyUsername(this);
			} else {
				return visitor.visitChildren(this);
			}
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class AnyRepositoryContext extends ParserRuleContext {

        public AnyRepositoryContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode ANY() {
            return getToken(LinkParserParser.ANY, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_anyRepository;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof LinkParserVisitor) {
				return ((LinkParserVisitor<? extends T>) visitor).visitAnyRepository(this);
			} else {
				return visitor.visitChildren(this);
			}
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class QuestionIdContext extends ParserRuleContext {

        public QuestionIdContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode NUM() {
            return getToken(LinkParserParser.NUM, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_questionId;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof LinkParserVisitor) {
				return ((LinkParserVisitor<? extends T>) visitor).visitQuestionId(this);
			} else {
				return visitor.visitChildren(this);
			}
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class AnyQuestionNameContext extends ParserRuleContext {

        public AnyQuestionNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode ANY() {
            return getToken(LinkParserParser.ANY, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_anyQuestionName;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof LinkParserVisitor) {
				return ((LinkParserVisitor<? extends T>) visitor).visitAnyQuestionName(this);
			} else {
				return visitor.visitChildren(this);
			}
        }
    }
}