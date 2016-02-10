module Statements
open Microsoft.FSharp.Collections


type questiontype = 
    | Money of string 
    | Integer of string
    | String of string


type identifier = string

type Question = {
     QuestionText : string;
     Identifier : string;
     QuestionType : questiontype;
     }

type Form = {
    Identifier : identifier;
    Questions : Question list;
    }


type Expr = string


type expr = 
    | Val of string
    | Int of System.Int32

type Stmt = 
    | Assign of string * expr
