package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MatrixProcessorTest {

    private MatrixProcessor matrixProcessor;
    private Matrix A;
    private double[][] array;
    private int rows;
    private int columns;

    @BeforeEach
    void setUp() {
        matrixProcessor = new MatrixProcessor();
        rows = 2;
        columns = 3;
        array = new double[][]{{1, 0, 3}, {4, 5, 6}};
        A = new Matrix(array, rows, columns);
    }

    @Test
    void addMatricesWhenTheSameSizeThanNewMatrix() {
        double[][] arrayB = new double[][]{{1, 2, 3}, {4, 5, 6}};
        Matrix B = new Matrix(arrayB, 2, 3);

        double[][] arrayExpected = new double[][]{{2, 2, 6}, {8, 10, 12}};
        Matrix expected = new Matrix(arrayExpected, 2, 3);

        assertEquals(expected, matrixProcessor.addMatrices(A, B));
    }

    @Test
    void addMatricesWhenDifferentSizeThanNull() {
        double[][] arrayB = new double[][]{{1, 2, 3, 4}, {4, 5, 6, 4}};
        Matrix B = new Matrix(arrayB, 2, 4);

        assertNull(matrixProcessor.addMatrices(A, B));
    }

    @Test
    void multiplyByConstantTest() {
        double[][] arrayExpected = new double[][]{{-1, 0, -3}, {-4, -5, -6}};
        Matrix expected = new Matrix(arrayExpected, 2, 3);
        int constant = -1;

        assertEquals(expected, matrixProcessor.multiplyByConstant(A, constant));
    }

    @Test
    void multiplyMatricesWhenColumnsInFirstNotEqualRowsInSecondThanNull() {
        double[][] arrayB = new double[][]{{1, 2, 3}, {4, 5, 6}};
        Matrix B = new Matrix(arrayB, 2, 3);

        assertNull(matrixProcessor.multiplyMatrices(A, B));
    }

    @Test
    void multiplyMatricesWhenBothMatricesOKThanNewMatrix() {
        double[][] arrayB = new double[][]{{1, 2}, {3, 4}, {5, 6}};
        Matrix B = new Matrix(arrayB, 3, 2);

        double[][] arrayExpected = new double[][]{{16, 20}, {49, 64}};
        Matrix expected = new Matrix(arrayExpected, 2, 2);

        assertEquals(expected, matrixProcessor.multiplyMatrices(A, B));
    }

    @Test
    void mainDiagonalTransposition() {
        double[][] arrayExpected = new double[][]{{1, 4}, {0, 5}, {3, 6}};
        Matrix expected = new Matrix(arrayExpected, 3, 2);

        assertEquals(expected, matrixProcessor.mainDiagonalTransposition(A));
    }

    @Test
    void sideDiagonalTransposition() {
        double[][] arrayExpected = new double[][]{{6, 3}, {5, 0}, {4, 1}};
        Matrix expected = new Matrix(arrayExpected, 3, 2);

        assertEquals(expected, matrixProcessor.sideDiagonalTransposition(A));
    }

    @Test
    void verticalLineTransposition() {
        double[][] arrayExpected = new double[][]{{3, 0, 1}, {6, 5, 4}};
        Matrix expected = new Matrix(arrayExpected, 2, 3);

        assertEquals(expected, matrixProcessor.verticalLineTransposition(A));
    }

    @Test
    void horizontalLineTransposition() {
        double[][] arrayExpected = new double[][]{{4, 5, 6}, {1, 0, 3}};
        Matrix expected = new Matrix(arrayExpected, 2, 3);

        assertEquals(expected, matrixProcessor.horizontalLineTransposition(A));
    }

    @Test
    void calculateDet() {
        double[][] arrayB = new double[][]{{1, 7, 7}, {6, 6, 4}, {4, 2, 1}};
        Matrix B = new Matrix(arrayB, 3, 3);

        assertEquals(-16, matrixProcessor.calculateDet(B));
    }

    @Test
    void getMatrixOfCofactors() {
        double[][] arrayB = new double[][]{{2, -1, 0}, {0, 1, 2}, {1, 1, 0}};
        Matrix B = new Matrix(arrayB, 3, 3);

        double[][] arrayExpected = new double[][]{{-2, 2, -1}, {0, 0, -3}, {-2, -4, 2}};
        Matrix expected = new Matrix(arrayExpected, 3, 3);

        assertEquals(expected, matrixProcessor.getMatrixOfCofactors(B));
    }
}