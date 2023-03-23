package ru.tinkoff.edu.java.linkparser;

import java.util.Map;
import ru.tinkoff.edu.java.linkparser.configuration.LexerSetup;
import ru.tinkoff.edu.java.linkparser.configuration.ParserSetup;
import ru.tinkoff.edu.java.linkparser.configuration.StartSetup;
import ru.tinkoff.edu.java.linkparser.configuration.TokensSetup;
import ru.tinkoff.edu.java.linkparser.configuration.VisitorSetup;

public record InternalParser(String url) {

    public Map<String, String> parseToMap() {
        final var lexer = LexerSetup.get(url);
        final var tokens = TokensSetup.get(lexer);
        final var parser = ParserSetup.get(tokens);
        final var tree = StartSetup.get(parser);
        final var visitor = VisitorSetup.get();
        return visitor.visit(tree);
    }
}