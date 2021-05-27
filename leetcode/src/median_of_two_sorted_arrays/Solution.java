package median_of_two_sorted_arrays;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        final int[] s = nums1.length <= nums2.length ? nums1 : nums2; // the shorter array
        final int[] l = s == nums1 ? nums2 : nums1; // the longer array
        final int size = s.length + l.length;
        final boolean odd = (size % 2 != 0);

        int start = 0, end = s.length-1, mid = (size - 1) / 2;
        if (end < 0) { // one of the array is empty
            return odd ? l[mid] : (l[mid] + l[mid+1]) / 2.0;
        }
        if (s[0] >= l[mid]) { // no element in s is less than any element in l
            return odd ? l[mid] : (l[mid] + (mid == end ? s[0] : Math.min(l[mid+1], s[0]))) / 2.0;
        }
        mid = size / 2 - s.length;
        if (s[end] <= l[mid]) { // no element in s is larger than any element in l
            return odd ? l[mid] : (l[mid] + (mid == 0 ? s[end] : Math.max(l[mid-1], s[end]))) / 2.0;
        }
        for (;;) {
            mid = (end - start) / 2 + start;
            int index = (size-1) / 2 - mid - 1;
            if (l[index] <= s[mid] && l[index+1] >= s[mid]) {
                return odd ? s[mid] : (s[mid] + Math.min(s[mid+1], l[index+1])) / 2.0;
            }
            if (s[mid] <= l[index] && s[mid+1] >= l[index]) {
                return odd ? l[index] : (l[index] + Math.min(s[mid+1], l[index+1])) / 2.0;
            }
            if (l[index] > s[mid]) start = mid + 1;
            else end = mid - 1;
        }
    }
}
