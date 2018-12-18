import java.time.LocalDate

import monocle.Iso
import monocle.macros.Lenses

object LensPerson {

  @Lenses
  case class Person(_name: Name, _born: Born, _address: Address)

  case class Name(_foreNames: String /*Space separated*/, _surName: String)

  type EpochDay = Long

  @Lenses
  case class Born(_bornAt: Address, _bornOn: EpochDay)

  @Lenses
  case class Address(_street: String, _houseNumber: Int, _place: String /*Village / city*/, _country: String)

  @Lenses
  case class Gregorian(_year: Int, _month: Int, _dayOfMonth: Int)

  import Address._
  import Born._
  import Gregorian._
  import Person._

  val toEpoch: Gregorian => EpochDay = g => LocalDate.of(g._year, g._month, g._dayOfMonth).toEpochDay
  val fromEpoch: EpochDay => Gregorian = { ed =>
    val ld = LocalDate.ofEpochDay(ed)
    Gregorian(ld.getYear, ld.getMonthValue, ld.getDayOfMonth)
  }

  val gregorianIso: Iso[EpochDay, Gregorian] = Iso(fromEpoch)(toEpoch)

  val bornStreet: Born => String =
    _bornAt ^|-> _street get _

  val setCurrentStreet: String => Person => Person =
    _address ^|-> _street set _

  val setBirthMonth: Int => Person => Person =
    _born ^|-> _bornOn ^<-> gregorianIso ^|-> _month set _

  val renameStreets: (String => String) => Person => Person = f =>
    (_address ^|-> _street modify f) andThen (_born ^|-> _bornAt ^|-> _street modify f)

}
