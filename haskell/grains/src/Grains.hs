module Grains (square, total) where

import Control.Monad
import Data.Bits (shiftL)
import Data.Maybe (mapMaybe)


square :: Int -> Maybe Integer
square = fmap (shiftL 1 . pred) . mfilter inRange . return
  where inRange = liftM2 (&&) (> 0) (<= 64)

total :: Integer
total = sum . mapMaybe square $ [1..64]

