package first_bad_version;

public class VersionControl {
    int firstBadVersion;

    boolean isBadVersion(int v) {
        return v >= firstBadVersion;
    }
}
