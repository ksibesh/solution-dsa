package com.solution.dsa.heap;

public class MinHeap {
    private int[] heapArr;
    private int capacity;
    private int currHeapSize;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.heapArr = new int[capacity];
        this.currHeapSize = 0;
    }

    private void swap(int source, int destination) {
        int temp = heapArr[source];
        heapArr[source] = heapArr[destination];
        heapArr[destination] = temp;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int left(int index) {
        return (index * 2) + 1;
    }

    private int right(int index) {
        return (index * 2) + 2;
    }

    public boolean add(int data) {
        if (currHeapSize == capacity) return false;

        int cur = currHeapSize;
        heapArr[currHeapSize++] = data;

        if (cur == 0) return true;

        while (parent(cur) >= 0 && heapArr[cur] < heapArr[parent(cur)]) {
            swap(cur, parent(cur));
            cur = parent(cur);
        }
        return true;
    }

    public int pop() {
        if (currHeapSize == 0) throw new IllegalStateException("Heap is empty!");

        int poppedElement = heapArr[0];
        heapArr[0] = heapArr[currHeapSize - 1];
        heapArr[currHeapSize - 1] = 0;
        currHeapSize--;

        heapify(0);
        return poppedElement;
    }

    private void heapify(int index) {
        int left = left(index);
        int right = right(index);

        int smallestElementIndex = index;
        if (left < currHeapSize && heapArr[left] < heapArr[smallestElementIndex]) smallestElementIndex = left;
        if (right < currHeapSize && heapArr[right] < heapArr[smallestElementIndex]) smallestElementIndex = right;

        if (smallestElementIndex != index) {
            swap(index, smallestElementIndex);
            heapify(smallestElementIndex);
        }
    }

    public static void main(String[] args) {
        MinHeap heap = new MinHeap(10);
        heap.add(4);
        heap.add(6);
        heap.add(3);
        heap.add(5);

        System.out.println("Popped element - " + heap.pop());
        System.out.println("Popped element - " + heap.pop());
        System.out.println("Popped element - " + heap.pop());
    }
}
