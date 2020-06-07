package lv.dita.project.data;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.HorizontalAlign;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import lv.dita.project.data.interfaces.DataRepository;
import java.util.List;

public class FoodLogger extends FormLayout {

    private Food food;
    private DataRepository repo = new MySqlDataRepository();
    private Select<String> foodTypes = new Select<String>();
    private Select<String> foodItemsByType = new Select<String>();
    private NumberField quantityEaten = new NumberField();
    private Button addToSelect = new Button();
    private Button calculateCaloriesEaten = new Button();
    private Button close = new Button();

    public FoodLogger() {
        addClassName("food-logger");
        add(createSelectOptionLayout());
        add(createButtonsLayout());
    }

    private void creatingTypeSelectOption() {
        foodTypes.setLabel("Select type of food");
        List<FoodCategory> result = repo.getList(FoodCategory.class);
        foodTypes.setItems(result.stream().map(s -> s.getType()));
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

    }

    private void createQuantityField() {
        quantityEaten.setLabel("Enter the quantity eaten in grams");
        quantityEaten.setRequiredIndicatorVisible(true);
        quantityEaten.setWidth("250px");
        quantityEaten.setMin(2d);
    }

    private Component createSelectOptionLayout() {
        creatingTypeSelectOption();
        creatingFoodItemSelectFromTypeOption();
        createQuantityField();

        return new HorizontalLayout(foodTypes, foodItemsByType, quantityEaten);
    }

//    public String calculateCalories(String food, Double quantity, Double caloriesPer100G) {
//        double caloriesEaten = 0;
//        List<String> items = new ArrayList<>();
//        for (String item : items) {
//            double caloriesPerFoodEaten = (caloriesPer100G / 100) * quantity;
//            caloriesEaten += caloriesPerFoodEaten;
//        }
//        DecimalFormat df = new DecimalFormat("##.##");
//        return df.format(caloriesEaten);
//    }


    public void createCaloriesCalculationButton() {
        calculateCaloriesEaten.setText("Calculate calories eaten");
        calculateCaloriesEaten.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        calculateCaloriesEaten.addClickListener(e -> {

//                String res = calculateCalories(foodChosen, quantity, calories);
//                lblCalorieCalculation.setText(res);

        });
    }


    public void createAddFoodToGridButton() {

        addToSelect.setText("Add selected food");
        addToSelect.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        addToSelect.addClickShortcut(Key.ENTER);
        addToSelect.addClickListener(e->{
            repo.addFoodEaten(new FoodEaten(0, foodItemsByType.getValue(), quantityEaten.getValue()));
        });
    }

    private Component createButtonsLayout() {
        createAddFoodToGridButton();
        createCaloriesCalculationButton();
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(addToSelect, close, calculateCaloriesEaten);
    }
}


