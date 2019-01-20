module BinarySearch
  ( find
  ) where

import Prelude
import Data.Maybe
import Data.Int (quot)
import Data.Array (length)


find :: Int -> Array Int -> Maybe Int
find x xs = find' x xs (length xs `quot` 2)

find' :: Int -> Array Int -> Int -> Maybe Int
find' x [] _ = Nothing
find' x xs idx
  | [x] == xs = Just idx
  | otherwise = Just idx


