import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    
    @Test
    public void testReachableArea_fullyUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,3,3,3},
            {3,1,2,3,0,3},
            {1,1,1,2,2,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(1, actual);
    }
    
    @Test
    public void testReachableArea_allReachable() {
        int[][] island = {
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,0,1},
            {1,1,1,1,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(30, actual);
    }
    
    @Test
    public void testReachableArea_horizontalPath() {
        int[][] island = {
            {2,2,2,2,2,2},
            {2,2,2,2,2,2},
            {0,1,1,1,1,1},
            {3,3,3,3,3,3},
            {3,3,3,3,3,3},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(6, actual);
    }
    
    @Test
    public void testReachableArea_verticalPath() {
        int[][] island = {
            {2,2,2,1,3,3},
            {2,2,2,1,3,3},
            {2,2,2,1,3,3},
            {2,2,2,1,3,3},
            {2,2,2,0,3,3},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(5, actual);
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

    // possibleMoves tests
    @Test
    public void testPossibleMoves_Freedom() {
        int[][] island = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(4, moves.size());
        assertTrue(moveSet.contains("0,1")); // up
        assertTrue(moveSet.contains("2,1")); // down
        assertTrue(moveSet.contains("1,0")); // left
        assertTrue(moveSet.contains("1,2")); // right
        
    }
    
    @Test
    public void testPossibleMoves_Captive() {
        int[][] island = {
            {2, 2, 2},
            {2, 0, 3},
            {3, 3, 3}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        assertTrue(moves.isEmpty());
    }
    
    @Test
    public void testPossibleMoves_MovesImpossible() {
        int[][] island = {
            {1, 2, 1},
            {2, 0, 3},
            {1, 3, 1}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        assertTrue(moves.isEmpty());
    }
    
    @Test
    public void testPossibleMoves_UpMoveOnly() {
        int[][] island = {
            {2, 1, 2},
            {2, 1, 3},
            {3, 0, 3}
        };
        int[] location = {2, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("1,1")); // up
    }

    @Test
    public void testPossibleMoves_UpMoveBlocked() {
        int[][] island = {
            {1, 2, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(3, moves.size());
        assertTrue(moveSet.contains("2,1")); // down
        assertTrue(moveSet.contains("1,0")); // left
        assertTrue(moveSet.contains("1,2")); // right
    }
    
    @Test
    public void testPossibleMoves_DownMoveOnly() {
        int[][] island = {
            {2, 0, 3},
            {2, 1, 3},
            {2, 1, 3}
        };
        int[] location = {0, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("1,1")); // down
    }

    @Test
    public void testPossibleMoves_DownMoveBlocked() {
        int[][] island = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 3, 1}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(3, moves.size());
        assertTrue(moveSet.contains("0,1")); // up
        assertTrue(moveSet.contains("1,0")); // left
        assertTrue(moveSet.contains("1,2")); // right
    }
    
    @Test
    public void testPossibleMoves_LeftMoveOnly() {
        int[][] island = {
            {2, 2, 2},
            {1, 1, 0},
            {3, 3, 3}
        };
        int[] location = {1, 2};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("1,1")); // left
    }

    @Test
    public void testPossibleMoves_LeftMoveBlocked() {
        int[][] island = {
            {1, 1, 1},
            {3, 0, 1},
            {1, 1, 1}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(3, moves.size());
        assertTrue(moveSet.contains("0,1")); // up
        assertTrue(moveSet.contains("2,1")); // down
        assertTrue(moveSet.contains("1,2")); // right
    }
    
    @Test
    public void testPossibleMoves_RightMoveOnly() {
        int[][] island = {
            {2, 2, 2},
            {0, 1, 1},
            {3, 3, 3}
        };
        int[] location = {1, 0};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("1,1")); // right
    }

    @Test
    public void testPossibleMoves_RightMoveBlocked() {
        int[][] island = {
            {1, 1, 1},
            {1, 0, 3},
            {1, 1, 1}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(3, moves.size());
        assertTrue(moveSet.contains("0,1")); // up
        assertTrue(moveSet.contains("2,1")); // down
        assertTrue(moveSet.contains("1,0")); // left
    }
    
    // Helper method
    private Set<String> toSet(List<int[]> list) {
        Set<String> set = new HashSet<>();
        for (int[] arr : list) {
            set.add(arr[0] + "," + arr[1]);
        }
        return set;
    }
}