package CT.HH_QC.q2_1;

import java.util.*;

class Solution {
    static final int INF = 999999999;
    static int LEN;
    
    public int solution(String[] strs, String t) {
        LEN = t.length();

        // set생성 후 삽입
        Set<String> mySet = new HashSet<String>();
        Collections.addAll(mySet, strs);

        int dp[] = new int[200002];

        // 초기화
        for(int i = 0; i< LEN; i++){
            dp[i] = INF;
        }
        dp[LEN] = 0;

        // dp 수행
        for (int i = LEN - 1; i >= 0; i--) {
            String temp = "";
            for (int j = 0; i + j < LEN && j < 5; j++) {
                temp += t.charAt(i + j);
                //System.out.println(temp);

                if (mySet.contains(temp) && dp[i + j + 1] != INF) {
                    dp[i] = Math.min(dp[i], dp[i + j + 1] + 1);
                }
            }
        }

        // 출력
        if (dp[0] == INF) {
            return -1;
        } else {
            return dp[0];
        }
    }
}