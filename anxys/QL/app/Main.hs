module Main where

import Lib
import Graphics.UI.WX

main :: IO ()
main = start gui

gui :: IO ()
gui = do
  frame [text := "Goodbye World!"]
  return ()
