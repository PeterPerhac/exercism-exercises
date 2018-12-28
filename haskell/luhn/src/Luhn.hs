module Luhn (isValid) where

import Control.Monad (liftM2)
import Data.Char (isDigit, digitToInt)

isValid :: String -> Bool
isValid = liftM2 (&&) ((>1) . length . strip) luhn
  where
    strip = filter isDigit
    luhn = (0 == ) . (flip rem) 10 . sum . luhnTreatment . map digitToInt . strip
    luhnTreatment = map luhnDoubling . zip [0..] . reverse
    luhnDoubling (idx, n)
      | odd idx && n >  4 = 2*n - 9
      | odd idx && n <= 4 = 2*n
      | otherwise = n

