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
class KthLargest {
    class HeapMin {
        int[] heap;
        int size;
        int maxSize;
        
        HeapMin() {
            maxSize = 20;
            size = 0;
            heap = new int[maxSize];
        }
        
        int parent(int pos) { return (pos == 0) ? 0 : (pos-1)/2; }
        
        int left(int pos) { return 2*pos+1; }
        
        int right(int pos) { return 2*pos+2; }
        
        void swap(int x, int y) {
            int tmp = heap[x];
            heap[x] = heap[y];
            heap[y] = tmp;
        }
        
        // extend max size
        void extend() {
            int[] newHeap = new int[maxSize*2];
            for (int i = 0; i < maxSize; ++i)
                newHeap[i] = heap[i];
            heap = newHeap;
            maxSize *= 2;
        }
        
        public int size() { return size; }
        
        public void insert(int val) {
            if (++size > maxSize)
                extend();
            heap[size-1] = val;
            int cur = size-1;
            while (heap[cur] < heap[parent(cur)]) {
                swap(cur, parent(cur));
                cur = parent(cur);
            }
        }
        
        public int pop(){
            if (size <= 0) return 0; 
            int min = heap[0];
            heap[0] = heap[--size];
            int cur = 0;
            while (left(cur) < size) {
                int smaller = left(cur);
                if (right(cur) < size && heap[right(cur)] < heap[smaller])
                    smaller = right(cur);
                if (heap[cur] < heap[smaller]) break; // smaller than left and right child
                swap(cur, smaller);
                cur = smaller;
            }
            return min;
        }
        
        public int peek() { return heap[0]; }
    }
    
    private HeapMin heapMin;
    private boolean isFull;
    
    public KthLargest(int k, int[] nums) {
        if (k > nums.length) isFull = false;
        else isFull = true;
        heapMin = new HeapMin();
        for (int i = 0; i < nums.length; ++i) {
            if (heapMin.size() <= k) {
                heapMin.insert(nums[i]);
                continue;
            }
            if (heapMin.peek() < nums[i]) {
                heapMin.pop();
                heapMin.insert(nums[i]);
            }
        }
    }
    
    public int add(int val) {
        if (!isFull) {
            heapMin.insert(val);
            isFull = true;
        } else if (val > heapMin.peek()) {
            heapMin.pop();
            heapMin.insert(val);
        }
        return heapMin.peek();
    }
}
```