package com.streamsFilesAndDirectories.lab;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    public static void main(String[] args) {
//        readFile();
//        writeToFile();
//        copyBytes();
//        extractIntegers();
//        writeEveryThirdLine();
//        sortLines();
//        listFiles();
//        nestedFolders();
//        nestedFolders2Point0();
//        serializeCustomObject();

    }

    private static class Cube implements Serializable {
        private String color;
        private double width;
        private double height;
        private double depth;

        public Cube(String color, double width, double height, double depth) {
            this.color = color;
            this.width = width;
            this.height = height;
            this.depth = depth;
        }
    }
    private static void serializeCustomObject() {
        Cube cube = new Cube("green", 15.3, 12.4, 3.0);

        String path = "src/com/streamsFilesAndDirectories/resources/Files-and-Streams/save.txt";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream((path)))) {
            oos.writeObject(cube);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void nestedFolders2Point0() {
        String path = "src/com/streamsFilesAndDirectories/resources/Files-and-Streams/";
        File root = new File(path);
        List<String> folderNames = new ArrayList<>();

        AddAllFoldersNames(root, folderNames);

        folderNames.forEach(System.out::println);
        System.out.println(folderNames.size() + " folders");

    }

    private static void AddAllFoldersNames(File file, List<String> names) {
        if (!file.isDirectory()) {
            return;
        } else {
            names.add(file.getName());
        }

        for (File f : file.listFiles()) {
            AddAllFoldersNames(f, names);
        }
    }


    private static void nestedFolders() {
        String path = "src/com/streamsFilesAndDirectories/resources/Files-and-Streams/";
        File root = new File(path);
        Deque<File> dirs = new ArrayDeque<>();
        dirs.offer(root);

        int directoriesCount = 0;
        while (!dirs.isEmpty()) {
            File current = dirs.poll();
            if (current.isDirectory()) {
                System.out.println(current.getName());
                directoriesCount++;
                for (File file : current.listFiles()) {
                    if (file.isDirectory()) {
                        dirs.offer(file);
                    }
                }
            }
        }

        System.out.println(directoriesCount + " folders");

    }

    private static void listFiles() {
        String path = "src/com/streamsFilesAndDirectories/resources/Files-and-Streams/Files-and-Streams";
        File file = new File(path);
        Arrays.stream(file.listFiles()).filter(File::isFile).forEach(f -> {
            System.out.printf("%s: [%d]%n", f.getName(), f.length());
        });
    }

    private static void sortLines() {
        String inputPath = "src/com/streamsFilesAndDirectories/resources/Files-and-Streams/input.txt";
        String outputPath = "src/com/streamsFilesAndDirectories/resources/Files-and-Streams/output.txt";
        Path path = Path.of(inputPath);
        Path output = Path.of(outputPath);
        try {
            List<String> lines = Files.readAllLines(path);
            Collections.sort(lines);
            Files.write(output, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeEveryThirdLine() {
        String inputPath = "src/com/streamsFilesAndDirectories/resources/Files-and-Streams/input.txt";
        String outputPath = "src/com/streamsFilesAndDirectories/resources/Files-and-Streams/output.txt";

        String[] lines = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputPath));
            lines = reader.lines().toArray(String[]::new);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            PrintWriter out = new PrintWriter(new FileWriter(outputPath));
            for (int i = 2; i < lines.length; i += 3) {
                out.println(lines[i]);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extractIntegers() {
        String inputPath = "src/com/streamsFilesAndDirectories/resources/Files-and-Streams/input.txt";
        String outputPath = "src/com/streamsFilesAndDirectories/resources/Files-and-Streams/output.txt";

        try {
            Scanner scanner = new Scanner(new FileInputStream(inputPath));
            PrintWriter out = new PrintWriter(new FileWriter(outputPath));
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    out.println(scanner.nextInt());
                }
                scanner.next();
            }

            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void copyBytes() {
        String inputPath = "src/com/streamsFilesAndDirectories/resources/Files-and-Streams/input.txt";
        String outputPath = "src/com/streamsFilesAndDirectories/resources/Files-and-Streams/output.txt";

        byte[] fileText = null;

        try (FileInputStream inputStream = new FileInputStream(inputPath);) {
            fileText = inputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
            for (byte b : fileText) {
                if (b >= 0 && b != 32 && b != 10) {
                    String digits = String.valueOf(b);
                    for (char c : digits.toCharArray()) {
                        outputStream.write(c);
                    }

                } else {
                    outputStream.write(b);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void writeToFile() {
        String inputPath = "src/com/streamsFilesAndDirectories/resources/Files-and-Streams/input.txt";
        String outputPath = "src/com/streamsFilesAndDirectories/resources/Files-and-Streams/output.txt";

        byte[] fileText = null;

        try (FileInputStream inputStream = new FileInputStream(inputPath);) {
            fileText = inputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
            for (byte b : fileText) {
                if (b == ',' || b == '.' || b == '!' || b == '?') {
                    continue;
                }
                outputStream.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void readFile() {
        String path = "src/com/streamsFilesAndDirectories/resources/input.txt";

        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            int oneByte = fileInputStream.read();
            while (oneByte >= 0) {
                System.out.print(Integer.toBinaryString(oneByte) + " ");
                oneByte = fileInputStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
