## Problem
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:
```
输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
说明:
```
你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

## Idea
1. 排序
2. 用K个元素的最小堆，先遍历数组前k个元素，建堆，然后判断后续元素与堆顶的大小，若后续元素大，，就删除堆顶把该元素加入堆中。

## Solution
```java
class Solution {
    class HeapMin {
        private int size;
        private int[] heap;
        private int maxSize;
        public HeapMin(int k) {
            size = 0;
            maxSize = k;
            heap = new int[maxSize];
        }
        
        private int parent(int pos) {
            return pos == 0 ? 0 : (pos-1)/2;
        }
        
        private int left(int pos) {
            return 2*pos + 1;
        }
        
        private int right(int pos) {
            return 2*pos + 2;
        }
        
        public int peek() {
            return heap[0];
        }
        
        public void swap(int pos1, int pos2) {
            int tmp = heap[pos1];
            heap[pos1] = heap[pos2];
            heap[pos2] = tmp;
        }
        
        public void heapPush(int val) {
            if (size < maxSize)
                size++;
            heap[size-1] = val;
            int cur = size-1;
            while (heap[cur] < heap[parent(cur)]) {
                swap(cur, parent(cur));
                cur = parent(cur);
            }
        }
        
        public int heapPop() {
            if (size > 0)
                size--;
            int min = heap[0];
            heap[0] = heap[size];
            int cur = 0;
            while (left(cur) < size) {
                int smaller = left(cur);
                if (right(cur) < size)
                    smaller = heap[right(cur)] < heap[smaller] ? right(cur) : smaller;
                if (heap[cur] < heap[smaller])
                    break;
                swap(cur, smaller);
                cur = smaller;
            }
            return min;
        }
        
        public int size() {
            return size;
        }
    }
    
    public int findKthLargest(int[] nums, int k) {
        HeapMin h = new HeapMin(k);
        // k * logk
        for (int i = 0; i < k; ++i) {
            h.heapPush(nums[i]);
        }
        
        // 2(n-k) * logk
        for (int i = k; i < nums.length; ++i) {
            if (nums[i] > h.peek()) {
                h.heapPop();
                h.heapPush(nums[i]);
            }
        }
        return h.peek();
    }
}
```