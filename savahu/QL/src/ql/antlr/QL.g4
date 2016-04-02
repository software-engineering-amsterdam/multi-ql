grammar QL;

@parser::header
{
    package ql.antlr.generatedcode;
    import ql.ast.*;
    import ql.ast.expression.*;
    import ql.ast.type.*;
    import ql.ast.form.*;
    import ql.ast.literal.*;
    import ql.ast.statement.*;
    import ql.ast.question.*;
}

@lexer::header
{
    package ql.antlr.generatedcode;
}

form returns [Form result]
  : 'form' Ident '{' body = block '}'
  { $result = new Form(new Ident($Ident.text), $body.result); }
  ;

block returns [Block result]
  @init { 
          List<Statement> statements = new ArrayList<Statement>(); 
          List<Question> questions = new ArrayList<Question>(); 
        }
:  (question { questions.add($question.result); } | statement { statements.add($statement.result); })* { 
       $result = new Block(statements, questions); 
    }
;

statement returns [Statement result]
  : ifstatement { $result = $ifstatement.result; }
  | ifelsestatement { $result = $ifelsestatement.result; }
  ;

ifstatement returns [Statement result]
  : 'if' '(' condition=orExpr ')' '{' block '}' { $result = new IfStatement($condition.result, $block.result); }
  ;

ifelsestatement returns [Statement result]
  : 'if' '(' condition=orExpr ')' '{' thenstatement=block '}' 'else' '{' elsestatement=block '}' { $result = new IfElseStatement($condition.result, $thenstatement.result, $elsestatement.result); }
  ;

question returns [Question result]
  : simplequestion { $result = $simplequestion.result;}
  | computedquestion { $result = $computedquestion.result;}
  ;

simplequestion returns [Question result]
  : questionid=Ident ':' questionlabel=label type { 
    $result = new SimpleQuestion(new Ident($questionid.text), new Label($questionlabel.text), $type.result); 
    }
  ;

computedquestion returns [Question result]
  : simplequestion '(' orExpr ')' { 
    $result = new ComputedQuestion($simplequestion.result.getId(), $simplequestion.result.getLabel(), $simplequestion.result.getType(), $orExpr.result);
    }
  ;

type returns [Type result] 
  : STRING_TYPE { $result = new StringType(); }
  | INTEGER_TYPE { $result = new IntType(); }
  | MONEY_TYPE { $result = new IntType(); }
  | BOOLEAN_TYPE { $result = new BoolType(); }
  ;

label returns [Label result] 
  : Str { $result = new Label($Str.text); }
  ;

primary returns [Expr result]
  : Int   { $result = new IntegerLiteral(Integer.parseInt($Int.text)); 
            $result.setLiteral(); }
  | Ident { $result = new Ident($Ident.text); }
  | Str   { $result = new StringLiteral($Str.text); 
            $result.setLiteral(); }
  | bool  { $result = $bool.result; 
            $result.setLiteral(); }
  | '(' x=orExpr ')'{ $result = $x.result; }
  ;

bool returns [Expr result]
  : t=True  { $result = new BoolLiteral(true); }
  | t=False { $result = new BoolLiteral(false); }
  ; 
    
unaryExpr returns [Expr result]
    :  PlusSymbol x=unaryExpr { $result = new Pos($x.result); }
    |  MinSymbol x=unaryExpr { $result = new Neg($x.result); }
    |  ExclamationMark x=unaryExpr { $result = new Not($x.result); }
    |  p=primary    { $result = $p.result; }
    ;   

mulExpr returns [Expr result]
    :   lhs=unaryExpr { $result=$lhs.result; } ( op=MulSymbol rhs=unaryExpr 
    { 
      if ($op.text.equals("*")) {
        $result = new Mul($result, $rhs.result);
      }
      if ($op.text.equals("<=")) {
        $result = new Div($result, $rhs.result);      
      }
    })*
    ;
    
  
addExpr returns [Expr result]
    :   lhs=mulExpr { $result=$lhs.result; } ( op=AddSymbol rhs=mulExpr
    { 
      if ($op.text.equals("+")) {
        $result = new Add($result, $rhs.result);
      }
      if ($op.text.equals("-")) {
        $result = new Sub($result, $rhs.result);      
      }
    })*
    ;
  
relExpr returns [Expr result]
    :   lhs=addExpr { $result=$lhs.result; } ( op=RelSymbol rhs=addExpr 
    { 
      if ($op.text.equals("<")) {
        $result = new LT($result, $rhs.result);
      }
      if ($op.text.equals("<=")) {
        $result = new LEq($result, $rhs.result);      
      }
      if ($op.text.equals(">")) {
        $result = new GT($result, $rhs.result);
      }
      if ($op.text.equals(">=")) {
        $result = new GEq($result, $rhs.result);      
      }
      if ($op.text.equals("==")) {
        $result = new Eq($result, $rhs.result);
      }
      if ($op.text.equals("!=")) {
        $result = new NEq($result, $rhs.result);
      }
    })*
    ;
    
andExpr returns [Expr result]
    :   lhs=relExpr { $result=$lhs.result; } ( '&&' rhs=relExpr { $result = new And($result, $rhs.result); } )*
    ;
    

orExpr returns [Expr result]
    :   lhs=andExpr { $result = $lhs.result; } ( OrSymbol rhs=andExpr { $result = new Or($result, $rhs.result); } )*
    ;

// Lexer Rules

    // Tokens

BOOLEAN_TYPE : 'boolean';
MONEY_TYPE : 'money';
INTEGER_TYPE : 'integer';
STRING_TYPE : 'string';
    
WS  :	(' ' | '\t' | '\n' | '\r') -> skip
    ;

COMMENT : ('/*' .* '*/') -> skip
    ;

Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Int: ('0'..'9')+;

Str: '"' .*? '"';

OrSymbol: '||';

AndSymbol: '&&';

RelSymbol: '<'|'<='|'>'|'>='|'=='|'!=';

AddSymbol: PlusSymbol | MinSymbol;

PlusSymbol: '+';

MinSymbol: '-';

MulSymbol: '*' | '/';

True: 'true';
      
False: 'false';

ExclamationMark: '!';