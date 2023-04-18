package ru.tinkoff.edu.java.linkparser.configuration;

import antlr.LinkParserLexer;
import lombok.NonNull;
import org.antlr.v4.runtime.CharStreams;

public class LexerSetup {

    private LexerSetup() {
        throw new AssertionError();
    }

    public static LinkParserLexer get(@NonNull final String url) {
        return new LinkParserLexer(CharStreams.fromString(url));
    }
}
