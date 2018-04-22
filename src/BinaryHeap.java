import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Created by ankkitabose on 4/5/17.
 */
public class BinaryHeap<T>  {

    private T[] key;                    //key of the heap
    private int size;                   //total number of elements in the PQ
    public Comparator<T> compareObj;    //to compare objects

    @SuppressWarnings("unchecked")
    //constructor
    public BinaryHeap (int total) {
        key = (T[]) new Object[total + 1];
        size = 0;
    }

    public BinaryHeap() {
        this(1);

    }

    public int getSize() {
        return size;
    }


    //***INSERT
    // /insert a new node in the heap
    public void insert(T node) {

        if (key.length - 1 == size) {
            increaseSize(2 * key.length);
        }

        key[++size] = node;
        upTraversal(size);
        assert minHeapCheck();
    }

    //Go up the PQ
    private void upTraversal (int i) {
        while (i > 1 && isBigger(i/2, i)) {
            swap(i, i/2);
            i = i/2;
        }
    }

    //Swap elements
    private void swap(int x, int y) {
        T temp = key[x];
        key[x] = key[y];
        key[y] = temp;
    }

    //increase the array size when needed
    @SuppressWarnings("unchecked")
    private void increaseSize(int n) {
        assert n > size;
        T[] temp = (T[]) new Object[n];
        //System.out.println(n);
        for (int i = 1; i <= size; i++) {

            temp[i] = key[i];
        }

        key = temp;
    }

    private boolean minHeapCheck() {
        return (checkMinHeap(1));
    }

    private boolean checkMinHeap(int n) {
        if (n > size)
            return true;
        int lptr = 2*n;
        int rptr = 2*n + 1;
        if (lptr  <= size && isBigger(n, lptr)){
            return false;
        }
        if (rptr <= size && isBigger(n, rptr)){
            return false;
        }
        return checkMinHeap(lptr) && checkMinHeap(rptr);
    }

    //****DELETE MIN
    //delete the min node
    public T delMin() {
        if (isEmpty()){
            throw new NoSuchElementException("Priority queue underflow");
        }
        swap(1, size);
        T min = key[size--];
        downTraversal(1);
        key[size+1] = null;
        if ((size > 0) && (size == (key.length - 1) / 4)){
            increaseSize(key.length  / 2);
        }
        assert minHeapCheck();
        return min;
    }

    private void downTraversal (int i) {
        while (2*i <= size) {
            int j = 2*i;
            if (j < size && isBigger(j, j+1)){
                j++;
            }
            if (!isBigger(i, j)){
                break;
            }
            swap(i, j);
            i = j;
        }
    }

    @SuppressWarnings("unchecked")
    private boolean isBigger(int i, int j) {
        if (compareObj == null) {
            return ((Comparable<T>) key[i]).compareTo(key[j]) > 0;
        }
        else {
            return compareObj.compare(key[i], key[j]) > 0;
        }
    }

    private boolean isEmpty() {
        if (size==0)
            return true;
        else
            return false;
    }
}
