pub fn find(array: &[i32], key: i32) -> Option<usize> {
    fn recurse(from: usize, to: usize, a: &[i32], k: i32) -> Option<usize> {
        if from == to {
            Some(from).filter(|_| a[from] == k)
        } else {
            let mid_index = (from + to) / 2;
            let mid_value = a[mid_index];
            if mid_value == k {
                Some(mid_index)
            } else {
                recurse(
                    if mid_value < k { mid_index + 1 } else { from },
                    if mid_value < k { to } else { mid_index },
                    a,
                    k,
                )
            }
        }
    }
    if array.is_empty() { return None; }
    recurse(0, array.len() - 1, array, key)
}
