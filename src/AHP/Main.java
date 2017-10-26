package AHP;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Ahp ahp = new Ahp(4, 3);

        ahp.criteria = new double[][]{{1,3,7,8}, {1/3d,1,3,6}, {1/7d,1/3d,1,3}, {1/8d,1/6d,1/3d,1}};

        ArrayList<double[][]> alternatives = new ArrayList<>();

        alternatives.add(new double[][]{{1,8,6}, {1/8d,1,1/3d}, {1/6d,3,1}});
        alternatives.add(new double[][]{{1,5,1/3d}, {1/8d,1,1/3d}, {1/6d,3,1}});
        alternatives.add(new double[][]{{1,1/7d,1/2d}, {7,1,4}, {2,1/4d,1}});
        alternatives.add(new double[][]{{1,1/8d,1/4d}, {8,1,3}, {4,1/3d,1}});

        ahp.alternatives = alternatives;

        double[] ranking = ahp.eigenMethod();

        for (int i = 0; i < ranking.length; ++i) {
            System.out.println(ranking[i]);
        }
    }
}
