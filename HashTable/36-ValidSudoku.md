## Problem

判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。

## Solution

```java
// 可以遍历一次完成
class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; ++i) {
            if (!isValidRow(board[i]))
                return false;
        }
        for (int i = 0; i < board.length; ++i) {
            if (!isValidCol(board, i))
                return false;
        }
        for (int i = 0; i < board.length; i += 3) {
            for (int j = 0; j < board.length; j += 3)
                if (!isValidSub(board, i, j))
                    return false;
        }
        return true;
    }
    
    private boolean isValidSub(char[][] board, int posX, int posY) {
        int[] counter = new int[board.length];
        for (int i = 0; i < Math.sqrt(board.length); ++i) {
            for (int j = 0; j < Math.sqrt(board.length); ++j) {
                if (board[i+posX][j+posY] == '.')
                    continue;
                else {
                    int tmp = (int)board[i+posX][j+posY] - (int)'0';
                    int key = tmp-1;
                    if (counter[key] == 0)
                        ++counter[key];
                    else
                        return false;   
                }
            }
        }
        return true;
    }
    
    private boolean isValidCol(char[][] board, int pos) {
        int[] counter = new int[board.length];
        for (int i = 0; i < board.length; ++i) {
            if (board[i][pos] == '.')
                continue;
            else {
                int tmp = (int)board[i][pos] - (int)'0';
                int key = tmp-1;
                if (counter[key] == 0)
                    ++counter[key];
                else
                    return false;   
            }
        }
        return true;
    }
    
    private boolean isValidRow(char[] row) {
        int[] counter = new int[row.length];
        for (int i = 0; i < row.length; ++i) {
            if (row[i] == '.')
                continue;
            else {
                int tmp = (int)row[i] - (int)'0';
                int key = tmp-1;
                if (counter[key] == 0)
                    ++counter[key];
                else
                    return false;   
            }
        }
        return true;
    }
}
```

