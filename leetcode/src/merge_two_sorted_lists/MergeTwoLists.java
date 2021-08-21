package merge_two_sorted_lists;

import common.ListNode;
import org.junit.Test;

public class MergeTwoLists {
    @Test
    public void mergeTwoLists() {
        new Solution().mergeTwoLists(makeList(new int[]{1,2,4}), makeList(new int[]{1,3,4}));
    }

    private ListNode makeList(int[] a) {
        if (a == null ) return null;
        var head = new ListNode();
        var tail = head;
        for(var n : a) {
            tail.next = new ListNode(n);
            tail = tail.next;
        }
        return head.next;
    }
}
