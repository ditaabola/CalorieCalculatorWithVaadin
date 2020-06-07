package lv.dita.project.data.interfaces;

import lv.dita.project.data.Activity;
import lv.dita.project.data.Food;
import lv.dita.project.data.FoodCategory;
import lv.dita.project.data.FoodEaten;

import java.util.List;

public interface DataRepository {

    <T extends EntityBase> List<T> getList (Class<T> item);
    <T extends EntityBase> T getById(Class<T> item, int id);

    int addFood(Food food);
    List<Food> getFoodItemsByMaxCaloriesPer100G(int calories);
    List<Food> getFoodItemsByType(String type);
    List<Food> getCaloriesByName(String name);

    int addActivity(Activity activity);
    List<Activity> getActivityByLevel (String level);
    List<Activity> getActivityByType (String type);
    List<Activity> getMetValueByActivityName (String name);

    void addFoodEaten(FoodEaten foodEaten);

    }
