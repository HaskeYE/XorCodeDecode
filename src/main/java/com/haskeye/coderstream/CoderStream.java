package com.haskeye.coderstream;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.math.BigInteger;

/**
 * This program xor codes or decodes file and returns coded or decoded one.
 * Command "-c" initiates coding "-d" decoding respectively.
 * Program reads byte array and applies xor operation to every byte according to the key.
 */
public class CoderStream {

    @Option(name = "-c", forbids = "-d", usage = "code")
    private BigInteger key;

    @Option(name = "-d", forbids = "-c", usage = "decode")
    private BigInteger keyNew;

    @Option(name = "-o", usage = "output")
    private String output;

    @Argument
    private String input;

    private void doMain(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.out.println("meh");
        }

        try {
            if (key != null) FileWorker.writeBytes(output, code(FileWorker.readFileToByteArray(input), key));
            else FileWorker.writeBytes(output, code(FileWorker.readFileToByteArray(input), keyNew));
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }


    public static void main(String[] args) {
        CoderStream instance = new CoderStream();

        try {
            instance.doMain(args);
        } catch (IOException e) {
            System.out.println("brah");
        }
    }

    private byte[] code(byte[] bArray, BigInteger key) throws Exception {
        //New byte array for output
        byte[] text = new byte[bArray.length];

        //Counter to iterate through the bytes of key
        int i = 0;

        byte[] keyBytes = key.toByteArray();
        int keyLength = keyBytes.length;

        for (int j = 0; j < bArray.length; j++) {
        text[j] = (byte) (0xff & ( ((int) bArray[j]) ^ ((int) keyBytes[i])));
        i = (i + 1) % keyLength;
        }
        return text;
    }
}
