package com.quokka_script;

import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.openapi.options.Configurable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by sonyahon on 16/08/2017.
 */
public class QuokkaCodeStyleSettingsProvider extends CodeStyleSettingsProvider {
	@Nullable
	@Override
	public CustomCodeStyleSettings createCustomSettings(CodeStyleSettings settings) {
		return new QuokkaCodeStyleSettings(settings);
	}

	@Nullable
	@Override
	public String getConfigurableDisplayName() {
		return "Quokka Script";
	}

	@NotNull
	@Override
	public Configurable createSettingsPage(CodeStyleSettings codeStyleSettings, CodeStyleSettings codeStyleSettings1) {
		return new CodeStyleAbstractConfigurable(codeStyleSettings, codeStyleSettings1, "Quokka Script") {
			@Override
			protected CodeStyleAbstractPanel createPanel(CodeStyleSettings codeStyleSettings) {
				return new QuokkaCodeStyleMainPanel(getCurrentSettings(), codeStyleSettings);
			}

			@Nullable
			@Override
			public String getHelpTopic() {
				return null;
			}
		};
	}

	private static class QuokkaCodeStyleMainPanel extends TabbedLanguageCodeStylePanel {
		private QuokkaCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
			super(QuokkaScript.INSTANCE, currentSettings, settings);
		}


	}
}
