package lv.dita.project.data;

import lv.dita.project.data.enums.DailyActivityLevel;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        double weight;
        double height;
        String gender;
        int age;
        DailyActivityLevel activityLevel;

        Scanner input = new Scanner(System.in);

        System.out.println("Input height in centimeters: ");
        height = input.nextDouble();

        System.out.println("Input weight in kilograms: ");
        weight = input.nextDouble();

        System.out.println("Input age: ");
        age = input.nextInt();

        Scanner input2 = new Scanner(System.in);
        System.out.println("Input gender: ");
        gender = input2.nextLine().toUpperCase();

        System.out.println("Input Activity level: ");
        activityLevel = DailyActivityLevel.valueOf(input2.nextLine().toUpperCase());
        System.out.println();

        calculateBMI(weight, height);
        calculateIBW(gender, height);
        calculateEER(gender, age, weight, height, activityLevel);
    }

    private static void calculateBMI(double weight, double height) {

        height = height / 100;
        double bmi = weight / (height * height);

        DecimalFormat df = new DecimalFormat("##.##");

        System.out.print("Your Body Mass Index is " + df.format(bmi));

        if (bmi < 18.5) {
            System.out.print(" - you're in the underweight range.\n");
        } else if (bmi > 18.5 && bmi <= 24.9) {
            System.out.print(" -  you're in the healthy weight range.\n");
        } else if (bmi > 25 && bmi <= 29.9) {
            System.out.print(" - you're in the overweight range.\n");
        } else if (bmi > 30 && bmi <= 39.9) {
            System.out.print(" - you're in the obese range.\n");
        } else if (bmi > 39.9) {
            System.out.print(" - your health is in danger. Please consult your doctor ASAP!\n");
        }
    }

    private static void calculateIBW(@NotNull String gender, double height) {

        //IBW = ideal body weight
        //dropdown option in the interface for selecting gender therefore no additional validation needed

        height = height / 100;
        double ibw = 0;
        DecimalFormat df = new DecimalFormat("##.##");

        switch (gender) {
            case "M":
                ibw = 22 * Math.pow(height, 2);
                break;
            case "F":
                ibw = 22 * Math.pow((height - 0.1), 2);
                break;
        }
        System.out.println("Your ideal weight is " + df.format(ibw) + " kgs.");
    }

    private static void calculateEER(@NotNull String gender, int age, double weight, double height, DailyActivityLevel activityLevel) {

        //EER = Estimated Energy Requirements (calories/day) required to maintain the current weight.

        height = height / 100;
        int eer = 0;

        switch (gender) {
            case "M":
                eer = (int) ((662 - (9.53 * age)) + (activityLevel.getPa() * ((15.91 * weight) + (539.6 * height))));
                break;
            case "F":
                eer = (int) ((354 - (6.91 * age)) + (activityLevel.getPa() * ((9.36 * weight) + (726 * height))));
                break;
        }
        int caloriesToLoseWeight = eer - ((eer/100) * 30);
        int caloriesToGainWeight = eer + ((eer/100) * 30);

        System.out.println("To maintain your current weight you need " + eer + " calories a day.");
        System.out.println("To lose weight you need " + caloriesToLoseWeight + " calories a day.");
        System.out.println("To gain weight you need " + caloriesToGainWeight + " calories a day.");
    }

    
}
