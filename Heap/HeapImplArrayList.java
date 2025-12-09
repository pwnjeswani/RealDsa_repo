import java.util.ArrayList;

public class HeapImplArrayList {
    // We use ArrayList to represent the "Array" because it handles resizing for us.
    private ArrayList<Integer> heap;

    public HeapImplArrayList() {
        this.heap = new ArrayList<>();
    }

    // Parent Index: (i - 1) / 2
    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    // Left Child Index: 2*i + 1
    private int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }

    // Right Child Index: 2*i + 2
    private int getRightChildIndex(int i) {
        return 2 * i + 2;
    }

    // Swap two elements in the array
    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    public void insert(int value) {
        // Step 1: Add the new element to the END of the array
        heap.add(value);

        // Step 2: "Bubble Up" to fix the order
        // Start at the last index and move up
        int currentIndex = heap.size() - 1;
        heapifyUp(currentIndex);
    }

    private void heapifyUp(int index) {
        // While we are not at the Root...
        while (index > 0) {
            int parentIndex = getParentIndex(index);

            // If I am SMALLER than my Parent (Min-Heap violation), swap!
            if (heap.get(index) < heap.get(parentIndex)) {
                swap(index, parentIndex);

                // Move my pointer up to the parent's spot
                index = parentIndex;
            } else {
                // If I am larger/equal, the order is correct. Stop.
                break;
            }
        }
    }

    public int extractMin() {
        if (heap.isEmpty()) {
            throw new RuntimeException("Heap is empty!");
        }

        // The Minimum is always at the Root (Index 0)
        int min = heap.get(0);

        // Step 1: Remove the LAST element from the array
        int lastElement = heap.remove(heap.size() - 1);

        // If the heap is not empty, move that last element to the Root
        if (!heap.isEmpty()) {
            heap.set(0, lastElement);

            // Step 2: "Heapify Down" (Sink) to fix the order
            heapifyDown(0);
        }

        return min;
    }

    private void heapifyDown(int index) {
        int size = heap.size();
        int smallest = index; // Assume parent is smallest initially

        int leftChild = getLeftChildIndex(index);
        int rightChild = getRightChildIndex(index);

        // Compare with Left Child
        if (leftChild < size && heap.get(leftChild) < heap.get(smallest)) {
            smallest = leftChild;
        }

        // Compare with Right Child
        if (rightChild < size && heap.get(rightChild) < heap.get(smallest)) {
            smallest = rightChild;
        }

        // If the smallest is NOT the parent (meaning one of the children is smaller)
        if (smallest != index) {
            // Swap Parent with the Smallest Child
            swap(index, smallest);

            // Recursively fix the subtree where we moved the parent
            heapifyDown(smallest);
        }
    }

    public void printHeap() {
        System.out.println(heap);
    }

    public static void main(String[] args) {
        HeapImplArrayList minHeap = new HeapImplArrayList();

        System.out.println("--- Inserting 10, 5, 20, 2 ---");
        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(20);
        minHeap.insert(2);

        // Visual check: 2 should be at index 0
        minHeap.printHeap();
        // Expected: [2, 5, 20, 10]

        System.out.println("\n--- Extracting Min ---");
        System.out.println("Removed: " + minHeap.extractMin()); // Should be 2

        minHeap.printHeap();
        // Expected: [5, 10, 20] (10 moved up and swapped with 5)

        System.out.println("Removed: " + minHeap.extractMin()); // Should be 5
        minHeap.printHeap();
    }
}