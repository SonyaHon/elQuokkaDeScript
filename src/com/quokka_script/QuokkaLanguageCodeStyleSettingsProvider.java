package com.quokka_script;

import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.application.options.SmartIndentOptionsEditor;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by sonyahon on 16/08/2017.
 */
public class QuokkaLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {
	@Override
	public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
		if(settingsType == SettingsType.SPACING_SETTINGS) {
			consumer.showStandardOptions("SPACE_AROUND_ASSIGNMENT_OPERATORS");
			consumer.renameStandardOption("SPACE_AROUND_ASSIGNMENT_OPERATORS", "Separator");
		}
	}

	@Nullable
	@Override
	public IndentOptionsEditor getIndentOptionsEditor() {
		return new SmartIndentOptionsEditor() {
			@Override
			public boolean isModified(CodeStyleSettings settings, CommonCodeStyleSettings.IndentOptions options) {
				QuokkaScript.SETTINGS.setTabSize(options.INDENT_SIZE	);
				return super.isModified(settings, options);
			}
		};
	}

	@NotNull
	@Override
	public Language getLanguage() {
		return QuokkaScript.INSTANCE;
	}

	@Override
	public String getCodeSample(@NotNull SettingsType settingsType) {
		return "def Page main\n"+
				"	public Label l1: KPACUBO\n"+
				" 		bottom: #afafaf";
	}
}
