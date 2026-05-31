import java.util.Arrays;

public class SpotifySorting {

    // ---------------- MERGE SORT ----------------

    static void merge(int arr[], int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for(int i = 0; i < n1; i++)
            L[i] = arr[l + i];

        for(int j = 0; j < n2; j++)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while(i < n1 && j < n2) {

            if(L[i] <= R[j])
                arr[k++] = L[i++];
            else
                arr[k++] = R[j++];
        }

        while(i < n1)
            arr[k++] = L[i++];

        while(j < n2)
            arr[k++] = R[j++];
    }

    static void mergeSort(int arr[], int l, int r) {

        if(l < r) {

            int m = (l + r) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    // ---------------- QUICK SORT ----------------

    static int partition(int arr[], int low, int high) {

        int pivot = arr[high];

        int i = low - 1;

        for(int j = low; j < high; j++) {

            if(arr[j] < pivot) {

                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    static void quickSort(int arr[], int low, int high) {

        if(low < high) {

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // ---------------- HEAP SORT ----------------

    static void heapify(int arr[], int n, int i) {

        int largest = i;

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if(left < n && arr[left] > arr[largest])
            largest = left;

        if(right < n && arr[right] > arr[largest])
            largest = right;

        if(largest != i) {

            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }

    static void heapSort(int arr[]) {

        int n = arr.length;

        for(int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for(int i = n - 1; i > 0; i--) {

            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    // ---------------- MAIN ----------------

    public static void main(String[] args) {

        int data[] = {45, 12, 78, 34, 23, 90};

        int mergeArr[] = data.clone();
        int quickArr[] = data.clone();
        int heapArr[] = data.clone();

        mergeSort(mergeArr, 0, mergeArr.length - 1);

        System.out.println("Merge Sort:");
        System.out.println(Arrays.toString(mergeArr));

        quickSort(quickArr, 0, quickArr.length - 1);

        System.out.println("\nQuick Sort:");
        System.out.println(Arrays.toString(quickArr));

        heapSort(heapArr);

        System.out.println("\nHeap Sort:");
        System.out.println(Arrays.toString(heapArr));
    }
}