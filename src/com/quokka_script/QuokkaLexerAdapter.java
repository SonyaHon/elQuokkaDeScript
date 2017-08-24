package com.quokka_script;
import com.intellij.lexer.FlexAdapter;

public class QuokkaLexerAdapter extends FlexAdapter{

    public QuokkaLexerAdapter() {
        super(new QuokkaLexer());
    }
}
