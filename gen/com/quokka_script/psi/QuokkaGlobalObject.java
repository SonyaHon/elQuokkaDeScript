// This is a generated file. Not intended for manual editing.
package com.quokka_script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface QuokkaGlobalObject extends PsiElement {

  @NotNull
  List<QuokkaComment> getCommentList();

  @NotNull
  List<QuokkaMetaInn> getMetaInnList();

  @NotNull
  List<QuokkaMethod> getMethodList();

  @NotNull
  List<QuokkaQsObject> getQsObjectList();

}
