/**
 * Created by ankkitabose on 3/24/17.
 */

public class Huffman {


    public Huffman() {

    }

    public Node createHuffmanTree(int[] frequency) {

        BinaryHeap<Node> queue = new BinaryHeap<>();
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] > 0) {
                //creating tree
                queue.insert(new Node(i, frequency[i], null, null));
            }
        }

        //do it for when queue size equals 1

        if (queue.getSize() == 1) {
            if (frequency['\0'] == 0){
                queue.insert(new Node('\0', 0, null, null));
            }
            else{
                queue.insert(new Node('\1', 0, null, null));
            }
        }

        // Merging the min ones to create a new node
        while (queue.getSize() > 1) {
            Node left  = queue.delMin();
            Node right = queue.delMin();
            Node parent = new Node('\0', left.getFrequency() + right.getFrequency(), left, right);
            queue.insert(parent);
        }
        return queue.delMin();

    }

    public void generateCode(String[] codeStr, Node var, String str) {
        if (!var.isLeaf()) {
            //System.out.println(var.getFrequency());
            generateCode(codeStr, var.getLeft(),  str + '0');
            //System.out.println("j "+j++);
            generateCode(codeStr, var.getRight(),  str + '1');
        }
        else {
            codeStr[var.getValue()]=str;
        }

    }


}
