package lv.dita.project.layouts;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lv.dita.project.data.*;

import java.util.List;

public class FoodLayout extends VerticalLayout {

    private Grid<Food> grid;
    private Grid<FoodEaten> gridEaten;
    private MySqlDataRepository repo = new MySqlDataRepository();
    private FoodLogger form;
    private Button showGrid;
    private Button hideGrid;

    public FoodLayout() {

        form = new FoodLogger();
        createGridWithSelectedValues();
        createGridWithData();
        Div content = new Div(grid, form, gridEaten);
        content.addClassName("content");
        content.setSizeFull();
        add(content, showGrid, hideGrid);

    }

    private void createGridWithData() {
        grid = new Grid<Food>();
        Grid.Column<Food> colName = grid.addColumn(Food::getName).setHeader("Name").setSortable(true);
        Grid.Column<Food> colType = grid.addColumn(Food::getType).setHeader("Type").setSortable(true);
        Grid.Column<Food> colCalories = grid.addColumn(Food::getCaloriesPer100G).setHeader("Calories per 100 g").setSortable(true);
        List result = repo.getList(Food.class);
        grid.setItems(result);
        grid.getColumns().forEach(column -> column.setAutoWidth(true));
        grid.setHeight("1000px");
        grid.setVisible(false);
        add(grid);
        hideGrid = new Button("Hide the food table");
        hideGrid.setVisible(false);
        showGrid = new Button("Show the food table", e -> {
            grid.setVisible(true);
            hideGrid.setVisible(true);
            showGrid.setVisible(false);
        });
        hideGrid.addClickListener(e -> {
            grid.setVisible(false);
            showGrid.setVisible(true);
        });

        add(showGrid);
        add(hideGrid);
    }

    public void createGridWithSelectedValues() {
        gridEaten = new Grid<FoodEaten>();
        Grid.Column<FoodEaten> colName = gridEaten.addColumn(FoodEaten::getName).setHeader("Name");
        Grid.Column<FoodEaten> colQuantity = gridEaten.addColumn(FoodEaten::getQuantity).setHeader("Quantity");
        List result = repo.getList(FoodEaten.class);
        gridEaten.setItems(result);
        gridEaten.getColumns().forEach(column -> column.setWidth("50px"));
        gridEaten.setHeightByRows(true);
        gridEaten.setVisible(true);
        add(gridEaten);

    }

}
