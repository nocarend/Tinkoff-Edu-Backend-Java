package ru.tinkoff.edu.java.linkparser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.linkparser.url.Github;
import ru.tinkoff.edu.java.linkparser.url.StackOverflow;

class ExternalParserTest {

    @Test
    void parseCorrectGithub() {
        final String url = "https://github.com/sanyarnd/tinkoff-java-course-2022/";
        final Github data = (Github) new ExternalParser(url).parse();
        Assertions.assertAll(
            () -> Assertions.assertEquals("sanyarnd", data.username()),
            () -> Assertions.assertEquals("tinkoff-java-course-2022", data.repositoryName())
        );
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

    @Test
    void parseWrongGitHub() {
        final String url = "https://github.com/pysathq";
        Assertions.assertNull(new ExternalParser(url).parse());
    }

    @Test
    void parseTeacherCorrectGitHub() {
        final String url = "https://github.com/dmitry-irtegov/NSU-Python2023";
        final Github data = (Github) new ExternalParser(url).parse();
        Assertions.assertAll(
            () -> Assertions.assertEquals("dmitry-irtegov", data.username()),
            () -> Assertions.assertEquals("NSU-Python2023", data.repositoryName())
        );
    }
}