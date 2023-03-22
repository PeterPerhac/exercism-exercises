use std::collections::HashMap;

pub fn can_construct_note(magazine: &[&str], note: &[&str]) -> bool {
    let mut m_hash: HashMap<&&str, i32> = HashMap::new();
    for w in magazine { *m_hash.entry(w).or_default() += 1; }
    let mut n_hash: HashMap<&&str, i32> = HashMap::new();
    for w in note { *n_hash.entry(w).or_default() += 1; }
    n_hash.iter().all(|(w, c)| m_hash.entry(w).or_default() >= &mut c.clone())
}
