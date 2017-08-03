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

public class QuokkaMainObjectImpl extends ASTWrapperPsiElement implements QuokkaMainObject {

  public QuokkaMainObjectImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull QuokkaVisitor visitor) {
    visitor.visitMainObject(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof QuokkaVisitor) accept((QuokkaVisitor)visitor);
    else super.accept(visitor);
  }

}
