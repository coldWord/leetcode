## Problem
Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Your **KthLargest** class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.

Example:
```java
int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8
```
Note:
You may assume that nums' length ≥ k-1 and k ≥ 1.

## Idea
建立一个最小堆，然后pop直到堆中只有K个元素。这样可以做到每次add时最多只需要O(logn).

注意堆中元素不足k个时的处理

## Solution
```java
class KthLargest {
    public class HeapMin {
        private int[] heap;
        private int size;
        private int maxSize;
        public HeapMin() {
            size = 0;
            maxSize = 200;
            heap = new int[maxSize];
        }
        
        private void extend() {
            int[] newHeap = new int[2*size];
            // copy old to new
            for (int i = 0; i < size; ++i) {
                newHeap[i] = heap[i];
            }
            maxSize = 2*size;
            heap = newHeap;
        }
        
        private int left(int pos) {
            return 2 * pos + 1;
        }
        
        private int right(int pos) {
            return 2 * pos + 2;
        }
        
        private int parent(int pos) {
            return pos == 0 ? 0 : (pos-1)/2;
        }
        
        public int size() {
            return size;
        }
        
        public int peek() {
            return heap[0];
        }
        
        public void heapPush(int val) {
            if ((size+1) > maxSize)
                extend();
            size++;
            heap[size-1] = val;
            int cur = size - 1;
            while (heap[cur] < heap[parent(cur)]) {
                int tmp = heap[cur];
                heap[cur] = heap[parent(cur)];
                heap[parent(cur)] = tmp;
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
                if (right(cur) < size && heap[right(cur)] < heap[left(cur)])
                    smaller = right(cur);
                if (heap[cur] < heap[smaller])
                    break;
                int tmp = heap[smaller];
                heap[smaller] = heap[cur];
                heap[cur] = tmp;
                cur = smaller;
            }
            return min;
        }
    }
    
    private int k;
    private HeapMin heap;
    
    public KthLargest(int k, int[] nums) {
        this.k = k;
        heap = new HeapMin();
        for (int i = 0; i < nums.length; ++i) {
            heap.heapPush(nums[i]);
        }
        while (heap.size() > k)
            heap.heapPop();
    }
    
    public int add(int val) {
        if (heap.size() < k) {
            heap.heapPush(val);
            return heap.peek();
        }
        
        if (val > heap.peek()) {
            heap.heapPop();
            heap.heapPush(val);
        }
        return heap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
```