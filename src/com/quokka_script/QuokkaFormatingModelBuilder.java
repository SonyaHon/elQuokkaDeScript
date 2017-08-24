package com.quokka_script;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * Created by sonyahon on 23/08/2017.
 */
public class QuokkaFormatingModelBuilder implements FormattingModelBuilder {
	@NotNull
	@Override
	public FormattingModel createModel(PsiElement psiElement, CodeStyleSettings codeStyleSettings) {
		return FormattingModelProvider.createFormattingModelForPsiFile(psiElement.getContainingFile(),
						new QuokkaBlock(psiElement.getNode(), Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment(), createSpaceBuilder(codeStyleSettings)),
						codeStyleSettings);
	}

	private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
		return new SpacingBuilder(settings, QuokkaScript.INSTANCE).
						around(QuokkaTypes.COLON).spaceIf(settings.SPACE_AROUND_ASSIGNMENT_OPERATORS).before(QuokkaTypes.IDENTIFIER).none();
	}

	@Nullable
	@Override
	public TextRange getRangeAffectingIndent(PsiFile psiFile, int i, ASTNode astNode) {
		return null;
	}
}
