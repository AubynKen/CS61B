import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    @Test
    public void testUnion() {
        UnionFind testUF = new UnionFind(15);
        testUF.union(0, 1);
        testUF.union(0, 2);

        int expected = 0;
        System.out.println("testing parent");
        assertEquals(0, testUF.parent(2));

        expected = 3;
        System.out.println("testing weight");
        assertEquals(3, testUF.sizeOf(2));

        System.out.println("testing if 2 and 1 are connected");
        assertEquals(true, testUF.connected(1, 2));
    }
}

