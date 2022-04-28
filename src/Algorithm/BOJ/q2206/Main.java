package Algorithm.BOJ.q2206;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[][] matrix;
    static boolean[][][] visited;
    static final int[] dy = { -1, 1, 0, 0 };
    static final int[] dx = { 0, 0, -1, 1 };

    static class State {
        int y;
        int x;
        boolean isBreak;
        int cnt;

        public State(int y, int x, boolean isBreak, int cnt) {
            this.y = y;
            this.x = x;
            this.isBreak = isBreak;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        
        matrix = new int[N][M];
        // 벽을 부셨을때와 안부셨을 때를 구분해서 체크
        /*
        2 4
        0111
        0010
        의 경우에, 탐색순서에 따라 1,1에 도착하는 게 0,1이 1,0보다 먼저 온다면 1,2를 부수지 못해서 -1 리턴됨
        */
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String s = sc.next();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = s.charAt(j) - '0';
            }
        }

        if (!bfs(new State(0, 0, false, 1))) {
            System.out.println(-1);
        }

        sc.close();
    }

    private static boolean bfs(State start) {
        Queue<State> q = new ArrayDeque<State>();
        q.offer(start);
        visited[start.y][start.x][0] = true;

        while (!q.isEmpty()) {
            State cur = q.poll();
            int curY = cur.y;
            int curX = cur.x;
            boolean isBreak = cur.isBreak;
            int curCnt = cur.cnt;

            if (curY == N - 1 && curX == M - 1) {
                System.out.println(curCnt);
                return true;
            }

            if (isBreak) {
                for (int i = 0; i < 4; i++) {
                    int yy = curY + dy[i];
                    int xx = curX + dx[i];

                    if (isNotOutOfRange(yy, xx, isBreak) && (matrix[yy][xx] != 1)) {
                        q.offer(new State(yy, xx, true, curCnt + 1));
                        visited[yy][xx][1] = true;
                    }
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    int yy = curY + dy[i];
                    int xx = curX + dx[i];

                    if (isNotOutOfRange(yy, xx, isBreak)) {
                        if (matrix[yy][xx] == 1) {
                            q.offer(new State(yy, xx, true, curCnt + 1));
                            visited[yy][xx][1] = true;
                        } else {
                            q.offer(new State(yy, xx, false, curCnt + 1));
                            visited[yy][xx][0] = true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean isNotOutOfRange(int yy, int xx, boolean isBreak) {
        if (yy >= 0 && yy < N && xx >= 0 && xx < M) {
            if (isBreak) {
                if (!visited[yy][xx][1]) {
                    return true;
                }
            } else {
                if (!visited[yy][xx][0]) {
                    return true;
                }
            }
        }
        return false;
    }
}
