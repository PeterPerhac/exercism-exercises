module LeapYear (isLeapYear) where

isLeapYear :: Integer -> Bool
isLeapYear y = divisibleBy 4 && (divisibleBy 400 || not (divisibleBy 100))
              where divisibleBy = (0 == ) . rem y
