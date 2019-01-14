module DNA (nucleotideCounts, Nucleotide(..)) where

import Data.Map (Map, fromListWith)
import Text.Read (readMaybe)
import Control.Monad

data Nucleotide = A | C | G | T deriving (Eq, Ord, Show, Enum, Read)

nucleotideCounts :: String -> Either String (Map Nucleotide Int)
nucleotideCounts = fmap (fromListWith (+) . (startingPoint ++ ) . map (\c -> (c, 1))) . traverse readIt
  where startingPoint = map (\c -> (c, 0)) [A ..]
        readIt = ap (flip maybe Right . Left . return) readNucleotide

readNucleotide :: Char -> Maybe Nucleotide
readNucleotide = readMaybe . return
