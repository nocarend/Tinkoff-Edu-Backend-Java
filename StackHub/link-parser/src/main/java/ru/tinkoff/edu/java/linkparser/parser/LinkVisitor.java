package ru.tinkoff.edu.java.linkparser.parser;

import antlr.LinkParserBaseVisitor;
import antlr.LinkParserParser;
import java.util.HashMap;
import java.util.Map;

public class LinkVisitor extends LinkParserBaseVisitor<Map<String, String>> {
    private final Map<String, String> contents = new HashMap<>();

    @Override
    public Map<String, String> visitLink(LinkParserParser.LinkContext ctx) {
        super.visitLink(ctx);
        return contents;
    }

    @Override
    public Map<String, String> visitGitHub(LinkParserParser.GitHubContext ctx) {
        super.visitGitHub(ctx);
        LinkParserParser.AnyRepositoryContext repositoryName = ctx.anyRepository();
        LinkParserParser.AnyUsernameContext username = ctx.anyUsername();
        if (repositoryName != null && username != null) {
            contents.put("repositoryName", repositoryName.getText());
            contents.put("username", username.getText());
        }
        return null;
    }

    @Override
    public Map<String, String> visitStackOverflow(LinkParserParser.StackOverflowContext ctx) {
        super.visitStackOverflow(ctx);
        LinkParserParser.QuestionIdContext questionId = ctx.questionId();
        if (questionId != null) {
            contents.put("questionId", questionId.getText());
        }
        return null;
    }
}
