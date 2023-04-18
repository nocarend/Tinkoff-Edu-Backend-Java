package ru.tinkoff.edu.java.linkparser.configuration;

import antlr.LinkParserParser;
import lombok.NonNull;
import org.antlr.v4.runtime.TokenStream;

public class ParserSetup {

    private ParserSetup() {
        throw new AssertionError();
    }

    public static LinkParserParser get(@NonNull final TokenStream tokenStream) {
        return new LinkParserParser(tokenStream);
    }
}
