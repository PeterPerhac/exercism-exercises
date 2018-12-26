module Pangram (isPangram) where

import Data.List
import Data.Char (toLower)

isPangram :: String -> Bool
isPangram = checkPangram []

checkPangram :: [Char] -> String -> Bool
checkPangram chars text = (null $ ['a'..'z'] \\ chars) || case text of
  []  -> False
  h:t -> checkPangram (toLower h : chars) t

