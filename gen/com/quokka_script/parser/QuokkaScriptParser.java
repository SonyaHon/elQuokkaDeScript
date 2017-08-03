// This is a generated file. Not intended for manual editing.
package com.quokka_script.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.quokka_script.QuokkaTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
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
    if (t == GLOBAL_OBJECT) {
      r = globalObject(b, 0);
    }
    else if (t == MAIN_OBJECT) {
      r = mainObject(b, 0);
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
  // ' '
  public static boolean globalObject(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "globalObject")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, GLOBAL_OBJECT, "<global object>");
    r = consumeToken(b, " ");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'def Page main'
  public static boolean mainObject(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mainObject")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MAIN_OBJECT, "<main object>");
    r = consumeToken(b, "def Page main");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // !<<eof>> globalObject | &mainObject
  static boolean rootFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rootFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = rootFile_0(b, l + 1);
    if (!r) r = rootFile_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // !<<eof>> globalObject
  private static boolean rootFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rootFile_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = rootFile_0_0(b, l + 1);
    r = r && globalObject(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // !<<eof>>
  private static boolean rootFile_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rootFile_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !eof(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &mainObject
  private static boolean rootFile_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rootFile_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = mainObject(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
