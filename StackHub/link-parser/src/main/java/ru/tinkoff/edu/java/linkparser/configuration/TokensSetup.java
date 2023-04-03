package ru.tinkoff.edu.java.linkparser.configuration;

import antlr.LinkParserLexer;
import lombok.NonNull;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

public class TokensSetup {

    private TokensSetup() {
        throw new AssertionError();
    }

    public static TokenStream get(@NonNull final LinkParserLexer lexer) {
        return new CommonTokenStream(lexer);
    }
}
