package com.quokka_script;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.FlexLexer;
import org.jetbrains.annotations.NotNull;

public class QuokkaSyntaxLexerAdapter extends FlexAdapter{
    public QuokkaSyntaxLexerAdapter() {
        super(new QuokkaSyntaxLexer());
    }
}
