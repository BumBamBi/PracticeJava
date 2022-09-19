package Algorithm.BOJ.q15686;

import java.util.*;

class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    
    
    static int N, M;
    static int[][] matrix;
    static ArrayList<Node> homes;
    static ArrayList<Node> chickens;
    static boolean[] open;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        
        matrix = new int[N][N];
        homes = new ArrayList<Node>();
        chickens = new ArrayList<Node>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = sc.nextInt();
                if (matrix[i][j] == 1) {
                    homes.add(new Node(i, j));
                } else if (matrix[i][j] == 2) {
                    chickens.add(new Node(i, j));
                }
            }
        }

        open = new boolean[chickens.size()];

        dfs(0, 0);

        System.out.println(ans);

        sc.close();
    }


    private static void dfs(int start, int cnt) {
        if (cnt == M) {
            int res = 0;

            for (int i = 0; i < homes.size(); i++) {
                int temp = Integer.MAX_VALUE;

                // 어떤 집과 치킨집 중 open한 치킨집의 모든 거리를 비교한다.
                // 그 중, 최소 거리를 구한다.
                for (int j = 0; j < chickens.size(); j++) {
                    if (open[j]) {
                        int distance = Math.abs(homes.get(i).x - chickens.get(j).x)
                                + Math.abs(homes.get(i).y - chickens.get(j).y);

                        temp = Math.min(temp, distance);
                    }
                }
                res += temp;
            }
            ans = Math.min(ans, res);
            return;
        }
 
        // 백트래킹
        for (int i = start; i < chickens.size(); i++) {
            open[i] = true;
            dfs(i + 1, cnt + 1);
            open[i] = false;
        }
    }
}
