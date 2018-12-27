module Pangram (isPangram) where

import Data.List
import Data.Char (toLower)

isPangram :: String -> Bool
isPangram = null . (\\) ['a'..'z'] . map toLower
