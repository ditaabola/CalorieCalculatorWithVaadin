package lv.dita.project.data;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class Calculator2 {
    private double weight;
    private double height;


    public static String calculateBMI(double weight, double height) {

        if (weight <= 0 || height <= 0) {
            return "Invalid data!";

        } else {

            DecimalFormat df = new DecimalFormat("##.##");
            double bmi = weight / ((height / 100) * (height / 100));
            return df.format(bmi);
        }
    }

    @Contract(pure = true)
    public static @NotNull String commentAboutUsersBmi(double weight, double height) {

        double bmi = weight / ((height / 100) * (height / 100));
        if (bmi < 18.5) {
            return "You're in the underweight range.";
        } else if (bmi > 18.5 && bmi <= 24.9) {
            return "You're in the healthy weight range.";
        } else if (bmi > 25 && bmi <= 29.9) {
            return "You're in the overweight range.";
        } else if (bmi > 30 && bmi <= 39.9) {
            return "You're in the obese range.";
        } else if (bmi > 39.9) {
            return "Your health is in danger. Please consult your doctor ASAP!";
        }
        return "Something went wrong.";
    }

}

