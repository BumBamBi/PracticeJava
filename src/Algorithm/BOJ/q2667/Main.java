package Algorithm.BOJ.q2667;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N;
    static int[][] matrix;
    static boolean[][] visited;

    // 델타 배열
    static final int[] dy = { -1, 1, 0, 0 };
    static final int[] dx = { 0, 0, -1, 1 };

    // 결과를 저장할 PQ
    // 작은 놈부터 출력됨 
    static PriorityQueue<Integer> result;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        matrix = new int[N][N];
        visited = new boolean[N][N];
        result = new PriorityQueue<Integer>();

        for (int i = 0; i < N; i++) {
            String s = sc.next();
            for (int j = 0; j < N; j++) {
                matrix[i][j] = s.charAt(j) - '0';
            }
        }

        // 입력 끝 ------------------------------------------

        // 모든 노드에 접근해서 bfs수행
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 대신, 방문한적 없는 1값일 때만 수행
                if (!visited[i][j] && matrix[i][j] != 0) {
                    bfs(i, j);
                }
            }
        }

        // pq 사이즈(단지 개수) 출력
        System.out.println(result.size());
        // 각 동의 크기를 오름차순으로 출력
        while (!result.isEmpty()) {
            System.out.println(result.poll());
        }

        sc.close();
    }

    private static void bfs(int i, int j) {
        // 동개수 카운트
        int cnt = 0;
        Queue<int[]> q = new LinkedList<int[]>();
        q.offer(new int[] { i, j });
        visited[i][j] = true;
        cnt++;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int curY = cur[0] + dy[k];
                int curX = cur[1] + dx[k];

                if (isAvailable(curY, curX)) {
                    q.offer(new int[] { curY, curX });
                    visited[curY][curX] = true;
                    cnt++;
                }
            }
        }
        result.offer(cnt);
    }

    private static boolean isAvailable(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < N) {
            if (!visited[i][j]) {
                if (matrix[i][j] != 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
