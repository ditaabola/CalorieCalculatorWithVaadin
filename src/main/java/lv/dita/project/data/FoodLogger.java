package lv.dita.project.data;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
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

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.util.List;

public class FoodLogger extends VerticalLayout {

    private Food food;
    private DataRepository repo = new MySqlDataRepository();
    private Select<String> foodTypes = new Select<String>();
    private Select<String> foodItemsByType = new Select<String>();
    private NumberField quantityEaten = new NumberField();
    private NumberField calories = new NumberField();
    private Button addToSelect = new Button();
    private Button calculateCaloriesEaten = new Button();
    private Button cancel = new Button();
    private Label lblCommentBmi = new Label();
    private Label lblCalorieCalculation = new Label();

    public FoodLogger() {
        addClassName("food-logger");
        Div addOptions = new Div();
        addOptions.add(createSelectOptionLayout());
        addOptions.setWidthFull();
        Div addButtons = new Div();
        addButtons.add(createButtonsLayout());
        addButtons.setWidthFull();
        add(addOptions);
        add(addButtons);
        createCaloriesCalculationButton();
        add(calculateCaloriesEaten);
        add(lblCalorieCalculation);
        add(cancel);

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

    @Contract(" -> new")
    private @NotNull Component createSelectOptionLayout() {
        creatingTypeSelectOption();
        creatingFoodItemSelectFromTypeOption();
        createQuantityField();
        calories.setVisible(false);
        return new HorizontalLayout(foodTypes, foodItemsByType, quantityEaten, calories);
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

    public void createAddFoodToGridButton() {
        addToSelect.setText("Add this food item");
        addToSelect.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addToSelect.addClickShortcut(Key.ENTER);
        addToSelect.addClickListener(e->{
            if (quantityEaten.isEmpty()){
                Notification.show("Please enter the quantity").setDuration(1000);
            } else {
                repo.addFoodEaten(new FoodEaten(0, foodItemsByType.getValue(), quantityEaten.getValue(), calories.getValue()));
                Notification.show("The food item added").setDuration(1000);
            }});
                add(addToSelect);
    }

    private void createResetChoiceButton(){
        cancel.setText("Reset the choice");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancel.addClickShortcut(Key.ESCAPE);
        cancel.addClickListener(e->{
            foodTypes.clear();
            foodItemsByType.clear();
            quantityEaten.clear();
            repo.resetFoodEatenTable();

        });

        add(cancel);
    }

    @Contract(" -> new")
    private @NotNull Component createButtonsLayout() {
        createAddFoodToGridButton();
        createResetChoiceButton();

        return new HorizontalLayout(addToSelect, cancel);
    }



}


