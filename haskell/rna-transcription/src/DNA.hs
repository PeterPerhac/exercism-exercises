module DNA (toRNA) where

import Control.Monad

toRNA :: String -> Either Char String
toRNA = join . fmap (map mapping) . return
  where mapping c = case c of 'G' -> Right 'C'
                              'C' -> Right 'G'
                              'T' -> Right 'A'
                              'A' -> Right 'U'
                              otherwise -> Left c
