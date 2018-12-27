module CollatzConjecture (collatz) where

import Control.Monad (mfilter)

collatz :: Integer -> Maybe Integer
collatz = fmap (steps 0) . mfilter (>0) . Just
  where
    steps n 1 = n
    steps n num
      | even num = steps(n+1)(num `div` 2)
      | otherwise = steps(n+1)(3*num + 1)

