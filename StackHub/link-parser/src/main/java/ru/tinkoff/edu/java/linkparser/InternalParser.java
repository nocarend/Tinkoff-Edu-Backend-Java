package ru.tinkoff.edu.java.linkparser;

import java.util.Map;
import ru.tinkoff.edu.java.linkparser.configuration.LexerSetup;
import ru.tinkoff.edu.java.linkparser.configuration.ParserSetup;
import ru.tinkoff.edu.java.linkparser.configuration.StartSetup;
import ru.tinkoff.edu.java.linkparser.configuration.TokensSetup;
import ru.tinkoff.edu.java.linkparser.configuration.VisitorSetup;

public class InternalParser {
    private final String url;

    public InternalParser(String url) {
        this.url = url;
    }

    public Map<String, String> parseToMap() {
        var lexer = LexerSetup.get(url);
        var tokens = TokensSetup.get(lexer);
        var parser = ParserSetup.get(tokens);
        var tree = StartSetup.get(parser);
        var visitor = VisitorSetup.get();
        return visitor.visit(tree);
    }


}