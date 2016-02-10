module Statements
open Microsoft.FSharp.Collections


type value =   
    | Int of int  
    | Float of float  
    | String of string  

type identifier = string
type op = Eq | Gt | Ge | Lt | Le    // =, >, >=, <, <=   
 
type where =   
    | Cond of (value * op * value)   
    | And of where * where   
    | Or of where * where   

// removed QuestionType: questiontype;
type Question = {
     QuestionText : string;
     Identifier : string;
     QuestionType : string;
     
     }

type questiontype = 
    | Money of string
    | Integer of string
    | Stringtype of string

type Form = {
    Identifier : identifier;
    Questions : Question list;
    }

type Questionary = {
    Questionary : Form list; 
    }

type Expr = string

