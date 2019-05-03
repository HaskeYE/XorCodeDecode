package com.haskeye.coderstream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.haskeye.coderstream.CoderStream.code;

class CoderStreamTest {
    @Test
    public void writeTest() {
        byte[] input = FileWorker.readFileToByteArray("first.txt");
        byte[] coded = code(input, "F342A4824C");
        FileWorker.writeBytes("Third.txt", code(coded, "F342A4824C"));
        File fileFirst = new File("First.txt");
        File fileNew = new File("Third.txt");
        Assertions.assertEquals(fileFirst, fileNew);
    }

    @Test
    public void readTest() {
        byte[] input = FileWorker.readFileToByteArray("first.txt");
        byte[] real = "Каламбур JGJGJNGJN jkenhekjnge ehe".getBytes();
        Assertions.assertArrayEquals(input, real);
    }

    @Test
    public void codeTest() {
        byte[] input = FileWorker.readFileToByteArray("first.txt");
        FileWorker.writeBytes("Second.txt", code(input, "F342A4824C"));
        byte[] coded = code(input, "F342A4824C");
        byte[] codedBack = code(coded, "F342A4824C");
        Assertions.assertArrayEquals(input, codedBack);
    }
}