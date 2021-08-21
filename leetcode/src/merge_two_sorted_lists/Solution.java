package merge_two_sorted_lists;

import common.ListNode;

public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val > l2.val) return mergeTwoLists(l2, l1);

        var head = l1;
        var tail = head;
        for(;;) {
            for(;l1.val <= l2.val;) {
                tail = l1;
                l1 = l1.next;
                if (l1 == null) {tail.next = l2; return head;}
            }
            var temp = l1;
            l1 = l2;
            l2 = temp;
            tail.next = l1;
        }
    }
}
