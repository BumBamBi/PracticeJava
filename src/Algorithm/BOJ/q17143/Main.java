package Algorithm.BOJ.q17143;

import java.util.Scanner;

public class Main {
    static int R, C, M;
    static Shark[][][] matrix;
    static int[][] matrixIdx;
    static int result = 0;

    static class Shark{
        int y;
        int x;
        int speed;
        int direction; // 1 위 / 2 아래 / 3 오른쪽 / 4 왼쪽
        int size;

        public Shark(int y, int x, int speed, int direction, int size) {
            this.y = y;
            this.x = x;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }

        public Shark(int size) {
            this.size = size;
        }
        
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        M = sc.nextInt();

        matrix = new Shark[R+1][C+1][M];
        matrixIdx = new int[R+1][C+1];

        if (M == 0) {
            System.out.println(result);
            sc.close();
            return;
        }

        for (int i = 0; i < M; i++) {
            int[] input = new int[5];
            for (int j = 0; j < 5; j++) {
                input[j] = sc.nextInt();
            }
            matrix[input[0]][input[1]][0] = new Shark(input[0], input[1], input[2], input[3], input[4]);
        }

        for (int curCol = 1; curCol <= C; curCol++) {
            catchShark(curCol);
            moveShark();
        }

        System.out.println(result);

        sc.close();
    }

    private static void catchShark(int curCol) {
        for (int i = 1; i <= R; i++) {
            if (matrix[i][curCol][0] != null) {
                result += matrix[i][curCol][0].size;
                matrix[i][curCol][0] = null;
                return;
            }
        }
    }
    
    private static void moveShark() {

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (matrix[i][j][0] != null) {
                    // 대기열로 이동
                    if (matrix[i][j][0].speed > 0) {
                        move(matrix[i][j][0]);
                    } else {
                        matrixIdx[i][j]++;
                        matrix[i][j][matrixIdx[i][j]] = matrix[i][j][0];
                    }

                    // 삭제
                    matrix[i][j][0] = null;
                }
            }
        }

        // 잡아먹기 계산
        calcEating();
    }

    private static void move(Shark shark) {
        boolean isDone = false;
        int remainDistance = shark.speed;

        while (!isDone) {
            if (shark.direction == 1) {
                if (shark.y - remainDistance >= 1) {
                    shark.y -= remainDistance;
                    isDone = true;
                } else {
                    remainDistance -= (shark.y-1);
                    shark.y = 1;
                    shark.direction = 2;
                }
            } else if (shark.direction == 2) {
                if (shark.y + remainDistance <= R) {
                    shark.y += remainDistance;
                    isDone = true;
                } else {
                    remainDistance -= (R - shark.y);
                    shark.y = R;
                    shark.direction = 1;
                }
            } else if (shark.direction == 3) {
                if (shark.x + remainDistance <= C) {
                    shark.x += remainDistance;
                    isDone = true;
                } else {
                    remainDistance -= (C - shark.x);
                    shark.x = C;
                    shark.direction = 4;
                }
            } else if (shark.direction == 4) {
                if (shark.x - remainDistance >= 1) {
                    shark.x -= remainDistance;
                    isDone = true;
                } else {
                    remainDistance -= (shark.x-1);
                    shark.x = 1;
                    shark.direction = 3;
                }
            }
        }

        //
        matrixIdx[shark.y][shark.x]++;
        matrix[shark.y][shark.x][matrixIdx[shark.y][shark.x]] = shark;

    }

    private static void calcEating() {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (matrix[i][j][1] != null) {
                    Shark bigShark = new Shark(-1);
                    for (int k = 1; k <= matrixIdx[i][j]; k++) {
                        if (bigShark.size < matrix[i][j][k].size) {
                            bigShark = matrix[i][j][k];
                        }
                        matrix[i][j][k] = null;
                    }
                    matrix[i][j][0] = bigShark;
                    matrixIdx[i][j] = 0;
                }
            }
        }
    }
}
