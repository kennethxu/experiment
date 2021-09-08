package regular_expression_matching;

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
                {"aa|a*", "aa", "a*", true},
                {"a|ab*", "a", "ab*", true},
                {"aa|a", "aa", "a", false},
                {"aab|c*a*b", "aab", "c*a*b", true},
                {"mississippi|mis*is*p*.", "mississippi", "mis*is*p*.", false},
                {"aaa|aaaa", "aaa", "aaaa", false},
                {"ab|.*..", "ab", ".*..", true},
                {"complex", "abaabaaaabbabbaaabaabababbaabaabbabaaaaabababbababaabbabaabbbbaabbbbbbbabaaabbaaaaabbaabbbaaaaabbbabb",
                        "ab.*aaba.*abbaaaa.*b.*b.*aa.*a.*b.*ba.*a.*ba.*baaa.*b.*ab.*", false},
        });
    }

    @Test
    public void isMatch() {
        boolean result = new Solution().isMatch(s, p);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void isMatch2() {
        boolean result = new Solution2().isMatch(s, p);
        Assert.assertEquals(expected, result);
    }
}
