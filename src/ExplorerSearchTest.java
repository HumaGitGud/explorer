import static org.junit.Assert.*;
import org.junit.Test;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }

    // explorerCoordinates tests
    @Test
    public void testExplorerCoordinates_ExplorerExists() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int[] actual = ExplorerSearch.explorerCoordinates(island);
        int[] expected = {3, 4};
        assertArrayEquals(expected, actual);
    }
    
    @Test
    public void testExplorerCoordinates_ExplorerDoesNotExist() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,1,1},
            {1,1,1,2,1,1},
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            ExplorerSearch.explorerCoordinates(island);
        });
        assertEquals("Explorer not found", exception.getMessage());
    }
    
    @Test
    public void testExplorerCoordinates_At_0_0() {
        int[][] island = {
            {0,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,1,1},
            {1,1,1,2,1,1},
        };
        int[] actual = ExplorerSearch.explorerCoordinates(island);
        int[] expected = {0, 0};
        assertArrayEquals(expected, actual);
    }
}