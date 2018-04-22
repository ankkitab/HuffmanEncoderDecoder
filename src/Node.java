 /**
 * Created by ankkitabose on 3/23/17.
 */
public class Node implements Comparable<Node>{

    final public int value;
    final public int frequency;
    final public Node left, right;

    public Node(int value, int frequency, Node left, Node right) {
        this.value = value;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public int getFrequency() {
        return frequency;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }


    public int compareTo(Node ptr) {
        return (this.frequency-ptr.frequency);
    }

    public boolean isLeaf() {
        return (this.left == null) && (this.right == null);
    }


}
