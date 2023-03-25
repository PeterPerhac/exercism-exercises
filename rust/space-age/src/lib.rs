#[derive(Debug)]
pub struct Duration {
    s: u64,
}

const EARTH_YEAR_SECONDS: u64 = 31_557_600;

impl From<u64> for Duration {
    fn from(s: u64) -> Self { Duration { s } }
}

pub trait Planet {
    fn years_during(d: &Duration) -> f64;
}

pub struct Mercury;
pub struct Venus;
pub struct Earth;
pub struct Mars;
pub struct Jupiter;
pub struct Saturn;
pub struct Uranus;
pub struct Neptune;

macro_rules! planet {
    (for $($t:ty)+, $($d:tt),+) => {
        $(impl Planet for $t {
            fn years_during(d: &Duration) -> f64 {
               (d.s as f64 / EARTH_YEAR_SECONDS as f64) / $d
            }
        })*
    }
}

planet!(for Mercury, 0.2408467);
planet!(for Venus, 0.61519726);
planet!(for Earth, 1.0);
planet!(for Mars, 1.8808158);
planet!(for Jupiter, 11.862615);
planet!(for Saturn, 29.447498);
planet!(for Uranus, 84.016846);
planet!(for Neptune, 164.79132);
