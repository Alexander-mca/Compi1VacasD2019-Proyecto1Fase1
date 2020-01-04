package Analizadores;
import java_cup.runtime.Symbol; 
import Interfaz.ColorearPalabras;
import java.awt.Color;
import Interfaz.CError;
%% 
%class Scanner
%public
%line
%char 
%cup 
%column
%full
%unicode
%ignorecase

%init{
    yyline = 1; 
    yychar = 1; 
    yycolumn = 1;
%init} 
 
%{
	String aux = "";
public ColorearPalabras pin=new ColorearPalabras();
int columna=1;
%}


%state COMENTARIO_MULTI, ESTADO_CADENA


id=([A-ZÑa-zñ]["_"0-9A-ZÑa-zñ]*)|(["_"]+[0-9A-ZÑa-zñ]["_"0-9A-ZÑa-zñ]*)

entero=[0-9]+
doble=[0-9]+"."[0-9]+
caracter='.'
comentario_linea=("//".*\r\n)|("//".*\n)|("//".*\r)
BLANCOS=[ \r\t]+
ESCAPE=[ \r\t\n\\]+
%%


<YYINITIAL> \n {}
<YYINITIAL> {BLANCOS} {}
<YYINITIAL> {comentario_linea} {pin.pintaGris(yychar-1,yylength()); }
<YYINITIAL> "/*" {yybegin(COMENTARIO_MULTI);pin.pintaGris(yychar-1,yylength());}
<COMENTARIO_MULTI> "*/" {yybegin(YYINITIAL);pin.pintaGris(yychar-1,yylength());}
<COMENTARIO_MULTI> \n {}
<COMENTARIO_MULTI> {BLANCOS} {}
<COMENTARIO_MULTI> . {pin.pintaGris(yychar-1,yylength());}

<YYINITIAL> "\"" {yybegin(ESTADO_CADENA); aux = ""; pin.pintaNara(yychar-1,yylength());}
<ESTADO_CADENA> {ESCAPE} { aux = aux + yytext();} 
<ESTADO_CADENA> "\"" {yybegin(YYINITIAL);pin.pintaNara(yychar-1,yylength()); return new Symbol(sym.cadena,yyline,yycolumn,aux);}
<ESTADO_CADENA> . {aux = aux + yytext(); pin.pintaNara(yychar-1,yylength());}


<YYINITIAL> "++" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.masmas,yyline,yycolumn, yytext());}
<YYINITIAL> "--" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.menosmenos,yyline,yycolumn, yytext());}

<YYINITIAL> "+" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.mas,yyline,yycolumn, yytext());}
<YYINITIAL> "-" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.menos,yyline,yycolumn, yytext());}
<YYINITIAL> "*" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.por,yyline,yycolumn, yytext());}
<YYINITIAL> "/" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.division,yyline,yycolumn, yytext());}
<YYINITIAL> "%" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.modulo,yyline,yycolumn, yytext());}
<YYINITIAL> "(" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.parizquierdo,yyline,yycolumn, yytext());}
<YYINITIAL> ")" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.parderecho,yyline,yycolumn, yytext());}
<YYINITIAL> "=" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.igual,yyline,yycolumn, yytext());}


<YYINITIAL> "<" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.menorque,yyline,yycolumn, yytext());}
<YYINITIAL> ">" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.mayorque,yyline,yycolumn, yytext());}
<YYINITIAL> "<=" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.menorigualque,yyline,yycolumn, yytext());}
<YYINITIAL> ">=" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.mayorigualque,yyline,yycolumn, yytext());}
<YYINITIAL> "==" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.igualigual,yyline,yycolumn, yytext());}
<YYINITIAL> "!=" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.diferenteque,yyline,yycolumn, yytext());}

<YYINITIAL> "||" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.or,yyline,yycolumn, yytext());}
<YYINITIAL> "&&" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.and,yyline,yycolumn, yytext());}
<YYINITIAL> "^" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.xor,yyline,yycolumn, yytext());}
<YYINITIAL> "!" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.not,yyline,yycolumn, yytext());}

<YYINITIAL> "," {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.coma,yyline,yycolumn, yytext());}
<YYINITIAL> ";" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.puntoycoma,yyline,yycolumn, yytext());}
<YYINITIAL> "{" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.llaveizq,yyline,yycolumn, yytext());}
<YYINITIAL> "}" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.llaveder,yyline,yycolumn, yytext());}
<YYINITIAL> ":" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.dospuntos,yyline,yycolumn, yytext());}
<YYINITIAL> "?" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.interrogacion,yyline,yycolumn, yytext());}
<YYINITIAL> "[" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.corizquierdo,yyline,yycolumn, yytext());}
<YYINITIAL> "]" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.corderecho,yyline,yycolumn, yytext());}
<YYINITIAL> "." {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.punto,yyline,yycolumn, yytext());}
<YYINITIAL> "**" {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.potencia,yyline,yycolumn, yytext());}



