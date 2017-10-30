package AHP;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String mode;
        Scanner input = new Scanner (System.in);

        do {
            System.out.println ("Please indicate the operation you want to perform: \n");
            System.out.println ("Press 0 to finish the program.");
            System.out.println ("Press 1 to to solve the practice exercises.");
            System.out.println ("Press 2 to enter the arrays of the AHP problem manually.");
            mode = input.nextLine();
        } while (!mode.equals("0") && !mode.equals("1") && !mode.equals("2"));

        if (mode.equals("1")) {

            Ahp ahp = new Ahp(4, 3);

            ahp.criteria = new double[][]{{1,3,7,8}, {1/3d,1,3,6}, {1/7d,1/3d,1,3}, {1/8d,1/6d,1/3d,1}};

            ArrayList<double[][]> alternatives = new ArrayList<>();

            alternatives.add(new double[][]{{1,8,6}, {1/8d,1,1/3d}, {1/6d,3,1}});
            alternatives.add(new double[][]{{1,5,1/3d}, {1/5d,1,1/7d}, {3,7,1}});
            alternatives.add(new double[][]{{1,1/7d,1/2d}, {7,1,4}, {2,1/4d,1}});
            alternatives.add(new double[][]{{1,1/8d,1/4d}, {8,1,3}, {4,1/3d,1}});

            ahp.alternatives = alternatives;

            System.out.println("Exercise 1: \n");
            System.out.println("Approximated method: \n");

            double[] ranking = ahp.approximatedMethod();

            for (int i = 0; i < ranking.length; ++i) {
                System.out.println("Alternative " + (i + 1) + ": " + ranking[i]);
            }

            System.out.println();

            double max = ranking[0];
            int pos = 0;

            for (int i = 1; i < ranking.length; ++i) {
                if (ranking[i] > max) {
                    max = ranking[i];
                    pos = 1;
                }
            }

            System.out.println("The best alternative is: Alternative " + (pos + 1) + "\n");

            System.out.println("Geometric mean method: \n");

            double[] ranking2 = ahp.geometricMeanMethod();

            for (int i = 0; i < ranking2.length; ++i) {
                System.out.println("Alternative " + (i + 1) + ": " + ranking2[i]);
            }

            System.out.println();
            max = ranking2[0];
            pos = 0;

            for (int i = 1; i < ranking2.length; ++i) {
                if (ranking2[i] > max) {
                    max = ranking2[i];
                    pos = 1;
                }
            }

            System.out.println("The best alternative is: Alternative " + (pos + 1) + "\n");

            System.out.println("Eigenvalue method: \n");
            double[] ranking3 = ahp.eigenvalueMethod();

            for (int i = 0; i < ranking3.length; ++i) {
                System.out.println("Alternative " + (i + 1) + ": " + ranking3[i]);
            }

            System.out.println();
            max = ranking3[0];
            pos = 0;

            for (int i = 1; i < ranking3.length; ++i) {
                if (ranking3[i] > max) {
                    max = ranking3[i];
                    pos = 1;
                }
            }

            System.out.println("The best alternative is: Alternative " + (pos + 1) + "\n");

            ArrayList<Double> consistencyRatios = ahp.getConsistencyRatios();
            ArrayList<Double> consistencyIndexes = ahp.getConsistencyIndexes();

            System.out.println("Consistency Indexes: \n");
            System.out.println("Criteria: " + consistencyIndexes.get(0));

            for (int i = 1; i < consistencyIndexes.size(); ++i) {
                System.out.println("Alternative " + i + ": " + consistencyIndexes.get(i));
            }

            System.out.println();
            System.out.println("Consistency Ratios: \n");
            System.out.println("Criteria: " + consistencyRatios.get(0));

            for (int i = 1; i < consistencyRatios.size(); ++i) {
                System.out.println("Alternative " + i + ": " + consistencyRatios.get(i));
            }

            Ahp ahp2 = new Ahp(4, 3);

            ahp2.criteria = new double[][]{{1,2,6,2}, {1/2d,1,7,1}, {1/6d,1/7d,1,5}, {1/2d,1,1/5d,1}};

            ArrayList<double[][]> alternatives2 = new ArrayList<>();

            alternatives2.add(new double[][]{{1,1/6d,6}, {6,1,4}, {1/6d,1/4d,1}});
            alternatives2.add(new double[][]{{1,5,2}, {1/5d,1,1/7d}, {1/2d,7,1}});
            alternatives2.add(new double[][]{{1,1/4d,1/2d}, {4,1,4}, {2,1/4d,1}});
            alternatives2.add(new double[][]{{1,1/5d,1/2d}, {5,1,1}, {2,1,1}});

            ahp2.alternatives = alternatives2;

            System.out.println();
            System.out.println("Exercise 2: \n");
            System.out.println("Approximated method: \n");

            double[] ranking4 = ahp2.approximatedMethod();

            for (int i = 0; i < ranking4.length; ++i) {
                System.out.println("Alternative " + (i + 1) + ": " + ranking4[i]);
            }

            System.out.println();
            max = ranking4[0];
            pos = 0;

            for (int i = 1; i < ranking4.length; ++i) {
                if (ranking4[i] > max) {
                    max = ranking4[i];
                    pos = 1;
                }
            }

            System.out.println("The best alternative is: Alternative " + (pos + 1) + "\n");

            System.out.println("Geometric mean method: \n");

            double[] ranking5 = ahp2.geometricMeanMethod();

            for (int i = 0; i < ranking5.length; ++i) {
                System.out.println("Alternative " + (i + 1) + ": " + ranking5[i]);
            }

            System.out.println();
            max = ranking5[0];
            pos = 0;

            for (int i = 1; i < ranking5.length; ++i) {
                if (ranking5[i] > max) {
                    max = ranking5[i];
                    pos = 1;
                }
            }

            System.out.println("The best alternative is: Alternative " + (pos + 1) + "\n");
            System.out.println("Eigenvalue method: \n");
            double[] ranking6 = ahp2.eigenvalueMethod();

            for (int i = 0; i < ranking6.length; ++i) {
                System.out.println("Alternative " + (i + 1) + ": " + ranking6[i]);
            }

            System.out.println();
            max = ranking6[0];
            pos = 0;

            for (int i = 1; i < ranking6.length; ++i) {
                if (ranking6[i] > max) {
                    max = ranking6[i];
                    pos = 1;
                }
            }

            System.out.println("The best alternative is: Alternative " + (pos + 1) + "\n");

            ArrayList<Double> consistencyRatios2 = ahp2.getConsistencyRatios();
            ArrayList<Double> consistencyIndexes2 = ahp2.getConsistencyIndexes();

            System.out.println("Consistency Indexes: \n");
            System.out.println("Criteria: " + consistencyIndexes2.get(0));

            for (int i = 1; i < consistencyIndexes2.size(); ++i) {
                System.out.println("Alternative " + i + ": " + consistencyIndexes2.get(i));
            }

            System.out.println();
            System.out.println("Consistency Ratios: \n");
            System.out.println("Criteria: " + consistencyRatios2.get(0));

            for (int i = 1; i < consistencyRatios2.size(); ++i) {
                System.out.println("Alternative " + i + ": " + consistencyRatios2.get(i));
            }

        } else if (mode.equals("2")) {

            int numberOfCriteria, numberOfAlternatives;
            String data;


            System.out.println("Number of criteria: ");
            data = input.nextLine();
            if (!StringUtils.isNumeric(data) || Integer.parseInt(data) < 0) {
                do {
                    System.out.println("Please enter a valid number.");
                    data = input.nextLine();
                } while(!StringUtils.isNumeric(data) || data.contains(".") || data.contains(","));
            }
            numberOfCriteria = Integer.parseInt(data);
            System.out.println();

            System.out.println("Number of alternatives:");
            data = input.nextLine();
            if (!StringUtils.isNumeric(data) || Integer.parseInt(data) < 0) {
                do {
                    System.out.println("Please enter a valid number.");
                    data = input.nextLine();
                } while(!StringUtils.isNumeric(data) || data.contains(".") || data.contains(","));
            }
            numberOfAlternatives = Integer.parseInt(data);
            System.out.println();

            Ahp ahp = new Ahp(numberOfCriteria, numberOfAlternatives);
            double[][] criteriaMatrix = new double[numberOfCriteria][numberOfCriteria];

            for (int i = 0; i < numberOfCriteria; ++i) {
                String values[];
                boolean validValues = true;
                do {
                    System.out.println("Please enter row " + (i + 1) + " of the criteria matrix by separating each value with ';': \n");
                    values = input.nextLine().split(";");

                    if(values.length != numberOfCriteria){
                        validValues = false;
                    } else {
                        for (String value : values) {
                            if (!StringUtils.isNumeric(value)) {
                                validValues = false;
                            }
                        }
                    }
                } while(!validValues);

                for (int j = 0; j < numberOfCriteria; ++j) {
                    criteriaMatrix[i][j] = Double.parseDouble(values[j]);
                }

            }

        }
    }
}
