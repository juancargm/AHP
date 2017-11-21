package AHP;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String mode;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println();
            System.out.println("Please indicate the operation you want to perform: \n");
            System.out.println("Press 0 to finish the program.");
            System.out.println("Press 1 to to solve the practice exercises.");
            System.out.println("Press 2 to enter the matrices of the AHP problem manually.");
            mode = input.nextLine();

            if (mode.equals("1")) {

                Ahp ahp = new Ahp(4, 3);

                ahp.criteria = new double[][]{{1, 3, 7, 8}, {1 / 3d, 1, 3, 6}, {1 / 7d, 1 / 3d, 1, 3}, {1 / 8d, 1 / 6d, 1 / 3d, 1}};

                ArrayList<double[][]> alternatives = new ArrayList<>();

                alternatives.add(new double[][]{{1, 8, 6}, {1 / 8d, 1, 1 / 3d}, {1 / 6d, 3, 1}});
                alternatives.add(new double[][]{{1, 5, 1 / 3d}, {1 / 5d, 1, 1 / 7d}, {3, 7, 1}});
                alternatives.add(new double[][]{{1, 1 / 7d, 1 / 2d}, {7, 1, 4}, {2, 1 / 4d, 1}});
                alternatives.add(new double[][]{{1, 1 / 8d, 1 / 4d}, {8, 1, 3}, {4, 1 / 3d, 1}});

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

                ranking = ahp.geometricMeanMethod();

                for (int i = 0; i < ranking.length; ++i) {
                    System.out.println("Alternative " + (i + 1) + ": " + ranking[i]);
                }

                System.out.println();
                max = ranking[0];
                pos = 0;

                for (int i = 1; i < ranking.length; ++i) {
                    if (ranking[i] > max) {
                        max = ranking[i];
                        pos = 1;
                    }
                }

                System.out.println("The best alternative is: Alternative " + (pos + 1) + "\n");

                System.out.println("Eigenvalue method: \n");
                ranking = ahp.eigenvalueMethod();

                for (int i = 0; i < ranking.length; ++i) {
                    System.out.println("Alternative " + (i + 1) + ": " + ranking[i]);
                }

                System.out.println();
                max = ranking[0];
                pos = 0;

                for (int i = 1; i < ranking.length; ++i) {
                    if (ranking[i] > max) {
                        max = ranking[i];
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

                ahp = new Ahp(4, 3);

                ahp.criteria = new double[][]{{1, 2, 6, 2}, {1 / 2d, 1, 7, 1}, {1 / 6d, 1 / 7d, 1, 5}, {1 / 2d, 1, 1 / 5d, 1}};

                alternatives = new ArrayList<>();

                alternatives.add(new double[][]{{1, 1 / 6d, 6}, {6, 1, 4}, {1 / 6d, 1 / 4d, 1}});
                alternatives.add(new double[][]{{1, 5, 2}, {1 / 5d, 1, 1 / 7d}, {1 / 2d, 7, 1}});
                alternatives.add(new double[][]{{1, 1 / 4d, 1 / 2d}, {4, 1, 4}, {2, 1 / 4d, 1}});
                alternatives.add(new double[][]{{1, 1 / 5d, 1 / 2d}, {5, 1, 1}, {2, 1, 1}});

                ahp.alternatives = alternatives;

                System.out.println();
                System.out.println("Exercise 2: \n");
                System.out.println("Approximated method: \n");

                ranking = ahp.approximatedMethod();

                for (int i = 0; i < ranking.length; ++i) {
                    System.out.println("Alternative " + (i + 1) + ": " + ranking[i]);
                }

                System.out.println();
                max = ranking[0];
                pos = 0;

                for (int i = 1; i < ranking.length; ++i) {
                    if (ranking[i] > max) {
                        max = ranking[i];
                        pos = 1;
                    }
                }

                System.out.println("The best alternative is: Alternative " + (pos + 1) + "\n");

                System.out.println("Geometric mean method: \n");

                ranking = ahp.geometricMeanMethod();

                for (int i = 0; i < ranking.length; ++i) {
                    System.out.println("Alternative " + (i + 1) + ": " + ranking[i]);
                }

                System.out.println();
                max = ranking[0];
                pos = 0;

                for (int i = 1; i < ranking.length; ++i) {
                    if (ranking[i] > max) {
                        max = ranking[i];
                        pos = 1;
                    }
                }

                System.out.println("The best alternative is: Alternative " + (pos + 1) + "\n");
                System.out.println("Eigenvalue method: \n");
                ranking = ahp.eigenvalueMethod();

                for (int i = 0; i < ranking.length; ++i) {
                    System.out.println("Alternative " + (i + 1) + ": " + ranking[i]);
                }

                System.out.println();
                max = ranking[0];
                pos = 0;

                for (int i = 1; i < ranking.length; ++i) {
                    if (ranking[i] > max) {
                        max = ranking[i];
                        pos = 1;
                    }
                }

                System.out.println("The best alternative is: Alternative " + (pos + 1) + "\n");

                ArrayList<Double> consistencyRatios2 = ahp.getConsistencyRatios();
                ArrayList<Double> consistencyIndexes2 = ahp.getConsistencyIndexes();

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
                    } while (!StringUtils.isNumeric(data) || data.contains(".") || data.contains(","));
                }
                numberOfCriteria = Integer.parseInt(data);
                System.out.println();

                System.out.println("Number of alternatives:");
                data = input.nextLine();
                if (!StringUtils.isNumeric(data) || Integer.parseInt(data) < 0) {
                    do {
                        System.out.println("Please enter a valid number.");
                        data = input.nextLine();
                    } while (!StringUtils.isNumeric(data) || data.contains(".") || data.contains(","));
                }
                numberOfAlternatives = Integer.parseInt(data);
                System.out.println();

                Ahp ahp = new Ahp(numberOfCriteria, numberOfAlternatives);
                double[][] criteriaMatrix = new double[numberOfCriteria][numberOfCriteria];

                for (int i = 0; i < numberOfCriteria; ++i) {
                    String values[];
                    boolean validValues;
                    do {
                        validValues = true;
                        System.out.println("Please enter row " + (i + 1) + " of the criteria matrix by separating each value with ';': \n");
                        values = input.nextLine().split(";");

                        if (values.length != numberOfCriteria) {
                            validValues = false;
                            System.out.println("The number of values should be " + numberOfCriteria + ".");
                        } else {
                            if (values[i].equals("1")) {
                                for (String value : values) {
                                    if (!StringUtils.isNumeric(value)) {
                                        if (value.contains("\\.")) {
                                            String[] parts = value.split("\\.");
                                            validValues = parts.length == 2 && StringUtils.isNumeric(parts[0]) && StringUtils.isNumeric(parts[1]);
                                        }
                                        if (value.contains("/")) {
                                            String[] parts = value.split("/");
                                            validValues = parts.length == 2 && StringUtils.isNumeric(parts[0]) && StringUtils.isNumeric(parts[1]);
                                        }
                                    }
                                }
                            } else {
                                System.out.println("The reciprocal value of the row must be 1.");
                                validValues = false;
                            }
                        }
                    } while (!validValues);

                    for (int j = 0; j < numberOfCriteria; ++j) {
                        if (!values[j].contains("/")) {
                            criteriaMatrix[i][j] = Double.parseDouble(values[j]);
                        } else {
                            String[] parts = values[j].split("/");
                            criteriaMatrix[i][j] = Double.valueOf(parts[0]) / Double.valueOf(parts[1]);
                        }
                    }

                }

                System.out.println();
                ahp.criteria = criteriaMatrix;
                ArrayList<double[][]> alternatives = new ArrayList<>();

                for (int i = 0; i < numberOfCriteria; ++i) {
                    System.out.println("Please enter alternative's comparision matrices.");
                    double[][] alternativeMatrix = new double[numberOfAlternatives][numberOfAlternatives];
                    for (int j = 0; j < numberOfAlternatives; ++j) {
                        String values[];
                        boolean validValues;
                        do {
                            validValues = true;
                            System.out.println("Please enter row " + (j + 1) + " of the alternative " + (i + 1) + "comparision matrix by separating each value with ';': \n");
                            values = input.nextLine().split(";");

                            if (values.length != numberOfAlternatives) {
                                System.out.println("The number of values should be " + numberOfAlternatives + ".");
                                validValues = false;
                            } else {
                                if (values[j].equals("1")) {
                                    for (String value : values) {
                                        if (!StringUtils.isNumeric(value)) {
                                            if (value.contains("\\.")) {
                                                String[] parts = value.split("\\.");
                                                validValues = parts.length == 2 && StringUtils.isNumeric(parts[0]) && StringUtils.isNumeric(parts[1]);
                                            }
                                            if (value.contains("/")) {
                                                String[] parts = value.split("/");
                                                validValues = parts.length == 2 && StringUtils.isNumeric(parts[0]) && StringUtils.isNumeric(parts[1]);
                                            }
                                        }
                                    }
                                } else {
                                    System.out.println("The reciprocal value of the row must be 1.");
                                    validValues = false;
                                }
                            }
                        } while (!validValues);

                        for (int k = 0; k < numberOfAlternatives; ++k) {
                            if (!values[k].contains("/")) {
                                alternativeMatrix[j][k] = Double.parseDouble(values[k]);
                            } else {
                                String[] parts = values[k].split("/");
                                alternativeMatrix[j][k] = Double.valueOf(parts[0]) / Double.valueOf(parts[1]);
                            }
                        }
                    }
                    alternatives.add(alternativeMatrix);
                }

                ahp.alternatives = alternatives;

                do {
                    System.out.println("Please indicate the method to solve the problem: \n");
                    System.out.println("Press 0 for approximated method.");
                    System.out.println("Press 1 for geometric mean method.");
                    System.out.println("Press 2 for eigenvalue method.");
                    System.out.println("Press 3 for all methods.");
                    mode = input.nextLine();
                } while (!mode.equals("0") && !mode.equals("1") && !mode.equals("2") && !mode.equals("3"));

                double[] ranking = new double[numberOfAlternatives];

                switch (mode) {

                    case "0":
                        System.out.println("Approximated method: \n");
                        ranking = ahp.approximatedMethod();
                        break;
                    case "1":
                        System.out.println("Geometric mean method: \n");
                        ranking = ahp.geometricMeanMethod();
                        break;
                    case "2":
                        System.out.println("Eigenvalue method: \n");
                        ranking = ahp.eigenvalueMethod();
                        break;
                    case "3":
                        System.out.println("Approximated method: \n");

                        ranking = ahp.approximatedMethod();

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

                        ranking = ahp.geometricMeanMethod();

                        for (int i = 0; i < ranking.length; ++i) {
                            System.out.println("Alternative " + (i + 1) + ": " + ranking[i]);
                        }

                        System.out.println();
                        max = ranking[0];
                        pos = 0;

                        for (int i = 1; i < ranking.length; ++i) {
                            if (ranking[i] > max) {
                                max = ranking[i];
                                pos = 1;
                            }
                        }

                        System.out.println("The best alternative is: Alternative " + (pos + 1) + "\n");

                        System.out.println("Eigenvalue method: \n");
                        ranking = ahp.eigenvalueMethod();

                        for (int i = 0; i < ranking.length; ++i) {
                            System.out.println("Alternative " + (i + 1) + ": " + ranking[i]);
                        }

                        System.out.println();
                        max = ranking[0];
                        pos = 0;

                        for (int i = 1; i < ranking.length; ++i) {
                            if (ranking[i] > max) {
                                max = ranking[i];
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
                        break;
                }

                if (!mode.equals("3")) {
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
                }
            }
        } while (!mode.equals("0"));
    }
}
