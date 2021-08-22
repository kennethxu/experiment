package bigint;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParseTest {

    @Parameterized.Parameters(name = "{index}: parse(\"{0}\")=\"{1}\"")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "+0", "0", 0 },
                { "-0", "0", 0 },
                { "+123", "123", 3 },
                { "1023", "1023", 4 },
                { "0012", "12", 2 },
                { "0", "0", 0 },
                { "-123", "-123", 3 },
                { "-001", "-1", 1 },
                { "+000", "0", 0 }, // multiple 0s
                { "  +123", "123", 3 },
                { "-123  ", "-123", 3 },
                { "+07536", "7536", 4 }, // single leading 0 after +
                { "-045", "-45", 2 },
                { " +0034  ", "34", 2 },
                { "  -078 ", "-78", 2 },
        });
    }

    private String input;

    private String expected;

    private int expectedDigits;

    public ParseTest(String input, String expected, int expectedDigits) {
        this.input = input;
        this.expected = expected;
        this.expectedDigits = expectedDigits;
    }

    private BigInteger i;

    @Before
    public void setup() {
        i = BigInteger.parse(input);
    }


    @Test
    public void value() {
        assertEquals(expected, i.toString());
    }

    @Test
    public void numDigits() {
        assertEquals(expectedDigits, i.numDigits);
    }
}
