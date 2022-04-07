package Algorithm.SWEA.q1249_yet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sezin {
   static int map[][], N, result;
   static int dx[]= {-1,1,0,0}, dy[] = {0,0,-1,1};
   static int dp[][];
   static class location
   {
      int x,y;

      public location(int x, int y) {
         super();
         this.x = x;
         this.y = y;
      }
      
   }
   public static void main(String[] args) throws NumberFormatException, IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int T = Integer.parseInt(br.readLine());
      for(int tc=1;tc<=T;tc++)
      {
         result=Integer.MAX_VALUE;
         N = Integer.parseInt(br.readLine());
         map = new int[N][N];
         dp = new int[N][N];
         for(int i=0;i<N;i++)
         {
            String input = br.readLine();
            for(int j=0;j<N;j++)
               map[i][j] = input.charAt(j) - '0';
         }
         for(int i=0;i<N;i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);
         
         bfs();
         System.out.println("#"+tc+ " " + dp[N-1][N-1]);
      }
      

   }
   private static void bfs() {
      Queue<location> q = new LinkedList<>();
      q.offer(new location(0,0));
      dp[0][0]=0;
      boolean visited[][] = new boolean[N][N];
   
      while(!q.isEmpty())
      {
         location temp = q.poll();
         int x = temp.x;
         int y = temp.y;
         
         for(int i=0;i<4;i++)
         {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx >= 0 && ny >= 0 && nx < N && ny < N)
            {
               if(dp[nx][ny] > dp[x][y]+map[nx][ny])
               {
                  dp[nx][ny]=dp[x][y]+map[nx][ny];
                  q.offer(new location(nx,ny));
               }
            }
         }
      }
      
   }
   
   

}