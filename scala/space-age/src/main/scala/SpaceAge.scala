object SpaceAge {

val onEarthOrbit: Double = 1.0
val onMercuryOrbit: Double = 0.2408467
val onVenusOrbit: Double = 0.61519726
val onMarsOrbit: Double = 1.8808158
val onJupiterOrbit: Double = 11.862615
val onSaturnOrbit: Double = 29.447498
val onUranusOrbit: Double = 84.016846
val onNeptuneOrbit: Double = 164.79132

val earthYearInSeconds = 31557600

private def spaceAge(k: Double)(secs: Double): Double = secs / (k * earthYearInSeconds)

val onEarth: Double => Double = spaceAge(onEarthOrbit)
val onMercury: Double => Double = spaceAge(onMercuryOrbit)
val onVenus: Double => Double = spaceAge(onVenusOrbit)
val onMars: Double => Double = spaceAge(onMarsOrbit)
val onJupiter: Double => Double = spaceAge(onJupiterOrbit)
val onSaturn: Double => Double = spaceAge(onSaturnOrbit)
val onUranus: Double => Double = spaceAge(onUranusOrbit)
val onNeptune: Double => Double = spaceAge(onNeptuneOrbit)

}
