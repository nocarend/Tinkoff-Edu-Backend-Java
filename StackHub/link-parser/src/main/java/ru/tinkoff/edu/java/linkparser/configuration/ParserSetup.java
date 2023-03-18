package ru.tinkoff.edu.java.linkparser.configuration;

import antlr.LinkParserParser;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

public class ParserSetup {
    private ParserSetup() {
        throw new AssertionError();
    }

    public static LinkParserParser get(TokenStream tokenStream) {
        return new LinkParserParser(tokenStream);
    }
}
