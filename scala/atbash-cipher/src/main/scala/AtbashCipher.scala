object AtbashCipher{

  type Codec = Char => Char

  val alphabet = ('a' to 'z').toList
  val reverseAlphabet = alphabet.reverse

  val pos: List[Char] => Char => Int =
    charset => char => charset.indexOf(char)

  val letter: List[Char] => Int => Char =
    charset => pos => charset(pos)

  val codec: List[Char] => List[Char] => Codec =
    fromAlpha => toAlpha => pos(fromAlpha) andThen letter(toAlpha)

  val atbashCodec: Codec = codec(alphabet)(reverseAlphabet)

  val transcode: Codec => String => String =
    codec => input => input.toLowerCase.collect{
      case c if alphabet.contains(c) => codec(c)
      case n if Character.isDigit(n) => n
    }.mkString

  val group: Int => String => String =
    groupSize => in => in.grouped(groupSize).mkString(" ")

  val encode: String => String =
    transcode(atbashCodec) andThen group(5)

  val decode: String => String =
    transcode(atbashCodec)

}
