package com.quokka_script.psi;

import com.intellij.lang.Language;
import com.intellij.psi.tree.ILazyParseableElementType;

public class LazyFuncs extends ILazyParseableElementType{
    public LazyFuncs() {
        super("LAZY_FUNC", Language.ANY, true);
    }
}
