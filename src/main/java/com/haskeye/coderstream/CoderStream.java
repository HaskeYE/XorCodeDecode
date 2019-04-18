package com.haskeye.coderstream;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class CoderStream {
    private void code(File file, String key) throws Exception {
        File newFile = new File("C:/Users/user/IdeaProjects/XorCodeDecode");

        FileReader fr = new FileReader(file);
        Scanner scan = new Scanner(fr);
        StringBuilder sbKey = new StringBuilder();
        sbKey.append(key);

        FileWriter fw = new FileWriter(newFile);
        StringBuilder text = new StringBuilder();

        while (scan.hasNextLine()) {
            String str = scan.nextLine();
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
                    fw.write(" ");

                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < piece.length(); i++) {
                        int c = (int) piece.charAt(i);
                        int k = (int) sbKey.charAt(j);
                        c = c ^ k;
                        fw.write((char) c);
                    }
                }
                fw.write(" ");

            }
            fw.write("\n");
        }

        fw.close();
        fr.close();
    }
}
