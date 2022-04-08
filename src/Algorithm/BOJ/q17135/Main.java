package Algorithm.BOJ.q17135;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, M, D;
    static int[][] map;
    static boolean[][] visited;
    static boolean[] isArcher;
    
    static int remainTargetsCnt;
    static int answer;

    // 좌/상/우
    static final int[] dy = { 0, -1, 0 };
    static final int[] dx = { -1, 0, 1 };

    // 잡을 타겟이 없다는 것을 표시할 상수
    static final int NO_TARGET = -1;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        D = sc.nextInt();
        map = new int[N][M];
        isArcher = new boolean[M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) {
                    remainTargetsCnt++;
                }
            }
        }
        
        // 궁수의 위치를 설정
        // 해당 궁수 위치일 때, 시뮬레이션 수행
        dfs(0, 0);

        System.out.println(answer);

        sc.close();
    }

    private static void dfs(int idx, int cnt) {
        
        if (cnt == 3) {
            simulate();
            return;
        } else {
            if (idx < M) {
                isArcher[idx] = true;
                dfs(idx + 1, cnt+1);
                isArcher[idx] = false;
                dfs(idx + 1, cnt);
            }
        }
    }

    private static void simulate() {
        int[][] copyMap = copyMap();
        int remainCnt = remainTargetsCnt;
        int getTargets = 0;
        int turn = 0;
        
        while (remainCnt > 0) {
            int[][] targets = new int[3][2];
            int idx = 0;
            for (int i = 0; i < M; i++) {
                if (isArcher[i]) {
                    int[] target = findTarget(i, copyMap);
                    targets[idx][0] = target[0];
                    targets[idx][1] = target[1];
                    idx++;
                }
            }

            // 잡을 몬스터 위치를 기반으로 몬스터 잡기
            for (int i = 0; i < 3; i++) {
                // 타겟이 있고, 이미 제거되지 않았다면
                if (targets[i][0] != NO_TARGET && copyMap[targets[i][0]][targets[i][1]] != 0) {
                    copyMap[targets[i][0]][targets[i][1]] = 0;
                    getTargets++;
                    remainCnt--;
                }
            }

            // 몬스터 한칸씩 내려오기
            // 가장 아래, 처치하지 못한 몬스터들은 사라짐
            for (int col = 0; col < M; col++) {
                if (copyMap[N - 1][col] == 1) {
                    remainCnt--;
                }
            }
            // 나머지는 한칸씩 내려옴
            // 매번 모두 다 내릴 필요 없이, 지나온 turn수만큼만 내리면 됨(그냥 다 내려도 되긴 함)
            for (int row = N - 2; row >= turn; row--) {
                for (int col = 0; col < M; col++) {
                    copyMap[row + 1][col] = copyMap[row][col];
                    copyMap[row][col] = 0;
                }
            }
            turn++;
        }
        answer = Math.max(answer, getTargets);
    }

    private static int[][] copyMap() {
        int[][] result = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result[i][j] = map[i][j];
            }
        }
        return result;
    }

    private static int[] findTarget(int archerPoint, int[][] copyMap) {
        Queue<int[]> q = new LinkedList<int[]>();
        visited = new boolean[N][M];
        q.offer(new int[] { N - 1, archerPoint });
        visited[N - 1][archerPoint] = true;

        for (int range = 0; range < D; range++) {
            int qSize = q.size();
            for(int i=0; i<qSize; i++){
                int[] curTarget = q.poll();
                int row = curTarget[0];
                int col = curTarget[1];
    
                if (copyMap[row][col] == 1) {
                    // 가장 높은 우선순위의 적을 타겟팅
                    return new int[] { row, col };
                }
    
                // 마지막 사정거리 계산이 아니면 계산진행
                if (range < D - 1) {
                    for (int j = 0; j < 3; j++) {
                        int nextRow = row + dy[j];
                        int nextCol = col + dx[j];
        
                        if (isAvailable(nextRow, nextCol)) {
                            q.offer(new int[] { nextRow, nextCol });
                            visited[nextRow][nextCol] = true;
                        }
                    }
                }
            }
        }
        // 잡을 타겟이 없다면
        return new int[]{NO_TARGET, NO_TARGET};
    }

    private static boolean isAvailable(int i, int j) {
        if(i>=0 && i<N && j>=0 && j<M){
            if (!visited[i][j]) {
                return true;
            }
        }
        return false;
    }
}
