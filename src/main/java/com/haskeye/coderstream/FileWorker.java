package com.haskeye.coderstream;

import java.io.*;

public class FileWorker {
    public static String read(String fileName) throws FileNotFoundException {
        //Define file
        File file = new File(fileName);

        //Init StringBuilder
        StringBuilder sb = new StringBuilder();

        exists(fileName);

        try {
            //Use BufferedReader
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                //Closing file
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

    public static void write(String fileName, String text) {
        //Define file
        File file = new File(fileName);

        try {
            //Creating new file
            if(!file.exists()){
                file.createNewFile();
            }
            //New PrintWriter class
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                //Writing text into our file
                out.print(text);
            } finally {
                out.close();
            }
            //Exception
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
    }
    public static void delete(String nameFile) throws FileNotFoundException {
        exists(nameFile);
        new File(nameFile).delete();
    }
}
