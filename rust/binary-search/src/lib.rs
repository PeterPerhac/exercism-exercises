use tailcall::tailcall;

pub fn find(array: &[i32], key: i32) -> Option<usize> {
    #[tailcall]
    fn recurse(from: usize, to: usize, a: &[i32], k: i32) -> Option<usize> {
        if to == from {
            if a[from] == k { Some(from) } else { None }
        } else {
            let mid = (from + to) / 2;
            let mid_value = a[mid];
            if mid_value == k {
                Some(mid)
            } else {
                recurse(
                    if mid_value < k { mid + 1 } else { from },
                    if mid_value < k { to } else { mid },
                    a,
                    k,
                )
            }
        }
    }
    if array.is_empty() { return None; }
    recurse(0, array.len() - 1, array, key)
}
