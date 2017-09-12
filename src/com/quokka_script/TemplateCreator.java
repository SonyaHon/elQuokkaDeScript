package com.quokka_script;

import com.intellij.codeInsight.template.Template;
import com.intellij.codeInsight.template.TemplateManager;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.project.ProjectManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class TemplateCreator implements ApplicationComponent {
    public TemplateCreator() {
    }

    @Override
    public void initComponent() {
        // TODO: insert component initialization logic here
        addFileTemplate("QuokkaScript file", "qs");
    }

    private void addFileTemplate(@NonNls String name, @NonNls String ext) {
        FileTemplate template = FileTemplateManager.getInstance(ProjectManager.getInstance().getDefaultProject()).getTemplate(name);
        if(template == null) {
            template = FileTemplateManager.getInstance(ProjectManager.getInstance().getDefaultProject()).addTemplate(name, ext);
            template.setText("def Page main\n");
        }
    }

    @Override
    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "TemplateCreator";
    }
}
