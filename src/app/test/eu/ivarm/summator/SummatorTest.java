package app.test.eu.ivarm.sum;

import org.junit.Test;
import static org.junit.Assert.*;

public class SummatorTest {
    Summator summator = new Summator();
    @Test
    public void fourNotEqualsTwo() {
        assertNotEquals(summator.sum(2,2), 2);
    }

    @Test
    public void fourEqualsFour() {
        assertEquals(summator.sum(2,2), 4);
    }
}
