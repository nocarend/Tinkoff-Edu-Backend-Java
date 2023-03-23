package ru.tinkoff.edu.java.linkparser.configuration;

import antlr.LinkParserParser;

public class StartSetup {
    private StartSetup() {
        throw new AssertionError();
    }

    public static LinkParserParser.LinkContext get(final LinkParserParser parser) {
        return parser.link();
    }
}
