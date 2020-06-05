package lv.dita.project.layouts;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import lv.dita.project.data.*;


import java.util.ArrayList;
import java.util.List;

public class FoodLayout extends Div {
    private Button addToSelect;
    private Select<String> foodTypes;
    private Select<String> foodItemsByType;
    private Button calculateCaloriesEaten;
    private NumberField quantityEaten;
    private Label lblQuantity;
    private Grid<Food> grid;
    private MySqlDataRepository repo;
    private FoodLogger fl;
    private Label lblCalorieCalculation;
    private Button showGrid;

    public FoodLayout() {
        repo = new MySqlDataRepository();
        creatingTypeSelectOption();
        creatingFoodItemSelectFromTypeOption();
        createQuantityField();
        createAddFoodToGridButton();
        createCaloriesCalculationButton();
        createGridWithData();
    }

    public void creatingTypeSelectOption() {
        foodTypes = new Select<String>();
        foodTypes.setLabel("Select type of food");
        List<FoodCategory> result = repo.getList(FoodCategory.class);
        foodTypes.setItems(result.stream().map(s -> s.getType()));
        foodTypes.addValueChangeListener(e -> {
            List<Food> items = repo.getFoodItemsByType(e.getValue());
            foodItemsByType.setItems(items.stream().map(s -> s.getName()));
        });
        add(foodTypes);
    }

    public void creatingFoodItemSelectFromTypeOption() {
        foodItemsByType = new Select<>();
        foodItemsByType.setLabel("Select food item");
        List<Food> result = repo.getList(Food.class);
        foodItemsByType.setItems(result.stream().map(s -> s.getName()));
        foodItemsByType.setRequiredIndicatorVisible(true);
        add(foodItemsByType);
    }

    public void createQuantityField() {
        quantityEaten = new NumberField();
        quantityEaten.setLabel("Enter the quantity eaten in grams");
        quantityEaten.setRequiredIndicatorVisible(true);
        quantityEaten.setWidth("250px");
        quantityEaten.setMin(2d);
        add(quantityEaten);
    }

    public void createAddFoodToGridButton() {
        addToSelect = new Button("Add selected food");
//        List<String> columnValuesTypes = new ArrayList<>();
//        List<String> columnValuesItems = new ArrayList<>();
//        List<Double> columnValuesQuantity = new ArrayList<>();
//        addToSelect.addClickListener(e -> {
//            columnValuesTypes.add(foodTypes.getValue());
//            columnValuesItems.add(foodItemsByType.getValue());
//            columnValuesQuantity.add(quantityEaten.getValue());
//
//        });
        add(addToSelect);

    }

    public void createCaloriesCalculationButton() {
        lblCalorieCalculation = new Label();
        calculateCaloriesEaten = new Button("Calculate calories eaten", new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {

                String foodChosen = foodItemsByType.getValue();
                Double quantity = quantityEaten.getValue();
                Double calories = 100d;
                String res = fl.calculateCalories(foodChosen, quantity, calories);
                lblCalorieCalculation.setText(res);
            }
        });
                add(calculateCaloriesEaten);
                add(lblCalorieCalculation);
            }

            public void createGridWithData() {
                grid = new Grid<>();
                Grid.Column<Food> colName = grid.addColumn(Food::getName).setHeader("Name").setSortable(true);
                Grid.Column<Food> colType = grid.addColumn(Food::getType).setHeader("Type").setSortable(true);
                Grid.Column<Food> colCalories = grid.addColumn(Food::getCaloriesPer100G).setHeader("Calories per 100 g").setSortable(true);
                List result = repo.getList(Food.class);
                grid.setItems(result);
                grid.getColumns().forEach(column -> column.setAutoWidth(true));
                grid.setHeight("1000px");
                grid.setVisible(false);
                showGrid = new Button("Show the food table", e->{
                    grid.setVisible(true);
                });

//        grid.setRowsDraggable(true);
//        grid.setDragDataGenerator("Name", Food::getName);
                add(grid);
                add(showGrid);
            }

            public void createGridWithSelectedValues() {
                grid = new Grid<>();
                grid.setColumns("Type", "Name", "Quantity");
                add(grid);

            }

        }
