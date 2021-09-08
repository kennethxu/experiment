package substring_with_concatenation_of_all_words;

import java.util.*;

class Solution2 {
    private static class Helper {
        private final int count;
        private final Integer DEFAULT;
        private final Map<String, Integer> indices;
        private final int[] set, queue;
        private int size = 0, head = 0, tail = 0;

        public Helper(String[] words) {
            DEFAULT = size = count = words.length;
            indices = new HashMap<>(count);
            set = new int[count + 1];
            queue = new int[count];
            int index = 0;
            for (var word : words) {
                Integer i = indices.putIfAbsent(word, index);
                set[i == null ? index++ : i]++;
            }
        }

        public int getIndex(String word) {
            return indices.getOrDefault(word, DEFAULT);
        }
        public void unmatch(int index) {
            set[index]++;
            size++;
        }

        public boolean match(int index) {
            if (set[index] > 0) {
                set[index]--;
                size--;
                return true;
            }
            return false;
        }

        public void slideIn(int index) {
            queue[tail++] = index;
            tail %= count;
        }

        public int slideOut() {
            var result = queue[head++];
            head %= count;
            return result;
        }

        public void reset() {
            while (tail != head) unmatch(slideOut());
        }

        public boolean isQueueEmpty() {
            return head == tail;
        }

        public boolean allMatched() {
            return size == 0;
        }
    }

    public List<Integer> findSubstring(String s, String[] words) {
        var result = new ArrayList<Integer>();
        int count = words.length, len = words[0].length();
        var helper = new Helper(words);
        for (int k = 0; k < len; k++) {
            helper.reset();
            for (int i = k; i <= s.length() - len; i += len) {
                var index = helper.getIndex(s.substring(i, i+len));
                if (helper.match(index)) {
                    helper.slideIn(index);
                    if (helper.allMatched()) {
                        result.add(i - (count - 1) * len);
                        helper.unmatch(helper.slideOut());
                    }
                } else {
                    while (!helper.isQueueEmpty()) {
                        var v = helper.slideOut();
                        if (v == index) {
                            helper.slideIn(index);
                            break;
                        }
                        helper.unmatch(v);
                    }
                }
            }
        }
        return result;
    }
}
