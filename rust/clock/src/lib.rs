use std::fmt::{Display, Formatter};

const MINUTES_PER_DAY: i32 = 24 * 60;

#[derive(Debug, PartialEq)]
pub struct Clock {
    minutes: i32,
}

impl Display for Clock {
    fn fmt(&self, f: &mut Formatter<'_>) -> std::fmt::Result {
        write!(f, "{:0>2}:{:0>2}", self.minutes / 60, self.minutes % 60)
    }
}

impl Clock {
    fn adjust_minutes(input: i32) -> i32 {
        let days = if input >= MINUTES_PER_DAY {
            input / MINUTES_PER_DAY
        } else {
            if input < 0 { (input / MINUTES_PER_DAY) - 1 } else { 0 }
        };
        input - (days * MINUTES_PER_DAY)
    }

    pub fn new(hours: i32, minutes: i32) -> Self {
        Clock { minutes: Self::adjust_minutes(hours * 60 + minutes) }
    }

    pub fn add_minutes(self: &Self, minutes: i32) -> Self {
        Clock { minutes: Self::adjust_minutes(self.minutes + minutes) }
    }
}
