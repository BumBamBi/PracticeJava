package Algorithm.BOJ.q13023;

import java.util.*;

public class Main {
    
    static int N;
    static int M;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        graph = new ArrayList[N];
        
        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<Integer>();
        }    

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            graph[from].add(to);
            graph[to].add(from);
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
        if(cnt == 5){
            ans = 1;
            return;
        }

        visited[cur] = true;

        for (int linkedNode : graph[cur]) {
            if(visited[linkedNode] == false){
                func(linkedNode, cnt+1);
            }
        }
        visited[cur] = false;
    }
}