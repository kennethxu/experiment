package regular_expression_matching;

public class Solution {

    private static class Matcher {
        private final String s, p;
        private final Boolean[][] matched;
        private long complexity = 0;

        private Matcher(String s, String p) {
            this.s = s;
            this.p = p;
            matched = new Boolean[s.length() + 1][p.length() + 1];
        }

        public boolean match() {
            return match(s.length() - 1, p.length() - 1);
        }

        private boolean match(int is, int ip) {
            final int ms = is + 1, mp = ip + 1;
            if (matched[ms][mp] != null) return matched[ms][mp];
            complexity ++;
            if (is >= 0 && ip >= 0) {
                var c = p.charAt(ip--);
                if (c != '*') {
                    return matched[ms][mp] = (c == '.' || c == s.charAt(is)) && match(is - 1, ip);
                }
                c = p.charAt(ip--);
                do {
                    if (match(is, ip)) return matched[ms][mp] = true;
                } while ((c == '.' || c == s.charAt(is)) && --is >= 0);
            }
            return matched[ms][mp] = is < 0 && emptyPattern(ip);
        }

        private boolean emptyPattern(int ip) {
            while (ip >= 0 && p.charAt(ip) == '*') ip -= 2;
            return ip < 0;
        }
    }

    public boolean isMatch(String s, String p) {
        Matcher matcher = new Matcher(s, p);
        boolean match = matcher.match();
        System.out.println("complexity:" + matcher.complexity);
        return match;
    }
}
