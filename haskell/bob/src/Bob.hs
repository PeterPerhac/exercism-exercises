module Bob (responseFor) where

import Data.Char (isSpace, isLower, isUpper)
import Data.List (isPrefixOf)

responseFor :: String -> String
responseFor s
  | null . clean $ s = "Fine. Be that way!"
  | shouting s && asking s = "Calm down, I know what I'm doing!"
  | asking s = "Sure."
  | shouting s = "Whoa, chill out!"
  | otherwise = "Whatever."
  where
    clean = reverse . filter (not . isSpace)
    asking = (isPrefixOf "?") . clean
    shouting str = let exists = flip any $ str in (exists isUpper) && (not $ exists isLower)
