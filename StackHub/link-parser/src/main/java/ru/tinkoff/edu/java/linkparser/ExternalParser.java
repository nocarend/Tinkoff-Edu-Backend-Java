package ru.tinkoff.edu.java.linkparser;

public class ExternalParser {
    private final String url;

    public ExternalParser(String url) {
        this.url = url;
    }

    public ParsedValue parse() {
        var map = new InternalParser(url).parseToMap();
        if (map.isEmpty()) {
            return null;
        }
        if (map.containsKey(GithubHelper.repositoryName) && map.containsKey(
                GithubHelper.username)) {
            return new Github(map.get(GithubHelper.username), map.get(GithubHelper.repositoryName));
        }
        return new StackOverflow(map.get(StackOverflowHelper.questionId));
    }

    private static final class GithubHelper {
        static final String repositoryName = "repositoryName";
        static final String username = "username";
    }

    private static final class StackOverflowHelper {
        static final String questionId = "questionId";
    }
}
