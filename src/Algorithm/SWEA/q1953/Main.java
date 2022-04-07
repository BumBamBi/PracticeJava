package Algorithm.SWEA.q1953;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    
    static int N;
    static int M;
    static int R;
    static int C;
    static int L;
    static int[][] map;
    
    static boolean[][] visited;
    // 0 : 공백 / 1~7 : 파이프가 갈 수 있는 delta
    static final int[][] dy = { {0}, { -1, 1, 0, 0 }, { -1, 1 }, { 0, 0 }, { -1, 0 }, { 1, 0 }, { 1, 0 }, { -1, 0 } };
    static final int[][] dx = { { 0 }, { 0, 0, -1, 1 }, { 0, 0 }, { -1, 1 }, { 0, 1 }, { 0, 1 }, { 0, -1 },
            { 0, -1 } };

    // 밑/위/오른/왼 쪽에서 이동될 때 연결가능한 파이프들
    static final int[][] canPipes = { { 1, 2, 5, 6 }, { 1, 2, 4, 7 }, { 1, 3, 4, 5 }, { 1, 3, 6, 7 } };

    static int answer;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            answer = 0;

            N = sc.nextInt();
            M = sc.nextInt();
            R = sc.nextInt();
            C = sc.nextInt();
            L = sc.nextInt();
            
            map = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            if (L == 1) {
                answer = 1;
            } else {
                bfs();
            }
            
            // 출력
            System.out.println("#" + tc +" " +answer);
        }

        

        sc.close();
    }

    private static void bfs() {
        Queue<int[]> q = new LinkedList<int[]>();
        q.offer(new int[] { R, C });
        visited[R][C] = true;
        answer++;

        for (int i = 0; i < L-1; i++) {
            int qLen = q.size();
            for (int j = 0; j < qLen; j++) {
                int[] curArr = q.poll();
                int curR = curArr[0];
                int curC = curArr[1];
                int curPipes = map[curR][curC];

                for (int k = 0; k < dy[curPipes].length; k++) {            
                    int dyy = curR + dy[curPipes][k];
                    int dxx = curC + dx[curPipes][k];
                    
                    if (isAvaivlable(dyy, dxx, curPipes, new int[] { dy[curPipes][k], dx[curPipes][k] })) {
                        q.offer(new int[] { dyy, dxx });
                        visited[dyy][dxx] = true;
                        answer++;
                    }
                }
            }
        }
    }

    private static boolean isAvaivlable(int dyy, int dxx, int prePipes, int[] diffYX) {        
        // index처리/방문처리/파이프 없는처리/알맞은 파이프 연결처리
        if (dyy >= 0 && dyy < N && dxx >= 0 && dxx < M) {
            if (!visited[dyy][dxx]) {
                int curPipe = map[dyy][dxx];
                if (curPipe != 0) {
                    int canLinkPipesIdx = Integer.MAX_VALUE;
                    if (diffYX[1] == 0) {
                        if (diffYX[0] == -1) { // 밑에서 들어오는
                            canLinkPipesIdx = 0;
                        } else { // 위에서 들어오는
                            canLinkPipesIdx = 1;
                        }
                    }
                    if (diffYX[0] == 0) {
                        if (diffYX[1] == -1) { // 오른쪽에서 들어오는
                            canLinkPipesIdx = 2;
                        } else { // 왼쪽에서 들어오는
                            canLinkPipesIdx = 3;
                        }
                    }

                    for (int pipe : canPipes[canLinkPipesIdx]) {
                        if (curPipe == pipe) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
