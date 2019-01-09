module SumOfMultiples (sumOfMultiples) where

sumOfMultiples :: [Integer] -> Integer -> Integer
sumOfMultiples factors limit = (sum . filter f) [1..limit-1]
  where f n = any ((0 ==) . rem n) $  filter (0 /=) factors
