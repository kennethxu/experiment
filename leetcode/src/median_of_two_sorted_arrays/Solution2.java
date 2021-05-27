package median_of_two_sorted_arrays;

class Solution2 {
    public double findMedianSortedArrays(final int[] nums1, final int[] nums2) {
        final int n1d = nums1.length * 2;
        final int n2d = nums2.length * 2;
        if (n1d > n2d) return findMedianSortedArrays(nums2, nums1);	// ensure nums1 is shorter.

        final int n = (n1d + n2d) / 2;
        int start = 0, end = n1d;
        for (;;) {
            int mid1 = (start + end) / 2; // binary cut nums1
            int mid2 = n - mid1;  // corresponding cut point for nums2

            int left1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1-1)/2];
            int left2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2-1)/2];
            int right1 = (mid1 == n1d) ? Integer.MAX_VALUE : nums1[mid1/2];
            int right2 = (mid2 == n2d) ? Integer.MAX_VALUE : nums2[mid2/2];

            if (right1 < left2) start = mid1 + 1;    // nums1's upper half is too small;
            else if (left1 > right2) end = mid1 - 1; // nums1's lower half is too big;
            else return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
        }
    }
}
