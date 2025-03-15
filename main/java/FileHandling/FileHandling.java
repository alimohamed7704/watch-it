package main.java.FileHandling;

import java.io.*;

public class FileHandling {
    public static BufferedReader reader;
    public static BufferedWriter writer;
    public static Boolean isWriterClosed = false;
    public static Boolean isReaderClosed = false;
    static {
        try {
            reader = new BufferedReader(new FileReader("src/main/resources/FileHandling/usersData.txt"));
            isReaderClosed = false;
            writer = new BufferedWriter(new FileWriter("src/main/resources/FileHandling/usersData.txt",true));
            isWriterClosed = false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeReader() {
        try {
            if (reader != null) {
                reader.close();
                isReaderClosed = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeWriter() {
        try {
            if (writer != null) {
                writer.close();
                isWriterClosed = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
