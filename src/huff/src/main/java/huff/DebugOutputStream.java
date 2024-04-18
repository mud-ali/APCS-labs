package huff;

import java.io.*;

/**
 * Created by bryres on 4/26/2017.
 */
public class DebugOutputStream extends PrintStream {
    private static final int bmask[] = { 0x80,0x40,0x20,0x10,
            0x08,0x04,0x02,0x01};

    public DebugOutputStream(OutputStream out) {
        super(out);
    }

    public DebugOutputStream(OutputStream out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public DebugOutputStream(OutputStream out, boolean autoFlush, String encoding) throws UnsupportedEncodingException {
        super(out, autoFlush, encoding);
    }

    public DebugOutputStream(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public DebugOutputStream(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(fileName, csn);
    }

    public DebugOutputStream(File file) throws FileNotFoundException {
        super(file);
    }

    public DebugOutputStream(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(file, csn);
    }

    public void write(int b) {
        for (int n : bmask) {
            if ((n & b) > 0)
                super.append('1');
            else
                super.append('0');
        }
    }
}
