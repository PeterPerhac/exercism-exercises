module RunLength (decode, encode) where

import Data.Char
import Control.Monad
import Data.List
import Data.List.Split
import Text.Read

decode :: String -> String
decode = concat . (map explode) . (split splitter)
    where splitter = dropFinalBlank . keepDelimsR $ whenElt (flip elem $ supportedChars)
          explode = join . liftM2 replicate times letter
          times = maybe 1 id . readMaybe . takeWhile isDigit
          letter = dropWhile isDigit
          supportedChars = ' ' : ['a'..'z'] ++ ['A'..'Z']

encode :: String -> String
encode = concat . map implode . liftM2 zip (map $ show . length) (map nub) . group
  where implode ("1", letter) = letter
        implode (num, letter) = num ++ letter
