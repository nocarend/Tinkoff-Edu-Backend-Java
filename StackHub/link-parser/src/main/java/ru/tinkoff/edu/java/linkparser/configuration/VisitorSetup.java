package ru.tinkoff.edu.java.linkparser.configuration;

import ru.tinkoff.edu.java.linkparser.parser.LinkVisitor;

public class VisitorSetup {
    private VisitorSetup() {
        throw new AssertionError();
    }

    public static LinkVisitor get() {
        return new LinkVisitor();
    }
}
