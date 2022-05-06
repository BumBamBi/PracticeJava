package CT.HH_QC.q1_1;

class Solution {
    static int SIZE;
    static boolean[] check;
    static int[][] inputs;
    static int answer = 1;

    public int solution(int[][] circles) {

        SIZE = circles.length;
        check = new boolean[SIZE];
        inputs = copy(circles);

        dfs(0, 0);

        return answer;
    }

    public int[][] copy(int[][] circles) {
        int[][] result = new int[SIZE][3];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = circles[i][j];
            }
        }
        return result;
    }

    public void dfs(int idx, int cnt) {
        if (idx == SIZE) {
            if (cnt >= answer && cnt > 1) {
                simulate();
            }
            return;
        } else {
            check[idx] = true;
            dfs(idx + 1, cnt + 1);
            check[idx] = false;
            dfs(idx + 1, cnt + 1);
        }
    }
    
    public void simulate() {
        // if(두점의 거리 + 작은 반지름 <= 큰 반지름) : 올바른 예시
        // 보다 큰것을 남겨서 계속 비교해야 함
        System.out.println("z");
        boolean isFirst = true;
        int[] std = new int[3];
        int tempCnt = 1;
        for (int i = 0; i < SIZE; i++) {
            if (check[i]) {
                if (isFirst) {
                    int x = inputs[i][0];
                    int y = inputs[i][1];
                    int r = inputs[i][2];
                    std = new int[] { x, y, r };
                    isFirst = false;
                } else {
                    int r = inputs[i][2];

                    int[] big = new int[3];
                    int[] small = new int[3];

                    if (std[2] >= r) {
                        for (int j = 0; j < 3; j++) {
                            big[j] = std[j];
                            small[j] = inputs[i][j];
                        }
                    } else if (std[2] < r) {
                        for (int j = 0; j < 3; j++) {
                            small[j] = std[j];
                            big[j] = inputs[i][j];
                        }
                    }

                    // 각 값 최대 10억
                    double dist = Math.sqrt(Math.pow(Math.abs(big[0] - small[0]), 2) + Math.pow(Math.abs(big[1] - small[1]), 2));
                    if (dist + small[2] <= big[2]) {
                        tempCnt++;
                    }
                    // std = 큰 배열 값으로 초기화
                    for (int j = 0; j < 3; j++) {
                        std[j] = big[j];
                    }
                }
            }
        }
        if (tempCnt > answer) {
            answer = tempCnt;
        }
    }
}