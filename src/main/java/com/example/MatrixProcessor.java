package com.example;

public class MatrixProcessor {

    public Matrix addMatrices(Matrix A, Matrix B) {
        if (A.getRows() != B.getRows() || A.getColumns() != B.getColumns()) {
            return null;
        }

        int n = A.getRows();
        int m = A.getColumns();
        double[][] matrix = new double[n][];

        for (int i = 0; i < n; i++) {
            matrix[i] = new double[m];
            for (int j = 0; j < m; j++) {
                matrix[i][j] = A.getMatrix()[i][j] + B.getMatrix()[i][j];
            }
        }
        return new Matrix(matrix, n, m);
    }

    public Matrix multiplyByConstant(Matrix A, double constant) {
        int n = A.getRows();
        int m = A.getColumns();
        double[][] matrix = new double[n][];

        for (int i = 0; i < n; i++) {
            matrix[i] = new double[m];
            for (int j = 0; j < m; j++) {
                if (A.getMatrix()[i][j] == 0) {
                    matrix[i][j] = A.getMatrix()[i][j];
                } else {
                    matrix[i][j] = constant * A.getMatrix()[i][j];
                }
            }
        }
        return new Matrix(matrix, n, m);
    }

    public Matrix multiplyMatrices(Matrix A, Matrix B) {
        if (A.getColumns() != B.getRows()) {
            return null;
        }

        double[][] matrix = new double[A.getRows()][B.getColumns()];
        for (int i = 0; i < A.getRows(); i++) {
            double[] row = A.getMatrix()[i];

            for (int j = 0; j < B.getColumns(); j++) {
                double sum = 0;

                double[] column = new double[row.length];
                for (int k = 0; k < B.getRows(); k++) {
                    column[k] = B.getMatrix()[k][j];
                }

                for (int l = 0; l < row.length; l++) {
                    sum += row[l] * column[l];
                }
                matrix[i][j] = sum;
            }
        }
        return new Matrix(matrix, A.getRows(), B.getColumns());
    }

    public Matrix mainDiagonalTransposition(Matrix matrix) {
        double[][] transposed = new double[matrix.getColumns()][matrix.getRows()];
        double[][] array = matrix.getMatrix();

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                transposed[j][i] = array[i][j];
            }
        }
        return new Matrix(transposed, matrix.getColumns(), matrix.getRows());
    }

    public Matrix sideDiagonalTransposition(Matrix matrix) {
        double[][] transposed = new double[matrix.getColumns()][matrix.getRows()];
        double[][] array = matrix.getMatrix();

        for (int j = 0; j < matrix.getColumns(); j++) {

            for (int k = 0, l = matrix.getRows() - 1; k < matrix.getRows(); k++, l--) {

                transposed[j][l] = array[k][j];
            }
        }
        Matrix indirect = new Matrix(transposed, matrix.getColumns(), matrix.getRows());

        return horizontalLineTransposition(indirect);
    }

    public Matrix verticalLineTransposition(Matrix matrix) {
        double[][] transposed = new double[matrix.getRows()][matrix.getColumns()];
        double[][] array = matrix.getMatrix();

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0, k = matrix.getColumns() - 1; j < matrix.getColumns(); j++, k--) {
                transposed[i][j] = array[i][k];
            }
        }
        return new Matrix(transposed, matrix.getRows(), matrix.getColumns());
    }

    public Matrix horizontalLineTransposition(Matrix matrix) {
        double[][] transposed = new double[matrix.getRows()][matrix.getColumns()];
        double[][] array = matrix.getMatrix();

        for (int i = 0, j = matrix.getRows() - 1; i < matrix.getRows(); i++, j--) {
            transposed[i] = array[j];
        }
        return new Matrix(transposed, matrix.getRows(), matrix.getColumns());
    }

    public double calculateDet(Matrix matrix) {
        if (matrix.getRows() == 2) {
            double a11 = matrix.getMatrix()[0][0];
            double a22 = matrix.getMatrix()[1][1];
            double a12 = matrix.getMatrix()[0][1];
            double a21 = matrix.getMatrix()[1][0];

            return a11 * a22 - a12 * a21;
        }

        double result = 0;
        for (int i = 0; i < matrix.getColumns(); i++) {
            double[][] minor = getSubmatrix(matrix, 0, i);
            result += Math.pow(-1, 1 + i + 1) * matrix.getMatrix()[0][i] *
                    calculateDet(new Matrix(minor, matrix.getRows() - 1, matrix.getColumns() - 1));
        }
        return result;
    }

    public Matrix getMatrixOfCofactors(Matrix matrix) {
        double[][] cofactors = new double[matrix.getRows()][matrix.getColumns()];

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                double[][] subMatrix = getSubmatrix(matrix, i, j);
                cofactors[i][j] = Math.pow(-1, 1 + i + 1 + j) *
                        calculateDet(new Matrix(subMatrix, matrix.getRows() - 1, matrix.getColumns() - 1));
            }
        }
        return new Matrix(cofactors, matrix.getRows(), matrix.getColumns());
    }

    private double[][] getSubmatrix(Matrix matrix, int x, int y) {
        double[][] subMatrix = new double[matrix.getRows() - 1][matrix.getColumns() - 1];
        int xIndex = 0;
        for (int i = 0; i < matrix.getRows(); i++) {
            int yIndex = 0;
            for (int j = 0; j < matrix.getColumns(); j++) {
                if (i != x && j != y) {
                    subMatrix[xIndex][yIndex] = matrix.getMatrix()[i][j];
                    yIndex++;
                }
            }
            if (i != x) {
                xIndex++;
            }
        }
        return subMatrix;
    }
}
