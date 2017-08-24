package com.quokka_script;

import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;

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
		else if(settingsType == SettingsType.BLANK_LINES_SETTINGS) {
			consumer.showStandardOptions("KEEP_BLANK_LINES_IN_CODE");
		}
		else if(settingsType == SettingsType.INDENT_SETTINGS) {

		}
	}

	@NotNull
	@Override
	public Language getLanguage() {
		return QuokkaScript.INSTANCE;
	}

	@Override
	public String getCodeSample(@NotNull SettingsType settingsType) {
		return "def Page main\n"+
						" public Label l1: KPACUBO\n"+
						"  bottom: #afafaf";
	}
}
