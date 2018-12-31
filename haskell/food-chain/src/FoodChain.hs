{-# LANGUAGE RecordWildCards #-}
module FoodChain (song) where

import Data.Maybe (catMaybes)

data AnimalVerse = AnimalVerse {
                   animal :: String
                 , exclamation       :: Maybe String
                 , swallowedEnding   :: Maybe String
                 }

song :: String
song = concatMap stanza aAndPrev
  where
    aAndPrev = animals `zip` (Nothing : ((map Just . map animal) animals))
    sheSwallowed = (++ ".") . (++) "I know an old lady who swallowed a "
    toCatchTheOther ending a prev = "She swallowed the " ++ a ++ " to catch the " ++ prev ++ maybe "." id ending
    perhapsShellDie = "I don't know why she swallowed the fly. Perhaps she'll die."
    catchingLine ending a prev = (toCatchTheOther ending a) <$> prev
    stanza (AnimalVerse{..}, prev) = unlines $ catMaybes [Just $ sheSwallowed animal, exclamation, catchingLine swallowedEnding animal prev, Just perhapsShellDie, Just ""]

animals = [
    AnimalVerse {
      animal = "fly"
    , exclamation = Nothing
    , swallowedEnding = Nothing
    },
    AnimalVerse {
      animal = "spider"
    , exclamation = Just "It wriggled and jiggled and tickled inside her."
    , swallowedEnding = Nothing
    },
    AnimalVerse {
      animal = "bird"
    , exclamation = Just "How absurd to swallow a bird!"
    , swallowedEnding = Just " that wriggled and jiggled and tickled inside her."
    },
    AnimalVerse {
      animal = "cat"
    , exclamation = Just "Imagine that, to swallow a cat!"
    , swallowedEnding = Nothing
    },
    AnimalVerse {
      animal = "dog"
    , exclamation = Just "What a hog, to swallow a dog!"
    , swallowedEnding = Nothing
    },
    AnimalVerse {
      animal = "goat"
    , exclamation = Just "Just opened her throat and swallowed a goat!"
    , swallowedEnding = Nothing
    },
    AnimalVerse {
      animal = "cow"
    , exclamation = Just "I don't know how she swallowed a cow!"
    , swallowedEnding = Nothing
    },
    AnimalVerse {
      animal = "horse"
    , exclamation = Just "She's dead, of course!"
    , swallowedEnding = Nothing
    }
    ]
