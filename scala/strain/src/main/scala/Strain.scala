object Strain{

  def keep[A](as: Seq[A], f: A => Boolean): Seq[A] =
    as.foldRight(Seq.empty[A])((e,acc) => if (f(e)) e +: acc else acc)

  def discard[A](as: Seq[A], f: A => Boolean): Seq[A] =
    keep(as,  (a:A) => !f(a))

}
