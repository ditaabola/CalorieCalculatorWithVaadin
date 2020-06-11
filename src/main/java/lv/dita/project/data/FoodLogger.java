package lv.dita.project.data;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lv.dita.project.data.interfaces.DataRepository;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;


public class FoodLogger extends VerticalLayout {

    private DataRepository repo = new MySqlDataRepository();
    private Select<String> foodTypes = new Select<String>();
    private Select<String> foodItemsByType = new Select<String>();
    private NumberField quantityEaten = new NumberField();
    private NumberField calories = new NumberField();
    private Button addToSelect = new Button();
    private Button deleteSelected = new Button();
    private Button calculateCaloriesEaten = new Button();
    private Button cancel = new Button();
    private Label lblCalorieCalculation = new Label();
    private Grid<FoodEaten> gridEaten;
    private Label lblEnterItemSuccess = new Label();
    private Label lblEnterItemWarning = new Label();
    private String itemForDelete = "";

    public FoodLogger() {
        addClassName("food-logger");

        Div addOptions = new Div();
        addOptions.add(createSelectOptionLayout(), lblEnterItemSuccess, lblEnterItemWarning);
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

        createCaloriesCalculationButton();
        add(addOptions);
        add(addButtons);
        add(addCalculation);
        add(addTable);
        deleteItem();
        setHorizontalComponentAlignment(Alignment.CENTER, addOptions, addButtons, addCalculation);

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
        repo.emptyEatenTable();
        return new HorizontalLayout(gridEaten);
    }

    @Contract(" -> new")
    private @NotNull Component createCalc() {
        createCaloriesCalculationButton();
        return new HorizontalLayout(calculateCaloriesEaten, lblCalorieCalculation);
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
        foodItemsByType.addValueChangeListener(e -> {
            calories.setValue(repo.getCaloriesByName(foodItemsByType.getValue()));
        });
    }

    private void createQuantityField() {
        quantityEaten.setLabel("Enter the quantity in grams");
        quantityEaten.setWidth("250px");
        quantityEaten.setMin(2d);
    }

    public String calculateCalories() {
        double quantity = 0;
        double caloriesPer100G = 0;
        double caloriesEaten = 0;
        DecimalFormat df = new DecimalFormat("##.##");
        List<FoodEaten> eatenFoodList = repo.getList(FoodEaten.class);
        for (FoodEaten eatenFood : eatenFoodList) {
            quantity = eatenFood.getQuantity();
            caloriesPer100G = eatenFood.getCalories();
            double caloriesPerFoodEaten = (caloriesPer100G / 100) * quantity;
            caloriesEaten += caloriesPerFoodEaten;
            if (caloriesEaten == 0) {
                SessionHandler.setFoodCalories(0);
            } else {
                SessionHandler.setFoodCalories(caloriesEaten);
            }
        }
        return df.format(caloriesEaten);

    }

    public void createCaloriesCalculationButton() {
        calculateCaloriesEaten.setText("Calculate calories eaten");
        calculateCaloriesEaten.addClickListener(e -> {
            String res = calculateCalories();
            lblCalorieCalculation.setText("The total of the calories you have eaten during the day is: " + res);
            lblCalorieCalculation.setVisible(true);
        });
    }

    @Contract(" -> new")
    private @NotNull Component createButtonsLayout() {
        createAddFoodToGridButton();
//        createResetChoiceButton();
        return new HorizontalLayout(addToSelect);
    }

    public void createAddFoodToGridButton() {

        addToSelect.setText("Add this food item");
//        addToSelect.addClickShortcut(Key.ENTER);
        addToSelect.addClickListener(e -> {
            if (foodItemsByType.isEmpty() || quantityEaten.isEmpty()) {
                lblEnterItemSuccess.setVisible(false);
                lblEnterItemWarning.setVisible(true);
                lblEnterItemWarning.setText("Please select food item and / or quantity eaten!");
            } else {
                repo.addFoodEaten(new FoodEaten(0,
                        foodItemsByType.getValue(),
                        quantityEaten.getValue(),
                        calories.getValue()));
                loadData();
                lblEnterItemSuccess.setText("The food item added");
                lblEnterItemSuccess.setVisible(true);
                lblEnterItemWarning.setVisible(false);
                clearFields();
                lblCalorieCalculation.setVisible(false);
            }
        });
        add(addToSelect);
    }

//    private void createResetChoiceButton() {
//        cancel.setText("Reset the choice");
//        cancel.addClickListener(e -> {
//            repo.emptyEatenTable();
//            clearFields();
//            loadData();
//            calculateCalories();
//        });
//    }

    private void createOneItemDeleteButton() {
        deleteSelected.setText("Delete the selected choice");
        deleteSelected.setVisible(false);
        add(deleteSelected);
    }

    private void clearFields() {
        foodTypes.clear();
        foodItemsByType.clear();
        quantityEaten.clear();
    }

    private void deleteItem() {
        createOneItemDeleteButton();
        gridEaten.addSelectionListener(selectionEvent -> {
            deleteSelected.setVisible(true);
            Optional<FoodEaten> chosenFood = selectionEvent.getFirstSelectedItem();
            chosenFood.ifPresent(e -> {
                itemForDelete = chosenFood.get().getName();
                deleteSelected.addClickListener(event -> {
                    repo.deleteItemFromFoodEatenTable(itemForDelete);
                    loadData();
                    deleteSelected.setVisible(false);
                });
            });
        });
    }

    private void createTable() {
        repo.emptyEatenTable();
        gridEaten = new Grid<FoodEaten>();
        Grid.Column<FoodEaten> colName = gridEaten.addColumn(FoodEaten::getName).setHeader("Food item");
        Grid.Column<FoodEaten> colQuantity = gridEaten.addColumn(FoodEaten::getQuantity).setHeader("Quantity eaten");
        loadData();
        colName.setKey("name");
        colQuantity.setKey("quantity");
        gridEaten.setWidth("400px");
        gridEaten.setHeightByRows(true);
        gridEaten.setVisible(true);
        gridEaten.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
        gridEaten.setSelectionMode(Grid.SelectionMode.SINGLE);

    }

    private void loadData() {
        List result = repo.getList(FoodEaten.class);
        gridEaten.setItems(result);
        add(gridEaten);
    }

}

