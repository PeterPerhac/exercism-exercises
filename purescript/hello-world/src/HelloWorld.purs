module HelloWorld where

helloWorld :: Maybe String -> String
helloWorld = (++ "!") . ("Hello, " ++) . maybe "World" id

