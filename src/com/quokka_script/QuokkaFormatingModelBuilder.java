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
				.before(QuokkaTypes.GLOBAL_OBJECT).none()
				.before(QuokkaTypes.META_INFO).none()
				.after(QuokkaTypes.GLOBAL_OBJECT).lineBreakOrForceSpace(true, false)
				.afterInside(QuokkaTypes.IDENTIFIER, QuokkaTypes.GLOBAL_OBJECT).lineBreakOrForceSpace(true, false)
				.beforeInside(QuokkaTypes.QS_OBJECT, QuokkaTypes.GLOBAL_OBJECT).parentDependentLFSpacing(1, 1, true, 1)
				.afterInside(QuokkaTypes.QS_OBJECT, QuokkaTypes.GLOBAL_OBJECT).lineBreakOrForceSpace(true, false)
				.beforeInside(QuokkaTypes.QS_OBJECT, QuokkaTypes.QS_OBJECT).parentDependentLFSpacing(1, 1, true ,1)
				.afterInside(QuokkaTypes.QS_OBJECT, QuokkaTypes.QS_OBJECT).lineBreakInCode()
				.after(QuokkaTypes.COLON).spacing(0, 1, 0, false, 0)
				.before(QuokkaTypes.DEDENT).none()
				.before(QuokkaTypes.INDENT).none()
				.after(QuokkaTypes.DEDENT).none()
				.after(QuokkaTypes.INDENT).none();
	}

	@Nullable
	@Override
	public TextRange getRangeAffectingIndent(PsiFile psiFile, int i, ASTNode astNode) {
		return null;
	}
}
