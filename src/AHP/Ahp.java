package AHP;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.ArrayList;

public class Ahp {

    public double[][] criteria;
    public ArrayList<double[][]> alternatives;
    private ArrayList<double[]> alternativeWeights;

    public Ahp(int numberOfCriteria, int numberOfAlternatives) {

        criteria = new double[numberOfCriteria][];
        alternatives = new ArrayList<>();

        for (int i = 0; i < numberOfCriteria; ++i) {
            double[][] matrix = new double[numberOfAlternatives][];
            alternatives.add(matrix);
        }

        alternativeWeights = new ArrayList<>();
    }

    /**
     * Calculates the alternative's ranking using the eigenvalue method.
     *
     * @return the array with the rankings.
     */
    public double[] eigenMethod() {

        double[] criteriaWeights = normalizeVector(calculateMatrixWeightsEigenvalue(criteria));

        for (double[][] alternative : alternatives) {
            alternativeWeights.add(normalizeVector(calculateMatrixWeightsEigenvalue(alternative)));
        }

        double[][] alternativesMatrix = new double[alternatives.size()][alternatives.get(0).length];

        for (int i = 0; i < alternatives.size(); ++i) {
            for (int j = 0; j < alternatives.get(0).length; ++j) {
                alternativesMatrix[i][j] = alternativeWeights.get(i)[j];
            }
        }

        double[] ranking = new double[alternatives.get(0).length];

        for (int i = 0; i < alternatives.get(0).length; ++i) {
            double sum = 0;
            for (int j = 0; j < alternatives.size(); ++j) {
                sum += criteriaWeights[j] * alternativesMatrix[j][i];
            }
            ranking[i] = sum;
        }

        return  ranking;
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

        double max = eigenValues[0];
        int pos = 0;

        for (int i = 0; i < eigenValues.length; ++i) {
            if (eigenValues[i] > max) {
                max = eigenValues[i];
                pos = i;
            }
        }
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
}
