package com.quokka_script;

import com.intellij.lexer.FlexAdapter;
import java.util.regex.Pattern;

public class QuokkaSyntaxLexerAdapter extends FlexAdapter{
    public QuokkaSyntaxLexerAdapter() {
        super(new QuokkaSyntaxLexer());
    }
}
