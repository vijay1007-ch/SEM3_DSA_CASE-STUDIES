public class NetflixOptimization {

    // ---------------- GREEDY ----------------

    static int max(int a, int b) {

        if(a > b)
            return a;

        return b;
    }

    // ---------------- 0/1 KNAPSACK DP ----------------

    static int knapsack(int capacity,
                        int cost[],
                        int score[],
                        int n) {

        int dp[][] =
                new int[n + 1][capacity + 1];

        for(int i = 0; i <= n; i++) {

            for(int w = 0; w <= capacity; w++) {

                if(i == 0 || w == 0) {

                    dp[i][w] = 0;
                }

                else if(cost[i - 1] <= w) {

                    dp[i][w] =
                            Math.max(
                                    score[i - 1]
                                            + dp[i - 1]
                                            [w - cost[i - 1]],

                                    dp[i - 1][w]
                            );
                }

                else {

                    dp[i][w] =
                            dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity];
    }

    // ---------------- MAIN ----------------

    public static void main(String[] args) {

        int cost[] = {2,3,4,5};

        int score[] = {20,30,50,60};

        int budget = 8;

        System.out.println(
                "Greedy Comparison:");

        System.out.println(
                "max(20,30) = "
                        + max(20,30));

        System.out.println(
                "max(50,60) = "
                        + max(50,60));

        int result =
                knapsack(
                        budget,
                        cost,
                        score,
                        cost.length);

        System.out.println(
                "\nMaximum Engagement Score = "
                        + result);
    }
}