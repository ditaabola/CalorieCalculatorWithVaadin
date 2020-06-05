package lv.dita.project.layouts;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import lv.dita.project.data.Food;
import lv.dita.project.data.MySqlDataRepository;

import java.util.List;


public class FoodLayout extends Div {
        private Button addToSelect;
        private Select<String> foodCategories;
        private Select<String> foodItemsByCategory;
        private Button calculateCaloriesEaten;
        private NumberField quantityEaten;
//    private Label lblFoodCategories;
//    private Label lblFoodItemsByCategory;
//    private Food food;

        public FoodLayout() {

            foodCategories = new Select<String>();
            foodCategories.setLabel("Select food category");
            foodCategories.setItems("Apple", "Pear", "Grape", "Watermelon", "Tomato");
            add(foodCategories);

            foodItemsByCategory = new Select<>();
            foodItemsByCategory.setLabel("Select food item");
            foodItemsByCategory.setItems("Apple", "Pear", "Grape", "Watermelon", "Tomato");
            foodItemsByCategory.setRequiredIndicatorVisible(true);
            add(foodItemsByCategory);

            quantityEaten = new NumberField("Enter the quantity eaten in grams");
            add(quantityEaten);
            quantityEaten.setRequiredIndicatorVisible(true);

            addToSelect = new Button("Add selected food");
            add(addToSelect);

            calculateCaloriesEaten = new Button("Calculate calories eaten");
            add(calculateCaloriesEaten);

            Grid<Food> grid;

            grid = new Grid<>();
            grid.addColumn(Food::getName).setHeader("Name");
            grid.addColumn(Food::getType).setHeader("Type");
            grid.addColumn(Food::getCaloriesPer100G).setHeader("Calories per 100 g");
            MySqlDataRepository repo = new MySqlDataRepository();
            List result = repo.getList(Food.class);
            grid.setItems(result);
            grid.setWidth("600px");
            grid.setHeight("1000px");
            add(grid);


        }



}
