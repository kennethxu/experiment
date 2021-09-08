package verify_preorder_serialization_of_a_binary_tree;

class Solution {
    public boolean isValidSerialization(String preorder) {
        return isValid(preorder, 0) >= preorder.length();
    }

    int isValid(String s, int index) {
        while(index < s.length()) {
            var c = s.charAt(index++);
            if (c == '#') return ++index;
            if (c == ',') {
                if ((index = isValid(s, index)) < 0) return -1;
                return isValid(s, index);
            }
        }
        return -1;
    }
}
