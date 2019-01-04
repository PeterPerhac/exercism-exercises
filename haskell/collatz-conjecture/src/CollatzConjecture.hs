module CollatzConjecture (collatz) where

import Control.Monad (mfilter)
import Data.List (genericLength)

collatz :: Integer -> Maybe Integer
collatz = fmap (genericLength . takeWhile (/=1) . iterate step) . mfilter (>0) . Just
  where step n
           | even n = n `quot` 2
           | otherwise = 3*n + 1

