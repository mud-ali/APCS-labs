package huff;

import java.util.PriorityQueue;

/**
 * The main assignment file, where the Huffman Encoding/Decoding algorithms are
 * implemented.
 * 
 * @author Brian Lavallee, updated by bryres on 4/20/2017.
 * @author Updated to fill out template by Mudasir Ali 4/18/2024
 */
public class HuffProcessor implements Processor {

    @Override
    public void compress(BitInputStream in, BitOutputStream out) {
        // Count the frequency of each character (0-255) from the file.
        int[] counts = readForCounts(in);

        // For debugging purposes, print the count array.
        printCounts(counts);

        // Create the trie from the frequency table
        HuffNode root = makeTreeFromCounts(counts);

        // For debugging purposes, print the encoded tree to System.out
        printEncodingTree(root, 1);

        // Traverse the tree to determine the compressed encoding for each character.
        String[] codings = makeCodingsFromTree(root);

        // For debugging purposes, print the encodings array.
        printCodingsArray(codings);

        // Traverse the tree to record the encodings in the output file.
        writeHeader(root, out);

        // Debugging output to System.out
        int headerBits = out.getBitsWritten();
        System.out.println("Header out.  Total bits: " + headerBits);

        // Go back to the beginning of the input file
        in.reset();

        // Write the body of the compressed file, followed by EOF.
        writeCompressedBits(in, codings, out);

        // Make sure all bits have been physically written to the file.
        out.flush();

        // Debugging output to System.out
        int bodyBits = out.getBitsWritten() - headerBits;
        System.out.println("Body out.  Total bits: " + bodyBits);

    }

    /**
     * Returns an array [0-255] that represents the frequency of each byte value in
     * the input file.
     */
    private int[] readForCounts(BitInputStream in) {
        int[] counts = new int[256];
        int byteIn = 0;
        while (byteIn != -1) {
            counts[byteIn]++;
            byteIn = in.readBits(8);
        }
        return counts;
    }

    /**
     * Debugging method.
     * Prints the counts in the array.
     */
    public void printCounts(int[] counts) {
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                System.out.printf("%d (%c): %d\n", i, i > 32 ? i : ' ', counts[i]);
            }
        }
    }

    /**
     * Uses the array from readForCounts and returns a HuffNode that's
     * the root of the Huffman coding tree. Use a priority queue (PriorityQueue) to
     * create
     * the tree, as we did in class. Be sure to create a count of one for
     * PSEUDO_EOF and include it in the priority queue (and tree) as well.
     */
    private HuffNode makeTreeFromCounts(int[] array) {
        PriorityQueue<HuffNode> pq = new PriorityQueue<>();
        for (int i=0;i<array.length;i++) {
            HuffNode node = new HuffNode(i, array[i]);
            pq.add(node);
        }
        pq.add(new HuffNode(PSEUDO_EOF, 1));
        return pq.peek();
    }

    /**
     * Debugging method.
     * This is very similar to the prior lab. I am providing the implementation so
     * that our trees
     * have a consistent appearance.
     */
    private void printEncodingTree(HuffNode n, int indent) {
        if (n.isLeaf()) {
            System.out.printf("%" + indent + "s", "");
            String val = n.value() == PSEUDO_EOF ? "EOF" : "" + (char) n.value();
            System.out.println("\'" + val + "\'");
        } else {
            System.out.printf("%" + indent + "sL\n", "");
            printEncodingTree(n.left(), indent + 2);

            System.out.printf("%" + indent + "sR\n", "");
            printEncodingTree(n.right(), indent + 2);
        }
    }

    /**
     * Returns an array [0-256] -- note extra space for the EOF character
     * which has a value of 256. Each position in the array includes a String
     * of 0's and 1's indicating the compressed encoding for the byte. In
     * order to determine these encodings, a preorder traversal of the tree is
     * used. Each left traversal adds a "0" to the encoding. Each right
     * traversal adds a "1" to the encoding. When a leaf is reached, the
     * current encoding is added to the array.
     */

    private String[] makeCodingsFromTree(HuffNode root) {
        // TODO: Step 3! You will need to create a helper recursive method
        String[] encodings = new String[257];
        makeCodingsFromTree(root, encodings, "");
        return encodings;
    }
    private void makeCodingsFromTree(HuffNode root, String[] encodings, String path) {
        if (root == null) {
			return;
		}
        encodings[i] = path;
        makeCodingsFromTree(root.left(), encodings, path + "0");
        makeCodingsFromTree(root.right(), encodings, path + "1");
    }

    /**
     * Debugging method.
     * Prints the codings of the characters in the array [0-256].
     */
    public void printCodingsArray(String[] codings) {
        for (int i = 0; i < 256; i++) {
            if (codings[i] != null) {
                System.out.printf("%d (%c): %s\n", i, i > 32 ? i : ' ', codings[i]);
            }
        }
        System.out.printf("256 (EOF): %s\n", codings[PSEUDO_EOF]);

    }

    /**
     * Writes the bits for the header to the output stream.
     * Traverse the tree using preorder traversal.
     * For each internal node, write "0" to the output and traverse left and right.
     * For each leaf, write "1" to the output followed by the 9 bit representation
     * of
     * the value stored in the leaf.
     */
    private void writeHeader(HuffNode n, BitOutputStream out) {
        // TODO: Step 4
        System.out.println("hi");
    }

    /**
     * Write the body of the compressed file. Read through the input stream 8 bits
     * at time. Look up the encoding for that byte from "codings". Write that
     * encoding to the output stream using the following, which interprets the
     * String
     * as a binary number to convert it to an int and then writes the correct number
     * of bits to represent that number to output:
     * int code = Integer.parseInt(strCode, 2);
     * out.writeBits(strCode.length(), code);
     * After reading through all of "in", write PSEUDO_EOF as a final encoded
     * character to the file.
     */
    private void writeCompressedBits(BitInputStream in, String[] codings, BitOutputStream out) {
        // TODO: Step 5
    }

    @Override
    public void decompress(BitInputStream in, BitOutputStream out) {
        // Reads the encoding of the trie from the beginning of in.
        HuffNode root = readHeader(in);

        // Debugging method -- print the tree.
        printEncodingTree(root, 1);

        // Read the body of the compressed file.
        readCompressedBits(root, in, out);
        out.flush();
    }

    /**
     * Reads the header of the compressed file and returns encoding trie.
     * This method uses a recursion. The algorithm is:
     * Read one bit from the input.
     * If the bit is a 0, recurse left and right, creating two new HuffNodes. Then,
     * return a new HuffNode with the left and right nodes as the new node's
     * children.
     * If the bit is a 1, read 9 bits from the input and create a HuffNode using
     * these bits.
     * The weight of the HuffNodes does not matter during decompression and can be
     * set to -1.
     */
    HuffNode readHeader(BitInputStream in) {
        // TODO: Step 6
        return new HuffNode(-1, -1);
    }

    /**
     * Reads the body of the compressed file. Start at the root of the tree.
     * Read 1 bit of input.
     * If the bit is a 0, traverse left. If 1, traverse right.
     * If we are now pointing to a leaf, write the value of the leaf to output. Go
     * back to the top of the tree and traverse again.
     * (If the value of the leaf is PSEUDO_EOF, exit the function.)
     */
    private void readCompressedBits(HuffNode root, BitInputStream in, BitOutputStream out) {
        // TODO: Step 7
    }

}
