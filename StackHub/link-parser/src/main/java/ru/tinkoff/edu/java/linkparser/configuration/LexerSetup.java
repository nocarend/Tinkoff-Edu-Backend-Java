package ru.tinkoff.edu.java.linkparser.configuration;

import antlr.LinkParserLexer;
import org.antlr.v4.runtime.CharStreams;

public class LexerSetup {
    private LexerSetup(){
        throw new AssertionError();
    }
    public static LinkParserLexer get(String url) {
        return new LinkParserLexer(CharStreams.fromString(url));
    }
}
