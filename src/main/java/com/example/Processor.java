package com.example;

import java.util.Scanner;

public class Processor {

    private Scanner scanner;
    private MatrixUtil matrixUtil;
    private MatrixProcessor matrixProcessor;

    public Processor(Scanner scanner) {
        this.scanner = scanner;
        matrixUtil = new MatrixUtil();
        matrixProcessor = new MatrixProcessor();
    }

    public void run() {
        boolean work = true;
        do {
            menu();
            String s = getUserOption();
            switch (s) {
                case "1":
                    addMatrices();
                    break;
                case "2":
                    multiplyByConstant();
                    break;
                case "3":
                    multiplyMatrices();
                    break;
                case "4":
                    transpositionSubmenu();
                    String option = getUserOption();
                    transpositionOptions(option);
                    break;
                case "5":
                    calculateDeterminant();
                    break;
                case "6":
                    findInverseMatrix();
                    break;
                case "0":
                    work = false;
                    break;
            }
        } while (work);
    }

    private void menu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix to a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
    }

    private void transpositionSubmenu() {
        System.out.println("\n");
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
    }

    private void transpositionOptions(String option) {
        switch (option) {
            case "1":
                Matrix A = getMatrix("");
                Matrix B = matrixProcessor.mainDiagonalTransposition(A);
                System.out.println(matrixUtil.displayMatrix(B));
                break;
            case "2":
                Matrix C = getMatrix("");
                Matrix D = matrixProcessor.sideDiagonalTransposition(C);
                System.out.println(matrixUtil.displayMatrix(D));
                break;
            case "3":
                Matrix E = getMatrix("");
                Matrix F = matrixProcessor.verticalLineTransposition(E);
                System.out.println(matrixUtil.displayMatrix(F));
                break;
            case "4":
                Matrix G = getMatrix("");
                Matrix H = matrixProcessor.horizontalLineTransposition(G);
                System.out.println(matrixUtil.displayMatrix(H));
                break;
        }
    }

    private void addMatrices() {
        Matrix A = getMatrix("first");
        Matrix B = getMatrix("second");
        Matrix C = matrixProcessor.addMatrices(A, B);
        if (C == null) {
            errorMessage();
        } else {
            resultMessage();
            System.out.println(matrixUtil.displayMatrix(C));
        }
    }

    private void multiplyByConstant() {
        Matrix A = getMatrix("");
        double c = readConstant();
        Matrix B = matrixProcessor.multiplyByConstant(A, c);
        resultMessage();
        System.out.println(matrixUtil.displayMatrix(B));
    }

    private void multiplyMatrices() {
        Matrix A = getMatrix("first");
        Matrix B = getMatrix("second");
        Matrix C = matrixProcessor.multiplyMatrices(A, B);
        if (C == null) {
            errorMessage();
        } else {
            resultMessage();
            System.out.println(matrixUtil.displayMatrix(C));
        }
    }

    private void calculateDeterminant() {
        Matrix A = getMatrix();
        double det = matrixProcessor.calculateDet(A);
        resultMessage();
        System.out.println(det + "\n");
    }

    private void findInverseMatrix() {
        Matrix A = getMatrix();
        double det = matrixProcessor.calculateDet(A);
        if (det == 0) {
            inverseMessage();
        } else {
            Matrix B = matrixProcessor.getMatrixOfCofactors(A);
            Matrix C = matrixProcessor.mainDiagonalTransposition(B);
            Matrix D = matrixProcessor.multiplyByConstant(C, 1 / det);
            System.out.println(matrixUtil.displayMatrix(D));
        }
    }

    private String getUserOption() {
        System.out.print("Your choice: ");
        return scanner.nextLine().trim();
    }

    private Matrix getMatrix(String name) {
        int[] size = readSize(name);

        double[][] matrix = readMatrix(name, size);

        return new Matrix(matrix, size);
    }

    private Matrix getMatrix() {
        int[] size = readSquareSize();

        double[][] matrix = readMatrix("", size);

        return new Matrix(matrix, size);
    }

    private int[] readSize(String name) {
        int[] size;
        do {
            System.out.print("Enter size of " + name + " matrix (rows columns): ");
            size = matrixUtil.checkSize(scanner.nextLine().trim());
            if (size == null) {
                System.out.println("Incorrect size of the matrix");
            }
        } while (size == null);
        return size;
    }

    private int[] readSquareSize() {
        int[] size;
        boolean flag = false;
        do {
            System.out.print("Enter size of matrix: ");
            size = matrixUtil.checkSize(scanner.nextLine().trim());
            if (size != null) {
                if (size[0] == size[1]) {
                    flag = true;
                } else {
                    System.out.println("Matrix must be square");
                }
            } else {
                System.out.println("Incorrect size of the matrix");
            }
        } while (!flag);
        return size;
    }

    private double[][] readMatrix(String name, int[] size) {
        double[][] matrix;
        do {
            System.out.println("Enter " + name + " matrix:");
            String[][] input = new String[size[0]][];
            for (int i = 0; i < size[0]; i++) {
                input[i] = scanner.nextLine().split("\\s");
            }
            matrix = matrixUtil.checkMatrix(input, size);
            if (matrix == null) {
                System.out.println("Matrix does not match provided size " + size[0] + " " + size[1]);
            }
        } while (matrix == null);
        return matrix;
    }

    private double readConstant() {
        Double constant;
        do {
            System.out.print("Enter the constant: ");
            String cons = scanner.nextLine().trim();
            constant = matrixUtil.checkConstant(cons);
            if (constant == null) {
                System.out.println("Incorrect constant");
            }
        } while (constant == null);
        return constant;
    }

    private void errorMessage() {
        System.out.println("ERROR");
    }

    private void resultMessage() {
        System.out.println("The result is:");
    }

    private void inverseMessage() {
        System.out.println("Inverse matrix does not exist");
    }
}
