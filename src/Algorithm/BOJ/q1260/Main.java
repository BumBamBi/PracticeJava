package Algorithm.BOJ.q1260;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, M, V;
    static int[][] matrix;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        V = sc.nextInt();

        // 인접리스트로 구현
        matrix = new int[N+1][N+1];

        for (int i = 1; i <= M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            matrix[from][to] = matrix[to][from] = 1;
        }

        // dfs하며 처리할 visited배열
        boolean[] visited = new boolean[N+1];
        dfs(V, visited);

        System.out.println();

        // bfs
        bfs(V);

        sc.close();
    }

    private static void dfs(int node, boolean[] visited) {
        System.out.print(node + " ");
        visited[node] = true;

        // 차례로 조회하며
        for (int i = 1; i <= N; i++) {
            if (matrix[node][i] > 0 && !visited[i]) {
                // 가능한 것부터 재귀로 입장
                dfs(i, visited);
            }
        }
    }

    private static void bfs(int node) {
        // bfs를 위한 visited배열과 queue
        boolean[] visited = new boolean[N+1];
        Queue<Integer> q = new LinkedList<Integer>();
        // 첫 노드를 q에 넣고 방문처리
        q.offer(node);
        visited[node] = true;

        // 반복
        while (!q.isEmpty()) {
            int cur = q.poll();
            System.out.print(cur + " ");

            for (int i = 1; i <= N; i++) {
                if (matrix[cur][i] > 0 && !visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
    
}
