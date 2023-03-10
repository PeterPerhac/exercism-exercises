use enum_iterator::{all, Sequence};

#[derive(Debug, PartialEq, Eq, Sequence, Clone, Copy)]
pub enum ResistorColor {
    Black,
    Blue,
    Brown,
    Green,
    Grey,
    Orange,
    Red,
    Violet,
    White,
    Yellow,
}

pub fn color_to_value(color: ResistorColor) -> u32 {
    match color {
        ResistorColor::Black => 0,
        ResistorColor::Blue => 6,
        ResistorColor::Brown => 1,
        ResistorColor::Green => 5,
        ResistorColor::Grey => 8,
        ResistorColor::Orange => 3,
        ResistorColor::Red => 2,
        ResistorColor::Violet => 7,
        ResistorColor::White => 9,
        ResistorColor::Yellow => 4
    }
}

pub fn value_to_color_string(value: u32) -> String {
    let color = colors().iter().position(|rc| color_to_value(*rc) == value);
    match color {
        Some(idx) => format!("{:?}", colors().get(idx).unwrap()),
        None => String::from("value out of range")
    }
}

pub fn colors() -> Vec<ResistorColor> {
    let mut colors = all::<ResistorColor>().collect::<Vec<_>>();
    colors.sort_by(|&a, &b| color_to_value(a).cmp(&color_to_value(b)));
    colors
}
