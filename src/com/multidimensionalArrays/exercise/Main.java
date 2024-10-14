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
//        crossfire(); //70% passing tests!!!
//        crossfire2Point0(); //90% passing tests!!!
        theHeiganDance(); //Solution from 19.01.2023
    }

    private static void theHeiganDance() {
        int[][] chamberMatrix = new int[15][15];

        fillChamber(chamberMatrix);

        int playerHitPoints = 18500;
        int[] playerPosition = {7, 7};

        double heiganHitPoints = 3000000;
        double damageToHeigan = Double.parseDouble(SCANNER.nextLine());

        String[] command;
        String attack;
        int attackRow;
        int attackCol;

        boolean isTakingCloudDamage = false;
        boolean playerIsKilled = false;
        boolean heiganIsDefeated = false;
        boolean isKilledByEruption = false;

        while (!playerIsKilled) {
            fillChamber(chamberMatrix);
            heiganHitPoints -= damageToHeigan;

            if (isTakingCloudDamage) {
                playerHitPoints = getCloudDamage(playerHitPoints);
                isTakingCloudDamage = false;
                if (playerHitPoints <= 0) {
                    playerIsKilled = true;
                }
            }
            if (heiganHitPoints <= 0) {
                heiganIsDefeated = true;
            }

            command = SCANNER.nextLine().split("\\s+");

            attack = command[0];
            attackRow = Integer.parseInt(command[1]);
            attackCol = Integer.parseInt(command[2]);

            int previousRow = playerPosition[0];
            int previousCol = playerPosition[1];


            if (heiganIsDefeated || playerIsKilled) {
                break;
            }

            switch (attack) {
                case "Cloud":
                    attackCells(chamberMatrix, attackRow, attackCol);
                    if (playerIsInAttacksRange(playerPosition, attackRow, attackCol)) {

                        playerMove(chamberMatrix, playerPosition);

                        if (playerDidNotMove(playerPosition, previousRow, previousCol)
                                || playerIsInAttacksRange(playerPosition, attackRow, attackCol)) {

                            playerHitPoints = getCloudDamage(playerHitPoints);

                            isTakingCloudDamage = true;
                        }
                    }

                    if (playerHitPoints <= 0) {
                        playerIsKilled = true;
                        break;
                    }
                    break;
                case "Eruption":
                    attackCells(chamberMatrix, attackRow, attackCol);
                    if (playerIsInAttacksRange(playerPosition, attackRow, attackCol)) {

                        playerMove(chamberMatrix, playerPosition);

                        if (playerDidNotMove(playerPosition, previousRow, previousCol)
                                || playerIsInAttacksRange(playerPosition, attackRow, attackCol)) {

                            playerHitPoints = getEruptionDamage(playerHitPoints);
                        }
                    }

                    if (playerHitPoints <= 0) {
                        playerIsKilled = true;
                        isKilledByEruption = true;
                        break;
                    }

                    break;
            }
        }

        if (heiganIsDefeated) {
            System.out.println("Heigan: Defeated!");
        } else {
            System.out.printf("Heigan: %.2f%n", heiganHitPoints);
        }

        if (playerIsKilled) {
            if (isKilledByEruption) {
                System.out.println("Player: Killed by Eruption");
            } else {
                System.out.println("Player: Killed by Plague Cloud");
            }
        } else {
            System.out.println("Player: " + playerHitPoints);
        }

        System.out.println("Final position: " + playerPosition[0] + ", " + playerPosition[1]);
    }

    private static int getEruptionDamage(int playerHitPoints) {
        return playerHitPoints - 6000;
    }

    private static boolean playerIsInAttacksRange(int[] playerPosition, int attackRow, int attackCol) {
        for (int row = attackRow - 1; row <= attackRow + 1; row++) {
            for (int col = attackCol - 1; col <= attackCol + 1; col++) {
                if (playerPosition[0] == row && playerPosition[1] == col) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void attackCells(int[][] chamberMatrix, int attackRow, int attackCol) {
        for (int row = attackRow - 1; row <= attackRow + 1; row++) {
            for (int col = attackCol - 1; col <= attackCol + 1; col++) {
                if (isInBounds(row, col, chamberMatrix)) {
                    chamberMatrix[row][col] = -1;
                }
            }
        }
    }

    private static boolean playerDidNotMove(int[] playerPosition, int previousRow, int previousCol) {
        return previousRow == playerPosition[0] && previousCol == playerPosition[1];
    }

    private static int getCloudDamage(int playerHitPoints) {
        return playerHitPoints - 3500;
    }

    private static void playerMove(int[][] chamberMatrix, int[] playerPosition) {
        if (!cellIsDamaged(playerPosition[0] - 1, playerPosition[1], chamberMatrix)
                && isInBounds(playerPosition[0] - 1, playerPosition[1], chamberMatrix)) {
            playerPosition[0] -= 1;
        } else if (!cellIsDamaged(playerPosition[0], playerPosition[1] + 1, chamberMatrix)
                && isInBounds(playerPosition[0], playerPosition[1] + 1, chamberMatrix)) {
            playerPosition[1] += 1;
        } else if (!cellIsDamaged(playerPosition[0] + 1, playerPosition[1], chamberMatrix)
                && isInBounds(playerPosition[0] + 1, playerPosition[1], chamberMatrix)) {
            playerPosition[0] += 1;
        } else if (!cellIsDamaged(playerPosition[0], playerPosition[1] - 1, chamberMatrix)
                && isInBounds(playerPosition[0], playerPosition[1] - 1, chamberMatrix)) {
            playerPosition[1] -= 1;
        }
    }

    private static void fillChamber(int[][] chamberMatrix) {
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                chamberMatrix[row][col] = 1;
            }
        }
    }

    private static boolean isInBounds(int row, int col, int[][] matrix) {
        return (row >= 0 && col >= 0 && row < matrix.length && col < matrix[row].length);
    }

    private static boolean cellIsDamaged(int row, int col, int[][] matrix) {
        if (isInBounds(row, col, matrix)) {
            return matrix[row][col] < 0;
        }
        return false;
    }

    private static void crossfire2Point0() {
        int[] dimensions = parseIntArray(SCANNER.nextLine());

        List<List<Integer>> matrix = new ArrayList<>();
        int i = 1;
        for (int row = 0; row < dimensions[0]; row++) {
            matrix.add(new ArrayList<>());
            for (int col = 0; col < dimensions[1]; col++) {
                matrix.get(row).add(i++);
            }
        }

        String input = SCANNER.nextLine();


        while (!"Nuke it from orbit".equals(input)) {
            int[] explosionInfo = parseIntArray(input);
            int row = explosionInfo[0];
            int col = explosionInfo[1];
            int radius = explosionInfo[2];

            if (radius == 0 && isInBounds(matrix, row, col)) {
                matrix.get(row).set(col, -1);
            } else {
                if (row >= 0 && row < matrix.size()) {
                    int startCol = Math.max(0, col - radius);
                    int endCol = Math.min(col + radius, matrix.get(row).size() - 1);

                    for (int c = endCol; c >= startCol; c--) {
                        matrix.get(row).set(c, -1);
                    }
                }

                if (col >= 0) {
                    int startRow = Math.max(row - radius, 0);
                    int endRow = Math.min(row + radius, matrix.size());
                    for (int r = startRow; r < endRow; r++) {
                        if (col < matrix.get(r).size()) {
                            matrix.get(r).set(col, -1);
                        }
                    }
                }

            }

            matrix.forEach(r -> r.removeIf(n -> n <= 0));
            matrix.removeIf(List::isEmpty);

            input = SCANNER.nextLine();
        }


        matrix.forEach(row -> System.out.println(row.toString().replaceAll("[\\,\\[\\]]", "")));

    }

    private static void crossfire() {
        int[] dimensions = parseIntArray(SCANNER.nextLine());

        int[][] matrix = new int[dimensions[0]][dimensions[1]];

        fillMatrix(matrix);

        String input = SCANNER.nextLine();

        while (!"Nuke it from orbit".equals(input)) {
            int[] explosionInfo = parseIntArray(input);
            int row = explosionInfo[0];
            int col = explosionInfo[1];
            int radius = explosionInfo[2];

            if (radius > 0) {
                blowMatrix(matrix, row, col, radius);
                matrix = removeBlownIndices(matrix);
            } else {
                if (isInBounds(matrix, row, col)) {
                    matrix[row][col] = -1;
                    matrix = removeBlownIndices(matrix);
                }
            }

            input = SCANNER.nextLine();
        }

        printMatrixWithUnevenLength(matrix);

    }

    private static void printMatrixWithUnevenLength(int[][] matrix) {
        for (int[] row : matrix) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }


    private static void blowMatrix(int[][] matrix, int row, int col, int radius) {
        if (col >= 0) {
            for (int r = Math.max(row - radius, 0); r < Math.min(row + radius, matrix.length); r++) {
                if (isInBounds(matrix, r, col)) {
                    matrix[r][col] = -1;
                }
            }
        }

        if (row >= 0 && row < matrix.length) {
            for (int c = Math.max(col - radius, 0); c < Math.min(col + radius, matrix[row].length); c++) {
                if (isInBounds(matrix, row, c)) {
                    matrix[row][c] = -1;
                }

            }
        }
    }

    private static int[][] removeBlownIndices(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            int[] newRow = Arrays.stream(matrix[row]).filter(n -> n >= 0).toArray();
            if (newRow == null || newRow.length == 0) {
                matrix = getNewMatrix(matrix, row);
                row--;
            } else {
                matrix[row] = newRow;
            }
        }

        return matrix;
    }

    private static int[][] getNewMatrix(int[][] matrix, int row) {
        int[][] newMatrix = new int[matrix.length - 1][];

        if (row >= 0) System.arraycopy(matrix, 0, newMatrix, 0, row);

        if (newMatrix.length - row >= 0) System.arraycopy(matrix, row + 1, newMatrix, row, newMatrix.length - row);

        return newMatrix;
    }

    private static void fillMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            matrix[row] = IntStream.rangeClosed(row * matrix[row].length + 1, (row + 1) * matrix[row].length).toArray();
        }
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
        int[] dimensions = parseIntArray(SCANNER.nextLine());
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

    private static boolean isInBounds(int[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static boolean isInBounds(List<List<Integer>> matrix, int row, int col) {
        return row >= 0 && row < matrix.size() && col >= 0 && col < matrix.get(row).size();
    }

    private static void maximalSum() {
        int[] dimensions = parseIntArray(SCANNER.nextLine());

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

    private static int[] parseIntArray(String text) {
        return Arrays.stream(text.split(" ")).mapToInt(Integer::parseInt).toArray();
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

