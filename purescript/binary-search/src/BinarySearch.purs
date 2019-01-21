module BinarySearch
  ( find
  ) where

import Prelude
import Data.Maybe
import Data.Int
import Data.Array

find :: Int -> Array Int -> Maybe Int
find x xs = search 0 (length xs - 1) x xs

search :: Int -> Int -> Int -> Array Int -> Maybe Int
search _ _ _ [] = Nothing
search l u x xs = let mid = ( (u - l) `quot` 2 ) in
                   if (maybe false (_ == x) (xs !! mid)) then Just mid else Nothing

