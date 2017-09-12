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

    static Stack<Integer> escapedDedents = new Stack<>();
    static Stack<Integer> wrappers = new Stack<Integer>() {{push(0);}};
    public static int tab_size = 4;
    private static   int current_line_indent = 0;

    static boolean wasLastTokenComponent = false;
    static boolean wasLastTokenMeta = false;
    static boolean wasLastTokenMethod = false;

    static boolean isOpenBrackets = false;
    static int numberToClose = 0;
    static int openedBrackets1 = 0;
    static int openedBrackets2 = 0;
    static boolean reactiveLinkInJSON = false;
    static int openedFuncBrackets = 0;
    static boolean firstTouch = false;
    static int indent_to_close = 0;
    static boolean argumentsTime = false;
    static int indent_to_close_meta = 0;
    static boolean metaFirstTouch = false;

    static boolean shouldGoOutOfJson = false;
%}

whitespace = [ \t]
digit = [0-9]+(\.[0-9]*)?
hex_digit = #[0-9a-fA-F]{1} | #[0-9a-fA-F]{3} | #[0-9a-fA-F]{6}
identifier = [a-zA-Z][a-zA-Z0-9_]*
components = [a-zA-Z][a-zA-Z\.]*
method = \.[a-zA-Z]*
colon = ":"
toFuncSeparator = :[ ]*\(([a-zA-Z0-9]+)?\)[ ]*->
comment = "//"[^\r\n]*
metadata = @[a-zA-Z]*
value = [\p{L}0-9].*
string = '[^']*'|\"[^\"]*\"


%state normal
%state returnDedents

%state w8ForValue

%state comment
%state string_filling_single
%state string_filling_double
%state stringFillingExtra
%state stringFillingExtra2
%state json_filling
%state reactive_link_filling
%state meta_filling
%state method_filling
%state method_function_filling_with_brace
%state method_function_filling_no_brace

%state probablyJson

%state indentsEOFeascape
%state method_begin

%state indentsCountMeta
%state indentsCoutnFunc
%state endOfComment

%%

