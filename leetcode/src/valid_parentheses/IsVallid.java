package valid_parentheses;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IsVallid {
    private final String input;
    private final boolean expected;

    public IsVallid(String input, boolean expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][]{
                {"()", true},
                {"()[]{}", true},
                {"(]", false},
                {"([)]", false},
                {"{[]}", true},
                {"({{{{}}}))", false},
        });
    }

    @Test
    public void isMatch() {
        boolean result = new Solution().isValid(input);
        Assert.assertEquals(expected, result);
    }
}
