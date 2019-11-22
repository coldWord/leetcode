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

    public static void main(String[] args) {
        
    }
}