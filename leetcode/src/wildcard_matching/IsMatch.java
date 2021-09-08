package wildcard_matching;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/*
 *
 */
@RunWith(Parameterized.class)
public class IsMatch {
    private final String s;
    private final String p;
    private final boolean expected;

    public IsMatch(String name, String s, String p, boolean expected) {
        this.s = s;
        this.p = p;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][]{
                {"ab*cdec|ab*c", "ab*cdec", "ab*c", true},
                {"*ab|*b","*ab","*b",true},
                {"*ab|*a","*ab","*a",false},
                {"aa|a","aa","a",false},
                {"aa|*","aa","*",true},
                {"cb|?a","cb","?a",false},
                {"adceb|*a*b","adceb","*a*b",true},
                {"acdcb|a*c?b","acdcb","a*c?b",false},
                {"aab|c*a*b", "aab", "c*a*b", false},
                {"multi-match","aabacaabacaaabadab","a*b*c*aabad*b",true},
                {"duplex", "aaaababbbaaabaabbbbabaababaabbabbaabababbaaaaaaabba",
                        "baaaaba*****b***ab******", false},
                {"complex", "abaabaaaabbabbaaabaabababbaabaabbabaaaaabababbababaabbabaabbbbaabbbbbbbabaaabbaaaaabbaabbbaaaaabbbabb",
                        "ab*aaba*abbaaaa*b*b*aa*a*b*ba*a*ba*baaa*b*ab*", false},
                {"complex+duplex", "abaabaaaabbabbaaabaabababbaabaabbabaaaaabababbababaabbabaabbbbaabbbbbbbabaaabbaaaaabbaabbbaaaaabbbabb",
                        "ab*aaba**abbaaaa**b*b****aa***a*b**ba*a**ba*baaa*b*ab*", false},
                {"long", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                        "*aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab*", false},
                {"long2", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                        "*aaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaa*", false},        });
    }

    @Test
    public void isMatch() {
        Solution solution = new Solution();
        boolean result = solution.isMatch(s, p);
        System.out.println("Complexity: " + solution.complexity);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void isMatchReverse() {
        Solution solution = new Solution();
        boolean result = solution.isMatch(reverse(s), reverse(p));
        System.out.println("Complexity: " + solution.complexity);
        Assert.assertEquals(expected, result);
    }
    @Test
    public void isMatch2() {
        Solution2 solution = new Solution2();
        boolean result = solution.isMatch(s, p);
        System.out.println("Complexity: " + solution.complexity);
        Assert.assertEquals(expected, result);
    }

    private String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
