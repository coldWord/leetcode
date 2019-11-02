## Heap
堆（Heap）是一个可以被看成近似完全二叉树的数组。树上的每一个结点对应数组的一个元素。除了最底层外，该树是完全充满的，而且是从左到右填充。—— 来自：《算法导论》

堆包括最大堆和最小堆：最大堆的每一个节点（除了根结点）的值不大于其父节点；最小堆的每一个节点（除了根结点）的值不小于其父节点。

堆常见的操作：

- HEAPIFY 建堆：把一个乱序的数组变成堆结构的数组，时间复杂度为 O(n)。
- HEAPPUSH：把一个数值放进已经是堆结构的数组中，并保持堆结构，时间复杂度为 O(log n)。
- HEAPPOP：从最大堆中取出最大值或从最小堆中取出最小值，并将剩余的数组保持堆结构，时间复杂度为 O(log n)。
- HEAPSORT：借由 HEAPFY 建堆和 HEAPPOP 堆数组进行排序，时间复杂度为 O(n log n)，空间复杂度为 O(1)。

应用：
- 堆排序
- 优先队列
- 找出第k大元素

## Implementation
```java
public class Heap {
    private int maxSize;
    private int size;
    private int[] heap;

    public Heap() {
        maxSize = 128;
        size = 0;
        heap = new int[maxSize];    
    }

    private int parent(int pos) {
        return pos == 0 ? 0 : (pos-1)/2;
    }

    private int left(int pos) {
        return 2 * pos + 1;
    }

    private int right(int pos) {
        return 2 * pos + 2;
    }

    public void heapPush(int num) {
        if (size < maxSize)
            size++;
        heap[size-1] = num;
        int current = size-1;
        while (heap[current] < heap[parent(current)]) {
            int tmp = heap[current];
            heap[current] = heap[parent(current)];
            heap[parent(current)] = tmp;
            current = parent(current);
        }
    }

    public int heapPop() {
        int min = 0;
        if (size > 0)
            min = heap[0];
        else
            return 0;
        heap[0] = heap[size-1]; // move the last element to the root
        --size;
        int current = 0;
        // if current is not leaf
        while (left(current) < size) {
            int smaller = left(current);
            // find the smallest of the left and right
            if (right(current) < size && heap[right(current)] < heap[smaller])
                smaller = right(current); 
            if (heap[current] < heap[smaller])
                break;
            int tmp = heap[smaller];
            heap[smaller] = heap[current];
            heap[current] = tmp;
            current = smaller;
        }

        return min;
    }

    public void output() {
        for (int i = 0; i < size; ++i) {
            System.out.println(heap[i]);
        }
    }
}
```