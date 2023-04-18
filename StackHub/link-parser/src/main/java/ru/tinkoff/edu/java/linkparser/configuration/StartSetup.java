package ru.tinkoff.edu.java.linkparser.configuration;

import antlr.LinkParserParser;
import lombok.NonNull;

public class StartSetup {

    private StartSetup() {
        throw new AssertionError();
    }

    public static LinkParserParser.LinkContext get(@NonNull final LinkParserParser parser) {
        return parser.link();
    }
}
