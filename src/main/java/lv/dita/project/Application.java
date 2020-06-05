package lv.dita.project;

import lv.dita.project.data.Activity;
import lv.dita.project.data.FoodCategory;
import lv.dita.project.data.interfaces.DataRepository;
import lv.dita.project.data.Food;
import lv.dita.project.data.MySqlDataRepository;
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


}
