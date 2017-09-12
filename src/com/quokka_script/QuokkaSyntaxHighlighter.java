package com.quokka_script;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.lexer.Lexer;
import com.intellij.psi.impl.search.MethodDeepestSuperSearcher;
import com.intellij.psi.tree.IElementType;
import com.quokka_script.psi.QuokkaArItem;
import org.jetbrains.annotations.NotNull;

import javax.xml.soap.Text;


/**
 * Created by sonyahon on 11/08/2017.
 */
public class QuokkaSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey KEYWORD = TextAttributesKey.createTextAttributesKey("Q_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD); //
    public static final TextAttributesKey COMPONENT = TextAttributesKey.createTextAttributesKey("Q_COMPONENT", DefaultLanguageHighlighterColors.CLASS_NAME); //
    public static final TextAttributesKey IDENTIFIER = TextAttributesKey.createTextAttributesKey("Q_NAME", DefaultLanguageHighlighterColors.IDENTIFIER); //
    public static final TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey("Q_STRING", DefaultLanguageHighlighterColors.STRING); //
    public static final TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey("Q_NUMBER", DefaultLanguageHighlighterColors.NUMBER); //
    public static final TextAttributesKey COMMENT = TextAttributesKey.createTextAttributesKey("Q_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT); //

    public static final TextAttributesKey META = TextAttributesKey.createTextAttributesKey("Q_META", DefaultLanguageHighlighterColors.METADATA); //
    public static final TextAttributesKey METHOD = TextAttributesKey.createTextAttributesKey("Q_METHOD", DefaultLanguageHighlighterColors.STATIC_METHOD); //
    public static final TextAttributesKey FUNCTION = TextAttributesKey.createTextAttributesKey("Q_FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION); //

    public static final TextAttributesKey REACTIVE = TextAttributesKey.createTextAttributesKey("Q_PIPE", DefaultLanguageHighlighterColors.BRACKETS);

    public static final TextAttributesKey ARG = TextAttributesKey.createTextAttributesKey("Q_ARG", DefaultLanguageHighlighterColors.PARAMETER);

    private TextAttributesKey[] KEYWORDS = new TextAttributesKey[]{KEYWORD};
    private TextAttributesKey[] COMPONENTS = new TextAttributesKey[]{COMPONENT};
    private TextAttributesKey[] IDENTIFIERS = new TextAttributesKey[]{IDENTIFIER};
    private TextAttributesKey[] STRINGS = new TextAttributesKey[]{STRING};
    private TextAttributesKey[] NUMBERS = new TextAttributesKey[]{NUMBER};
    private TextAttributesKey[] COMMENTS = new TextAttributesKey[]{COMMENT};
    private TextAttributesKey[] METAS = new TextAttributesKey[]{META};
    private TextAttributesKey[] METHODS = new TextAttributesKey[]{METHOD};
    private TextAttributesKey[] FUNCS = new TextAttributesKey[]{FUNCTION};
    private TextAttributesKey[] PIPES = new TextAttributesKey[]{REACTIVE};
    private TextAttributesKey[] ARGS = new TextAttributesKey[]{ARG};

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new QuokkaSyntaxLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType type) {
        if(type == QuokkaTypes.DEFINE || type == QuokkaTypes.KEYWORD) {
            return KEYWORDS;
        }
        else if(type == QuokkaTypes.COMPONENT) {
            return COMPONENTS;
        }
        else if(type == QuokkaTypes.IDENTIFIER) {
            return IDENTIFIERS;
        }
        else if(type == QuokkaTypes.VAL || type == QuokkaTypes.STRING) {
            return STRINGS;
        }
        else if(type == QuokkaTypes.DIGIT || type == QuokkaTypes.HEX_DIGIT) {
            return NUMBERS;
        }

        else if(type == QuokkaTypes.COMMENT_BEGIN || type == QuokkaTypes.COMMENT_END || type == QuokkaTypes.COMMENT_SYMBOL || type == QuokkaTypes.LINE_COMMENT || type == QuokkaTypes.COMMENT_END_HALF) {
            return COMMENTS;
        }
        else if(type == QuokkaTypes.META ) {
            return METAS;
        }
        else if(type == QuokkaTypes.META_INFO_BLOCK) {
            return STRINGS;
        }
        else if(type == QuokkaTypes.METHOD_NAME) {
            return METHODS;
        }
        else if(type == QuokkaTypes.REACTIVE_BRACKET_CLOSED || type == QuokkaTypes.REACTIVE_BRACKET_OPENED) {
            return PIPES;
        }
        else if(type == QuokkaTypes.ARGUMENT) {
            return ARGS;
        }
        else
            return EMPTY;
    }
}
