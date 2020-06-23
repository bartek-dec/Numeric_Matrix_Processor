package com.example;

public class MatrixUtil {

    public String displayMatrix(Matrix m) {
        StringBuilder builder = new StringBuilder();
        double[][] matrix = m.getMatrix();
        for (double[] row : matrix) {
            for (double element : row) {
                builder.append(String.format("%.2f", element));
                builder.append(" ");
            }
            builder.append("\n");
        }
        builder.append("\n");

        return builder.toString();
    }

    public int[] checkSize(String s) {
        String[] input = s.split("\\s");
        if (input.length != 2) {
            return null;
        }

        try {
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);

            if (n < 1 || m < 1) {
                return null;
            }

            return new int[]{n, m};
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public double[][] checkMatrix(String[][] input, int[] size) {
        int length = input.length;
        double[][] matrix = new double[length][];

        for (int i = 0; i < length; i++) {
            int row = input[i].length;

            if (row != size[1]) {
                return null;
            }
            matrix[i] = new double[row];
            for (int j = 0; j < row; j++) {
                try {
                    matrix[i][j] = Double.parseDouble(input[i][j]);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }
        return matrix;
    }

    public Double checkConstant(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
