module Leap where

import Prelude

isLeapYear :: Int -> Boolean
isLeapYear y = divisibleBy 4 && (divisibleBy 400 || not (divisibleBy 100))
  where divisibleBy = (0 == _) <<< (y `mod` _)
