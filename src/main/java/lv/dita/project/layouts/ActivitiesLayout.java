package lv.dita.project.layouts;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lv.dita.project.data.Activity;
import lv.dita.project.data.MySqlDataRepository;

import java.util.List;

public class ActivitiesLayout extends VerticalLayout {

    private Grid<Activity> grid;

    public ActivitiesLayout() {

        grid = new Grid<>();

        Grid.Column<Activity> colName = grid.addColumn(Activity::getName).setHeader("Name").setSortable(true);
        Grid.Column<Activity> colLevel = grid.addColumn(Activity::getLevel).setHeader("Level").setSortable(true);
        Grid.Column<Activity> colMetValue = grid.addColumn(Activity::getMetValue).setHeader("MET value").setSortable(true);
        Grid.Column<Activity> colType = grid.addColumn(Activity::getType).setHeader("Type").setSortable(true);

        MySqlDataRepository repo = new MySqlDataRepository();
        List result = repo.getList(Activity.class);
        grid.setItems(result);
        grid.setWidth("600px");
        grid.setHeightByRows(true);
        add(grid);
    }
}

