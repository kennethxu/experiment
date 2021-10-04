package biological_hazards;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BioHazard {
    private final int n;
    private final int[] allergic;
    private final int[] poisonous;
    private final int expected;

    public BioHazard(String name, int n, int[] allergic, int[] poisonous, int expected) {
        this.n = n;
        this.allergic = allergic;
        this.poisonous = poisonous;
        this.expected = expected;
    }

    @Parameters(name="{0}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][] {
                {"4|1,2|3,4", 4, new int[]{1,2}, new int[]{3,4}, 7},
                {"26|5,8,14,16|4,4,18,18", 26, new int[]{5,8,14,16}, new int[]{4,4,18,18}, 155},
                {"16||", 16, new int[]{}, new int[]{}, 136},
        });
    }
    
    @Test
    public void bioHazard() {
        int result = new Solution().bioHazard(n, allergic, poisonous);
        Assert.assertEquals(expected, result);
    }
}
