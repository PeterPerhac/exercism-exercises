{-# LANGUAGE LambdaCase    #-}
module Bob (responseFor) where

import Data.Char (isSpace, isAlpha, toUpper)

data Interaction = None | ShoutingQuestions | Shouting | Questioning | General deriving (Show)

phrase :: Interaction -> String
phrase = \case
  None -> "Fine. Be that way!"
  ShoutingQuestions -> "Calm down, I know what I'm doing!"
  Shouting -> "Whoa, chill out!"
  Questioning -> "Sure."
  General -> "Whatever."

trim :: String -> String
trim = let f = reverse . dropWhile isSpace in f . f

mode :: String -> Interaction
mode input
  | noInput = None
  | isShouting input && isQuestion input = ShoutingQuestions
  | isQuestion input = Questioning
  | isShouting input = Shouting
  | otherwise = General
  where acronyms = ["OK", "DMV"]
        noInput = null . trim $ input
        shoutedWord w =
          let uppercased = map toUpper w in
            length w > 1 &&
            uppercased == w &&
            (not . elem uppercased $ acronyms)
        isShouting = not . null . (filter shoutedWord) . words . filter isAlpha
        isQuestion = ('?' == ) . last . trim

responseFor :: String -> String
responseFor = phrase . mode

