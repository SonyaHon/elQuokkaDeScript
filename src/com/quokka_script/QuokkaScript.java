package com.quokka_script;

import com.intellij.lang.Language;

/**
 * Created by sonyahon on 20/07/2017.
 */

public class QuokkaScript extends Language {
    public static QuokkaScript INSTANCE = new QuokkaScript();
    public static QuokkaCustomSettings SETTINGS = new QuokkaCustomSettings();
    private QuokkaScript() {
        super("Quokka Script");
    }
}
