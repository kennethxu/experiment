package median_of_two_sorted_arrays;

class Solution {
    public double findMedianSortedArrays(final int[] nums1, final int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1); // ensure nums1 is shorter;

        final int n = nums1.length + nums2.length;
        final boolean odd = (n % 2 != 0);

        int start = 0, end = nums1.length-1, mid = (n - 1) / 2;
        if (end < 0) { // one of the array is empty
            return odd ? nums2[mid] : (nums2[mid] + nums2[mid+1]) / 2.0;
        }
        if (nums1[0] >= nums2[mid]) { // no element in nums1 is less than any element in nums2
            return odd ? nums2[mid] : (nums2[mid] + (mid == end ? nums1[0] : Math.min(nums2[mid+1], nums1[0]))) / 2.0;
        }
        mid = n / 2 - nums1.length;
        if (nums1[end] <= nums2[mid]) { // no element in nums1 is larger than any element in nums2
            return odd ? nums2[mid] : (nums2[mid] + (mid == 0 ? nums1[end] : Math.max(nums2[mid-1], nums1[end]))) / 2.0;
        }
        for (;;) {
            int mid1 = (end + start) / 2;
            int mid2 = (n - 1) / 2 - mid1 - 1;
            if (nums2[mid2] <= nums1[mid1] && nums2[mid2+1] >= nums1[mid1]) {
                return odd ? nums1[mid1] : (nums1[mid1] + Math.min(nums1[mid1+1], nums2[mid2+1])) / 2.0;
            }
            if (nums1[mid1] <= nums2[mid2] && nums1[mid1+1] >= nums2[mid2]) {
                return odd ? nums2[mid2] : (nums2[mid2] + Math.min(nums1[mid1+1], nums2[mid2+1])) / 2.0;
            }
            if (nums2[mid2] > nums1[mid1]) start = mid1 + 1;
            else end = mid1 - 1;
        }
    }
}
