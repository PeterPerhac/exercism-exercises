module CollatzConjecture (collatz) where

import Control.Monad (mfilter)

collatz :: Integer -> Maybe Integer
collatz = fmap steps . mfilter (>0) . Just
  where steps 1 = 0
        steps n
          | even n = 1 + steps (n `quot` 2)
          | otherwise = 1 + steps(3*n + 1)

