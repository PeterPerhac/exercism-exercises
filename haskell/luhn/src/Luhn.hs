module Luhn (isValid) where

import Data.Char (isDigit, digitToInt)

isValid :: String -> Bool
isValid s
  | (<2) . length . strip $ s = False
  | otherwise = luhn s
  where
    strip = filter isDigit
    luhn = (0 == ) . (flip rem) 10 . sum . luhnTreatment . map digitToInt . strip
    luhnTreatment = map luhnDoubling . zip [0..] . reverse
    luhnDoubling (idx, n)
      | odd idx && n >  4 = 2*n - 9
      | odd idx && n <= 4 = 2*n
      | otherwise = n

