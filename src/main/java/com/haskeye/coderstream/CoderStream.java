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
                sbKey.delete(0, sbKey.length());
                if (piece.length() > key.length()) {

                } else {
                    sbKey.delete(piece.length()-1, key.length());

                }
            }
        }
    }
}
