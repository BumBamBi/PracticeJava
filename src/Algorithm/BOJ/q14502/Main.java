package Algorithm.BOJ.q14502;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[][] virusPoint;
    static int virusCnt;

    static final int[] dy = { -1, 1, 0, 0 };
    static final int[] dx = { 0, 0, -1, 1 };

    static int zeroCnt;
    static int answer = -1;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        virusPoint = new int[10][2];
        virusCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 2) {
                    virusPoint[virusCnt][0] = i;
                    virusPoint[virusCnt][1] = j;
                    virusCnt++;
                } else if (map[i][j] == 0) {
                    zeroCnt++;
                }
            }
        }

        // 벽 3개의 좌표를 중복되지 않게 모두 골라서
        // 각각 bfs를 돌며 최소 값구하기
        dfs(0);
        System.out.println(answer);

        sc.close();
    }

    private static void dfs(int cnt) {
        if (cnt == 3) {
            // 각각 bfs를 돌며 최소 값구하기
            bfs();
            return;
        } else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) {
                        map[i][j] = 1;
                        dfs(cnt + 1);
                        map[i][j] = 0;
                    }
                }
            }
        }
    }

    private static void bfs() {
        Queue<int[]> q = new LinkedList<int[]>();
        visited = new boolean[N][M];
        int safeCnt = zeroCnt-3;    // 새로지은 벽 3개를 사전에 제거
        for (int i = 0; i < virusCnt; i++) {
            q.offer(new int[] { virusPoint[i][0], virusPoint[i][1] });
            visited[virusPoint[i][0]][virusPoint[i][1]] = true;
        }

        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                int[] curPoint = q.poll();
                int curY = curPoint[0];
                int curX = curPoint[1];

                for (int j = 0; j < 4; j++) {

                    int y = curY + dy[j];
                    int x = curX + dx[j];
                    
                    if (isAvailable(y, x)) {
                        q.offer(new int[] { y, x });
                        visited[y][x] = true;
                        safeCnt--;
                    }
                }
                
            }
        }
        answer = Math.max(answer, safeCnt);
    }

    private static boolean isAvailable(int y, int x) {
        if (y >= 0 && y < N && x >= 0 && x < M) {
            if(!visited[y][x]){
                if(map[y][x] == 0){
                    return true;
                }
            }
        }
        return false;
    }
    
    
}
