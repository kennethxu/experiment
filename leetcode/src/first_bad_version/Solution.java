package first_bad_version;

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        return bsearch(1, n, n);
    }
    private int bsearch(int start, int end, int bad) {
        if (start > end) return bad;
        int mid = start + (end - start) / 2;
        var isBad = isBadVersion(mid);
        if (isBad) bad = mid;
        return isBad ? bsearch(start, mid-1, bad) : bsearch(mid+1, end, bad);
    }
}
