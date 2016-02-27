module Location (
    Location(Location),
    startLine,
    startColumn,
    endLine,
    endColumn,
    ) where

import           Text.ParserCombinators.Parsec.Pos as P

data Location = Location { start :: SourcePos, end :: SourcePos }
  deriving (Eq, Show)

line :: SourcePos -> Int
line = P.sourceLine

column :: SourcePos -> Int
column = P.sourceColumn

startLine :: Location -> Int
startLine loc = line $ start loc

startColumn :: Location -> Int
startColumn loc = column $ start loc

endLine :: Location -> Int
endLine loc = line $ end loc

endColumn :: Location -> Int
endColumn loc = column $ end loc
