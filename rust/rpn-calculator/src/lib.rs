#[derive(Debug)]
pub enum CalculatorInput {
    Add,
    Subtract,
    Multiply,
    Divide,
    Value(i32),
}

pub fn evaluate(inputs: &[CalculatorInput]) -> Option<i32> {
    let mut stack: Vec<i32> = Vec::new();

    fn calc(stack: &mut Vec<i32>, op: fn(i32, i32) -> i32) -> Option<()> {
        let b = stack.pop()?;
        let a = stack.pop()?;
        stack.push(op(a, b));
        Some(())
    }

    for input in inputs {
        match input {
            CalculatorInput::Value(n) => stack.push(*n),
            CalculatorInput::Add => calc(&mut stack, std::ops::Add::add)?,
            CalculatorInput::Subtract => calc(&mut stack, std::ops::Sub::sub)?,
            CalculatorInput::Multiply => calc(&mut stack, std::ops::Mul::mul)?,
            CalculatorInput::Divide => calc(&mut stack, std::ops::Div::div)?,
        }
    }
    if stack.len() == 1 { stack.pop() } else { None }
}
