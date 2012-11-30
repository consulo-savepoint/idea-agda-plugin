package org.jetbrains.agda.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.tree.IFileElementType;
import org.jetbrains.agda.AgdaFileImpl;
import org.jetbrains.agda.AgdaLanguage;
import org.jetbrains.agda.psi.AgdaPsiBuilder;
import org.jetbrains.annotations.NotNull;

public class AgdaParserDefinition implements ParserDefinition {
    IFileElementType AGDA_FILE = new IFileElementType(AgdaLanguage.INSTANCE);


    @NotNull
    public Lexer createLexer(Project project) {
        return new AgdaLexer();
    }

    public IFileElementType getFileNodeType() {
        return AGDA_FILE;
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return AgdaTokenTypes.WHITESPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return AgdaTokenTypes.COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new AgdaParser();
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new AgdaFileImpl(viewProvider);
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        return AgdaPsiBuilder.build(node);
    }
}
