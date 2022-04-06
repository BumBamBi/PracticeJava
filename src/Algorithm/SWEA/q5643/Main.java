package Algorithm.SWEA.q5643;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N;
    static int M;
    static int[][] adjMatrix;
    static int cnt;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int answer = 0;
            N = sc.nextInt();
            M = sc.nextInt();

            // 인접행렬 생성
            adjMatrix = new int[N + 1][N + 1];
            for (int i = 0; i < M; i++) {
                int smaller = sc.nextInt();
                int taller = sc.nextInt();
                adjMatrix[smaller][taller] = 1;
                adjMatrix[taller][smaller] = -1;
            }
            
            // 모든 정점 기준으로 보다크고 작은 노드 찾기
            for (int i = 1; i <= N; i++) {
                cnt = 0;

                bfs(i, 1);  // 보다 큰 노드 찾기
                bfs(i, -1); // 보다 작은 노드 찾기

                // 모든 노드 수만큼 카운트 된다면 자신의 키 순서를 알 수 있음
                // 자기 자신이 두번 더해짐
                if (cnt - 1 == N) {
                    answer++;
                }
            }

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }

    private static void bfs(int n, int mode) {
        Queue<Integer> q = new LinkedList<Integer>();
        boolean[] visited = new boolean[N + 1];
        q.offer(n);
        visited[n] = true;
        cnt++;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 1; i <= N; i++) {
                // 보다 크거나/작고(mode) && 방문하지 않은 친구라면
                if (adjMatrix[cur][i] == mode && !visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                    cnt++;
                }
            }
        }
    }
}
