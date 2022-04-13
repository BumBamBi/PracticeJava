package Algorithm.BOJ.q2178;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[][] matrix;
    static final int[] dy = { -1, 1, 0, 0 };
    static final int[] dx = { 0, 0, -1, 1 };

    static class Point implements Comparable<Point>{
        int y;
        int x;
        int cnt;

        public Point(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return this.cnt - o.cnt;
        }
        
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        matrix = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = sc.next();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = s.charAt(j) - '0';
            }
        }

        bfs(0,0);

        sc.close();
    }

    private static void bfs(int y, int x) {
        boolean[][] visited = new boolean[N][M];
        PriorityQueue<Point> q = new PriorityQueue<Point>();
        q.offer(new Point(y, x, 1));
        visited[y][x] = true;


        while (!q.isEmpty()) {
            Point cur = q.poll();
            if (cur.y == N - 1 && cur.x == M - 1) {
                System.out.println(cur.cnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int curY = cur.y + dy[i];
                int curX = cur.x + dx[i];
                int curCnt = cur.cnt;

                if (isAvailable(curY, curX, visited)) {
                    visited[curY][curX] = true;
                    q.offer(new Point(curY, curX , curCnt+1));
                }
            }
        }
    }

    private static boolean isAvailable(int curY, int curX, boolean[][] visited) {
        if (curY >= 0 && curY < N && curX >= 0 && curX < M) {
            if (!visited[curY][curX] && matrix[curY][curX] == 1) {
                return true;
            }
        }
        return false;
    }
}
