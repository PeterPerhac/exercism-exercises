module Isogram (isIsogram) where

import Data.Char (isLetter, toLower)

isIsogram :: String -> Bool
isIsogram = fst . foldl f (True, []) . map toLower . filter isLetter
  where f (b, acc) c = (b && (not . elem c) acc, c : acc)
