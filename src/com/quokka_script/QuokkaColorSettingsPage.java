package com.quokka_script;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

/**
 * Created by sonyahon on 15/08/2017.
 */
public class QuokkaColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[] {
            new AttributesDescriptor("Keywords", QuokkaSyntaxHighlighter.KEYWORD),
            new AttributesDescriptor("Strings and Values", QuokkaSyntaxHighlighter.STRING),
            new AttributesDescriptor("Methods", QuokkaSyntaxHighlighter.METHOD),
            new AttributesDescriptor("Metadata", QuokkaSyntaxHighlighter.META),
           /* new AttributesDescriptor("Numbers", QuokkaSyntaxHighlighter.NUMBER),
            new AttributesDescriptor("Separators", QuokkaSyntaxHighlighter.SEPARATOR),
            new AttributesDescriptor("Reactive link", QuokkaSyntaxHighlighter.REACTIVE),
            new AttributesDescriptor("Identifiers", QuokkaSyntaxHighlighter.IDENTIFIER)*/
    };


    @Nullable
    @Override
    public Icon getIcon() {
        return QuokkaIcon.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new QuokkaSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return  "// Comment\n"+
                "@metadata: \"Эстонский флаг\"\n"+
                "def Page main\n"+
                "   background: #333\n"+
                "   public Label s1: \"Hello World!\" \n" +
                "   Button b1: {{ s1 }}\n"+
                "       .click: () -> \n" +
                "           console.log(\"Hello World!\")";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Quokka Script";
    }
}
