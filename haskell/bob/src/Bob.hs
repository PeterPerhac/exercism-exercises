module Bob (responseFor) where

import Data.Char (isSpace, isAlpha, toUpper)
import Data.List (isPrefixOf)

responseFor :: String -> String
responseFor input
  | noInput input = "Fine. Be that way!"
  | isShouting input && isQuestion input = "Calm down, I know what I'm doing!"
  | isQuestion input = "Sure."
  | isShouting input = "Whoa, chill out!"
  | otherwise = "Whatever."
  where
    noInput = null . filter (not . isSpace)
    shoutedWord w = length w > 1 && map toUpper w == w && (not . elem w $ ["OK", "DMV"])
    isShouting = not . null . filter shoutedWord . words . filter (oneof [isAlpha, isSpace])
    isQuestion s = "?" `isPrefixOf` ((dropWhile isSpace) . reverse $ s)

oneof :: [(a -> Bool)] -> (a -> Bool)
oneof ps = \c -> foldl (\b p -> b || p c) False ps
