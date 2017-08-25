// This is a generated file. Not intended for manual editing.
package com.quokka_script.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.quokka_script.QuokkaTypes.*;
import static com.quokka_script.parser.QuokkaScriptParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class QuokkaScriptParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == COMMENT) {
      r = COMMENT(b, 0);
    }
    else if (t == FULL_COMMENT) {
      r = FULL_COMMENT(b, 0);
    }
    else if (t == FUNCTION) {
      r = FUNCTION(b, 0);
    }
    else if (t == GLOBAL_OBJECT) {
      r = GLOBAL_OBJECT(b, 0);
    }
    else if (t == META_INFO) {
      r = META_INFO(b, 0);
    }
    else if (t == METHOD) {
      r = METHOD(b, 0);
    }
    else if (t == QS_OBJECT) {
      r = QS_OBJECT(b, 0);
    }
    else if (t == REACTIVE_LINK) {
      r = REACTIVE_LINK(b, 0);
    }
    else if (t == VALUE) {
      r = VALUE(b, 0);
    }
    else if (t == AR_ITEM) {
      r = arItem(b, 0);
    }
    else if (t == JS_ARRAY) {
      r = jsArray(b, 0);
    }
    else if (t == JS_OBJECT) {
      r = jsObject(b, 0);
    }
    else if (t == JSON_VALUE) {
      r = jsonValue(b, 0);
    }
    else if (t == OB_ITEM) {
      r = obItem(b, 0);
    }
    else if (t == OB_NAME) {
      r = obName(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return rootFile(b, l + 1);
  }

  /* ********************************************************** */
  // (LINE_COMMENT close_rule?) | (FULL_COMMENT)
  public static boolean COMMENT(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "COMMENT")) return false;
    if (!nextTokenIs(b, "<comment>", COMMENT_BEGIN, LINE_COMMENT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMMENT, "<comment>");
    r = COMMENT_0(b, l + 1);
    if (!r) r = COMMENT_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LINE_COMMENT close_rule?
  private static boolean COMMENT_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "COMMENT_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LINE_COMMENT);
    r = r && COMMENT_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // close_rule?
  private static boolean COMMENT_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "COMMENT_0_1")) return false;
    close_rule(b, l + 1);
    return true;
  }

  // (FULL_COMMENT)
  private static boolean COMMENT_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "COMMENT_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FULL_COMMENT(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COMMENT_BEGIN COMMENT_SYMBOL* COMMENT_END close_rule?
  public static boolean FULL_COMMENT(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FULL_COMMENT")) return false;
    if (!nextTokenIs(b, COMMENT_BEGIN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMENT_BEGIN);
    r = r && FULL_COMMENT_1(b, l + 1);
    r = r && consumeToken(b, COMMENT_END);
    r = r && FULL_COMMENT_3(b, l + 1);
    exit_section_(b, m, FULL_COMMENT, r);
    return r;
  }

  // COMMENT_SYMBOL*
  private static boolean FULL_COMMENT_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FULL_COMMENT_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, COMMENT_SYMBOL)) break;
      if (!empty_element_parsed_guard_(b, "FULL_COMMENT_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // close_rule?
  private static boolean FULL_COMMENT_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FULL_COMMENT_3")) return false;
    close_rule(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // FULL_FUNCTION | (FUNC_LINE? [INDENT FUNC_LINE+])
  public static boolean FUNCTION(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FUNCTION")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION, "<function>");
    r = consumeToken(b, FULL_FUNCTION);
    if (!r) r = FUNCTION_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // FUNC_LINE? [INDENT FUNC_LINE+]
  private static boolean FUNCTION_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FUNCTION_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FUNCTION_1_0(b, l + 1);
    r = r && FUNCTION_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // FUNC_LINE?
  private static boolean FUNCTION_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FUNCTION_1_0")) return false;
    consumeToken(b, FUNC_LINE);
    return true;
  }

  // [INDENT FUNC_LINE+]
  private static boolean FUNCTION_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FUNCTION_1_1")) return false;
    FUNCTION_1_1_0(b, l + 1);
    return true;
  }

  // INDENT FUNC_LINE+
  private static boolean FUNCTION_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FUNCTION_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && FUNCTION_1_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // FUNC_LINE+
  private static boolean FUNCTION_1_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FUNCTION_1_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FUNC_LINE);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, FUNC_LINE)) break;
      if (!empty_element_parsed_guard_(b, "FUNCTION_1_1_0_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DEFINE COMPONENT IDENTIFIER [COMMENT+] [INDENT INN*]  close_rule?
  public static boolean GLOBAL_OBJECT(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GLOBAL_OBJECT")) return false;
    if (!nextTokenIs(b, DEFINE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DEFINE, COMPONENT, IDENTIFIER);
    r = r && GLOBAL_OBJECT_3(b, l + 1);
    r = r && GLOBAL_OBJECT_4(b, l + 1);
    r = r && GLOBAL_OBJECT_5(b, l + 1);
    exit_section_(b, m, GLOBAL_OBJECT, r);
    return r;
  }

  // [COMMENT+]
  private static boolean GLOBAL_OBJECT_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GLOBAL_OBJECT_3")) return false;
    GLOBAL_OBJECT_3_0(b, l + 1);
    return true;
  }

  // COMMENT+
  private static boolean GLOBAL_OBJECT_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GLOBAL_OBJECT_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = COMMENT(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!COMMENT(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "GLOBAL_OBJECT_3_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // [INDENT INN*]
  private static boolean GLOBAL_OBJECT_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GLOBAL_OBJECT_4")) return false;
    GLOBAL_OBJECT_4_0(b, l + 1);
    return true;
  }

  // INDENT INN*
  private static boolean GLOBAL_OBJECT_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GLOBAL_OBJECT_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && GLOBAL_OBJECT_4_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INN*
  private static boolean GLOBAL_OBJECT_4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GLOBAL_OBJECT_4_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!INN(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "GLOBAL_OBJECT_4_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // close_rule?
  private static boolean GLOBAL_OBJECT_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GLOBAL_OBJECT_5")) return false;
    close_rule(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // QS_OBJECT | METHOD | COMMENT
  static boolean INN(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "INN")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = QS_OBJECT(b, l + 1);
    if (!r) r = METHOD(b, l + 1);
    if (!r) r = COMMENT(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // META COLON VAL? [INDENT VAL*] close_rule?
  public static boolean META_INFO(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "META_INFO")) return false;
    if (!nextTokenIs(b, META)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, META, COLON);
    r = r && META_INFO_2(b, l + 1);
    r = r && META_INFO_3(b, l + 1);
    r = r && META_INFO_4(b, l + 1);
    exit_section_(b, m, META_INFO, r);
    return r;
  }

  // VAL?
  private static boolean META_INFO_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "META_INFO_2")) return false;
    consumeToken(b, VAL);
    return true;
  }

  // [INDENT VAL*]
  private static boolean META_INFO_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "META_INFO_3")) return false;
    META_INFO_3_0(b, l + 1);
    return true;
  }

  // INDENT VAL*
  private static boolean META_INFO_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "META_INFO_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && META_INFO_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // VAL*
  private static boolean META_INFO_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "META_INFO_3_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, VAL)) break;
      if (!empty_element_parsed_guard_(b, "META_INFO_3_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // close_rule?
  private static boolean META_INFO_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "META_INFO_4")) return false;
    close_rule(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // METHOD_NAME COLON ROUND_BRACE_OPENED (ARGUMENT (COMMA ARGUMENT)*)? ROUND_BRACE_CLOSED ARROW_RIGHT FUNCTION close_rule?
  public static boolean METHOD(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "METHOD")) return false;
    if (!nextTokenIs(b, METHOD_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, METHOD_NAME, COLON, ROUND_BRACE_OPENED);
    r = r && METHOD_3(b, l + 1);
    r = r && consumeTokens(b, 0, ROUND_BRACE_CLOSED, ARROW_RIGHT);
    r = r && FUNCTION(b, l + 1);
    r = r && METHOD_7(b, l + 1);
    exit_section_(b, m, METHOD, r);
    return r;
  }

  // (ARGUMENT (COMMA ARGUMENT)*)?
  private static boolean METHOD_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "METHOD_3")) return false;
    METHOD_3_0(b, l + 1);
    return true;
  }

  // ARGUMENT (COMMA ARGUMENT)*
  private static boolean METHOD_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "METHOD_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ARGUMENT);
    r = r && METHOD_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA ARGUMENT)*
  private static boolean METHOD_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "METHOD_3_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!METHOD_3_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "METHOD_3_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA ARGUMENT
  private static boolean METHOD_3_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "METHOD_3_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, ARGUMENT);
    exit_section_(b, m, null, r);
    return r;
  }

  // close_rule?
  private static boolean METHOD_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "METHOD_7")) return false;
    close_rule(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // KEYWORD? COMPONENT [IDENTIFIER] [COLON VALUE?] [INDENT INN*] close_rule?
  public static boolean QS_OBJECT(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QS_OBJECT")) return false;
    if (!nextTokenIs(b, "<qs object>", COMPONENT, KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, QS_OBJECT, "<qs object>");
    r = QS_OBJECT_0(b, l + 1);
    r = r && consumeToken(b, COMPONENT);
    r = r && QS_OBJECT_2(b, l + 1);
    r = r && QS_OBJECT_3(b, l + 1);
    r = r && QS_OBJECT_4(b, l + 1);
    r = r && QS_OBJECT_5(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // KEYWORD?
  private static boolean QS_OBJECT_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QS_OBJECT_0")) return false;
    consumeToken(b, KEYWORD);
    return true;
  }

  // [IDENTIFIER]
  private static boolean QS_OBJECT_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QS_OBJECT_2")) return false;
    consumeToken(b, IDENTIFIER);
    return true;
  }

  // [COLON VALUE?]
  private static boolean QS_OBJECT_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QS_OBJECT_3")) return false;
    QS_OBJECT_3_0(b, l + 1);
    return true;
  }

  // COLON VALUE?
  private static boolean QS_OBJECT_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QS_OBJECT_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && QS_OBJECT_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // VALUE?
  private static boolean QS_OBJECT_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QS_OBJECT_3_0_1")) return false;
    VALUE(b, l + 1);
    return true;
  }

  // [INDENT INN*]
  private static boolean QS_OBJECT_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QS_OBJECT_4")) return false;
    QS_OBJECT_4_0(b, l + 1);
    return true;
  }

  // INDENT INN*
  private static boolean QS_OBJECT_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QS_OBJECT_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && QS_OBJECT_4_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INN*
  private static boolean QS_OBJECT_4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QS_OBJECT_4_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!INN(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "QS_OBJECT_4_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // close_rule?
  private static boolean QS_OBJECT_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "QS_OBJECT_5")) return false;
    close_rule(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // REACTIVE_BRACKET_OPENED IDENTIFIER* REACTIVE_BRACKET_CLOSED
  public static boolean REACTIVE_LINK(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "REACTIVE_LINK")) return false;
    if (!nextTokenIs(b, REACTIVE_BRACKET_OPENED)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, REACTIVE_BRACKET_OPENED);
    r = r && REACTIVE_LINK_1(b, l + 1);
    r = r && consumeToken(b, REACTIVE_BRACKET_CLOSED);
    exit_section_(b, m, REACTIVE_LINK, r);
    return r;
  }

  // IDENTIFIER*
  private static boolean REACTIVE_LINK_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "REACTIVE_LINK_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, IDENTIFIER)) break;
      if (!empty_element_parsed_guard_(b, "REACTIVE_LINK_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // VAL | STRING | json | REACTIVE_LINK | HEX_DIGIT
  public static boolean VALUE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VALUE")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUE, "<value>");
    r = consumeToken(b, VAL);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = json(b, l + 1);
    if (!r) r = REACTIVE_LINK(b, l + 1);
    if (!r) r = consumeToken(b, HEX_DIGIT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // json | jsonValue
  public static boolean arItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arItem")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, AR_ITEM, "<ar item>");
    r = json(b, l + 1);
    if (!r) r = jsonValue(b, l + 1);
    exit_section_(b, l, m, r, false, jsonRecoverRule_parser_);
    return r;
  }

  /* ********************************************************** */
  // DEDENT | <<eof>>
  static boolean close_rule(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "close_rule")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DEDENT);
    if (!r) r = eof(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // BRACKET1O [!BRACKET1C arItem (!BRACKET1C COMMA arItem) *] BRACKET1C
  public static boolean jsArray(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsArray")) return false;
    if (!nextTokenIs(b, BRACKET1O)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JS_ARRAY, null);
    r = consumeToken(b, BRACKET1O);
    p = r; // pin = 1
    r = r && report_error_(b, jsArray_1(b, l + 1));
    r = p && consumeToken(b, BRACKET1C) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [!BRACKET1C arItem (!BRACKET1C COMMA arItem) *]
  private static boolean jsArray_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsArray_1")) return false;
    jsArray_1_0(b, l + 1);
    return true;
  }

  // !BRACKET1C arItem (!BRACKET1C COMMA arItem) *
  private static boolean jsArray_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsArray_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = jsArray_1_0_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, arItem(b, l + 1));
    r = p && jsArray_1_0_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // !BRACKET1C
  private static boolean jsArray_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsArray_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, BRACKET1C);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (!BRACKET1C COMMA arItem) *
  private static boolean jsArray_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsArray_1_0_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!jsArray_1_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "jsArray_1_0_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // !BRACKET1C COMMA arItem
  private static boolean jsArray_1_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsArray_1_0_2_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = jsArray_1_0_2_0_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, consumeToken(b, COMMA));
    r = p && arItem(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // !BRACKET1C
  private static boolean jsArray_1_0_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsArray_1_0_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, BRACKET1C);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // BRACKET2O [!BRACKET2C obItem (!BRACKET2C COMMA? obItem) *] BRACKET2C
  public static boolean jsObject(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsObject")) return false;
    if (!nextTokenIs(b, BRACKET2O)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JS_OBJECT, null);
    r = consumeToken(b, BRACKET2O);
    p = r; // pin = 1
    r = r && report_error_(b, jsObject_1(b, l + 1));
    r = p && consumeToken(b, BRACKET2C) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [!BRACKET2C obItem (!BRACKET2C COMMA? obItem) *]
  private static boolean jsObject_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsObject_1")) return false;
    jsObject_1_0(b, l + 1);
    return true;
  }

  // !BRACKET2C obItem (!BRACKET2C COMMA? obItem) *
  private static boolean jsObject_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsObject_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = jsObject_1_0_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, obItem(b, l + 1));
    r = p && jsObject_1_0_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // !BRACKET2C
  private static boolean jsObject_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsObject_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, BRACKET2C);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (!BRACKET2C COMMA? obItem) *
  private static boolean jsObject_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsObject_1_0_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!jsObject_1_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "jsObject_1_0_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // !BRACKET2C COMMA? obItem
  private static boolean jsObject_1_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsObject_1_0_2_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = jsObject_1_0_2_0_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, jsObject_1_0_2_0_1(b, l + 1));
    r = p && obItem(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // !BRACKET2C
  private static boolean jsObject_1_0_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsObject_1_0_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, BRACKET2C);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // COMMA?
  private static boolean jsObject_1_0_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsObject_1_0_2_0_1")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // jsArray | jsObject
  static boolean json(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "json")) return false;
    if (!nextTokenIs(b, "", BRACKET1O, BRACKET2O)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = jsArray(b, l + 1);
    if (!r) r = jsObject(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !(COMMA | BRACKET1C | BRACKET2C | BRACKET1O | BRACKET2O)
  static boolean jsonRecoverRule(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonRecoverRule")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !jsonRecoverRule_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // COMMA | BRACKET1C | BRACKET2C | BRACKET1O | BRACKET2O
  private static boolean jsonRecoverRule_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonRecoverRule_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    if (!r) r = consumeToken(b, BRACKET1C);
    if (!r) r = consumeToken(b, BRACKET2C);
    if (!r) r = consumeToken(b, BRACKET1O);
    if (!r) r = consumeToken(b, BRACKET2O);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DIGIT | STRING | json | REACTIVE_LINK
  public static boolean jsonValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JSON_VALUE, "<json value>");
    r = consumeToken(b, DIGIT);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = json(b, l + 1);
    if (!r) r = REACTIVE_LINK(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // obName COLON jsonValue
  public static boolean obItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "obItem")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, OB_ITEM, "<ob item>");
    r = obName(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, consumeToken(b, COLON));
    r = p && jsonValue(b, l + 1) && r;
    exit_section_(b, l, m, r, p, jsonRecoverRule_parser_);
    return r || p;
  }

  /* ********************************************************** */
  // STRING | IDENTIFIER
  public static boolean obName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "obName")) return false;
    if (!nextTokenIs(b, "<ob name>", IDENTIFIER, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OB_NAME, "<ob name>");
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (GLOBAL_OBJECT | META_INFO | COMMENT)*
  static boolean rootFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rootFile")) return false;
    int c = current_position_(b);
    while (true) {
      if (!rootFile_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "rootFile", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // GLOBAL_OBJECT | META_INFO | COMMENT
  private static boolean rootFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rootFile_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = GLOBAL_OBJECT(b, l + 1);
    if (!r) r = META_INFO(b, l + 1);
    if (!r) r = COMMENT(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  final static Parser jsonRecoverRule_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return jsonRecoverRule(b, l + 1);
    }
  };
}
