package creatures;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestClorus {

    @Test
    public void testBasics() {
        var clorus = new Clorus(1);
        assertEquals(1d, clorus.energy(), 0.01);
        clorus.attack(new Clorus(2));
        assertEquals(3d, clorus.energy(), 0.01);
        clorus.stay();
        assertEquals(2.99d, clorus.energy(), 0.005);
        clorus.move();
        assertEquals(2.96d, clorus.energy(), 0.005);

        clorus = new Clorus(2);
        clorus.replicate();
        assertEquals(1, clorus.energy(), 0.01);

    }
}
