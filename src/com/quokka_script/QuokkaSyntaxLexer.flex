package com.quokka_script;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.quokka_script.psi.QuokkaTokenType;
import java.util.Stack;

%%

%class QuokkaSyntaxLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

%{
    public QuokkaSyntaxLexer() {
        this((java.io.Reader)null);
    }

    Stack<Integer> escapedDedents = new Stack<>();
    Stack<Integer> wrappers = new Stack<Integer>() {{push(0);}};
    public int tab_size = 4;
    private int current_line_indent = 0;

    boolean wasLastTokenComponent = false;
    boolean wasLastTokenMeta = false;
    boolean wasLastTokenMethod = false;

    boolean isOpenBrackets = false;
    int numberToClose = 0;
    int openedBrackets1 = 0;
    int openedBrackets2 = 0;
    boolean reactiveLinkInJSON = false;

    int openedFuncBrackets = 0;

    boolean firstTouch = false;
    int indent_to_close = 0;

    boolean argumentsTime = false;
%}

whitespace = [ \t]
digit = [0-9]+(\.[0-9]*)?
hex_digit = #[0-9a-fA-F]{1} | #[0-9a-fA-F]{3} | #[0-9a-fA-F]{6}
identifier = [a-zA-Z][a-zA-Z0-9_]*
components = [a-zA-Z][a-zA-Z\.]+
method = \.[a-z]?[a-zA-Z]*
colon = ":"
toFuncSeparator = :[ ]*\(([a-zA-Z0-9]+)?\)[ ]*->
comment = "//"[^\r\n]*
metadata = @[a-zA-Z]+
value = [\p{L}0-9].*
string = '[^']*'|\"[^\"]*\"

prop_name = [a-z][a-zA-Z]+:

%state normal
%state returnDedents

%state w8ForValue

%state comment
%state string_filling
%state stringFillingExtra
%state stringFillingExtra2
%state json_filling
%state reactive_link_filling
%state meta_filling
%state method_filling
%state method_function_filling_with_brace
%state method_function_filling_no_brace

%state indentsEOFeascape

%%

<YYINITIAL> {
    {whitespace}                { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
    {whitespace}+               { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
    \n                          { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
    "def"                       { yybegin(YYINITIAL); return QuokkaTypes.KEYWORD; }
    "public"                    { yybegin(YYINITIAL); return QuokkaTypes.KEYWORD; }
    "pub"                       { yybegin(YYINITIAL); return QuokkaTypes.KEYWORD; }
    "private"                   { yybegin(YYINITIAL); return QuokkaTypes.KEYWORD; }

    ":"                         { yybegin(w8ForValue); return QuokkaTypes.COLON; }
    "->"                        { yybegin(YYINITIAL); return QuokkaTypes.ARROW_RIGHT; }
    "{"                         { yybegin(YYINITIAL); return QuokkaTypes.BRACKET1C; }
    "}"                         { yybegin(YYINITIAL); return QuokkaTypes.BRACKET1C; }
    "["                         { yybegin(YYINITIAL); return QuokkaTypes.BRACKET1C; }
    "]"                         { yybegin(YYINITIAL); return QuokkaTypes.BRACKET1C; }
    "("                         { yybegin(YYINITIAL); return QuokkaTypes.BRACKET1C; }
    ")"                         { yybegin(YYINITIAL); return QuokkaTypes.BRACKET1C; }

    "{{"                        { yybegin(YYINITIAL); return QuokkaTypes.REACTIVE_BRACKET_OPENED; }
    "}}"                        { yybegin(YYINITIAL); return QuokkaTypes.REACTIVE_BRACKET_OPENED; }

    {metadata}                  { yybegin(YYINITIAL); return QuokkaTypes.META; }
    {method}                    { yybegin(YYINITIAL); return QuokkaTypes.METHOD_NAME; }

    .                           { yybegin(YYINITIAL); return QuokkaTypes.FUNC_LINE; }
}

<w8ForValue> {
    \n                          { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
    {whitespace}                { yybegin(w8ForValue); return TokenType.WHITE_SPACE; }
    {whitespace}+               { yybegin(w8ForValue); return TokenType.WHITE_SPACE; }
    "("                         { yypushback(1); yybegin(YYINITIAL); }
     [^ \t\n\(]+                  { yybegin(w8ForValue); return QuokkaTypes.VAL; }
}