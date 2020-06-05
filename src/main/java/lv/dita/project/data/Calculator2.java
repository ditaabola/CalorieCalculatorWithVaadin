package lv.dita.project.data;

import lv.dita.project.data.enums.DailyActivityLevel;
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

    public static @NotNull String calculateIBW(@NotNull String gender, double height) {

        //IBW = ideal body weight
        //dropdown option in the interface for selecting gender therefore no additional validation needed
        height = height / 100;
        double ibw;
        DecimalFormat df = new DecimalFormat("##.##");

        if (gender.equals("")) {
            return "Choose gender";
        } else if (gender == "Female") {
            ibw = 22 * Math.pow((height - 0.1), 2);
            return "Your ideal body weight is: " + df.format(ibw) + " kg";
        } else if (gender == "Male") {
            ibw = 22 * Math.pow(height, 2);
            return "Your ideal body weight is: " + df.format(ibw) + " kg";
        } else {
            return "Something went wrong!";
        }

    }



}

