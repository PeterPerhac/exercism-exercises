{-# LANGUAGE TemplateHaskell #-}

module Person
  ( Address (..)
  , Born    (..)
  , Name    (..)
  , Person  (..)
  , bornStreet
  , renameStreets
  , setBirthMonth
  , setCurrentStreet
  ) where

import Control.Lens
import Control.Applicative (liftA2)
import Data.Time.Calendar

data Person = Person { _name    :: Name
                     , _born    :: Born
                     , _address :: Address
                     }

data Name = Name { _foreNames :: String
                 , _surName   :: String
                 }

data Born = Born { _bornAt :: Address
                 , _bornOn :: Day
                 }

data Address = Address { _street      :: String
                       , _houseNumber :: Int
                       , _place       :: String
                       , _country     :: String
                       }

data Gregorian = Gregorian { _year  :: Integer
                           , _month :: Int
                           , _day   :: Int
                           }

makeLenses ''Person
makeLenses ''Name
makeLenses ''Born
makeLenses ''Address
makeLenses ''Gregorian

dayiso :: Iso' Day Gregorian
dayiso = iso ((\(y, m, d) -> Gregorian y m d) . toGregorian) (\(Gregorian y m d) -> fromGregorian y m d)

bornStreet :: Born -> String
bornStreet = view (bornAt . street)

setCurrentStreet :: String -> Person -> Person
setCurrentStreet = set (address . street)

setBirthMonth :: Int -> Person -> Person
setBirthMonth = set ( born . bornOn . dayiso . month)

renameStreets :: (String -> String) -> Person -> Person
renameStreets = liftA2 (.) (over (born . bornAt . street)) (over (address . street))
