package lv.dita.project.data.tabs.calculateBurntCalories;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import lv.dita.project.data.MySqlDataRepository;
import lv.dita.project.data.SessionHandler;
import lv.dita.project.data.interfaces.DataRepository;
import lv.dita.project.data.tabs.activitiesDatabase.Activity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ActivityLoggerList extends VerticalLayout {

    private DataRepository repo = new MySqlDataRepository();
    private Select<String> activityTypes = new Select<String>();
    private Select<String> activitiesByType = new Select<String>();
    private IntegerField hours = new IntegerField();
    private IntegerField minutes = new IntegerField();
    private NumberField userWeight = new NumberField();
    private Button addToSelect = new Button();
    private Button calculateCaloriesBurned = new Button();
    private Button cancel = new Button();
    private Label lblCommentResult = new Label();
    private Label lblCalorieCalculation = new Label();
    private Grid<ActivityPerformed> activitiesPerformedGrid;
    private ActivityPerformed newActivity;
    private int id = 0;
    private int catchId;
    private List<ActivityPerformed> activitiesPerformedList = new ArrayList<>();
    private Div addTable;
    private Label lblInfoAboutAdding = new Label();
    private Button deleteSelected;
    private String itemForDelete = "";
    private int numberId;
    private Component gridItemWithActivities = new HorizontalLayout();

    public ActivityLoggerList() {
        addClassName("activity-logger");

        Div addOptions = new Div();
        addOptions.add(createSelectOptionLayout());
        addOptions.setWidthFull();

        Div addButtons = new Div();
        addButtons.add(createButtonsLayout());
        addButtons.setWidthFull();

        Div addCalculation = new Div();
        addCalculation.add(createCalc());
        addCalculation.setWidthFull();

        Div addDelete = new Div();
        addDelete.add(createOneItemDeleteButton());

        add(addOptions);
        add(addButtons);
        add(addCalculation);
        add(addDelete);

        reloadGrid();
    }

    @Contract(" -> new")
    private @NotNull Component createGridFromActivitiesList() {

        activitiesPerformedGrid = new Grid<>();
        activitiesPerformedGrid.setItems(activitiesPerformedList);
        activitiesPerformedGrid.addColumn(ActivityPerformed::getName).setHeader("Name");
        activitiesPerformedGrid.addColumn(ActivityPerformed::getCalories).setHeader("Calories");
        activitiesPerformedGrid.addColumn(ActivityPerformed::getMinutes).setHeader("Minutes");

        activitiesPerformedGrid.setSelectionMode(Grid.SelectionMode.SINGLE);

        activitiesPerformedGrid.getSelectionModel().addSelectionListener(e -> {
            deleteSelected.setEnabled(true);
            numberId = e.getFirstSelectedItem().get().getId();

        });

        gridItemWithActivities = new HorizontalLayout(activitiesPerformedGrid);
        return gridItemWithActivities;
    }

    private void createAddActivityToGridButton() {
        lblInfoAboutAdding.setVisible(false);
        addToSelect.setText("Add activity");
        addToSelect.addClickShortcut(Key.ENTER);
        addToSelect.addClickListener(e -> {
            if (hours.isEmpty() & minutes.isEmpty() || userWeight.isEmpty() || activitiesByType.isEmpty()) {
                //Notification.show("Please enter data!").setDuration(1000);
                lblInfoAboutAdding.setVisible(true);
                lblInfoAboutAdding.setText("Please enter data!");
            } else {
                lblInfoAboutAdding.setVisible(true);

                //veco grid dzēšam ar activitiesList
                remove(addTable);

                //pievieno aktivitati no jaunizveidotas + GRID add
                addActivityToListReturningList(createActivity());
                id++;

                reloadGrid();

                //notira ievades laukus
                clearFields();

                lblInfoAboutAdding.setText("Activity added");
            }
        });
        add(addToSelect);
    }

    private void reloadGrid() {
        addTable = new Div();
        addTable.add(createGridFromActivitiesList());
        addTable.setWidthFull();
        add(addTable);
    }

    private void clearFields() {
        activityTypes.clear();
        activitiesByType.clear();
        hours.clear();
        minutes.clear();
    }

    private ActivityPerformed createActivity() {
        String name = activitiesByType.getValue();
        double met = repo.getMetValueByName1(activitiesByType.getValue());
        double weight = userWeight.getValue();
        if (hours.getValue() == null) {
            hours.setValue(0);
        }
        if (minutes.getValue() == null) {
            minutes.setValue(0);
        }
        DecimalFormat df = new DecimalFormat("##.##");
        int time = hours.getValue() * 60 + minutes.getValue();
        double calories = Math.round((met * 3.5 * weight)) / 200 * time;



        newActivity = new ActivityPerformed(activitiesPerformedList.size(), name, time, calories);

        return newActivity;
    }

    private List<ActivityPerformed> addActivityToListReturningList(ActivityPerformed newActivity) {
        activitiesPerformedList.add(newActivity);
        return activitiesPerformedList;
    }

    private void createWeightField() {
        userWeight.setLabel("Weight in kg");
        userWeight.setWidth("100px");
        userWeight.setMin(2d);
        userWeight.getValue();
    }

    private void createMinutesField() {
        minutes.setLabel("Minutes");
        minutes.setRequiredIndicatorVisible(false);
        minutes.setWidth("100px");
        minutes.setMin(0);
    }

    private void createHoursField() {
        hours.setLabel("Hours");
        hours.setRequiredIndicatorVisible(false);
        hours.setWidth("100px");
        hours.setMin(0);
    }

    // izvēlas tipu (type)
    private void creatingTypeSelectOption() {
        activityTypes.setLabel("Select activity's type");
        List<ActivityType> result = repo.getList(ActivityType.class);
        activityTypes.setItems(result.stream().map(s -> s.getType()));
        activityTypes.addValueChangeListener(e -> {
            List<Activity> items = repo.getActivityByType(e.getValue());
            activitiesByType.setItems(items.stream().map(s -> s.getName()));
        });
    }

    // izvēlas aktivitāti (atkarigs no tipa) (activity)
    private void creatingActivityNameSelectFromTypeOption() {
        activitiesByType.setLabel("Select activity");
        List<Activity> res = repo.getList(Activity.class);
        activitiesByType.setItems(res.stream().map(s -> s.getName()));
    }

    @Contract(" -> new")
    private @NotNull Component createSelectOptionLayout() {
        creatingTypeSelectOption();
        creatingActivityNameSelectFromTypeOption();
        createWeightField();
        createHoursField();
        createMinutesField();
        return new HorizontalLayout(activityTypes, activitiesByType, userWeight, hours, minutes);
    }

    @Contract(" -> new")
    private @NotNull Component createButtonsLayout() {
        createAddActivityToGridButton();
        return new HorizontalLayout(addToSelect, lblInfoAboutAdding);
    }

    @Contract(" -> new")
    private @NotNull Component createCalc() {
        createCaloriesCalculationButton();
        return new HorizontalLayout(calculateCaloriesBurned, lblCalorieCalculation);
    }

    private void createCaloriesCalculationButton() {
        calculateCaloriesBurned.setText("Calculate burnt calories");
        calculateCaloriesBurned.addClickListener(e -> {
            String res = calculateCaloriesBurnedFromList();
            lblCalorieCalculation.setText("The total of the calories you have burnt is: " + res);
            lblCalorieCalculation.setVisible(true);
        });
    }

    public String calculateCaloriesBurnedFromList() {
        double calories = Math.round(0);
        DecimalFormat df = new DecimalFormat("##.##");
        for (ActivityPerformed act : activitiesPerformedList) {
            calories += act.getCalories();
            SessionHandler.setActivitiesCalories(calories);
        }
        return df.format(calories);
    }

    @Contract(" -> new")
    private Component createOneItemDeleteButton() {
        deleteSelected = new Button();
        deleteSelected.setText("Delete selected activity");
        deleteSelected.setEnabled(false);

        deleteSelected.addClickListener(e -> {
            activitiesPerformedList.remove(numberId);
            addTable.remove(gridItemWithActivities);
            changeIdInList();
            reloadGrid();
            deleteSelected.setEnabled(false);
        });
        return deleteSelected;
    }


    public void changeIdInList(){
        for (ActivityPerformed act : activitiesPerformedList) {
        for(int i=0; i<activitiesPerformedList.size(); i++) {

            act.setId(i);
        }
        }
    }
}