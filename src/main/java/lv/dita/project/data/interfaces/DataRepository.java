package lv.dita.project.data.interfaces;

import lv.dita.project.alternatives.ActivityPerformed;
import lv.dita.project.data.tabs.activitiesDatabase.Activity;
import lv.dita.project.data.tabs.activitiesDatabase.ActivityListByCalories;
import lv.dita.project.data.tabs.calculateConsumedCalories.FoodEaten;
import lv.dita.project.data.tabs.foodDatabase.Food;

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
    List<ActivityListByCalories> getListWithCalories (int time, double weight, double calories);

    void addFoodEaten(FoodEaten foodEaten);
    void deleteItemFromFoodEatenTable(String name);
    void emptyActivitiesTable();
    void emptyEatenTable();
    void addActivityPerformed (ActivityPerformed activityPerformed);

    }
