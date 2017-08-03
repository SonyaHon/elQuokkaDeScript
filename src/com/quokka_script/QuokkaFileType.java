package com.quokka_script;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by sonyahon on 20/07/2017.
 */

public class QuokkaFileType extends LanguageFileType{
    public static final QuokkaFileType INSTANCE = new QuokkaFileType();

    private QuokkaFileType() {
        super(QuokkaScript.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "QuokkaScript file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "QuokkaScript file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "qs";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return QuokkaIcon.FILE;
    }

}
