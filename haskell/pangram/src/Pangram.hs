module Pangram (isPangram) where

import Data.List (nub, sort)
import Data.Char (isAlpha, toLower)

isPangram :: String -> Bool
isPangram = (==) ['a'..'z'] . sort . filter isAlpha . nub . map toLower

