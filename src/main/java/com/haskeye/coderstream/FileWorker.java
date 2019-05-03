package com.haskeye.coderstream;

import com.sun.istack.internal.NotNull;

import java.io.*;

/**
 * This class helps to read bytes from the file and write bytes back to file
 */
public class FileWorker {
    public static byte[] readFileToByteArray(String fileName) {
        //Define file
        File file = new File(fileName);

        FileInputStream fis;
        // Creating a byte array using the length of the file
        // file.length returns long which is cast to int
        byte[] bArray = new byte[(int) file.length()];
        try {
            fis = new FileInputStream(file);
            fis.read(bArray);
            fis.close();

        } catch (IOException ioExp) {
            ioExp.printStackTrace();
        }
        return bArray;
    }

    public static void writeBytes(String fileName, byte[] bytes) {
        //Define file
        File file = new File(fileName);

        try {
            //Creating new file
            if (!file.exists()) {
                file.createNewFile();
            }

            // Initialize a pointer
            // in file using OutputStream
            OutputStream
                    os
                    = new FileOutputStream(file);

            // Starts writing the bytes in it
            os.write(bytes);

            // Close the file
            os.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
