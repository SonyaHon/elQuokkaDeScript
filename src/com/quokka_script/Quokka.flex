package com.quokka_script;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.quokka_script.psi.QuokkaTokenType;
import com.quokka_script.psi.LazyFuncs;
import java.util.Stack;

%%

%class QuokkaLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

%{
    public QuokkaLexer() {
        this((java.io.Reader)null);
    }

    Stack<Integer> escapedDedents = new Stack<>();
    Stack<Integer> wrappers = new Stack<Integer>() {{push(0);}};

    public int tab_size = QuokkaScript.SETTINGS.getTabSize();
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

    int indent_to_close_meta = 0;
    boolean metaFirstTouch = false;

%}

whitespace = [ \t]
digit = [0-9]+(\.[0-9]*)?
hex_digit = #[0-9a-fA-F]{1} | #[0-9a-fA-F]{3} | #[0-9a-fA-F]{6}
identifier = [a-zA-Z][a-zA-Z0-9_]*
components = [a-zA-Z][a-zA-Z\.]*
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

%state indentsCountMeta
%state indentsCoutnFunc

%%

<YYINITIAL> {
    " "                         {
                                    yybegin(YYINITIAL); current_line_indent += 1;
                                }
    \t                          {
                                    yybegin(YYINITIAL); current_line_indent = (current_line_indent + tab_size) & ~(tab_size - 1);
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

<indentsCountMeta> {
    " "                         { yybegin(indentsCountMeta); current_line_indent++; return TokenType.WHITE_SPACE; }
    \t                          { yybegin(indentsCountMeta); current_line_indent = (current_line_indent + tab_size) & ~(tab_size - 1); return TokenType.WHITE_SPACE; }
    .                           {
                                    int realOffset = current_line_indent - indent_to_close_meta;

                                    if(realOffset == 0) {
                                        yybegin(normal);
                                        yypushback(yylength());
                                        indent_to_close_meta = 0;
                                        wasLastTokenMeta = false;
                                        //wrappers.pop();
                                        return QuokkaTypes.END_LAST;
                                    }
                                    else if (realOffset > 0 && metaFirstTouch == true) {
                                        metaFirstTouch = false;
                                        yybegin(meta_filling);
                                        yypushback(yylength());
                                        //wrappers.push(realOffset);
                                        return QuokkaTypes.INDENT;
                                    }
                                    else if( realOffset > 0 ) {
                                        yybegin(meta_filling);
                                        yypushback(yylength());
                                        return TokenType.WHITE_SPACE;
                                    }
                                    else if (realOffset < 0) {
                                        wasLastTokenMeta = false;
                                        indent_to_close_meta = 0;
                                        yybegin(returnDedents);
                                        yypushback(1);
                                        return QuokkaTypes.END_LAST;
                                    }
                                }
    \n                          { current_line_indent = 0; yybegin(indentsCountMeta); return TokenType.WHITE_SPACE; }
}

<indentsCoutnFunc> {
    " "                         { yybegin(indentsCoutnFunc); current_line_indent++; return TokenType.WHITE_SPACE; }
    \t                          { yybegin(indentsCoutnFunc); current_line_indent = (current_line_indent + tab_size) & ~(tab_size - 1); return TokenType.WHITE_SPACE; }
    .                           {
                                    int realOffset = current_line_indent - indent_to_close;
                                    if(realOffset == 0) { // end function
                                        yybegin(normal);
                                        firstTouch = false;
                                        indent_to_close = 0;
                                        wasLastTokenMethod = false;
                                        yypushback(yylength());
                                        return QuokkaTypes.END_LAST;
                                    }
                                    else if(realOffset > 0 && firstTouch == true) { // should go in function
                                        firstTouch = false;
                                        yybegin(method_function_filling_no_brace);
                                        yypushback(yylength());
                                        return QuokkaTypes.INDENT;
                                    }
                                    else if(realOffset > 0) { // already indented, just ws should be there
                                         yybegin(method_function_filling_no_brace);
                                         yypushback(1);
                                         return TokenType.WHITE_SPACE;
                                    }
                                    else if(realOffset < 0) { // should dedent to some previus pos
                                        wasLastTokenMethod = false;
                                        indent_to_close = 0;
                                        firstTouch = false;
                                        yypushback(1);
                                        yybegin(returnDedents);
                                        return QuokkaTypes.END_LAST;
                                    }
                                }
    \n                          { current_line_indent = 0; yybegin(indentsCoutnFunc); return TokenType.WHITE_SPACE; }
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
    "/*"                        { yybegin(comment); return QuokkaTypes.COMMENT_BEGIN; }
    {metadata}                  { metaFirstTouch = true; wasLastTokenMeta = true; yybegin(normal);  indent_to_close_meta = current_line_indent; return QuokkaTypes.META; }
    {identifier}                { if(wasLastTokenComponent) { yybegin(normal); wasLastTokenComponent = false; return QuokkaTypes.IDENTIFIER; } }
    {method}                    { yybegin(normal); firstTouch = true; indent_to_close = current_line_indent; wasLastTokenMethod = true; return QuokkaTypes.METHOD_NAME; }
    [^]                         { yybegin(normal); return TokenType.BAD_CHARACTER; }

}

<comment> {
    "*/"                        { yybegin(normal); return QuokkaTypes.COMMENT_END; }
    \n                          { yybegin(comment); return TokenType.WHITE_SPACE; }
    {whitespace}                { yybegin(comment); return TokenType.WHITE_SPACE; }
    {whitespace}+               { yybegin(comment); return TokenType.WHITE_SPACE; }
    "*"                         { yybegin(comment); return QuokkaTypes.COMMENT_END_HALF; }
    [^"*/" \n]*                 { yybegin(comment); return QuokkaTypes.COMMENT_SYMBOL; }
}

<w8ForValue> {
    \n                          { current_line_indent = 0; yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
    {whitespace}                { yybegin(w8ForValue); return TokenType.WHITE_SPACE; }
    {whitespace}+               { yybegin(w8ForValue); return TokenType.WHITE_SPACE; }
    {value}                     { yybegin(normal); return QuokkaTypes.VAL; }
    {hex_digit}                 { yybegin(normal); return QuokkaTypes.HEX_DIGIT; }
    "{{"                        { yybegin(reactive_link_filling); return QuokkaTypes.REACTIVE_BRACKET_OPENED; }
    \"{3}\"*                    {
                                       if(yylength() % 2 == 0) {
                                           return TokenType.BAD_CHARACTER;
                                       }
                                       else {
                                           isOpenBrackets= false;
                                           numberToClose = yylength();
                                           yypushback(yylength());
                                           yybegin(stringFillingExtra);
                                       }
                                }
    '{3}'*                      {
                                           if(yylength() % 2 == 0) {
                                               return TokenType.BAD_CHARACTER;
                                           }
                                           else {
                                                isOpenBrackets= false;
                                                numberToClose = yylength();
                                                yypushback(yylength());
                                                 yybegin(stringFillingExtra2);
                                             }
                                }
    [\"']{1}                    { yypushback(1); yybegin(string_filling); }
    "["                         { openedBrackets1++; yybegin(json_filling); return QuokkaTypes.BRACKET1O; }
    "{"                         { openedBrackets2++; yybegin(json_filling); return QuokkaTypes.BRACKET2O; }
    [^]                         { yybegin(w8ForValue); return TokenType.BAD_CHARACTER; }
 }

<reactive_link_filling> {
    {whitespace}                 { yybegin(reactive_link_filling); return TokenType.WHITE_SPACE; }
    {whitespace}+                { yybegin(reactive_link_filling); return TokenType.WHITE_SPACE; }
     "}}"                        {
                                             if(reactiveLinkInJSON)
                                                 yybegin(json_filling);
                                             else
                                                 yybegin(normal);
                                             return QuokkaTypes.REACTIVE_BRACKET_CLOSED;
                                     }
     [^"}}"\n ]*                  { yybegin(reactive_link_filling); return QuokkaTypes.IDENTIFIER; }
}

 <string_filling> {
    {whitespace}                { yybegin(string_filling); return TokenType.WHITE_SPACE; }
    {whitespace}+               { yybegin(string_filling); return TokenType.WHITE_SPACE; }
    "\n"                        { yybegin(string_filling); return TokenType.WHITE_SPACE; }
    {string}                    { yybegin(normal); return QuokkaTypes.STRING; }
 }

 <stringFillingExtra> {
        \"{3}\"*                {
                                    if(!isOpenBrackets) {
                                        isOpenBrackets = true;
                                    }
                                    else {
                                        if(numberToClose == yylength()) {
                                            yybegin(normal);
                                            return QuokkaTypes.STRING;
                                        }
                                        else if (numberToClose < yylength()) {
                                            return TokenType.BAD_CHARACTER;
                                        }
                                    }
                                }
        [^]|"\n"                { yybegin(stringFillingExtra); }
 }

 <stringFillingExtra2> {
        '{3}'*                  {
                                             if(!isOpenBrackets) {
                                                 isOpenBrackets = true;
                                             }
                                             else {
                                                 if(numberToClose == yylength()) {
                                                     yybegin(normal);
                                                     return QuokkaTypes.STRING;
                                                 }
                                                 else if (numberToClose < yylength()) {
                                                     return TokenType.BAD_CHARACTER;
                                                 }
                                             }
                                         }
        [^]|"\n"                { yybegin(stringFillingExtra2); }
 }

<json_filling> {
    "\n"                                        { yybegin(json_filling); return TokenType.WHITE_SPACE; }
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
                                                        yybegin(YYINITIAL);
                                                    }
                                                    else
                                                        yybegin(json_filling);
                                                    return QuokkaTypes.BRACKET1C;
                                                }
    "}"                                         {
                                                    openedBrackets2--;
                                                    if(openedBrackets1 == 0 && openedBrackets2 == 0) {
                                                        yybegin(YYINITIAL);
                                                    }
                                                    else
                                                        yybegin(json_filling);
                                                    return QuokkaTypes.BRACKET2C;
                                                }
    {identifier}                                { yybegin(json_filling); return QuokkaTypes.IDENTIFIER; }
    [^]                                         { yybegin(json_filling); return TokenType.BAD_CHARACTER; }
 }

 <meta_filling> {
    \n                                           { current_line_indent = 0; yybegin(indentsCountMeta); return TokenType.WHITE_SPACE; }
    {whitespace}                                 { yybegin(meta_filling); return TokenType.WHITE_SPACE; }
    {whitespace}+                                { yybegin(meta_filling); return TokenType.WHITE_SPACE; }
    [^\n]+                                       { yybegin(meta_filling); return QuokkaTypes.VAL; }
 }

 <method_filling> {
    {whitespace}                                { yybegin(method_filling); return TokenType.WHITE_SPACE; }
    {whitespace}+                               { yybegin(method_filling); return TokenType.WHITE_SPACE; }
    "("                                         { argumentsTime = true; yybegin(method_filling); return QuokkaTypes.ROUND_BRACE_OPENED; }
    ")"                                         { argumentsTime = false; yybegin(method_filling); return QuokkaTypes.ROUND_BRACE_CLOSED; }
    ","                                         { yybegin(method_filling); return QuokkaTypes.COMMA; }
    "->"                                         { yybegin(method_filling); return QuokkaTypes.ARROW_RIGHT; }
    \n                                          { current_line_indent = 0; yybegin(indentsCoutnFunc); return TokenType.WHITE_SPACE; }

    [^\n{]{1}                                   { if(!argumentsTime) { yybegin(method_function_filling_no_brace); yypushback(1); } else{ yybegin(method_filling); return QuokkaTypes.ARGUMENT;}}
    {identifier}                                {
                                                    if(argumentsTime) {
                                                        yybegin(method_filling);
                                                        return QuokkaTypes.ARGUMENT;
                                                    }
                                                    else {
                                                        yypushback(yylength());
                                                        yybegin(method_function_filling_no_brace);
                                                    }
                                                }
    "{"                                         { openedFuncBrackets+=1; yybegin(method_function_filling_with_brace); }
 }

 <method_function_filling_with_brace> {
    "{"                                         { openedFuncBrackets+=1; yybegin(method_function_filling_with_brace); }
    "}"                                         {
                                                  openedFuncBrackets-=1;
                                                  if(openedFuncBrackets == 0) {
                                                     yybegin(YYINITIAL);
                                                     wasLastTokenMethod = false;
                                                     firstTouch = false;
                                                     indent_to_close = 0;
                                                     return QuokkaTypes.FULL_FUNCTION;
                                                  }
                                                  else {
                                                    yybegin(method_function_filling_with_brace);
                                                  }
                                                }
    [^{}]                                       { yybegin(method_function_filling_with_brace); }
 }

<method_function_filling_no_brace> {
    \n                                           { current_line_indent = 0; yybegin(indentsCoutnFunc); return TokenType.WHITE_SPACE; }
    {whitespace}                                 { yybegin(method_function_filling_no_brace); return TokenType.WHITE_SPACE; }
    {whitespace}+                                { yybegin(method_function_filling_no_brace); return TokenType.WHITE_SPACE; }
    [^\n]+                                       { yybegin(method_function_filling_no_brace); return QuokkaTypes.FUNC_LINE; }
}