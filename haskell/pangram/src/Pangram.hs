module Pangram (isPangram) where

import Data.List
import Data.Char (isAlpha, toLower)


isPangram :: String -> Bool
isPangram = checkPangram []

checkPangram :: [Char] -> String -> Bool
checkPangram chars text = (null $ ['a'..'z'] \\ chars) || case text of
    [] -> False
    h:t -> checkPangram (union chars (filter isAlpha . map toLower $ [h])) t

