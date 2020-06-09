package lv.dita.project.data;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import lv.dita.project.data.interfaces.DataRepository;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.text.DecimalFormat;
import java.util.List;

public class FoodLogger extends VerticalLayout {

    private DataRepository repo = new MySqlDataRepository();
    private Select<String> foodTypes = new Select<String>();
    private Select<String> foodItemsByType = new Select<String>();
    private NumberField quantityEaten = new NumberField();
    private NumberField calories = new NumberField();
    private Button addToSelect = new Button();
    private Button calculateCaloriesEaten = new Button();
    private Button cancel = new Button();
    private Label lblCalorieCalculation = new Label();
    private Grid<FoodEaten> gridEaten;

    public FoodLogger() {
        addClassName("food-logger");

        Div addOptions = new Div();
        addOptions.add(createSelectOptionLayout());
        addOptions.setWidthFull();

        Div addButtons = new Div();
        addButtons.add(createButtonsLayout());
        addButtons.setWidthFull();

        Div addTable = new Div();
        addTable.add(createTableData());
        addTable.setWidthFull();

        Div addCalculation = new Div();
        addCalculation.add(createCalc());
        addCalculation.setWidthFull();

        add(addOptions);
        add(addButtons);
        add(addCalculation);
        add(addTable);

        createCaloriesCalculationButton();
        add(calculateCaloriesEaten);
        add(lblCalorieCalculation);
        add(cancel);
    }

    @Contract(" -> new")
    private @NotNull Component createSelectOptionLayout() {
        creatingTypeSelectOption();
        creatingFoodItemSelectFromTypeOption();
        createQuantityField();
        calories.setVisible(false);
        return new HorizontalLayout(foodTypes, foodItemsByType, quantityEaten, calories);
    }

    @Contract(" -> new")
    private @NotNull Component createTableData() {
        createTable();
        repo.resetFoodEatenTable();
        return new HorizontalLayout(gridEaten);
    }

    @Contract(" -> new")
    private @NotNull Component createCalc() {
        createCaloriesCalculationButton();
        return new HorizontalLayout( calculateCaloriesEaten, lblCalorieCalculation, cancel);
    }

    private void creatingTypeSelectOption() {
        foodTypes.setLabel("Select type of food");
        List<FoodCategory> result = repo.getList(FoodCategory.class);
        foodTypes.setItems(result.stream().map(s -> s.getType()));
        foodTypes.setRequiredIndicatorVisible(true);
        foodTypes.addValueChangeListener(e -> {
            List<Food> items = repo.getFoodItemsByType(e.getValue());
            foodItemsByType.setItems(items.stream().map(s -> s.getName()));
        });
    }

    private void creatingFoodItemSelectFromTypeOption() {
        foodItemsByType.setLabel("Select food item");
        List<Food> res = repo.getList(Food.class);
        foodItemsByType.setItems(res.stream().map(s -> s.getName()));
        foodItemsByType.setRequiredIndicatorVisible(true);
        foodItemsByType.addValueChangeListener(e->{
            calories.setValue(repo.getCaloriesByName(foodItemsByType.getValue()));
        });
    }

    private void createQuantityField() {
        quantityEaten.setLabel("Enter the quantity eaten in grams");
        quantityEaten.setRequiredIndicatorVisible(true);
        quantityEaten.setWidth("200px");
        quantityEaten.setMin(2d);
    }


    public String calculateCalories() {
        double caloriesEaten = 0;
        double quantity = 0;
        double caloriesPer100G = 0;
        String food = "";
        DecimalFormat df = new DecimalFormat("##.##");
        List<FoodEaten> eatenFoodList = repo.getList(FoodEaten.class);
        for (FoodEaten eatenFood : eatenFoodList) {
            food = eatenFood.getName();
            quantity = eatenFood.getQuantity();
            caloriesPer100G = eatenFood.getCalories();
            double caloriesPerFoodEaten = (caloriesPer100G / 100) * quantity;
            caloriesEaten += caloriesPerFoodEaten;

        }
        return df.format(caloriesEaten);
    }

    public void createCaloriesCalculationButton() {
        calculateCaloriesEaten.setText("Calculate calories eaten");
        calculateCaloriesEaten.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        calculateCaloriesEaten.addClickListener(e -> {
            String res = calculateCalories();
            lblCalorieCalculation.setText("The total of the calories you have eaten during the day is: " + res);
            lblCalorieCalculation.setVisible(true);
        });
    }

    @Contract(" -> new")
    private @NotNull Component createButtonsLayout() {
        createAddFoodToGridButton();
        createResetChoiceButton();

        return new HorizontalLayout(addToSelect, cancel);
    }


    public void createAddFoodToGridButton() {
        addToSelect.setText("Add this food item");
        addToSelect.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addToSelect.addClickShortcut(Key.ENTER);
        addToSelect.addClickListener(e->{
            if (quantityEaten.isEmpty()){
                Notification.show("Please enter the quantity").setDuration(1000);
            } else {
                repo.addFoodEaten(new FoodEaten(0,
                        foodItemsByType.getValue(),
                        quantityEaten.getValue(),
                        calories.getValue()));
                clearFields();
                loadData();
                Notification.show("The food item added").setDuration(1000);
            }});
                add(addToSelect);
    }

    private void createResetChoiceButton(){
        cancel.setText("Reset the choice");
        cancel.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        cancel.addClickListener(e->{
//            foodTypes.clear();
//            foodItemsByType.clear();
//            quantityEaten.clear();
            repo.resetFoodEatenTable();
            clearFields();
            loadData();
            lblCalorieCalculation.setVisible(false);

        });

        add(cancel);
    }


    private void clearFields(){
        foodTypes.clear();
        foodItemsByType.clear();
        quantityEaten.clear();
    }

    private void createTable(){
        repo.emptyActivitiesTable();
        gridEaten = new Grid<FoodEaten>();
        Grid.Column<FoodEaten> colName = gridEaten.addColumn(FoodEaten::getName).setHeader("Food item");
        Grid.Column<FoodEaten> colQuantity = gridEaten.addColumn(FoodEaten::getQuantity).setHeader("Quantity eaten");
        loadData();
        gridEaten.setWidth("400px");
        gridEaten.setHeightByRows(true);
        gridEaten.setVisible(true);
        gridEaten.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);

    }

    private void loadData(){
        List result = repo.getList(FoodEaten.class);
        gridEaten.setItems(result);
        add(gridEaten);
    }


}


