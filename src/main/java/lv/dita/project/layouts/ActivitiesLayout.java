package lv.dita.project.layouts;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import lv.dita.project.data.Activity;
import lv.dita.project.data.ActivityListByCalories;
import lv.dita.project.data.MySqlDataRepository;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesLayout extends HorizontalLayout {

    private MySqlDataRepository repo = new MySqlDataRepository();
    private Grid<Activity> grid;
    private Grid<ActivityListByCalories> gridWithCalories;
    private List<ActivityListByCalories> list;
    private IntegerField time;
    private NumberField weight;
    private NumberField calories;
    private Button btnFindOptions;
    private Div addGridCal;
    private Component tableWithCalories = new HorizontalLayout();
    private Label info = new Label();


    public ActivitiesLayout() {
        addClassName("activity");

        Div addGrid = new Div();
        addGrid.add(createGrid());
        addGrid.setWidthFull();

        addGridCal = new Div();
        addGridCal.add(createInputFields());
        addGridCal.add(createButton());
        addGridCal.setWidthFull();

        add(addGrid);
        add(addGridCal);
    }

    @Contract(" -> new")
    private @NotNull Component createGrid() {
        grid = new Grid<>();

        Grid.Column<Activity> colName = grid.addColumn(Activity::getName).setHeader("Name").setSortable(true);
        Grid.Column<Activity> colLevel = grid.addColumn(Activity::getLevel).setHeader("Level").setSortable(true);
        Grid.Column<Activity> colMetValue = grid.addColumn(Activity::getMetValue).setHeader("Met").setSortable(true);
        Grid.Column<Activity> colType = grid.addColumn(Activity::getType).setHeader("Type").setSortable(true);

        List result = repo.getList(Activity.class);
        grid.setItems(result);
        grid.setWidth("600px");
        grid.setHeightByRows(true);
        return new HorizontalLayout(grid);
    }

    @Contract(" -> new")
    private @NotNull Component createGridWithCalories() {

        gridWithCalories = new Grid<>(ActivityListByCalories.class);
        list = new ArrayList<>();
        list = repo.getListWithCalories(time.getValue(), weight.getValue(), calories.getValue());
        gridWithCalories.setItems(list);
        addGridCal.remove(tableWithCalories);
        tableWithCalories = new HorizontalLayout(gridWithCalories);
        return tableWithCalories;
    }

    @Contract(" -> new")
    private @NotNull Component createInputFields() {
        time = new IntegerField("Time for activity in minutes");
        time.setMin(1);
        time.setMinWidth("200px");
        weight = new NumberField("Your weight");
        weight.setMin(1d);
        calories = new NumberField("Calories to lose");
        calories.setMin(1d);
        return new HorizontalLayout(time, weight, calories);
    }

    @Contract(" -> new")
    private @NotNull Component createButton() {
        btnFindOptions = new Button("Find my options");
        btnFindOptions.addClickShortcut(Key.ENTER);
        info.setVisible(false);

        btnFindOptions.addClickListener(buttonClickEvent -> {
            if (time.isEmpty() || weight.isEmpty() || calories.isEmpty()){
                info.setText("Please enter data!");
                info.setVisible(true);
            }else{
                info.setText("");
            }
            addGridCal.add(createGridWithCalories());
        });

        return new HorizontalLayout(btnFindOptions, info);
    }



}

