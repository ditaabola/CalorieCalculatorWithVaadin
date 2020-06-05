package lv.dita.project.layouts;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dnd.GridDropMode;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
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
    private Label lblQuantity;
    private Grid<Food> grid;

    public FoodLayout() {
        creatingCategorySelectOption();
        creatingFoodItemSelectFromCategory();
        createQuantityField();
        createAddFoodToGridButton();
        createGridWithData();
        createCaloriesCalculationButton();
        createEmptyGrid();
    }

    public void creatingCategorySelectOption(){
        foodCategories = new Select<String>();
        foodCategories.setLabel("Select food category");
        MySqlDataRepository repo = new MySqlDataRepository();
        List result = repo.getList(Food.class);
        foodCategories.setItems();
//      foodCategories.setItemLabelGenerator();
        add(foodCategories);
    }

    public void creatingFoodItemSelectFromCategory() {
        foodItemsByCategory = new Select<>();
        foodItemsByCategory.setLabel("Select food item");
        foodItemsByCategory.setItems("Apple", "Pear", "Grape", "Watermelon", "Tomato");
        foodItemsByCategory.setRequiredIndicatorVisible(true);
        add(foodItemsByCategory);
    }

    public void createQuantityField(){
        quantityEaten = new NumberField("Enter the quantity eaten in grams");
        quantityEaten.setWidth("50px");
        quantityEaten.setMin(2d);
        add(quantityEaten);
        quantityEaten.setRequiredIndicatorVisible(true);
    }

    public void createAddFoodToGridButton(){
        addToSelect = new Button("Add selected food");
        add(addToSelect);
    }

    public void createCaloriesCalculationButton() {
        calculateCaloriesEaten = new Button("Calculate calories eaten");
        add(calculateCaloriesEaten);
    }

    public void createGridWithData() {
        grid = new Grid<>();
        Grid.Column<Food> colName = grid.addColumn(Food::getName).setHeader("Name").setSortable(true);
        Grid.Column<Food> colType = grid.addColumn(Food::getType).setHeader("Type").setSortable(true);
        Grid.Column<Food> colCalories = grid.addColumn(Food::getCaloriesPer100G).setHeader("Calories per 100 g").setSortable(true);
        MySqlDataRepository repo = new MySqlDataRepository();
        List result = repo.getList(Food.class);
        grid.setItems(result);
        grid.getColumns().forEach(column -> column.setAutoWidth(true));
        grid.setHeight("1000px");
        add(grid);
        grid.setRowsDraggable(true);
        grid.setDragDataGenerator("Name", Food::getName);
    }

    public void createEmptyGrid() {
        grid = new Grid<>();
        grid.setDropMode(GridDropMode.BETWEEN);
        grid.addDropListener(event -> {  /* ... */ } );
    }

}
