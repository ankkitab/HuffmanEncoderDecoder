import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ankkitabose on 3/24/17.
 */

public class encoder {
    public int[] freqTable=new int[100000000];
    public Huffman huffman=new Huffman();

    public void createFreqTable(ArrayList<Integer> input_val) {
        //encoder obj=new encoder();
        for (int i=0;i<input_val.size();i++) {
            freqTable[input_val.get(i)]=freqTable[input_val.get(i)]+1;
        }
    }

    public void createCodeTable(String[] codeStr) throws IOException{
        PrintWriter pw = new PrintWriter("code_table.txt", "UTF-8");
        for(int i=0; i<codeStr.length; i++){
            if(codeStr[i] != null){
                pw.print(i);
                pw.print(" ");
                pw.println(codeStr[i]);
            }
        }
        pw.close();
    }

    public void encoding(String[] str, ArrayList<Integer> inputValues) throws FileNotFoundException, UnsupportedEncodingException {
        BinaryConversion binaryOut = new BinaryConversion("encoded.bin");
        for(Integer k : inputValues){
            String s = str[k];
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == '0') {
                    binaryOut.write(false);
                }
                else {
                    binaryOut.write(true);
                }
            }
        }
        binaryOut.close();
    }


    public static void main(String args[]) throws IOException, NumberFormatException{
      //  long time1= System.currentTimeMillis();
        encoder en=new encoder();
        ArrayList<Integer> inputValues=new ArrayList<>();
        // file read
        String path=args[0];
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String l1=br.readLine();
            while (l1 != null) {

                l1 = l1.trim();
                if (!l1.equals("")) {
                    Integer i = Integer.valueOf(l1);
                    inputValues.add(i);
                }
                l1 = br.readLine();
            }
            br.close();
        }
        catch (NumberFormatException n) {
            System.out.println("The file contains non-numbers!!! ");
        }
        catch (IOException e) {
            System.out.println("File not present!!");
        }

        //Frequency table creation
        en.createFreqTable(inputValues);

        Node root = en.huffman.createHuffmanTree(en.freqTable);

        // Make Code table
        String[] codeStr = new String[100000000];

        en.huffman.generateCode(codeStr,root,"");

        try {
            en.createCodeTable(codeStr);
        }
        catch (IOException e) {
            System.out.println("Cannot write in the codeTable file!!");
        }

        en.encoding(codeStr,inputValues);

     //   long time=System.currentTimeMillis()-time1;

//        System.out.println("Time :"+ time);

    }
}

