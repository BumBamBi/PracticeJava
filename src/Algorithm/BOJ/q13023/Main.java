package Algorithm.BOJ.q13023;

import java.util.*;

public class Main {
    
    static int N;
    static int M;
    static boolean[][] matrix;
    static boolean[] visited;
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        matrix = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            matrix[from][to] = true;
            matrix[to][from] = true;

        }
        
        for(int i=0; i<N; i++){
            if(ans == 0){
                visited = new boolean[N];
                func(i, 1);
            }
            
        }

        System.out.println(ans);

        sc.close();
    }

    private static void func(int cur, int cnt) {
        if(cnt == 4){
            ans = 1;
            return;
        }

        visited[cur] = true;

        for(int i=0; i<N; i++){
            if (matrix[cur][i] == true) {
                if(visited[i] == false){
                    func(i, cnt+1);
                }
            }
        }
        visited[cur] = false;
    }
}