package Algorithm.BOJ.q7569;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int M, N, H;
    static int[][][] matrix;
    static int remainCnt = 0;
    static int answer = -1;

    static Queue<int[]> q = new ArrayDeque<int[]>();
    static boolean[][][] visited;

    // 상하전후좌우
    static final int[] dy = { 0, 0, -1, 1, 0, 0 };
    static final int[] dx = { 0, 0, 0, 0, -1, 1 };
    static final int[] dz = { 1, -1, 0, 0, 0, 0 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        N = sc.nextInt();
        H = sc.nextInt();

        // 순서를 세로 - 가로 - 높이 순으로 변경
        matrix = new int[N][M][H];
        visited = new boolean[N][M][H];

        for (int z = 0; z < H; z++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    matrix[y][x][z] = sc.nextInt();
                    if (matrix[y][x][z] == 0) {
                        remainCnt++;
                    } else if (matrix[y][x][z] == 1) {
                        q.offer(new int[] { y, x, z });
                        visited[y][x][z] = true;
                    }
                }
            }
        }

        if (remainCnt == 0) {
            answer = 0;
        }else if (q.size() > 0) {
            bfs();
        }

        System.out.println(answer);

        sc.close();
    }

    private static void bfs() {
        int cnt = 0;
        while (!q.isEmpty() && remainCnt > 0) {
            int qSize = q.size();
            for (int l = 0; l < qSize; l++) {
                int[] cur = q.poll();
                int y = cur[0];
                int x = cur[1];
                int z = cur[2];

                for (int i = 0; i < 6; i++) {
                    int yy = y + dy[i];
                    int xx = x + dx[i];
                    int zz = z + dz[i];
                    if (isAvalable(yy, xx, zz)) {
                        q.offer(new int[] { yy, xx, zz });
                        visited[yy][xx][zz] = true;
                        matrix[yy][xx][zz] = 1;
                        remainCnt--;
                    }
                }
            }
            cnt++;
        }
        if (remainCnt == 0) {
            answer = cnt;
        }
    }

    private static boolean isAvalable(int yy, int xx, int zz) {
        if (yy >= 0 && yy < N && xx >= 0 && xx < M && zz >= 0 && zz < H) {
            if (!visited[yy][xx][zz] && matrix[yy][xx][zz] == 0) {
                return true;
            }
        }
        return false;
    }
}
