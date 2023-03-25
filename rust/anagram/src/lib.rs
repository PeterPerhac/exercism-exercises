use std::collections::HashSet;

pub fn anagrams_for<'a>(word: &str, possible_anagrams: &'a [&str]) -> HashSet<&'a str> {
    let mut res = HashSet::new();
    let mut sorted_word: Vec<char> = word.to_lowercase().chars().collect();
    sorted_word.sort_unstable();
    for option in possible_anagrams {
        if word.to_lowercase() == option.to_lowercase() { continue; }
        let mut sorted_option = option.to_lowercase().chars().collect::<Vec<char>>();
        sorted_option.sort_unstable();
        if sorted_option == sorted_word {
            res.insert(*option);
        }
    }
    res
}
