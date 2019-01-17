module Grains (square, total) where

import Control.Monad (mfilter)
import Data.Bits (shiftL)

square :: Int -> Maybe Integer
square = fmap (shiftL 1 . pred) . validateInput . return
  where validateInput = mfilter (flip elem [1..64])

total :: Integer
total = maybe 0 sum $ traverse square [1..64]

