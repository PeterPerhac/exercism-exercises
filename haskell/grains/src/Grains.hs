module Grains (square, total) where

import Control.Monad
import Data.Bits (shiftL)

square :: Int -> Maybe Integer
square = fmap (shiftL 1 . pred) . mfilter inRange . return
  where inRange = liftM2 (&&) (> 0) (<= 64)

total :: Integer
total = maybe 0 sum $ traverse square [1..64]

