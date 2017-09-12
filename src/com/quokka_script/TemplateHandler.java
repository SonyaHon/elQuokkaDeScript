package com.quokka_script;

import com.intellij.codeInsight.template.Template;
import com.intellij.codeInsight.template.TemplateManager;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;

public class TemplateHandler extends CreateFileFromTemplateAction {
    public TemplateHandler() {
        super("QuokkaScript file","Creates a new QuokkaScript file", QuokkaIcon.FILE);
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory psiDirectory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle("New QuokkaScript file");
        builder.addKind("QuokkaScript file", QuokkaIcon.FILE, "QuokkaScript file");
    }

    @Override
    protected PsiFile createFile(String s, String s1, PsiDirectory psiDirectory) {
        return super.createFile(s, s1, psiDirectory);
    }

    @Override
    protected String getActionName(PsiDirectory psiDirectory, String s, String s1) {
        return "New QuokkaScript File";
    }


}
