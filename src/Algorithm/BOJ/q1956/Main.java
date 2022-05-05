package Algorithm.BOJ.q1956;

import java.util.Scanner;

public class Main {
    static int V, E;
    static int[][] matrix;

    // MAX는 두 값을 더해도 초과 안나게끔 지정
    static final int MAX = 999999999;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();
        matrix = new int[V][V];

        // 초기값을 MAX값으로 둠
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                matrix[i][j] = MAX;
            }
        }

        for (int i = 0; i < E; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int c = sc.nextInt();

            matrix[a][b] = c;
        }

        // 플루이드 와샬(경-출-도)
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                for (int k = 0; k < V; k++) {
                    if (matrix[j][k] > matrix[j][i] + matrix[i][k]) {
                        matrix[j][k] = matrix[j][i] + matrix[i][k];
                    }
                }
            }
        }

        // 사이클 확인
        // (a->k) + (k->a) 가 MAX보다 작다면 돌아올 수 있는 길이 있음(사이클) (자기 자신 제외)
        // 최소를 구하면 되는데, 이미 플루이드 와샬로 최소값만 있으므로, 모두 비교해보며 그 중 최소값 구하기 
        int answer = MAX;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j && matrix[i][j] + matrix[j][i] < MAX) {
                    answer = Math.min(answer, matrix[i][j] + matrix[j][i]);
                }
            }
        }
        if (answer == MAX) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
        
        sc.close();
    }
}
