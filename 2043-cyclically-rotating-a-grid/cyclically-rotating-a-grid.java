
class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;

        int layers = Math.min(m, n) / 2;

        for (int layer = 0; layer < layers; layer++) {

            List<Integer> list = new ArrayList<>();

    
            for (int j = layer; j < n - layer; j++)
                list.add(grid[layer][j]);

            
            for (int i = layer + 1; i < m - layer - 1; i++)
                list.add(grid[i][n - layer - 1]);

            
            for (int j = n - layer - 1; j >= layer; j--)
                list.add(grid[m - layer - 1][j]);

        
            for (int i = m - layer - 2; i > layer; i--)
                list.add(grid[i][layer]);

            int size = list.size();
            int rot = k % size;

            
            Collections.rotate(list, -rot);

            int idx = 0;

            
            for (int j = layer; j < n - layer; j++)
                grid[layer][j] = list.get(idx++);

            for (int i = layer + 1; i < m - layer - 1; i++)
                grid[i][n - layer - 1] = list.get(idx++);


            for (int j = n - layer - 1; j >= layer; j--)
                grid[m - layer - 1][j] = list.get(idx++);


            for (int i = m - layer - 2; i > layer; i--)
                grid[i][layer] = list.get(idx++);
        }

        return grid;
    }
}