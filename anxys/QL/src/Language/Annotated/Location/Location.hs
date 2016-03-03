module Location (
    Location(Location),
    Position(Position),
    startLine,
    startColumn,
    endLine,
    endColumn,
    ) where

data Position = Position { line :: Int, column :: Int }
  deriving (Eq, Show)

data Location = Location { start :: Position, end :: Position }
  deriving (Eq, Show)

startLine :: Location -> Int
startLine loc = line $ start loc

startColumn :: Location -> Int
startColumn loc = column $ start loc

endLine :: Location -> Int
endLine loc = line $ end loc

endColumn :: Location -> Int
endColumn loc = column $ end loc
