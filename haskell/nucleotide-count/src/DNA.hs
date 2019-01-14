{-# LANGUAGE TupleSections #-}
module DNA (nucleotideCounts, Nucleotide(..)) where

import Data.Map (Map, fromListWith)
import Text.Read (readEither)

data Nucleotide = A | C | G | T deriving (Eq, Ord, Show, Enum, Bounded, Read)

nucleotideCounts :: String -> Either String (Map Nucleotide Int)
nucleotideCounts = fmap (fromListWith (+) . (startingPoint ++ ) . map ((, 1))) . traverse readNucleotide
  where startingPoint = map ((, 0)) [minBound::Nucleotide ..]
        readNucleotide = readEither . return
