{-# LANGUAGE LambdaCase                #-}
module Bob (responseFor) where

import Data.Char (isSpace, toUpper)

data Interaction = None | ShoutingQuestions | Shouting | Questioning | General

phrase :: Interaction -> String
phrase = \case
  None -> "Fine. Be that way!"
  ShoutingQuestions -> "Calm down, I know what I'm doing!"
  Shouting -> "Whoa, chill out!"
  Questioning -> "Sure."
  General -> "Whatever."

trim :: String -> String
trim = let f = reverse . dropWhile isSpace in f . f

interactionType :: String -> Interaction
interactionType input
  | null input = None
  | isShouting s && isQuestion s = ShoutingQuestions
  | isQuestion s = Questioning
  | isShouting s = Shouting
  | otherwise = General
  where acronyms = ["OK", "DMV"]
        s = trim input
        up = map toUpper s
        shouting w = length w > 1 && up == w && not (elem w acronyms)
        isShouting = not . null . (filter shouting) . words
        isQuestion = ('?' == ) . last

responseFor :: String -> String
responseFor = phrase . interactionType

{-
  my scala solution

 object Bob {
  def response(statement: String): String = {
    val s = statement.trim
    val acronyms = Set("OK", "DMV")
    val isShouting = s
      .split("[^a-zA-Z]+")
      .filter { word =>
        word.toUpperCase == word && word.length > 1 && !acronyms.contains(word.toUpperCase)
      }
      .nonEmpty
    val isQuestion = s.endsWith("?")
    (isQuestion, isShouting) match {
      case (_, _) if s.isEmpty => "Fine. Be that way!"
      case (true, true)        => "Calm down, I know what I'm doing!"
      case (true, false)       => "Sure."
      case (false, true)       => "Whoa, chill out!"
      case _                   => "Whatever."
    }
  }
}
-}
