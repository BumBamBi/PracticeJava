package Algorithm.BOJ.q11404;

import java.util.Scanner;

public class Main {
    static final int INF = 100000001;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[][] arr = new int[N + 1][N + 1];

        // 초기값 설정
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] = INF;

                if (i == j) {
                    arr[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            // 출발 도시와 도착 도시가 같지만 시간이 다른 입력값이 들어올 수 있음.
            // 예를 들어 "1 4 1"과 "1 4 2"가 입력으로 들어왔으면,
            // "1 4 1"을 택해야 함.
            arr[from][to] = Math.min(arr[from][to], cost);
        }

        // 입력 완료(테이블 세팅 완료)----------------------------------------------------------------------


        // 플로이드 와샬 알고리즘(경/출/도)
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    // 최단경로 초기화
                    // 현재 경로 > k번째 경유지를 거쳐서 가는 경로
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        // 현재 경로를 업데이트
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }

        // 출력
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 갈 수 없는 곳은 0으로 초기화
                if (arr[i][j] == INF) {
                    arr[i][j] = 0;
                }
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}
