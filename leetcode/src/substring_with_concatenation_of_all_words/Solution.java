package substring_with_concatenation_of_all_words;

import java.util.*;

class Solution {
    private static class MultiSet {
        private int size = 0;
        private Map<String, Integer> map;
        public MultiSet(String[] words) {
            map = new HashMap<String, Integer>(words.length);
            for (var word : words) add(word);
        }
        public void add(String word) {
            var count = map.get(word);
            map.put(word, count == null ? 1 : count + 1);
            size++;
        }
        public boolean remove(String word) {
            var count = map.get(word);
            if (count != null && count > 0) {
                map.put(word, count - 1);
                size--;
                return true;
            }
            return false;
        }
        public boolean isEmpty() {
            return size == 0;
        }
    }

    public List<Integer> findSubstring(String s, String[] words) {
        var result = new ArrayList<Integer>();
        int count = words.length, len = words[0].length();
        var set = new MultiSet(words);
        var queue = new ArrayDeque<String>(count);
        for (int k = 0; k < len; k++) {
            while(!queue.isEmpty()) set.add(queue.remove());
            for (int i = k; i <= s.length() - len; i += len) {
                var e = s.substring(i, i+len);
                if (set.remove(e)) {
                    queue.add(e);
                    if (set.isEmpty()) {
                        result.add(i - (count - 1) * len);
                        set.add(queue.remove());
                    }
                } else {
                    while (!queue.isEmpty()) {
                        var word = queue.remove();
                        if (e.equals(word)) {
                            queue.add(e);
                            break;
                        }
                        set.add(word);
                    }
                }
            }
        }
        return result;
    }
}
