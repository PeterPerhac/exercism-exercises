object Twofer {
  def twofer(name: String = ""): String = {
    val theName = name match {
      case empty if name.trim().isEmpty => "you"
      case theName => theName
    }

    s"One for $theName, one for me."
  }
}
