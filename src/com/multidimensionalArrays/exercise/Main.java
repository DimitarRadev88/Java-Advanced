package com.multidimensionalArrays.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
//        fillTheMatrix();
//        matrixOfPalindromes();
//        diagonalDifference();
//        maximalSum();
//        matrixShuffling();
//        stringMatrixRotation();

    }

    private static void stringMatrixRotation() {
        String rotate = SCANNER.nextLine();
        int rotationDegrees = Integer.parseInt(rotate.substring("Rotate(".length(), rotate.length() - 1));

        List<String> words = new ArrayList<>();
        addWordsFromConsole(words);

        printRotatedMatrix(words, rotationDegrees);

    }

    private static void printRotatedMatrix(List<String> words, int rotationDegrees) {
        int longestWord = words.stream().mapToInt(String::length).max().getAsInt();

        List<String> resizedWords = words.stream().map(w -> {
            if (w.length() < longestWord) {
                w = w.concat(" ".repeat(longestWord - w.length()));
            }
            return w;
        }).collect(Collectors.toList());

        if (rotationDegrees == 0 || rotationDegrees % 360 == 0) {
            resizedWords.forEach(System.out::println);
        } else if (rotationDegrees % 360 == 90) {
            printAt90Degrees(longestWord, resizedWords);
        } else if (rotationDegrees % 360 == 270) {
            printAt270Degrees(longestWord, resizedWords);
        } else if (rotationDegrees % 360 == 180) {
            printAt180Degrees(resizedWords);
        }
    }

    private static void printAt90Degrees(int longestWord, List<String> resizedWords) {
        for (int col = 0; col < longestWord; col++) {
            for (int row = resizedWords.size() - 1; row >= 0; row--) {
                System.out.print(resizedWords.get(row).charAt(col));
            }
            System.out.println();
        }
    }

    private static void printAt180Degrees(List<String> resizedWords) {
        for (int row = resizedWords.size() - 1; row >= 0; row--) {
            System.out.println(new StringBuilder(resizedWords.get(row)).reverse());
        }
    }

    private static void printAt270Degrees(int longestWord, List<String> resizedWords) {
        for (int col = longestWord - 1; col >= 0; col--) {
            for (String resizedWord : resizedWords) {
                System.out.print(resizedWord.charAt(col));
            }
            System.out.println();
        }
    }

    private static void addWordsFromConsole(List<String> words) {
        String input = SCANNER.nextLine();
        while (!"END".equals(input)) {
            words.add(input);
            input = SCANNER.nextLine();
        }
    }

    private static void matrixShuffling() {
        int[] dimensions = readIntArray();
        String[][] matrix = new String[dimensions[0]][dimensions[1]];

        fillMatrix(matrix);
        String input = SCANNER.nextLine();

        while (!"END".equals(input)) {
            try {
                parseCommand(input, matrix);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            input = SCANNER.nextLine();
        }
    }

    private static void fillMatrix(String[][] matrix) {
        IntStream.range(0, matrix.length).forEach(n -> matrix[n] = SCANNER.nextLine().split(" "));
    }

    private static void parseCommand(String input, String[][] matrix) {
        if (!input.startsWith("swap ")) {
            throw new IllegalArgumentException("Invalid input!");
        }

        String[] command = input.split("\\s+");

        if (command.length != 5) {
            throw new IllegalArgumentException("Invalid input!");
        }

        int[] swapCoordinates = Arrays.stream(command, 1, 5)
                .mapToInt(Integer::parseInt)
                .toArray();

        swapElementsInMatrix(matrix, swapCoordinates);

        printMatrix(matrix);
    }

    private static void swapElementsInMatrix(String[][] matrix, int[] swapCoordinates) {
        checkIfInBounds(matrix, swapCoordinates[0], swapCoordinates[1]);
        checkIfInBounds(matrix, swapCoordinates[2], swapCoordinates[3]);

        String temp = matrix[swapCoordinates[0]][swapCoordinates[1]];
        matrix[swapCoordinates[0]][swapCoordinates[1]] = matrix[swapCoordinates[2]][swapCoordinates[3]];
        matrix[swapCoordinates[2]][swapCoordinates[3]] = temp;
    }

    private static void checkIfInBounds(String[][] matrix, int row, int col) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length) {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    private static void maximalSum() {
        int[] dimensions = readIntArray();

        int[][] matrix = new int[dimensions[0]][dimensions[1]];
        fillIntMatrix(matrix);

        int[] subMatrixIndex = getMaximalSumSubMatrix(matrix);

        System.out.println("Sum = " + getMatrixSum(matrix, subMatrixIndex[0], subMatrixIndex[1]));
        printMatrix(matrix, subMatrixIndex[0], subMatrixIndex[0] + 3, subMatrixIndex[1], subMatrixIndex[1] + 3);

    }

    private static void printMatrix(int[][] matrix, int startRow, int endRow, int startCol, int endCol) {
        Arrays.stream(matrix, startRow, endRow).forEach(r -> {
            Arrays.stream(r, startCol, endCol).forEach(n -> System.out.print(n + " "));
            System.out.println();
        });
    }

    private static int[] getMaximalSumSubMatrix(int[][] matrix) {
        int[] rowAndCol = null;
        int sum = 0;
        for (int row = 0; row < matrix.length - 2; row++) {
            for (int col = 0; col < matrix[row].length - 2; col++) {
                int subMatrixSum = getMatrixSum(matrix, row, col);
                if (subMatrixSum > sum) {
                    sum = subMatrixSum;
                    rowAndCol = new int[]{row, col};
                }
            }
        }
        if (sum == 0) {
            return new int[]{0, 0};
        }

        return rowAndCol;
    }

    private static int getMatrixSum(int[][] matrix, int row, int col) {

        return Arrays.stream(matrix, row, row + 3)
                .mapToInt(r -> Arrays.stream(r, col, col + 3).sum())
                .sum();
    }

    private static void diagonalDifference() {
        int size = Integer.parseInt(SCANNER.nextLine());

        int[][] matrix = new int[size][size];
        fillIntMatrix(matrix);

        System.out.println(getMatrixDiagonalDifference(matrix));
    }

    private static int getMatrixDiagonalDifference(int[][] matrix) {
        return Math.abs(getDiagonalSum(matrix, "primary") - getDiagonalSum(matrix, "secondary"));
    }

    private static int getDiagonalSum(int[][] matrix, String diagonal) {
        return switch (diagonal) {
            case "primary":
                yield getPrimaryDiagonalSum(matrix);
            case "secondary":
                yield getSecondaryDiagonalSum(matrix);
            default:
                throw new IllegalArgumentException();
        };
    }

    private static int getSecondaryDiagonalSum(int[][] matrix) {
        int sum = 0;
        for (int row = matrix.length - 1, col = 0; row >= 0 && col < matrix[row].length; row--, col++) {
            sum += matrix[row][col];
        }
        return sum;
    }

    private static int getPrimaryDiagonalSum(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];
        }
        return sum;
    }

    private static void fillIntMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            matrix[row] = Arrays.stream(SCANNER.nextLine().split(" "), 0, matrix[row].length)
                    .mapToInt(Integer::parseInt).toArray();

        }
    }

    private static int[] readIntArray() {
        return Arrays.stream(SCANNER.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static void matrixOfPalindromes() {
        int rows = SCANNER.nextInt();
        int cols = SCANNER.nextInt();
        String[][] matrix = new String[rows][cols];

        fillPalindromeMatrix(matrix);

        printMatrix(matrix);
    }

    private static void printMatrix(String[][] matrix) {
        for (String[] row : matrix) {
            System.out.println(String.join(" ", row));
        }
    }

    private static void fillPalindromeMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                char firstAndLast = (char) (row + 97);
                char middle = (char) (firstAndLast + col);
                matrix[row][col] = "" + firstAndLast + middle + firstAndLast;
            }
        }
    }

    private static void fillTheMatrix() {
        String[] input = SCANNER.nextLine().split(", ");
        int size = Integer.parseInt(input[0]);
        String pattern = input[1];

        int[][] matrix = new int[size][size];
        fillMatrix(matrix, pattern);

        printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        printMatrix(matrix, 0, matrix.length, 0, matrix[0].length);
    }

    private static void fillMatrix(int[][] matrix, String pattern) {
        switch (pattern) {
            case "A" -> fillWithAPattern(matrix);
            case "B" -> fillWithBPattern(matrix);
        }
    }

    private static void fillWithBPattern(int[][] matrix) {
        int number = 1;
        for (int col = 0; col < matrix[0].length; col++) {
            if (col % 2 == 0) {
                for (int row = 0; row < matrix.length; row++) {
                    matrix[row][col] = number++;
                }
            } else {
                for (int row = matrix.length - 1; row >= 0; row--) {
                    matrix[row][col] = number++;
                }
            }
        }
    }

    private static void fillWithAPattern(int[][] matrix) {
        int number = 1;
        for (int col = 0; col < matrix[0].length; col++) {
            for (int row = 0; row < matrix.length; row++) {
                matrix[row][col] = number++;
            }
        }
    }
}

