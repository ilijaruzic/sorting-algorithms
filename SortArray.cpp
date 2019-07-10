#include <iostream>
#include <cstdlib>
#include "SortArray.h"

// README
// Time Complexity: Worst-case, Best-case, Average
// Space Complexity: Total, Auxiliary

void swap(int *array, int i, int j) {
	int temp = array[i];
	array[i] = array[j];
	array[j] = temp;
}

// Insertion Sort
// Time Complexity: O(n^2) comparisons and swaps, O(n) comparisons and O(1) swaps, O(n^2) comparisons and swaps
// Space Complexity: O(n), O(1)
void SortArray::insertionSort(int *array, int size) {
	for (int i = 1; i < size; ++i) {
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
void SortArray::shellSort(int *array, int size) {
	const int increments[] = { 701, 301, 132, 57, 23, 10, 4, 1 };
	int inc_size = sizeof(increments) / sizeof(increments[0]);
	for (int i = 0; i < inc_size; i++) {
		int increment = increments[i];
		for (int j = increment; j < size; j++) {
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
void SortArray::selectionSort(int *array, int size) {
	for (int i = 0; i < size - 1; i++) {
		int min = array[i];
		int position = i;
		for (int j = i + 1; j < size; j++)
			if (array[j] < min) {
				min = array[j];
				position = j;
			}
		array[position] = array[i];
		array[i] = min;
	}
}

void adjust(int *array, int size, int index) {
	int left = 2 * index + 1;
	int right = 2 * index + 2;
	int root = index;
	if (left < size && array[left] > array[root]) root = left;
	if (right < size && array[right] > array[root]) root = right;
	if (root != index) {
		swap(array, index, root);
		adjust(array, size, root);
	}
}

// Heap Sort
// Time Complexity: O(n*log(n)), O(n*log(n)) or O(n), O(n*log(n))
// Space Complexity: O(n), O(1)
void SortArray::heapSort(int *array, int size) {
	for (int i = size / 2 - 1; i >= 0; i--)
		adjust(array, size, i);
	for (int i = size - 1; i >= 0; i--) {
		swap(array, 0, i);
		adjust(array, i, 0);
	}
}

// Bubble Sort
// Time Complexity: O(n^2) comparisons and swaps, O(n^2) or O(n) comparisons and O(1) swaps, O(n^2) comparisons and O(n^2) swaps
// Space Complexity: O(n), O(1)
void SortArray::bubbleSort(int *array, int size) {
	for (int i = size - 1; i > 0; i--)
		for (int j = 0; j < i; j++)
			if (array[j] > array[j + 1])
				swap(array, j, j + 1);
}

// Shaker (Cocktail) Sort
// Time Complexity: O(n^2), O(n), O(n^2) 
// Space Complexity: O(n), O(1)
void SortArray::shakerSort(int *array, int size) {
	bool swapped = true;
	int low = 0;
	int high = size;
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

int partition(int *array, int down, int up) {
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
void SortArray::quickSort(int *array, int low, int high) {
	if (low < high) {
		int j = partition(array, low, high);
		quickSort(array, low, j - 1);
		quickSort(array, j + 1, high);
	}
}

// Counting Sort
// Time Complexity: O(n+k), -, -
// Space Complexity: O(n+k), -
void SortArray::countingSort(int *array, int size) {
	int cnt_size = 256, *counters = new int[cnt_size];
	int *sortedArray = new int[size];
	for (int i = 0; i < cnt_size; ++i)
		counters[i] = 0;
	for (int j = 0; j < size; ++j)
		++counters[array[j]];
	for (int i = 1; i <= cnt_size - 1; ++i)
		counters[i] += counters[i - 1];
	for (int j = size - 1; j >= 0; j--) {
		sortedArray[counters[array[j]] - 1] = array[j];
		--counters[array[j]];
	}
	for (int i = 0; i < size; ++i)
		array[i] = sortedArray[i];
}

// Bucket Sort
// Time Complexity: O(n^2), -, O(n+n^2/k+k) or O(n)
// Space Complexity: O(n*k), -
void SortArray::bucketSort(int *array, int size) {
	int max = maximum(array, size);
	int bkt_size = max + 1, *bucket = new int[bkt_size];
	int *sortedArray = new int[size];
	for (int i = 0; i < bkt_size; i++)
		bucket[i] = 0;
	for (int j = 0; j < size; j++)
		bucket[array[j]]++;
	int position = 0;
	for (int i = 0; i < bkt_size; i++)
		for (int j = 0; j < bucket[i]; j++)
			sortedArray[position++] = i;
	for (int i = 0; i < size; ++i)
		array[i] = sortedArray[i];
}

void countingSortDigits(int *array, int size, int digit) {
	int cnt_size = 10, *counters = new int[cnt_size];
	int *sortedArray = new int[size];
	for (int i = 0; i < cnt_size; i++)
		counters[i] = 0;
	for (int j = 0; j < size; j++)
		counters[(array[j] / digit) % 10]++;
	for (int i = 1; i < cnt_size; i++)
		counters[i] += counters[i - 1];
	for (int j = size - 1; j >= 0; j--) {
		sortedArray[counters[(array[j] / digit) % 10] - 1] = array[j];
		counters[(array[j] / digit) % 10]--;
	}
	for (int i = 0; i < size; ++i)
		array[i] = sortedArray[i];
}

// Radix Sort
// Time Complexity: O(w*n), -, -
// Space Complexity: O(w+n), -
void SortArray::radixSort(int *array, int size) {
	int max = maximum(array, size);
	for (int digit = 1; max / digit > 0; digit *= 10)
		countingSortDigits(array, size, digit);
}

void split(int *array, int left, int medium, int right) {
	int length1 = medium - left + 1;
	int length2 = right - medium;
	int *leftSubarray = new int[length1], *rightSubarray = new int[length2];
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
void SortArray::mergeSort(int *array, int low, int high) {
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
void SortArray::twoWayMergeSort(int *array1, int size1, int *array2, int size2, int *array3) {
	mergeSort(array1, 0, size1 - 1);
	mergeSort(array2, 0, size2 - 1);
	int i = 0, j = 0, k = 0;
	while (i < size1 && j < size2) {
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
	while (i < size1) {
		array3[k] = array1[i];
		i++;
		k++;
	}
	while (j < size2) {
		array3[k] = array2[j];
		j++;
		k++;
	}
}

int SortArray::minimum(int *array, int size) {
	int min = array[0];
	for (int i = 1; i < size; i++)
		if (min > array[i]) min = array[i];
	return min;
}

int SortArray::maximum(int *array, int size) {
	int max = array[0];
	for (int i = 1; i < size; i++)
		if (max < array[i]) max = array[i];
	return max;
}

void SortArray::print(int *array, int size) {
	for (int i = 0; i < size; i++)
		std::cout << array[i] << " ";
	puts("\n");
}

void SortArray::peek(int *array) {
	for (int i = 0; i < 49; i++)
		std::cout << array[i] << " ";
	puts("...\n");
}