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
        int[] start = explorerCoordinates(island);
        boolean[][] visited = new boolean[island.length][island[0].length];
        return reachableArea(island, start, visited);
    }
    
    public static int reachableArea(int[][] island, int[] current, boolean[][] visited) {
        // current position
        int currRow = current[0];
        int currCol = current[1];

        // base cases:
        // out of bounds
        if (currRow < 0 || currRow >= island.length || currCol < 0 || currCol >= island[0].length) return 0;
        // impassable tile
        if (island[currRow][currCol] == 2 || island[currRow][currCol] == 3) return 0;
        // avoid revisiting/cycle
        if (visited[currRow][currCol]) return 0;

        // mark current tile as visited
        visited[currRow][currCol] = true;

        int moveCount = 1;

        // recurse
        List<int[]> moves = possibleMoves(island, current);
        for (int[] move : moves) {
            moveCount += reachableArea(island, move, visited);
        }

        return moveCount;
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

    public static List<int[]> possibleMoves(int[][] island, int[] current) {
        int curRow = current[0];
        int curCol = current[1];

        List<int[]> moves = new ArrayList<>();

        // UP
        int newRow = curRow - 1;
        int newCol = curCol;
        if (newRow >= 0 && island[newRow][newCol] != 2 && island[newRow][newCol] != 3) {
            moves.add(new int[]{newRow, newCol});
        }

        // DOWN
        newRow = curRow + 1;
        newCol = curCol;
        if (newRow < island.length && island[newRow][newCol] != 2 && island[newRow][newCol] != 3) {
            moves.add(new int[]{newRow, newCol});
        }

        // LEFT
        newRow = curRow;
        newCol = curCol - 1;
        if (newCol >= 0 && island[newRow][newCol] != 2 && island[newRow][newCol] != 3) {
            moves.add(new int[]{newRow, newCol});
        }

        // RIGHT
        newRow = curRow;
        newCol = curCol + 1;
        if (newCol < island[0].length && island[newRow][newCol] != 2 && island[newRow][newCol] != 3) {
            moves.add(new int[]{newRow, newCol});
        }

        return moves;
    }
}