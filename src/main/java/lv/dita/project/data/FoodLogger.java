package lv.dita.project.data;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.HorizontalAlign;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.NumberField;
import lv.dita.project.data.interfaces.DataRepository;
import lv.dita.project.layouts.FoodLayout;

import java.util.List;

public class FoodLogger extends FormLayout {

    private Food food;
    private DataRepository repo = new MySqlDataRepository();
    private Select<String> foodTypes = new Select<String>();
    private Select<String> foodItemsByType = new Select<String>();
    private NumberField quantityEaten = new NumberField();
    private Button addToSelect = new Button();
    private Button calculateCaloriesEaten = new Button();
    private Button cancel = new Button();
    private Label lblCommentBmi = new Label();

    public FoodLogger() {
        addClassName("food-logger");
        Div addOptions = new Div();
        addOptions.add(createSelectOptionLayout());
//        addOptions.setWidth("40px");
        addOptions.setWidthFull();
        Div addButtons = new Div();
        addButtons.add(createButtonsLayout());
//        addButtons.setWidth("40px");
        addButtons.setWidthFull();
        add(addOptions);
        add(addButtons);

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
        quantityEaten.setWidth("200px");
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
        add(calculateCaloriesEaten);
    }

    public void createAddFoodToGridButton() {
        addToSelect.setText("Add this food item");
        addToSelect.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addToSelect.addClickShortcut(Key.ENTER);
        addToSelect.addClickListener(e->{
            if (quantityEaten.isEmpty()){
                Notification.show("Please enter the quantity");
            } else {
                repo.addFoodEaten(new FoodEaten(0, foodItemsByType.getValue(), quantityEaten.getValue()));
                Notification.show("The food item added");

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
        });

        add(cancel);
    }

    private Component createButtonsLayout() {
        createAddFoodToGridButton();
        createResetChoiceButton();

        return new HorizontalLayout(addToSelect, cancel);
    }
}


