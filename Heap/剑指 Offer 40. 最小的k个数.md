## problem
输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

## idea
维护一个k大小的大顶堆
(随机化快排)

## solution
```java
// O(nlogk)
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr == null || arr.length == 0)
            return new int[0];
        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (int i = 0; i < k; ++i) {
            pq.offer(arr[i]);
        }
        for (int i = k; i < arr.length; ++i) {
            if (arr[i] < pq.peek()) {
                pq.poll();
                pq.offer(arr[i]);
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; ++i) {
            res[i] = pq.poll();
        }
        return res;
    }
}
```