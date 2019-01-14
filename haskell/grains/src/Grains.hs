module Grains (square, total) where

import Control.Monad (mfilter)

square :: Int -> Maybe Integer
square = fmap (squares !!) . mfilter (flip elem [0..63]) . return . pred

total :: Integer
total = sum $ take 64 squares

squares :: [Integer]
squares = iterate (2*) 1
