// README
// Time Complexity: Worst-case, Best-case, Average
// Space Complexity: Total, Auxiliary
public class SortArray {

    private SortArray() {}

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Insertion Sort
    // Time Complexity: O(n^2) comparisons and swaps, O(n) comparisons and O(1) swaps, O(n^2) comparisons and swaps
    // Space Complexity: O(n), O(1)
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; ++i) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    // Shell Sort
    // Time Complexity: O(n^2) or O(n*log^2(n)), O(n*log(n)) or O(n*log^2(n)), depends
    // Auxiliary Space: O(n), O(1)
    public static void shellSort(int[] array) {
        final int[] increments = {701, 301, 132, 57, 23, 10, 4, 1};
        for (int i = 0; i < increments.length; i++) {
            int increment = increments[i];
            for (int j = increment; j < array.length; j++) {
                int temp = array[j], key;
                for (key = j; key >= increment && temp < array[key - increment]; key -= increment)
                    array[key] = array[key - increment];
                array[key] = temp;
            }
        }
    }

    // Selection Sort
    // Time Complexity: O(n^2) comparisons and O(n) swaps, O(n^2) comparisons and O(n) swaps, O(n^2) comparisons and O(n) swaps
    // Space Complexity: O(n), O(1)
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = array[i];
            int position = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j] < min) {
                    min = array[j];
                    position = j;
                }
            array[position] = array[i];
            array[i] = min;
        }
    }

    private static void adjust(int[] array, int length, int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int root = index;
        if (left < length && array[left] > array[root]) root = left;
        if (right < length && array[right] > array[root]) root = right;
        if (root != index) {
            swap(array, index, root);
            adjust(array, length, root);
        }
    }

    // Heap Sort
    // Time Complexity: O(n*log(n)), O(n*log(n)) or O(n), O(n*log(n))
    // Space Complexity: O(n), O(1)
    public static void heapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--)
            adjust(array, array.length, i);
        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, 0, i);
            adjust(array, i, 0);
        }
    }

    // Bubble Sort
    // Time Complexity: O(n^2) comparisons and swaps, O(n^2) or O(n) comparisons and O(1) swaps, O(n^2) comparisons and O(n^2) swaps
    // Space Complexity: O(n), O(1)
    public static void bubbleSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--)
            for (int j = 0; j < i; j++)
                if (array[j] > array[j + 1])
                    swap(array, j, j + 1);
    }

    // Shaker (Cocktail) Sort
    // Time Complexity: O(n^2), O(n), O(n^2)
    // Space Complexity: O(n), O(1)
    public static void shakerSort(int[] array) {
        boolean swapped = true;
        int low = 0;
        int high = array.length;
        while (swapped) {
            swapped = false;
            for (int i = low; i < high - 1; ++i)
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    swapped = true;
                }
            if (!swapped) break;
            swapped = false;
            high--;
            for (int i = high - 1; i >= low; i--)
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    swapped = true;
                }
            low++;
        }
    }

    private static int partition(int[] array, int down, int up) {
        int i = down - 1;
        int pivot = array[up];
        for (int j = down; j <= up - 1; j++)
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        swap(array, i + 1, up);
        return i + 1;
    }

    // Quick Sort
    // Time Complexity: O(n^2), O(n*log(n)) or O(n), O(n*log(n))
    // Space Complexity: O(log(n)), O(n) or O(log(n))
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int j = partition(array, low, high);
            quickSort(array, low, j - 1);
            quickSort(array, j + 1, high);
        }
    }

    // Counting Sort
    // Time Complexity: O(n+k), -, -
    // Space Complexity: O(n+k), -
    public static void countingSort(int[] array) {
        int[] counters = new int[256];
        int[] sortedArray = new int[array.length];
        for (int i = 0; i < counters.length; ++i)
            counters[i] = 0;
        for (int j = 0; j < array.length; ++j)
            ++counters[array[j]];
        for (int i = 1; i <= counters.length - 1; ++i)
            counters[i] += counters[i - 1];
        for (int j = array.length - 1; j >= 0; j--) {
            sortedArray[counters[array[j]] - 1] = array[j];
            --counters[array[j]];
        }
        for (int i = 0; i < array.length; ++i)
            array[i] = sortedArray[i];
    }

    // Bucket Sort
    // Time Complexity: O(n^2), -, O(n+n^2/k+k) or O(n)
    // Space Complexity: O(n*k), -
    public static void bucketSort(int[] array) {
        int max = maximumElement(array);
        int[] bucket = new int[max + 1];
        int[] sortedArray = new int[array.length];
        for (int i = 0; i < bucket.length; i++)
            bucket[i] = 0;
        for (int j = 0; j < array.length; j++)
            bucket[array[j]]++;
        int position = 0;
        for (int i = 0; i < bucket.length; i++)
            for (int j = 0; j < bucket[i]; j++)
                sortedArray[position++] = i;
        for (int i = 0; i < array.length; ++i)
            array[i] = sortedArray[i];
    }

    private static void countingSortDigits(int[] array, int digit) {
        int[] counters = new int[10];
        int[] sortedArray = new int[array.length];
        for (int i = 0; i < counters.length; i++)
            counters[i] = 0;
        for (int j = 0; j < array.length; j++)
            counters[(array[j] / digit) % 10]++;
        for (int i = 1; i < counters.length; i++)
            counters[i] += counters[i - 1];
        for (int j = array.length - 1; j >= 0; j--) {
            sortedArray[counters[(array[j] / digit) % 10] - 1] = array[j];
            counters[(array[j] / digit) % 10]--;
        }
        for (int i = 0; i < array.length; ++i)
            array[i] = sortedArray[i];
    }

    // Radix Sort
    // Time Complexity: O(w*n), -, -
    // Space Complexity: O(w+n), -
    public static void radixSort(int[] array) {
        int max = maximumElement(array);
        for (int digit = 1; max / digit > 0; digit *= 10)
            countingSortDigits(array, digit);
    }

    private static void split(int[] array, int left, int medium, int right) {
        int length1 = medium - left + 1;
        int length2 = right - medium;
        int[] leftSubarray = new int[length1], rightSubarray = new int[length2];
        for (int i = 0; i < length1; ++i)
            leftSubarray[i] = array[left + i];
        for (int i = 0; i < length2; ++i)
            rightSubarray[i] = array[medium + i + 1];
        int p = 0, q = 0, r = left;
        while (p < length1 && q < length2) {
            if (leftSubarray[p] <= rightSubarray[q]) {
                array[r] = leftSubarray[p];
                p++;
            }
            else {
                array[r] = rightSubarray[q];
                q++;
            }
            r++;
        }
        while (p < length1) {
            array[r] = leftSubarray[p];
            p++;
            r++;
        }
        while (q < length2) {
            array[r] = rightSubarray[q];
            q++;
            r++;
        }
    }

    // Merge Sort
    // Time Complexity: O(n*log(n)), O(n*log(n)) or O(n), O(n*log(n))
    // Space Complexity: O(n), O(n) or O(1)
    public static void mergeSort(int[] array, int low, int high) {
        if (low < high) {
            int medium = (low + high) / 2;
            mergeSort(array, low, medium);
            mergeSort(array, medium + 1, high);
            split(array, low, medium, high);
        }
    }

    // Two-way Merge Sort
    // Time Complexity: O(n*log(n)+m*log(m)+(n+m)), -, -
    // Space Complexity: O(n+m), -
    public static void twoWayMergeSort(int[] array1, int[] array2, int[] array3) {
        mergeSort(array1, 0, array1.length - 1);
        mergeSort(array2, 0, array2.length - 1);
        int i = 0, j = 0, k = 0;
        while (i < array1.length && j < array2.length) {
            if (array1[i] <= array2[j]) {
                array3[k] = array1[i];
                i++;
                k++;
            }
            else {
                array3[k] = array2[j];
                j++;
                k++;
            }
        }
        while (i < array1.length) {
            array3[k] = array1[i];
            i++;
            k++;
        }
        while (j < array2.length) {
            array3[k] = array2[j];
            j++;
            k++;
        }
    }

    public static int minimumElement(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++)
            if (min > array[i]) min = array[i];
        return min;
    }

    public static int maximumElement(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++)
            if (max < array[i]) max = array[i];
        return max;
    }

    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");;
        System.out.println();
    }


    public static void peek(int[] array) {
        for (int i = 0; i < 49; i++)
            System.out.print(array[i] + " ");;
        System.out.println("...");
    }
}
