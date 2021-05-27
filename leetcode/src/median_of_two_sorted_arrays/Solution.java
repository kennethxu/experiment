package median_of_two_sorted_arrays;

class Solution {
    public double findMedianSortedArrays(final int[] nums1, final int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1); // ensure nums1 is shorter;

        final int n = nums1.length + nums2.length;
        final int r1 = nums1.length - 1, r2 = nums2.length - 1;

        for (int start = 0, end = r1;;) {
            int mid1 = (end + start + 2) / 2 - 1;
            int mid2 = (n - 1) / 2 - mid1 - 1;

            int left1 = mid1 < 0 ? Integer.MIN_VALUE : nums1[mid1];
            int left2 = mid2 < 0 ? Integer.MIN_VALUE : nums2[mid2];
            int right1 = mid1 == r1 ? Integer.MAX_VALUE : nums1[mid1 + 1];
            int right2 = mid2 == r2 ? Integer.MAX_VALUE : nums2[mid2 + 1];

            if (left1 > right2) end = mid1 - 1;
            else if (right1 < left2) start = mid1 + 1;
            else return (n % 2 != 0) ?
                        Math.max(left1, left2) :
                        (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
        }
    }
}
