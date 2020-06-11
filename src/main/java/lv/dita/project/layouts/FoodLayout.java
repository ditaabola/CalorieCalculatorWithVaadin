package lv.dita.project.layouts;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lv.dita.project.data.*;
import java.util.List;

public class FoodLayout extends VerticalLayout {

    private Grid<Food> grid;
    private MySqlDataRepository repo = new MySqlDataRepository();

    public FoodLayout() {

        grid = new Grid<Food>();
        Grid.Column<Food> colName = grid.addColumn(Food::getName).setHeader("Name").setSortable(true);
        Grid.Column<Food> colType = grid.addColumn(Food::getType).setHeader("Type").setSortable(true);
        Grid.Column<Food> colCalories = grid.addColumn(Food::getCaloriesPer100G).setHeader("Calories per 100 g").setSortable(true);
        List result = repo.getList(Food.class);
        grid.setItems(result);
        grid.setWidth("600px");
        grid.setHeightByRows(true);
        add(grid);
        setHorizontalComponentAlignment(Alignment.CENTER, grid);
    }
}
