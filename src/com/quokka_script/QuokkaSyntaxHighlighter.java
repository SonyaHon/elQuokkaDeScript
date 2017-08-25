package com.quokka_script;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.lexer.Lexer;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;


/**
 * Created by sonyahon on 11/08/2017.
 */
public class QuokkaSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey KEYWORD = TextAttributesKey.createTextAttributesKey("Q_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey("Q_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey("Q_NUM", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey CLASS = TextAttributesKey.createTextAttributesKey("Q_COMP", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey SEPARATOR = TextAttributesKey.createTextAttributesKey("Q_SEPP", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey BRACE = TextAttributesKey.createTextAttributesKey("Q_BRACE", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey METHOD = TextAttributesKey.createTextAttributesKey("Q_METHOD", DefaultLanguageHighlighterColors.STATIC_METHOD);
    public static final TextAttributesKey META = TextAttributesKey.createTextAttributesKey("Q_META", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey REACTIVE = TextAttributesKey.createTextAttributesKey("Q_META", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey IDENTIFIER = TextAttributesKey.createTextAttributesKey("Q_META", DefaultLanguageHighlighterColors.IDENTIFIER);

    private static final TextAttributesKey[] KEYWORDS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] STRINGS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] NUMBERS = new TextAttributesKey[]{NUMBER};
    private static final TextAttributesKey[] CLASSES = new TextAttributesKey[]{CLASS};
    private static final TextAttributesKey[] SEPARATORS = new TextAttributesKey[]{SEPARATOR};
    private static final TextAttributesKey[] BRACES = new TextAttributesKey[]{BRACE};
    private static final TextAttributesKey[] METHODS = new TextAttributesKey[]{METHOD};
    private static final TextAttributesKey[] METAS = new TextAttributesKey[]{META};
    private static final TextAttributesKey[] REACTIVES = new TextAttributesKey[]{REACTIVE};
    private static final TextAttributesKey[] IDENTIFIERS = new TextAttributesKey[]{IDENTIFIER};

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new QuokkaLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType type) {

        if(type == QuokkaTypes.KEYWORD || type == QuokkaTypes.DEFINE) return KEYWORDS;
        else if(type == QuokkaTypes.VAL || type == QuokkaTypes.STRING) return STRINGS;
        else if(type == QuokkaTypes.DIGIT || type == QuokkaTypes.HEX_DIGIT) return NUMBERS;
        else if(type == QuokkaTypes.COMPONENT) return CLASSES;
        else if(type == QuokkaTypes.COLON || type == QuokkaTypes.ARROW_RIGHT) return SEPARATORS;
        else if(type == QuokkaTypes.METHOD_NAME) return METHODS;
        else if(type == QuokkaTypes.META) return METAS;
        else if(type == QuokkaTypes.BRACKET1C || type == QuokkaTypes.BRACKET1O ||
                type == QuokkaTypes.BRACKET2C || type == QuokkaTypes.BRACKET2O ||
                type == QuokkaTypes.ROUND_BRACE_CLOSED || type == QuokkaTypes.ROUND_BRACE_OPENED ||
                type == QuokkaTypes.COMMA) return BRACES;
        else if(type == QuokkaTypes.REACTIVE_BRACKET_CLOSED || type == QuokkaTypes.REACTIVE_BRACKET_OPENED) return REACTIVES;
        else if(type == QuokkaTypes.IDENTIFIER) return IDENTIFIERS;
        else return EMPTY;
    }
}
