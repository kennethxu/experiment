package first_bad_version;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FirstBadVersion {
    private final int n;
    private final int expected;

    public FirstBadVersion(String name, int n, int expected) {
        this.n = n;
        this.expected = expected;
    }

    @Parameters(name="{0}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][] {
                {"5|4", 5, 4},
                {"1|1", 1, 1},
                {"2126753390|1702766719", 2126753390, 1702766719},
        });
    }
    
    @Test
    public void bioHazard() {
        Solution solution = new Solution();
        solution.firstBadVersion = expected;
        int result = solution.firstBadVersion(n);
        Assert.assertEquals(expected, result);
    }
}
