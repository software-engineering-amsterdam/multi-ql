module Statements
open Microsoft.FSharp.Collections


type QuestionType = 
    | Money of string 
    | Integer of string
    | String of string


type Identifier = string

type Question = Identifier * QuestionType

type Form =  Question list 



type Expr = string


type expr = 
    | Val of string
    | Int of System.Int32

type Stmt = 
    | Assign of string * expr