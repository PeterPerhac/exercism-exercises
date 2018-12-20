module Queens (boardString, canAttack) where

boardString :: Maybe (Int, Int) -> Maybe (Int, Int) -> String
boardString white black =
  do row <- [1..8]
     col <- [1..9]
     return c col row
  where c :: Int -> Int -> Char
        c 9 y = '\n'
        c x y = '-'

canAttack :: (Int, Int) -> (Int, Int) -> Bool
canAttack queenA queenB = error "You need to implement this function."
