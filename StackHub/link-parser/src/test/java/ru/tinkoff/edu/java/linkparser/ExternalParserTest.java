package ru.tinkoff.edu.java.linkparser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExternalParserTest {

    @Test
    void parseCorrectGithub() {
        final String url = "https://github.com/sanyarnd/tinkoff-java-course-2022/";
        final Github data = (Github) new ExternalParser(url).parse();
        Assertions.assertEquals("sanyarnd", data.username());
        Assertions.assertEquals("tinkoff-java-course-2022", data.repositoryName());
    }

    @Test
    void parseCorrectStackoverflow() {
        final String url = "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c";
        Assertions.assertEquals("1642028", ((StackOverflow) new ExternalParser(
                url).parse()).questionId());

    }

    @Test
    void parseWrongStackoverflow() {
        final String url = "https://stackoverflow.com/search?q=unsupported%20link";
        Assertions.assertNull(new ExternalParser(url).parse());
    }
}