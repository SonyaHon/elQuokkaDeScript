// This is a generated file. Not intended for manual editing.
package com.quokka_script;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.quokka_script.psi.QuokkaElementType;
import com.quokka_script.psi.QuokkaTokenType;
import com.quokka_script.psi.impl.*;

public interface QuokkaTypes {

  IElementType AR_ITEM = new QuokkaElementType("AR_ITEM");
  IElementType COMMENT = new QuokkaElementType("COMMENT");
  IElementType FULL_COMMENT = new QuokkaElementType("FULL_COMMENT");
  IElementType FUNCTION = new QuokkaElementType("FUNCTION");
  IElementType GLOBAL_OBJECT = new QuokkaElementType("GLOBAL_OBJECT");
  IElementType JSON_VALUE = new QuokkaElementType("JSON_VALUE");
  IElementType JS_ARRAY = new QuokkaElementType("JS_ARRAY");
  IElementType JS_OBJECT = new QuokkaElementType("JS_OBJECT");
  IElementType META_INFO = new QuokkaElementType("META_INFO");
  IElementType METHOD = new QuokkaElementType("METHOD");
  IElementType OB_ITEM = new QuokkaElementType("OB_ITEM");
  IElementType OB_NAME = new QuokkaElementType("OB_NAME");
  IElementType QS_OBJECT = new QuokkaElementType("QS_OBJECT");
  IElementType REACTIVE_LINK = new QuokkaElementType("REACTIVE_LINK");
  IElementType VALUE = new QuokkaElementType("VALUE");

  IElementType ARGUMENT = new QuokkaTokenType("ARGUMENT");
  IElementType ARROW_RIGHT = new QuokkaTokenType("ARROW_RIGHT");
  IElementType BRACKET1C = new QuokkaTokenType("BRACKET1C");
  IElementType BRACKET1O = new QuokkaTokenType("BRACKET1O");
  IElementType BRACKET2C = new QuokkaTokenType("BRACKET2C");
  IElementType BRACKET2O = new QuokkaTokenType("BRACKET2O");
  IElementType COLON = new QuokkaTokenType("COLON");
  IElementType COMMA = new QuokkaTokenType("COMMA");
  IElementType COMMENT_BEGIN = new QuokkaTokenType("COMMENT_BEGIN");
  IElementType COMMENT_END = new QuokkaTokenType("COMMENT_END");
  IElementType COMMENT_SYMBOL = new QuokkaTokenType("COMMENT_SYMBOL");
  IElementType COMPONENT = new QuokkaTokenType("COMPONENT");
  IElementType DEDENT = new QuokkaTokenType("DEDENT");
  IElementType DEFINE = new QuokkaTokenType("DEFINE");
  IElementType DIGIT = new QuokkaTokenType("DIGIT");
  IElementType FULL_FUNCTION = new QuokkaTokenType("FULL_FUNCTION");
  IElementType FUNC_LINE = new QuokkaTokenType("FUNC_LINE");
  IElementType HEX_DIGIT = new QuokkaTokenType("HEX_DIGIT");
  IElementType IDENTIFIER = new QuokkaTokenType("IDENTIFIER");
  IElementType INDENT = new QuokkaTokenType("INDENT");
  IElementType KEYWORD = new QuokkaTokenType("KEYWORD");
  IElementType LINE_COMMENT = new QuokkaTokenType("LINE_COMMENT");
  IElementType META = new QuokkaTokenType("META");
  IElementType METHOD_NAME = new QuokkaTokenType("METHOD_NAME");
  IElementType REACTIVE_BRACKET_CLOSED = new QuokkaTokenType("REACTIVE_BRACKET_CLOSED");
  IElementType REACTIVE_BRACKET_OPENED = new QuokkaTokenType("REACTIVE_BRACKET_OPENED");
  IElementType ROUND_BRACE_CLOSED = new QuokkaTokenType("ROUND_BRACE_CLOSED");
  IElementType ROUND_BRACE_OPENED = new QuokkaTokenType("ROUND_BRACE_OPENED");
  IElementType STRING = new QuokkaTokenType("STRING");
  IElementType VAL = new QuokkaTokenType("VAL");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == AR_ITEM) {
        return new QuokkaArItemImpl(node);
      }
      else if (type == COMMENT) {
        return new QuokkaCommentImpl(node);
      }
      else if (type == FULL_COMMENT) {
        return new QuokkaFullCommentImpl(node);
      }
      else if (type == FUNCTION) {
        return new QuokkaFunctionImpl(node);
      }
      else if (type == GLOBAL_OBJECT) {
        return new QuokkaGlobalObjectImpl(node);
      }
      else if (type == JSON_VALUE) {
        return new QuokkaJsonValueImpl(node);
      }
      else if (type == JS_ARRAY) {
        return new QuokkaJsArrayImpl(node);
      }
      else if (type == JS_OBJECT) {
        return new QuokkaJsObjectImpl(node);
      }
      else if (type == META_INFO) {
        return new QuokkaMetaInfoImpl(node);
      }
      else if (type == METHOD) {
        return new QuokkaMethodImpl(node);
      }
      else if (type == OB_ITEM) {
        return new QuokkaObItemImpl(node);
      }
      else if (type == OB_NAME) {
        return new QuokkaObNameImpl(node);
      }
      else if (type == QS_OBJECT) {
        return new QuokkaQsObjectImpl(node);
      }
      else if (type == REACTIVE_LINK) {
        return new QuokkaReactiveLinkImpl(node);
      }
      else if (type == VALUE) {
        return new QuokkaValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
