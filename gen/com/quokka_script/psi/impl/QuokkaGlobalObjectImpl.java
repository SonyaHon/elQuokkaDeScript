// This is a generated file. Not intended for manual editing.
package com.quokka_script.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.quokka_script.QuokkaTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.quokka_script.psi.*;

public class QuokkaGlobalObjectImpl extends ASTWrapperPsiElement implements QuokkaGlobalObject {

  public QuokkaGlobalObjectImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull QuokkaVisitor visitor) {
    visitor.visitGlobalObject(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof QuokkaVisitor) accept((QuokkaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<QuokkaComment> getCommentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, QuokkaComment.class);
  }

  @Override
  @NotNull
  public List<QuokkaMetaInn> getMetaInnList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, QuokkaMetaInn.class);
  }

  @Override
  @NotNull
  public List<QuokkaMethod> getMethodList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, QuokkaMethod.class);
  }

  @Override
  @NotNull
  public List<QuokkaQsObject> getQsObjectList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, QuokkaQsObject.class);
  }

}
