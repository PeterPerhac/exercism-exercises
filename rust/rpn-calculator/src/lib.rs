#[derive(Debug)]
pub enum CalculatorInput {
    Add,
    Subtract,
    Multiply,
    Divide,
    Value(i32),
}

pub fn evaluate(inputs: &[CalculatorInput]) -> Option<i32> {
    use CalculatorInput::*;

    let mut stack: Vec<i32> = Vec::new();

    for input in inputs {
        match input {
            Value(n) => stack.push(*n),
            op @ (Add | Subtract | Multiply | Divide) => {
                let b = stack.pop()?;
                let a = stack.pop()?;
                stack.push(match op {
                    Add => a + b,
                    Subtract => a - b,
                    Multiply => a * b,
                    Divide => a / b,
                    _ => panic!("can't happen")
                });
            }
        }
    }
    if stack.len() == 1 { stack.pop() } else { None }
}
