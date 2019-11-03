public class Heap {
    private int maxSize;
    private int size;
    private int[] heap;

    public Heap() {
        maxSize = 128;
        size = 0;
        heap = new int[maxSize];    
    }

    public int size() {
        return size;
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

    private void swap(int pos1, int pos2) {
        int tmp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = tmp;
    }

    // 将元素插入至最后，然后向上调整
    public void heapPush(int num) {
        if (size < maxSize)
            size++;
        heap[size-1] = num;
        int current = size-1;
        while (heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // pop root元素，把最后一个元素放在root，然后调整
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
            swap(current, smaller);
            current = smaller;
        }

        return min;
    }

    public int remove(int pos) {
        int res = heap[pos];
        int cur = pos;
        do {
            // cur是叶节点
            if (left(cur) > size) {
                int oldSize = size-1;
                size = cur;
                while (size < oldSize) {
                    heapPush(heap[++cur]);
                }
                break;
            }
            int smaller = left(cur);
            if (right(cur) < size && heap[right(cur)] < heap[smaller])
                smaller = right(cur);
            heap[cur] = heap[smaller];
            cur = smaller;
        } while(true);
        return res;
    }

    public void output() {
        for (int i = 0; i < size; ++i) {
            System.out.println(heap[i]);
        }
    }

    public static void main(String[] args) {
        Heap h = new Heap();
        for (int i = 10; i > 0; --i) {
            h.heapPush(i);
        }
        h.output();
        int val = h.remove(7);
        System.out.println("after remove : " + val);
        System.out.println(h.size());
        h.output();
    }
}