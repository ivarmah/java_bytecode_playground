package app.test.eu.ivarm.minimator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MinimatorTest {
    @Test
    public void oneIsSmallerThanTwo() {
        assertEquals(Minimator.min(1,2), 1);
    }

    @Test
    public void twoIsSmallerThanThreeAndFour() {
        assertEquals(Minimator.min(2,3,4), 2);
    }
}
