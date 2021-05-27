package median_of_two_sorted_arrays;

class Solution {
    public double findMedianSortedArrays(final int[] nums1, final int[] nums2) {
        final int n1 = nums1.length, n2 = nums2.length;
        if (n1 > n2) return findMedianSortedArrays(nums2, nums1); // ensure nums1 is shorter;

        for (int start = 0, end = n1;;) { // initail range is [start, end)
            // binary cut nums1 to [start, cut1) and [cut1, end)
            final int cut1 = (end + start) / 2;
            // cut nums2 so that elements count nums1[<cut1] + nums2[<cut2) is  the smaller (when odd) half of total.
            final int cut2 = (n1 + n2) / 2 - cut1;

            final int left1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1-1];
            final int left2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2-1];
            final int right1 = cut1 == n1 ? Integer.MAX_VALUE : nums1[cut1];
            final int right2 = cut2 == n2 ? Integer.MAX_VALUE : nums2[cut2];

            if (left1 > right2) end = cut1 -1 ; // nums1's left half is too big, iterate to the left
            else if (right1 < left2) start = cut1 + 1; //nums1's right half is too small, iterate to the right
            else return ((n1 + n2) % 2 == 0) ?
                        (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0 :
                        Math.min(right1, right2); // if odd, the smallest of the right should be the median.
        }
    }
}
