package wildcard_matching;

class Solution2 {
    int complexity = 0;

    public boolean isMatch(String s, String p) {
        //return matches(s, p, s.length() - 1, p.length() - 1, new Boolean[s.length() + 1][p.length() + 1]);
        return comparison(s, p);
    }
    public boolean yisMatch(String s, String p) {
        boolean[][] match=new boolean[s.length()+1][p.length()+1];
        match[s.length()][p.length()]=true;
        for(int i=p.length()-1;i>=0;i--){
            if(p.charAt(i)!='*')
                break;
            else
                match[s.length()][i]=true;
        }
        for(int i=s.length()-1;i>=0;i--){
            for(int j=p.length()-1;j>=0;j--){
                complexity++;
                if(s.charAt(i)==p.charAt(j)||p.charAt(j)=='?')
                    match[i][j]=match[i+1][j+1];
                else if(p.charAt(j)=='*')
                    match[i][j]=match[i+1][j]||match[i][j+1];
                else
                    match[i][j]=false;
            }
        }
        return match[0][0];
    }

    boolean comparison(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()){
            complexity++;
            // * found, only advancing pattern pointer
            if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
            // advancing both pointers
            else if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                p = starIdx + 1;
                match++;
                s = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }

    private boolean matches(String s, String p, int is, int ip, Boolean[][] matched) {
        final int ms = is + 1, mp = ip + 1;
        if (matched[ms][mp] != null) return matched[ms][mp];
        complexity++;
        if (is >= 0 && ip >= 0) {
            var c = p.charAt(ip--);
            if (c != '*') {
                return matched[ms][mp] = (c == '?' || c == s.charAt(is)) && matches(s, p, is - 1, ip, matched);
            }
            while(ip >= 0 && p.charAt(ip) == '*') ip--;
            do {
                if (matches(s, p, is, ip, matched)) return matched[ms][mp] = true;
            } while (--is >= 0);
        }
        return matched[ms][mp] = is < 0 && emptyPattern(p, ip);
    }

    private static boolean emptyPattern(String p, int ip) {
        while (ip >= 0 && p.charAt(ip) == '*') ip--;
        return ip < 0;
    }
}
