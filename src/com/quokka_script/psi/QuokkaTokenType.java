package com.quokka_script.psi;

import com.intellij.psi.tree.IElementType;
import com.quokka_script.QuokkaScript;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by sonyahon on 20/07/2017.
 */

public class QuokkaTokenType extends IElementType {
    public QuokkaTokenType(@NotNull @NonNls String debugName) {
        super(debugName, QuokkaScript.INSTANCE);
    }

    @Override
    public String toString() {
        String debugName = super.toString();
        switch (debugName) {
            case "INDENT":
                return "inner object";
            case "DEDENT":
                return "";
            case "END_LAST":
                return "";
            case "DEFINE":
                return "def";
            case "META":
                return "metadata";
            default:
                return debugName;
        }
    }
}
