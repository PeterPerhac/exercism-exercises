module DNA (nucleotideCounts, Nucleotide(..)) where

import Data.Map (Map, fromList)
import Text.Read (readMaybe)

data Nucleotide = A | C | G | T deriving (Eq, Ord, Show, Enum, Read)

nucleotideCounts :: String -> Either String (Map Nucleotide Int)
nucleotideCounts = fmap fromList . (fmap ((startingPoint ++ ) . map (\c -> (c, 1)))) . traverse readNucleotide
  where startingPoint = map (\c -> (c, 0)) [A ..]
        readNucleotide c = maybe (Left [c]) Right (readMaybe [c]::Maybe Nucleotide)
