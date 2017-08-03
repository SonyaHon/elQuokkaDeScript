package com.quokka_script;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.simpleplugin.psi.SimpleTypes;
import com.intellij.psi.TokenType;

%%

%class QuokkaLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF = \R
KEYWORD = ["def"]
WHITESPACE=[ \t]
TYPE = ["Page""Button""VBox""HBox"]
NAME = \D[\D\d].*
STRING = \D.*
NUMBER = \d+*\.?\d+*
VALUE = [{STRING}{NUMBER}]
GLOBAL_COMPONENT = {KEYWORD}{WHITESPACE}{TYPE}{WHITESPACE}{NAME}
COMPONENT = {WHITESPACE}{TYPE}{NAME}":"{VALUE}

%%