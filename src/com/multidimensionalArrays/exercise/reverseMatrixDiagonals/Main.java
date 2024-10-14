package com.multidimensionalArrays.exercise.reverseMatrixDiagonals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] dimensions = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] matrix = new int[dimensions[0]][dimensions[1]];
        for (int row = 0; row < dimensions[0]; row++) {
            matrix[row] = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        }

        for (int col = matrix[0].length - 1; col > 0; col--) {
            for (int r = matrix.length - 1, c = col; r >= 0 && c < matrix[r].length; r--, c++) {

                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }

        for (int row = matrix.length - 1; row >= 0; row--) {
            for (int r = row, c = 0; r >= 0 && c < matrix[r].length; r--, c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }
}

