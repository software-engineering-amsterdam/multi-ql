// Learn more about F# at http://fsharp.org
// See the 'F# Tutorial' project for more help.

open System 
open System.IO 
open Statements
 
let x = "   
    SELECT x, y, z   
    FROM t1   
    LEFT JOIN t2   
    INNER JOIN t3 ON t3.ID = t2.ID   
    WHERE x = 50 AND y = 20   
    ORDER BY x ASC, y DESC, z   
"   
 
let xample = "form taxOfficeExample { 
  \"Did you sell a house in 2010?\"
    hasSoldHouse: boolean
  \"Did you buy a house in 2010?\"
    hasBoughtHouse: boolean
  \"Did you enter a loan?\"
    hasMaintLoan: boolean
"

let xample2 = "
form taxOfficeExample { 
    \'Did you sell a house in 2010?\'
    hasSoldHouse: boolean

    }
"
let xample3 = "
form taxOfficeExample { 

    }
"

let parserGenTest = " \"test esc string thingy?\" "
let parserGenTest2 = " test esc string"
let parserGenTest3 = "10232";


[<EntryPoint>]
let main argv = 
    printfn "%A" argv
    
    printfn "Starting parsing"
    let text = File.ReadAllLines("../../examples/example.txt");
    for str in text do
        printfn "%s" str

    printfn "Starting parsing test 3 "
    let lexbuf1 = Microsoft.FSharp.Text.Lexing.LexBuffer<_>.FromString parserGenTest3
    let y = Parser.start Lexer.tokenize lexbuf1
    printfn "%A" y   

    printfn "Starting parsing test 2 "
    let lexbuf2 = Microsoft.FSharp.Text.Lexing.LexBuffer<_>.FromString parserGenTest2
    let y = Parser.start Lexer.tokenize lexbuf2
    printfn "%A" y   
       
    printfn "Starting parsing test 4 "
    let lexbuf4 = Microsoft.FSharp.Text.Lexing.LexBuffer<_>.FromString parserGenTest
    let y = Parser.start Lexer.tokenize lexbuf4
    printfn "%A" y  

    printfn "Starting parsing xample "
    let lexbuf3 = Microsoft.FSharp.Text.Lexing.LexBuffer<_>.FromString xample3
    let y = Parser.start Lexer.tokenize lexbuf3
    printfn "%A" y   


    Console.WriteLine("(press any key)")   
    Console.ReadKey(true) |> ignore
    0 // return an integer exit code
