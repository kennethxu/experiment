package regular_expression_matching;

class Solution2 {

    public boolean isMatch(String s, String p) {
        return matches(s, p, s.length() - 1, p.length() - 1, new boolean[s.length() + 1][p.length() + 1]);
    }

    private static boolean matches(String s, String p, int is, int ip, boolean[][] matched) {
        final int ms = is + 1, mp = ip + 1;
        if (matched[ms][mp]) return true;
        if (is >= 0 && ip >= 0) {
            var c = p.charAt(ip--);
            if (c != '*') {
                return matched[ms][mp] = (c == '.' || c == s.charAt(is)) && matches(s, p, is - 1, ip, matched);
            }
            c = p.charAt(ip--);
            do {
                if (matches(s, p, is, ip, matched)) return matched[ms][mp] = true;
            } while ((c == '.' || c == s.charAt(is)) && --is >= 0);
        }
        return matched[ms][mp] = is < 0 && emptyPattern(p, ip);
    }

    private static boolean emptyPattern(String p, int ip) {
        while (ip >= 0 && p.charAt(ip) == '*') ip -= 2;
        return ip < 0;
    }
}
