package lv.dita.project.data;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        double weight;
        double height;
        String gender;

        Scanner input = new Scanner(System.in);

        System.out.println("Input height in meters: ");
        height = input.nextDouble();

        System.out.println("Input weight in kilograms: ");
        weight = input.nextDouble();

        Scanner input2 = new Scanner(System.in);
        System.out.println("Input gender: ");
        gender = input2.nextLine().toUpperCase();
        System.out.println();

        calculateBMI(weight, height);
        calculateIBW(gender, height);
    }

    private static void calculateBMI(double weight, double height) {

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
        } else {
            System.out.print(" - your health is in danger. Please consult your doctor ASAP!\n");
        }
    }

    private static void calculateIBW(String gender, double height) {
        //IBW = ideal body weight
        //dropdown option in the interface for selecting gender therefore no additional validation needed

        double ibw;
        DecimalFormat df = new DecimalFormat("##.##");

        switch (gender) {
            case "M":
                ibw = 22 * Math.pow(height, 2);
                System.out.println("Your ideal weight is " + df.format(ibw) + " kgs.");
                break;
            case "F":
                ibw = 22 * Math.pow((height - 0.1), 2);
                System.out.println("Your ideal weight is " + df.format(ibw) + " kgs.");
                break;
        }
    }
}
