package ru.tinkoff.edu.java.linkparser;

import lombok.Getter;

public record ExternalParser(@Getter String url) {
    private static final String GITHUB_REPOSITORY_NAME="repositoryName";
    private static final String GITHUB_USERNAME = "username";
    private static final String STACKOVERFLOW_QUESTION_ID = "questionId";

    public ParsedValue parse() {
        var map = new InternalParser(url).parseToMap();
        if (map.isEmpty()) {
            return null;
        }
        if (map.containsKey(GITHUB_REPOSITORY_NAME) && map.containsKey(
                GITHUB_USERNAME)) {
            return new Github(map.get(GITHUB_USERNAME),
                              map.get(GITHUB_REPOSITORY_NAME));
        }
        return new StackOverflow(map.get(STACKOVERFLOW_QUESTION_ID));
    }

}
