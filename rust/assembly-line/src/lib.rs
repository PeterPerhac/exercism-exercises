pub fn production_rate_per_hour(speed: u8) -> f64 {
    let success_rate: f64 = match speed {
        0_u8 => 0.0,
        1..=4 => 1.0,
        5..=8 => 0.9,
        9..=10 => 0.77,
        11_u8..=u8::MAX => todo!(),
    };
    ((speed as u64 * 221) as f64) * success_rate
}

pub fn working_items_per_minute(speed: u8) -> u32 {
    (production_rate_per_hour(speed) / 60.0) as u32
}
