package com.multidimensionalArrays.lab;

import java.util.*;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

//        compareMatrices();
//        positionsOf();
//        intersectionOfTwoMatrices();
//        sumMatrixElements();
//        printDiagonalsOfSquareMatrix();
//        maximumSumOf2x2SubMatrix();
//        findTheRealQueen();
        wrongMeasurements();
    }

    private static void wrongMeasurements() {
        int rows = Integer.parseInt(SCANNER.nextLine());

        int[][] matrix = readIntMatrix(rows, " ");
        int[] location = getIntArray(" ");

        int mistakenValue = matrix[location[0]][location[1]];

        Deque<Integer> newValuesQueue = new ArrayDeque<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == mistakenValue) {
                    newValuesQueue.offer(getNewValue(matrix, row, col, mistakenValue));
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == mistakenValue) {
                    matrix[row][col] = newValuesQueue.poll();
                }
            }
        }

        printIntMatrix(matrix);
    }

    private static int getNewValue(int[][] matrix, int row, int col, int mistakenValue) {
        int newValue = 0;

        if (row - 1 >= 0 && matrix[row - 1][col] != mistakenValue) {
            newValue += matrix[row - 1][col];;
        }
        if (row + 1 < matrix.length && matrix[row + 1][col] != mistakenValue) {
            newValue += matrix[row + 1][col];
        }
        if (col - 1 >= 0 && matrix[row][col - 1] != mistakenValue) {
            newValue += matrix[row][col - 1];
        }
        if (col + 1 < matrix[row].length && matrix[row][col + 1] != mistakenValue) {
            newValue += matrix[row][col + 1];
        }


        return newValue;
    }

    private static void findTheRealQueen() {
        char[][] board = readBoard();

        int[] positions = findQueenPositions(board);

        System.out.println(positions[0] + " " + positions[1]);
    }

    private static char[][] readBoard() {
        char[][] board = new char[8][8];
        for (int row = 0; row < 8; row++) {
            board[row] = String.join("", SCANNER.nextLine().split(" ")).toCharArray();
        }
        return board;
    }

    private static int[] findQueenPositions(char[][] board) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (isTheRealQueen(row, col, board)) {
                    return new int[]{row, col};
                }
            }
        }
        throw new IllegalStateException();
    }

    private static boolean isTheRealQueen(int row, int col, char[][] board) {
        if (!isOnlyOneOnTheRow(board[row])) {
            return false;
        }
        if (!isOnlyOneOnTheCol(board, col)) {
            return false;
        }
        return isOnlyOneOnTheDiagonals(board, row, col);
    }

    private static boolean isOnlyOneOnTheDiagonals(char[][] board, int row, int col) {
        int queens = 0;
        for (int r = row, c = col; r < 8 && c < 8; r++, c++) {
            if (board[r][c] == 'q') {
                queens++;
            }
        }

        for (int r = row, c = col; r >= 0 && c >= 0; r--, c--) {
            if (board[r][c] == 'q') {
                queens++;
            }
        }

        for (int r = row, c = col; r >= 0 && c < 8; r--, c++) {
            if (board[r][c] == 'q') {
                queens++;
            }
        }

        for (int r = row, c = col; r < 8 && c >= 0; r++, c--) {
            if (board[r][c] == 'q') {
                queens++;
            }
        }

        return queens == 4;
    }

    private static boolean isOnlyOneOnTheCol(char[][] board, int col) {
        int count = 0;
        for (int row = 0; row < 8; row++) {
            char current = board[row][col];
            if (current == 'q') {
                count++;
            }
        }

        return count == 1;
    }

    private static boolean isOnlyOneOnTheRow(char[] row) {
        int count = 0;
        for (char c : row) {
            if (c == 'q') {
                count++;
            }
        }

        return count == 1;
    }


    private static void maximumSumOf2x2SubMatrix() {
        String delimiter = ",\\s+";
        int[] dimensions = getIntArray(delimiter);

        int[][] matrix = readIntMatrix(dimensions[0], delimiter);

        int[][] maximumSumMatrixAndSum = getMaximumSumSubMatrix(matrix);

        printIntMatrix(maximumSumMatrixAndSum);
        System.out.println(getMatrixSum(maximumSumMatrixAndSum));
    }

    private static void printIntMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    private static int[][] getMaximumSumSubMatrix(int[][] matrix) {
        int[][] maximumSumMatrix = new int[2][2];
        int[][] currentMatrix;
        int sum = 0;
        for (int row = 0; row < matrix.length - 1; row++) {
            for (int col = 0; col < matrix[row].length - 1; col++) {
                currentMatrix = fill2X2SubMatrix(matrix, row, col);
                if (getMatrixSum(currentMatrix) > sum) {
                    maximumSumMatrix = fill2X2SubMatrix(matrix, row, col);
                    sum = getMatrixSum(maximumSumMatrix);
                }
            }
        }

        return maximumSumMatrix;
    }

    private static int[][] fill2X2SubMatrix(int[][] matrix, int row, int col) {
        int[][] subMatrix = new int[2][2];
        subMatrix[0][0] = matrix[row][col];
        subMatrix[0][1] = matrix[row][col + 1];
        subMatrix[1][0] = matrix[row + 1][col];
        subMatrix[1][1] = matrix[row + 1][col + 1];

        return subMatrix;
    }

    private static void printDiagonalsOfSquareMatrix() {
        int dimensions = Integer.parseInt(SCANNER.nextLine());

        int[][] matrix = readIntMatrix(dimensions, " ");

        printMatrixDiagonals(matrix);
    }

    private static void printMatrixDiagonals(int[][] matrix) {
        printTopLeftDiagonal(matrix);
        System.out.println();
        printBottomLeftDiagonal(matrix);
    }

    private static void printBottomLeftDiagonal(int[][] matrix) {
        for (int row = matrix.length - 1, col = 0; row >= 0 && col < matrix[row].length; row--, col++) {
            System.out.print(matrix[row][col] + " ");
        }
    }

    private static void printTopLeftDiagonal(int[][] matrix) {
        for (int row = 0, col = 0; row < matrix.length && col < matrix[row].length; row++, col++) {
            System.out.print(matrix[row][col] + " ");
        }
    }

    private static void sumMatrixElements() {
        String delimiter = ",\\s+";
        int[] dimensions = getIntArray(delimiter);

        int[][] matrix = readIntMatrix(dimensions[0], delimiter);

        System.out.println(dimensions[0]);
        System.out.println(dimensions[1]);
        System.out.println(getMatrixSum(matrix));

    }

    private static int getMatrixSum(int[][] matrix) {
        return Arrays.stream(matrix).mapToInt(arr -> Arrays.stream(arr).sum()).sum();
    }

    private static void intersectionOfTwoMatrices() {
        int rows = Integer.parseInt(SCANNER.nextLine());
        int cols = Integer.parseInt(SCANNER.nextLine());

        char[][] first = readCharMatrix(rows, cols);
        char[][] second = readCharMatrix(rows, cols);

        char[][] intersectingElements = getIntersectingElementsMatrix(first, second);

        printCharMatrix(intersectingElements);
    }

    private static void printCharMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static char[][] getIntersectingElementsMatrix(char[][] first, char[][] second) {
        char[][] result = new char[first.length][first[0].length];

        for (int row = 0; row < first.length; row++) {
            for (int col = 0; col < first[row].length; col++) {
                if (first[row][col] == second[row][col]) {
                    result[row][col] = first[row][col];
                } else {
                    result[row][col] = '*';
                }
            }
        }

        return result;
    }

    private static char[][] readCharMatrix(int rows, int cols) {
        char[][] matrix = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            matrix[row] = SCANNER.nextLine().toCharArray();
        }

        return matrix;
    }

    private static void positionsOf() {
        int[] dimension = getIntArray(" ");
        int[][] matrix = readIntMatrix(dimension[0], " ");

        int toFind = SCANNER.nextInt();

        List<int[]> positions = findNumberPositions(toFind, matrix);

        if (!positions.isEmpty()) {
            positions.forEach(p -> {
                        System.out.print(p[0] + " " + p[1]);
                        System.out.println();
                    }
            );
        } else {
            System.out.println("not found");
        }

    }

    private static List<int[]> findNumberPositions(int toFind, int[][] matrix) {
        List<int[]> positions = new ArrayList<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == toFind) {
                    positions.add(new int[]{row, col});
                }

            }
        }

        return positions;
    }

    private static void compareMatrices() {
        int[] dimensions = getIntArray(" ");

        int[][] first = readIntMatrix(dimensions[0], " ");

        dimensions = getIntArray(" ");

        if (first.length != dimensions[0] || first[0].length != dimensions[1]) {
            System.out.println("not equal");
            return;
        }

        int[][] second = readIntMatrix(dimensions[0], " ");

        boolean areEqual = multiDimArraysAreEqual(first, second);

        System.out.println(areEqual ? "equal" : "not equal");

    }

    private static boolean multiDimArraysAreEqual(int[][] first, int[][] second) {
        for (int row = 0; row < first.length; row++) {
            for (int col = 0; col < first[row].length; col++) {
                if (first[row][col] != second[row][col]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static int[] getIntArray(String delimiter) {
        return Arrays
                .stream(SCANNER.nextLine().split(delimiter))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static int[][] readIntMatrix(int rows, String delimiter) {
        int[][] matrix = new int[rows][];
        for (int row = 0; row < rows; row++) {
            matrix[row] = getIntArray(delimiter);
        }

        return matrix;
    }
}
