public class SortingAlgorithms {

    // Direktno umetanje
    public static final void insertion(int[] array) {
        insertionSort(array);
    }

    // Umetanje sa smanjenjem inkementa
    public static final void shell(int[] array) {
        shellSort(array);
    }

    // Direktna selekcija
    public static final void selection(int[] array) {
        selectionSort(array);
    }

    // Sortiranje pomoću binarnog stabla selekcije
    public static final void heap(int[] array) {
        heapSort(array);
    }

    // Direktna zamena
    public static final void bubble(int[] array) {
        bubbleSort(array);
    }

    // Direktna zamena – modifikacija bubble sort-a
    public static final void shaker(int[] array) {
        shakerSort(array);
    }

    // Particijsko sortiranje
    public static final void quick(int[] array, int low, int high) {
        quickSort(array, low, high);
    }

    // Sortiranje brojanjem
    public static final void counting(int[] array) {
        countingSort(array);
    }

    // Adresno sortiranje
    public static final void bucket(int[] array) {
        bucketSort(array);
    }

    // Radix sortiranje
    public static final void radix(int[] array) {
        radixSort(array);
    }

    // Direktno spajanje
    public static final void merge(int[] array, int low, int high) {
        mergeSort(array, low, high);
    }

    // Dvoulazno spajanje
    public static final void twoway_merge(int[] array1, int low1, int high1, int[] array2, int low2, int high2, int[] array3, int low3) {
        twoWayMergeSort(array1, low1, high1, array2, low2, high2, array3, low3);
    }

    // Određivanje minimuma
    public static final int minimum(int[] array) {
        return minimumElement(array);
    }

    // Određivanje maksimuma
    public static final int maximum(int[] array) {
        return maximumElement(array);
    }

    // Određivanje k-tog najmanjeg  i najvećeg elementa
    // TODO

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void insertionSort(int[] array) {
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

    private static void shellSort(int[] array) {
        int[] increments = {701, 301, 132, 57, 23, 10, 4, 1};
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

    private static void selectionSort(int[] array) {
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
    private static void heapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--)
            adjust(array, array.length, i);
        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, 0, i);
            adjust(array, i, 0);
        }
    }

    private static void bubbleSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--)
            for (int j = 0; j < i; j++)
                if (array[j] > array[j + 1])
                    swap(array, j, j + 1);
    }

    private static void shakerSort(int[] array) {
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
    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int j = partition(array, low, high);
            quickSort(array, low, j - 1);
            quickSort(array, j + 1, high);
        }
    }

    private static void countingSort(int[] array) {
        int[] counters = new int[256], sortedArray = new int[array.length];
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

    private static void bucketSort(int[] array) {
        int max = maximumElement(array);
        int[] bucket = new int[max + 1], sortedArray = new int[array.length];
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
        int[] counters = new int[10], sortedArray = new int[array.length];
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
    private static void radixSort(int[] array) {
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
    private static void mergeSort(int[] array, int low, int high) {
        if (low < high) {
            int medium = (low + high) / 2;
            mergeSort(array, low, medium);
            mergeSort(array, medium + 1, high);
            split(array, low, medium, high);
        }
    }

    // FIXME: Uspešno spaja dva niza, ali ih ne sortira
    private static void twoWayMergeSort(int[] array1, int low1, int high1, int[] array2, int low2, int high2, int[] array3, int low3) {
        int i = low1;
        int j = low2;
        int k = low3;
        while (i <= high1 && j <= high2) {
            if (array1[i] < array2[j]) {
                array3[k] = array1[i];
                i++;
            }
            else {
                array3[k] = array2[j];
                j++;
            }
            k++;
        }
        if (i > high1)
            while (j <= high2) {
                array3[k] = array2[j];
                j++;
                k++;
            }
        else
            while (i <= high1) {
                array3[k] = array1[i];
                i++;
                k++;
            }
    }

    private static int minimumElement(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++)
            if (min > array[i]) min = array[i];
        return min;
    }

    private static int maximumElement(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++)
            if (max < array[i]) max = array[i];
        return max;
    }
}
