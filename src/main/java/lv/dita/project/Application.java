package lv.dita.project;

import lv.dita.project.data.*;
import lv.dita.project.data.interfaces.DataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.List;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Test
    public void get_foods_by_max_calories() {
        MySqlDataRepository repo = new MySqlDataRepository();
        List<Food> result = repo.getFoodItemsByMaxCaloriesPer100G(350);
        for (Food item : result) {
            System.out.println(item.getName());
        }
    }

    @Test
    public void get_foods_by_type() {
        DataRepository repo = new MySqlDataRepository();
        List<Food> result = repo.getFoodItemsByType("fruits");
        for (Food item : result) {
            System.out.println(item.getName());
        }
    }

    @Test
    public void get_activities_by_type() {
        DataRepository repo = new MySqlDataRepository();
        List<Activity> result = repo.getActivityByType("home");
        for (Activity item : result) {
            System.out.println(item.getName());
        }
    }

    @Test
    public void printFoodCategory(){
        DataRepository repo = new MySqlDataRepository();
        List<FoodCategory> result = repo.getList(FoodCategory.class);
        for(FoodCategory item: result){
            System.out.println(item);
        }
    }

    @Test
    public void createFoodEaten(){
        DataRepository repo = new MySqlDataRepository();
        List<FoodEaten> result = repo.getList(FoodEaten.class);
        for(FoodEaten item: result){
            System.out.println(item);
        }
    }

    @Test
    public void add_food_eaten(){
        DataRepository repo = new MySqlDataRepository();
        FoodEaten foodEaten = new FoodEaten(0, "bread", 50);
        repo.addFoodEaten(foodEaten);
        }
    }


