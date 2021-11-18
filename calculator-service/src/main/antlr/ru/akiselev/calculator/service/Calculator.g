grammar Calculator;

@header {
    package ru.akiselev.calculator.service;
}

WHITESPACE : (' ' | '\t') -> skip ;
fragment DIGIT : [0-9] ;
fragment NUMBER : DIGIT + ;
fragment LETTER : [A-Za-z] ;

OPERAND  : (LETTER | NUMBER)+ ;

MULT   : '*' ;
DIV    : '/' ;
PLUS   : '+' ;
MINUS  : '-' ;
UMINUS : '--';
LEFT_BRACKET  : '(' ;
RIGHT_BRACKET : ')' ;

expression
                :
                  OPERAND                               # Operand
                | expression UMINUS                     # UnaryMinus
                | expression MULT expression            # Multiply
                | expression DIV expression             # Division
                | expression PLUS expression            # Plus
                | expression MINUS expression           # Minus
                | LEFT_BRACKET expression RIGHT_BRACKET # Brackets
                ;
