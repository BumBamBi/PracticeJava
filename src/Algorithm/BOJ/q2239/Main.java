package Algorithm.BOJ.q2239;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    static int[][] matrix;
    static LinkedList<Point> list;

    static class Point {
        int i;
        int j;
        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        matrix = new int[9][9];
        list = new LinkedList<Point>();

        for (int i = 0; i < 9; i++) {
            String s = sc.next();
            for (int j = 0; j < 9; j++) {
                matrix[i][j] = s.charAt(j) - '0';
                if (matrix[i][j] == 0) {
                    list.add(new Point(i, j));
                }
            }
        }

        dfs(0);

        sc.close();
    }

    static void dfs(int cnt) {
        // cnt의 개수가 list와 같으면 모든 0을 바꾼것.
        if(list.size() == cnt) {
            for(int i=0;i<9;i++){
                for (int j = 0; j < 9; j++) {
                    System.out.print(matrix[i][j]);
                }
                System.out.println();
            }
            System.exit(0);
        } else {
            int i = list.get(cnt).i;
            int j = list.get(cnt).j;
            
            for (int k = 1; k <= 9; k++) {
                if(isAvailable(i,j,i)){
                    matrix[i][j] = k;
                    dfs(cnt+1);
                    matrix[i][j] = 0;
                }
            }
        }
        
    }

    private static boolean isAvailable(int ii, int jj, int kk) {
        // 가로 세로 중 같은 숫자가 있다면
        for (int i = 0; i < 9; i++) {
            if (matrix[ii][i] == kk || matrix[i][jj] == kk) {
                return false;
            }
        }

        // 박스 중 같은 숫자가 있다면
        int bi = (ii / 3) * 3; // 0 3 6
        int bj = (jj / 3) * 3; // 0 3 6
        
        for (int i = bi; i < bi + 3; i++) {
            for (int j = bj; j < bj + 3; j++) {
                if (matrix[i][j] == kk) {
                    return false;
                }
            }
        }

        return true;
    }
}
