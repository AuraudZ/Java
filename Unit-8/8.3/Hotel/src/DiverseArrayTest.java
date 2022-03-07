import org.junit.*;
import static org.junit.Assert.*;

public class DiverseArrayTest {
    int[] shortArray;
    int[][] diverseArray;

    @Before
    public void setUp() throws Exception {
        shortArray = new int[] {1, 2, 3};
        diverseArray = new int[][] {
            {1, 2, 3},
            {1, 2, 3},
            {1, 2, 3}
        };
    }



    @Test
    public void testArraySum() {
        assertEquals(6, DiverseArray.arraySum(shortArray));
    }

    @Test
    public void testRowSums() {

    }

    @Test
    public void testIsDiverseTrue() {

    }

    @Test
    public void testIsDiverseFalse() {

    }

}
