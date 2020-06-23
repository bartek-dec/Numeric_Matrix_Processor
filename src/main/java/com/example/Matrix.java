package com.example;

import java.util.Arrays;

public class Matrix {

    private int rows;
    private int columns;
    private double[][] matrix;

    public Matrix(double[][] matrix, int rows, int columns) {
        this(matrix);
        this.rows = rows;
        this.columns = columns;
    }

    public Matrix(double[][] matrix, int[] size) {
        this(matrix);
        this.rows = size[0];
        this.columns = size[1];
    }

    private Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix matrix1 = (Matrix) o;

        if (rows != matrix1.rows) return false;
        if (columns != matrix1.columns) return false;
        return Arrays.deepEquals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        int result = rows;
        result = 31 * result + columns;
        result = 31 * result + Arrays.deepHashCode(matrix);
        return result;
    }
}
