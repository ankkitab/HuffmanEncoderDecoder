/**
 * Created by ankkitabose on 3/25/17.
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class decoder {

    //here
    public  class Node {
        int value = Integer.MIN_VALUE;
        Node left,right;


        public Node() {}

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public decoder.Node getLeft() {
            return left;
        }

        public void setLeft(decoder.Node left) {
            this.left = left;
        }

        public decoder.Node getRight() {
            return right;
        }

        public void setRight(decoder.Node right) {
            this.right = right;
        }
    }

    public Node createHuffmanTree(String path) throws IOException {
       // decoder d=new decoder();


        BufferedReader br = new BufferedReader(new FileReader(new File(path)));

        Node root = new Node();
        String l1;
        while ((l1=br.readLine()) != null) {
            String[] divide = l1.split(" ");
            String data = divide[0];
            String code = divide[1];
            Node curr = root;

            for (int i = 0; i < code.length(); i++) {
                char ch = code.charAt(i);
                if (ch == '0') {
                    if (curr.left == null) {
                        curr.left = new Node();
                    }
                    curr = curr.left;
                }
                else if (ch == '1') {
                    if (curr.right == null) {
                        curr.right = new Node();
                    }
                    curr = curr.right;
                }
            }
            curr.value = Integer.parseInt(data);
        }
        br.close();

        return root;
    }

    private String readCodeFile(String path) throws IOException {
        BufferedInputStream bs = new BufferedInputStream(new FileInputStream(path));
        int line;
        StringBuffer stbuffer = new StringBuffer("");
        while ((line = bs.read()) != -1) {
            int x = 1;
            while (x <= 8) {
                stbuffer.append((line >> (8 - x)) & 0x1);
                x++;
            }
        }
        bs.close();
        return stbuffer.toString();
    }

    private void writeFile(Node root, String code) {

        ArrayList<Integer> result = new ArrayList<Integer>();


        Node curr = root;
        for(int i=0; i<code.length(); i++){
            char ch = code.charAt(i);

            if(ch == '1'){
                curr = curr.right;
            }
            else{
                curr = curr.left;
            }
            if(curr.value != Integer.MIN_VALUE){
                result.add(curr.value);
                curr = root;
            }
        }

        // Write in file decoded.txt
        try{
            PrintWriter pw = new PrintWriter("decoded.txt", "UTF-8");
            for(int i=0; i<result.size(); i++){
                pw.println(result.get(i));
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Unable to write in decoded.txt");
        }
    }



    public static void main(String[] args) throws IOException {

        decoder dec=new decoder();
        Node root = dec.createHuffmanTree(args[1]);
        String code = dec.readCodeFile(args[0]);



        dec.writeFile(root, code);


    }
}
