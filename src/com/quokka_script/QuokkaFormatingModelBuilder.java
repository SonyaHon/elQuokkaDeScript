package com.quokka_script;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.sun.org.apache.xpath.internal.operations.Quo;
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
		return new SpacingBuilder(settings, QuokkaScript.INSTANCE)
				.before(QuokkaTypes.DEFINE).none()
				.before(QuokkaTypes.META_INFO).none()
				.between(QuokkaTypes.DEFINE, QuokkaTypes.COMPONENT).spaces(1)
				.between(QuokkaTypes.COMPONENT, QuokkaTypes.IDENTIFIER).spaces(1)
				.between(QuokkaTypes.COMPONENT, QuokkaTypes.COLON).none()
				.between(QuokkaTypes.COLON, QuokkaTypes.VALUE).spacing(0, 10, 1, false, 0);
	}

	@Nullable
	@Override
	public TextRange getRangeAffectingIndent(PsiFile psiFile, int i, ASTNode astNode) {
		return null;
	}
}
