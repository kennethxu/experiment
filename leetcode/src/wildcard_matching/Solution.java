package wildcard_matching;

public class Solution {
    int complexity = 0;

    public boolean isMatch(String s, String p) {
        return match2d(s, p);
    }

    private boolean match2d(String s, String p) {
        int pStarLeft = -1, sStarLeft = -1;
        int pLeft = 0, sLeft = 0;
        int pStarRight = p.length(), sStarRight = p.length();
        int pRight = p.length() - 1, sRight = s.length()-1;
        while (sLeft < sRight) {
            complexity++;
            if (complexity % 2 == 0) {
                char c = (pLeft < p.length()) ? p.charAt(pLeft) : 0;
                if (c == '*') {
                    pStarLeft = ++pLeft;
                    sStarLeft = sLeft;
                } else if (c == '?' || c == s.charAt(sLeft)) {
                    sLeft++;
                    pLeft++;
                } else if (pStarLeft >= 0) {
                    sLeft = ++sStarLeft;
                    pLeft = pStarLeft;
                } else {
                    return false;
                }
            } else {
                char c = (pRight >= 0) ? p.charAt(pRight) : 0;
                if (c == '*') {
                    pStarRight = --pRight;
                    sStarRight = sRight;
                } else if (c == '?' || c == s.charAt(sRight)) {
                    sRight--;
                    pRight--;
                } else if (pStarRight < p.length()) {
                    sRight = --sStarRight;
                    pRight = pStarRight;
                } else {
                    return false;
                }
            }
        }
        while (pLeft < pRight && p.charAt(pLeft) == '*') pLeft++;
        return pLeft == pRight;
    }

    private boolean matchForward(String s, String p) {
        int pStar = -1, sStar = -1;
        int ip = 0, is = 0;
        while (is < s.length()) {
            complexity++;
            char c = (ip < p.length()) ? p.charAt(ip) : 0;
            if (c == '*') {
                pStar = ++ip;
                sStar = is;
            } else if (c == '?' || c == s.charAt(is)) {
                is++;
                ip++;
            } else if (pStar >= 0) {
                is = ++sStar;
                ip = pStar;
            } else {
                return false;
            }
        }
        while (ip < p.length() && p.charAt(ip) == '*') ip++;
        return ip == p.length();
    }

    private boolean matchBackward(String s, String p) {
        int pStar = p.length(), sStar = p.length();
        int ip = p.length() - 1, is = s.length()-1;
        while (is >= 0) {
            complexity++;
            char c = (ip >= 0) ? p.charAt(ip) : 0;
            if (c == '*') {
                pStar = --ip;
                sStar = is;
            } else if (c == '?' || c == s.charAt(is)) {
                is--;
                ip--;
            } else if (pStar < p.length()) {
                is = --sStar;
                ip = pStar;
            } else {
                return false;
            }
        }
        while (ip >= 0 && p.charAt(ip) == '*') ip--;
        return ip < 0;
    }


}
