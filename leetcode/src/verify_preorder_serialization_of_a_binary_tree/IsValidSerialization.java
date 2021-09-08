package verify_preorder_serialization_of_a_binary_tree;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IsValidSerialization {
    private final String input;
    private final boolean expected;

    public IsValidSerialization(String input, boolean expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][]{
                {"9,#,#", true},
                {"1,#", false},
                {"9,#,#,1", false},
                {"", true},
                {"9,3,4,#,#,1,#,#,2,#,6,#,#", true},
        });
    }

    @Test
    public void isMatch() {
        boolean result = new Solution().isValidSerialization(input);
        Assert.assertEquals(expected, result);
    }
}

