package lv.dita.project.data;

import lv.dita.project.data.enums.DailyActivityLevel;
import lv.dita.project.data.enums.PersonsGender;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class Calculator {

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

    public static @NotNull String calculateIBW(@NotNull PersonsGender gender, double height) {

        height = height / 100;
        double ibw;
        DecimalFormat df = new DecimalFormat("##.##");

        if (gender.equals("")) {
            return "Choose gender";
        } else if (gender.equals(PersonsGender.FEMALE)) {
            ibw = 22 * Math.pow((height - 0.1), 2);
            return "Your ideal body weight is: " + df.format(ibw) + " kg";
        } else if (gender.equals(PersonsGender.MALE)) {
            ibw = 22 * Math.pow(height, 2);
            return "Your ideal body weight is: " + df.format(ibw) + " kg";
        } else {
            return "Something went wrong!";
        }
    }

    public static @NotNull String calculateEER(@NotNull PersonsGender gender, int age, double weight,
                                               double height, DailyActivityLevel activityLevel) {

        height = height / 100;
        double eer;

        if (gender.equals("") || activityLevel.equals("")) {
            return "Please enter all data";

        } else if (gender.equals(PersonsGender.FEMALE)) {
            eer = (int) ((354 - (6.91 * age)) + (activityLevel.getPa() * ((9.36 * weight) + (726 * height))));
            SessionHandler.setUserEEr(eer);

            double caloriesToLoseWeight = eer - ((eer / 100) * 30);
            double caloriesToGainWeight = eer + ((eer / 100) * 30);

            return "To maintain your current weight you need " + eer + " calories a day. " +
                    " To lose weight you need " + caloriesToLoseWeight + " calories a day. " +
                    " To gain weight you need " + caloriesToGainWeight + " calories a day.";

        } else if (gender.equals(PersonsGender.MALE)) {
            eer = (int) ((662 - (9.53 * age)) + (activityLevel.getPa() * ((15.91 * weight) + (539.6 * height))));

            double caloriesToLoseWeight = eer - ((eer / 100) * 30);
            double caloriesToGainWeight = eer + ((eer / 100) * 30);


            return "To maintain your current weight you need " + eer + " calories a day. " +
                    " To lose weight you need " + caloriesToLoseWeight + " calories a day. " +
                    " To gain weight you need " + caloriesToGainWeight + " calories a day.";

        } else {
            return "Something went wrong!";
        }
    }
}

