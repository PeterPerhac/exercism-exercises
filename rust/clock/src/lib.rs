use std::fmt::{Display, Formatter};

const MINUTES_PER_DAY: i32 = 24 * 60;

#[derive(Debug, PartialEq)]
pub struct Clock {
    hours: i32,
    minutes: i32,
}

impl Display for Clock {
    fn fmt(&self, f: &mut Formatter<'_>) -> std::fmt::Result {
        write!(f, "{:0>2}:{:0>2}", self.hours, self.minutes)
    }
}

impl Clock {
    fn to_minutes(self: &Self) -> i32 { self.hours * 60 + self.minutes }

    fn adjust_minutes(input: i32) -> i32 {
        let days = if input >= MINUTES_PER_DAY {
            input / MINUTES_PER_DAY
        } else {
            if input < 0 { (input / MINUTES_PER_DAY) - 1 } else { 0 }
        };
        input - (days * MINUTES_PER_DAY)
    }

    fn from_minutes(n: i32) -> Clock {
        let adjusted_minutes = Self::adjust_minutes(n);
        Clock { hours: adjusted_minutes / 60, minutes: adjusted_minutes % 60 }
    }

    pub fn new(hours: i32, minutes: i32) -> Self {
        Self::from_minutes(hours * 60 + minutes)
    }

    pub fn add_minutes(self: &Self, minutes: i32) -> Self {
        Self::from_minutes(self.to_minutes() + minutes)
    }
}
