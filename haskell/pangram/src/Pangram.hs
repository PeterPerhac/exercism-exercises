module Pangram (isPangram) where

import Data.List
import Data.Char (toLower)

isPangram :: String -> Bool
isPangram text = any (null . (\\) ['a'..'z']) ((map . map) toLower $ inits text)
