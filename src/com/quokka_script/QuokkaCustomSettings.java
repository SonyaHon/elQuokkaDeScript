package com.quokka_script;

import org.jetbrains.annotations.Contract;

public class QuokkaCustomSettings {
    private static int tab_size = 4;

    @Contract(pure = true)
    public static int getTabSize() {
        return tab_size;
    }

    public static void setTabSize(int ts) {
        tab_size = ts;
    }
}
