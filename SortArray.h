#ifndef SORTARRAY_H
#define SORTARRAY_H

class SortArray {
public:
	SortArray() = delete;
	SortArray(const SortArray& s) = delete;
	SortArray(SortArray&& s) = delete;
	~SortArray() = delete;

	static void insertionSort(int *array, int size);
	static void shellSort(int *array, int size);
	static void selectionSort(int *array, int size);
	static void heapSort(int *array, int size);
	static void bubbleSort(int *array, int size);
	static void shakerSort(int *array, int size);
	static void quickSort(int *array, int low, int high);
	static void countingSort(int *array, int size);
	static void bucketSort(int *array, int size);
	static void radixSort(int *array, int size);
	static void mergeSort(int *array, int low, int high);
	static void twoWayMergeSort(int *array1, int size1, int *array2, int size2, int *array3);

	static int minimum(int *array, int size);
	static int maximum(int *array, int size);

	static void print(int *array, int size);
	static void peek(int *array);
};

#endif