<YYINITIAL> {
    " "                         {
                                    yybegin(YYINITIAL); current_line_indent += 1; return TokenType.WHITE_SPACE;
                                }
    \t                          {
                                    yybegin(YYINITIAL); current_line_indent = (current_line_indent + tab_size) & ~(tab_size - 1); return TokenType.WHITE_SPACE;
                                }
    "/*"                        { yybegin(comment); return QuokkaTypes.COMMENT_BEGIN; }
    [^\n\t ]                    {

                                        if(wrappers.peek() == 0 && current_line_indent == 0){
                                            yypushback(1);
                                            yybegin(normal);
                                            return QuokkaTypes.END_LAST;
                                        }
                                        else {
                                            int a = wrappers.peek();
                                            if(a == current_line_indent) {
                                                yybegin(normal);
                                                yypushback(1);
                                                return QuokkaTypes.END_LAST;
                                            }
                                            else if(a < current_line_indent) {
                                                wrappers.push(current_line_indent);
                                                yypushback(yylength());
                                                yybegin(normal);
                                                return QuokkaTypes.INDENT;
                                            }
                                            else if(a > current_line_indent) {
                                                yypushback(1);
                                                yybegin(returnDedents);
                                                wrappers.pop();
                                                return QuokkaTypes.DEDENT;
                                            }
                                        }
                                }
    \n                          { current_line_indent = 0; yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
}

<returnDedents> {
    .                           {

                                       if(current_line_indent == 0) {
                                            yypushback(yylength());
                                            if(wrappers.peek() != 0) {
                                                yybegin(returnDedents);
                                                wrappers.pop();
                                                return QuokkaTypes.DEDENT;
                                            }
                                            else {
                                                yybegin(YYINITIAL);
                                                return TokenType.WHITE_SPACE;
                                            }
                                       }
                                       else {
                                            if(wrappers.peek() != 0) {
                                                int a = wrappers.pop();
                                                if(current_line_indent == wrappers.peek()) {
                                                    System.out.println("YAY");
                                                    yybegin(normal);
                                                    yypushback(1);
                                                    return QuokkaTypes.DEDENT;
                                                }
                                                else {
                                                    yybegin(returnDedents);
                                                    yypushback(1);
                                                    return QuokkaTypes.DEDENT;
                                                }
                                            }
                                            else {
                                                yybegin(normal);
                                                yypushback(1);
                                                return QuokkaTypes.DEDENT;
                                            }
                                       }
                                }
}

<normal> {
    \n                          { current_line_indent = 0; yybegin(YYINITIAL); wasLastTokenComponent = false; return TokenType.WHITE_SPACE; }
    {whitespace}                { yybegin(normal); return TokenType.WHITE_SPACE; }
    {whitespace}+               { yybegin(normal); return TokenType.WHITE_SPACE; }
    {comment}                   { yybegin(normal); return QuokkaTypes.LINE_COMMENT; }
    "def"                       { yybegin(normal); return QuokkaTypes.DEFINE; }
    "pub"                       { yybegin(normal); return QuokkaTypes.KEYWORD; }
    "public"                    { yybegin(normal); return QuokkaTypes.KEYWORD; }
    "private"                   { yybegin(normal); return QuokkaTypes.KEYWORD; }
    {components}                { if(!wasLastTokenComponent) {yybegin(normal); wasLastTokenComponent= true; return QuokkaTypes.COMPONENT;}
                                  else {wasLastTokenComponent = false; yybegin(normal); return QuokkaTypes.IDENTIFIER; }
                                }
    ":"                         {
                                    if(wasLastTokenMeta) {
                                        yybegin(meta_filling);
                                    }
                                    else if(wasLastTokenMethod) {
                                        yybegin(method_filling);
                                    }
                                    else {
                                         yybegin(w8ForValue);
                                    }
                                    wasLastTokenComponent = false;
                                    return QuokkaTypes.COLON;
                                }
    "/*"                        { yypushback(yylength()); yybegin(comment); return QuokkaTypes.COMMENT_BEGIN; }
    {metadata}                  {  yybegin(normal); return QuokkaTypes.META; }
    {identifier}                { if(wasLastTokenComponent) { yybegin(normal); wasLastTokenComponent = false; return QuokkaTypes.IDENTIFIER; } }
    {method}                    { yybegin(normal); return QuokkaTypes.METHOD_NAME; }
    [^]                         { yybegin(normal); return TokenType.BAD_CHARACTER; }
}

<method_begin> {
    {whitespace}                { yybegin(method_begin); return TokenType.WHITE_SPACE; }
    {whitespace}+               { yybegin(method_begin); return TokenType.WHITE_SPACE; }
    {method}                    { yybegin(method_begin);  return QuokkaTypes.METHOD_NAME; }
    ":"                         { yybegin(method_filling);  wasLastTokenComponent = false; wasLastTokenMethod = true;
                                                                                               return QuokkaTypes.COLON; }
   [^]                         { yybegin(normal); return TokenType.BAD_CHARACTER; }
}

<comment> {
    "*"                        { yybegin(endOfComment); return QuokkaTypes.COMMENT_END_HALF; }
    \n                          { yybegin(comment); return TokenType.WHITE_SPACE; }
    {whitespace}                { yybegin(comment); return TokenType.WHITE_SPACE; }
    {whitespace}+               { yybegin(comment); return TokenType.WHITE_SPACE; }
    [^"*/" \n]*                 { yybegin(comment); return QuokkaTypes.COMMENT_SYMBOL; }
    [^]                         { yybegin(normal); return TokenType.BAD_CHARACTER; }
}

<endOfComment> {
    "/"                         { yybegin(normal); return QuokkaTypes.COMMENT_END; }
    [^]                         { yybegin(comment); return QuokkaTypes.COMMENT_SYMBOL; }
}

<w8ForValue> {
    \n                          { current_line_indent = 0; yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
    {whitespace}                { yybegin(w8ForValue); return TokenType.WHITE_SPACE; }
    {whitespace}+               { yybegin(w8ForValue); return TokenType.WHITE_SPACE; }
    {value}                     { yybegin(w8ForValue); return QuokkaTypes.VAL; }
    {hex_digit}                 { yybegin(w8ForValue); return QuokkaTypes.HEX_DIGIT; }
    "{{"                        { yybegin(reactive_link_filling); openedBrackets2 = 0; return QuokkaTypes.REACTIVE_BRACKET_OPENED; }
    \"{3}\"*                    {
                                       if(yylength() % 2 == 0) {
                                           return TokenType.BAD_CHARACTER;
                                       }
                                       else {
                                           isOpenBrackets= false;
                                           numberToClose = yylength();
                                           yybegin(stringFillingExtra);
                                           return QuokkaTypes.STRING;
                                       }
                                }
    '{3}'*                      {
                                           if(yylength() % 2 == 0) {
                                               return TokenType.BAD_CHARACTER;
                                           }
                                           else {
                                                isOpenBrackets= false;
                                                numberToClose = yylength();
                                                 yybegin(stringFillingExtra2);
                                                 return QuokkaTypes.STRING;
                                             }
                                }
    \"                          { yybegin(string_filling_double); return QuokkaTypes.STRING; }
    '                           { yybegin(string_filling_single); return QuokkaTypes.STRING; }
    "["                         { openedBrackets1 = 1; openedBrackets2 = 0; yybegin(json_filling); return QuokkaTypes.BRACKET1O; }
    "{"                         { openedBrackets2 = 1; openedBrackets1 = 0; yybegin(json_filling); return QuokkaTypes.BRACKET2O; }

    [^]                         { yybegin(w8ForValue); return TokenType.BAD_CHARACTER; }
 }

<reactive_link_filling> {
    \n                           { yybegin(YYINITIAL); current_line_indent = 0; return TokenType.WHITE_SPACE; }
    {whitespace}                 { yybegin(reactive_link_filling); return TokenType.WHITE_SPACE; }
    {whitespace}+                { yybegin(reactive_link_filling); return TokenType.WHITE_SPACE; }
     "}}"                        {
                                             if(reactiveLinkInJSON)
                                                 yybegin(json_filling);
                                             else
                                                 yybegin(normal);
                                             return QuokkaTypes.REACTIVE_BRACKET_CLOSED;
                                     }
     [^"}}"\n ]*                 { yybegin(reactive_link_filling); return QuokkaTypes.IDENTIFIER; }
     [^]                         { yybegin(normal); return TokenType.BAD_CHARACTER; }
}

 <string_filling_single> {
    {whitespace}                { yybegin(string_filling_single); return TokenType.WHITE_SPACE; }
    {whitespace}+               { yybegin(string_filling_single); return TokenType.WHITE_SPACE; }
    "\n"                        { yybegin(string_filling_single); return TokenType.WHITE_SPACE; }
    [^']*                       { yybegin(string_filling_single); return QuokkaTypes.STRING; }
    '                           { yybegin(normal); return QuokkaTypes.STRING; }
    [^]                         { yybegin(normal); return TokenType.BAD_CHARACTER; }
 }

 <string_filling_double> {
     {whitespace}                { yybegin(string_filling_double); return TokenType.WHITE_SPACE; }
     {whitespace}+               { yybegin(string_filling_double); return TokenType.WHITE_SPACE; }
     "\n"                        { yybegin(string_filling_double); return TokenType.WHITE_SPACE; }
     [^\"]*                      { yybegin(string_filling_double); return QuokkaTypes.STRING; }
     \"                          { yybegin(normal); return QuokkaTypes.STRING; }
     [^]                         { yybegin(normal); return TokenType.BAD_CHARACTER; }
}

 <stringFillingExtra> {
        \"{3}\"*                {
                                        if(numberToClose == yylength()) {
                                            yybegin(normal);
                                            return QuokkaTypes.STRING;
                                        }
                                        else if (numberToClose < yylength()) {
                                            return TokenType.BAD_CHARACTER;
                                        }

                                }
        [^]|"\n"                { yybegin(stringFillingExtra); return QuokkaTypes.STRING; }
        [^]                     { yybegin(normal); return TokenType.BAD_CHARACTER; }
 }

 <stringFillingExtra2> {
        '{3}'*                  {


                                                 if(numberToClose == yylength()) {
                                                     yybegin(normal);
                                                     return QuokkaTypes.STRING;
                                                 }
                                                 else if (numberToClose < yylength()) {
                                                     return TokenType.BAD_CHARACTER;
                                                 }

                                         }
        [^]|"\n"                { yybegin(stringFillingExtra2); return QuokkaTypes.STRING; }
        [^]                         { yybegin(normal); return TokenType.BAD_CHARACTER; }
 }

<json_filling> {
    \n                                          {
                                                    if(shouldGoOutOfJson) {
                                                        yybegin(normal);
                                                        current_line_indent = 0;
                                                        shouldGoOutOfJson = false;
                                                        openedBrackets1 = 0;
                                                        openedBrackets2 = 0;
                                                        return QuokkaTypes.END_LAST;
                                                    }
                                                    else
                                                        yybegin(json_filling);
                                                    return TokenType.WHITE_SPACE;
                                                }
    {whitespace}                                { yybegin(json_filling); return TokenType.WHITE_SPACE; }
    {whitespace}+                               { yybegin(json_filling); return TokenType.WHITE_SPACE; }
    {string}                                    { yybegin(json_filling); return QuokkaTypes.STRING; }
    {digit}                                     { yybegin(json_filling); return QuokkaTypes.DIGIT; }
    "{{"                                        { reactiveLinkInJSON = true; yybegin(reactive_link_filling); return QuokkaTypes.REACTIVE_BRACKET_OPENED; }
    ":"                                         { yybegin(json_filling); return QuokkaTypes.COLON; }
    ","                                         { yybegin(json_filling); return QuokkaTypes.COMMA; }
    "["                                         { openedBrackets1++; yybegin(json_filling); return QuokkaTypes.BRACKET1O; }
    "{"                                         { openedBrackets2++; yybegin(json_filling); return QuokkaTypes.BRACKET2O; }
    "]"                                         {
                                                    openedBrackets1--;
                                                    if(openedBrackets1 == 0 && openedBrackets2 == 0) {
                                                        yybegin(json_filling);
                                                        shouldGoOutOfJson = true;
                                                    }
                                                    else
                                                        yybegin(json_filling);
                                                    return QuokkaTypes.BRACKET1C;
                                                }
    "}"                                         {
                                                    openedBrackets2--;
                                                    if(openedBrackets1 == 0 && openedBrackets2 == 0) {
                                                        yybegin(json_filling);
                                                        shouldGoOutOfJson = true;
                                                    }
                                                    else
                                                        yybegin(json_filling);
                                                    return QuokkaTypes.BRACKET2C;
                                                }
    {identifier}                                { yybegin(json_filling); return QuokkaTypes.IDENTIFIER; }
    [^]                                         { yybegin(json_filling); return TokenType.BAD_CHARACTER; }
 }
