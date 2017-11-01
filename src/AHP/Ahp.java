package AHP;

import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.ArrayList;

class Ahp {

    double[][] criteria;
    private ArrayList<Double> consistencyIndexes, consistencyRatios;
    ArrayList<double[][]> alternatives;

    Ahp(int numberOfCriteria, int numberOfAlternatives) {

        criteria = new double[numberOfCriteria][];
        alternatives = new ArrayList<>();
        consistencyIndexes = new ArrayList<>();
        consistencyRatios = new ArrayList<>();

        for (int i = 0; i < numberOfCriteria; ++i) {
            double[][] matrix = new double[numberOfAlternatives][];
            alternatives.add(matrix);
        }
    }

    /**
     *  Calculates the alternative's ranking using the approximated method.
     *
     * @return an array with the alternatives's ranking.
     */
    double[] approximatedMethod() {

        double[] criteriaWeights = normalizeVector(calculateMatrixWeightsApproximated(criteria));
        ArrayList<double[]> alternativeWeights = new ArrayList<>();

        for (double[][] alternative : alternatives) {
            alternativeWeights.add(normalizeVector(calculateMatrixWeightsApproximated(alternative)));
        }

        double[][] alternativesMatrix = new double[alternatives.size()][alternatives.get(0).length];

        for (int i = 0; i < alternatives.size(); ++i) {
            System.arraycopy(alternativeWeights.get(i), 0, alternativesMatrix[i],
                    0, alternatives.get(0).length);
        }

        return aggregation(criteriaWeights, alternativesMatrix);
    }

    /**
     *  Calculates the alternative's ranking using the geometric mean method.
     *
     * @return an array with the alternatives's ranking.
     */
    double[] geometricMeanMethod() {

        double[] criteriaWeights = normalizeVector(calculateMatrixWeightsGeometricMean(criteria));
        ArrayList<double[]> alternativeWeights = new ArrayList<>();

        for (double[][] alternative : alternatives) {
            alternativeWeights.add(normalizeVector(calculateMatrixWeightsGeometricMean(alternative)));
        }

        double[][] alternativesMatrix = new double[alternatives.size()][alternatives.get(0).length];

        for (int i = 0; i < alternatives.size(); ++i) {
            System.arraycopy(alternativeWeights.get(i), 0, alternativesMatrix[i],
                    0, alternatives.get(0).length);
        }

        return aggregation(criteriaWeights, alternativesMatrix);
    }

    /**
     * Calculates the alternative's ranking using the eigenvalue method.
     *
     * @return an array with the alternatives's ranking.
     */
    double[] eigenvalueMethod() {

        double[] criteriaWeights = normalizeVector(calculateMatrixWeightsEigenvalue(criteria));
        ArrayList<double[]> alternativeWeights = new ArrayList<>();

        for (double[][] alternative : alternatives) {
            alternativeWeights.add(normalizeVector(calculateMatrixWeightsEigenvalue(alternative)));
        }

        double[][] alternativesMatrix = new double[alternatives.size()][alternatives.get(0).length];

        for (int i = 0; i < alternatives.size(); ++i) {
            System.arraycopy(alternativeWeights.get(i), 0, alternativesMatrix[i],
                    0, alternatives.get(0).length);
        }

        calculateConsistencyRatios();
        return aggregation(criteriaWeights, alternativesMatrix);
    }

    /**
     * Aggregates the two matrices to obtain the ranking of the alternatives.
     *
     * @param criteriaWeights the criteria priorities.
     * @param alternativesMatrix a matrix that contains the local alternative priorities regarding criteria.
     * @return an array with the alternatives's ranking.
     */
    private double[] aggregation(double[] criteriaWeights, double [][] alternativesMatrix) {

        double[] ranking = new double[alternatives.get(0).length];

        for (int i = 0; i < alternatives.get(0).length; ++i) {
            double sum = 0;
            for (int j = 0; j < alternatives.size(); ++j) {
                sum += criteriaWeights[j] * alternativesMatrix[j][i];
            }
            ranking[i] = sum;
        }

        return ranking;
    }

    /**
     * Calculates the matrix's weights using the approximated method.
     *
     * @return an array with the matrix's weight.
     */
    private double[] calculateMatrixWeightsApproximated(double[][] matrix) {

        double[] resultVector = new double[matrix.length];

        for (int i = 0; i < matrix.length; ++i) {
            double sum = 0;
            for (int j = 0; j < matrix.length; j++) {
                sum += matrix[i][j];
            }
            resultVector[i] = sum;
        }

        return resultVector;
    }

    /**
     * Calculates the matrix's weights using the geometric mean method.
     *
     * @return an array with the matrix's weight.
     */
    private double[] calculateMatrixWeightsGeometricMean(double[][] matrix) {

        double[] resultVector = new double[matrix.length];

        for (int i = 0; i < matrix.length; ++i) {
            double product = 1;
            for (int j = 0; j < matrix.length; j++) {
                product *= matrix[i][j];
            }
            resultVector[i] = Math.pow(product, 1.0/resultVector.length);
        }

        return resultVector;
    }

    /**
     * Calculates the matrix's weights using the eigenvalue method.
     *
     * @return an array with the matrix's weight.
     */
    private double[] calculateMatrixWeightsEigenvalue(double[][] matrix) {

        RealMatrix realMatrix = MatrixUtils.createRealMatrix(matrix);


        EigenDecomposition decomposition = new EigenDecomposition(realMatrix);
        double[] eigenValues = decomposition.getRealEigenvalues();

        int pos = 0;

        for (int i = 1; i < eigenValues.length; ++i) {
            if (eigenValues[i] > eigenValues[pos]) {
                pos = i;
            }
        }

        consistencyIndexes.add((eigenValues[pos] - matrix.length) / (matrix.length - 1));
        return decomposition.getEigenvector(pos).toArray();
    }

    /**
     * Normalizes the vector by dividing each element by the sum of all vector's elements.
     *
     * @param vector the vector to normalize.
     * @return an array with normalized elements.
     */
    private double[] normalizeVector(double[] vector) {

        double[] normalizedVector = new double[vector.length];
        double sum = 0;

        for (double element : vector) {
            sum += element;
        }

        for (int i = 0; i < vector.length; ++i) {
            normalizedVector[i] = vector[i] / sum;
        }

        return normalizedVector;
    }

    /**
     * Calculates the consistency ratios for the AHP problem.
     */
    private void calculateConsistencyRatios() {

        double[] randomIndex = new double[] {0.58, 0.9, 1.12, 1.24, 1.32, 1.41, 1.45, 1.49};

        consistencyRatios.add(consistencyIndexes.get(0) / randomIndex[criteria.length - 3]);

        for (int i = 0; i < criteria.length; ++i) {
            consistencyRatios.add(consistencyIndexes.get(i + 1) / randomIndex[alternatives.get(0).length - 3]);
        }
    }

    /**
     *
     * @return an ArrayList with the problem's consistency indexes.
     */
    ArrayList<Double> getConsistencyIndexes() { return consistencyIndexes;}

    /**
     *
     * @return an ArrayList with the problem's consistency ratios.
     */
    ArrayList<Double> getConsistencyRatios() { return consistencyRatios;}
}
