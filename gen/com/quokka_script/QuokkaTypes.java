// This is a generated file. Not intended for manual editing.
package com.quokka_script;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.quokka_script.psi.QuokkaElementType;
import com.quokka_script.psi.QuokkaTokenType;
import com.quokka_script.psi.impl.*;

public interface QuokkaTypes {

  IElementType GLOBAL_OBJECT = new QuokkaElementType("GLOBAL_OBJECT");
  IElementType MAIN_OBJECT = new QuokkaElementType("MAIN_OBJECT");

  IElementType PROPNAME = new QuokkaTokenType("propName");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == GLOBAL_OBJECT) {
        return new QuokkaGlobalObjectImpl(node);
      }
      else if (type == MAIN_OBJECT) {
        return new QuokkaMainObjectImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
