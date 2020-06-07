package lv.dita.project.data;

import lv.dita.project.data.interfaces.DataRepository;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FoodLogger {

    private Food food;
    private DataRepository repo = new MySqlDataRepository();


    public String calculateCalories(String food, Double quantity, Double caloriesPer100G) {
        double caloriesEaten = 0;
        List<String> items = new ArrayList<>();
        for (String item : items) {
            double caloriesPerFoodEaten = (caloriesPer100G / 100) * quantity;
            caloriesEaten += caloriesPerFoodEaten;
        }
        DecimalFormat df = new DecimalFormat("##.##");
        return df.format(caloriesEaten);
    }
}
