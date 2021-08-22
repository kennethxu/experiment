package bigint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParseErrorTest {

    @Parameterized.Parameters(name = "{index}: parse(\"{0}\")")
    public static Object[] data() {
        return new Object[] {
                null,
                "",
                "1e10",
                "01-45",
                "7847-",
                " ",
                "+-123",
                "-+0123",
                " 234e90 ",
                " + ",
                "-",
                "+",
        };
    }

    private String input;

    public ParseErrorTest(String input) {
        this.input = input;
    }

    @Test(expected = IllegalArgumentException.class)
    public void test() {
        BigInteger.parse(input);
    }

}
