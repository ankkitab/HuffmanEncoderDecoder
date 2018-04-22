import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by ankkitabose on 3/24/17.
 */
public class BinaryConversion {

    private BufferedOutputStream bout;
    private int buffer;
    private int n;

    public BinaryConversion(String filename) {
        try {
            OutputStream os = new FileOutputStream(filename);
            bout = new BufferedOutputStream(os);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        flush();
        try {
            bout.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flush() {
        clearBuffer();
        try {
            bout.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearBuffer() {
        if (n == 0) return;
        if (n > 0) buffer <<= (8 - n);
        try {
            bout.write(buffer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        n = 0;
        buffer = 0;
    }

    public void write(boolean x) {
        writeBit(x);
    }

    private void writeBit(boolean x) {
        buffer <<= 1;
        if (x) buffer |= 1;

        // if buffer is full (8 bits), write out as a single byte
        n++;
        if (n == 8) clearBuffer();
    }
}
