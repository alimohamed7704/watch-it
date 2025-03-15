package Search;
public class SubstringFinder {
    static final double ratio = 0.7;
    public static boolean isFound(String search, String ourData) {
        int m = search.length();
        int n = ourData.length();

        search = search.toLowerCase();
        ourData = ourData.toLowerCase();

        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (search.charAt(i - 1) == ourData.charAt(j - 1)) {
                    // lowercase
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int longestSubString = dp[m][n];
        return longestSubString >= (m * ratio);
    }
}
