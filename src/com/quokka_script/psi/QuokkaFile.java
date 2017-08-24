package com.quokka_script.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.quokka_script.QuokkaFileType;
import com.quokka_script.QuokkaScript;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class QuokkaFile extends PsiFileBase{
    public QuokkaFile(@NotNull FileViewProvider viewProvider) {

        super(viewProvider, QuokkaScript.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return QuokkaFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Quokka Script file";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }

}
