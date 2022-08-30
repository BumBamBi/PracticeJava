package Algorithm.BOJ.q1388;

import java.util.Scanner;

public class Main {

    static int N, M;
    static char[][] arr;
    static boolean[][] visited;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        arr = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String temp = sc.next();
            for (int j = 0; j < M; j++) {
                arr[i][j] = temp.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == false) {
                    func(i, j);
                }
            }
        }

        sc.close();
    }
    public static void func(int row, int col) {
        visited[row][col] = true;

        for (int i = 0; i < 4; i++) {
            
        }
    }
}
