package substring_with_concatenation_of_all_words;

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
public class FindSubstring {
    private final String s;
    private final String[] words;
    private final int[] expected;

    public FindSubstring(String name, String s, String[] words, int[] expected) {
        this.s = s;
        this.words = words;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][]{
                {"foobar", "barfoothefoobarman", new String[]{"foo","bar"}, new int[]{0,9},},
                {"goodbest", "wordgoodgoodgoodbestword", new String[]{"word","good","best","word"}, new int[]{},},
                {"foobar2", "barfoofoobarthefoobarman", new String[]{"bar","foo","the"}, new int[]{6,9,12},},
                {"goodbest2", "wordgoodgoodgoodbestword", new String[]{"word","good","best","good"}, new int[]{8},},
                {"lingmindra", "lingmindraboofooowingdingbarrwingmonkeypoundcake", new String[]{"fooo","barr","wing","ding","wing"}, new int[]{13},},
                {"a*", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", new String[]{"aa"},
                        new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40},},
        });
    }

    @Test
    public void isMatch() {
        Solution solution = new Solution();
        var result = solution.findSubstring(s, words).stream().mapToInt(Integer::intValue).sorted().toArray();
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void isMatch2() {
        Solution2 solution = new Solution2();
        var result = solution.findSubstring(s, words).stream().mapToInt(Integer::intValue).sorted().toArray();
        Assert.assertArrayEquals(expected, result);
    }
}
