package valid_parentheses;

public class Solution {
    public boolean isValid(String s) {
        char[] stack = new char[s.length()];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack[count++] = c;
                    break;
                case ')':
                case ']':
                case '}':
                    if (count == 0 || Math.abs(c - stack[--count]) > 2) return false;
                    break;
            }
        }
        return count == 0;
    }
}
