package Algorithm.BOJ.q1388;

import java.util.Scanner;

public class Main {

    static int N, M;
    static char[][] arr;
    static boolean[][] visited;

    static int[] dxy = { -1, 1 };
    
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


        int ans = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == false) {
                    func(i, j, arr[i][j]);
                    ans++;
                }
            }
        }
        
        System.out.println(ans);

        sc.close();
    }

    public static void func(int row, int col, char curChar) {
        visited[row][col] = true;

        if (curChar == '|') {
            for (int i = 0; i < 2; i++) {
                int curRow = row + dxy[i];
                if(isAvailable(curRow, col) == true){
                    if (arr[curRow][col] == '|') {
                        func(curRow, col, arr[curRow][col]);
                    }
                }
            }
        } else if (curChar == '-') {
            for (int i = 0; i < 2; i++) {
                int curCol = col + dxy[i];
                if(isAvailable(row, curCol) == true){
                    if (arr[row][curCol] == '-') {
                        func(row, curCol, arr[row][curCol]);
                    }
                }
            }
        }
    }

    private static boolean isAvailable(int curRow, int curCol) {
        if (curRow >= 0 && curCol >= 0 && curRow < N && curCol < M) {
            if (visited[curRow][curCol] == false) {
                return true;
            }
        }
        return false;
    }
    
}
