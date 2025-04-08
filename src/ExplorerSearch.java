import java.util.ArrayList;
import java.util.List;

public class ExplorerSearch {

    public static void main(String[] args) {
        // total path tiles = 11
        // total available tiles = 9
        int[][] island = {
            {0, 1, 2, 1, 2},
            {1, 3, 3, 1, 2},
            {1, 1, 2, 2, 2},
            {3, 1, 1, 1, 1},
            {3, 3, 3, 2, 1}
        };
    }

    /**
     * Returns how much land area an explorer can reach on a rectangular island.
     * 
     * The island is represented by a rectangular int[][] that contains
     * ONLY the following nunbers:
     * 
     * '0': represents the starting location of the explorer
     * '1': represents a field the explorer can walk through
     * '2': represents a body of water the explorer cannot cross
     * '3': represents a mountain the explorer cannot cross
     * 
     * The explorer can move one square at a time: up, down, left, or right.
     * They CANNOT move diagonally.
     * They CANNOT move off the edge of the island.
     * They CANNOT move onto a a body of water or mountain.
     * 
     * This method should return the total number of spaces the explorer is able
     * to reach from their starting location. It should include the starting
     * location of the explorer.
     * 
     * For example
     * 
     * @param island the locations on the island
     * @return the number of spaces the explorer can reach
     */
    public static int reachableArea(int[][] island) {
        return -1;
    }

    public static int[] explorerCoordinates(int[][] island) {
        for(int r = 0; r < island.length; r++) {
            for (int c = 0; c < island[0].length; c++) {
                if (island[r][c] == 0) {
                    return new int[]{r, c};
                }
            }
        }

        throw new IllegalArgumentException("Explorer not found");
    }
}
