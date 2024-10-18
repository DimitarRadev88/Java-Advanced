package com.streamsFilesAndDirectories.exercise;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    private static final String RESOURCES_FOLDER_PATH = "src/com/streamsFilesAndDirectories/exercise/resources/";

    public static void main(String[] args) {
//        sumLines();
//        sumBytes();
//        allCapitals();
//        countCharacterTypes();
//        lineNumbers();
//        wordCount();
//        mergeTwoFiles();
//        getFolderSize();
//        copyAPicture();
//        serializeArrayList();
//        serializeCustomObject();
        createZipArchive();
    }

    private static void createZipArchive() {

        List<File> textFiles = new ArrayList<>();
        textFiles.add(new File(RESOURCES_FOLDER_PATH + "input.txt"));
        textFiles.add(new File(RESOURCES_FOLDER_PATH + "inputOne.txt"));
        textFiles.add(new File(RESOURCES_FOLDER_PATH + "inputTwo.txt"));

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(RESOURCES_FOLDER_PATH + "/files.zip"))) {
            for (File f : textFiles) {
                zipOutputStream.putNextEntry(new ZipEntry(f.getName()));
                FileInputStream inputStream = new FileInputStream(f);
                zipOutputStream.write(inputStream.readAllBytes());
                zipOutputStream.closeEntry();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Course implements Serializable {
        private String name;
        private int numberOfStudents;

        public Course(String name, int numberOfStudents) {
            this.name = name;
            this.numberOfStudents = numberOfStudents;
        }
    }

    private static void serializeCustomObject() {
        Course course = new Course("Java-Advanced", 100);
        String filePath = RESOURCES_FOLDER_PATH + "course.ser";

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(course);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            Course deserializedCourse = (Course) inputStream.readObject();
            System.out.println(deserializedCourse.name);
            System.out.println(deserializedCourse.numberOfStudents);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void serializeArrayList() {
        List<Double> doubleList = new ArrayList<>(List.of(1.0, 2.0));

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(RESOURCES_FOLDER_PATH + "list.ser"))) {
            outputStream.writeObject(doubleList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(RESOURCES_FOLDER_PATH + "list.ser"))) {
            List<Double> doubles = (List<Double>) inputStream.readObject();
            doubles.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private static void copyAPicture() {
        byte[] picture = null;
        String pictureFilePath = "src/com/streamsFilesAndDirectories/exercise/resources/exercisesResources/picture/picture.jpg";
        try (FileInputStream inputStream = new FileInputStream(pictureFilePath)) {
            picture = inputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String pictureCopyFilePath = "src/com/streamsFilesAndDirectories/exercise/resources/exercisesResources/picture/picture-copy.jpg";
        try (FileOutputStream outputStream = new FileOutputStream(pictureCopyFilePath)) {
            outputStream.write(picture);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getFolderSize() {
        File file = new File(RESOURCES_FOLDER_PATH);
        File[] files = file.listFiles();
        Deque<File> queue = new ArrayDeque<>();

        for (File f : files) {
            queue.offer(f);
        }

        long size = 0;
        while (!queue.isEmpty()) {
            File current = queue.poll();
            if (current.isDirectory()) {
                for (File f : current.listFiles()) {
                    queue.offer(f);
                }
            } else {
                size += current.length();
            }
        }
        System.out.println("Folder size: " + size);

    }

    private static void mergeTwoFiles() {
        List<String> allLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RESOURCES_FOLDER_PATH + "inputOne.txt"))) {
            allLines.addAll(reader.lines().toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(RESOURCES_FOLDER_PATH + "inputTwo.txt"))) {
            allLines.addAll(reader.lines().toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(RESOURCES_FOLDER_PATH + "merged.txt"))) {
            allLines.forEach(writer::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class CaseInsensitiveComparator implements Comparator<String> {
        public static final CaseInsensitiveComparator INSTANCE = new CaseInsensitiveComparator();

        public int compare(String first, String second) {
            return first.compareToIgnoreCase(second);
        }
    }

    private static void wordCount() {
        Map<String, Integer> wordsCount = new TreeMap<>(CaseInsensitiveComparator.INSTANCE);

        try (BufferedReader reader = new BufferedReader(new FileReader(RESOURCES_FOLDER_PATH + "words.txt"))) {
            Arrays.stream(reader.readLine().split(" ")).forEach(word -> wordsCount.put(word, 0));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(RESOURCES_FOLDER_PATH + "text.txt"))) {
            Arrays.stream(reader.readLine()
                            .toLowerCase()
                            .replaceAll("[!,.?]", "").split(" "))
                    .filter(w -> wordsCount.containsKey(w))
                    .forEach(word -> wordsCount.put(word, wordsCount.get(word) + 1));
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (PrintWriter writer = new PrintWriter(new FileWriter(RESOURCES_FOLDER_PATH + "results.txt"))) {
            wordsCount.entrySet().stream()
                    .sorted((f, s) -> s.getValue() - f.getValue())
                    .forEach(e -> writer.println(e.getKey() + " - " + e.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void lineNumbers() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RESOURCES_FOLDER_PATH + "inputLineNumbers.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(RESOURCES_FOLDER_PATH + "output.txt")) {
            for (int i = 0; i < lines.size(); i++) {
                writer.println(i + 1 + ". " + lines.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void countCharacterTypes() {
        Map<String, Integer> characterTypesCount = new LinkedHashMap<>();
        characterTypesCount.put("Vowels", 0);
        characterTypesCount.put("Other symbols", 0);
        characterTypesCount.put("Punctuation", 0);
        try (BufferedReader reader = new BufferedReader(new FileReader(RESOURCES_FOLDER_PATH + "input.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                addCharTypesCount(line, characterTypesCount);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(RESOURCES_FOLDER_PATH + "output.txt"))) {
            characterTypesCount.forEach((k, v) -> writer.println(k + ": " + v));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addCharTypesCount(String line, Map<String, Integer> characterTypesCount) {
        for (char c : line.toCharArray()) {
            String type = null;
            if (isVowel(c)) {
                type = "Vowels";
            } else if (isPunctuation(c)) {
                type = "Punctuation";
            } else if (c == ' ') {
                continue;
            } else {
                type = "Other symbols";
            }
            characterTypesCount.put(type, characterTypesCount.get(type) + 1);
        }
    }

    private static boolean isPunctuation(char c) {
        return c == ',' || c == '!' || c == '.' || c == '?';
    }

    private static boolean isVowel(char c) {
        Set<Character> vowels = new HashSet<>(Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        return vowels.contains(c);
    }

    private static void allCapitals() {
        String[] text = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(RESOURCES_FOLDER_PATH + "input.txt"))) {
            text = reader.lines().map(String::toUpperCase).toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(RESOURCES_FOLDER_PATH + "output.txt")) {
            for (String line : text) {
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sumBytes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RESOURCES_FOLDER_PATH + "input.txt"))) {

            String line = reader.readLine();
            long sum = 0;
            while (line != null) {
                for (char c : line.toCharArray()) {
                    sum += c;
                }
                line = reader.readLine();
            }
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sumLines() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RESOURCES_FOLDER_PATH + "input.txt"))) {

            String line = reader.readLine();
            while (line != null) {
                long sum = 0;
                for (char c : line.toCharArray()) {
                    sum += c;
                }
                System.out.println(sum);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
