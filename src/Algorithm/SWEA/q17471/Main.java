package Algorithm.SWEA.q17471;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N;
    static int[] population;
    static int[][] adjMatrix;
    static int[] area;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        population = new int[N + 1];    // 인구수
        adjMatrix = new int[N + 1][N + 1];  // 인접행렬
        area = new int[N + 1];  // 1과2로 구분

        // 인구수
        for (int i = 1; i <= N; i++) {
            population[i] = sc.nextInt();
        }

        // 인접 행렬 만들기
        for (int i = 1; i <= N; i++) {
            int temp = sc.nextInt();
            for (int j = 0; j < temp; j++) {
                int adjNode = sc.nextInt();
                adjMatrix[i][adjNode] = population[adjNode];
            }
        }

        dfs(1);

        if (answer == Integer.MAX_VALUE) {  // 아무 업데이트가 일어나지 않았다면
            System.out.println(-1);
        } else {
            System.out.println(answer); // 업데이트가 일어났다면
        }

        sc.close();
    }
    
    // 조합 만들기
    static void dfs(int n) {
        // 모든 정점을 1과2로 구분했다면
        if (n > N) {
            boolean[] visited1 = new boolean[N + 1];
            boolean[] visited2 = new boolean[N + 1];

            int[] area1 = bfs(1, visited1);
            int[] area2 = bfs(2, visited2);

            // 2구역인지 확인
            boolean isOk = true;

            for (int i = 1; i <= N; i++) {
                // 둘 다 접근 못하는 공간이 있거나, 전부 하나의 영역으로 이루어져 있다면
                if ((visited1[i] == false && visited2[i] == false) || (area1[1] == N || area2[1] == N)) {
                    // 2구역이 아님
                    isOk = false;
                }
            }

            if (isOk) {
                answer = Math.min(answer, Math.abs(area1[0] - area2[0]));
            }
            
            return;
        } else {
            area[n] = 1;
            dfs(n + 1);
            area[n] = 2;
            dfs(n + 1);
        }
    }


    static int[] bfs(int where, boolean[] visited1) {
        Queue<Integer> q = new LinkedList<Integer>();
        int start = 0;
        int cnt = 0;
        int sumPopulation = 0;

        // 처음으로 q에 들어갈 정점 찾기
        for (int i = 1; i <= N; i++) {
            if (area[i] == where) {
                start = i;
                break;
            }
        }

        q.offer(start);
        visited1[start] = true;
        sumPopulation += population[start];
        cnt++;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 2; i <= N; i++) {
                // 인접하고 && 방문하지 않았으며 && 특정 구역이라면
                if (adjMatrix[cur][i] != 0 && !visited1[i] && area[i] == where) {
                    visited1[i] = true;

                    // System.out.print(", " + i);
                    sumPopulation += population[i];
                    cnt++;
                    q.offer(i);
                }
            }
        }

        return new int[] { sumPopulation, cnt };
    }
}
