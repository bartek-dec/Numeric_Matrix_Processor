package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixUtilTest {

    private MatrixUtil matrixUtil;
    private Matrix matrix;
    private double[][] array;
    private int rows;
    private int columns;

    @BeforeEach
    void setUp() {
        matrixUtil = new MatrixUtil();
        rows = 2;
        columns = 3;
        array = new double[][]{{1, 2, 3}, {4, 5, 6}};
        matrix = new Matrix(array, rows, columns);
    }

    @Test
    void displayMatrix() {
        String result = "1.00 2.00 3.00 \n4.00 5.00 6.00 \n\n";
        assertEquals(result, matrixUtil.displayMatrix(matrix));
    }

    @Test
    void checkSizeWhenValidStringThanValidNumbers() {
        String input = "2 2";
        int[] size = new int[]{2, 2};
        assertArrayEquals(size, matrixUtil.checkSize(input));
    }

    @Test
    void checkSizeWhenNumbersLessThanOneThanNull() {
        String input = "0 4";
        assertNull(matrixUtil.checkSize(input));
    }

    @Test
    void checkSizeWhenThreeNumbersThanNull() {
        String input = "2 2 2";
        assertNull(matrixUtil.checkSize(input));
    }

    @Test
    void checkSizeWhenLettersThanNull() {
        String input = "2 p";
        assertNull(matrixUtil.checkSize(input));
    }

    @Test
    void checkMatrixWhenInputOkThanReturnArray() {
        String[][] input = new String[][]{{"1", "2", "3"}, {"4", "5", "6"}};
        int[] size = new int[]{2, 3};
        assertArrayEquals(array, matrixUtil.checkMatrix(input, size));
    }

    @Test
    void checkMatrixWhenEmptyStringInInputThanNull() {
        String[][] input = new String[][]{{""}, {"4", "5", "6"}};
        int[] size = new int[]{2, 3};
        assertNull(matrixUtil.checkMatrix(input, size));
    }

    @Test
    void checkMatrixWhenLetterInInputThanNull() {
        String[][] input = new String[][]{{"1", "A", "3"}, {"4", "5", "6"}};
        int[] size = new int[]{2, 3};
        assertNull(matrixUtil.checkMatrix(input, size));
    }

    @Test
    void checkConstantWhenValidStringThanThisNumber() {
        String s = "5.7";
        assertEquals(5.7, matrixUtil.checkConstant(s));
    }

    @Test
    void checkConstantWhenLetterThanNull() {
        String s = "a";
        assertNull(matrixUtil.checkConstant(s));
    }
}