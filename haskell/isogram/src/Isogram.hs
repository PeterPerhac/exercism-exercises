module Isogram (isIsogram) where

import Data.Char (isLetter, toLower)

isIsogram :: String -> Bool
isIsogram = f [] . map toLower . filter isLetter
  where
    f _ [] = True
    f seen (h:t)
      | elem h seen = False
      | otherwise = f (h:seen) t

