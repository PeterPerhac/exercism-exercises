-- first basic implementation that doesn't pass the last test
isPangram :: String -> Bool
isPangram = (==) ['a'..'z'] . sort . filter isAlpha . nub . map toLower

