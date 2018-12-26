module IsbnVerifier (isbn) where

import Data.Maybe
import Data.List
import Data.Char (isDigit, digitToInt)

isbn :: String -> Bool
isbn = validIsbn . catMaybes . map charMapping
  where charMapping 'X' = Just 10
        charMapping c
         | isDigit c = Just $ digitToInt c
         | otherwise = Nothing

validIsbn :: [Int] -> Bool
validIsbn ds = checkDigitValid ds && validIsbnLength ds
  where validIsbnLength digits = case length digits of
          10 -> divisibleBy 11 . sum . checksum $ ds
          _  -> False
        checksum digits = let mult (n, rank) = (10-rank) * n in
          map mult $ zip [10,9..] digits
        checkDigitValid digits = let idx = elemIndex 10 digits in
          elem idx [Nothing, Just 9]
        divisibleBy d = (0 ==) . flip rem d

