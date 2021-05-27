package median_of_two_sorted_arrays;

class Solution2 {
    public double findMedianSortedArrays(final int[] nums1, final int[] nums2) {
        final int n1d = nums1.length * 2, n2d = nums2.length * 2;
        if (n1d > n2d) return findMedianSortedArrays(nums2, nums1);	// ensure nums1 is shorter.

        for (int start = 0, end = n1d;;) {
            final int cut1 = (start + end) / 2; // binary cut nums1
            final int cut2 = (n1d + n2d) / 2 - cut1;  // corresponding cut point for nums2

            final int left1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[(cut1-1)/2];
            final int left2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[(cut2-1)/2];
            final int right1 = (cut1 == n1d) ? Integer.MAX_VALUE : nums1[cut1/2];
            final int right2 = (cut2 == n2d) ? Integer.MAX_VALUE : nums2[cut2/2];

            if (right1 < left2) start = cut1 + 1;    // nums1's right half is too small;
            else if (left1 > right2) end = cut1 - 1; // nums1's left half is too big;
            else return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
        }
    }
}
