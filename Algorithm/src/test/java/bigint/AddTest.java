package bigint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static bigint.BigInteger.*;

@RunWith(Parameterized.class)
public class AddTest {

    @Parameterized.Parameters(name = "{index}: add({0},{1})={2} ({3})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "0", "0", "0", 0 },
                { "0", "123", "123", 3 },
                { "-123", "0", "-123", 3 },
                { "437", "-348", "89", 2 },
                { "100", "-100", "0", 0 },
                { "237", "-348", "-111", 3 },
                { "-99", "-99", "-198", 3 },
                { "-100", "99", "-1", 1 },
        });
    }

    private String first, second, expected;

    private int expectedDigits;

    public AddTest(String first, String second, String expected, int expectedDigits) {
        this.first = first;
        this.second = second;
        this.expected = expected;
        this.expectedDigits = expectedDigits;
    }

    private BigInteger i;

    @Before
    public void setup() {
        i = add(parse(first), parse(second));
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
