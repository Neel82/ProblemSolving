package leetCode.DynamicPrograming;

/*There are n stairs, a person standing at the bottom wants to climb stairs to reach the nth stair.
The person can climb either 1 stair or 2 stairs at a time,
 the task is to count the number of ways that a person can reach at the top.*/
 class TestNStairs{

    static int countWays(int n, int[] dp){
    
        if(n<=1)
        dp[n] = 1;

        if(dp[n]!=-1){
            return dp[n];
        }

        dp[n]= countWays(n-1, dp)+countWays(n-2, dp);
        return dp[n];
    }


    //driver program
    public static void main(String[] args){
        int n=4;
        int[] dp = new int[n+1];
        for(int i=0;i<n+1;i++){
            dp[i] =-1;
        }
        System.out.println(countWays(n, dp));
    }
 }