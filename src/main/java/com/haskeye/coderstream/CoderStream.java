package com.haskeye.coderstream;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.math.BigInteger;

/**
 * This program xor codes or decodes file and returns coded or decoded one.
 * Command "-c" initiates coding "-d" decoding respectively.
 * Program reads byte array and applies xor operation to every byte according to the key.
 */
public class CoderStream {

    @Option(name = "-c", forbids = "-d", usage = "code")
    private String key;

    @Option(name = "-d", forbids = "-c", usage = "decode")
    private String keyNew;

    @Option(name = "-o", usage = "output")
    private String output;

    @Argument
    private String input;

    private void doMain(String[] args) throws CmdLineException{
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.out.println("meh");
        }
        if (key != null) FileWorker.writeBytes(output, code(FileWorker.readFileToByteArray(input), key));
        else FileWorker.writeBytes(output, code(FileWorker.readFileToByteArray(input), keyNew));
    }


    public static void main(String[] args) throws CmdLineException{
        CoderStream instance = new CoderStream();
        instance.doMain(args);
    }

    public static byte[] code(byte[] bArray, String key) {
        //New byte array for output
        byte[] text = new byte[bArray.length];

        //Counter to iterate through the bytes of key
        int i = 0;

        BigInteger keyBig = new BigInteger(key, 16);
        byte[] keyBytes = keyBig.toByteArray();
        int keyLength = keyBytes.length;

        for (int j = 0; j < bArray.length; j++) {
            text[j] = (byte) (((byte) 0xff) & (((int) bArray[j]) ^ ((int) keyBytes[i])));
            i = (i + 1) % keyLength;
        }
        return text;
    }
}