<YYINITIAL> "int" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rint,yyline,yycolumn, yytext());}
<YYINITIAL> "double" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rdouble,yyline,yycolumn, yytext());}
<YYINITIAL> "char" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rchar,yyline,yycolumn, yytext());}
<YYINITIAL> "boolean" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rboolean,yyline,yycolumn, yytext());}
<YYINITIAL> "String" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rstring,yyline,yycolumn, yytext());}
<YYINITIAL> "graficar_dot" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.graphdot,yyline,yycolumn, yytext());}
<YYINITIAL> "graficar_entornos" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.graphentornos,yyline,yycolumn, yytext());}
<YYINITIAL> "class" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rclass,yyline,yycolumn, yytext());}
<YYINITIAL> "extends" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rextends,yyline,yycolumn, yytext());}
<YYINITIAL> "void" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rvoid,yyline,yycolumn, yytext());}
<YYINITIAL> "println" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rprintln,yyline,yycolumn, yytext());}
<YYINITIAL> "print" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rprint,yyline,yycolumn, yytext());}
//<YYINITIAL> "printtabla" {return new Symbol(sym.rprinttabla,yyline,yycolumn, yytext());}


<YYINITIAL> "if" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rif,yyline,yycolumn, yytext());}
<YYINITIAL> "else" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.relse,yyline,yycolumn, yytext());}
<YYINITIAL> "while" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rwhile,yyline,yycolumn, yytext());}
<YYINITIAL> "break" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rbreak,yyline,yycolumn, yytext());}
<YYINITIAL> "continue" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rcontinue,yyline,yycolumn, yytext());}
<YYINITIAL> "for" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rfor,yyline,yycolumn, yytext());}
<YYINITIAL> "do" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rdo,yyline,yycolumn, yytext());}
<YYINITIAL> "switch" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rswitch,yyline,yycolumn, yytext());}
<YYINITIAL> "case" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rcase,yyline,yycolumn, yytext());}
<YYINITIAL> "default" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rdefault,yyline,yycolumn, yytext());}
<YYINITIAL> "pow" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rpow,yyline,yycolumn, yytext());}

//<YYINITIAL> "void" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rvoid,yyline,yycolumn, yytext());}
<YYINITIAL> "return" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rreturn,yyline,yycolumn, yytext());}
<YYINITIAL> "new" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rnew,yyline,yycolumn, yytext());}

//<YYINITIAL> "public" {return new Symbol(sym.rpublic,yyline,yycolumn, yytext());}
//<YYINITIAL> "protected" {return new Symbol(sym.rprotected,yyline,yycolumn, yytext());}
//<YYINITIAL> "private" {return new Symbol(sym.rprivate,yyline,yycolumn, yytext());}
//<YYINITIAL> "abstract" {return new Symbol(sym.rabstract,yyline,yycolumn, yytext());}
//<YYINITIAL> "static" {return new Symbol(sym.rstatic,yyline,yycolumn, yytext());}
//<YYINITIAL> "final" {return new Symbol(sym.rfinal,yyline,yycolumn, yytext());}

//<YYINITIAL> "str" {return new Symbol(sym.rstr,yyline,yycolumn, yytext());}
//<YYINITIAL> "toDouble" {return new Symbol(sym.rtoDouble,yyline,yycolumn, yytext());}
//<YYINITIAL> "toInt" {return new Symbol(sym.rtoInt,yyline,yycolumn, yytext());}
//<YYINITIAL> "toChar" {return new Symbol(sym.rtoChar,yyline,yycolumn, yytext());}

<YYINITIAL> "this" {return new Symbol(sym.rthis,yyline,yycolumn, yytext());}
//<YYINITIAL> "super" {return new Symbol(sym.rsuper,yyline,yycolumn, yytext());}
<YYINITIAL> "null" {return new Symbol(sym.rnull,yyline,yycolumn, yytext());}
<YYINITIAL> "import" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rimport,yyline,yycolumn, yytext());}

//<YYINITIAL> "graph" {return new Symbol(sym.rgraph,yyline,yycolumn, yytext());}
//<YYINITIAL> "read_file" {return new Symbol(sym.rread_file,yyline,yycolumn, yytext());}
//<YYINITIAL> "write_file" {return new Symbol(sym.rwrite_file,yyline,yycolumn, yytext());}

//<YYINITIAL> "instanceof" {return new Symbol(sym.rinstanceof,yyline,yycolumn, yytext());}
//<YYINITIAL> "read" {return new Symbol(sym.rread,yyline,yycolumn, yytext());}

<YYINITIAL> {entero} {pin.pintaMora(yychar-1,yylength());return new Symbol(sym.entero,yyline,yycolumn, yytext());}
<YYINITIAL> {doble} {pin.pintaMora(yychar-1,yylength());return new Symbol(sym.doble,yyline,yycolumn, yytext());}
<YYINITIAL> "true" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rtrue,yyline,yycolumn, yytext());}
<YYINITIAL> "false" {pin.pintaAzul(yychar-1,yylength());return new Symbol(sym.rfalse,yyline,yycolumn, yytext());}

<YYINITIAL> {caracter} { pin.pintaNara(yychar-1,yylength());return new Symbol(sym.caracter,yyline,yycolumn, yytext());}
<YYINITIAL> {id} {pin.pintaNegro(yychar-1,yylength());return new Symbol(sym.id,yyline,yycolumn, yytext());}








. {
    System.err.println("Este es un error lexico: "+yytext()+", en la linea: "+yyline+", en la columna: "+yycolumn+1);
    Interfaz.Editor.lista_errores.add(new CError("Léxico", "Caractér incorrecto '" + yytext() + "'", yyline, yycolumn+1));
    

}