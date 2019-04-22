package com.haskeye.coderstream;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.Console;
import java.io.IOException;

public class CoderStream {

    @Option(name = "-c", forbids = "-d", usage = "code")
    private boolean coding;
    private String key;

    @Option(name = "-d", forbids = "-c", usage = "decode")
    private boolean uncoding;
    private String keyNew;

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
            if (coding) FileWorker.write(output, code(FileWorker.read(input), key));
            else FileWorker.write(output, code(FileWorker.read(input), keyNew));
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }


    public static void main(String[] args) {
        CoderStream instance = new CoderStream();

        Console console = System.console();
        String[] str = console.readLine().split(" ");

        try {
            instance.doMain(str);
        } catch (IOException e) {
            System.out.println("brah");
        }
    }

    private String code(String file, String key) throws Exception {

        StringBuilder sbKey = new StringBuilder();
        sbKey.append(key);

        StringBuilder text = new StringBuilder();

        String[] strs = file.split("\n");

        for (String str : strs) {
            String[] pieces = str.split("\\s+");
            for (String piece : pieces) {
                int j = 0;
                sbKey.delete(0, sbKey.length());
                if (piece.length() > key.length()) {
                    for (int i = sbKey.length(); i < piece.length(); i++) {
                        if (i % key.length() != 0) {
                            sbKey.append(key.charAt(i % key.length()));
                        } else {
                            sbKey.append(key.charAt(key.length() - 1));
                        }
                    }
                    for (int i = 0; i < piece.length(); i++) {
                        int c = (int) piece.charAt(i);
                        int k = (int) sbKey.charAt(j);
                        j++;
                        c = c ^ k;
                        text.append((char) c);
                    }
                } else {
                    for (int i = 0; i < piece.length(); i++) {
                        int c = (int) piece.charAt(i);
                        int k = (int) sbKey.charAt(j);
                        j++;
                        c = c ^ k;
                        text.append((char) c);
                    }
                }
                text.append(" ");

            }
            text.append("\n");
        }
        return text.toString();
    }
}
