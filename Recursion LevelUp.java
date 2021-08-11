//find total no. of paths possible
//flood fill with 8 directions single jump allowed

// means backtracking is possible

// we have faith that 1 box/cell will find its all paths and give to us

// make a String array corresponding to each 8 directions
// make an visited array, its size = size of given matrix
// use dfs approach

// mark itself
// call neighbours
// unmark itself

// Corner cases: loop can be formed




public class Main {
    public static int floodFill(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS, String ans){
        if(sr == er && sc == ec){
            System.out.println(ans);
            return 1;
        }
        
        int n = vis.length;//no. of rows
        int m = vis[0].length;//no. of columns
        
        vis[sr][sc] = true;//mark
        int count = 0;
        for(int d = 0; d < dir.length; d++){//call for nbrs
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            
            if(r < n && c < m && r >=0 && c >= 0 && !vis[r][c]){
                count += floodFill(r, c, er, ec, vis, dir, dirS, ans + dirS[d]);
            }
        }
        
        vis[sr][sc] = false;//unmark
        
        return count;
        
        
    }
    public static void main(String[] args) {
        boolean[][] vis = new boolean[3][3]; // vector<vector<bool>> vis(3,vector<bool>(3,false));
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 1, 1 }, { -1, -1 }, { 0, 1 }, { 0, -1 }, { -1, 1 }, { 1, -1 } };
        String[] dirS = { "D", "U", "S", "N", "R", "L", "E", "W" };

        System.out.println(floodFill(0, 0, 2, 2, vis, dir, dirS, ""));
    }
}

//flood fill in 8 directions with multiple jumps allowed

//run a loop with radius of circle approach that is equal to max(row, col) of 
//given matrix same as that of visited matrix

public class Main {
    public static int floodFill(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS, String ans){
        if(sr == er && sc == ec){
            System.out.println(ans);
            return 1;
        }
        
        int n = vis.length;//no. of rows
        int m = vis[0].length;//no. of columns
        
        vis[sr][sc] = true;//mark
        int count = 0;
        for(int rad = 0; rad < Math.max(n, m); rad++){
            for(int d = 0; d < dir.length; d++){//call for nbrs
                int r = sr + dir[d][0];
                int c = sc + dir[d][1];

                if(r < n && c < m && r >=0 && c >= 0 && !vis[r][c]){
                    count += floodFill(r, c, er, ec, vis, dir, dirS, ans + dirS[d]);
                }
            }
        }
        
        vis[sr][sc] = false;//unmark
        
        return count;
        
        
    }
    
    public static void main(String[] args) {
        boolean[][] vis = new boolean[2][2]; // vector<vector<bool>> vis(3,vector<bool>(3,false));
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 1, 1 }, { -1, -1 }, { 0, 1 }, { 0, -1 }, { -1, 1 }, { 1, -1 } };
        String[] dirS = { "D", "U", "S", "N", "R", "L", "E", "W" };

        System.out.println(floodFill(0, 0, 1, 1, vis, dir, dirS, ""));
    }
}


