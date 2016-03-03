grammar QL;
questionnaire         :  form ;
form                  : 'form'  IDENTIFIER '{' question+'}';

question  : IDENTIFIER  ':'    STRINGLITERAL type                                       #NormalQuestion
          | IDENTIFIER  ':'    STRINGLITERAL type  '(' expression ')'                   #CalculatedQuestion
          | 'if'  '(' expression ')' '{' then= question* '}'                            #ifQuestion
          | 'if'   ('else' '{' question* '}')?                                          #IfElseQuestion
          ;   
          
expression 
                     : op=('!'|'+'|'-') expression 					     #unaryExpr
                     |  expression op=('*' |'/') expression              #TimeDivExpr 
                     |  expression op=('+' | '-') expression 			 #addSubExpr                        
                     |  expression op=('>'|'>='|'<'|'<=' ) expression   #COMPExpr  
                     |  expression op=('==' |'!=') expression            #EQUALExpr 
                     |  expression '&&' expression                        #AndExpr 
                     |   expression '||' expression 					  #ORExpr                                       
                     |  ('(' expression ')')                              #PAR 
                     |  BOOLEANLITERAL                                     #Booleanliteral                                       
                     |  STRINGLITERAL                                      #Stringliteral
                     |  INT                                                #Integerliteral
                     |  IDENTIFIER                                         #identifier
                     ;                                            
                     
                     
              
STRINGLITERAL        :'"'(ESC | .)*? '"';
BOOLEANLITERAL       :  'true'
                     |  'false'
                     ;
INT                  : DIGIT+; 
    
IDENTIFIER          : (LOWERCASE | UPPERCASE) (LOWERCASE | UPPERCASE | DIGIT )*;

 LOWERCASE           : [a-z]; 
 UPPERCASE           : [A-Z]; 
 DIGIT               : [0-9];  
  
type                 :  'boolean'    #booleanQL_type                 
                     |  'string'     #stringQL_type
                     |  'integer'    #integerQL_type
                     ;

      
WS                   :  [ \t\r\n]+ -> skip;
ESC				     : '\\"' | '\\\\';
COMMENT
                     :   '/*' .*? '*/' -> skip
                     ;