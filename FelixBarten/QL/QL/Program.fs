// Learn more about F# at http://fsharp.org
// See the 'F# Tutorial' project for more help.

open System   
open Sql   
 
let x = "   
    SELECT x, y, z   
    FROM t1   
    LEFT JOIN t2   
    INNER JOIN t3 ON t3.ID = t2.ID   
    WHERE x = 50 AND y = 20   
    ORDER BY x ASC, y DESC, z   
"   
 


[<EntryPoint>]
let main argv = 
    printfn "%A" argv

    let lexbuf = Microsoft.FSharp.Text.Lexing.LexBuffer<_>.FromString x
    let y = Parser.start Lexer.tokenize lexbuf   
    printfn "%A" y   
 
    Console.WriteLine("(press any key)")   
    Console.ReadKey(true) |> ignore
    0 // return an integer exit code
