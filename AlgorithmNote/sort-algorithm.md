| 算法名   | 最好时间复杂度 | 最坏时间复杂度 | 空间复杂度 |
| -------- | -------------- | -------------- | ---------- |
| 归并排序 | O(nlogn)       | O(nlogn)       | O(n)       |
| 快速排序 | O(nlogn)       | O(n^2)         |            |

## 归并排序(Merge Sort)

### 思想

归并排序就是分治法的一个应用，其可按照上述三步结构分为：

- **分解**：把一个待排的长度为n的序列分解成两个长度为n/2的子序列;

- **求解**：使用归并排序递归地分别排序两个子序列;

- **合并**：合并两个已排序的子序列。

  ![](./mergesort.png)

```java
public class MergeSort {
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left+right)/2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid+1, right);
            merge(arr, left, right);
        }
    }

    private static void merge(int[] arr, int left, int right) {
        int[] mergedArray = new int[right-left+1];
        int mid = (left+right)/2;
        int startLeft = left, startRight = mid+1;
        int pos = 0;
        while (startLeft <= mid && startRight <= right) {
            if (arr[startLeft] < arr[startRight]) {
                mergedArray[pos] = arr[startLeft];
                ++startLeft;
            } else {
                mergedArray[pos] = arr[startRight];
                ++startRight;
            }
            ++pos;
        }

        while (startRight <= right) {
            mergedArray[pos] = arr[startRight];
            ++startRight;
            ++pos;
        }

        while (startLeft <= mid) {
            mergedArray[pos] = arr[startLeft];
            ++startLeft;
            ++pos;
        }

        for (int i = left, j = 0; i <= right; ++i, ++j)
            arr[i] = mergedArray[j];
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 2, 7, 0, 8, 0, 9};
        MergeSort.mergeSort(array, 0, array.length-1);
        for (int i : array) {
            System.out.println(i);
        }
    }
}
```

## 快速排序

### 思想

快速排序也使用了分治法的思想，步骤如下：

- **分解**：把原序列分解成以一个基准元素为分界线的两个子序列，一个的元素都小于基准元素，另一个的元素都大于基准元素;
- **求解**：递归地使用快速排序求解两个子序列;
- **合并**：使用in-place排序，不需要合并。

```java
import java.util.Arrays;

public class QuickSort {
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot-1);
            quickSort(arr, pivot+1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = left;
        ++left;
        while (left < right) {
            if (arr[left] < arr[pivot])
                ++left;
            else {
                // swap arr[left] and arr[right]
                int tmp = arr[right];
                arr[right] = arr[left];
                arr[left] = tmp;

                --right;
            }
        }
        if (left == right) {
            int tmp = arr[left];
            arr[left] = arr[pivot];
            arr[pivot] = tmp;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 2, 7, 0, 8, 0, 9};
        MergeSort.mergeSort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }
}
```






