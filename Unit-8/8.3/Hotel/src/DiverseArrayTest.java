import org.junit.*;
import static org.junit.Assert.*;

public class DiverseArrayTest {
    int[] shortArray;
    int[][] diverseArray;
    int[][] nonDiverseArray;

    @Before
    public void setUp() throws Exception {
        shortArray = new int[] {1, 2, 3};
        nonDiverseArray = new int[][] {
            {1, 2, 3},
            {1, 2, 3},
            {1, 2, 3}
        };
        diverseArray = new int[][] {
            {1, 2, 3},
            {2,4,6},
            {3,6,9}
        };
    }



    @Test
    public void testArraySum() {


        assertEquals(6, DiverseArray.arraySum(shortArray));
    }

    @Test
    public void testRowSums() {
        assertEquals(6, DiverseArray.rowSums(diverseArray)[0]);
        assertEquals(12, DiverseArray.rowSums(diverseArray)[1]);
        assertEquals(18, DiverseArray.rowSums(diverseArray)[2]);
    }

    @Test
    public void testIsDiverseTrueCase() {
        assertTrue(DiverseArray.isDiverse(diverseArray));

    }

    @Test
    public void testIsDiverseFalseCase() {
        assertFalse(DiverseArray.isDiverse(nonDiverseArray));
    }

}
