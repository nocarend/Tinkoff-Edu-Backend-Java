// Generated from .\LinkParser.g4 by ANTLR 4.12.0

package antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced by
 * {@link LinkParserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for operations with no return
 *            type.
 */
public interface LinkParserVisitor<T> extends ParseTreeVisitor<T> {

    /**
     * Visit a parse tree produced by {@link LinkParserParser#link}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLink(LinkParserParser.LinkContext ctx);

    /**
     * Visit a parse tree produced by {@link LinkParserParser#protocol}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitProtocol(LinkParserParser.ProtocolContext ctx);

    /**
     * Visit a parse tree produced by {@link LinkParserParser#server}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitServer(LinkParserParser.ServerContext ctx);

    /**
     * Visit a parse tree produced by {@link LinkParserParser#gitHub}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitGitHub(LinkParserParser.GitHubContext ctx);

    /**
     * Visit a parse tree produced by {@link LinkParserParser#stackOverflow}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitStackOverflow(LinkParserParser.StackOverflowContext ctx);

    /**
     * Visit a parse tree produced by {@link LinkParserParser#anyUsername}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAnyUsername(LinkParserParser.AnyUsernameContext ctx);

    /**
     * Visit a parse tree produced by {@link LinkParserParser#anyRepository}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAnyRepository(LinkParserParser.AnyRepositoryContext ctx);

    /**
     * Visit a parse tree produced by {@link LinkParserParser#questionId}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitQuestionId(LinkParserParser.QuestionIdContext ctx);

    /**
     * Visit a parse tree produced by {@link LinkParserParser#anyQuestionName}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAnyQuestionName(LinkParserParser.AnyQuestionNameContext ctx);
}