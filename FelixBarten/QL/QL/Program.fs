// Learn more about F# at http://fsharp.org
// See the 'F# Tutorial' project for more help.

open System 
open System.IO 
open StatementsTwo
 
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

[<EntryPoint>]
let main argv = 
    printfn "%A" argv
    
    printfn "Starting parsing"
    let text = File.ReadAllLines("../../examples/example.txt");
    for str in text do
        printfn "%s" str

  

    let lexbuf2 = Microsoft.FSharp.Text.Lexing.LexBuffer<_>.FromString xample3
    let y = ParserTwo.start Lexer2.tokenize lexbuf2
    printfn "%A" y   


    Console.WriteLine("(press any key)")   
    Console.ReadKey(true) |> ignore
    0 // return an integer exit code
