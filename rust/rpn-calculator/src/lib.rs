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

    let mut stack: Vec<i32> = vec![];

    for input in inputs {
        match input {
            Add => {
                if let (Some(b), Some(a)) = (stack.pop(), stack.pop()) {
                    stack.push(a + b);
                } else { return None; }
            }
            Subtract => {
                if let (Some(b), Some(a)) = (stack.pop(), stack.pop()) {
                    stack.push(a - b);
                } else { return None; }
            }
            Multiply => {
                if let (Some(b), Some(a)) = (stack.pop(), stack.pop()) {
                    stack.push(a * b);
                } else { return None; }
            }
            Divide => {
                if let (Some(b), Some(a)) = (stack.pop(), stack.pop()) {
                    stack.push(a / b);
                } else { return None; }
            }
            Value(n) => stack.push(*n)
        }
    }

    if stack.len() == 1 { stack.pop() } else { None }
}
