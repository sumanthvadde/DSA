package Graphs;

import java.util.*;
import java.util.LinkedList;

/*
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
*/

class Pair {
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class NumOfIslands{
    public void bfs(int row, int col, boolean[][] vis, char[][] grid){
        vis[row][col] = true;
        Queue<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(row, col));
        int n = grid.length;
        int m = grid[0].length;

        while(!q.isEmpty()){
            int ro = q.peek().first;
            int co = q.peek().second;
            q.remove();

            // traverse in the neighbours and mark them if it is visited
            for(int delRow = -1; delRow <= 1; delRow++ ){
                for(int delCol = -1; delCol <= 1; delCol++){
                    if((delRow==-1 && delCol==0)||(delRow==0 && delCol==1) || (delRow==1 && delCol==0) || (delRow==0 && delCol==-1)){ 
                        int nRow = ro + delRow;
                        int nCol = co + delCol;

                        if((nRow >= 0 && nRow < n) && (nCol >= 0 && nCol < m) && (vis[nRow][nCol] == false) && 
                        (grid[nRow][nCol] == '1')){
                            vis[nRow][nCol] = true;
                            q.add(new Pair(nRow, nCol));
                        }
                    }
                }
            }
        }
    }
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int cnt = 0;
        boolean[][] vis = new boolean[n][m];
        
        for(int row = 0; row < n; row++){
            for(int col = 0; col < m; col++){
                if(!vis[row][col] && grid[row][col] == '1'){
                    cnt++;
                    bfs(row, col, vis, grid);
                }
            }
        }
        return cnt;
    }
}

class Solution {
    private int m;
    private int n;
    public int numIslands(char[][] grid) {
        int count = 0;
        n = grid.length;
        if(n==0) return 0;
        m = grid[0].length;
        
        for(int i =0; i<n; i++){
            for(int j = 0 ; j < m; j++ ){
                if(grid[i][j] == '1'){
                    DFS(grid, i, j);
                    ++count;
                }
            }
        }
        return count;
    }
    
    private void DFS(char[][] grid, int i, int j){
            if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1') return;
            grid[i][j] = '0';
            DFS(grid, i + 1, j);
            DFS(grid, i - 1, j);
            DFS(grid, i, j + 1);
            DFS(grid, i, j - 1);
    }
}
