class DynamicProgrammingExample {
    public DynamicProgrammingExample() {

    }

    // 一般递归
    public int fibDec(int n) {
        if (n == 1 || n == 2) return 1;
        return fibDec(n - 1) + fibDec(n - 2);
    }

    // 带备忘录的递归
    public int fibDecWithBackup(int[] backup, int n) {
        if (n == 1 || n == 2) {
            backup[1] = 1;
            backup[2] = 1;
            return 1;
        }
        if (backup[n] != 0) return backup[n];
        backup[n] = fibDecWithBackup(backup, n - 1) + fibDecWithBackup(backup, n - 2); 
        return backup[n];
    }

    public int fibDecBottomToTop(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int[] dp = new int[n+1];
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        DynamicProgrammingExample ex = new DynamicProgrammingExample();
        int n = 20;
        long  startTime = System.currentTimeMillis();
        System.out.println("result = " + ex.fibDec(n));
        long endTime = System.currentTimeMillis();
        System.out.println("runtime ：" + (endTime - startTime) + "ms"); 
        int[] backup = new int[n+1];
        startTime = System.currentTimeMillis();
        System.out.println("result = " + ex.fibDecWithBackup(backup, n));
        endTime = System.currentTimeMillis();
        System.out.println("runtime ：" + (endTime - startTime) + "ms"); 
        startTime = System.currentTimeMillis();
        System.out.println("result = " + ex.fibDecBottomToTop(n)); 
        endTime = System.currentTimeMillis();
        System.out.println("runtime ：" + (endTime - startTime) + "ms"); 
    }
}