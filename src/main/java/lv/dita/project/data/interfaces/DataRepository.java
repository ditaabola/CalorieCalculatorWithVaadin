package lv.dita.project.data.interfaces;

import lv.dita.project.data.*;

import java.util.List;

public interface DataRepository {

    <T extends EntityBase> List<T> getList (Class<T> item);
    <T extends EntityBase> T getById(Class<T> item, int id);

    int addFood(Food food);
    List<Food> getFoodItemsByMaxCaloriesPer100G(int calories);
    List<Food> getFoodItemsByType(String type);
    double getCaloriesByName(String name);
    double getMetValueByName1(String name);

    int addActivity(Activity activity);
    List<Activity> getActivityByLevel (String level);
    List<Activity> getActivityByType (String type);
    List<Activity> getMetValueByActivityName (String name);

    void addFoodEaten(FoodEaten foodEaten);
    String resetFoodEatenTable();


    void addActivityPerformed (ActivityPerformed activityPerformed);

    }
