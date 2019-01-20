module RNATranscription (
 toRNA
) where

import Prelude ((<$>), (<<<))
import Data.Maybe
import Data.Traversable (traverse)
import Data.String.CodeUnits (fromCharArray, toCharArray)

toRNA :: String -> Maybe String
toRNA = (fromCharArray <$> _) <<< (traverse transcribe) <<< toCharArray
  where transcribe = case _ of
                         'G' -> Just 'C'
                         'C' -> Just 'G'
                         'T' -> Just 'A'
                         'A' -> Just 'U'
                         c   -> Nothing
