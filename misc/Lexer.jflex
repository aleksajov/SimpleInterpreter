import java_cup.runtime.Symbol;

%%

%class Lexer
%unicode
%cup
%line
%column



%%

// Whitespace
[ \t\r\n]+     { /* Skip */ }

scope          { return new Symbol(sym.SCOPE); }
print          { return new Symbol(sym.PRINT); }


"="            { return new Symbol(sym.ASSIGN); }
"{"            { return new Symbol(sym.LBRACE); }
"}"            { return new Symbol(sym.RBRACE); }

// Identifiers
[a-zA-Z_][a-zA-Z0-9_]* { return new Symbol(sym.IDENTIFIER, yyline, yycolumn, yytext()); }

// Integer literals
[0-9]+         { return new Symbol(sym.NUMBER, yyline, yycolumn, Integer.parseInt(yytext())); }


// Error handling
.              { System.err.println("Illegal character: " + yytext() + " at line " + (yyline+1)); }