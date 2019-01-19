module HelloWorld where

import Prelude (identity, (<<<), (<>))
import Data.Maybe

helloWorld :: Maybe String -> String
helloWorld = ("Hello, " <> _ ) <<< ( _ <> "!") <<< (maybe "World" identity)

