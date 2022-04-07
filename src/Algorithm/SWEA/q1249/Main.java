package Algorithm.SWEA.q1249;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N;
    static int map[][];
    static int dx[] = { -1, 1, 0, 0 };
    static int dy[] = { 0, 0, -1, 1 };
    static int dp[][];
    
    static class location{
        int x,y;

        public location(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String s = sc.next();
                for (int j = 0; j < N; j++) {
                    map[i][j] = s.charAt(j)-'0';
                }
            }
            // ------------------------------------------

            dp = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);    
            }
            
            bfs();

            // ------------------------------------------
            System.out.println("#" + tc + " " + dp[N-1][N-1]);
        }

        sc.close();
    }

    private static void bfs() {
        Queue<location> q = new LinkedList<>();
        q.offer(new location(0,0));
        dp[0][0] = 0;

        while(!q.isEmpty()){
            location temp = q.poll();
            int x = temp.x;
            int y = temp.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (dp[nx][ny] > dp[x][y] + map[nx][ny]) {
                        dp[nx][ny] = dp[x][y] + map[nx][ny];
                        q.offer(new location(nx, ny));
                    }
                }
            }
        }
    }
}