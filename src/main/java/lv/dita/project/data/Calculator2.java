package lv.dita.project.data;

import java.text.DecimalFormat;

public class Calculator2 {
    private double weight;
    private double height;


    public static String calculateBMI(double weight, double height) {

        if (weight <= 0 || height <= 0 ) {
            return "Invalid data!";

        } else {

            DecimalFormat df = new DecimalFormat("##.##");
            double bmi = weight / ((height / 100) * (height / 100));
            return df.format(bmi);
        }
    }
}

