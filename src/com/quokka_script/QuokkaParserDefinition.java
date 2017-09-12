package com.quokka_script;

import com.intellij.ide.script.IDE;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.quokka_script.parser.QuokkaScriptParser;
import com.quokka_script.psi.QuokkaArItem;
import com.quokka_script.psi.QuokkaFile;
import jdk.nashorn.internal.parser.Token;
import org.jetbrains.annotations.NotNull;

public class QuokkaParserDefinition implements ParserDefinition{
    // Tokens here...
    public static final TokenSet WhiteSpaces = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet Comment = TokenSet.create(QuokkaTypes.LINE_COMMENT);

    public static final TokenSet EMTY_SET = TokenSet.create(QuokkaTypes.DEDENT, QuokkaTypes.INDENT, QuokkaTypes.AR_ITEM,
            QuokkaTypes.COMMENT, QuokkaTypes.FULL_COMMENT, QuokkaTypes.FUNCTION, QuokkaTypes.GLOBAL_OBJECT, QuokkaTypes.JSON_VALUE,
            QuokkaTypes.JS_ARRAY, QuokkaTypes.JS_OBJECT, QuokkaTypes.META_INFO, QuokkaTypes.METHOD, QuokkaTypes.OB_NAME, QuokkaTypes.OB_ITEM,
            QuokkaTypes.QS_OBJECT, QuokkaTypes.REACTIVE_LINK, QuokkaTypes.VALUE);
    public static final TokenSet KEYWORDS = TokenSet.create(QuokkaTypes.DEFINE, QuokkaTypes.KEYWORD);
    public static final TokenSet STRINGS = TokenSet.create(QuokkaTypes.STRING, QuokkaTypes.VAL);
    public static final TokenSet OPERATORS = TokenSet.create(QuokkaTypes.COLON, QuokkaTypes.ARROW_RIGHT);
    public static final TokenSet ARGUMENTS = TokenSet.create(QuokkaTypes.ARGUMENT);
    public static final TokenSet METADATA = TokenSet.create(QuokkaTypes.META);
    public static final TokenSet METHODS = TokenSet.create(QuokkaTypes.METHOD_NAME);
    public static final TokenSet NUMBERS = TokenSet.create(QuokkaTypes.DIGIT, QuokkaTypes.HEX_DIGIT);
    public static final TokenSet IDENTIFIERS = TokenSet.create(QuokkaTypes.IDENTIFIER);
    public static final TokenSet COMPONENTS = TokenSet.create(QuokkaTypes.COMPONENT);
    public static final TokenSet FUNCTION_BODYS = TokenSet.create(QuokkaTypes.FUNC_LINE, QuokkaTypes.FULL_FUNCTION);
    public static final TokenSet REACTIVE_OPERATOR = TokenSet.create(QuokkaTypes.REACTIVE_BRACKET_CLOSED, QuokkaTypes.REACTIVE_BRACKET_OPENED);
    public static final TokenSet BRACKETS = TokenSet.create(QuokkaTypes.BRACKET1C, QuokkaTypes.BRACKET1O, QuokkaTypes.BRACKET2C,
            QuokkaTypes.BRACKET2O, QuokkaTypes.COMMA, QuokkaTypes.ROUND_BRACE_CLOSED, QuokkaTypes.ROUND_BRACE_OPENED);


    public static final IFileElementType FILE = new IFileElementType(QuokkaScript.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new QuokkaLexerAdapter();
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WhiteSpaces;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return Comment;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new QuokkaScriptParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new QuokkaFile(viewProvider);
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        PsiElement elem = QuokkaTypes.Factory.createElement(node);
        return elem;
    }
}
